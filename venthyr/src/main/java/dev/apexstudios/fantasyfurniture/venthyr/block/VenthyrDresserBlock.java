package dev.apexstudios.fantasyfurniture.venthyr.block;

import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.DresserBlock;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureBlockComponentHolder;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrDresserBlock extends DresserBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(-15D, 0D, 1D, 15D, 16D, 15D),
            box(-16D, 13D, 14D, 16D, 16D, 16D),
            box(-16D, 13D, 0D, 16D, 16D, 2D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = ApexShapes.rotateHorizontal(SHAPE);

    public VenthyrDresserBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        return FurnitureBlockComponentHolder.getShape(FACING_SHAPES, blockState, pos);
    }
}
