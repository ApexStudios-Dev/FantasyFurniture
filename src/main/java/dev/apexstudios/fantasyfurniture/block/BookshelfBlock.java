package dev.apexstudios.fantasyfurniture.block;

import dev.apexstudios.apexcore.lib.multiblock.MultiBlockProperties;
import dev.apexstudios.apexcore.lib.multiblock.MultiBlockProperty;
import dev.apexstudios.fantasyfurniture.block.entity.BookshelfBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BookshelfBlock extends InventoryMultiBlock {
    public BookshelfBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState blockState) {
        return blockState.getValue(getMultiBlockProperty()) == 0 ? new BookshelfBlockEntity(pos, blockState) : null;
    }

    @Override
    public MultiBlockProperty getMultiBlockProperty() {
        return MultiBlockProperties.MB_1x2x2;
    }
}
