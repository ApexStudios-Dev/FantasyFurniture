package dev.apexstudios.fantasyfurniture.nordic.block;

import dev.apexstudios.fantasyfurniture.block.ChandelierBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NordicChandelierBlock extends ChandelierBlock {
    public static final VoxelShape SHAPE = box(1D, 0D, 1D, 15, 16D, 15D);

    public NordicChandelierBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
