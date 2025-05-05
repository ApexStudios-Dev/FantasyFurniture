package dev.apexstudios.fantasyfurniture.block;

import dev.apexstudios.fantasyfurniture.block.base.FurnitureBaseBlock;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PaintingSmallBlock extends FurnitureBaseBlock {
    public static final VoxelShape SHAPE = box(0D, 0D, 14D, 16D, 16D, 16D);
    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public PaintingSmallBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(FACING_SHAPES, blockState, facingProperty(), pos);
    }
}
