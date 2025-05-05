package dev.apexstudios.fantasyfurniture.dunmer.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.OvenBlock;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class DunmerOvenBlock extends OvenBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(12.5D, 0D, 6.5D, 15.5D, 3D, 9.5D),
            box(-15.5D, 0D, 6.5D, -12.5D, 3D, 9.5D),
            box(-15D, 3D, 7D, -13D, 16D, 9D),
            box(13D, 3D, 7D, 15D, 16D, 9D),
            box(-16D, 12D, 7D, 16D, 14D, 9D),
            box(-5D, 10.5D, 5.5D, 5D, 15.5D, 10.5D),
            box(-6D, 0D, 2D, 6D, 3D, 14D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public DunmerOvenBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(FACING_SHAPES, blockState, facingProperty(), pos);
    }
}
