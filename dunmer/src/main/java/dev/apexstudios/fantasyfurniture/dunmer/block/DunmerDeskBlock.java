package dev.apexstudios.fantasyfurniture.dunmer.block;

import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.DeskBlock;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureBlockComponentHolder;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class DunmerDeskBlock extends DeskBlock {
    public static final VoxelShape SHAPE_LEFT = ApexShapes.join(
            box(12D, 0D, 2D, 14D, 14D, 4D),
            box(-14D, 0D, 2D, -12D, 14D, 4D),
            box(-14D, 0D, 12D, -12D, 14D, 14D),
            box(12D, 0D, 12D, 14D, 14D, 14D),
            box(-16D, 14D, 0D, 16D, 16D, 16D),
            box(4D, 10D, 2D, 11D, 14D, 11D)
    );

    public static final VoxelShape SHAPE_RIGHT = ApexShapes.join(
            box(12D, 0D, 2D, 14D, 14D, 4D),
            box(-14D, 0D, 2D, -12D, 14D, 4D),
            box(-14D, 0D, 12D, -12D, 14D, 14D),
            box(12D, 0D, 12D, 14D, 14D, 14D),
            box(-16D, 14D, 0D, 16D, 16D, 16D),
            box(-11D, 10D, 2D, -4D, 14D, 11D)
    );

    public static final Map<Direction, VoxelShape> LEFT_FACING_SHAPES = ApexShapes.rotateHorizontal(SHAPE_LEFT);
    public static final Map<Direction, VoxelShape> RIGHT_FACING_SHAPES = ApexShapes.rotateHorizontal(SHAPE_RIGHT);

    public DunmerDeskBlock(Properties properties, boolean left) {
        super(properties, left);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return FurnitureBlockComponentHolder.getShape(left ? LEFT_FACING_SHAPES : RIGHT_FACING_SHAPES, blockState, pos);
    }
}
