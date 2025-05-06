package dev.apexstudios.fantasyfurniture.block;

import dev.apexstudios.fantasyfurniture.block.entity.BookshelfBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BookshelfBlock extends InventoryBlock {
    public BookshelfBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState blockState) {
        return new BookshelfBlockEntity(pos, blockState);
    }
}
