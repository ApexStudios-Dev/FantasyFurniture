package dev.apexstudios.fantasyfurniture.dunmer.block;

import dev.apexstudios.fantasyfurniture.block.ShelfBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class DunmerShelfBlock extends ShelfBlock {
    public static final VoxelShape LEFT_SHAPE = box(13D, 8D, 14D, 15D, 14D, 16D);
    public static final VoxelShape RIGHT_SHAPE = box(1D, 8D, 14D, 3D, 14D, 16D);
    public static final VoxelShape TOP_SHAPE = box(0D, 14D, 0D, 16D, 16D, 16D);

    public DunmerShelfBlock(Properties properties) {
        super(properties, LEFT_SHAPE, RIGHT_SHAPE, TOP_SHAPE);
    }
}
