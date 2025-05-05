package dev.apexstudios.fantasyfurniture.dunmer.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureSeatBlock;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class DunmerStoolBlock extends FurnitureSeatBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(12D, 0D, 2D, 14D, 5D, 4D),
            box(2D, 0D, 2D, 4D, 5D, 4D),
            box(2D, 0D, 12D, 4D, 5D, 14D),
            box(12D, 0D, 12D, 14D, 5D, 14D),
            box(1D, 5D, 1, 15D, 7D, 15D),
            box(12.5D, 2.5D, 4D, 13.5D, 3.5D, 12D),
            box(2.5D, 2.5D, 4D, 3.5D, 3.5D, 12D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public DunmerStoolBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(FACING_SHAPES, blockState, facingProperty(), pos);
    }
}
