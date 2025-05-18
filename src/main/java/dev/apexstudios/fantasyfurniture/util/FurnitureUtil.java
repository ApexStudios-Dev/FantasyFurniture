package dev.apexstudios.fantasyfurniture.util;

import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import dev.apexstudios.apexcore.lib.multiblock.ClientMultiBlockExtensions;
import dev.apexstudios.apexcore.lib.multiblock.MultiBlock;
import dev.apexstudios.apexcore.lib.placement.PlacementRenderEvent;
import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.apexcore.lib.registree.holder.DeferredBlock;
import dev.apexstudios.apexcore.lib.util.ApexUtil;
import dev.apexstudios.fantasyfurniture.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.FurnitureBlockEntities;
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
import java.util.Collections;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import net.minecraft.client.renderer.MaterialMapper;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.ItemLike;
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
import net.minecraft.world.level.block.entity.SmokerBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.items.wrapper.InvWrapper;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;
import net.neoforged.neoforge.mixins.BlockEntityTypeAccessor;

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

    static <TBlock extends DeskBlock> DeferredBlock<TBlock> desk(Registree registree, boolean left, Function<BlockBehaviour.Properties, TBlock> factory) {
        var block = registree.registerBlock(left ? Names.DESK_LEFT : Names.DESK_RIGHT, factory, DESK_PROPERTIES);
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
            appendValidBlocks(FurnitureBlockEntities.INVENTORY.value(), Names.blocks(
                    registree, Names.DRESSER, Names.LOCKBOX, Names.DRAWER, Names.DESK_LEFT, Names.DESK_RIGHT,
                    Names.CHEST, Names.COUNTER, Names.WARDROBE
            ));

            appendValidBlocks(FurnitureBlockEntities.BOOKSHELF.value(), Names.blocks(registree, Names.BOOKSHELF));
            appendValidBlocks(BlockEntityType.SMOKER, Names.blocks(registree, Names.OVEN));
            appendValidBlocks(BlockEntityType.HANGING_SIGN, Names.blocks(registree, Names.HANGING_SIGN, Names.WALL_HANGING_SIGN));
            appendValidBlocks(BlockEntityType.SIGN, Names.blocks(registree, Names.SIGN, Names.WALL_SIGN));
        });

        modBus.addListener(FMLCommonSetupEvent.class, event -> event.enqueueWork(() -> {
            registerPoi(registree, PoiTypes.HOME, Names.BED_SINGLE, blockState -> blockState.getValue(BedBlock.PART) == BedPart.HEAD);
            registerPoi(registree, PoiTypes.HOME, Names.BED_DOUBLE, blockState -> blockState.getValue(BedBlock.PART) == BedPart.HEAD);
            registerPoi(registree, PoiTypes.BUTCHER, Names.OVEN, Predicates.alwaysTrue());
        }));

        modBus.addListener(RegisterClientExtensionsEvent.class, event -> event.registerBlock(
                ClientMultiBlockExtensions.INSTANCE, Names.blocks(registree, Names.BED_DOUBLE, Names.DRESSER))
        );

        modBus.addListener(RegisterCapabilitiesEvent.class, event -> Names.block(
                registree,
                Names.OVEN,
                block -> {
                    event.registerBlockEntity(
                            Capabilities.ItemHandler.BLOCK,
                            BlockEntityType.SMOKER,
                            (blockEntity, side) -> side == null ? new InvWrapper(blockEntity) : new SidedInvWrapper(blockEntity, side)
                    );

                    if(!(block instanceof MultiBlock))
                        return;

                    event.registerBlock(Capabilities.ItemHandler.BLOCK, (level, pos, blockState, blockEntity, side) -> {
                        if(blockEntity == null)
                            blockEntity = MultiBlock.getBlockEntity(level, pos, blockState);
                        if(!(blockEntity instanceof SmokerBlockEntity smoker))
                            return null;

                        return side == null ? new InvWrapper(smoker) : new SidedInvWrapper(smoker, side);
                    }, block);
                })
        );

        // Even though vanilla seems to be registering these for us most of the time
        // there are times in which the game is crashing due to the material not being registered
        // this is here to ensure that our materials are being registered
        modBus.addListener(FMLClientSetupEvent.class, event -> event.enqueueWork(() -> {
            Names.block(registree, Names.HANGING_SIGN, block -> registerMaterial(woodType, Sheets.HANGING_SIGN_MATERIALS, Sheets.HANGING_SIGN_MAPPER, true));
            Names.block(registree, Names.SIGN, block -> registerMaterial(woodType, Sheets.SIGN_MATERIALS, Sheets.SIGN_MAPPER, false));
        }));

        NeoForge.EVENT_BUS.addListener(PlacementRenderEvent.DefaultBlockState.class, event -> {
            var blockState = event.defaultBlockState();

            Names.block(registree, Names.SOFA, block -> {
                if(blockState.is(block))
                    event.setDefaultBlockState(SofaConnection.setConnection(event.level(), event.pos(), blockState));
            });
        });

        registree.registerEvents(modBus);
    }

    static VoxelShape getShape(VoxelShape shape, BlockState blockState, BlockPos worldPos) {
        return MultiBlock.fixShape(shape, blockState, worldPos);
    }

    static VoxelShape getShape(Map<Direction, VoxelShape> shapes, BlockState blockState, BlockPos worldPos) {
        var facing = blockState.getValueOrElse(HorizontalDirectionalBlock.FACING, Direction.NORTH);
        return getShape(shapes.get(facing), blockState, worldPos);
    }

    private static void registerMaterial(WoodType woodType, Map<WoodType, Material> materials, MaterialMapper materialMapper, boolean hanging) {
        // sometimes vanilla does register these for us
        // which is causing the 'ISE' to be thrown
        /*if(materials.putIfAbsent(woodType, materialMapper.apply(ResourceLocation.parse(woodType.name()))) != null)
            throw new IllegalStateException("Duplicate wood type material registration: " + woodType.name() + " (" + (hanging ? "hanging" : "standing") + ')');*/
        materials.put(woodType, materialMapper.apply(ResourceLocation.parse(woodType.name())));
    }

    private static void registerPoi(Registree registree, ResourceKey<PoiType> poiType, String name, Predicate<BlockState> blockStateTest) {
        Names.block(registree, name, block -> ApexUtil.registerPoiBlockStates(poiType, block, blockStateTest));
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
