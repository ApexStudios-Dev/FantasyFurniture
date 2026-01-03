package dev.apexstudios.fantasyfurniture.venthyr.common.block;

import dev.apexstudios.fantasyfurniture.common.block.ChestBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrChestBlock extends ChestBlock {
    public static final VoxelShape SHAPE = box(-13D, 0D, 1D, 13D, 14.25D, 15D);

    public VenthyrChestBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
