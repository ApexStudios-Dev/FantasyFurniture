package dev.apexstudios.fantasyfurniture.data;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import dev.apexstudios.fantasyfurniture.common.util.OptionalPacks;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.Util;
import net.neoforged.neoforge.common.NeoForgeMod;
import org.jspecify.annotations.Nullable;

final class ConTexProvider implements DataProvider {
    public static final String HORIZONTAL_SUFFIX = "_horizontal";
    public static final String VERTICAL_SUFFIX = "_vertical";
    public static final String CENTER_SUFFIX = "_center";
    public static final String EMPTY_SUFFIX = "_empty";

    private final Map<Identifier, Consumer<JsonObject>> map = Maps.newHashMap();
    private final PackOutput output;
    private final ResourceManager resourceManager;
    private final String modId;
    private final String definitionId;
    private final String metadataKey;
    private final String carpetKey;
    private final TexturesConsumer texturesConsumer;

    private ConTexProvider(PackOutput output, ResourceManager resourceManager, String modId, String definitionId, String metadataKey, String carpetKey, TexturesConsumer texturesConsumer) {
        this.output = output;
        this.resourceManager = resourceManager;
        this.modId = modId;
        this.definitionId = definitionId;
        this.metadataKey = metadataKey;
        this.carpetKey = carpetKey;
        this.texturesConsumer = texturesConsumer;
    }

    private void registerJsons() {
        FantasyFurnitureDataEntryPoint.forEachModule((modId, dyeable) -> {
            var woolId = Identifier.fromNamespaceAndPath(modId, FurnitureUtil.Names.WOOL);
            var texture = woolId.withPrefix("block/");
            var ctmBaseTexture = woolId.withPath(path -> "block/ctm/" + path);

            BiConsumer<JsonObject, Boolean> additional = null;

            if(dyeable) {
                additional = (root, isCarpet) -> withTexture(root, texture.withSuffix("_tint"), ctmBaseTexture.withSuffix("_tint"), isCarpet);
            }

            with(woolId, texture, ctmBaseTexture, false, additional);
            with(Identifier.fromNamespaceAndPath(modId, FurnitureUtil.Names.CARPET), texture, ctmBaseTexture, true, additional);
        });
    }

    private void with(Identifier blockId, Identifier texture, Identifier ctmBaseTexture, boolean isCarpet, @Nullable BiConsumer<JsonObject, Boolean> additional) {
        var existing = FantasyFurnitureDataEntryPoint.loadExisting(resourceManager, "blockstates", blockId);

        if(existing == null) {
            return;
        }

        Consumer<JsonObject> consumer = root -> {
            root.addProperty(NeoForgeMod.MOD_ID + Identifier.NAMESPACE_SEPARATOR + "definition_type", modId + Identifier.NAMESPACE_SEPARATOR + definitionId);
            withTexture(root, texture, ctmBaseTexture, isCarpet);

            if(additional != null) {
                additional.accept(root, isCarpet);
            }

            FantasyFurnitureDataEntryPoint.copyInto(existing, root);
        };

        if(map.putIfAbsent(blockId, consumer) != null) {
            throw new IllegalStateException("Duplicate Model registration: " + blockId);
        }
    }

    private void withTexture(JsonObject root, Identifier texture, Identifier ctmBaseTexture, boolean isCarpet) {
        var metaJson = metadata(root);

        var conTexJson = new JsonObject();
        var texturesJson = new JsonArray();
        var textureJson = new JsonObject();

        conTexJson.addProperty("type", modId + Identifier.NAMESPACE_SEPARATOR + (isCarpet ? carpetKey : "full"));
        texturesConsumer.accept(textureJson, texture, ctmBaseTexture);

        texturesJson.add(textureJson);
        conTexJson.add("textures", texturesJson);
        metaJson.add(conTexJson);
    }

    private JsonArray metadata(JsonObject root) {
        if(root.has(metadataKey)) {
            return root.getAsJsonArray(metadataKey);
        }

        var metadata = new JsonArray();
        root.add(metadataKey, metadata);
        return metadata;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        registerJsons();
        var futures = Lists.<CompletableFuture<?>>newArrayList();
        var pathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "blockstates");
        map.forEach((blockId, consumer) -> futures.add(DataProvider.saveStable(cache, Util.make(new JsonObject(), consumer), pathProvider.json(blockId))));
        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "ctm-provider/" + modId;
    }

    static DataProvider.Factory<ConTexProvider> create(ResourceManager resourceManager, OptionalPacks pack, String definitionId, String metadataKey, String carpetKey, TexturesConsumer texturesConsumer) {
        return output -> new ConTexProvider(output, resourceManager, Objects.requireNonNull(pack.modId), definitionId, metadataKey, carpetKey, texturesConsumer);
    }

    @FunctionalInterface
    public interface TexturesConsumer {
        void accept(JsonObject root, Identifier mainTexture, Identifier ctmBase);
    }
}
