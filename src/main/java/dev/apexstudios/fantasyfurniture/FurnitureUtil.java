package dev.apexstudios.fantasyfurniture;

import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dev.apexstudios.apexcore.lib.data.ProviderTypes;
import dev.apexstudios.apexcore.lib.data.pack.FeaturePackGenerator;
import dev.apexstudios.apexcore.lib.data.pack.ModPackGenerator;
import dev.apexstudios.apexcore.lib.data.pack.PackGenerator;
import dev.apexstudios.apexcore.lib.data.provider.LanguageProvider;
import dev.apexstudios.apexcore.lib.data.provider.RecipeProvider;
import dev.apexstudios.apexcore.lib.data.provider.loot.LootTableProvider;
import dev.apexstudios.apexcore.lib.data.provider.model.ModelProvider;
import dev.apexstudios.apexcore.lib.data.provider.tag.IntrusiveTagProvider;
import dev.apexstudios.apexcore.lib.placement.PlacementRenderEvent;
import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.apexcore.lib.registree.holder.DeferredBlock;
import dev.apexstudios.apexcore.lib.util.ApexTags;
import dev.apexstudios.apexcore.lib.util.ApexUtil;
import dev.apexstudios.apexcore.lib.util.TagPair;
import dev.apexstudios.fantasyfurniture.block.BedDoubleBlock;
import dev.apexstudios.fantasyfurniture.block.BedSingleBlock;
import dev.apexstudios.fantasyfurniture.block.BenchBlock;
import dev.apexstudios.fantasyfurniture.block.BookshelfBlock;
import dev.apexstudios.fantasyfurniture.block.ChairBlock;
import dev.apexstudios.fantasyfurniture.block.ChandelierBlock;
import dev.apexstudios.fantasyfurniture.block.ChestBlock;
import dev.apexstudios.fantasyfurniture.block.CounterBlock;
import dev.apexstudios.fantasyfurniture.block.CushionBlock;
import dev.apexstudios.fantasyfurniture.block.DeskBlock;
import dev.apexstudios.fantasyfurniture.block.DrawerBlock;
import dev.apexstudios.fantasyfurniture.block.DresserBlock;
import dev.apexstudios.fantasyfurniture.block.FloorLightBlock;
import dev.apexstudios.fantasyfurniture.block.FurnitureDoorBlock;
import dev.apexstudios.fantasyfurniture.block.LockBoxBlock;
import dev.apexstudios.fantasyfurniture.block.OvenBlock;
import dev.apexstudios.fantasyfurniture.block.PaintingSmallBlock;
import dev.apexstudios.fantasyfurniture.block.PaintingWideBlock;
import dev.apexstudios.fantasyfurniture.block.ShelfBlock;
import dev.apexstudios.fantasyfurniture.block.SofaBlock;
import dev.apexstudios.fantasyfurniture.block.StoolBlock;
import dev.apexstudios.fantasyfurniture.block.TableBlock;
import dev.apexstudios.fantasyfurniture.block.WallLightBlock;
import dev.apexstudios.fantasyfurniture.block.WardrobeBlock;
import dev.apexstudios.fantasyfurniture.block.property.SofaConnection;
import dev.apexstudios.fantasyfurniture.station.FurnitureStationRecipeBuilder;
import dev.apexstudios.fantasyfurniture.station.FurnitureStationSetup;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.NeoForgeConditions;
import net.neoforged.neoforge.common.crafting.DifferenceIngredient;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;

public interface FurnitureUtil {
    Supplier<BlockBehaviour.Properties> PLANK_PROPERTIES = () -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS);
    Supplier<BlockBehaviour.Properties> STONE_PROPERTIES = () -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.STONE);
    Supplier<BlockBehaviour.Properties> WOOL_PROPERTIES = () -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.WHITE_WOOL);
    Supplier<BlockBehaviour.Properties> CARPET_PROPERTIES = () -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.WHITE_CARPET);
    Supplier<BlockBehaviour.Properties> DRESSER_PROPERTIES = () -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.CHEST).pushReaction(PushReaction.BLOCK);
    Supplier<BlockBehaviour.Properties> STOOL_PROPERTIES = PLANK_PROPERTIES;
    Supplier<BlockBehaviour.Properties> CUSHION_PROPERTIES = PLANK_PROPERTIES;
    Supplier<BlockBehaviour.Properties> LOCKBOX_PROPERTIES = DRESSER_PROPERTIES;
    Supplier<BlockBehaviour.Properties> DRAWER_PROPERTIES = DRESSER_PROPERTIES;
    Supplier<BlockBehaviour.Properties> CHAIR_PROPERTIES = mutating(PLANK_PROPERTIES, properties -> properties.pushReaction(PushReaction.BLOCK));
    Supplier<BlockBehaviour.Properties> BOOKSHELF_PROPERTIES = DRESSER_PROPERTIES;
    Supplier<BlockBehaviour.Properties> BED_PROPERTIES = () -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.WHITE_BED).pushReaction(PushReaction.BLOCK);
    Supplier<BlockBehaviour.Properties> DOOR_PROPERTIES = () -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_DOOR).pushReaction(PushReaction.BLOCK);
    Supplier<BlockBehaviour.Properties> DESK_PROPERTIES = DRESSER_PROPERTIES;
    Supplier<BlockBehaviour.Properties> PAINTING_WIDE_PROPERTIES = CHAIR_PROPERTIES;
    Supplier<BlockBehaviour.Properties> PAINTING_SMALL_PROPERTIES = PLANK_PROPERTIES;
    Supplier<BlockBehaviour.Properties> OVEN_PROPERTIES = () -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.SMOKER);
    Supplier<BlockBehaviour.Properties> CHEST_PROPERTIES = DRESSER_PROPERTIES;
    Supplier<BlockBehaviour.Properties> FLOOR_LIGHT_PROPERTIES = mutating(PLANK_PROPERTIES, properties -> properties.pushReaction(PushReaction.BLOCK).lightLevel(blockState -> 14));
    Supplier<BlockBehaviour.Properties> CHANDELIER_PROPERTIES = mutating(PLANK_PROPERTIES, properties -> properties.lightLevel(blockState -> 14));
    Supplier<BlockBehaviour.Properties> SHELF_PROPERTIES = PLANK_PROPERTIES;
    Supplier<BlockBehaviour.Properties> SOFA_PROPERTIES = PLANK_PROPERTIES;
    Supplier<BlockBehaviour.Properties> COUNTER_PROPERTIES = DRESSER_PROPERTIES;
    Supplier<BlockBehaviour.Properties> WALL_LIGHT_PROPERTIES = () -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.WALL_TORCH);
    Supplier<BlockBehaviour.Properties> BENCH_PROPERTIES = mutating(PLANK_PROPERTIES, properties -> properties.pushReaction(PushReaction.BLOCK));
    Supplier<BlockBehaviour.Properties> WARDROBE_PROPERTIES = DRESSER_PROPERTIES;
    Supplier<BlockBehaviour.Properties> TABLE_PROPERTIES = PLANK_PROPERTIES;
    Supplier<BlockBehaviour.Properties> STAIRS_PROPERTIES = () -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_STAIRS);
    Supplier<BlockBehaviour.Properties> SLAB_PROPERTIES = () -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_SLAB);
    Supplier<BlockBehaviour.Properties> FENCE_PROPERTIES = () -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_FENCE);
    Supplier<BlockBehaviour.Properties> FENCE_GATE_PROPERTIES = () -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_FENCE_GATE);
    Supplier<BlockBehaviour.Properties> TRAPDOOR_PROPERTIES = () -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_TRAPDOOR);
    Supplier<BlockBehaviour.Properties> PRESSURE_PLATE_PROPERTIES = () -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PRESSURE_PLATE);
    Supplier<BlockBehaviour.Properties> HANGING_SIGN_BLOCK_PROPERTIES = () -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_HANGING_SIGN);
    Supplier<BlockBehaviour.Properties> WALL_HANGING_SIGN_BLOCK_PROPERTIES = () -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_WALL_HANGING_SIGN);
    Supplier<BlockBehaviour.Properties> SIGN_BLOCK_PROPERTIES = () -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_SIGN);
    Supplier<BlockBehaviour.Properties> WALL_SIGN_BLOCK_PROPERTIES = () -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_WALL_SIGN);

    Supplier<Item.Properties> SIGN_ITEM_PROPERTIES = () -> new Item.Properties().stacksTo(16);

    static <TBlock extends Block> DeferredBlock<TBlock> planks(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        var block = registree.registerBlock(Names.PLANKS, factory, PLANK_PROPERTIES);
        registree.registerSimpleBlockItem(block);
        return block;
    }

    static <TBlock extends Block> DeferredBlock<TBlock> bricks(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        var block = registree.registerBlock(Names.BRICKS, factory, STONE_PROPERTIES);
        registree.registerSimpleBlockItem(block);
        return block;
    }

    static <TBlock extends Block> DeferredBlock<TBlock> wool(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        var block = registree.registerBlock(Names.WOOL, factory, WOOL_PROPERTIES);
        registree.registerSimpleBlockItem(block);
        return block;
    }

    static <TBlock extends CarpetBlock> DeferredBlock<TBlock> carpet(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        var block = registree.registerBlock(Names.CARPET, factory, CARPET_PROPERTIES);
        registree.registerSimpleBlockItem(block);
        return block;
    }

    static <TBlock extends DresserBlock> DeferredBlock<TBlock> dresser(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        var block = registree.registerBlock(Names.DRESSER, factory, DRESSER_PROPERTIES);
        registree.registerSimpleBlockItem(block);
        return block;
    }

    static <TBlock extends StoolBlock> DeferredBlock<TBlock> stool(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        var block = registree.registerBlock(Names.STOOL, factory, STOOL_PROPERTIES);
        registree.registerSimpleBlockItem(block);
        return block;
    }

    static <TBlock extends CushionBlock> DeferredBlock<TBlock> cushion(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        var block = registree.registerBlock(Names.CUSHION, factory, CUSHION_PROPERTIES);
        registree.registerSimpleBlockItem(block);
        return block;
    }

    static <TBlock extends LockBoxBlock> DeferredBlock<TBlock> lockbox(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        var block = registree.registerBlock(Names.LOCKBOX, factory, LOCKBOX_PROPERTIES);
        registree.registerSimpleBlockItem(block);
        return block;
    }

    static <TBlock extends DrawerBlock> DeferredBlock<TBlock> drawer(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        var block = registree.registerBlock(Names.DRAWER, factory, DRAWER_PROPERTIES);
        registree.registerSimpleBlockItem(block);
        return block;
    }

    static <TBlock extends ChairBlock> DeferredBlock<TBlock> chair(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        var block = registree.registerBlock(Names.CHAIR, factory, CHAIR_PROPERTIES);
        registree.registerSimpleBlockItem(block);
        return block;
    }

    static <TBlock extends BookshelfBlock> DeferredBlock<TBlock> bookshelf(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        var block = registree.registerBlock(Names.BOOKSHELF, factory, BOOKSHELF_PROPERTIES);
        registree.registerSimpleBlockItem(block);
        return block;
    }

    static <TBlock extends BedSingleBlock> DeferredBlock<TBlock> bedSingle(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        var block = registree.registerBlock(Names.BED_SINGLE, factory, BED_PROPERTIES);
        registree.registerSimpleBlockItem(block);
        return block;
    }

    static <TBlock extends BedDoubleBlock> DeferredBlock<TBlock> bedDouble(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        var block = registree.registerBlock(Names.BED_DOUBLE, factory, BED_PROPERTIES);
        registree.registerSimpleBlockItem(block);
        return block;
    }

    static <TBlock extends FurnitureDoorBlock> DeferredBlock<TBlock> doorSingle(Registree registree, BlockSetType blockSet, BiFunction<BlockBehaviour.Properties, BlockSetType, TBlock> factory) {
        var block = registree.registerBlock(Names.DOOR_SINGLE, properties -> factory.apply(properties, blockSet), DOOR_PROPERTIES);
        registree.registerSimpleBlockItem(block);
        return block;
    }

    static <TBlock extends FurnitureDoorBlock> DeferredBlock<TBlock> doorDouble(Registree registree, BlockSetType blockSet, BiFunction<BlockBehaviour.Properties, BlockSetType, TBlock> factory) {
        var block = registree.registerBlock(Names.DOOR_DOUBLE, properties -> factory.apply(properties, blockSet), DOOR_PROPERTIES);
        registree.registerSimpleBlockItem(block);
        return block;
    }

    static <TBlock extends DeskBlock> DeferredBlock<TBlock> desk(Registree registree, boolean left, BiFunction<BlockBehaviour.Properties, Boolean, TBlock> factory) {
        var block = registree.registerBlock(left ? Names.DESK_LEFT : Names.DESK_RIGHT, properties -> factory.apply(properties, left), DESK_PROPERTIES);
        registree.registerSimpleBlockItem(block);
        return block;
    }

    static <TBlock extends PaintingWideBlock> DeferredBlock<TBlock> paintingWide(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        var block = registree.registerBlock(Names.PAINTING_WIDE, factory, PAINTING_WIDE_PROPERTIES);
        registree.registerSimpleBlockItem(block);
        return block;
    }

    static <TBlock extends PaintingSmallBlock> DeferredBlock<TBlock> paintingSmall(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        var block = registree.registerBlock(Names.PAINTING_SMALL, factory, PAINTING_SMALL_PROPERTIES);
        registree.registerSimpleBlockItem(block);
        return block;
    }

    static <TBlock extends OvenBlock> DeferredBlock<TBlock> oven(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        var block = registree.registerBlock(Names.OVEN, factory, OVEN_PROPERTIES);
        registree.registerSimpleBlockItem(block);
        return block;
    }

    static <TBlock extends ChestBlock> DeferredBlock<TBlock> chest(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        var block = registree.registerBlock(Names.CHEST, factory, CHEST_PROPERTIES);
        registree.registerSimpleBlockItem(block);
        return block;
    }

    static <TBlock extends FloorLightBlock> DeferredBlock<TBlock> floorLight(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        var block = registree.registerBlock(Names.FLOOR_LIGHT, factory, FLOOR_LIGHT_PROPERTIES);
        registree.registerSimpleBlockItem(block);
        return block;
    }

    static <TBlock extends ChandelierBlock> DeferredBlock<TBlock> chandelier(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        var block = registree.registerBlock(Names.CHANDELIER, factory, CHANDELIER_PROPERTIES);
        registree.registerSimpleBlockItem(block);
        return block;
    }

    static <TBlock extends ShelfBlock> DeferredBlock<TBlock> shelf(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        var block = registree.registerBlock(Names.SHELF, factory, SHELF_PROPERTIES);
        registree.registerSimpleBlockItem(block);
        return block;
    }

    static <TBlock extends SofaBlock> DeferredBlock<TBlock> sofa(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        var block = registree.registerBlock(Names.SOFA, factory, SOFA_PROPERTIES);
        registree.registerSimpleBlockItem(block);
        return block;
    }

    static <TBlock extends CounterBlock> DeferredBlock<TBlock> counter(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        var block = registree.registerBlock(Names.COUNTER, factory, COUNTER_PROPERTIES);
        registree.registerSimpleBlockItem(block);
        return block;
    }

    static <TBlock extends WallLightBlock> DeferredBlock<TBlock> wallLight(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        var block = registree.registerBlock(Names.WALL_LIGHT, factory, WALL_LIGHT_PROPERTIES);
        registree.registerSimpleBlockItem(block);
        return block;
    }

    static <TBlock extends BenchBlock> DeferredBlock<TBlock> bench(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        var block = registree.registerBlock(Names.BENCH, factory, BENCH_PROPERTIES);
        registree.registerSimpleBlockItem(block);
        return block;
    }

    static <TBlock extends WardrobeBlock> DeferredBlock<TBlock> wardrobe(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        var block = registree.registerBlock(Names.WARDROBE, factory, WARDROBE_PROPERTIES);
        registree.registerSimpleBlockItem(block);
        return block;
    }

    static <TBlock extends TableBlock> DeferredBlock<TBlock> table(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        var block = registree.registerBlock(Names.TABLE, factory, TABLE_PROPERTIES);
        registree.registerSimpleBlockItem(block);
        return block;
    }

    static DeferredBlock<StairBlock> stairs(Registree registree, Supplier<? extends Block> baseBlock) {
        var block = registree.registerBlock(Names.STAIRS, properties -> new StairBlock(baseBlock.get().defaultBlockState(), properties), experimental(STAIRS_PROPERTIES));
        registree.registerSimpleBlockItem(block);
        return block;
    }

    static DeferredBlock<SlabBlock> slab(Registree registree) {
        var block = registree.registerBlock(Names.SLAB, SlabBlock::new, experimental(SLAB_PROPERTIES));
        registree.registerSimpleBlockItem(block);
        return block;
    }

    static DeferredBlock<FenceBlock> fence(Registree registree) {
        var block = registree.registerBlock(Names.FENCE, FenceBlock::new, experimental(FENCE_PROPERTIES));
        registree.registerSimpleBlockItem(block);
        return block;
    }

    static DeferredBlock<FenceGateBlock> fenceGate(Registree registree, WoodType woodType) {
        var block = registree.registerBlock(Names.FENCE_GATE, properties -> new FenceGateBlock(woodType, properties), experimental(FENCE_GATE_PROPERTIES));
        registree.registerSimpleBlockItem(block);
        return block;
    }

    static DeferredBlock<TrapDoorBlock> trapdoor(Registree registree, BlockSetType blockSet) {
        var block = registree.registerBlock(Names.TRAPDOOR, properties -> new TrapDoorBlock(blockSet, properties), experimental(TRAPDOOR_PROPERTIES));
        registree.registerSimpleBlockItem(block);
        return block;
    }

    static DeferredBlock<PressurePlateBlock> pressurePlate(Registree registree, BlockSetType blockSet) {
        var block = registree.registerBlock(Names.PRESSURE_PLATE, properties -> new PressurePlateBlock(blockSet, properties), experimental(PRESSURE_PLATE_PROPERTIES));
        registree.registerSimpleBlockItem(block);
        return block;
    }

    static SignPair<CeilingHangingSignBlock, WallHangingSignBlock> hangingSign(Registree registree, WoodType woodType) {
        var ceilingSign = registree.registerBlock(Names.HANGING_SIGN, properties -> new CeilingHangingSignBlock(woodType, properties), experimental(HANGING_SIGN_BLOCK_PROPERTIES));
        var wallSign = registree.registerBlock(Names.WALL_HANGING_SIGN, properties -> new WallHangingSignBlock(woodType, properties), experimental(mutating(
                WALL_HANGING_SIGN_BLOCK_PROPERTIES, properties -> properties.overrideLootTable(ceilingSign.value().getLootTable()))
        ));
        registree.registerItem(Names.HANGING_SIGN, properties -> new HangingSignItem(ceilingSign.value(), wallSign.value(), properties), SIGN_ITEM_PROPERTIES);
        return new SignPair<>(ceilingSign, wallSign);
    }

    static SignPair<StandingSignBlock, WallSignBlock> sign(Registree registree, WoodType woodType) {
        var standingSign = registree.registerBlock(Names.SIGN, properties -> new StandingSignBlock(woodType, properties), experimental(SIGN_BLOCK_PROPERTIES));
        var wallSign = registree.registerBlock(Names.WALL_SIGN, properties -> new WallSignBlock(woodType, properties), experimental(mutating(
                WALL_SIGN_BLOCK_PROPERTIES, properties -> properties.overrideLootTable(standingSign.value().getLootTable())
        )));
        registree.registerItem(Names.SIGN, properties -> new SignItem(standingSign.value(), wallSign.value(), properties), SIGN_ITEM_PROPERTIES);
        return new SignPair<>(standingSign, wallSign);
    }

    static ResourceKey<CreativeModeTab> creativeModeTab(Registree registree, ItemLike displayItem) {
        return registree.registerCreativeModeTab(Names.CREATIVE_MODE_TAB, () -> new ItemStack(displayItem), (parameters, output) -> registree
                .asLookup(Registries.ITEM)
                .filterFeatures(parameters.enabledFeatures())
                .listElements()
                .filter(Holder::isBound)
                .map(Holder::value)
                .forEach(output::accept)
        );
    }

    private static <T> Supplier<T> mutating(Supplier<T> initial, Consumer<T> mutator) {
        return () -> {
            var properties = initial.get();
            mutator.accept(properties);
            return properties;
        };
    }

    private static Supplier<BlockBehaviour.Properties> experimental(Supplier<BlockBehaviour.Properties> initial) {
        return mutating(initial, properties -> properties.requiredFeatures(FantasyFurniture.EXPERIMENTAL));
    }

    static void registerEvents(IEventBus modBus, Registree registree, WoodType woodType) {
        modBus.addListener(BlockEntityTypeAddBlocksEvent.class, event -> {
            event.modify(FurnitureBlockEntities.INVENTORY.value(), Names.blocks(
                    registree, Names.DRESSER, Names.LOCKBOX, Names.DRAWER, Names.DESK_LEFT, Names.DESK_RIGHT,
                    Names.CHEST, Names.COUNTER, Names.WARDROBE
            ));

            event.modify(FurnitureBlockEntities.BOOKSHELF.value(), Names.blocks(registree, Names.BOOKSHELF));
            event.modify(BlockEntityType.SMOKER, Names.blocks(registree, Names.OVEN));
            event.modify(BlockEntityType.HANGING_SIGN, Names.blocks(registree, Names.HANGING_SIGN, Names.WALL_HANGING_SIGN));
            event.modify(BlockEntityType.SIGN, Names.blocks(registree, Names.SIGN, Names.WALL_SIGN));
        });

        modBus.addListener(FMLCommonSetupEvent.class, event -> event.enqueueWork(() -> {
            registerPoi(registree, PoiTypes.HOME, Names.BED_SINGLE, blockState -> blockState.getValue(BedBlock.PART) == BedPart.HEAD);
            registerPoi(registree, PoiTypes.HOME, Names.BED_DOUBLE, blockState -> blockState.getValue(BedBlock.PART) == BedPart.HEAD);
            registerPoi(registree, PoiTypes.BUTCHER, Names.BED_DOUBLE, Predicates.alwaysTrue());
        }));

        // Vanilla seems to be registering these for us
        /*modBus.addListener(FMLClientSetupEvent.class, event -> event.enqueueWork(() -> {
            Names.block(registree, Names.HANGING_SIGN, block -> registerMaterial(woodType, Sheets.HANGING_SIGN_MATERIALS, Sheets.HANGING_SIGN_MAPPER, true));
            Names.block(registree, Names.SIGN, block -> registerMaterial(woodType, Sheets.SIGN_MATERIALS, Sheets.SIGN_MAPPER, false));
        }));*/

        NeoForge.EVENT_BUS.addListener(PlacementRenderEvent.DefaultBlockState.class, event -> {
            var blockState = event.defaultBlockState();

            Names.block(registree, Names.SOFA, block -> {
                if(blockState.is(block))
                    event.setDefaultBlockState(SofaConnection.setConnection(event.level(), event.pos(), blockState));
            });
        });

        registree.registerEvents(modBus);
    }

    /*private static void registerMaterial(WoodType woodType, Map<WoodType, Material> materials, MaterialMapper materialMapper, boolean hanging) {
        if(materials.putIfAbsent(woodType, materialMapper.apply(ResourceLocation.parse(woodType.name()))) != null)
            throw new IllegalStateException("Duplicate wood type material registration: " + woodType.name() + " (" + (hanging ? "hanging" : "standing") + ')');
    }*/

    private static void registerPoi(Registree registree, ResourceKey<PoiType> poiType, String name, Predicate<BlockState> blockStateTest) {
        Names.block(registree, name, block -> ApexUtil.registerPoiBlockStates(poiType, block, blockStateTest));
    }

    static void registerDataGen(DataGenContext context, ModPackGenerator generator) {
        registerDataGen0(context, generator, generator);
    }

    static void registerDataGen(DataGenContext context, FeaturePackGenerator assetPack, FeaturePackGenerator dataPack) {
        registerDataGen0(context, assetPack, dataPack);
    }

    private static void registerDataGen0(DataGenContext context, PackGenerator<?> assetPack, PackGenerator<?> dataPack) {
        assetPack.providing(ProviderTypes.MODELS, ($, provider) -> registerModels(context, provider))
                .providing(ProviderTypes.LANGUAGE, ($, provider) -> registerLanguage(context, provider));

        dataPack.providing(ProviderTypes.LOOT_TABLE, ($, provider) -> registerLootTables(context, provider))
                .providing(ProviderTypes.BLOCK_TAGS, ($, provider) -> registerBlockTags(context, provider))
                .providing(ProviderTypes.ITEM_TAGS, ($, provider) -> registerItemTags(context, provider))
                .providing(ProviderTypes.RECIPES, ($, provider) -> registerRecipes(context, provider, $.enabledFeatures()));
    }

    static void registerLootTables(DataGenContext context, LootTableProvider provider) {
        provider.fromRegistree(context.registree);

        provider.block(blocks -> {
            Names.block(context.registree, Names.PLANKS, blocks::dropSelf);
            Names.block(context.registree, Names.BRICKS, blocks::dropSelf);
            Names.block(context.registree, Names.WOOL, blocks::dropSelf);
            Names.block(context.registree, Names.CARPET, blocks::dropSelf);
            Names.block(context.registree, Names.DRESSER, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
            Names.block(context.registree, Names.STOOL, blocks::dropSelf);
            Names.block(context.registree, Names.CUSHION, blocks::dropSelf);
            Names.block(context.registree, Names.LOCKBOX, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
            Names.block(context.registree, Names.DRAWER, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
            Names.block(context.registree, Names.CHAIR, blocks::dropSelf);
            Names.block(context.registree, Names.BOOKSHELF, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
            Names.block(context.registree, Names.BED_SINGLE, block -> blocks.accept(block, blocks.createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD)));
            Names.block(context.registree, Names.BED_DOUBLE, block -> blocks.accept(block, blocks.createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD)));
            Names.block(context.registree, Names.DOOR_SINGLE, block -> blocks.accept(block, blocks.createDoorTable(block)));
            Names.block(context.registree, Names.DOOR_DOUBLE, block -> blocks.accept(block, blocks.createDoorTable(block)));
            Names.block(context.registree, Names.DESK_LEFT, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
            Names.block(context.registree, Names.DESK_RIGHT, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
            Names.block(context.registree, Names.PAINTING_WIDE, blocks::dropSelf);
            Names.block(context.registree, Names.PAINTING_SMALL, blocks::dropSelf);
            Names.block(context.registree, Names.OVEN, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
            Names.block(context.registree, Names.CHEST, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
            Names.block(context.registree, Names.FLOOR_LIGHT, blocks::dropSelf);
            Names.block(context.registree, Names.CHANDELIER, blocks::dropSelf);
            Names.block(context.registree, Names.SHELF, blocks::dropSelf);
            Names.block(context.registree, Names.SOFA, blocks::dropSelf);
            Names.block(context.registree, Names.COUNTER, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
            Names.block(context.registree, Names.WALL_LIGHT, blocks::dropSelf);
            Names.block(context.registree, Names.BENCH, blocks::dropSelf);
            Names.block(context.registree, Names.WARDROBE, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
            Names.block(context.registree, Names.TABLE, blocks::dropSelf);
            Names.block(context.registree, Names.STAIRS, blocks::dropSelf);
            Names.block(context.registree, Names.SLAB, block -> blocks.accept(block, blocks.createSlabItemTable(block)));
            Names.block(context.registree, Names.FENCE, blocks::dropSelf);
            Names.block(context.registree, Names.FENCE_GATE, blocks::dropSelf);
            Names.block(context.registree, Names.TRAPDOOR, blocks::dropSelf);
            Names.block(context.registree, Names.PRESSURE_PLATE, blocks::dropSelf);
            Names.block(context.registree, Names.BUTTON, blocks::dropSelf);
            Names.block(context.registree, Names.HANGING_SIGN, blocks::dropSelf);
            // Names.block(context.registree, Names.WALL_HANGING_SIGN, blocks::dropSelf);
            Names.block(context.registree, Names.SIGN, blocks::dropSelf);
            // Names.block(context.registree, Names.WALL_SIGN, blocks::dropSelf);
        });
    }

    static void registerModels(DataGenContext context, ModelProvider provider) {
        var blockModels = provider.blockModels();
        provider.fromRegistree(context.registree);

        Names.block(context.registree, Names.PLANKS, blockModels::createTrivialCube);
        Names.block(context.registree, Names.BRICKS, blockModels::createTrivialCube);
        Names.block(context.registree, Names.WOOL, blockModels::createTrivialCube);

        Names.block(context.registree, Names.CARPET, block -> {
            var wool = context.registree.getValueOrThrow(Registries.BLOCK, Names.WOOL);
            var variant = BlockModelGenerators.plainVariant(TexturedModel.CARPET.get(wool).create(block, blockModels.modelOutput));
            blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, variant));
        });

        Names.block(context.registree, Names.DRESSER, blockModels::createNonTemplateHorizontalBlock); // TODO
        Names.block(context.registree, Names.STOOL, blockModels::createNonTemplateHorizontalBlock); // TODO
        Names.block(context.registree, Names.CUSHION, blockModels::createNonTemplateHorizontalBlock); // TODO
        Names.block(context.registree, Names.LOCKBOX, blockModels::createNonTemplateHorizontalBlock); // TODO
        Names.block(context.registree, Names.DRAWER, blockModels::createNonTemplateHorizontalBlock); // TODO
        Names.block(context.registree, Names.CHAIR, blockModels::createNonTemplateHorizontalBlock); // TODO
        Names.block(context.registree, Names.BOOKSHELF, blockModels::createNonTemplateHorizontalBlock); // TODO
        Names.block(context.registree, Names.BED_SINGLE, blockModels::createNonTemplateHorizontalBlock); // TODO
        Names.block(context.registree, Names.BED_DOUBLE, blockModels::createNonTemplateHorizontalBlock); // TODO
        Names.block(context.registree, Names.DOOR_SINGLE, blockModels::createNonTemplateHorizontalBlock); // TODO
        Names.block(context.registree, Names.DOOR_DOUBLE, blockModels::createNonTemplateHorizontalBlock); // TODO
        Names.block(context.registree, Names.DESK_LEFT, blockModels::createNonTemplateHorizontalBlock); // TODO
        Names.block(context.registree, Names.DESK_RIGHT, blockModels::createNonTemplateHorizontalBlock); // TODO
        Names.block(context.registree, Names.PAINTING_WIDE, blockModels::createNonTemplateHorizontalBlock); // TODO
        Names.block(context.registree, Names.PAINTING_SMALL, blockModels::createNonTemplateHorizontalBlock); // TODO
        Names.block(context.registree, Names.OVEN, blockModels::createNonTemplateHorizontalBlock); // TODO
        Names.block(context.registree, Names.CHEST, blockModels::createNonTemplateHorizontalBlock); // TODO
        Names.block(context.registree, Names.FLOOR_LIGHT, blockModels::createNonTemplateHorizontalBlock); // TODO
        Names.block(context.registree, Names.CHANDELIER, blockModels::createNonTemplateHorizontalBlock); // TODO
        Names.block(context.registree, Names.SHELF, blockModels::createNonTemplateHorizontalBlock); // TODO
        Names.block(context.registree, Names.SOFA, blockModels::createNonTemplateHorizontalBlock); // TODO
        Names.block(context.registree, Names.COUNTER, blockModels::createNonTemplateHorizontalBlock); // TODO
        Names.block(context.registree, Names.WALL_LIGHT, blockModels::createNonTemplateModelBlock); // TODO
        Names.block(context.registree, Names.BENCH, blockModels::createNonTemplateHorizontalBlock); // TODO
        Names.block(context.registree, Names.WARDROBE, blockModels::createNonTemplateHorizontalBlock); // TODO
        Names.block(context.registree, Names.TABLE, blockModels::createNonTemplateHorizontalBlock); // TODO
        /*Names.block(context.registree, Names.STAIRS, blockModels::createNonTemplateHorizontalBlock); // TODO
        Names.block(context.registree, Names.SLAB, blockModels::createNonTemplateModelBlock); // TODO
        Names.block(context.registree, Names.FENCE, blockModels::createNonTemplateModelBlock); // TODO
        Names.block(context.registree, Names.FENCE_GATE, blockModels::createNonTemplateHorizontalBlock); // TODO
        Names.block(context.registree, Names.TRAPDOOR, blockModels::createNonTemplateHorizontalBlock); // TODO
        Names.block(context.registree, Names.PRESSURE_PLATE, blockModels::createNonTemplateModelBlock); // TODO
        Names.block(context.registree, Names.BUTTON, blockModels::createNonTemplateHorizontalBlock); // TODO
        Names.block(context.registree, Names.HANGING_SIGN, blockModels::createNonTemplateModelBlock); // TODO
        Names.block(context.registree, Names.WALL_HANGING_SIGN, blockModels::createNonTemplateHorizontalBlock); // TODO
        Names.block(context.registree, Names.SIGN, blockModels::createNonTemplateHorizontalBlock); // TODO
        Names.block(context.registree, Names.WALL_SIGN, blockModels::createNonTemplateHorizontalBlock); // TODO*/

        Names.block(context.registree, Names.HANGING_SIGN, block -> {
            var hangingSign = context.registree.getValueOrThrow(Registries.BLOCK, Names.WALL_HANGING_SIGN);
            blockModels.createHangingSign(context.family.getBaseBlock(), block, hangingSign);
        });

        blockModels.familyWithExistingFullBlock(context.family.getBaseBlock()).generateFor(context.family);
    }

    static void registerLanguage(DataGenContext context, LanguageProvider provider) {
        Names.creativeModeTab(context.registree, key -> provider.addCreativeModeTab(key, "Fantasy's Furniture - " + context.englishName));

        context.registerLanguage(Names.PLANKS, "Planks", provider);
        context.registerLanguage(Names.BRICKS, "Bricks", provider);
        context.registerLanguage(Names.WOOL, "Wool", provider);
        context.registerLanguage(Names.CARPET, "Carpet", provider);
        context.registerLanguage(Names.DRESSER, "Dresser", provider);
        context.registerLanguage(Names.STOOL, "Stool", provider);
        context.registerLanguage(Names.CUSHION, "Cushion", provider);
        context.registerLanguage(Names.LOCKBOX, "Lockbox", provider);
        context.registerLanguage(Names.DRAWER, "Drawer", provider);
        context.registerLanguage(Names.CHAIR, "Chair", provider);
        context.registerLanguage(Names.BOOKSHELF, "Bookshelf", provider);
        context.registerLanguage(Names.BED_SINGLE, "Bed Single", provider);
        context.registerLanguage(Names.BED_DOUBLE, "Bed Double", provider);
        context.registerLanguage(Names.DOOR_SINGLE, "Door Single", provider);
        context.registerLanguage(Names.DOOR_DOUBLE, "Door Double", provider);
        context.registerLanguage(Names.DESK_LEFT, "Desk Left", provider);
        context.registerLanguage(Names.DESK_RIGHT, "Desk Right", provider);
        context.registerLanguage(Names.PAINTING_WIDE, "Painting Wide", provider);
        context.registerLanguage(Names.PAINTING_SMALL, "Painting Small", provider);
        context.registerLanguage(Names.OVEN, "Oven", provider);
        context.registerLanguage(Names.CHEST, "Chest", provider);
        context.registerLanguage(Names.FLOOR_LIGHT, "Floor Light", provider);
        context.registerLanguage(Names.CHANDELIER, "Chandelier", provider);
        context.registerLanguage(Names.SHELF, "Shelf", provider);
        context.registerLanguage(Names.SOFA, "Sofa", provider);
        context.registerLanguage(Names.COUNTER, "Counter", provider);
        context.registerLanguage(Names.WALL_LIGHT, "Wall Light", provider);
        context.registerLanguage(Names.BENCH, "Bench", provider);
        context.registerLanguage(Names.WARDROBE, "Wardrobe", provider);
        context.registerLanguage(Names.TABLE, "Table", provider);
        context.registerLanguage(Names.STAIRS, "Stairs", provider);
        context.registerLanguage(Names.SLAB, "Slab", provider);
        context.registerLanguage(Names.FENCE, "Fence", provider);
        context.registerLanguage(Names.FENCE_GATE, "Fence Gate", provider);
        context.registerLanguage(Names.TRAPDOOR, "Trapdoor", provider);
        context.registerLanguage(Names.PRESSURE_PLATE, "Pressure Plate", provider);
        context.registerLanguage(Names.BUTTON, "Button", provider);
        context.registerLanguage(Names.HANGING_SIGN, "Hanging Sign", provider);
        context.registerLanguage(Names.WALL_HANGING_SIGN, "Wall Hanging Sign", provider);
        context.registerLanguage(Names.SIGN, "Sign", provider);
        context.registerLanguage(Names.WALL_SIGN, "Wall Sign", provider);
    }

    static void registerBlockTags(DataGenContext context, IntrusiveTagProvider<Block> provider) {
        Names.block(context.registree, Names.PLANKS, block -> tag(provider, block, context.mineableTag, BlockTags.PLANKS));
        Names.block(context.registree, Names.BRICKS, block -> tag(provider, block, context.mineableTag, Tags.Blocks.STONES));
        Names.block(context.registree, Names.WOOL, block -> tag(provider, block, BlockTags.WOOL));
        Names.block(context.registree, Names.CARPET, block -> tag(provider, block, BlockTags.WOOL_CARPETS));
        Names.block(context.registree, Names.DRESSER, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        Names.block(context.registree, Names.LOCKBOX, block -> tag(provider, block, context.mineableTag));
        Names.block(context.registree, Names.DRAWER, block -> tag(provider, block, context.mineableTag));
        Names.block(context.registree, Names.CHAIR, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.SEAT_ORIGIN_ONLY, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        Names.block(context.registree, Names.BOOKSHELF, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED, BlockTags.ENCHANTMENT_POWER_PROVIDER));
        Names.block(context.registree, Names.BED_SINGLE, block -> tag(provider, block, context.mineableTag, BlockTags.BEDS, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        Names.block(context.registree, Names.BED_DOUBLE, block -> tag(provider, block, context.mineableTag, BlockTags.BEDS, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        Names.block(context.registree, Names.DOOR_SINGLE, block -> tag(provider, block, context.mineableTag, context.doorTag.block(), ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        Names.block(context.registree, Names.DOOR_DOUBLE, block -> tag(provider, block, context.mineableTag, context.doorTag.block(), ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        Names.block(context.registree, Names.DESK_LEFT, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        Names.block(context.registree, Names.DESK_RIGHT, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        Names.block(context.registree, Names.PAINTING_WIDE, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        Names.block(context.registree, Names.PAINTING_SMALL, block -> tag(provider, block, context.mineableTag));
        Names.block(context.registree, Names.OVEN, block -> tag(provider, block, BlockTags.MINEABLE_WITH_PICKAXE, Tags.Blocks.PLAYER_WORKSTATIONS_FURNACES));
        Names.block(context.registree, Names.CHEST, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        Names.block(context.registree, Names.FLOOR_LIGHT, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        Names.block(context.registree, Names.CHANDELIER, block -> tag(provider, block, context.mineableTag));
        Names.block(context.registree, Names.SHELF, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST));
        Names.block(context.registree, Names.SOFA, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST));
        Names.block(context.registree, Names.COUNTER, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST));
        Names.block(context.registree, Names.WALL_LIGHT, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST));
        Names.block(context.registree, Names.BENCH, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.SEAT_ORIGIN_ONLY, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        Names.block(context.registree, Names.WARDROBE, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        Names.block(context.registree, Names.TABLE, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST));
        Names.block(context.registree, Names.STAIRS, block -> tag(provider, block, context.mineableTag, context.stairsTag));
        Names.block(context.registree, Names.SLAB, block -> tag(provider, block, context.mineableTag, context.slabTag.block()));
        Names.block(context.registree, Names.FENCE, block -> tag(provider, block, context.mineableTag, context.fenceTag.block()));
        Names.block(context.registree, Names.FENCE_GATE, block -> tag(provider, block, context.mineableTag, BlockTags.FENCE_GATES));
        Names.block(context.registree, Names.TRAPDOOR, block -> tag(provider, block, context.mineableTag, context.trapdoorTag.block()));
        Names.block(context.registree, Names.PRESSURE_PLATE, block -> tag(provider, block, context.mineableTag, context.pressurePlateTag.block()));
        Names.block(context.registree, Names.BUTTON, block -> tag(provider, block, context.mineableTag, context.buttonTag.block()));
        Names.block(context.registree, Names.HANGING_SIGN, block -> tag(provider, block, context.mineableTag, BlockTags.CEILING_HANGING_SIGNS));
        Names.block(context.registree, Names.WALL_HANGING_SIGN, block -> tag(provider, block, context.mineableTag, BlockTags.WALL_HANGING_SIGNS));
        Names.block(context.registree, Names.SIGN, block -> tag(provider, block, context.mineableTag, BlockTags.STANDING_SIGNS));
        Names.block(context.registree, Names.WALL_SIGN, block -> tag(provider, block, context.mineableTag, BlockTags.WALL_SIGNS));
    }

    static void registerItemTags(DataGenContext context, IntrusiveTagProvider<Item> provider) {
        Names.item(context.registree, Names.PLANKS, item -> tag(provider, item, FantasyFurniture.FURNITURE_PLANKS));
        Names.item(context.registree, Names.BRICKS, item -> tag(provider, item, FantasyFurniture.FURNITURE_BRICKS));
        Names.item(context.registree, Names.WOOL, item -> tag(provider, item, FantasyFurniture.FURNITURE_WOOL));
        Names.item(context.registree, Names.CARPET, item -> tag(provider, item, ItemTags.WOOL_CARPETS));
        Names.item(context.registree, Names.BED_SINGLE, item -> tag(provider, item, ItemTags.BEDS));
        Names.item(context.registree, Names.BED_DOUBLE, item -> tag(provider, item, ItemTags.BEDS));
        Names.item(context.registree, Names.DOOR_SINGLE, item -> tag(provider, item, context.doorTag.item()));
        Names.item(context.registree, Names.DOOR_DOUBLE, item -> tag(provider, item, context.doorTag.item()));
        Names.item(context.registree, Names.OVEN, item -> tag(provider, item, Tags.Items.PLAYER_WORKSTATIONS_FURNACES));
        Names.item(context.registree, Names.STAIRS, item -> tag(provider, item, ItemTags.STAIRS));
        Names.item(context.registree, Names.SLAB, item -> tag(provider, item, context.slabTag.item()));
        Names.item(context.registree, Names.FENCE, item -> tag(provider, item, context.fenceTag.item()));
        Names.item(context.registree, Names.FENCE_GATE, item -> tag(provider, item, ItemTags.FENCE_GATES));
        Names.item(context.registree, Names.TRAPDOOR, item -> tag(provider, item, context.trapdoorTag.item()));
        Names.item(context.registree, Names.PRESSURE_PLATE, item -> tag(provider, item, context.pressurePlateTag.item()));
        Names.item(context.registree, Names.BUTTON, item -> tag(provider, item, context.buttonTag.item()));
        Names.item(context.registree, Names.WALL_HANGING_SIGN, item -> tag(provider, item, ItemTags.HANGING_SIGNS));
        Names.item(context.registree, Names.SIGN, item -> tag(provider, item, ItemTags.SIGNS));
    }

    @SafeVarargs
    static <TRegistry> void tag(IntrusiveTagProvider<TRegistry> provider, TRegistry element, TagKey<TRegistry>... tags) {
        for(var tag : tags) {
            if(tag != null)
                provider.tag(tag).withElement(element);
        }
    }

    static void registerRecipes(DataGenContext context, RecipeProvider provider, FeatureFlagSet enabledFeatures) {
        Names.block(context.registree, Names.PLANKS, block -> conversionRecipe(ItemTags.PLANKS, FantasyFurniture.FURNITURE_PLANKS, "has_" + Names.PLANKS, block, provider, enabledFeatures));
        Names.block(context.registree, Names.BRICKS, block -> conversionRecipe(ItemTags.STONE_CRAFTING_MATERIALS, FantasyFurniture.FURNITURE_BRICKS, "has_" + Names.BRICKS, block, provider, enabledFeatures));
        Names.block(context.registree, Names.WOOL, block -> conversionRecipe(ItemTags.WOOL, FantasyFurniture.FURNITURE_WOOL, "has_" + Names.WOOL, block, provider, enabledFeatures));
        Names.block(context.registree, Names.CARPET, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        Names.block(context.registree, Names.DRESSER, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        Names.block(context.registree, Names.STOOL, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        Names.block(context.registree, Names.CUSHION, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        Names.block(context.registree, Names.LOCKBOX, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        Names.block(context.registree, Names.DRAWER, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        Names.block(context.registree, Names.CHAIR, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        Names.block(context.registree, Names.BOOKSHELF, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        Names.block(context.registree, Names.BED_SINGLE, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        Names.block(context.registree, Names.BED_DOUBLE, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        Names.block(context.registree, Names.DOOR_SINGLE, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        Names.block(context.registree, Names.DOOR_DOUBLE, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        Names.block(context.registree, Names.DESK_LEFT, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        Names.block(context.registree, Names.DESK_RIGHT, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        Names.block(context.registree, Names.PAINTING_WIDE, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        Names.block(context.registree, Names.PAINTING_SMALL, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        Names.block(context.registree, Names.OVEN, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        Names.block(context.registree, Names.CHEST, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        Names.block(context.registree, Names.FLOOR_LIGHT, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        Names.block(context.registree, Names.CHANDELIER, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        Names.block(context.registree, Names.SHELF, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        Names.block(context.registree, Names.SOFA, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        Names.block(context.registree, Names.COUNTER, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        Names.block(context.registree, Names.WALL_LIGHT, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        Names.block(context.registree, Names.BENCH, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        Names.block(context.registree, Names.WARDROBE, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        Names.block(context.registree, Names.TABLE, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        Names.block(context.registree, Names.STAIRS, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        Names.block(context.registree, Names.SLAB, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        Names.block(context.registree, Names.FENCE, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        Names.block(context.registree, Names.FENCE_GATE, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        Names.block(context.registree, Names.TRAPDOOR, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        Names.block(context.registree, Names.PRESSURE_PLATE, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        Names.block(context.registree, Names.BUTTON, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        Names.block(context.registree, Names.HANGING_SIGN, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        Names.block(context.registree, Names.SIGN, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
    }

    static void conversionRecipe(TagKey<Item> baseTag, TagKey<Item> furnitureTag, String hasKey, ItemLike result, RecipeProvider provider, FeatureFlagSet enabledFeatures) {
        var output = result.asItem().isEnabled(enabledFeatures) ? provider.output() : provider.output().withConditions(NeoForgeConditions.featureFlagsEnabled(FantasyFurniture.EXPERIMENTAL));

        SingleItemRecipeBuilder
                .stonecutting(DifferenceIngredient.of(provider.tag(baseTag), provider.tag(furnitureTag)), RecipeCategory.MISC, result)
                .unlockedBy(hasKey, provider.has(baseTag))
                .save(output, RecipeProvider.recipeKeyWithPrefix(result, "conversion/"));
    }

    static void furnitureStationRecipe(DataGenContext context, ItemLike result, RecipeProvider provider, FeatureFlagSet enabledFeatures) {
        var wool = context.registree.getValue(Registries.BLOCK, Names.WOOL);
        var woolIngredient = wool == null ? null : Ingredient.of(wool);
        var output = result.asItem().isEnabled(enabledFeatures) ? provider.output() : provider.output().withConditions(NeoForgeConditions.featureFlagsEnabled(FantasyFurniture.EXPERIMENTAL));

        FurnitureStationRecipeBuilder
                .builder(RecipeCategory.MISC, Ingredient.of(context.family.getBaseBlock()), woolIngredient, provider.tag(FurnitureStationSetup.BINDING_AGENT), result)
                .group(context.family.getRecipeGroupPrefix().orElse(null))
                .unlockedBy(context.family.getRecipeUnlockedBy().orElseGet(() -> RecipeProvider.getHasName(context.family.getBaseBlock())), provider.has(context.family.getBaseBlock()))
                .save(output, RecipeProvider.recipeKeyWithPrefix(result, "furniture_station/"));
    }

    record DataGenContext(
            Registree registree,
            String englishName,
            BlockFamily family,
            TagKey<Block> mineableTag,
            TagPair doorTag,
            TagKey<Block> stairsTag,
            TagPair buttonTag,
            TagPair pressurePlateTag,
            TagPair trapdoorTag,
            TagPair fenceTag,
            TagPair slabTag
    ) {
       private void registerLanguage(String name, String englishName, LanguageProvider provider) {
           Names.block(registree, name, block -> provider.add(block, this.englishName + ' ' + englishName));
       }
    }

    record SignPair<TSign extends SignBlock, TWall extends SignBlock>(
            DeferredBlock<TSign> sign,
            DeferredBlock<TWall> wall
    ) { }

    interface Names {
        String PLANKS = "planks";
        String BRICKS = "bricks";
        String WOOL = "wool";
        String CARPET = "carpet";
        String DRESSER = "dresser";
        String STOOL = "stool";
        String CUSHION = "cushion";
        String LOCKBOX = "lockbox";
        String DRAWER = "drawer";
        String CHAIR = "chair";
        String BOOKSHELF = "bookshelf";
        String BED_SINGLE = "bed_single";
        String BED_DOUBLE = "bed_double";
        String DOOR_SINGLE = "door_single";
        String DOOR_DOUBLE = "door_double";
        String DESK_LEFT = "desk_left";
        String DESK_RIGHT = "desk_right";
        String PAINTING_WIDE = "painting_wide";
        String PAINTING_SMALL = "painting_small";
        String OVEN = "oven";
        String CHEST = "chest";
        String FLOOR_LIGHT = "floor_light";
        String CHANDELIER = "chandelier";
        String SHELF = "shelf";
        String SOFA = "sofa";
        String COUNTER = "counter";
        String WALL_LIGHT = "wall_light";
        String BENCH = "bench";
        String WARDROBE = "wardrobe";
        String TABLE = "table";
        String STAIRS = "stairs";
        String SLAB = "slab";
        String FENCE = "fence";
        String FENCE_GATE = "fence_gate";
        String TRAPDOOR = "trapdoor";
        String PRESSURE_PLATE = "pressure_plate";
        String BUTTON = "button";
        String HANGING_SIGN = "hanging_sign";
        String WALL_HANGING_SIGN = "wall_hanging_sign";
        String SIGN = "sign";
        String WALL_SIGN = "wall_sign";

        String CREATIVE_MODE_TAB = "furniture_set";

        static <TRegistry> boolean ifPresent(Registree registree, ResourceKey<? extends Registry<TRegistry>> registryType, String name, Consumer<? super TRegistry> action) {
            var value = registree.getValue(registryType, name);

            if(value != null) {
                action.accept(value);
                return true;
            }

            return false;
        }

        @CanIgnoreReturnValue
        static boolean block(Registree registree, String name, Consumer<? super Block> action) {
            return ifPresent(registree, Registries.BLOCK, name, action);
        }

        @CanIgnoreReturnValue
        static boolean item(Registree registree, String name, Consumer<? super Item> action) {
            if(block(registree, name, block -> action.accept(block.asItem())))
                return true;

            return ifPresent(registree, Registries.ITEM, name, action);
        }

        static void creativeModeTab(Registree registree, Consumer<ResourceKey<CreativeModeTab>> action) {
            registree.get(Registries.CREATIVE_MODE_TAB, CREATIVE_MODE_TAB).ifPresent(holder -> action.accept(holder.getKey()));
        }

        static Block[] blocks(Registree registree, String... names) {
            var blocks = Lists.<Block>newArrayList();

            for(var name : names) {
                block(registree, name, blocks::add);
            }

            return blocks.toArray(Block[]::new);
        }
    }
}
