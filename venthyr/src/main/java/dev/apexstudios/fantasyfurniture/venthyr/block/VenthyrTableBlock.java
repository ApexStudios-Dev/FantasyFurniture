package dev.apexstudios.fantasyfurniture.venthyr.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.TableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrTableBlock extends TableBlock {
    public static final VoxelShape SHAPE_TABLE_LEG = ApexShapes.join(
            box(12D, 0D, 1D, 15D, 2D, 4D),
            box(12.5D, 2D, 1.5D, 14.5D, 13D, 3.5D)
    );

    public VenthyrTableBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        var facing = blockState.getValue(facingProperty());
        return ApexShapes.rotateHorizontal(getShape(blockState, SHAPE_TABLE_TOP, SHAPE_TABLE_LEG, facingProperty()), facing);
    }
}
