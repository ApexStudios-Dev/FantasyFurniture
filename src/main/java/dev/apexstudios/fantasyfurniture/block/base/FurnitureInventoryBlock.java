package dev.apexstudios.fantasyfurniture.block.base;

import dev.apexstudios.apexcore.lib.block.InventoryBlock;
import dev.apexstudios.apexcore.lib.block.MultiBlock;
import dev.apexstudios.fantasyfurniture.block.entity.FurnitureInventoryBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class FurnitureInventoryBlock extends FurnitureBaseBlock implements InventoryBlock {
    public FurnitureInventoryBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState blockState) {
        if(this instanceof MultiBlock multiBlock && !multiBlock.isMultiBlockOrigin(blockState))
            return null;

        return new FurnitureInventoryBlockEntity(pos, blockState);
    }
}
