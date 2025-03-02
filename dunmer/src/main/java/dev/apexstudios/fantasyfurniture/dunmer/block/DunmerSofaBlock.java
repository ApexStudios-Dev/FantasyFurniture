package dev.apexstudios.fantasyfurniture.dunmer.block;

import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.SofaBlock;
import dev.apexstudios.fantasyfurniture.block.property.SofaConnection;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class DunmerSofaBlock extends SofaBlock {
    public static final VoxelShape LEFT_SHAPE = ApexShapes.join(
            box(0D, 4D, 1D, 16D, 6D, 15D),
            box(0D, 6D, 2D, 13D, 7D, 12D),
            box(0D, 6D, 12D, 15D, 16D, 14D),
            box(13D, 10D, 2D, 15D, 12D, 12D),
            box(13D, 6D, 3D, 15D, 10D, 12D),
            box(12D, 0D, 12D, 14D, 4D, 14D),
            box(12D, 0D, 2D, 14D, 4D, 4D)
    );

    public static final VoxelShape RIGHT_SHAPE = ApexShapes.join(
            box(0D, 4D, 1D, 16D, 6D, 15D),
            box(3D, 6D, 2D, 16D, 7D, 12D),
            box(1D, 6D, 12D, 16D, 16D, 14D),
            box(1D, 10D, 2D, 3D, 12D, 12D),
            box(1D, 6D, 3D, 3D, 10D, 12D),
            box(2D, 0D, 12D, 4D, 4D, 14D),
            box(2D, 0D, 2D, 4D, 4D, 4D)
    );

    public static final VoxelShape BOTH_SHAPE = ApexShapes.join(
            box(0D, 4D, 1D, 16D, 6D, 15D),
            box(0D, 6D, 2D, 16D, 7D, 12D),
            box(0D, 6D, 12D, 16D, 16D, 14D)
    );

    public static final VoxelShape CORNER_SHAPE = ApexShapes.join(
            box(2D, 0D, 12D, 4D, 4D, 14D),
            box(2D, 0D, 2D, 4D, 4D, 4D),
            box(12D, 0D, 2D, 14D, 4D, 4D),
            box(12D, 0D, 12D, 14D, 4D, 14D),
            box(12D, 6D, 12D, 14D, 16D, 14D),
            box(12D, 6D, 0D, 14D, 16D, 12D),
            box(0D, 6D, 12D, 12D, 16D, 14D),
            box(1D, 4D, 0D, 15D, 6D, 15D),
            box(0D, 4D, 1D, 1D, 6D, 15D),
            box(0D, 6D, 2D, 13D, 7D, 13D),
            box(2D, 6D, 0D, 13D, 7D, 2D)
    );

    public static final VoxelShape SHAPE = ApexShapes.join(
            box(0D, 4D, 1D, 16D, 6D, 15D),
            box(2D, 0D, 12D, 4D, 4D, 14D),
            box(2D, 0D, 2D, 4D, 4D, 4D),
            box(12D, 0D, 2D, 14D, 4D, 4D),
            box(12D, 0D, 12D, 14D, 4D, 14D),
            box(1D, 6D, 12D, 15D, 16D, 14D),
            box(13D, 10D, 2D, 15D, 12D, 12D),
            box(13D, 6D, 3D, 15D, 10D, 5D),
            box(1D, 6D, 3D, 3D, 10D, 5D),
            box(1D, 10D, 2D, 3D, 12D, 12D),
            box(3D, 6D, 2D, 13D, 7D, 13D)
    );

    public static final Map<Direction, VoxelShape> LEFT_FACING_SHAPES = ApexShapes.rotateHorizontal(LEFT_SHAPE);
    public static final Map<Direction, VoxelShape> RIGHT_FACING_SHAPES = ApexShapes.rotateHorizontal(RIGHT_SHAPE);
    public static final Map<Direction, VoxelShape> BOTH_FACING_SHAPES = ApexShapes.rotateHorizontal(BOTH_SHAPE);
    public static final Map<Direction, VoxelShape> CORNER_FACING_SHAPES = ApexShapes.rotateHorizontal(CORNER_SHAPE);
    public static final Map<Direction, VoxelShape> FACING_SHAPES = ApexShapes.rotateHorizontal(SHAPE);

    public DunmerSofaBlock(Properties properties) {
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
