package dev.apexstudios.fantasyfurniture.decorations.util;

import dev.apexstudios.fantasyfurniture.decorations.block.BerryBasketBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.BookStackBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.BowlBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.CoinStackBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.FoodBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.MuffinsBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.MushroomsBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.PlatterBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.SoulGemsBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.TankardsBlock;
import dev.apexstudios.fantasyfurniture.util.FurnitureUtil;
import dev.apexstudios.registree.api.Registree;
import dev.apexstudios.registree.api.holder.DeferredBlock;
import org.jetbrains.annotations.Nullable;

public interface DecorationUtil {
    static DeferredBlock<BerryBasketBlock> berryBasket(Registree registree, String berryType) {
        return FurnitureUtil.simpleBlock(registree, berryType + "_basket", BerryBasketBlock::new, FurnitureUtil.PLANK_PROPERTIES);
    }

    static DeferredBlock<BowlBlock> bowl(Registree registree, @Nullable String soup) {
        var name = "bowl";

        if(soup != null && !soup.isBlank()) {
            name = soup + '_' + name;
        }

        return FurnitureUtil.simpleBlock(registree, name, BowlBlock::new, FurnitureUtil.PLANK_PROPERTIES);
    }

    static DeferredBlock<CoinStackBlock> coinStack(Registree registree, String material) {
        return FurnitureUtil.simpleBlock(registree, material + "_coin_stack", CoinStackBlock::new, FurnitureUtil.PLANK_PROPERTIES);
    }

    static DeferredBlock<TankardsBlock> tankards(Registree registree, @Nullable String drink) {
        var name = "tankards";

        if(drink != null && !drink.isBlank()) {
            name = name + '_' + drink;
        }

        return FurnitureUtil.simpleBlock(registree, name, TankardsBlock::new, FurnitureUtil.PLANK_PROPERTIES);
    }

    static DeferredBlock<MushroomsBlock> mushrooms(Registree registree, boolean red) {
        var name = "mushrooms_" + (red ? "red" : "brown");
        return FurnitureUtil.simpleBlock(registree, name, properties -> new MushroomsBlock(properties, red), FurnitureUtil.PLANK_PROPERTIES);
    }

    static DeferredBlock<MuffinsBlock> muffins(Registree registree, String berry) {
        var name = "muffins_" + berry;
        return FurnitureUtil.simpleBlock(registree, name, MuffinsBlock::new, FurnitureUtil.PLANK_PROPERTIES);
    }

    static DeferredBlock<SoulGemsBlock> soulGems(Registree registree, String type) {
        return FurnitureUtil.simpleBlock(registree, "soul_gems_" + type, SoulGemsBlock::new, FurnitureUtil.PLANK_PROPERTIES);
    }

    static DeferredBlock<FoodBlock> food(Registree registree, int id) {
        return FurnitureUtil.simpleBlock(registree, "food_" + id, FoodBlock::new, FurnitureUtil.PLANK_PROPERTIES);
    }

    static DeferredBlock<PlatterBlock> platter(Registree registree, int id) {
        return FurnitureUtil.simpleBlock(registree, "platter_" + id, PlatterBlock::new, FurnitureUtil.PLANK_PROPERTIES);
    }

    static DeferredBlock<BookStackBlock> bookStack(Registree registree, int id) {
        return FurnitureUtil.simpleBlock(registree, "book_stack_" + id, BookStackBlock::new, FurnitureUtil.PLANK_PROPERTIES);
    }
}
