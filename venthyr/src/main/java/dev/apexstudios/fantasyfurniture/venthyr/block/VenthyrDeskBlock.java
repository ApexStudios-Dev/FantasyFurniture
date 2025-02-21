package dev.apexstudios.fantasyfurniture.venthyr.block;

import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.DeskBlock;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureBlockComponentHolder;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrDeskBlock extends DeskBlock {
    public static final VoxelShape SHAPE_LEFT = ApexShapes.join(
            box(13D, 0D, 0D, 15D, 9D, 2D),
            box(13D, 7D, 1D, 15D, 13D, 3D),
            box(13D, 7D, 13D, 15D, 13D, 15D),
            box(-15D, 7D, 13D, -13D, 13D, 15D),
            box(-15D, 0D, 0D, -13D, 9D, 2D),
            box(-15D, 0D, 14D, -13D, 9D, 16D),
            box(13D, 0D, 14D, 15D, 9D, 16D),
            box(-16D, 13D, 0D, 16D, 16D, 16D),
            box(-15D, 7D, 1D, -13D, 13D, 3D),
            box(5D, 9D, 2D, 12D, 13D, 11D)
    );

    public static final VoxelShape SHAPE_RIGHT = ApexShapes.join(
            box(13D, 0D, 0D, 15D, 9D, 2D),
            box(13D, 7D, 1D, 15D, 13D, 3D),
            box(13D, 7D, 13D, 15D, 13D, 15D),
            box(-15D, 7D, 13D, -13D, 13D, 15D),
            box(-15D, 0D, 0D, -13D, 9D, 2D),
            box(-15D, 0D, 14D, -13D, 9D, 16D),
            box(13D, 0D, 14D, 15D, 9D, 16D),
            box(-16D, 13D, 0D, 16D, 16D, 16D),
            box(-15D, 7D, 1D, -13D, 13D, 3D),
            box(-12D, 9D, 2D, -5D, 13D, 11D)
    );

    public static final Map<Direction, VoxelShape> LEFT_FACING_SHAPES = ApexShapes.rotateHorizontal(SHAPE_LEFT);
    public static final Map<Direction, VoxelShape> RIGHT_FACING_SHAPES = ApexShapes.rotateHorizontal(SHAPE_RIGHT);

    private final boolean left;

    public VenthyrDeskBlock(Properties properties, boolean left) {
        super(properties);

        this.left = left;
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        return FurnitureBlockComponentHolder.getShape(left ? LEFT_FACING_SHAPES : RIGHT_FACING_SHAPES, blockState, pos);
    }
}
