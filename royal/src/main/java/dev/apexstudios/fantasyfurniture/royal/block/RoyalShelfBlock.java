package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.ShelfBlock;
import dev.apexstudios.fantasyfurniture.block.property.ShelfConnection;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class RoyalShelfBlock extends ShelfBlock {
    public static final VoxelShape LEFT_SHAPE = ApexShapes.join(
            box(0D, 14D, 0D, 16D, 16D, 16D),
            box(13D, 11D, 3D, 15D, 14D, 6D),
            box(13D, 12D, 6D, 15D, 14D, 16D),
            box(13D, 8D, 14D, 15D, 12D, 16D),
            box(13D, 5D, 13D, 15D, 8D, 16D)
    );

    public static final VoxelShape RIGHT_SHAPE = ApexShapes.join(
            box(0D, 14D, 0D, 16D, 16D, 16D),
            box(1D, 11D, 3D, 3D, 14D, 6D),
            box(1D, 12D, 6D, 3D, 14D, 16D),
            box(1D, 8D, 14D, 3D, 12D, 16D),
            box(1D, 5D, 13D, 3D, 8D, 16D)
    );

    public static final VoxelShape BOTH_SHAPE = box(0D, 14D, 0D, 16D, 16D, 16D);

    public static final VoxelShape SHAPE = ApexShapes.join(
            box(0D, 14D, 0D, 16D, 16D, 16D),
            box(13D, 11D, 3D, 15D, 14D, 6D),
            box(13D, 12D, 6D, 15D, 14D, 16D),
            box(13D, 8D, 14D, 15D, 12D, 16D),
            box(13D, 5D, 13D, 15D, 8D, 16D),
            box(1D, 5D, 13D, 3D, 8D, 16D),
            box(1D, 11D, 3D, 3D, 14D, 6D),
            box(1D, 12D, 6D, 3D, 14D, 16D),
            box(1D, 8D, 14D, 3D, 12D, 16D)
    );

    public static final Map<Direction, VoxelShape> LEFT_FACING_SHAPES = Shapes.rotateHorizontal(LEFT_SHAPE);
    public static final Map<Direction, VoxelShape> RIGHT_FACING_SHAPES = Shapes.rotateHorizontal(RIGHT_SHAPE);
    public static final Map<Direction, VoxelShape> BOTH_FACING_SHAPES = Shapes.rotateHorizontal(BOTH_SHAPE);
    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public RoyalShelfBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(switch (blockState.getValue(ShelfConnection.PROPERTY)) {
            case LEFT -> LEFT_FACING_SHAPES;
            case RIGHT -> RIGHT_FACING_SHAPES;
            case BOTH -> BOTH_FACING_SHAPES;
            case NONE -> FACING_SHAPES;
        }, blockState, facingProperty(), pos);
    }
}
