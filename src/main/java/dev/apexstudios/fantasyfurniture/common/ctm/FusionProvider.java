package dev.apexstudios.fantasyfurniture.common.ctm;

import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.apexstudios.apexcore.api.data.ProviderType;
import dev.apexstudios.apexcore.api.data.provider.context.ProviderOutputContext;
import dev.apexstudios.apexcore.common.ApexCore;
import dev.apexstudios.fantasyfurniture.common.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import dev.apexstudios.registree.registrar.BlockRegistrar;
import java.nio.file.Path;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.level.block.Block;

final class FusionProvider extends SimpleJsonProvider {
    public static final String ID = "fusion";
    public static final ProviderType<FusionProvider> PROVIDER_TYPE = ProviderType.register(FantasyFurniture.identifier("ctm/" + ID), FusionProvider::new);

    private final Set<Identifier> textures = Sets.newHashSet();

    public void with(BlockRegistrar blocks) {
        FurnitureUtil.Names.block(blocks, FurnitureUtil.Names.CARPET, carpet -> {
            var wool = blocks.getValueOrThrow(FurnitureUtil.Names.WOOL);
            var tinted = isDyeable(blocks);

            with(wool, root -> model(root, wool, tinted, "minecraft:block/cube_all", ApexCore.id("block/cube_all_tinted"), "all"));
            with(carpet, root -> model(root, wool, tinted, "minecraft:block/carpet", ApexCore.id("block/carpet_tinted"), "wool"));
        });
    }

    private void model(JsonObject root, Block wool, boolean tinted, String model, String tintModel, String textureKey) {
        root.addProperty("parent", tinted ? tintModel : model);
        root.add("textures", Util.make(new JsonObject(), texturesJson -> {
            var texture = registryName(wool).withPath(path -> "block/ctm/" + path + "_simple_vertical");

            textures.add(texture);
            texturesJson.addProperty(textureKey, texture.toString());

            if(tinted) {
                var textureTint = registryName(wool).withPath(path -> "block/ctm/" + path + "_tint_simple_vertical");
                textures.add(textureTint);
                texturesJson.addProperty(textureKey + "_tinted", textureTint.toString());
            }
        }));
    }

    private JsonObject texture(Identifier texture) {
        var root = new JsonObject();

        root.addProperty("type", "connecting");
        root.addProperty("layout", "pieced");

        if (texture.getPath().contains("tint"))
            root.addProperty("render_type", "cutout");

        return root;
    }

    @Override
    protected JsonObject json(ProviderOutputContext context, Block block, Consumer<JsonObject> consumer) {
        var root = super.json(context, block, consumer);
        root.addProperty("type", "connecting");
        root.addProperty("loader", ID + ":model");
        root.add("connections", Util.make(new JsonArray(), $ -> $.add(Util.make(new JsonObject(), $$ -> $$.addProperty("type", "is_same_block")))));
        return root;
    }

    @Override
    protected Path path(ProviderOutputContext context, Block block) {
        return context.outputPath(PackOutput.Target.RESOURCE_PACK, registryName(block).withPath(path -> "models/block/" + path + ".json"));
    }

    @Override
    public CompletableFuture<?> generate(CachedOutput cache, ProviderOutputContext context) {
        return CompletableFuture.allOf(super.generate(cache, context), generateTextures(cache, context));
    }

    private CompletableFuture<?> generateTextures(CachedOutput cache, ProviderOutputContext context) {
        var pathProvider = context.pathProvider(PackOutput.Target.RESOURCE_PACK, "textures");
        return CompletableFuture.allOf(textures.stream()
                .map(texture -> DataProvider.saveStable(cache, Util.make(new JsonObject(), root -> root.add(ID, texture(texture))), pathProvider.file(texture, "png.mcmeta")))
                .toArray(CompletableFuture[]::new)
        );
    }
}
