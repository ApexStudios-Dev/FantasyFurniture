package dev.apexstudios.fantasyfurniture.venthyr.block;

import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.CounterBlock;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureBlockComponentHolder;
import dev.apexstudios.fantasyfurniture.block.property.CounterConnection;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrCounterBlock extends CounterBlock {
    public static final VoxelShape CORNER_SHAPE = ApexShapes.join(
            box(0D, 0D, 0D, 13D, 13D, 4D),
            box(0D, 0D, 3D, 16D, 13D, 16D),
            box(0D, 13D, 0D, 16D, 16D, 16D)
    );

    public static final VoxelShape SHAPE = ApexShapes.join(
            box(0D, 0D, 3D, 16D, 13D, 16D),
            box(0D, 13D, 0D, 16D, 16D, 16D),
            box(1D, 1D, 2D, 15D, 12D, 3D)
    );

    public static final Map<Direction, VoxelShape> CORNER_FACING_SHAPES = ApexShapes.rotateHorizontal(CORNER_SHAPE);
    public static final Map<Direction, VoxelShape> FACING_SHAPES = ApexShapes.rotateHorizontal(SHAPE);

    public VenthyrCounterBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        return FurnitureBlockComponentHolder.getShape(switch (blockState.getValue(CounterConnection.PROPERTY)) {
            case CORNER_INNER, CORNER_OUTER -> CORNER_FACING_SHAPES;
            case NONE -> FACING_SHAPES;
        }, blockState, pos);
    }
}
