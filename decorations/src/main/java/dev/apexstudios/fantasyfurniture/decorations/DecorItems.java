package dev.apexstudios.fantasyfurniture.decorations;

import dev.apexstudios.apexcore.lib.registree.holder.DeferredBlock;
import dev.apexstudios.apexcore.lib.registree.holder.DeferredItem;
import net.minecraft.world.item.BlockItem;

public interface DecorItems {
    DeferredItem<BlockItem> BERRY_BASKET = register(DecoBlocks.BERRY_BASKET);
    DeferredItem<BlockItem> BERRY_BASKET_STRAWBERRY = register(DecoBlocks.BERRY_BASKET_STRAWBERRY);
    DeferredItem<BlockItem> BERRY_BASKET_SWEETBERRY = register(DecoBlocks.BERRY_BASKET_SWEETBERRY);
    DeferredItem<BlockItem> BERRY_BASKET_BLUEBERRY = register(DecoBlocks.BERRY_BASKET_BLUEBERRY);

    static void register() {

    }

    private static DeferredItem<BlockItem> register(DeferredBlock<?> block) {
        return DecorationsFurnitureModule.REGISTREE.registerSimpleBlockItem(block);
    }
}
