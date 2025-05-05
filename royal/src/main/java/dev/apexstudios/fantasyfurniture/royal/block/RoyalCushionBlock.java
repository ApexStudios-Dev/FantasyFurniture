package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.CushionBlock;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class RoyalCushionBlock extends CushionBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(1D, 0D, 1D, 5D, 4D, 5D),
            box(1D, 0D, 11D, 5D, 4D, 15D),
            box(11D, 0D, 11D, 15D, 4D, 15D),
            box(11D, 0D, 1D, 15D, 4D, 5D),
            box(1.5D, 4D, 1.5D, 14.5D, 6D, 14.5D),
            box(2D, 6D, 2D, 14D, 9D, 14D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public RoyalCushionBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(FACING_SHAPES, blockState, facingProperty(), pos);
    }
}
