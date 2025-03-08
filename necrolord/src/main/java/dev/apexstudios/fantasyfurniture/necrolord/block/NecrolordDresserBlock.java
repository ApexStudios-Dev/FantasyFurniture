package dev.apexstudios.fantasyfurniture.necrolord.block;

import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.DresserBlock;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureBlockComponentHolder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

public final class NecrolordDresserBlock extends DresserBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(-16D, 0D, 0D, -13D, 2D, 3D),
            box(-16D, 0D, 13D, -13D, 2D, 16D),
            box(13D, 0D, 13D, 16D, 2D, 16D),
            box(13D, 0D, 0D, 16D, 2D, 3D),
            box(13D, 14D, 0D, 16D, 16D, 3D),
            box(13D, 14D, 13D, 16D, 16D, 16D),
            box(-16D, 14D, 13D, -13D, 16D, 16D),
            box(-16D, 14D, 0D, -13D, 16D, 3D),
            box(-15D, 0D, 1D, 15D, 16D, 15D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = ApexShapes.rotateHorizontal(SHAPE);

    public NecrolordDresserBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return FurnitureBlockComponentHolder.getShape(FACING_SHAPES, blockState, pos);
    }
}
