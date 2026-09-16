package dev.apexstudios.fantasyfurniture.decorations.data;

import dev.apexstudios.fantasyfurniture.decorations.common.DecorationsFurnitureModule;
import dev.apexstudios.fantasyfurniture.decorations.common.grave.GravestoneEditScreen;
import dev.apexstudios.fantasyfurniture.decorations.common.plushie.PlushieBlockItem;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

final class DFLanguageProvider extends LanguageProvider {
    DFLanguageProvider(PackOutput output) {
        super(output, DecorationsFurnitureModule.ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addKey(DecorationsFurnitureModule.CREATIVE_MODE_TAB, "itemGroup", "Fantasy's Furniture - Decorations");
        add(GravestoneEditScreen.TRANSLATION, "Edit Gravestone Message");

        addBlock(DecorationsFurnitureModule.BERRY_BASKET, "Berry Basket");
        addBlock(DecorationsFurnitureModule.BLUEBERRY_BASKET, "Blueberry Basket");
        addBlock(DecorationsFurnitureModule.STRAWBERRY_BASKET, "Strawberry Basket");
        addBlock(DecorationsFurnitureModule.SWEETBERRY_BASKET, "Sweetberry Basket");
        addBlock(DecorationsFurnitureModule.BOLTS_OF_CLOTH, "Bolts of Cloth");
        addBlock(DecorationsFurnitureModule.BOWL, "Bowl");
        addBlock(DecorationsFurnitureModule.BEETROOT_SOUP_BOWL, "Beetroot Soup Bowl");
        addBlock(DecorationsFurnitureModule.MUSHROOM_STEW_BOWL, "Mushroom Stew Bowl");
        addBlock(DecorationsFurnitureModule.GOLDEN_COIN_STACK, "Golden Coin Stack");
        addBlock(DecorationsFurnitureModule.IRON_COIN_STACK, "Iron Coin Stack");
        addBlock(DecorationsFurnitureModule.COOKIE_JAR_BLOCK, "Cookie Jar");
        addBlock(DecorationsFurnitureModule.BREWING_CAULDRON, "Brewing Cauldron");
        addBlock(DecorationsFurnitureModule.GRAVESTONE_BLOCK, "Gravestone");
        addBlock(DecorationsFurnitureModule.HANGING_HERBS, "Hanging Herbs");
        addBlock(DecorationsFurnitureModule.PAPER_STACK, "Paper Stack");
        addBlock(DecorationsFurnitureModule.SPIDER_WEB_SMALL, "Spiderweb Small");
        addBlock(DecorationsFurnitureModule.SPIDER_WEB_WIDE, "Spiderweb Wide");
        addBlock(DecorationsFurnitureModule.BRONZE_CHAIN, "Bronze Chain");
        addBlock(DecorationsFurnitureModule.FAIRY_LIGHTS, "Fairy Lights");
        addBlock(DecorationsFurnitureModule.STOCKING, "Stocking");
        addBlock(DecorationsFurnitureModule.BOOK_STACK_0, "Book Stack 0");
        addBlock(DecorationsFurnitureModule.BOOK_STACK_1, "Book Stack 1");
        addBlock(DecorationsFurnitureModule.TANKARDS, "Tankards");
        addBlock(DecorationsFurnitureModule.TANKARDS_HONEYMEAD, "Honeymead Tankards");
        addBlock(DecorationsFurnitureModule.TANKARDS_MILK, "Milk Tankards");
        addBlock(DecorationsFurnitureModule.TANKARDS_SWEETBERRY, "Sweetberry Tankards");
        addBlock(DecorationsFurnitureModule.MUSHROOMS_RED, "Mushrooms Red");
        addBlock(DecorationsFurnitureModule.MUSHROOMS_BROWN, "Mushrooms Brown");
        addBlock(DecorationsFurnitureModule.MUFFINS_BLUEBERRY, "Blueberry Muffins");
        addBlock(DecorationsFurnitureModule.MUFFINS_CHOCOLATE, "Chocolate Muffins");
        addBlock(DecorationsFurnitureModule.MUFFINS_SWEETBERRY, "Sweetberry Muffins");
        addBlock(DecorationsFurnitureModule.FLOATING_TOMES, "Floating Tomes");
        addBlock(DecorationsFurnitureModule.STACKABLE_PUMPKINS, "Stackable Pumpkins");
        addBlock(DecorationsFurnitureModule.POTION_BOTTLES, "Potion Bottles");
        addBlock(DecorationsFurnitureModule.PRESENTS, "Presents");
        addBlock(DecorationsFurnitureModule.COPPER_COIN_STACK, "Copper Coin Stack");
        addBlock(DecorationsFurnitureModule.SNOWBALLS, "Snowballs");
        addBlock(DecorationsFurnitureModule.BOILED_CREME_TREATS, "Boiled Creme Treats");
        addBlock(DecorationsFurnitureModule.SWEETROLLS, "Sweetrolls");
        addBlock(DecorationsFurnitureModule.MEAD_BOTTLES, "Mead Bottles");
        addBlock(DecorationsFurnitureModule.SOUL_GEMS_DARK, "Soul Gems Dark");
        addBlock(DecorationsFurnitureModule.SOUL_GEMS_LIGHT, "Soul Gems Light");
        addBlock(DecorationsFurnitureModule.FOOD_0, "Food 0");
        addBlock(DecorationsFurnitureModule.FOOD_1, "Food 1");
        addBlock(DecorationsFurnitureModule.FOOD_2, "Food 2");
        addBlock(DecorationsFurnitureModule.FOOD_3, "Food 3");
        addBlock(DecorationsFurnitureModule.TEA_SET, "Tea Set");
        addBlock(DecorationsFurnitureModule.TEA_CUPS, "Tea Cups");
        addBlock(DecorationsFurnitureModule.PLATTER_0, "Platter 0");
        addBlock(DecorationsFurnitureModule.PLATTER_1, "Platter 1");
        addBlock(DecorationsFurnitureModule.CHALICES_0, "Chalices 0");
        addBlock(DecorationsFurnitureModule.CHALICES_1, "Chalices 1");
        addBlock(DecorationsFurnitureModule.CHALICES_2, "Chalices 2");
        addBlock(DecorationsFurnitureModule.CHALICES_3, "Chalices 3");
        addBlock(DecorationsFurnitureModule.CANDLES_0, "Candles 0");
        addBlock(DecorationsFurnitureModule.CANDLES_1, "Candles 1");
        addBlock(DecorationsFurnitureModule.BANNER, "Banner");
        addBlock(DecorationsFurnitureModule.BONE_PILE_SKELETON, "Bone Pile Skeleton");
        addBlock(DecorationsFurnitureModule.BONE_PILE_WITHER, "Bone Pile Wither");
        addBlock(DecorationsFurnitureModule.CROWN, "Crown");
        addBlock(DecorationsFurnitureModule.CUSHIONED_CROWN, "Cushioned Crown");
        addBlock(DecorationsFurnitureModule.FLOOR_CUSHION, "Floor Cushion");
        addBlock(DecorationsFurnitureModule.CANDELABRA_0, "Candelabra 0");
        addBlock(DecorationsFurnitureModule.CANDELABRA_1, "Candelabra 1");
        addBlock(DecorationsFurnitureModule.WALL_MIRROR_SMALL, "Wall Mirror Small");
        addBlock(DecorationsFurnitureModule.WALL_MIRROR_LARGE, "Wall Mirror Large");
        addBlock(DecorationsFurnitureModule.POTTERY_0, "Pottery 0");
        addBlock(DecorationsFurnitureModule.POTTERY_1, "Pottery 1");
        addBlock(DecorationsFurnitureModule.WIDOW_BLOOM_BLOCK, "Widow Bloom");
        addBlock(DecorationsFurnitureModule.SKULL_BLOSSOM_SKELETON_BLOCK, "Skull Blossoms Skeleton");
        addBlock(DecorationsFurnitureModule.SKULL_BLOSSOM_WITHER_BLOCK, "Skull Blossoms Wither");
        addBlock(DecorationsFurnitureModule.PLUSHIE_BLOCK, "Plushie");
        add(PlushieBlockItem.PLAYER_KEY, "Player");
        add(PlushieBlockItem.DYANMIC_KEY, "%s %s");
    }
}
