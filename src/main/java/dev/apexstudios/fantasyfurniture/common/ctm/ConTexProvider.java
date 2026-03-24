package dev.apexstudios.fantasyfurniture.common.ctm;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.apexstudios.apexcore.api.data.ProviderType;
import dev.apexstudios.apexcore.api.data.provider.context.ProviderOutputContext;
import dev.apexstudios.fantasyfurniture.common.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import dev.apexstudios.registree.api.Registree;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.NeoForgeMod;

final class ConTexProvider extends ExtendedBlockStateProvider {
    public static final String XFACT_ID = "contex";
    public static final String SOARYN_ID = "context_matters";

    public static final ProviderType<ConTexProvider> XFACT_PROVIDER_TYPE = create(XFACT_ID, "definition", XFACT_ID + "_meta", "carpet_full", (textures, main, ctmBase) -> {
        textures.addProperty("main_texture", main.toString());

        textures.add("ct_textures", Util.make(new JsonObject(), ctmTextures -> {
            ctmTextures.addProperty("compact/hor", ctmBase.withSuffix("_horizontal").toString());
            ctmTextures.addProperty("compact/vert", ctmBase.withSuffix("_vertical").toString());
            ctmTextures.addProperty("compact/cross", ctmBase.withSuffix("_center").toString());
            ctmTextures.addProperty("compact/full", ctmBase.withSuffix("_empty").toString());
        }));
    });

    public static final ProviderType<ConTexProvider> SOARYN_PROVIDER_TYPE = create(SOARYN_ID, "connected_definition", "metadata", "carpet_full_down", (textures, main, ctmBase) -> {
        textures.addProperty("none", main.toString());
        textures.addProperty("horizontal", ctmBase.withSuffix("_horizontal").toString());
        textures.addProperty("vertical", ctmBase.withSuffix("_vertical").toString());
        textures.addProperty("full", ctmBase.withSuffix("_empty").toString());
        textures.addProperty("cardinal", ctmBase.withSuffix("_center").toString());
    });

    private final String modId;
    private final String definitionId;
    private final String metadataKey;
    private final String carpetKey;
    private final TexturesConsumer texturesConsumer;

    private ConTexProvider(String modId, String definitionId, String metadataKey, String carpetKey, TexturesConsumer texturesConsumer) {
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
    protected JsonObject json(ProviderOutputContext context, Block block, Consumer<JsonObject> consumer) {
        var root = super.json(context, block, consumer);
        root.addProperty(NeoForgeMod.MOD_ID + Identifier.NAMESPACE_SEPARATOR + "definition_type", modId + Identifier.NAMESPACE_SEPARATOR + definitionId);
        return root;
    }

    private static ProviderType<ConTexProvider> create(String modId, String definitionId, String metadataKey, String carpetKey, TexturesConsumer texturesConsumer) {
        return ProviderType.register(FantasyFurniture.identifier("ctm/" + modId), () -> new ConTexProvider(modId, definitionId, metadataKey, carpetKey, texturesConsumer));
    }

    @FunctionalInterface
    private interface TexturesConsumer {
        void accept(JsonObject root, Identifier mainTexture, Identifier ctmBase);
    }
}
