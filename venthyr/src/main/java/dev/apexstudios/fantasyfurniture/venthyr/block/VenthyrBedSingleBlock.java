package dev.apexstudios.fantasyfurniture.venthyr.block;

import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.BedSingleBlock;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrBedSingleBlock extends BedSingleBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(0D, 0D, 0D, 16D, 14D, 2D),
            box(0D, 0D, 30D, 16D, 14D, 32D),
            box(0D, 3D, 2D, 16D, 5D, 30D),
            box(1D, 5D, 2D, 15D, 8D, 30D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = ApexShapes.rotateHorizontal(SHAPE);

    public VenthyrBedSingleBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        return getShape(FACING_SHAPES, blockState, pos);
    }
}
