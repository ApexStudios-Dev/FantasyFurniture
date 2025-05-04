package dev.apexstudios.fantasyfurniture.decorations.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class BoltsOfCloth extends SimpleFacingBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(1D, 0D, 2.5D, 15D, 5D, 7.5D),
            box(1D, 0D, 8.5D, 15D, 5D, 13.5D),
            box(1D, 5D, 5.5D, 15D, 10D, 10.5D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public BoltsOfCloth(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(FACING_SHAPES, blockState, pos);
    }
}
