package dev.apexstudios.fantasyfurniture.common.ctm;

import com.google.gson.JsonObject;
import dev.apexstudios.apexcore.api.data.provider.context.ProviderOutputContext;
import java.nio.file.Path;
import java.util.function.Consumer;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.block.Block;

public class ExtendedBlockStateProvider extends SimpleJsonProvider {
    @Override
    protected Path path(ProviderOutputContext context, Block block) {
        return context.outputPath(PackOutput.Target.RESOURCE_PACK, blockStatePath(block));
    }

    @Override
    protected JsonObject json(ProviderOutputContext context, Block block, Consumer<JsonObject> consumer) {
        var root = super.json(context, block, consumer);
        var resourceManager = context.getResourceManager(PackType.CLIENT_RESOURCES);
        var blockState = existing(resourceManager, blockStatePath(block));
        copyInto(blockState, root);
        return root;
    }

    private static Identifier blockStatePath(Block block) {
        return registryName(block).withPath(path -> "blockstates/" + path + ".json");
    }
}
