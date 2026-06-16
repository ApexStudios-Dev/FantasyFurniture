package dev.apexstudios.fantasyfurniture.decorations.common;

import dev.apexstudios.fantasyfurniture.common.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import dev.apexstudios.fantasyfurniture.decorations.common.ber.SimpleBlockEntity;
import dev.apexstudios.fantasyfurniture.decorations.common.ber.SimpleBlockEntityBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.BannerBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.BerryBasketBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.BoiledCremeTreatsBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.BoltsOfClothBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.BonePileBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.BookStackBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.BowlBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.BrewingCauldronBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.CandelabraBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.CandlesBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.ChalicesBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.CoinStackBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.CrownBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.CushionedCrownBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.FairyLightsBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.FloatingTomesBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.FloorCushionBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.FoodBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.HangingHerbsBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.MeadBottlesBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.MuffinsBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.MushroomsBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.PaperStackBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.PlatterBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.PotionBottlesBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.PotteryBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.PresentsBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.SnowballsBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.SoulGemsBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.SpiderWebSmallBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.SpiderWebWideBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.StackablePumpkinsBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.StockingBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.SweetrollsBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.TankardsBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.TeaCupsBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.TeaSetBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.WallMirrorLargeBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.WallMirrorSmallBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.cookie.CookieJarBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.cookie.CookieJarBlockEntity;
import dev.apexstudios.fantasyfurniture.decorations.common.cookie.CookieJarMenu;
import dev.apexstudios.fantasyfurniture.decorations.common.cookie.CookieJarMenuScreen;
import dev.apexstudios.fantasyfurniture.decorations.common.grave.GravestoneBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.grave.GravestoneBlockEntity;
import dev.apexstudios.fantasyfurniture.decorations.common.grave.GravestoneBlockItem;
import dev.apexstudios.fantasyfurniture.decorations.common.plushie.PlushieBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.plushie.PlushieBlockEntity;
import dev.apexstudios.fantasyfurniture.decorations.common.plushie.PlushieBlockItem;
import dev.apexstudios.fantasyfurniture.decorations.common.util.DecorationUtil;
import dev.apexstudios.registree.Registree;
import dev.apexstudios.registree.holder.DeferredBlockEntity;
import dev.apexstudios.registree.holder.DeferredDataComponent;
import dev.apexstudios.registree.holder.DeferredMenu;
import dev.apexstudios.registree.holder.Holders;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChainBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AnvilUpdateEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

@Mod(DecorationsFurnitureModule.ID)
public class DecorationsFurnitureModule {
    public static final String ID = "fantasyfurniture_decorations";
    public static final Registree REGISTREE = Registree.create(ID);

    public static final DeferredBlock<BerryBasketBlock> BERRY_BASKET = DecorationUtil.berryBasket(REGISTREE, "berry").register();
    public static final DeferredBlock<BerryBasketBlock> BLUEBERRY_BASKET = DecorationUtil.berryBasket(REGISTREE, "blueberry").register();
    public static final DeferredBlock<BerryBasketBlock> STRAWBERRY_BASKET = DecorationUtil.berryBasket(REGISTREE, "strawberry").register();
    public static final DeferredBlock<BerryBasketBlock> SWEETBERRY_BASKET = DecorationUtil.berryBasket(REGISTREE, "sweetberry").register();

    public static final DeferredBlock<BoltsOfClothBlock> BOLTS_OF_CLOTH = REGISTREE.block("bolts_of_cloth", BoltsOfClothBlock::new)
            .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
            .item()
            .register();

    public static final DeferredBlock<BowlBlock> BOWL = DecorationUtil.bowl(REGISTREE, null).register();
    public static final DeferredBlock<BowlBlock> BEETROOT_SOUP_BOWL = DecorationUtil.bowl(REGISTREE, "beetroot_soup").register();
    public static final DeferredBlock<BowlBlock> MUSHROOM_STEW_BOWL = DecorationUtil.bowl(REGISTREE, "mushroom_stew").register();
    public static final DeferredBlock<CoinStackBlock> GOLDEN_COIN_STACK = DecorationUtil.coinStack(REGISTREE, "golden").register();
    public static final DeferredBlock<CoinStackBlock> IRON_COIN_STACK = DecorationUtil.coinStack(REGISTREE, "iron").register();

    public static final DeferredBlock<BrewingCauldronBlock> BREWING_CAULDRON = REGISTREE.block("brewing_cauldron", BrewingCauldronBlock::new)
            .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
            .item()
            .register();

    public static final DeferredBlock<HangingHerbsBlock> HANGING_HERBS = REGISTREE.block("hanging_herbs", HangingHerbsBlock::new)
            .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
            .properties(BlockBehaviour.Properties::noCollision)
            .item()
            .register();

    public static final DeferredBlock<PaperStackBlock> PAPER_STACK = REGISTREE.block("paper_stack", PaperStackBlock::new)
            .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
            .item()
            .register();

    public static final DeferredBlock<SpiderWebSmallBlock> SPIDER_WEB_SMALL = REGISTREE.block("spider_web_small", SpiderWebSmallBlock::new)
            .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
            .properties(BlockBehaviour.Properties::noCollision)
            .item()
            .register();

    public static final DeferredBlock<SpiderWebWideBlock> SPIDER_WEB_WIDE = REGISTREE.block("spider_web_wide", SpiderWebWideBlock::new)
            .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
            .properties(BlockBehaviour.Properties::noCollision)
            .item()
            .register();

    public static final DeferredBlock<ChainBlock> BRONZE_CHAIN = REGISTREE.block("bronze_chain", ChainBlock::new)
            .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.IRON_CHAIN))
            .item()
            .register();

    public static final DeferredBlock<FairyLightsBlock> FAIRY_LIGHTS = REGISTREE.block("fairy_lights", FairyLightsBlock::new)
            .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
            .properties(BlockBehaviour.Properties::noCollision)
            .item()
            .register();

    public static final DeferredBlock<StockingBlock> STOCKING = REGISTREE.block("stocking", StockingBlock::new)
            .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
            .properties(BlockBehaviour.Properties::noCollision)
            .item()
            .register();

    public static final DeferredBlock<BookStackBlock> BOOK_STACK_0 = DecorationUtil.bookStack(REGISTREE, 0).register();
    public static final DeferredBlock<BookStackBlock> BOOK_STACK_1 = DecorationUtil.bookStack(REGISTREE, 1).register();
    public static final DeferredBlock<TankardsBlock> TANKARDS = DecorationUtil.tankards(REGISTREE, null).register();
    public static final DeferredBlock<TankardsBlock> TANKARDS_HONEYMEAD = DecorationUtil.tankards(REGISTREE, "honeymead").register();
    public static final DeferredBlock<TankardsBlock> TANKARDS_MILK = DecorationUtil.tankards(REGISTREE, "milk").register();
    public static final DeferredBlock<TankardsBlock> TANKARDS_SWEETBERRY = DecorationUtil.tankards(REGISTREE, "sweetberry").register();
    public static final DeferredBlock<MushroomsBlock> MUSHROOMS_RED = DecorationUtil.mushrooms(REGISTREE, true).register();
    public static final DeferredBlock<MushroomsBlock> MUSHROOMS_BROWN = DecorationUtil.mushrooms(REGISTREE, false).register();
    public static final DeferredBlock<MuffinsBlock> MUFFINS_BLUEBERRY = DecorationUtil.muffins(REGISTREE, "blueberry").register();
    public static final DeferredBlock<MuffinsBlock> MUFFINS_CHOCOLATE = DecorationUtil.muffins(REGISTREE, "chocolate").register();
    public static final DeferredBlock<MuffinsBlock> MUFFINS_SWEETBERRY = DecorationUtil.muffins(REGISTREE, "sweetberry").register();

    public static final DeferredBlock<FloatingTomesBlock> FLOATING_TOMES = REGISTREE.block("floating_tomes", FloatingTomesBlock::new)
            .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
            .item()
            .register();

    public static final DeferredBlock<StackablePumpkinsBlock> STACKABLE_PUMPKINS = REGISTREE.block("stackable_pumpkins", StackablePumpkinsBlock::new)
            .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
            .item()
            .register();

    public static final DeferredBlock<PotionBottlesBlock> POTION_BOTTLES = REGISTREE.block("potion_bottles", PotionBottlesBlock::new)
            .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
            .properties(BlockBehaviour.Properties::noCollision)
            .item()
            .register();

    public static final DeferredBlock<PresentsBlock> PRESENTS = REGISTREE.block("presents", PresentsBlock::new)
            .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
            .item()
            .register();

    public static final DeferredBlock<CoinStackBlock> COPPER_COIN_STACK = DecorationUtil.coinStack(REGISTREE, "copper").register();

    public static final DeferredBlock<SnowballsBlock> SNOWBALLS = REGISTREE.block("snowballs", SnowballsBlock::new)
            .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
            .properties(BlockBehaviour.Properties::noCollision)
            .item()
            .register();

    public static final DeferredBlock<BoiledCremeTreatsBlock> BOILED_CREME_TREATS = REGISTREE.block("boiled_creme_treats", BoiledCremeTreatsBlock::new)
            .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
            .properties(BlockBehaviour.Properties::noCollision)
            .item()
            .register();

    public static final DeferredBlock<SweetrollsBlock> SWEETROLLS = REGISTREE.block("sweetrolls", SweetrollsBlock::new)
            .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
            .properties(BlockBehaviour.Properties::noCollision)
            .item()
            .register();

    public static final DeferredBlock<MeadBottlesBlock> MEAD_BOTTLES = REGISTREE.block( "mead_bottles", MeadBottlesBlock::new)
            .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
            .properties(BlockBehaviour.Properties::noCollision)
            .item()
            .register();

    public static final DeferredBlock<SoulGemsBlock> SOUL_GEMS_DARK = DecorationUtil.soulGems(REGISTREE, "dark").register();
    public static final DeferredBlock<SoulGemsBlock> SOUL_GEMS_LIGHT = DecorationUtil.soulGems(REGISTREE, "light").register();
    public static final DeferredBlock<FoodBlock> FOOD_0 = DecorationUtil.food(REGISTREE, 0).register();
    public static final DeferredBlock<FoodBlock> FOOD_1 = DecorationUtil.food(REGISTREE, 1).register();
    public static final DeferredBlock<FoodBlock> FOOD_2 = DecorationUtil.food(REGISTREE, 2).register();
    public static final DeferredBlock<FoodBlock> FOOD_3 = DecorationUtil.food(REGISTREE, 3).register();

    public static final DeferredBlock<TeaSetBlock> TEA_SET = REGISTREE.block("tea_set", TeaSetBlock::new)
            .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
            .properties(BlockBehaviour.Properties::noCollision)
            .item()
            .register();

    public static final DeferredBlock<TeaCupsBlock> TEA_CUPS = REGISTREE.block("tea_cups", TeaCupsBlock::new)
            .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
            .properties(BlockBehaviour.Properties::noCollision)
            .item()
            .register();

    public static final DeferredBlock<PlatterBlock> PLATTER_0 = DecorationUtil.platter(REGISTREE, 0).register();
    public static final DeferredBlock<PlatterBlock> PLATTER_1 = DecorationUtil.platter(REGISTREE, 1).register();
    public static final DeferredBlock<ChalicesBlock> CHALICES_0 = DecorationUtil.chalices(REGISTREE, 0, ChalicesBlock::new).register();
    public static final DeferredBlock<ChalicesBlock.AltModel> CHALICES_1 = DecorationUtil.chalices(REGISTREE, 1, ChalicesBlock.AltModel::new).register();
    public static final DeferredBlock<ChalicesBlock.AltModel> CHALICES_2 = DecorationUtil.chalices(REGISTREE, 2, ChalicesBlock.AltModel::new).register();
    public static final DeferredBlock<ChalicesBlock.Dyeable> CHALICES_3 = DecorationUtil.chalices(REGISTREE, 3, ChalicesBlock.Dyeable::new).register();
    public static final DeferredBlock<CandlesBlock> CANDLES_0 = DecorationUtil.candles(REGISTREE, 0).register();
    public static final DeferredBlock<CandlesBlock> CANDLES_1 = DecorationUtil.candles(REGISTREE, 1).register();

    public static final DeferredBlock<BannerBlock> BANNER = REGISTREE.block("banner", BannerBlock::new)
            .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
            .properties(properties -> properties
                    .noOcclusion()
                    .noCollision()
            )
            .item()
            .register();

    public static final DeferredBlock<BonePileBlock> BONE_PILE_SKELETON = DecorationUtil.bonePile(REGISTREE, "skeleton").register();
    public static final DeferredBlock<BonePileBlock> BONE_PILE_WITHER = DecorationUtil.bonePile(REGISTREE, "wither").register();

    public static final DeferredBlock<CrownBlock> CROWN = REGISTREE.block("crown", CrownBlock::new)
            .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
            .properties(BlockBehaviour.Properties::noCollision)
            .item()
            .register();

    public static final DeferredBlock<CushionedCrownBlock> CUSHIONED_CROWN = REGISTREE.block("cushioned_crown", CushionedCrownBlock::new)
            .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
            .properties(BlockBehaviour.Properties::noCollision)
            .item()
            .register();

    public static final DeferredBlock<FloorCushionBlock> FLOOR_CUSHION = REGISTREE.block("floor_cushion", FloorCushionBlock::new)
            .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
            .item()
            .register();

    public static final DeferredBlock<CandelabraBlock> CANDELABRA_0 = DecorationUtil.candelabra(REGISTREE, 0, CandelabraBlock.SHAPE_0).register();
    public static final DeferredBlock<CandelabraBlock> CANDELABRA_1 = DecorationUtil.candelabra(REGISTREE, 1, CandelabraBlock.SHAPE_1).register();

    public static final DeferredBlock<WallMirrorSmallBlock> WALL_MIRROR_SMALL = REGISTREE.block("wall_mirror_small", WallMirrorSmallBlock::new)
            .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
            .properties(BlockBehaviour.Properties::noCollision)
            .item()
            .register();

    public static final DeferredBlock<WallMirrorLargeBlock> WALL_MIRROR_LARGE = REGISTREE.block("wall_mirror_large", WallMirrorLargeBlock::new)
            .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
            .properties(BlockBehaviour.Properties::noCollision)
            .item()
            .register();

    public static final DeferredBlock<PotteryBlock> POTTERY_0 = DecorationUtil.pottery(REGISTREE, 0, PotteryBlock.SHAPE_0).register();
    public static final DeferredBlock<PotteryBlock> POTTERY_1 = DecorationUtil.pottery(REGISTREE, 1, PotteryBlock.SHAPE_1).register();

    public static final DeferredBlock<GravestoneBlock> GRAVESTONE_BLOCK = REGISTREE.block("gravestone", GravestoneBlock::new)
            .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
            .blockEntity(GravestoneBlockEntity::new)
            .item(GravestoneBlockItem::new)
            .register();

    public static final DeferredBlockEntity<GravestoneBlockEntity> GRAVESTONE_BLOCK_ENTITY = Holders.createBlockEntity(GRAVESTONE_BLOCK);

    public static final DeferredBlock<SimpleBlockEntityBlock.WidowBloom> WIDOW_BLOOM_BLOCK = REGISTREE.block("widow_bloom", SimpleBlockEntityBlock.WidowBloom::new)
            .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
            .properties(BlockBehaviour.Properties::noOcclusion)
            .blockEntity(SimpleBlockEntity.WidowBloom::new)
            .item()
            .register();

    public static final DeferredBlockEntity<SimpleBlockEntity.WidowBloom> WIDOW_BLOOM_BLOCK_ENTITY = Holders.createBlockEntity(WIDOW_BLOOM_BLOCK);

    public static final DeferredBlock<SimpleBlockEntityBlock.SkullBlossom> SKULL_BLOSSOM_SKELETON_BLOCK = REGISTREE.block("skull_blossom_skeleton", SimpleBlockEntityBlock.SkullBlossom::new)
            .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
            .properties(BlockBehaviour.Properties::noOcclusion)
            .item()
            .register();

    public static final DeferredBlock<SimpleBlockEntityBlock.SkullBlossom> SKULL_BLOSSOM_WITHER_BLOCK = REGISTREE.block("skull_blossom_wither", SimpleBlockEntityBlock.SkullBlossom::new)
            .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
            .properties(BlockBehaviour.Properties::noOcclusion)
            .item()
            .register();

    public static final DeferredBlockEntity<SimpleBlockEntity.SkullBlossom> SKULL_BLOSSOM_BLOCK_ENTITY = REGISTREE.blockEntity("skull_blossom", SimpleBlockEntity.SkullBlossom::new)
            .validBlock(SKULL_BLOSSOM_SKELETON_BLOCK, SKULL_BLOSSOM_WITHER_BLOCK)
            .register();

    public static final DeferredBlock<CookieJarBlock> COOKIE_JAR_BLOCK = REGISTREE.block("cookie_jar", CookieJarBlock::new)
            .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
            .blockEntity(CookieJarBlockEntity::new)
            .item()
            .register();

    public static final DeferredBlockEntity<CookieJarBlockEntity> COOKIE_JAR_BLOCK_ENTITY = Holders.createBlockEntity(COOKIE_JAR_BLOCK);

    public static final DeferredMenu<CookieJarMenu> COOKIE_JAR_MENU = REGISTREE.menu("cookie_jar", CookieJarMenu::new)
            .screen(() -> () -> CookieJarMenuScreen::new)
            .register();

    public static final DeferredBlock<PlushieBlock> PLUSHIE_BLOCK = REGISTREE.block("plushie", PlushieBlock::new)
            .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.WOOL.white()))
            .properties(BlockBehaviour.Properties::noOcclusion)
            .blockEntity(PlushieBlockEntity::new)
            .item(builder -> builder.properties(properties -> properties.equippable(EquipmentSlot.HEAD)))
            .register();

    public static final DeferredItem<PlushieBlockItem> PLUSHIE_BLOCK_ITEM = Holders.createItem(PLUSHIE_BLOCK);
    public static final DeferredBlockEntity<PlushieBlockEntity> PLUSHIE_BLOCK_ENTITY = Holders.createBlockEntity(PLUSHIE_BLOCK);

    public static final DeferredDataComponent<Unit> PLUSHIE_RENDER_NAME = REGISTREE.dataComponent("render_name", builder -> builder
            .persistent(Unit.CODEC)
            .networkSynchronized(Unit.STREAM_CODEC)
    );

    public static final ResourceKey<CreativeModeTab> CREATIVE_MODE_TAB = FurnitureUtil.creativeModeTab(REGISTREE, () -> new ItemStack(BERRY_BASKET.value()));

    public DecorationsFurnitureModule(IEventBus modBus) {
        REGISTREE.register(modBus);
        FantasyFurniture.FURNITURE_MODS.add(ID);

        NeoForge.EVENT_BUS.addListener(AnvilUpdateEvent.class, event -> {
            var left = event.getLeft();

            if (!left.is(PLUSHIE_BLOCK_ITEM) || !event.getRight().isEmpty()) {
                return;
            }

            var profile = PlushieBlockItem.profileFrom(event.getName());

            if(profile == null) {
                return;
            }

            var result = left.copy();
            PlushieBlockItem.setProfile(result, profile);
            event.setOutput(result);
        });
    }

    public static Identifier identifier(String identifier) {
        return Identifier.fromNamespaceAndPath(ID, identifier);
    }

    public static String id(String identifier) {
        return ID + Identifier.NAMESPACE_SEPARATOR + identifier;
    }
}