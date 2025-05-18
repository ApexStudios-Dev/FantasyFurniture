package dev.apexstudios.fantasyfurniture.dunmer.block;

import dev.apexstudios.fantasyfurniture.block.ChandelierBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class DunmerChandelierBlock extends ChandelierBlock {
    public static final VoxelShape SHAPE = box(1D, 2D, 1D, 15D, 16D, 15D);

    public DunmerChandelierBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
