package dev.apexstudios.fantasyfurniture.nordic.common.block;

import dev.apexstudios.fantasyfurniture.common.block.ChestBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NordicChestBlock extends ChestBlock {
    public static final VoxelShape SHAPE = box(-15D, 0D, 2D, 15D, 14D, 16D);

    public NordicChestBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
