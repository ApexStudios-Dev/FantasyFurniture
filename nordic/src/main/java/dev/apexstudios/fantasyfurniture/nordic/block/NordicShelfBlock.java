package dev.apexstudios.fantasyfurniture.nordic.block;

import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.ShelfBlock;
import dev.apexstudios.fantasyfurniture.block.property.ShelfConnection;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NordicShelfBlock extends ShelfBlock {
    public static final VoxelShape LEFT_SHAPE = ApexShapes.join(
            box(13.5D, 9D, 2D, 15.5D, 14D, 13D),
            box(0D, 14D, 0D, 16D, 16D, 16D),
            box(13D, 6D, 13D, 16D, 14D, 16D)
    );

    public static final VoxelShape RIGHT_SHAPE = ApexShapes.join(
            box(.5D, 9D, 2D, 2.5D, 14D, 13D),
            box(0D, 14D, 0D, 16D, 16D, 16D),
            box(0D, 6D, 13D, 3D, 14D, 16D)
    );

    public static final VoxelShape BOTH_SHAPE = box(0D, 14D, 0D, 16D, 16D, 16D);

    public static final VoxelShape SHAPE = ApexShapes.join(
            box(.5D, 9D, 2D, 2.5D, 14D, 13D),
            box(13.5D, 9D, 2D, 15.5D, 14D, 13D),
            box(0D, 14D, 0D, 16D, 16D, 16D),
            box(13D, 6D, 13D, 16D, 14D, 16D),
            box(0D, 6D, 13D, 3D, 14D, 16D)
    );

    public static final Map<Direction, VoxelShape> LEFT_FACING_SHAPES = ApexShapes.rotateHorizontal(LEFT_SHAPE);
    public static final Map<Direction, VoxelShape> RIGHT_FACING_SHAPES = ApexShapes.rotateHorizontal(RIGHT_SHAPE);
    public static final Map<Direction, VoxelShape> BOTH_FACING_SHAPES = ApexShapes.rotateHorizontal(BOTH_SHAPE);
    public static final Map<Direction, VoxelShape> FACING_SHAPES = ApexShapes.rotateHorizontal(SHAPE);

    public NordicShelfBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        return getShape(switch (blockState.getValue(ShelfConnection.PROPERTY)) {
            case LEFT -> LEFT_FACING_SHAPES;
            case RIGHT -> RIGHT_FACING_SHAPES;
            case BOTH -> BOTH_FACING_SHAPES;
            case NONE -> FACING_SHAPES;
        }, blockState, pos);
    }
}
