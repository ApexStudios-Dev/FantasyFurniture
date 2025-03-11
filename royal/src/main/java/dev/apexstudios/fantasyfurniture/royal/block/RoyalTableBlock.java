package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.TableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class RoyalTableBlock extends TableBlock {
    public static final VoxelShape SHAPE_TABLE_TOP = box(0D, 14D, 0D, 16D, 16D, 16D);
    public static final VoxelShape SHAPE_TABLE_LEG = box(11D, 0D, 1D, 15D, 14D, 5D);

    public RoyalTableBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        var facing = getComponentOrThrow(BlockComponentTypes.FACING).get(blockState);
        return ApexShapes.rotateHorizontal(getShape(blockState, SHAPE_TABLE_TOP, SHAPE_TABLE_LEG), facing);
    }
}
