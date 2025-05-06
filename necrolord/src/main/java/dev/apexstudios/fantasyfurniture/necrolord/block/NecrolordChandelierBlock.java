package dev.apexstudios.fantasyfurniture.necrolord.block;

import dev.apexstudios.fantasyfurniture.block.ChandelierBlock;
import java.util.Map;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NecrolordChandelierBlock extends ChandelierBlock {
    public static final VoxelShape SHAPE = box(3D, 0D, 3D, 13D, 16D, 13D);
    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public NecrolordChandelierBlock(Properties properties) {
        super(properties);
    }
}
