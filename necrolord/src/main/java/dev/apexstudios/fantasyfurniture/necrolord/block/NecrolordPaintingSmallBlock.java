package dev.apexstudios.fantasyfurniture.necrolord.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.PaintingSmallBlock;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NecrolordPaintingSmallBlock extends PaintingSmallBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(0D, 0D, 13D, 3D, 3D, 16D),
            box(0D, 13D, 13D, 3D, 16D, 16D),
            box(13D, 13D, 13D, 16D, 16D, 16D),
            box(13D, 0D, 13D, 16D, 3D, 16D),
            box(1D, 1D, 14D, 15D, 15D, 16D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public NecrolordPaintingSmallBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(FACING_SHAPES, blockState, facingProperty(), pos);
    }
}
