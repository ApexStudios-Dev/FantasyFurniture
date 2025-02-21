package dev.apexstudios.fantasyfurniture.venthyr.block;

import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.base.SeatBlock;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrStoolBlock extends SeatBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(2D, 0D, 2D, 4D, 3D, 4D),
            box(12D, 0D, 12D, 14D, 3D, 14D),
            box(12D, 0D, 2D, 14D, 3D, 4D),
            box(2D, 0D, 12D, 4D, 3D, 14D),
            box(2D, 3D, 11.5D, 4D, 5D, 13.5D),
            box(12D, 3D, 11.5D, 14D, 5, 13.5D),
            box(12D, 3D, 2.5D, 14D, 5D, 4.5D),
            box(1.5D, 5D, 1.75D, 14.5D, 7D, 14.25D),
            box(2D, 3D, 2.5D, 4D, 5D, 4.5D),
            box(2.5D, 3.5D, 4.5D, 3.5D, 4.5D, 11.5D),
            box(12.5D, 3.5D, 4.5D, 13.5D, 4.5D, 11.5D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = ApexShapes.rotateHorizontal(SHAPE);

    public VenthyrStoolBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        return getShape(FACING_SHAPES, blockState, pos);
    }
}
