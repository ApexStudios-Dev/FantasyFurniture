package dev.apexstudios.fantasyfurniture.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureInventoryBlock;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DeskBlock extends FurnitureInventoryBlock {
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

    public static final Map<Direction, VoxelShape> LEFT_FACING_SHAPES = Shapes.rotateHorizontal(SHAPE_LEFT);
    public static final Map<Direction, VoxelShape> RIGHT_FACING_SHAPES = Shapes.rotateHorizontal(SHAPE_RIGHT);

    protected final boolean left;

    public DeskBlock(Properties properties, boolean left) {
        super(properties);

        this.left = left;
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(left ? LEFT_FACING_SHAPES : RIGHT_FACING_SHAPES, blockState, facingProperty(), pos);
    }
}
