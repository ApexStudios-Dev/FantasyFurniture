package dev.apexstudios.fantasyfurniture.decorations.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class BerryBasketBlock extends SimpleFacingBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(2D, 0D, 3.5D, 14D, 5D, 12.5D),
            box(1.5D, 5D, 3D, 14.5D, 6D, 13D),
            box(7D, 6D, 3.25D, 9D, 11.75D, 12.75D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public BerryBasketBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(FACING_SHAPES, blockState, pos);
    }
}
