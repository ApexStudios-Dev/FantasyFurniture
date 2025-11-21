package dev.apexstudios.fantasyfurniture.decorations;

import dev.apexstudios.apexcore.lib.block.Dyeable;
import dev.apexstudios.fantasyfurniture.decorations.block.BerryBasketBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.BoltsOfClothBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.BookStackBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.BowlBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.BrewingCauldronBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.CoinStackBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.FairyLightsBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.GravestoneBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.HangingHerbsBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.PaperStackBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.SpiderWebSmallBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.SpiderWebWideBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.StockingBlock;
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
import java.util.stream.Stream;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
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

    public static Stream<Block> dyeables() {
        return REGISTREE.listElements(Registries.BLOCK).map(Holder::value).filter(Dyeable.class::isInstance);
    }
}