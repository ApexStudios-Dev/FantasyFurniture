package dev.apexstudios.fantasyfurniture.dunmer.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.TableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class DunmerTableBlock extends TableBlock {
    public static final VoxelShape SHAPE_TABLE_TOP = box(0D, 14D, 0D, 16D, 16D, 16D);

    public DunmerTableBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        var facing = blockState.getValue(facingProperty());
        return ApexShapes.rotateHorizontal(getShape(blockState, SHAPE_TABLE_TOP, SHAPE_TABLE_LEG, facingProperty()), facing);
    }
}
