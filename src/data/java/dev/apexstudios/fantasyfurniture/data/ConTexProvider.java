package dev.apexstudios.fantasyfurniture.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import dev.apexstudios.fantasyfurniture.common.util.OptionalPacks;
import dev.apexstudios.registree.api.Registree;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.Util;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.NeoForgeMod;

final class ConTexProvider extends ExtendedBlockStateProvider {
    public static final String HORIZONTAL_SUFFIX = "_horizontal";
    public static final String VERTICAL_SUFFIX = "_vertical";
    public static final String CENTER_SUFFIX = "_center";
    public static final String EMPTY_SUFFIX = "_empty";

    private final String modId;
    private final String definitionId;
    private final String metadataKey;
    private final String carpetKey;
    private final TexturesConsumer texturesConsumer;

    private ConTexProvider(PackOutput output, ResourceManager resourceManager, String modId, String definitionId, String metadataKey, String carpetKey, TexturesConsumer texturesConsumer) {
        super(output, resourceManager);

        this.modId = modId;
        this.definitionId = definitionId;
        this.metadataKey = metadataKey;
        this.carpetKey = carpetKey;
        this.texturesConsumer = texturesConsumer;
    }

    public void with(Registree registree) {
        FurnitureUtil.Names.block(registree, FurnitureUtil.Names.CARPET, carpet -> {
            var wool = registree.getOrThrow(Registries.BLOCK, FurnitureUtil.Names.WOOL);
            var woolRegistryName = wool.key().identifier();
            var texture = woolRegistryName.withPrefix("block/");
            var ctmBaseTexture = woolRegistryName.withPath(path -> "block/ctm/" + path);
            BiConsumer<JsonObject, Boolean> additional = (root, isCarpet) -> { };

            if(isDyeable(registree)) {
                var tintTexture = woolRegistryName.withPath(path -> "block/" + path + "_tint");
                var tintCtmBaseTexture = woolRegistryName.withPath(path -> "block/ctm/" + path + "_tint");
                additional = (root, isCarpet) -> withTexture(root, tintTexture, tintCtmBaseTexture, isCarpet);
            }

            with(wool.value(), texture, ctmBaseTexture, false, additional);
            with(carpet, texture, ctmBaseTexture, true, additional);
        });
    }

    private void with(Block block, Identifier texture, Identifier ctmBaseTexture, boolean isCarpet, BiConsumer<JsonObject, Boolean> additional) {
        with(block, root -> {
            withTexture(root, texture, ctmBaseTexture, isCarpet);
            additional.accept(root, isCarpet);
        });
    }

    private void withTexture(JsonObject root, Identifier texture, Identifier ctmBaseTexture, boolean isCarpet) {
        var metaJson = metadata(root);

        metaJson.add(Util.make(new JsonObject(), conTexJson -> {
            conTexJson.addProperty("type", modId + ':' + (isCarpet ? carpetKey : "full"));
            conTexJson.add("textures", Util.make(new JsonArray(), texturesJson -> texturesJson.add(Util.make(new JsonObject(), textureJson -> texturesConsumer.accept(textureJson, texture, ctmBaseTexture)))));
        }));
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
    protected JsonObject json(Block block, Consumer<JsonObject> consumer) {
        var root = super.json(block, consumer);
        root.addProperty(NeoForgeMod.MOD_ID + Identifier.NAMESPACE_SEPARATOR + "definition_type", modId + Identifier.NAMESPACE_SEPARATOR + definitionId);
        return root;
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
