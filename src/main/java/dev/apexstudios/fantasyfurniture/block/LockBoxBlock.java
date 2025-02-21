package dev.apexstudios.fantasyfurniture.block;

import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureBlockComponentHolder;
import dev.apexstudios.fantasyfurniture.block.base.InventoryBlock;
import dev.apexstudios.fantasyfurniture.block.entity.LockBoxBlockEntity;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LockBoxBlock extends InventoryBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(2D, 0D, 3D, 14D, 9D, 13D),
            box(2D, 9D, 5D, 14D, 10D, 11D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = ApexShapes.rotateHorizontal(SHAPE);

    public LockBoxBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return FurnitureBlockComponentHolder.getShape(FACING_SHAPES, blockState, pos);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState blockState) {
        return new LockBoxBlockEntity(pos, blockState);
    }
}
