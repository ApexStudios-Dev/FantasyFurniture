package dev.apexstudios.fantasyfurniture.dunmer.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.ChairBlock;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class DunmerChairBlock extends ChairBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(2D, 0D, 2D, 4D, 4D, 4D),
            box(2D, 0D, 12D, 4D, 4D, 14D),
            box(12D, 0D, 12D, 14D, 4D, 14D),
            box(12D, 0D, 2D, 14D, 4D, 4D),
            box(11.5D, 4D, 2.5D, 13.5D, 7D, 4.5D),
            box(2.5D, 4D, 2.5D, 4.5D, 7D, 4.5D),
            box(2.5D, 4D, 11.5D, 4.5D, 7D, 13.5D),
            box(11.5D, 4D, 11.5D, 13.5D, 7D, 13.5D),
            box(2D, 7D, 1D, 14D, 9D, 15D),
            box(2.5D, 9D, 11.5D, 13.5D, 31.5D, 13.5D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public DunmerChairBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(FACING_SHAPES, blockState, facingProperty(), pos);
    }
}
