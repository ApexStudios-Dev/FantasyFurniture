package dev.apexstudios.fantasyfurniture.necrolord.block;

import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.DeskBlock;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureBlockComponentHolder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

public final class NecrolordDeskBlock extends DeskBlock {
    public static final VoxelShape SHAPE_LEFT = ApexShapes.join(
            box(12D, 0D, 1D, 15D, 2D, 4D),
            box(12D, 0D, 12D, 15D, 2D, 15D),
            box(1D, 0D, 12D, 4D, 2D, 15D),
            box(1D, 0D, 1D, 4D, 2D, 4D),
            box(1D, 12D, 1D, 4D, 14D, 4D),
            box(1D, 12D, 12D, 4D, 14D, 15D),
            box(12D, 12D, 12D, 15D, 14D, 15D),
            box(12D, 12D, 1D, 15D, 14D, 4D),
            box(2D, 0D, 2D, 14D, 14D, 14D),
            box(-15D, 0D, 1D, -11D, 2D, 5D),
            box(-15D, 12D, 1D, -11D, 14D, 5D),
            box(-15D, 12D, 11D, -11D, 14D, 15D),
            box(-14D, 2D, 12D, -12D, 12D, 14D),
            box(-14D, 2D, 2D, -12D, 12D, 4D),
            box(-16D, 14D, 0D, 16D, 16D, 16D),
            box(-15D, 0D, 11D, -11D, 2D, 15D)
    );

    public static final VoxelShape SHAPE_RIGHT = ApexShapes.join(
            box(-15D, 0D, 1D, -12D, 2D, 4D),
            box(-15D, 0D, 12D, -12D, 2D, 15D),
            box(-4D, 0D, 12D, -1D, 2D, 15D),
            box(-4D, 0D, 1D, -1D, 2D, 4D),
            box(-4D, 12D, 1D, -1D, 14D, 4D),
            box(-4D, 12D, 12D, -1D, 14D, 15D),
            box(-15D, 12D, 12D, -12D, 14D, 15D),
            box(-15D, 12D, 1D, -12D, 14D, 4D),
            box(-14D, 0D, 2D, -2D, 14D, 14D),
            box(11D, 0D, 1D, 15D, 2D, 5D),
            box(11D, 12D, 1D, 15D, 14D, 5D),
            box(11D, 12D, 11D, 15D, 14D, 15D),
            box(12D, 2D, 12D, 14D, 12D, 14D),
            box(12D, 2D, 2D, 14D, 12D, 4D),
            box(-16D, 14D, 0D, 16D, 16D, 16D),
            box(11D, 0D, 11D, 15D, 2D, 15D)
    );

    public static final Map<Direction, VoxelShape> LEFT_FACING_SHAPES = ApexShapes.rotateHorizontal(SHAPE_LEFT);
    public static final Map<Direction, VoxelShape> RIGHT_FACING_SHAPES = ApexShapes.rotateHorizontal(SHAPE_RIGHT);

    public NecrolordDeskBlock(Properties properties, boolean left) {
        super(properties, left);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return FurnitureBlockComponentHolder.getShape(left ? LEFT_FACING_SHAPES : RIGHT_FACING_SHAPES, blockState, pos);
    }
}
