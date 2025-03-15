package dev.apexstudios.fantasyfurniture.decorations;

import dev.apexstudios.apexcore.lib.registree.holder.DeferredItem;
import net.minecraft.world.item.BlockItem;

public interface DecoItems {
    DeferredItem<BlockItem> BERRY_BASKET_EMPTY = DecorationsModule.REGISTREE.registerSimpleBlockItem(DecoBlocks.BERRY_BASKET_EMPTY);
    DeferredItem<BlockItem> BERRY_BASKET_SWEETBERRY = DecorationsModule.REGISTREE.registerSimpleBlockItem(DecoBlocks.BERRY_BASKET_SWEETBERRY);
    DeferredItem<BlockItem> BERRY_BASKET_BLUEBERRY = DecorationsModule.REGISTREE.registerSimpleBlockItem(DecoBlocks.BERRY_BASKET_BLUEBERRY);
    DeferredItem<BlockItem> BERRY_BASKET_STRAWBERRY = DecorationsModule.REGISTREE.registerSimpleBlockItem(DecoBlocks.BERRY_BASKET_STRAWBERRY);

    static void register() {

    }
}
