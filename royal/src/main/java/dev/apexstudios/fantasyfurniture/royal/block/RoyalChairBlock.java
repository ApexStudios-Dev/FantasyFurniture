package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.ChairBlock;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class RoyalChairBlock extends ChairBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(1D, 0D, 1D, 3D, 3D, 4D),
            box(1D, 0D, 12D, 3D, 3D, 15D),
            box(13D, 0D, 12D, 15D, 3D, 15D),
            box(13D, 0D, 1D, 15D, 3D, 4D),
            box(13D, 3D, 2D, 15D, 5D, 4D),
            box(13D, 3D, 12D, 15D, 5D, 14D),
            box(1D, 3D, 12D, 3D, 5D, 14D),
            box(1D, 3D, 2D, 3D, 5D, 4D),
            box(1D, 5D, 1.5D, 15D, 7D, 14.5D),
            box(1D, 7D, 2D, 15D, 9D, 14D),
            box(13D, 9D, 2D, 15D, 12D, 4D),
            box(1D, 9D, 2D, 3D, 12D, 4D),
            box(1D, 12D, 1D, 3D, 15D, 4D),
            box(13D, 12D, 1D, 15D, 15D, 4D),
            box(13D, 12D, 4D, 15D, 14D, 12D),
            box(1D, 12D, 4D, 3D, 14D, 12D),
            box(1D, 7D, 12D, 15D, 14D, 14D),
            box(2D, 14D, 12D, 14D, 20D, 14D),
            box(1D, 20D, 12D, 15D, 25D, 14D),
            box(3D, 25D, 12D, 13D, 26D, 14D),
            box(5D, 26D, 12D, 11D, 27D, 14D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = ApexShapes.rotateHorizontal(SHAPE);

    public RoyalChairBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(FACING_SHAPES, blockState, pos);
    }
}
