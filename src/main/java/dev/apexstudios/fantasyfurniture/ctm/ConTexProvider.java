package dev.apexstudios.fantasyfurniture.ctm;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.apexstudios.apexcore.lib.data.ProviderType;
import dev.apexstudios.apexcore.lib.data.provider.context.ProviderOutputContext;
import dev.apexstudios.fantasyfurniture.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.util.FurnitureUtil;
import dev.apexstudios.registree.api.Registree;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.internal.versions.neoforge.NeoForgeVersion;

final class ConTexProvider extends ExtendedBlockStateProvider {
    public static final String ID = "contex";
    public static final ProviderType<ConTexProvider> PROVIDER_TYPE = ProviderType.register(FantasyFurniture.identifier("ctm/" + ID), ConTexProvider::new);

    public void with(Registree registree) {
        FurnitureUtil.Names.block(registree, FurnitureUtil.Names.CARPET, carpet -> {
            var wool = registree.getOrThrow(Registries.BLOCK, FurnitureUtil.Names.WOOL);
            var woolRegistryName = wool.key().location();
            var texture = woolRegistryName.withPrefix("block/");
            var ctmTexture = woolRegistryName.withPath(path -> "block/ctm/" + path + "_simple");
            BiConsumer<JsonObject, Boolean> additional = (root, isCarpet) -> { };

            if(isDyeable(registree)) {
                var tintTexture = woolRegistryName.withPath(path -> "block/" + path + "_tint");
                var tintCtmTexture = woolRegistryName.withPath(path -> "block/ctm/" + path + "_tint_simple");
                additional = (root, isCarpet) -> withTexture(root, tintTexture, tintCtmTexture, isCarpet);
            }

            with(wool.value(), texture, ctmTexture, false, additional);
            with(carpet, texture, ctmTexture, true, additional);
        });
    }

    private void with(Block block, ResourceLocation texture, ResourceLocation ctmTexture, boolean isCarpet, BiConsumer<JsonObject, Boolean> additional) {
        with(block, root -> {
            withTexture(root, texture, ctmTexture, isCarpet);
            additional.accept(root, isCarpet);
        });
    }

    private void withTexture(JsonObject root, ResourceLocation texture, ResourceLocation ctmTexture, boolean isCarpet) {
        var metaKey = ID + "_meta";
        var metaJson = root.has(metaKey) ? root.getAsJsonArray(metaKey) : new JsonArray();

        metaJson.add(Util.make(new JsonObject(), conTexJson -> {
            conTexJson.addProperty("type", ID + ':' + (isCarpet ? "carpet_full" : "full"));
            conTexJson.add("textures", Util.make(new JsonArray(), texturesJson -> texturesJson.add(Util.make(new JsonObject(), textureJson -> {
                textureJson.addProperty("main_texture", texture.toString());
                textureJson.addProperty("ct_texture", ctmTexture.toString());
            }))));
        }));

        root.add(metaKey, metaJson);
    }

    @Override
    protected JsonObject json(ProviderOutputContext context, Block block, Consumer<JsonObject> consumer) {
        var root = super.json(context, block, consumer);
        root.addProperty(NeoForgeVersion.MOD_ID + ":definition_type", ID + ":definition");
        return root;
    }
}
