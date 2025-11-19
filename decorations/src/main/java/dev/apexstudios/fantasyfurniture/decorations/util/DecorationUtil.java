package dev.apexstudios.fantasyfurniture.decorations.util;

import dev.apexstudios.fantasyfurniture.decorations.block.BerryBasketBlock;
import dev.apexstudios.fantasyfurniture.util.FurnitureUtil;
import dev.apexstudios.registree.api.Registree;
import dev.apexstudios.registree.api.holder.DeferredBlock;

public interface DecorationUtil {
    static DeferredBlock<BerryBasketBlock> berryBasket(Registree registree, String berryType) {
        return FurnitureUtil.simpleBlock(registree, berryType + "_basket", BerryBasketBlock::new, FurnitureUtil.PLANK_PROPERTIES);
    }
}
