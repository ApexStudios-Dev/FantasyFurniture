package dev.apexstudios.fantasyfurniture.decorations.block;

import dev.apexstudios.apexcore.lib.block.SimpleHorizontalDirectionalBlock;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class PaperStackBlock extends SimpleHorizontalDirectionalBlock {
    public static final VoxelShape SHAPE = box(2D, 0D, 1D, 14D, 5D, 15D);
    public static final Map<Direction, VoxelShape> SHAPES = Shapes.rotateHorizontal(SHAPE);

    public PaperStackBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        var facing = blockState.getValue(FACING);
        return SHAPES.get(facing);
    }
}
