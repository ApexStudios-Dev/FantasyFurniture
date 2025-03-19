package dev.apexstudios.fantasyfurniture.venthyr.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.DresserBlock;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureBlockComponentHolder;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrDresserBlock extends DresserBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(-15.5D, 0D, .5D, -12.5D, 2D, 3.5D),
            box(-15.5D, 0D, 12.5D, -12.5D, 2D, 15.5D),
            box(12.5D, 0D, 12.5D, 15.5D, 2D, 15.5D),
            box(12.5D, 0D, .5D, 15.5D, 2D, 3.5D),
            box(13D, 2D, 1D, 15D, 13D, 3D),
            box(-15D, 2D, 1D, -13D, 13D, 3D),
            box(-15D, 2D, 13D, -13D, 13D, 15D),
            box(13D, 2D, 13D, 15D, 13D, 15D),
            box(-15D, 5D, 1D, 15D, 13D, 15D),
            box(-16D, 13D, 0D, 16D, 16D, 16D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public VenthyrDresserBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return FurnitureBlockComponentHolder.getShape(FACING_SHAPES, blockState, pos);
    }
}
