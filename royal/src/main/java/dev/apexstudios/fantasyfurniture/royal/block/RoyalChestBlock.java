package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.ChestBlock;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class RoyalChestBlock extends ChestBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(7.5D, 0D, .75D, 11.25D, 4D, 4.5D),
            box(-11.25D, 0D, .75D, -7.5D, 4D, 4.5D),
            box(-11.25D, 0D, 11.5D, -7.5D, 4D, 15.25D),
            box(7.5D, 0D, 11.5D, 11.25D, 4D, 15.25D),
            box(-12D, 4D, 1D, 12D, 6D, 15D),
            box(-11D, 6D, 2D, 11D, 16D, 14D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public RoyalChestBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(FACING_SHAPES, blockState, facingProperty(), pos);
    }
}
