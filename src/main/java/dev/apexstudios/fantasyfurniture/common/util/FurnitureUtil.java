package dev.apexstudios.fantasyfurniture.common.util;

import com.google.common.collect.Lists;
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
import dev.apexstudios.registree.Registree;
import dev.apexstudios.registree.builder.BlockBuilder;
import dev.apexstudios.registree.builder.ItemBuilder;
import dev.apexstudios.registree.registrar.BlockRegistrar;
import dev.apexstudios.registree.registrar.CreativeModeTabRegistrar;
import dev.apexstudios.registree.registrar.ItemRegistrar;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.TypedInstance;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemInstance;
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
import net.minecraft.world.level.block.entity.SignBlockEntity;
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
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.transfer.item.WorldlyContainerWrapper;

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
    Supplier<BlockBehaviour.Properties> FLOOR_LIGHT_PROPERTIES = mutating(PLANK_PROPERTIES, properties -> properties.pushReaction(PushReaction.BLOCK).lightLevel(blockState -> MultiBlock.getIndex(blockState) == 1 ? 14 : 0));
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

    Consumer<Item.Properties> SIGN_ITEM_PROPERTIES = properties -> properties.stacksTo(16).useBlockDescriptionPrefix();

    static <TBlock extends Block> BlockBuilder<TBlock> simpleBlock(BlockRegistrar blocks, String identifier, Function<BlockBehaviour.Properties, TBlock> factory, Supplier<BlockBehaviour.Properties> propertiesFactory) {
        return blocks.builder(identifier, factory)
                .initialProperties(propertiesFactory)
                .item();
    }

    static <TBlock extends Block> BlockBuilder<TBlock> planks(BlockRegistrar blocks, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(blocks, Names.PLANKS, factory, PLANK_PROPERTIES);
    }

    static <TBlock extends Block> BlockBuilder<TBlock> bricks(BlockRegistrar blocks, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(blocks, Names.BRICKS, factory, STONE_PROPERTIES);
    }

    static <TBlock extends Block> BlockBuilder<TBlock> wool(BlockRegistrar blocks, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(blocks, Names.WOOL, factory, WOOL_PROPERTIES);
    }

    static <TBlock extends CarpetBlock> BlockBuilder<TBlock> carpet(BlockRegistrar blocks, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(blocks, Names.CARPET, factory, CARPET_PROPERTIES);
    }

    static <TBlock extends DresserBlock> BlockBuilder<TBlock> dresser(BlockRegistrar blocks, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(blocks, Names.DRESSER, factory, DRESSER_PROPERTIES)
                .blockEntityType(FurnitureBlockEntities.INVENTORY);
    }

    static <TBlock extends StoolBlock> BlockBuilder<TBlock> stool(BlockRegistrar blocks, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(blocks, Names.STOOL, factory, STOOL_PROPERTIES);
    }

    static <TBlock extends CushionBlock> BlockBuilder<TBlock> cushion(BlockRegistrar blocks, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(blocks, Names.CUSHION, factory, CUSHION_PROPERTIES);
    }

    static <TBlock extends LockBoxBlock> BlockBuilder<TBlock> lockbox(BlockRegistrar blocks, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(blocks, Names.LOCKBOX, factory, LOCKBOX_PROPERTIES)
                .blockEntityType(FurnitureBlockEntities.INVENTORY);
    }

    static <TBlock extends DrawerBlock> BlockBuilder<TBlock> drawer(BlockRegistrar blocks, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(blocks, Names.DRAWER, factory, DRAWER_PROPERTIES)
                .blockEntityType(FurnitureBlockEntities.INVENTORY);
    }

    static <TBlock extends ChairBlock> BlockBuilder<TBlock> chair(BlockRegistrar blocks, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(blocks, Names.CHAIR, factory, CHAIR_PROPERTIES);
    }

    static <TBlock extends BookshelfBlock> BlockBuilder<TBlock> bookshelf(BlockRegistrar blocks, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(blocks, Names.BOOKSHELF, factory, BOOKSHELF_PROPERTIES)
                .blockEntityType(FurnitureBlockEntities.BOOKSHELF);
    }

    static <TBlock extends BedSingleBlock> BlockBuilder<TBlock> bedSingle(BlockRegistrar blocks, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(blocks, Names.BED_SINGLE, factory, BED_PROPERTIES)
                .poiType(PoiTypes.HOME, BedBlock.PART, BedPart.HEAD);
    }

    static <TBlock extends BedDoubleBlock> BlockBuilder<TBlock> bedDouble(BlockRegistrar blocks, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(blocks, Names.BED_DOUBLE, factory, BED_PROPERTIES)
                .poiType(PoiTypes.HOME, BedBlock.PART, BedPart.HEAD);
    }

    static <TBlock extends FurnitureDoorBlock> BlockBuilder<TBlock> door(BlockRegistrar blocks, String identifier, Supplier<BlockSetType> blockSet, BiFunction<BlockBehaviour.Properties, BlockSetType, TBlock> factory) {
        return simpleBlock(blocks, identifier, properties -> factory.apply(properties, blockSet.get()), DOOR_PROPERTIES);
    }

    static <TBlock extends FurnitureDoorBlock> BlockBuilder<TBlock> doorSingle(BlockRegistrar blocks, Supplier<BlockSetType> blockSet, BiFunction<BlockBehaviour.Properties, BlockSetType, TBlock> factory) {
        return door(blocks, Names.DOOR_SINGLE, blockSet, factory);
    }

    static <TBlock extends FurnitureDoorBlock> BlockBuilder<TBlock> doorDouble(BlockRegistrar blocks, Supplier<BlockSetType> blockSet, BiFunction<BlockBehaviour.Properties, BlockSetType, TBlock> factory) {
        return door(blocks, Names.DOOR_DOUBLE, blockSet, factory);
    }

    static <TBlock extends DeskBlock> BlockBuilder<TBlock> desk(BlockRegistrar blocks, boolean left, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(blocks, left ? Names.DESK_LEFT : Names.DESK_RIGHT, factory, DESK_PROPERTIES)
                .blockEntityType(FurnitureBlockEntities.INVENTORY);
    }

    static <TBlock extends PaintingWideBlock> BlockBuilder<TBlock> paintingWide(BlockRegistrar blocks, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(blocks, Names.PAINTING_WIDE, factory, PAINTING_WIDE_PROPERTIES);
    }

    static <TBlock extends PaintingSmallBlock> BlockBuilder<TBlock> paintingSmall(BlockRegistrar blocks, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(blocks, Names.PAINTING_SMALL, factory, PAINTING_SMALL_PROPERTIES);
    }

    static <TBlock extends OvenBlock> BlockBuilder<TBlock> oven(BlockRegistrar blocks, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(blocks, Names.OVEN, factory, OVEN_PROPERTIES)
                .blockEntityType(() -> BlockEntityType.SMOKER)
                .poiType(PoiTypes.BUTCHER, blockState -> MultiBlock.getIndex(blockState) == 0)
                .capability(Capabilities.Item.BLOCK, (level, pos, blockState, blockEntity, side) -> {
                    if(blockEntity == null) {
                        blockEntity = MultiBlock.getBlockEntity(level, pos, blockState);
                    }

                    if(!(blockEntity instanceof SmokerBlockEntity smoker)) {
                        return null;
                    }

                    return new WorldlyContainerWrapper(smoker, side);
                });
    }

    static <TBlock extends ChestBlock> BlockBuilder<TBlock> chest(BlockRegistrar blocks, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(blocks, Names.CHEST, factory, CHEST_PROPERTIES)
                .blockEntityType(FurnitureBlockEntities.INVENTORY);
    }

    static <TBlock extends FloorLightBlock> BlockBuilder<TBlock> floorLight(BlockRegistrar blocks, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(blocks, Names.FLOOR_LIGHT, factory, FLOOR_LIGHT_PROPERTIES);
    }

    static <TBlock extends ChandelierBlock> BlockBuilder<TBlock> chandelier(BlockRegistrar blocks, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(blocks, Names.CHANDELIER, factory, CHANDELIER_PROPERTIES);
    }

    static <TBlock extends ShelfBlock> BlockBuilder<TBlock> shelf(BlockRegistrar blocks, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(blocks, Names.SHELF, factory, SHELF_PROPERTIES);
    }

    static <TBlock extends SofaBlock> BlockBuilder<TBlock> sofa(BlockRegistrar blocks, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(blocks, Names.SOFA, factory, SOFA_PROPERTIES);
    }

    static <TBlock extends CounterBlock> BlockBuilder<TBlock> counter(BlockRegistrar blocks, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(blocks, Names.COUNTER, factory, COUNTER_PROPERTIES)
                .blockEntityType(FurnitureBlockEntities.INVENTORY);
    }

    static <TBlock extends WallLightBlock> BlockBuilder<TBlock> wallLight(BlockRegistrar blocks, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(blocks, Names.WALL_LIGHT, factory, WALL_LIGHT_PROPERTIES);
    }

    static <TBlock extends BenchBlock> BlockBuilder<TBlock> bench(BlockRegistrar blocks, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(blocks, Names.BENCH, factory, BENCH_PROPERTIES);
    }

    static <TBlock extends WardrobeBlock> BlockBuilder<TBlock> wardrobe(BlockRegistrar blocks, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(blocks, Names.WARDROBE, factory, WARDROBE_PROPERTIES)
                .blockEntityType(FurnitureBlockEntities.INVENTORY);
    }

    static <TBlock extends TableBlock> BlockBuilder<TBlock> table(BlockRegistrar blocks, String identifier, Function<BlockBehaviour.Properties, TBlock> factory) {
        return simpleBlock(blocks, identifier, factory, TABLE_PROPERTIES);
    }

    static <TBlock extends TableBlock> BlockBuilder<TBlock> table(BlockRegistrar blocks, Function<BlockBehaviour.Properties, TBlock> factory) {
        return table(blocks, Names.TABLE, factory);
    }

    static BlockBuilder<StairBlock> stairs(BlockRegistrar blocks, Supplier<? extends Block> baseBlock) {
        return simpleBlock(blocks, Names.STAIRS, properties -> new StairBlock(baseBlock.get().defaultBlockState(), properties), STAIRS_PROPERTIES);
    }

    static BlockBuilder<SlabBlock> slab(BlockRegistrar blocks) {
        return simpleBlock(blocks, Names.SLAB, SlabBlock::new, SLAB_PROPERTIES);
    }

    static BlockBuilder<FenceBlock> fence(BlockRegistrar blocks) {
        return simpleBlock(blocks, Names.FENCE, FenceBlock::new, FENCE_PROPERTIES);
    }

    static BlockBuilder<FenceGateBlock> fenceGate(BlockRegistrar blocks, Supplier<WoodType> woodType) {
        return simpleBlock(blocks, Names.FENCE_GATE, properties -> new FenceGateBlock(woodType.get(), properties), FENCE_GATE_PROPERTIES);
    }

    static BlockBuilder<TrapDoorBlock> trapdoor(BlockRegistrar blocks, Supplier<BlockSetType> blockSet) {
        return simpleBlock(blocks, Names.TRAPDOOR, properties -> new TrapDoorBlock(blockSet.get(), properties), TRAPDOOR_PROPERTIES);
    }

    static BlockBuilder<PressurePlateBlock> pressurePlate(BlockRegistrar blocks, Supplier<BlockSetType> blockSet) {
        return simpleBlock(blocks, Names.PRESSURE_PLATE, properties -> new PressurePlateBlock(blockSet.get(), properties), PRESSURE_PLATE_PROPERTIES);
    }

    static SignPair<CeilingHangingSignBlock, WallHangingSignBlock> hangingSign(Registree registree, Supplier<WoodType> woodType) {
        var ceilingSign = ceilingHangingSignBlockBuilder(registree.blocks(), Names.HANGING_SIGN, woodType).register();
        var wallSign = wallHangingSignBlockBuilder(registree.blocks(), Names.WALL_HANGING_SIGN, ceilingSign, woodType).register();
        signItemBuilder(registree.items(), Names.HANGING_SIGN, ceilingSign, wallSign).register();
        return new SignPair<>(ceilingSign, wallSign);
    }

    static SignPair<StandingSignBlock, WallSignBlock> sign(Registree registree, Supplier<WoodType> woodType) {
        var standingSign = standingSignBlockBuilder(registree.blocks(), Names.SIGN, woodType).register();
        var wallSign = wallSignBlockBuilder(registree.blocks(), Names.WALL_SIGN, standingSign, woodType).register();
        signItemBuilder(registree.items(), Names.SIGN, standingSign, wallSign).register();
        return new SignPair<>(standingSign, wallSign);
    }

    static BlockBuilder<StandingSignBlock> standingSignBlockBuilder(BlockRegistrar blocks, String identifier, Supplier<WoodType> woodType) {
        return signBlockBuilder(blocks, identifier, StandingSignBlock::new, woodType, () -> BlockEntityType.SIGN)
                .initialProperties(SIGN_BLOCK_PROPERTIES);
    }

    static BlockBuilder<WallSignBlock> wallSignBlockBuilder(BlockRegistrar blocks, String identifier, Supplier<? extends StandingSignBlock> standingSignBlock, Supplier<WoodType> woodType) {
        return signBlockBuilder(blocks, identifier, WallSignBlock::new, woodType, () -> BlockEntityType.SIGN)
                .initialProperties(WALL_SIGN_BLOCK_PROPERTIES)
                .properties(properties -> properties.overrideLootTable(standingSignBlock.get().getLootTable()));
    }

    static BlockBuilder<CeilingHangingSignBlock> ceilingHangingSignBlockBuilder(BlockRegistrar blocks, String identifier, Supplier<WoodType> woodType) {
        return signBlockBuilder(blocks, identifier, CeilingHangingSignBlock::new, woodType, () -> BlockEntityType.HANGING_SIGN)
                .initialProperties(HANGING_SIGN_BLOCK_PROPERTIES);
    }

    static BlockBuilder<WallHangingSignBlock> wallHangingSignBlockBuilder(BlockRegistrar blocks, String identifier, Supplier<? extends CeilingHangingSignBlock> ceilingHangingSignBlock, Supplier<WoodType> woodType) {
        return signBlockBuilder(blocks, identifier, WallHangingSignBlock::new, woodType, () -> BlockEntityType.HANGING_SIGN)
                .initialProperties(WALL_HANGING_SIGN_BLOCK_PROPERTIES)
                .properties(properties -> properties.overrideLootTable(ceilingHangingSignBlock.get().getLootTable()));
    }

    static <TBlock extends SignBlock> BlockBuilder<TBlock> signBlockBuilder(BlockRegistrar blocks, String identifier, BiFunction<WoodType, BlockBehaviour.Properties, TBlock> factory, Supplier<WoodType> woodType, Supplier<? extends BlockEntityType<? extends SignBlockEntity>> blockEntityType) {
        return blocks.builder(identifier, properties -> factory.apply(woodType.get(), properties)).blockEntityType(blockEntityType);
    }

    static ItemBuilder<SignItem> signItemBuilder(ItemRegistrar items, String identifier, Supplier<? extends SignBlock> standingSignBlock, Supplier<? extends SignBlock> wallSignBlock) {
        return items.builder(identifier, properties -> new SignItem(standingSignBlock.get(), wallSignBlock.get(), properties))
                .properties(SIGN_ITEM_PROPERTIES);
    }

    static ResourceKey<CreativeModeTab> creativeModeTab(Registree registree, Supplier<ItemInstance> displayItem) {
        return registree.creativeModeTabs().register(Names.CREATIVE_MODE_TAB, displayItem, (parameters, output) -> registree.items().stream().forEach(output::accept));
    }

    static <T> Supplier<T> mutating(Supplier<T> initial, Consumer<T> mutator) {
        return () -> {
            var properties = initial.get();
            mutator.accept(properties);
            return properties;
        };
    }

    static void registerEvents(IEventBus modBus, Registree registree) {
        FantasyFurniture.FURNITURE_MODS.add(registree.namespace());
        CtmPacks.register(registree);
        registree.registerEvents(modBus);
    }

    static VoxelShape getShape(VoxelShape shape, BlockState blockState, BlockPos worldPos) {
        return MultiBlock.fixShape(shape, blockState, worldPos);
    }

    static VoxelShape getShape(Map<Direction, VoxelShape> shapes, BlockState blockState, BlockPos worldPos) {
        var facing = blockState.getValueOrElse(HorizontalDirectionalBlock.FACING, Direction.NORTH);
        return getShape(shapes.get(facing), blockState, worldPos);
    }

    record SignPair<TSign extends SignBlock, TWall extends SignBlock>(
            DeferredBlock<TSign> sign,
            DeferredBlock<TWall> wall
    ) {
        public boolean is(TypedInstance<Block> instance) {
            return instance.is(sign) || instance.is(wall);
        }
    }

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

        static void block(BlockRegistrar blocks, String name, Consumer<? super Block> action) {
            var block = blocks.getValue(name);

            if(block != null)
                action.accept(block);
        }

        static void creativeModeTab(CreativeModeTabRegistrar creativeModeTabs, Consumer<ResourceKey<CreativeModeTab>> action) {
            creativeModeTabs.getOptional(CREATIVE_MODE_TAB).ifPresent(holder -> action.accept(holder.getKey()));
        }

        static Block[] blocks(BlockRegistrar blocks, String... names) {
            var result = Lists.<Block>newArrayList();

            for(var name : names) {
                block(blocks, name, result::add);
            }

            return result.toArray(Block[]::new);
        }
    }
}
