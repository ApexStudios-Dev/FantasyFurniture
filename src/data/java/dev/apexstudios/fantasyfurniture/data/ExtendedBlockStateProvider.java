package dev.apexstudios.fantasyfurniture.data;

import com.google.gson.JsonObject;
import java.nio.file.Path;
import java.util.function.Consumer;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.block.Block;

public abstract class ExtendedBlockStateProvider extends SimpleJsonProvider {
    protected ExtendedBlockStateProvider(PackOutput output, ResourceManager resourceManager) {
        super(output, resourceManager);
    }

    @Override
    protected Path path(Block block) {
        return path(PackOutput.Target.RESOURCE_PACK, blockStatePath(block));
    }

    @Override
    protected JsonObject json(Block block, Consumer<JsonObject> consumer) {
        var root = super.json( block, consumer);
        var blockState = existing(blockStatePath(block));
        copyInto(blockState, root);
        return root;
    }

    private static Identifier blockStatePath(Block block) {
        return registryName(block).withPath(path -> "blockstates/" + path + ".json");
    }
}
