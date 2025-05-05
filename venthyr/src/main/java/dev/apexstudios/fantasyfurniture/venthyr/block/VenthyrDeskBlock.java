package dev.apexstudios.fantasyfurniture.venthyr.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.DeskBlock;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrDeskBlock extends DeskBlock {
    public static final VoxelShape SHAPE_LEFT = ApexShapes.join(
            box(-15D, 0D, 1D, -12D, 2D, 4D),
            box(-15D, 0D, 12D, -12D, 2D, 15D),
            box(12D, 0D, 12D, 15, 2D, 15D),
            box(12D, 0D, 1D, 15, 2D, 4D),
            box(12.5D, 2D, 1.5D, 14.5D, 13D, 3.5D),
            box(-14.5D, 2D, 1.5D, -12.5D, 13D, 3.5D),
            box(-14.5D, 2D, 12.5D, -12.5D, 13D, 14.5D),
            box(12.5D, 2D, 12.5D, 14.5D, 13, 14.5D),
            box(-16D, 13D, 0D, 16D, 16D, 16D),
            box(5D, 9D, 2, 12D, 13D, 11D),
            box(12.5D, 9D, 3.5D, 14.5D, 13D, 12.5D),
            box(-14.5D, 9D, 3.5D, -12.5D, 13D, 12.5D)
    );

    public static final VoxelShape SHAPE_RIGHT = ApexShapes.join(
            box(-15D, 0D, 1D, -12D, 2D, 4D),
            box(-15D, 0D, 12D, -12D, 2D, 15D),
            box(12D, 0D, 12D, 15D, 2D, 15D),
            box(12D, 0D, 1D, 15D, 2D, 4D),
            box(12.5D, 2D, 1.5D, 14.5D, 13D, 3.5D),
            box(-14.5D, 2D, 1.5D, -12.5D, 13D, 3.5D),
            box(-14.5D, 2D, 12.5D, -12.5D, 13D, 14.5D),
            box(12.5D, 2D, 12.5D, 14.5D, 13D, 14.5D),
            box(-16D, 13D, 0D, 16D, 16D, 16D),
            box(-12D, 9D, 2D, -5D, 13D, 11D),
            box(12.5D, 9D, 3.5D, 14.5D, 13D, 12.5D),
            box(-14.5D, 9D, 3.5D, -12.5D, 13D, 12.5D)
    );

    public static final Map<Direction, VoxelShape> LEFT_FACING_SHAPES = Shapes.rotateHorizontal(SHAPE_LEFT);
    public static final Map<Direction, VoxelShape> RIGHT_FACING_SHAPES = Shapes.rotateHorizontal(SHAPE_RIGHT);

    public VenthyrDeskBlock(Properties properties, boolean left) {
        super(properties, left);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(left ? LEFT_FACING_SHAPES : RIGHT_FACING_SHAPES, blockState, facingProperty(), pos);
    }
}
