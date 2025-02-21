package dev.apexstudios.fantasyfurniture.venthyr.block;

import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.PaintingWideBlock;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrPaintingWideBlock extends PaintingWideBlock {
    public static final VoxelShape SHAPE = box(-16D, 0D, 14D, 16D, 16D, 16D);

    public static final Map<Direction, VoxelShape> FACING_SHAPES = ApexShapes.rotateHorizontal(SHAPE);

    public VenthyrPaintingWideBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        return getShape(FACING_SHAPES, blockState, pos);
    }
}
