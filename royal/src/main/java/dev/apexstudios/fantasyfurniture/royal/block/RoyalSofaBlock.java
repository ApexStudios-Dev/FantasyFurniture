package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.SofaBlock;
import dev.apexstudios.fantasyfurniture.block.property.SofaConnection;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class RoyalSofaBlock extends SofaBlock {
    public static final VoxelShape LEFT_SHAPE = ApexShapes.join(
            box(13D, 0D, 1D, 15D, 3D, 4D),
            box(13D, 0D, 12D, 15D, 3D, 15D),
            box(13D, 3D, 12D, 15D, 4D, 14D),
            box(13D, 3D, 2D, 15D, 4D, 4D),
            box(0D, 4D, 1.5D, 15D, 6D, 14.5D),
            box(0D, 6D, 12D, 15D, 13D, 14D),
            box(0D, 13D, 12D, 14D, 15D, 14D),
            box(0D, 15D, 12D, 12D, 16D, 14D),
            box(13D, 6D, 2D, 15D, 9D, 12D)
    );

    public static final VoxelShape RIGHT_SHAPE = ApexShapes.join(
            box(1D, 0D, 1D, 3D, 3D, 4D),
            box(1D, 0D, 12D, 3D, 3D, 15D),
            box(1D, 3D, 12D, 3D, 4D, 14D),
            box(1D, 3D, 2D, 3D, 4D, 4D),
            box(1D, 4D, 1.5D, 16D, 6D, 14.5D),
            box(1D, 6D, 12D, 16D, 13D, 14D),
            box(2D, 13D, 12D, 16D, 15D, 14D),
            box(4D, 15D, 12D, 16D, 16D, 14D),
            box(1D, 6D, 2D, 3D, 9D, 12D)
    );

    public static final VoxelShape BOTH_SHAPE = ApexShapes.join(
            box(0D, 4D, 1.5D, 16D, 6D, 14.5D),
            box(0D, 6D, 12D, 16D, 16D, 14D)
    );

    public static final VoxelShape CORNER_SHAPE = ApexShapes.join(
            box(.5D, 0D, .5D, 4.25D, 4D, 4.25D),
            box(11.5D, 0D, 11.25D, 15.25D, 4D, 15D),
            box(12D, 0D, 1D, 15D, 3D, 3D),
            box(12D, 3D, 1D, 14D, 4D, 3D),
            box(1D, 3D, 12D, 3D, 4D, 14D),
            box(1D, 0D, 12D, 3D, 3D, 15D),
            box(0D, 4D, 1.5D, 1.5D, 6D, 14.5D),
            box(1.5D, 4D, 0D, 14.5D, 6D, 14.5D),
            box(12D, 6D, 0D, 14D, 16D, 14D),
            box(0D, 6D, 12D, 12D, 16D, 14D)
    );

    public static final VoxelShape SHAPE = ApexShapes.join(
            box(13D, 0D, 12D, 15D, 3D, 15D),
            box(13D, 0D, 1D, 15D, 3D, 4D),
            box(1D, 0D, 1D, 3D, 3D, 4D),
            box(1D, 0D, 12D, 3D, 3D, 15D),
            box(1D, 3D, 12D, 3D, 4D, 14D),
            box(13D, 3D, 12D, 15D, 4D, 14D),
            box(13D, 3D, 2D, 15D, 4D, 4D),
            box(1D, 3D, 2D, 3D, 4D, 4D),
            box(1D, 4D, 1.5D, 15D, 6D, 14.5D),
            box(13D, 6D, 2D, 15D, 9D, 12D),
            box(1D, 6D, 2D, 3D, 9D, 12D),
            box(1D, 6D, 12D, 15D, 13D, 14D),
            box(2D, 13D, 12D, 14D, 15D, 14D),
            box(4D, 15D, 12D, 12D, 16D, 14D)
    );

    public static final Map<Direction, VoxelShape> LEFT_FACING_SHAPES = ApexShapes.rotateHorizontal(LEFT_SHAPE);
    public static final Map<Direction, VoxelShape> RIGHT_FACING_SHAPES = ApexShapes.rotateHorizontal(RIGHT_SHAPE);
    public static final Map<Direction, VoxelShape> BOTH_FACING_SHAPES = ApexShapes.rotateHorizontal(BOTH_SHAPE);
    public static final Map<Direction, VoxelShape> CORNER_FACING_SHAPES = ApexShapes.rotateHorizontal(CORNER_SHAPE);
    public static final Map<Direction, VoxelShape> FACING_SHAPES = ApexShapes.rotateHorizontal(SHAPE);

    public RoyalSofaBlock(Properties properties) {
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
