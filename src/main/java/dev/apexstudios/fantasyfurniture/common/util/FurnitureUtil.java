package dev.apexstudios.fantasyfurniture.common.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import dev.apexstudios.apexcore.api.multiblock.MultiBlock;
import dev.apexstudios.fantasyfurniture.common.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.common.FurnitureBlockEntities;
import dev.apexstudios.fantasyfurniture.common.block.BedDoubleBlock;
import dev.apexstudios.fantasyfurniture.common.block.BedSingleBlock;
import dev.apexstudios.fantasyfurniture.common.block.BenchBlock;
import dev.apexstudios.fantasyfurniture.common.block.BookshelfBlock;
import dev.apexstudios.fantasyfurniture.common.block.ChairBlock;
import dev.apexstudios.fantasyfurniture.common.block.ChandelierBlock;
import dev.apexstudios.fantasyfurniture.common.block.ChestBlock;
import dev.apexstudios.fantasyfurniture.common.block.CounterBlock;
import dev.apexstudios.fantasyfurniture.common.block.CushionBlock;
import dev.apexstudios.fantasyfurniture.common.block.DeskBlock;
import dev.apexstudios.fantasyfurniture.common.block.DrawerBlock;
import dev.apexstudios.fantasyfurniture.common.block.DresserBlock;
import dev.apexstudios.fantasyfurniture.common.block.FloorLightBlock;
import dev.apexstudios.fantasyfurniture.common.block.FurnitureDoorBlock;
import dev.apexstudios.fantasyfurniture.common.block.LockBoxBlock;
import dev.apexstudios.fantasyfurniture.common.block.OvenBlock;
import dev.apexstudios.fantasyfurniture.common.block.PaintingSmallBlock;
import dev.apexstudios.fantasyfurniture.common.block.PaintingWideBlock;
import dev.apexstudios.fantasyfurniture.common.block.ShelfBlock;
import dev.apexstudios.fantasyfurniture.common.block.SofaBlock;
import dev.apexstudios.fantasyfurniture.common.block.StoolBlock;
import dev.apexstudios.fantasyfurniture.common.block.TableBlock;
import dev.apexstudios.fantasyfurniture.common.block.WallLightBlock;
import dev.apexstudios.fantasyfurniture.common.block.WardrobeBlock;
import dev.apexstudios.fantasyfurniture.common.ctm.CtmPacks;
import dev.apexstudios.registree.api.Registree;
import dev.apexstudios.registree.api.holder.DeferredBlock;
import java.util.Collections;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityTypes;
import net.minecraft.world.level.block.entity.SmokerBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.common.world.poi.ExtendPoiTypesEvent;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.mixins.BlockEntityTypeAccessor;
import net.neoforged.neoforge.transfer.item.WorldlyContainerWrapper;

public interface FurnitureUtil {
    Supplier<BlockBehaviour.Properties> PLANK_PROPERTIES = () -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS);
    Supplier<BlockBehaviour.Properties> STONE_PROPERTIES = () -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.STONE);
    Supplier<BlockBehaviour.Properties> WOOL_PROPERTIES = () -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.WOOL.white());
    Supplier<BlockBehaviour.Properties> CARPET_PROPERTIES = () -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.CARPET.white());
    Supplier<BlockBehaviour.Properties> DRESSER_PROPERTIES = () -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.CHEST).pushReaction(PushReaction.BLOCK);
    Supplier<BlockBehaviour.Properties> STOOL_PROPERTIES = mutating(PLANK_PROPERTIES, properties -> properties.bounceRestitution(.75F));
    Supplier<BlockBehaviour.Properties> CUSHION_PROPERTIES = mutating(PLANK_PROPERTIES, properties -> properties.bounceRestitution(.75F));
    Supplier<BlockBehaviour.Properties> LOCKBOX_PROPERTIES = DRESSER_PROPERTIES;
    Supplier<BlockBehaviour.Properties> DRAWER_PROPERTIES = DRESSER_PROPERTIES;
    Supplier<BlockBehaviour.Properties> CHAIR_PROPERTIES = mutating(PLANK_PROPERTIES, properties -> properties.bounceRestitution(.75F).pushReaction(PushReaction.BLOCK));
    Supplier<BlockBehaviour.Properties> BOOKSHELF_PROPERTIES = DRESSER_PROPERTIES;
    Supplier<BlockBehaviour.Properties> BED_PROPERTIES = () -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.BED.white()).pushReaction(PushReaction.BLOCK);
    Supplier<BlockBehaviour.Properties> DOOR_PROPERTIES = () -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_DOOR).pushReaction(PushReaction.BLOCK);
    Supplier<BlockBehaviour.Properties> DESK_PROPERTIES = DRESSER_PROPERTIES;
    Supplier<BlockBehaviour.Properties> PAINTING_WIDE_PROPERTIES = CHAIR_PROPERTIES;
    Supplier<BlockBehaviour.Properties> PAINTING_SMALL_PROPERTIES = PLANK_PROPERTIES;
    Supplier<BlockBehaviour.Properties> OVEN_PROPERTIES = () -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.SMOKER);
    Supplier<BlockBehaviour.Properties> CHEST_PROPERTIES = DRESSER_PROPERTIES;
    Supplier<BlockBehaviour.Properties> FLOOR_LIGHT_PROPERTIES = mutating(PLANK_PROPERTIES, properties -> properties.pushReaction(PushReaction.BLOCK).lightLevel(blockState -> MultiBlock.getIndex(blockState) == 1 ? 14 : 0));
    Supplier<BlockBehaviour.Properties> CHANDELIER_PROPERTIES = mutating(PLANK_PROPERTIES, properties -> properties.lightLevel(blockState -> 14));
    Supplier<BlockBehaviour.Properties> SHELF_PROPERTIES = PLANK_PROPERTIES;
    Supplier<BlockBehaviour.Properties> SOFA_PROPERTIES = mutating(PLANK_PROPERTIES, properties -> properties.bounceRestitution(.75F));
    Supplier<BlockBehaviour.Properties> COUNTER_PROPERTIES = DRESSER_PROPERTIES;
    Supplier<BlockBehaviour.Properties> WALL_LIGHT_PROPERTIES = () -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.WALL_TORCH);
    Supplier<BlockBehaviour.Properties> BENCH_PROPERTIES = mutating(PLANK_PROPERTIES, properties -> properties.bounceRestitution(.75F).pushReaction(PushReaction.BLOCK));
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

    Supplier<Item.Properties> SIGN_ITEM_PROPERTIES = () -> new Item.Properties().stacksTo(16).useBlockDescriptionPrefix();

    static <TBlock extends Block> DeferredBlock<TBlock> simpleBlock(Registree registree, String identifier, Function<BlockBehaviour.Properties, TBlock> factory, Supplier<BlockBehaviour.Properties> propertiesFactory) {
        var block = registree.registerBlock(identifier, factory, propertiesFactory);
        registree.registerSimpleBlockItem(block);
        return block;
    }

    static <TBlock extends Block> DeferredBlock<TBlock> planks(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(registree, Names.PLANKS, factory, PLANK_PROPERTIES);
    }

    static <TBlock extends Block> DeferredBlock<TBlock> bricks(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(registree, Names.BRICKS, factory, STONE_PROPERTIES);
    }

    static <TBlock extends Block> DeferredBlock<TBlock> wool(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(registree, Names.WOOL, factory, WOOL_PROPERTIES);
    }

    static <TBlock extends CarpetBlock> DeferredBlock<TBlock> carpet(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(registree, Names.CARPET, factory, CARPET_PROPERTIES);
    }

    static <TBlock extends DresserBlock> DeferredBlock<TBlock> dresser(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(registree, Names.DRESSER, factory, DRESSER_PROPERTIES);
    }

    static <TBlock extends StoolBlock> DeferredBlock<TBlock> stool(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(registree, Names.STOOL, factory, STOOL_PROPERTIES);
    }

    static <TBlock extends CushionBlock> DeferredBlock<TBlock> cushion(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(registree, Names.CUSHION, factory, CUSHION_PROPERTIES);
    }

    static <TBlock extends LockBoxBlock> DeferredBlock<TBlock> lockbox(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(registree, Names.LOCKBOX, factory, LOCKBOX_PROPERTIES);
    }

    static <TBlock extends DrawerBlock> DeferredBlock<TBlock> drawer(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(registree, Names.DRAWER, factory, DRAWER_PROPERTIES);
    }

    static <TBlock extends ChairBlock> DeferredBlock<TBlock> chair(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(registree, Names.CHAIR, factory, CHAIR_PROPERTIES);
    }

    static <TBlock extends BookshelfBlock> DeferredBlock<TBlock> bookshelf(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(registree, Names.BOOKSHELF, factory, BOOKSHELF_PROPERTIES);
    }

    static <TBlock extends BedSingleBlock> DeferredBlock<TBlock> bedSingle(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(registree, Names.BED_SINGLE, factory, BED_PROPERTIES);
    }

    static <TBlock extends BedDoubleBlock> DeferredBlock<TBlock> bedDouble(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(registree, Names.BED_DOUBLE, factory, BED_PROPERTIES);
    }

    static <TBlock extends FurnitureDoorBlock> DeferredBlock<TBlock> door(Registree registree, String identifier, Supplier<BlockSetType> blockSet, BiFunction<BlockBehaviour.Properties, BlockSetType, TBlock> factory) {
        return simpleBlock(registree, identifier, properties -> factory.apply(properties, blockSet.get()), DOOR_PROPERTIES);
    }

    static <TBlock extends FurnitureDoorBlock> DeferredBlock<TBlock> doorSingle(Registree registree, Supplier<BlockSetType> blockSet, BiFunction<BlockBehaviour.Properties, BlockSetType, TBlock> factory) {
        return door(registree, Names.DOOR_SINGLE, blockSet, factory);
    }

    static <TBlock extends FurnitureDoorBlock> DeferredBlock<TBlock> doorDouble(Registree registree, Supplier<BlockSetType> blockSet, BiFunction<BlockBehaviour.Properties, BlockSetType, TBlock> factory) {
        return door(registree, Names.DOOR_DOUBLE, blockSet, factory);
    }

    static <TBlock extends DeskBlock> DeferredBlock<TBlock> desk(Registree registree, boolean left, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(registree, left ? Names.DESK_LEFT : Names.DESK_RIGHT, factory, DESK_PROPERTIES);
    }

    static <TBlock extends PaintingWideBlock> DeferredBlock<TBlock> paintingWide(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(registree, Names.PAINTING_WIDE, factory, PAINTING_WIDE_PROPERTIES);
    }

    static <TBlock extends PaintingSmallBlock> DeferredBlock<TBlock> paintingSmall(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(registree, Names.PAINTING_SMALL, factory, PAINTING_SMALL_PROPERTIES);
    }

    static <TBlock extends OvenBlock> DeferredBlock<TBlock> oven(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(registree, Names.OVEN, factory, OVEN_PROPERTIES);
    }

    static <TBlock extends ChestBlock> DeferredBlock<TBlock> chest(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(registree, Names.CHEST, factory, CHEST_PROPERTIES);
    }

    static <TBlock extends FloorLightBlock> DeferredBlock<TBlock> floorLight(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(registree, Names.FLOOR_LIGHT, factory, FLOOR_LIGHT_PROPERTIES);
    }

    static <TBlock extends ChandelierBlock> DeferredBlock<TBlock> chandelier(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(registree, Names.CHANDELIER, factory, CHANDELIER_PROPERTIES);
    }

    static <TBlock extends ShelfBlock> DeferredBlock<TBlock> shelf(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(registree, Names.SHELF, factory, SHELF_PROPERTIES);
    }

    static <TBlock extends SofaBlock> DeferredBlock<TBlock> sofa(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(registree, Names.SOFA, factory, SOFA_PROPERTIES);
    }

    static <TBlock extends CounterBlock> DeferredBlock<TBlock> counter(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(registree, Names.COUNTER, factory, COUNTER_PROPERTIES);
    }

    static <TBlock extends WallLightBlock> DeferredBlock<TBlock> wallLight(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(registree, Names.WALL_LIGHT, factory, WALL_LIGHT_PROPERTIES);
    }

    static <TBlock extends BenchBlock> DeferredBlock<TBlock> bench(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(registree, Names.BENCH, factory, BENCH_PROPERTIES);
    }

    static <TBlock extends WardrobeBlock> DeferredBlock<TBlock> wardrobe(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(registree, Names.WARDROBE, factory, WARDROBE_PROPERTIES);
    }

    static <TBlock extends TableBlock> DeferredBlock<TBlock> table(Registree registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(registree, Names.TABLE, factory, TABLE_PROPERTIES);
    }

    static DeferredBlock<StairBlock> stairs(Registree registree, Supplier<? extends Block> baseBlock) {
        return simpleBlock(registree, Names.STAIRS, properties -> new StairBlock(baseBlock.get().defaultBlockState(), properties), STAIRS_PROPERTIES);
    }

    static DeferredBlock<SlabBlock> slab(Registree registree) {
        return simpleBlock(registree, Names.SLAB, SlabBlock::new, SLAB_PROPERTIES);
    }

    static DeferredBlock<FenceBlock> fence(Registree registree) {
        return simpleBlock(registree, Names.FENCE, FenceBlock::new, FENCE_PROPERTIES);
    }

    static DeferredBlock<FenceGateBlock> fenceGate(Registree registree, Supplier<WoodType> woodType) {
        return simpleBlock(registree, Names.FENCE_GATE, properties -> new FenceGateBlock(woodType.get(), properties), FENCE_GATE_PROPERTIES);
    }

    static DeferredBlock<TrapDoorBlock> trapdoor(Registree registree, Supplier<BlockSetType> blockSet) {
        return simpleBlock(registree, Names.TRAPDOOR, properties -> new TrapDoorBlock(blockSet.get(), properties), TRAPDOOR_PROPERTIES);
    }

    static DeferredBlock<PressurePlateBlock> pressurePlate(Registree registree, Supplier<BlockSetType> blockSet) {
        return simpleBlock(registree, Names.PRESSURE_PLATE, properties -> new PressurePlateBlock(blockSet.get(), properties), PRESSURE_PLATE_PROPERTIES);
    }

    static SignPair<CeilingHangingSignBlock, WallHangingSignBlock> hangingSign(Registree registree, Supplier<WoodType> woodType) {
        var ceilingSign = registree.registerBlock(Names.HANGING_SIGN, properties -> new CeilingHangingSignBlock(woodType.get(), properties), HANGING_SIGN_BLOCK_PROPERTIES);
        var wallSign = registree.registerBlock(Names.WALL_HANGING_SIGN, properties -> new WallHangingSignBlock(woodType.get(), properties), mutating(WALL_HANGING_SIGN_BLOCK_PROPERTIES, properties -> properties.overrideLootTable(ceilingSign.value().getLootTable())));
        registree.registerItem(Names.HANGING_SIGN, properties -> new HangingSignItem(ceilingSign.value(), wallSign.value(), properties), SIGN_ITEM_PROPERTIES);
        return new SignPair<>(ceilingSign, wallSign);
    }

    static SignPair<StandingSignBlock, WallSignBlock> sign(Registree registree, Supplier<WoodType> woodType) {
        var standingSign = registree.registerBlock(Names.SIGN, properties -> new StandingSignBlock(woodType.get(), properties), SIGN_BLOCK_PROPERTIES);
        var wallSign = registree.registerBlock(Names.WALL_SIGN, properties -> new WallSignBlock(woodType.get(), properties), mutating(WALL_SIGN_BLOCK_PROPERTIES, properties -> properties.overrideLootTable(standingSign.value().getLootTable())));
        registree.registerItem(Names.SIGN, properties -> new SignItem(standingSign.value(), wallSign.value(), properties), SIGN_ITEM_PROPERTIES);
        return new SignPair<>(standingSign, wallSign);
    }

    static ResourceKey<CreativeModeTab> creativeModeTab(Registree registree, Supplier<ItemStack> displayItem) {
        return registree.registerCreativeModeTab(Names.CREATIVE_MODE_TAB, displayItem, (parameters, output) -> registree
                .asLookup(Registries.ITEM)
                .filterFeatures(parameters.enabledFeatures())
                .listElements()
                .filter(Holder::isBound)
                .map(Holder::value)
                .forEach(output::accept)
        );
    }

    static <T> Supplier<T> mutating(Supplier<T> initial, Consumer<T> mutator) {
        return () -> {
            var properties = initial.get();
            mutator.accept(properties);
            return properties;
        };
    }

    static void registerEvents(IEventBus modBus, Registree registree, Supplier<WoodType> woodType) {
        FantasyFurniture.FURNITURE_MODS.add(registree.namespace());

        CtmPacks.register(registree);

        modBus.addListener(BlockEntityTypeAddBlocksEvent.class, event -> {
            appendValidBlocks(FurnitureBlockEntities.INVENTORY.value(), Names.blocks(
                    registree, Names.DRESSER, Names.LOCKBOX, Names.DRAWER, Names.DESK_LEFT, Names.DESK_RIGHT,
                    Names.CHEST, Names.COUNTER, Names.WARDROBE
            ));

            appendValidBlocks(FurnitureBlockEntities.BOOKSHELF.value(), Names.blocks(registree, Names.BOOKSHELF));
            appendValidBlocks(BlockEntityTypes.SMOKER, Names.blocks(registree, Names.OVEN));
            appendValidBlocks(BlockEntityTypes.HANGING_SIGN, Names.blocks(registree, Names.HANGING_SIGN, Names.WALL_HANGING_SIGN));
            appendValidBlocks(BlockEntityTypes.SIGN, Names.blocks(registree, Names.SIGN, Names.WALL_SIGN));
        });

        modBus.addListener(ExtendPoiTypesEvent.class, event -> {
            registerHomePoi(event, registree, Names.BED_SINGLE);
            registerHomePoi(event, registree, Names.BED_DOUBLE);
            Names.block(registree, Names.OVEN, block -> event.addBlockToPoi(PoiTypes.BUTCHER, block));
        });

        modBus.addListener(RegisterCapabilitiesEvent.class, event -> Names.block(
                registree,
                Names.OVEN,
                block -> {
                    event.registerBlockEntity(Capabilities.Item.BLOCK, BlockEntityTypes.SMOKER, WorldlyContainerWrapper::new);

                    if(!(block instanceof MultiBlock))
                        return;

                    event.registerBlock(Capabilities.Item.BLOCK, (level, pos, blockState, blockEntity, side) -> {
                        if(blockEntity == null)
                            blockEntity = MultiBlock.getBlockEntity(level, pos, blockState);
                        if(!(blockEntity instanceof SmokerBlockEntity smoker))
                            return null;

                        return new WorldlyContainerWrapper(smoker, side);
                    }, block);
                })
        );

        registree.registerEvents(modBus);
    }

    static VoxelShape getShape(VoxelShape shape, BlockState blockState, BlockPos worldPos) {
        return MultiBlock.fixShape(shape, blockState, worldPos);
    }

    static VoxelShape getShape(Map<Direction, VoxelShape> shapes, BlockState blockState, BlockPos worldPos) {
        var facing = blockState.getValueOrElse(HorizontalDirectionalBlock.FACING, Direction.NORTH);
        return getShape(shapes.get(facing), blockState, worldPos);
    }

    private static void registerHomePoi(ExtendPoiTypesEvent event, Registree registree, String name) {
        Names.block(registree, name, block -> event.addStatesToPoi(PoiTypes.HOME, block.getStateDefinition()
                .getPossibleStates()
                .stream()
                .filter(blockState -> blockState.getValue(BedBlock.PART) == BedPart.HEAD)
                .collect(Collectors.toSet())
        ));
    }

    private static void appendValidBlocks(BlockEntityType<?> blockEntityType, Block... blocks) {
        if(blocks.length == 0)
            return;

        // neoforges implementation does not work very well
        // when the given block entity type has 0 valid blocks initially
        // the determined common super type is pulled from the first registered block
        // which can differ vastly from the rest of the blocks causing 'IAE' in 'addValidBlock'
        //
        // our implementation is basically theirs but without the block type checking
        // we are assuming that the given blocks are of the correct block types
        var validBlocks = Sets.newHashSet(blockEntityType.getValidBlocks());
        Collections.addAll(validBlocks, blocks);
        ((BlockEntityTypeAccessor) blockEntityType).neoforge$setValidBlocks(validBlocks);
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

        static void block(Registree registree, String name, Consumer<? super Block> action) {
            var block = registree.getValue(Registries.BLOCK, name);

            if(block != null)
                action.accept(block);
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
