package dev.apexstudios.fantasyfurniture.block.entity;

import dev.apexstudios.fantasyfurniture.FurnitureBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.transfer.item.ItemResource;

public final class BookshelfBlockEntity extends FurnitureInventoryBlockEntity {
    public BookshelfBlockEntity(BlockPos pos, BlockState blockState) {
        super(FurnitureBlockEntities.BOOKSHELF.value(), pos, blockState);
    }

    @Override
    protected boolean canInsert(int index, ItemResource itemResource) {
        return itemResource.is(ItemTags.BOOKSHELF_BOOKS);
    }
}
