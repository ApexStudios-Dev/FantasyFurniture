package dev.apexstudios.fantasyfurniture.venthyr.block;

import dev.apexstudios.fantasyfurniture.block.ChandelierBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrChandelierBlock extends ChandelierBlock {
    public static final VoxelShape SHAPE = box(1D, 0D, 1D, 15, 16D, 15D);

    public VenthyrChandelierBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
