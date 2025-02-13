package dev.apexstudios.fantasyfurniture.block;

import dev.apexstudios.fantasyfurniture.block.base.InventoryBlock;
import dev.apexstudios.fantasyfurniture.block.entity.DrawerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DrawerBlock extends InventoryBlock {
    public DrawerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState blockState) {
        return new DrawerBlockEntity(pos, blockState);
    }
}
