package dev.apexstudios.fantasyfurniture.necrolord.block;

import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.ShelfBlock;
import dev.apexstudios.fantasyfurniture.block.property.ShelfConnection;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NecrolordShelfBlock extends ShelfBlock {
    public static final VoxelShape LEFT_SHAPE = ApexShapes.join(
            box(12D, 4D, 13D, 15D, 6D, 16D),
            box(12.5D, 6D, 14D, 14.5D, 11D, 16D),
            box(12D, 11D, 13D, 15D, 14D, 16D),
            box(12.5D, 12D, 8D, 14.5D, 14D, 13D),
            box(0D, 14D, 0D, 16D, 16D, 16D)
    );

    public static final VoxelShape RIGHT_SHAPE = ApexShapes.join(
            box(1D, 4D, 13D, 4D, 6D, 16D),
            box(1.5D, 6D, 14D, 3.5D, 11D, 16D),
            box(1D, 11D, 13D, 4D, 14D, 16D),
            box(1.5D, 12D, 8D, 3.5D, 14D, 13D),
            box(0D, 14D, 0D, 16D, 16D, 16D)
    );

    public static final VoxelShape BOTH_SHAPE = box(0D, 14D, 0D, 16D, 16D, 16D);

    public static final VoxelShape SHAPE = ApexShapes.join(
            box(12D, 4D, 13D, 15D, 6D, 16D),
            box(12.5D, 6D, 14D, 14.5D, 11D, 16D),
            box(12D, 11D, 13D, 15D, 14D, 16D),
            box(12.5D, 12D, 8D, 14.5D, 14D, 13D),
            box(1D, 4D, 13D, 4D, 6D, 16D),
            box(1.5D, 6D, 14D, 3.5D, 11D, 16D),
            box(1D, 11D, 13D, 4D, 14D, 16D),
            box(1.5D, 12D, 8D, 3.5D, 14D, 13D),
            box(0D, 14D, 0D, 16D, 16D, 16D)
    );

    public static final Map<Direction, VoxelShape> LEFT_FACING_SHAPES = ApexShapes.rotateHorizontal(LEFT_SHAPE);
    public static final Map<Direction, VoxelShape> RIGHT_FACING_SHAPES = ApexShapes.rotateHorizontal(RIGHT_SHAPE);
    public static final Map<Direction, VoxelShape> BOTH_FACING_SHAPES = ApexShapes.rotateHorizontal(BOTH_SHAPE);
    public static final Map<Direction, VoxelShape> FACING_SHAPES = ApexShapes.rotateHorizontal(SHAPE);

    public NecrolordShelfBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(switch (blockState.getValue(ShelfConnection.PROPERTY)) {
            case LEFT -> LEFT_FACING_SHAPES;
            case RIGHT -> RIGHT_FACING_SHAPES;
            case BOTH -> BOTH_FACING_SHAPES;
            case NONE -> FACING_SHAPES;
        }, blockState, pos);
    }
}
