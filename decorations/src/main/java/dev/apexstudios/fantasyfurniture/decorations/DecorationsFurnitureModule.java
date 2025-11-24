package dev.apexstudios.fantasyfurniture.decorations;

import dev.apexstudios.fantasyfurniture.decorations.block.BerryBasketBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.BoiledCremeTreatsBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.BoltsOfClothBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.BookStackBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.BowlBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.BrewingCauldronBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.CoinStackBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.FairyLightsBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.FloatingTomesBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.FoodBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.GravestoneBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.HangingHerbsBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.MeadBottlesBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.MuffinsBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.MushroomsBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.PaperStackBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.PlatterBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.PotionBottlesBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.PresentsBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.SnowballsBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.SoulGemsBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.SpiderWebSmallBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.SpiderWebWideBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.StackablePumpkinsBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.StockingBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.SweetrollsBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.TankardsBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.TeaCupsBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.TeaSetBlock;
import dev.apexstudios.fantasyfurniture.decorations.cookie.CookieJarBlock;
import dev.apexstudios.fantasyfurniture.decorations.cookie.CookieJarBlockEntity;
import dev.apexstudios.fantasyfurniture.decorations.cookie.CookieJarMenu;
import dev.apexstudios.fantasyfurniture.decorations.cookie.CookieJarMenuScreen;
import dev.apexstudios.fantasyfurniture.decorations.util.DecorationUtil;
import dev.apexstudios.fantasyfurniture.util.FurnitureUtil;
import dev.apexstudios.registree.api.Registree;
import dev.apexstudios.registree.api.holder.DeferredBlock;
import dev.apexstudios.registree.api.holder.DeferredBlockEntity;
import dev.apexstudios.registree.api.holder.DeferredMenu;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChainBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(DecorationsFurnitureModule.ID)
public class DecorationsFurnitureModule {
    public static final String ID = "fantasyfurniture_decorations";
    public static final Registree REGISTREE = Registree.create(ID);

    public static final DeferredBlock<BerryBasketBlock> BERRY_BASKET = DecorationUtil.berryBasket(REGISTREE, "berry");
    public static final DeferredBlock<BerryBasketBlock> BLUEBERRY_BASKET = DecorationUtil.berryBasket(REGISTREE, "blueberry");
    public static final DeferredBlock<BerryBasketBlock> STRAWBERRY_BASKET = DecorationUtil.berryBasket(REGISTREE, "strawberry");
    public static final DeferredBlock<BerryBasketBlock> SWEETBERRY_BASKET = DecorationUtil.berryBasket(REGISTREE, "sweetberry");
    public static final DeferredBlock<BoltsOfClothBlock> BOLTS_OF_CLOTH = FurnitureUtil.simpleBlock(REGISTREE, "bolts_of_cloth", BoltsOfClothBlock::new, FurnitureUtil.PLANK_PROPERTIES);
    public static final DeferredBlock<BowlBlock> BOWL = DecorationUtil.bowl(REGISTREE, null);
    public static final DeferredBlock<BowlBlock> BEETROOT_SOUP_BOWL = DecorationUtil.bowl(REGISTREE, "beetroot_soup");
    public static final DeferredBlock<BowlBlock> MUSHROOM_STEW_BOWL = DecorationUtil.bowl(REGISTREE, "mushroom_stew");
    public static final DeferredBlock<CoinStackBlock> GOLDEN_COIN_STACK = DecorationUtil.coinStack(REGISTREE, "golden");
    public static final DeferredBlock<CoinStackBlock> IRON_COIN_STACK = DecorationUtil.coinStack(REGISTREE, "iron");
    public static final DeferredBlock<BrewingCauldronBlock> BREWING_CAULDRON = FurnitureUtil.simpleBlock(REGISTREE, "brewing_cauldron", BrewingCauldronBlock::new, FurnitureUtil.PLANK_PROPERTIES);
    public static final DeferredBlock<GravestoneBlock> GRAVESTONE = FurnitureUtil.simpleBlock(REGISTREE, "gravestone", GravestoneBlock::new, FurnitureUtil.PLANK_PROPERTIES);
    public static final DeferredBlock<HangingHerbsBlock> HANGING_HERBS = FurnitureUtil.simpleBlock(REGISTREE, "hanging_herbs", HangingHerbsBlock::new, FurnitureUtil.PLANK_PROPERTIES);
    public static final DeferredBlock<PaperStackBlock> PAPER_STACK = FurnitureUtil.simpleBlock(REGISTREE, "paper_stack", PaperStackBlock::new, FurnitureUtil.PLANK_PROPERTIES);
    public static final DeferredBlock<SpiderWebSmallBlock> SPIDER_WEB_SMALL = FurnitureUtil.simpleBlock(REGISTREE, "spider_web_small", SpiderWebSmallBlock::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision));
    public static final DeferredBlock<SpiderWebWideBlock> SPIDER_WEB_WIDE = FurnitureUtil.simpleBlock(REGISTREE, "spider_web_wide", SpiderWebWideBlock::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision));
    public static final DeferredBlock<ChainBlock> BRONZE_CHAIN = FurnitureUtil.simpleBlock(REGISTREE, "bronze_chain", ChainBlock::new, () -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.IRON_CHAIN));
    public static final DeferredBlock<FairyLightsBlock> FAIRY_LIGHTS = FurnitureUtil.simpleBlock(REGISTREE, "fairy_lights", FairyLightsBlock::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision));
    public static final DeferredBlock<StockingBlock> STOCKING = FurnitureUtil.simpleBlock(REGISTREE, "stocking", StockingBlock::new, FurnitureUtil.PLANK_PROPERTIES);
    public static final DeferredBlock<BookStackBlock> BOOK_STACK = FurnitureUtil.simpleBlock(REGISTREE, "book_stack", BookStackBlock::new, FurnitureUtil.PLANK_PROPERTIES);
    public static final DeferredBlock<TankardsBlock> TANKARDS = DecorationUtil.tankards(REGISTREE, null);
    public static final DeferredBlock<TankardsBlock> TANKARDS_HONEYMEAD = DecorationUtil.tankards(REGISTREE, "honeymead");
    public static final DeferredBlock<TankardsBlock> TANKARDS_MILK = DecorationUtil.tankards(REGISTREE, "milk");
    public static final DeferredBlock<TankardsBlock> TANKARDS_SWEETBERRY = DecorationUtil.tankards(REGISTREE, "sweetberry");
    public static final DeferredBlock<MushroomsBlock> MUSHROOMS_RED = DecorationUtil.mushrooms(REGISTREE, true);
    public static final DeferredBlock<MushroomsBlock> MUSHROOMS_BROWN = DecorationUtil.mushrooms(REGISTREE, false);
    public static final DeferredBlock<MuffinsBlock> MUFFINS_BLUEBERRY = DecorationUtil.muffins(REGISTREE, "blueberry");
    public static final DeferredBlock<MuffinsBlock> MUFFINS_CHOCOLATE = DecorationUtil.muffins(REGISTREE, "chocolate");
    public static final DeferredBlock<MuffinsBlock> MUFFINS_SWEETBERRY = DecorationUtil.muffins(REGISTREE, "sweetberry");
    public static final DeferredBlock<FloatingTomesBlock> FLOATING_TOMES = FurnitureUtil.simpleBlock(REGISTREE, "floating_tomes", FloatingTomesBlock::new, FurnitureUtil.PLANK_PROPERTIES);
    public static final DeferredBlock<StackablePumpkinsBlock> STACKABLE_PUMPKINS = FurnitureUtil.simpleBlock(REGISTREE, "stackable_pumpkins", StackablePumpkinsBlock::new, FurnitureUtil.PLANK_PROPERTIES);
    public static final DeferredBlock<PotionBottlesBlock> POTION_BOTTLES = FurnitureUtil.simpleBlock(REGISTREE, "potion_bottles", PotionBottlesBlock::new, FurnitureUtil.PLANK_PROPERTIES);
    public static final DeferredBlock<PresentsBlock> PRESENTS = FurnitureUtil.simpleBlock(REGISTREE, "presents", PresentsBlock::new, FurnitureUtil.PLANK_PROPERTIES);
    public static final DeferredBlock<CoinStackBlock> COPPER_COIN_STACK = DecorationUtil.coinStack(REGISTREE, "copper");
    public static final DeferredBlock<SnowballsBlock> SNOWBALLS = FurnitureUtil.simpleBlock(REGISTREE, "snowballs", SnowballsBlock::new, FurnitureUtil.PLANK_PROPERTIES);
    public static final DeferredBlock<BoiledCremeTreatsBlock> BOILED_CREME_TREATS = FurnitureUtil.simpleBlock(REGISTREE, "boiled_creme_treats", BoiledCremeTreatsBlock::new, FurnitureUtil.PLANK_PROPERTIES);
    public static final DeferredBlock<SweetrollsBlock> SWEETROLLS = FurnitureUtil.simpleBlock(REGISTREE, "sweetrolls", SweetrollsBlock::new, FurnitureUtil.PLANK_PROPERTIES);
    public static final DeferredBlock<MeadBottlesBlock> MEAD_BOTTLES = FurnitureUtil.simpleBlock(REGISTREE, "mead_bottles", MeadBottlesBlock::new, FurnitureUtil.PLANK_PROPERTIES);
    public static final DeferredBlock<SoulGemsBlock> SOUL_GEMS_DARK = DecorationUtil.soulGems(REGISTREE, "dark");
    public static final DeferredBlock<SoulGemsBlock> SOUL_GEMS_LIGHT = DecorationUtil.soulGems(REGISTREE, "light");
    public static final DeferredBlock<FoodBlock> FOOD_0 = DecorationUtil.food(REGISTREE, 0);
    public static final DeferredBlock<FoodBlock> FOOD_1 = DecorationUtil.food(REGISTREE, 1);
    public static final DeferredBlock<FoodBlock> FOOD_2 = DecorationUtil.food(REGISTREE, 2);
    public static final DeferredBlock<FoodBlock> FOOD_3 = DecorationUtil.food(REGISTREE, 3);
    public static final DeferredBlock<TeaSetBlock> TEA_SET = FurnitureUtil.simpleBlock(REGISTREE, "tea_set", TeaSetBlock::new, FurnitureUtil.PLANK_PROPERTIES);
    public static final DeferredBlock<TeaCupsBlock> TEA_CUPS = FurnitureUtil.simpleBlock(REGISTREE, "tea_cups", TeaCupsBlock::new, FurnitureUtil.PLANK_PROPERTIES);
    public static final DeferredBlock<PlatterBlock> PLATTER_0 = DecorationUtil.platter(REGISTREE, 0);
    public static final DeferredBlock<PlatterBlock> PLATTER_1 = DecorationUtil.platter(REGISTREE, 1);

    public static final DeferredBlock<CookieJarBlock> COOKIE_JAR_BLOCK = FurnitureUtil.simpleBlock(REGISTREE, "cookie_jar", CookieJarBlock::new, FurnitureUtil.PLANK_PROPERTIES);
    public static final DeferredBlockEntity<CookieJarBlockEntity> COOKIE_JAR_BLOCK_ENTITY = REGISTREE.registerBlockEntity(COOKIE_JAR_BLOCK, CookieJarBlockEntity::new);
    public static final DeferredMenu<CookieJarMenu> COOKIE_JAR_MENU = REGISTREE.registerMenu("cookie_jar", CookieJarMenu::new, () -> CookieJarMenuScreen::new);

    public static final ResourceKey<CreativeModeTab> CREATIVE_MODE_TAB = FurnitureUtil.creativeModeTab(REGISTREE, BERRY_BASKET);

    public DecorationsFurnitureModule(IEventBus modBus) {
        REGISTREE.registerEvents(modBus);
    }

    public static ResourceLocation identifier(String identifier) {
        return ResourceLocation.fromNamespaceAndPath(ID, identifier);
    }

    public static String id(String identifier) {
        return ID + ResourceLocation.NAMESPACE_SEPARATOR + identifier;
    }
}