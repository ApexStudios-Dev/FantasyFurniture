package dev.apexstudios.fantasyfurniture.decorations.common;

import dev.apexstudios.fantasyfurniture.common.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import dev.apexstudios.fantasyfurniture.decorations.common.ber.DecorLayerDefinitions;
import dev.apexstudios.fantasyfurniture.decorations.common.ber.SimpleBlockEntity;
import dev.apexstudios.fantasyfurniture.decorations.common.ber.SimpleBlockEntityBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.ber.SimpleBlockEntityRenderer;
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
import dev.apexstudios.fantasyfurniture.decorations.common.grave.GravestoneBlockEntityRenderer;
import dev.apexstudios.fantasyfurniture.decorations.common.plushie.PlushieBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.plushie.PlushieBlockEntity;
import dev.apexstudios.fantasyfurniture.decorations.common.plushie.PlushieBlockEntityRenderer;
import dev.apexstudios.fantasyfurniture.decorations.common.plushie.PlushieBlockItem;
import dev.apexstudios.fantasyfurniture.decorations.common.util.DecorationUtil;
import dev.apexstudios.registree.Registree;
import dev.apexstudios.registree.holder.DeferredBlockEntityType;
import dev.apexstudios.registree.holder.DeferredDataComponentType;
import dev.apexstudios.registree.holder.DeferredMenuType;
import dev.apexstudios.registree.registrar.BlockEntityTypeRegistrar;
import dev.apexstudios.registree.registrar.BlockRegistrar;
import dev.apexstudios.registree.registrar.DataComponentTypeRegistrar;
import dev.apexstudios.registree.registrar.ItemRegistrar;
import dev.apexstudios.registree.registrar.MenuTypeRegistrar;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Unit;
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

@Mod(DecorationsFurnitureModule.ID)
public class DecorationsFurnitureModule {
    public static final String ID = "fantasyfurniture_decorations";
    public static final Registree REGISTREE = Registree.create(ID);
    public static final BlockRegistrar BLOCKS = REGISTREE.blocks();
    public static final ItemRegistrar ITEMS = REGISTREE.items();
    public static final BlockEntityTypeRegistrar BLOCK_ENTITY_TYPES = REGISTREE.blockEntityTypes();
    public static final MenuTypeRegistrar MENU_TYPES = REGISTREE.menuTypes();
    public static final DataComponentTypeRegistrar DATA_COMPONENT_TYPES = REGISTREE.dataComponentTypes();

    public static final DeferredBlock<BerryBasketBlock> BERRY_BASKET = DecorationUtil.berryBasket(BLOCKS, "berry").register();
    public static final DeferredBlock<BerryBasketBlock> BLUEBERRY_BASKET = DecorationUtil.berryBasket(BLOCKS, "blueberry").register();
    public static final DeferredBlock<BerryBasketBlock> STRAWBERRY_BASKET = DecorationUtil.berryBasket(BLOCKS, "strawberry").register();
    public static final DeferredBlock<BerryBasketBlock> SWEETBERRY_BASKET = DecorationUtil.berryBasket(BLOCKS, "sweetberry").register();
    public static final DeferredBlock<BoltsOfClothBlock> BOLTS_OF_CLOTH = FurnitureUtil.simpleBlock(BLOCKS, "bolts_of_cloth", BoltsOfClothBlock::new, FurnitureUtil.PLANK_PROPERTIES).register();
    public static final DeferredBlock<BowlBlock> BOWL = DecorationUtil.bowl(BLOCKS, null).register();
    public static final DeferredBlock<BowlBlock> BEETROOT_SOUP_BOWL = DecorationUtil.bowl(BLOCKS, "beetroot_soup").register();
    public static final DeferredBlock<BowlBlock> MUSHROOM_STEW_BOWL = DecorationUtil.bowl(BLOCKS, "mushroom_stew").register();
    public static final DeferredBlock<CoinStackBlock> GOLDEN_COIN_STACK = DecorationUtil.coinStack(BLOCKS, "golden").register();
    public static final DeferredBlock<CoinStackBlock> IRON_COIN_STACK = DecorationUtil.coinStack(BLOCKS, "iron").register();
    public static final DeferredBlock<BrewingCauldronBlock> BREWING_CAULDRON = FurnitureUtil.simpleBlock(BLOCKS, "brewing_cauldron", BrewingCauldronBlock::new, FurnitureUtil.PLANK_PROPERTIES).register();
    public static final DeferredBlock<HangingHerbsBlock> HANGING_HERBS = FurnitureUtil.simpleBlock(BLOCKS, "hanging_herbs", HangingHerbsBlock::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision)).register();
    public static final DeferredBlock<PaperStackBlock> PAPER_STACK = FurnitureUtil.simpleBlock(BLOCKS, "paper_stack", PaperStackBlock::new, FurnitureUtil.PLANK_PROPERTIES).register();
    public static final DeferredBlock<SpiderWebSmallBlock> SPIDER_WEB_SMALL = FurnitureUtil.simpleBlock(BLOCKS, "spider_web_small", SpiderWebSmallBlock::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision)).register();
    public static final DeferredBlock<SpiderWebWideBlock> SPIDER_WEB_WIDE = FurnitureUtil.simpleBlock(BLOCKS, "spider_web_wide", SpiderWebWideBlock::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision)).register();
    public static final DeferredBlock<ChainBlock> BRONZE_CHAIN = FurnitureUtil.simpleBlock(BLOCKS, "bronze_chain", ChainBlock::new, () -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.IRON_CHAIN)).register();
    public static final DeferredBlock<FairyLightsBlock> FAIRY_LIGHTS = FurnitureUtil.simpleBlock(BLOCKS, "fairy_lights", FairyLightsBlock::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision)).register();
    public static final DeferredBlock<StockingBlock> STOCKING = FurnitureUtil.simpleBlock(BLOCKS, "stocking", StockingBlock::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision)).register();
    public static final DeferredBlock<BookStackBlock> BOOK_STACK_0 = DecorationUtil.bookStack(BLOCKS, 0).register();
    public static final DeferredBlock<BookStackBlock> BOOK_STACK_1 = DecorationUtil.bookStack(BLOCKS, 1).register();
    public static final DeferredBlock<TankardsBlock> TANKARDS = DecorationUtil.tankards(BLOCKS, null).register();
    public static final DeferredBlock<TankardsBlock> TANKARDS_HONEYMEAD = DecorationUtil.tankards(BLOCKS, "honeymead").register();
    public static final DeferredBlock<TankardsBlock> TANKARDS_MILK = DecorationUtil.tankards(BLOCKS, "milk").register();
    public static final DeferredBlock<TankardsBlock> TANKARDS_SWEETBERRY = DecorationUtil.tankards(BLOCKS, "sweetberry").register();
    public static final DeferredBlock<MushroomsBlock> MUSHROOMS_RED = DecorationUtil.mushrooms(BLOCKS, true).register();
    public static final DeferredBlock<MushroomsBlock> MUSHROOMS_BROWN = DecorationUtil.mushrooms(BLOCKS, false).register();
    public static final DeferredBlock<MuffinsBlock> MUFFINS_BLUEBERRY = DecorationUtil.muffins(BLOCKS, "blueberry").register();
    public static final DeferredBlock<MuffinsBlock> MUFFINS_CHOCOLATE = DecorationUtil.muffins(BLOCKS, "chocolate").register();
    public static final DeferredBlock<MuffinsBlock> MUFFINS_SWEETBERRY = DecorationUtil.muffins(BLOCKS, "sweetberry").register();
    public static final DeferredBlock<FloatingTomesBlock> FLOATING_TOMES = FurnitureUtil.simpleBlock(BLOCKS, "floating_tomes", FloatingTomesBlock::new, FurnitureUtil.PLANK_PROPERTIES).register();
    public static final DeferredBlock<StackablePumpkinsBlock> STACKABLE_PUMPKINS = FurnitureUtil.simpleBlock(BLOCKS, "stackable_pumpkins", StackablePumpkinsBlock::new, FurnitureUtil.PLANK_PROPERTIES).register();
    public static final DeferredBlock<PotionBottlesBlock> POTION_BOTTLES = FurnitureUtil.simpleBlock(BLOCKS, "potion_bottles", PotionBottlesBlock::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision)).register();
    public static final DeferredBlock<PresentsBlock> PRESENTS = FurnitureUtil.simpleBlock(BLOCKS, "presents", PresentsBlock::new, FurnitureUtil.PLANK_PROPERTIES).register();
    public static final DeferredBlock<CoinStackBlock> COPPER_COIN_STACK = DecorationUtil.coinStack(BLOCKS, "copper").register();
    public static final DeferredBlock<SnowballsBlock> SNOWBALLS = FurnitureUtil.simpleBlock(BLOCKS, "snowballs", SnowballsBlock::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision)).register();
    public static final DeferredBlock<BoiledCremeTreatsBlock> BOILED_CREME_TREATS = FurnitureUtil.simpleBlock(BLOCKS, "boiled_creme_treats", BoiledCremeTreatsBlock::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision)).register();
    public static final DeferredBlock<SweetrollsBlock> SWEETROLLS = FurnitureUtil.simpleBlock(BLOCKS, "sweetrolls", SweetrollsBlock::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision)).register();
    public static final DeferredBlock<MeadBottlesBlock> MEAD_BOTTLES = FurnitureUtil.simpleBlock(BLOCKS, "mead_bottles", MeadBottlesBlock::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision)).register();
    public static final DeferredBlock<SoulGemsBlock> SOUL_GEMS_DARK = DecorationUtil.soulGems(BLOCKS, "dark").register();
    public static final DeferredBlock<SoulGemsBlock> SOUL_GEMS_LIGHT = DecorationUtil.soulGems(BLOCKS, "light").register();
    public static final DeferredBlock<FoodBlock> FOOD_0 = DecorationUtil.food(BLOCKS, 0).register();
    public static final DeferredBlock<FoodBlock> FOOD_1 = DecorationUtil.food(BLOCKS, 1).register();
    public static final DeferredBlock<FoodBlock> FOOD_2 = DecorationUtil.food(BLOCKS, 2).register();
    public static final DeferredBlock<FoodBlock> FOOD_3 = DecorationUtil.food(BLOCKS, 3).register();
    public static final DeferredBlock<TeaSetBlock> TEA_SET = FurnitureUtil.simpleBlock(BLOCKS, "tea_set", TeaSetBlock::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision)).register();
    public static final DeferredBlock<TeaCupsBlock> TEA_CUPS = FurnitureUtil.simpleBlock(BLOCKS, "tea_cups", TeaCupsBlock::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision)).register();
    public static final DeferredBlock<PlatterBlock> PLATTER_0 = DecorationUtil.platter(BLOCKS, 0).register();
    public static final DeferredBlock<PlatterBlock> PLATTER_1 = DecorationUtil.platter(BLOCKS, 1).register();
    public static final DeferredBlock<ChalicesBlock> CHALICES_0 = DecorationUtil.chalices(BLOCKS, 0, ChalicesBlock::new).register();
    public static final DeferredBlock<ChalicesBlock.AltModel> CHALICES_1 = DecorationUtil.chalices(BLOCKS, 1, ChalicesBlock.AltModel::new).register();
    public static final DeferredBlock<ChalicesBlock.AltModel> CHALICES_2 = DecorationUtil.chalices(BLOCKS, 2, ChalicesBlock.AltModel::new).register();
    public static final DeferredBlock<ChalicesBlock.Dyeable> CHALICES_3 = DecorationUtil.chalices(BLOCKS, 3, ChalicesBlock.Dyeable::new).register();
    public static final DeferredBlock<CandlesBlock> CANDLES_0 = DecorationUtil.candles(BLOCKS, 0).register();
    public static final DeferredBlock<CandlesBlock> CANDLES_1 = DecorationUtil.candles(BLOCKS, 1).register();
    public static final DeferredBlock<BannerBlock> BANNER = FurnitureUtil.simpleBlock(BLOCKS, "banner", BannerBlock::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, properties -> properties.noOcclusion().noCollision())).register();
    public static final DeferredBlock<BonePileBlock> BONE_PILE_SKELETON = DecorationUtil.bonePile(BLOCKS, "skeleton").register();
    public static final DeferredBlock<BonePileBlock> BONE_PILE_WITHER = DecorationUtil.bonePile(BLOCKS, "wither").register();
    public static final DeferredBlock<CrownBlock> CROWN = FurnitureUtil.simpleBlock(BLOCKS, "crown", CrownBlock::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision)).register();
    public static final DeferredBlock<CushionedCrownBlock> CUSHIONED_CROWN = FurnitureUtil.simpleBlock(BLOCKS, "cushioned_crown", CushionedCrownBlock::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision)).register();
    public static final DeferredBlock<FloorCushionBlock> FLOOR_CUSHION = FurnitureUtil.simpleBlock(BLOCKS, "floor_cushion", FloorCushionBlock::new, FurnitureUtil.PLANK_PROPERTIES).register();
    public static final DeferredBlock<CandelabraBlock> CANDELABRA_0 = DecorationUtil.candelabra(BLOCKS, 0, CandelabraBlock.SHAPE_0).register();
    public static final DeferredBlock<CandelabraBlock> CANDELABRA_1 = DecorationUtil.candelabra(BLOCKS, 1, CandelabraBlock.SHAPE_1).register();
    public static final DeferredBlock<WallMirrorSmallBlock> WALL_MIRROR_SMALL = FurnitureUtil.simpleBlock(BLOCKS, "wall_mirror_small", WallMirrorSmallBlock::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision)).register();
    public static final DeferredBlock<WallMirrorLargeBlock> WALL_MIRROR_LARGE = FurnitureUtil.simpleBlock(BLOCKS, "wall_mirror_large", WallMirrorLargeBlock::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision)).register();
    public static final DeferredBlock<PotteryBlock> POTTERY_0 = DecorationUtil.pottery(BLOCKS, 0, PotteryBlock.SHAPE_0).register();
    public static final DeferredBlock<PotteryBlock> POTTERY_1 = DecorationUtil.pottery(BLOCKS, 1, PotteryBlock.SHAPE_1).register();

    public static final DeferredBlock<GravestoneBlock> GRAVESTONE_BLOCK = FurnitureUtil.simpleBlock(BLOCKS, "gravestone", GravestoneBlock::new, FurnitureUtil.PLANK_PROPERTIES)
            .blockEntityType(GravestoneBlockEntity::new, builder -> builder
                    .renderer(() -> () -> GravestoneBlockEntityRenderer::new)
            )
            .register();

    public static final DeferredBlockEntityType<GravestoneBlockEntity> GRAVESTONE_BLOCK_ENTITY = DeferredBlockEntityType.createBlockEntityType(GRAVESTONE_BLOCK.getId());

    public static final DeferredBlock<SimpleBlockEntityBlock.WidowBloom> WIDOW_BLOOM_BLOCK = FurnitureUtil.simpleBlock(BLOCKS, "widow_bloom", SimpleBlockEntityBlock.WidowBloom::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noOcclusion))
            .blockEntityType(SimpleBlockEntity.WidowBloom::new, builder -> builder
                    .renderer(() -> () -> SimpleBlockEntityRenderer.create(DecorLayerDefinitions.WIDOW_BLOOM, blockEntity -> DecorLayerDefinitions.WIDOW_BLOOM_TEXTURE))
            )
            .register();

    public static final DeferredBlockEntityType<SimpleBlockEntity.WidowBloom> WIDOW_BLOOM_BLOCK_ENTITY = DeferredBlockEntityType.createBlockEntityType(WIDOW_BLOOM_BLOCK.getId());

    public static final DeferredBlockEntityType<SimpleBlockEntity.SkullBlossom> SKULL_BLOSSOM_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.builder("skull_blossom", SimpleBlockEntity.SkullBlossom::new)
            .renderer(() -> () -> SimpleBlockEntityRenderer.create(
                    DecorLayerDefinitions.SKULL_BLOSSOM,
                    blockEntity -> blockEntity.getBlockState().is(DecorationsFurnitureModule.SKULL_BLOSSOM_SKELETON_BLOCK) ? DecorLayerDefinitions.SKULL_BLOSSOM_SKELETON_TEXTURE : DecorLayerDefinitions.SKULL_BLOSSOM_WITHER_TEXTURE
            ))
            .register();

    public static final DeferredBlock<SimpleBlockEntityBlock.SkullBlossom> SKULL_BLOSSOM_SKELETON_BLOCK = FurnitureUtil.simpleBlock(BLOCKS, "skull_blossom_skeleton", SimpleBlockEntityBlock.SkullBlossom::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noOcclusion))
            .blockEntityType(SKULL_BLOSSOM_BLOCK_ENTITY)
            .register();

    public static final DeferredBlock<SimpleBlockEntityBlock.SkullBlossom> SKULL_BLOSSOM_WITHER_BLOCK = FurnitureUtil.simpleBlock(BLOCKS, "skull_blossom_wither", SimpleBlockEntityBlock.SkullBlossom::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noOcclusion))
            .blockEntityType(SKULL_BLOSSOM_BLOCK_ENTITY)
            .register();

    public static final DeferredBlock<CookieJarBlock> COOKIE_JAR_BLOCK = FurnitureUtil.simpleBlock(BLOCKS, "cookie_jar", CookieJarBlock::new, FurnitureUtil.PLANK_PROPERTIES)
            .blockEntityType(CookieJarBlockEntity::new)
            .register();

    public static final DeferredBlockEntityType<CookieJarBlockEntity> COOKIE_JAR_BLOCK_ENTITY = DeferredBlockEntityType.createBlockEntityType(COOKIE_JAR_BLOCK.getId());
    public static final DeferredMenuType<CookieJarMenu> COOKIE_JAR_MENU = MENU_TYPES.register("cookie_jar", CookieJarMenu::new, () -> () -> CookieJarMenuScreen::new);

    public static final DeferredBlock<PlushieBlock> PLUSHIE_BLOCK = FurnitureUtil.simpleBlock(BLOCKS, "plushie", PlushieBlock::new, FurnitureUtil.mutating(FurnitureUtil.WOOL_PROPERTIES, BlockBehaviour.Properties::noOcclusion))
            .blockEntityType(PlushieBlockEntity::new, builder -> builder
                    .renderer(() -> () -> PlushieBlockEntityRenderer::new)
            )
            .register();

    public static final DeferredBlockEntityType<PlushieBlockEntity> PLUSHIE_BLOCK_ENTITY = DeferredBlockEntityType.createBlockEntityType(PLUSHIE_BLOCK.getId());
    public static final DeferredDataComponentType<Unit> PLUSHIE_RENDER_NAME = DATA_COMPONENT_TYPES.registerUnit("render_name");

    public static final ResourceKey<CreativeModeTab> CREATIVE_MODE_TAB = FurnitureUtil.creativeModeTab(REGISTREE, () -> new ItemStack(BERRY_BASKET.value()));

    public DecorationsFurnitureModule(IEventBus modBus) {
        REGISTREE.registerEvents(modBus);
        FantasyFurniture.FURNITURE_MODS.add(ID);

        NeoForge.EVENT_BUS.addListener(AnvilUpdateEvent.class, event -> {
            var left = event.getLeft();

            if (!left.is(PLUSHIE_BLOCK.asItem()) || !event.getRight().isEmpty()) {
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