package dev.apexstudios.fantasyfurniture.venthyr.block;

import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.SofaBlock;
import dev.apexstudios.fantasyfurniture.block.property.SofaConnection;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrSofaBlock extends SofaBlock {
    public static final VoxelShape LEFT_SHAPE = ApexShapes.join(
            box(12D, 0D, 1D, 15D, 2D, 4D),
            box(12D, 0D, 12D, 15D, 2D, 15D),
            box(0D, 2D, 0D, 16D, 6D, 16D),
            box(13D, 6D, 0D, 16D, 10D, 13D),
            box(0D, 6D, 13D, 16D, 16D, 16D)
    );

    public static final VoxelShape RIGHT_SHAPE = ApexShapes.join(
            box(1D, 0D, 1D, 4D, 2D, 4D),
            box(1D, 0D, 12D, 4D, 2D, 15D),
            box(0D, 2D, 13D, 16D, 16D, 16D),
            box(0D, 2D, 0D, 3D, 10D, 13D),
            box(3D, 2D, 0D, 16D, 6D, 13D)
    );

    public static final VoxelShape BOTH_SHAPE = ApexShapes.join(
            box(0D, 2D, 0D, 16D, 6D, 16D),
            box(0D, 6D, 13D, 16D, 16D, 16D)
    );

    public static final VoxelShape CORNER_SHAPE = ApexShapes.join(
            box(1D, 0D, 1D, 4D, 2D, 4D),
            box(1D, 0D, 12D, 4D, 2D, 15D),
            box(12D, 0D, 12D, 15D, 2D, 15D),
            box(12D, 0D, 1D, 15D, 2D, 4D),
            box(13D, 6D, 0D, 16D, 16D, 16D),
            box(0D, 6D, 13D, 13D, 16D, 16D),
            box(0D, 2D, 0D, 16D, 6D, 16D)
    );

    public static final VoxelShape SHAPE = ApexShapes.join(
            box(1D, 0D, 1D, 4D, 2D, 4D),
            box(1D, 0D, 12D, 4D, 2D, 15D),
            box(12D, 0D, 12D, 15D, 2D, 15D),
            box(12D, 0D, 1D, 15D, 2D, 4D),
            box(0D, 2D, 0D, 16D, 6D, 16D),
            box(13D, 6D, 0D, 16D, 10D, 13D),
            box(0D, 6D, 0D, 3D, 10D, 13D),
            box(0D, 6D, 13D, 16D, 16D, 16D)
    );

    public static final Map<Direction, VoxelShape> LEFT_FACING_SHAPES = ApexShapes.rotateHorizontal(LEFT_SHAPE);
    public static final Map<Direction, VoxelShape> RIGHT_FACING_SHAPES = ApexShapes.rotateHorizontal(RIGHT_SHAPE);
    public static final Map<Direction, VoxelShape> BOTH_FACING_SHAPES = ApexShapes.rotateHorizontal(BOTH_SHAPE);
    public static final Map<Direction, VoxelShape> CORNER_FACING_SHAPES = ApexShapes.rotateHorizontal(CORNER_SHAPE);
    public static final Map<Direction, VoxelShape> FACING_SHAPES = ApexShapes.rotateHorizontal(SHAPE);

    public VenthyrSofaBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(switch (blockState.getValue(SofaConnection.PROPERTY)) {
            case LEFT -> LEFT_FACING_SHAPES;
            case RIGHT -> RIGHT_FACING_SHAPES;
            case BOTH -> BOTH_FACING_SHAPES;
            case CORNER_INNER, CORNER_OUTER -> CORNER_FACING_SHAPES;
            case NONE -> FACING_SHAPES;
        }, blockState, pos);
    }
}
