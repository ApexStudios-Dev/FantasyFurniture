package apexstudios.fantasyfurniture.bone.block;

import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.DresserBlock;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureBlockComponentHolder;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class BoneDresserBlock extends DresserBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(-15D, 0D, 1D, -12, 2, 4D),
            box(-15D, 12D, 1D, -12D, 14D, 4D),
            box(-14.5D, 1D, 1.5D, -12.5D, 12D, 3.5D),
            box(-15D, 0D, 12D, -12D, 2D, 15D),
            box(-15D, 12D, 12D, -12D, 14D, 15D),
            box(-14.5D, 1D, 12.5D, -12.5D, 12D, 14.5D),
            box(12D, 0D, 12D, 15D, 2D, 15D),
            box(12D, 12D, 12D, 15D, 14D, 15D),
            box(12.5D, 1D, 12.5D, 14.5D, 12D, 14.5D),
            box(12D, 0D, 1D, 15D, 2D, 4D),
            box(12D, 12D, 1D, 15D, 14D, 4D),
            box(12.5D, 1D, 1.5D, 14.5D, 12D, 3.5D),
            box(-14.5D, 6D, 1.5D, 14.5D, 14D, 14.5D),
            box(-16D, 14D, 0D, 16D, 16D, 16D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = ApexShapes.rotateHorizontal(SHAPE);

    public BoneDresserBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return FurnitureBlockComponentHolder.getShape(FACING_SHAPES, blockState, pos);
    }
}
