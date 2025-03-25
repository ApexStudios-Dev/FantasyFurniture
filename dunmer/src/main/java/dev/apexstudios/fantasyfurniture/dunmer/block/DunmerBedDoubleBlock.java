package dev.apexstudios.fantasyfurniture.dunmer.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.BedDoubleBlock;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class DunmerBedDoubleBlock extends BedDoubleBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(-16D, 0D, 0D, -14D, 11D, 2D),
            box(-14D, 3D, 0D, 14D, 11D, 2D),
            box(-15D, 3D, 2D, 15D, 8D, 24D),
            box(-14D, 3D, 24D, 14D, 7D, 30D),
            box(-16D, 3D, 2D, 16D, 5D, 30D),
            box(14D, 0D, 0D, 16D, 11D, 2D),
            box(-16D, 0D, 30D, -14D, 13D, 32D),
            box(-14D, 3D, 30D, 14D, 13D, 32D),
            box(14D, 0D, 30D, 16D, 13D, 32D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public DunmerBedDoubleBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(FACING_SHAPES, blockState, pos);
    }
}
