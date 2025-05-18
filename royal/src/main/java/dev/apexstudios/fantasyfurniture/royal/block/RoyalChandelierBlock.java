package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.fantasyfurniture.block.ChandelierBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class RoyalChandelierBlock extends ChandelierBlock {
    public static final VoxelShape SHAPE = box(0D, 0D, 0D, 16D, 16D, 16D);

    public RoyalChandelierBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
