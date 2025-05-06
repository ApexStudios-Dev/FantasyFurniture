package dev.apexstudios.fantasyfurniture.dunmer.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.CounterBlock;
import java.util.Map;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class DunmerCounterBlock extends CounterBlock {
    public static final VoxelShape CORNER_SHAPE = ApexShapes.join(
            box(0D, 0D, 0D, 2D, 1D, 2D),
            box(11D, 0D, 0D, 13D, 1D, 5D),
            box(13D, 0D, 3D, 16D, 1D, 5D),
            box(14D, 0D, 14D, 16D, 1D, 16D),
            box(0D, 0D, 14D, 2D, 1D, 16D),
            box(0D, 1D, 3D, 16D, 14D, 16D),
            box(0D, 1D, 0D, 13D, 14D, 3D),
            box(0D, 14D, 0D, 16D, 16D, 16D)
    );

    public static final VoxelShape SHAPE = ApexShapes.join(
            box(0D, 0D, 3D, 2D, 1D, 5D),
            box(0D, 0D, 14D, 2D, 1D, 16D),
            box(14D, 0D, 14D, 16D, 1D, 16D),
            box(14D, 0D, 3D, 16D, 1D, 5D),
            box(0D, 1D, 3D, 16D, 14D, 16D),
            box(0D, 14D, 0D, 16D, 16D, 16D)
    );

    public static final Map<Direction, VoxelShape> CORNER_FACING_SHAPES = Shapes.rotateHorizontal(CORNER_SHAPE);
    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public DunmerCounterBlock(Properties properties) {
        super(properties);
    }
}
