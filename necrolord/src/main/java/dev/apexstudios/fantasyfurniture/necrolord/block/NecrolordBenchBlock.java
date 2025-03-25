package dev.apexstudios.fantasyfurniture.necrolord.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.BenchBlock;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NecrolordBenchBlock extends BenchBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(12D, 0D, 0D, 16D, 2D, 4D),
            box(12D, 5D, 0D, 16D, 7D, 4D),
            box(12D, 5D, 12D, 16D, 7D, 16D),
            box(12D, 0D, 12D, 16D, 2D, 16D),
            box(-16D, 0D, 12D, -12D, 2D, 16D),
            box(-16D, 5D, 12D, -12D, 7D, 16D),
            box(-16D, 5D, 0D, -12D, 7D, 4D),
            box(-16D, 0D, 0D, -12D, 2D, 4D),
            box(-15D, 2D, 1D, -13D, 5D, 3D),
            box(-15D, 2D, 13D, -13D, 5D, 15D),
            box(13D, 2D, 13D, 15D, 5D, 15D),
            box(13D, 2D, 1D, 15D, 5D, 3D),
            box(-15D, 5D, 1D, 15D, 7D, 15D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public NecrolordBenchBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(FACING_SHAPES, blockState, pos);
    }
}
