package dev.apexstudios.fantasyfurniture.common.ctm;

import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import dev.apexstudios.apexcore.api.data.ProviderType;
import dev.apexstudios.fantasyfurniture.common.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import dev.apexstudios.registree.api.Registree;
import java.util.Map;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.level.block.Block;

final class AthenaProvider extends ExtendedBlockStateProvider {
    public static final String ID = "athena";
    public static final ProviderType<AthenaProvider> PROVIDER_TYPE = ProviderType.register(FantasyFurniture.identifier("ctm/" + ID), AthenaProvider::new);

    public void with(Registree registree) {
        FurnitureUtil.Names.block(registree, FurnitureUtil.Names.CARPET, carpet -> {
            var wool = registree.getOrThrow(Registries.BLOCK, FurnitureUtil.Names.WOOL);
            var registryName = wool.key().identifier();
            var ctm = registryName.withPrefix("block/ctm/");

            var textures = Maps.<String, Identifier>newHashMap();
            textures.put("particle", registryName.withPrefix("block/"));
            textures.put("center", ctm.withSuffix("_center"));
            textures.put("empty", ctm.withSuffix("_empty"));
            textures.put("horizontal", ctm.withSuffix("_horizontal"));
            textures.put("vertical", ctm.withSuffix("_vertical"));

            with(wool.value(), "ctm", textures);
            with(carpet, "carpet", textures);
        });
    }

    private void with(Block block, String loader, Map<String, Identifier> textures) {
        with(block, root -> {
            root.addProperty(ID + ":loader", ID + ':' + loader);
            root.add("ctm_textures", Util.make(new JsonObject(), texturesJson -> textures.forEach((key, texture) -> texturesJson.addProperty(key, texture.toString()))));
        });
    }
}
