package dev.apexstudios.fantasyfurniture.necrolord.block;

import dev.apexstudios.fantasyfurniture.block.ChandelierBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NecrolordChandelierBlock extends ChandelierBlock {
    public static final VoxelShape SHAPE = box(3D, 0D, 3D, 13D, 16D, 13D);

    public NecrolordChandelierBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
