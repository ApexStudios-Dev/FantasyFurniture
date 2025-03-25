package dev.apexstudios.fantasyfurniture.necrolord.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.ChestBlock;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureBlockComponentHolder;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NecrolordChestBlock extends ChestBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(10D, 0D, 1D, 14D, 2D, 5D),
            box(-14D, 0D, 1D, -10D, 2D, 5D),
            box(-14D, 0D, 11D, -10D, 2D, 15D),
            box(10D, 0D, 11D, 14D, 2D, 15D),
            box(10D, 5D, 11D, 14D, 7D, 15D),
            box(10D, 5D, 1D, 14D, 7D, 5D),
            box(-14D, 5D, 1D, -10D, 7D, 5D),
            box(-14D, 5D, 11D, -10D, 7D, 15D),
            box(-12D, 0D, 3D, 12D, 12D, 13D),
            box(11D, 0D, 2D, 13D, 9D, 4D),
            box(-13D, 0D, 2D, -11D, 9D, 4D),
            box(-13D, 0D, 12D, -11D, 9D, 14D),
            box(11D, 0D, 12D, 13D, 9D, 14D),
            box(10D, 9D, 11D, 14D, 11D, 15D),
            box(10D, 9D, 1D, 14D, 11D, 5D),
            box(-14D, 9D, 1D, -10D, 11D, 5D),
            box(-14D, 9D, 11D, -10D, 11D, 15D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public NecrolordChestBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return FurnitureBlockComponentHolder.getShape(FACING_SHAPES, blockState, pos);
    }
}
