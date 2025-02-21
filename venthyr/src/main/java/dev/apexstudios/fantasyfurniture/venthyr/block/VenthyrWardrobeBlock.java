package dev.apexstudios.fantasyfurniture.venthyr.block;

import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.WardrobeBlock;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureBlockComponentHolder;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrWardrobeBlock extends WardrobeBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(-14.75D, 0D, .25D, -12.25D, 31D, 2.75D),
            box(-14.75D, 0D, 13.25D, -12.25D, 31D, 15.75D),
            box(12.25D, 0D, .25D, 14.75D, 31D, 2.75D),
            box(-14D, 2D, 1D, 14D, 31D, 15D),
            box(-15D, 31D, 0D, 16D, 45.75D, 16D),
            box(12.25D, 0D, 13.25D, 14.75D, 31D, 15.75D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = ApexShapes.rotateHorizontal(SHAPE);

    public VenthyrWardrobeBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        return FurnitureBlockComponentHolder.getShape(FACING_SHAPES, blockState, pos);
    }
}
