package dev.apexstudios.fantasyfurniture.venthyr.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.LockBoxBlock;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrLockBoxBlock extends LockBoxBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(2D, 1D, 4D, 14D, 10D, 12D),
            box(1.5D, 6D, 3.5D, 14.5D, 7D, 12.5D),
            box(1.5D, 1D, 3.5D, 14.5D, 2D, 12.5D),
            box(7D, 4D, 3.5D, 9D, 6D, 4.25D),
            box(12D, 0D, 4D, 14D, 1D, 6D),
            box(2D, 0D, 4D, 4D, 1D, 6D),
            box(2D, 0D, 10D, 4D, 1D, 12D),
            box(12D, 0D, 10D, 14D, 1D, 12D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public VenthyrLockBoxBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(FACING_SHAPES, blockState, facingProperty(), pos);
    }
}
