package dev.apexstudios.fantasyfurniture.set;

import com.google.common.collect.Sets;
import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.apexcore.lib.registree.holder.DeferredBlock;
import dev.apexstudios.apexcore.lib.registree.holder.DeferredItem;
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
import dev.apexstudios.fantasyfurniture.block.LockBoxBlock;
import dev.apexstudios.fantasyfurniture.block.OvenBlock;
import dev.apexstudios.fantasyfurniture.block.PaintingSmallBlock;
import dev.apexstudios.fantasyfurniture.block.PaintingWideBlock;
import dev.apexstudios.fantasyfurniture.block.ShelfBlock;
import dev.apexstudios.fantasyfurniture.block.SofaBlock;
import dev.apexstudios.fantasyfurniture.block.TableLargeBlock;
import dev.apexstudios.fantasyfurniture.block.TableSmallBlock;
import dev.apexstudios.fantasyfurniture.block.TableWideBlock;
import dev.apexstudios.fantasyfurniture.block.WallLightBlock;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureBlock;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureCarpetBlock;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureDoorBlockComponentHolder;
import dev.apexstudios.fantasyfurniture.block.base.SeatBlock;
import java.util.Collections;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import net.minecraft.SharedConstants;
import net.minecraft.Util;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

public sealed interface BlockType<TBlock extends Block, TItem extends Item> permits BlockType.Impl {
    // region: Planks
    BlockType<FurnitureBlock, BlockItem> PLANKS = new Impl<>(
            "planks",
            false,
            () -> BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2F, 3F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            UnaryOperator.identity(),
            FurnitureBlock::new,
            simpleBlockItem(),
            null
    );
    // endregion

    // region: Wool
    BlockType<FurnitureBlock, BlockItem> WOOL = new Impl<>(
            "wool",
            false,
            () -> BlockBehaviour.Properties.of()
                    .mapColor(MapColor.SNOW)
                    .instrument(NoteBlockInstrument.GUITAR)
                    .strength(.8F)
                    .sound(SoundType.WOOL)
                    .ignitedByLava(),
            UnaryOperator.identity(),
            FurnitureBlock::new,
            simpleBlockItem(),
            null
    );
    // endregion

    // region: Carpet
    BlockType<FurnitureCarpetBlock, BlockItem> CARPET = new Impl<>(
            "carpet",
            false,
            () -> BlockBehaviour.Properties.of()
                    .mapColor(MapColor.SNOW)
                    .strength(.1F)
                    .sound(SoundType.WOOL)
                    .ignitedByLava(),
            UnaryOperator.identity(),
            FurnitureCarpetBlock::new,
            simpleBlockItem(),
            null
    );
    // endregion

    // region: Dresser
    BlockType<DresserBlock, BlockItem> DRESSER = new Impl<>(
            "dresser",
            true,
            properties(PLANKS),
            UnaryOperator.identity(),
            DresserBlock::new,
            simpleBlockItem(),
            FurnitureBlockEntities.DRESSER
    );
    // endregion

    // region: Stool
    BlockType<SeatBlock, BlockItem> STOOL = new Impl<>(
            "stool",
            true,
            properties(PLANKS),
            UnaryOperator.identity(),
            SeatBlock::new,
            simpleBlockItem(),
            null
    );
    // endregion

    // region: Cushion
    BlockType<CushionBlock, BlockItem> CUSHION = new Impl<>(
            "cushion",
            true,
            properties(PLANKS),
            UnaryOperator.identity(),
            CushionBlock::new,
            simpleBlockItem(),
            null
    );
    // endregion

    // region: LockBox
    BlockType<LockBoxBlock, BlockItem> LOCKBOX = new Impl<>(
            "lockbox",
            true,
            properties(PLANKS),
            UnaryOperator.identity(),
            LockBoxBlock::new,
            simpleBlockItem(),
            FurnitureBlockEntities.LOCKBOX
    );
    // endregion

    // region: Drawer
    BlockType<DrawerBlock, BlockItem> DRAWER = new Impl<>(
            "drawer",
            true,
            properties(PLANKS),
            UnaryOperator.identity(),
            DrawerBlock::new,
            simpleBlockItem(),
            FurnitureBlockEntities.DRAWER
    );
    // endregion

    // region: Chair
    BlockType<ChairBlock, BlockItem> CHAIR = new Impl<>(
            "chair",
            true,
            properties(PLANKS),
            UnaryOperator.identity(),
            ChairBlock::new,
            simpleBlockItem(),
            null
    );
    // endregion

    // region: Bookshelf
    BlockType<BookshelfBlock, BlockItem> BOOKSHELF = new Impl<>(
            "bookshelf",
            true,
            properties(PLANKS),
            UnaryOperator.identity(),
            BookshelfBlock::new,
            simpleBlockItem(),
            FurnitureBlockEntities.BOOKSHELF
    );
    // endregion

    // region: Bed Single
    BlockType<BedSingleBlock, BlockItem> BED_SINGLE = new Impl<>(
            "bed_single",
            true,
            properties(PLANKS),
            UnaryOperator.identity(),
            BedSingleBlock::new,
            simpleBlockItem(),
            null
    );
    // endregion

    // region: Bed Double
    BlockType<BedDoubleBlock, BlockItem> BED_DOUBLE = new Impl<>(
            "bed_double",
            true,
            properties(PLANKS),
            UnaryOperator.identity(),
            BedDoubleBlock::new,
            simpleBlockItem(),
            null
    );
    // endregion

    // region: Door Single
    BlockType<FurnitureDoorBlockComponentHolder, BlockItem> DOOR_SINGLE = new Impl<>(
            "door_single",
            true,
            properties(PLANKS, BlockBehaviour.Properties::noOcclusion),
            UnaryOperator.identity(),
            FurnitureDoorBlockComponentHolder::new,
            simpleBlockItem(),
            null
    );
    // endregion

    // region: Door Double
    BlockType<FurnitureDoorBlockComponentHolder, BlockItem> DOOR_DOUBLE = new Impl<>(
            "door_double",
            true,
            properties(PLANKS, BlockBehaviour.Properties::noOcclusion),
            UnaryOperator.identity(),
            FurnitureDoorBlockComponentHolder::new,
            simpleBlockItem(),
            null
    );
    // endregion

    // region: Desk Left
    BlockType<DeskBlock, BlockItem> DESK_LEFT = new Impl<>(
            "desk_left",
            true,
            properties(PLANKS),
            UnaryOperator.identity(),
            DeskBlock::new,
            simpleBlockItem(),
            FurnitureBlockEntities.DESK
    );
    // endregion

    // region: Desk Right
    BlockType<DeskBlock, BlockItem> DESK_RIGHT = new Impl<>(
            "desk_right",
            true,
            properties(PLANKS),
            UnaryOperator.identity(),
            DeskBlock::new,
            simpleBlockItem(),
            FurnitureBlockEntities.DESK
    );
    // endregion

    // region: Painting Wide
    BlockType<PaintingWideBlock, BlockItem> PAINTING_WIDE = new Impl<>(
            "painting_wide",
            true,
            properties(PLANKS),
            UnaryOperator.identity(),
            PaintingWideBlock::new,
            simpleBlockItem(),
            null
    );
    // endregion

    // region: Painting Small
    BlockType<PaintingSmallBlock, BlockItem> PAINTING_SMALL = new Impl<>(
            "painting_small",
            true,
            properties(PLANKS),
            UnaryOperator.identity(),
            PaintingSmallBlock::new,
            simpleBlockItem(),
            null
    );
    // endregion

    // region: Oven
    BlockType<OvenBlock, BlockItem> OVEN = new Impl<>(
            "oven",
            true,
            properties(PLANKS, BlockBehaviour.Properties::noOcclusion),
            UnaryOperator.identity(),
            OvenBlock::new,
            simpleBlockItem(),
            FurnitureBlockEntities.OVEN
    );
    // endregion

    // region: Chest
    BlockType<ChestBlock, BlockItem> CHEST = new Impl<>(
            "chest",
            true,
            properties(PLANKS),
            UnaryOperator.identity(),
            ChestBlock::new,
            simpleBlockItem(),
            FurnitureBlockEntities.CHEST
    );
    // endregion

    // region: Table Large
    BlockType<TableLargeBlock, BlockItem> TABLE_LARGE = new Impl<>(
            "table_large",
            true,
            properties(PLANKS),
            UnaryOperator.identity(),
            TableLargeBlock::new,
            simpleBlockItem(),
            null
    );
    // endregion

    // region: Table Wide
    BlockType<TableWideBlock, BlockItem> TABLE_WIDE = new Impl<>(
            "table_wide",
            true,
            properties(PLANKS),
            UnaryOperator.identity(),
            TableWideBlock::new,
            simpleBlockItem(),
            null
    );
    // endregion

    // region: Table Small
    BlockType<TableSmallBlock, BlockItem> TABLE_SMALL = new Impl<>(
            "table_small",
            true,
            properties(PLANKS),
            UnaryOperator.identity(),
            TableSmallBlock::new,
            simpleBlockItem(),
            null
    );
    // endregion

    // region: Floor Light
    BlockType<FloorLightBlock, BlockItem> FLOOR_LIGHT = new Impl<>(
            "floor_light",
            true,
            properties(PLANKS, properties -> properties.lightLevel(blockState -> 14)),
            UnaryOperator.identity(),
            FloorLightBlock::new,
            simpleBlockItem(),
            null
    );
    // endregion

    // region: Chandelier
    BlockType<ChandelierBlock, BlockItem> CHANDELIER = new Impl<>(
            "chandelier",
            true,
            properties(PLANKS, properties -> properties.lightLevel(blockState -> 14)),
            UnaryOperator.identity(),
            ChandelierBlock::new,
            simpleBlockItem(),
            null
    );
    // endregion

    // region: Shelf
    BlockType<ShelfBlock, BlockItem> SHELF = new Impl<>(
            "shelf",
            true,
            properties(PLANKS),
            UnaryOperator.identity(),
            ShelfBlock::new,
            simpleBlockItem(),
            null
    );
    // endregion

    // region: Sofa
    BlockType<SofaBlock, BlockItem> SOFA = new Impl<>(
            "sofa",
            true,
            properties(PLANKS),
            UnaryOperator.identity(),
            SofaBlock::new,
            simpleBlockItem(),
            null
    );
    // endregion

    // region: Counter
    BlockType<CounterBlock, BlockItem> COUNTER = new Impl<>(
            "counter",
            true,
            properties(PLANKS),
            UnaryOperator.identity(),
            CounterBlock::new,
            simpleBlockItem(),
            FurnitureBlockEntities.COUNTER
    );
    // endregion

    // region: Wall Light
    BlockType<WallLightBlock, BlockItem> WALL_LIGHT = new Impl<>(
            "wall_light",
            true,
            properties(PLANKS, properties -> properties.lightLevel(blockState -> 14).noCollission()),
            UnaryOperator.identity(),
            WallLightBlock::new,
            simpleBlockItem(),
            null
    );
    // endregion

    // region: Bench
    BlockType<BenchBlock, BlockItem> BENCH = new Impl<>(
            "bench",
            true,
            properties(PLANKS),
            UnaryOperator.identity(),
            BenchBlock::new,
            simpleBlockItem(),
            null
    );
    // endregion

    // region: Stairs
    BlockType<StairBlock, BlockItem> STAIRS = new Impl<>(
            "stairs",
            false,
            properties(PLANKS),
            UnaryOperator.identity(),
            (furnitureSet, blockType, properties) -> new StairBlock(furnitureSet.blockOrThrow(PLANKS).value().defaultBlockState(), properties),
            simpleBlockItem(),
            null
    );
    // endregion

    // region: Slab
    BlockType<SlabBlock, BlockItem> SLAB = new Impl<>(
            "slab",
            false,
            properties(PLANKS),
            UnaryOperator.identity(),
            (furnitureSet, blockType, properties) -> new SlabBlock(properties),
            simpleBlockItem(),
            null
    );
    // endregion

    // region: Fence
    BlockType<FenceBlock, BlockItem> FENCE = new Impl<>(
            "fence",
            false,
            properties(PLANKS),
            UnaryOperator.identity(),
            (furnitureSet, blockType, properties) -> new FenceBlock(properties),
            simpleBlockItem(),
            null
    );
    // endregion

    // region: Fence Gate
    BlockType<FenceGateBlock, BlockItem> FENCE_GATE = new Impl<>(
            "fence_gate",
            false,
            properties(PLANKS),
            UnaryOperator.identity(),
            (furnitureSet, blockType, properties) -> new FenceGateBlock(furnitureSet.woodType(), properties),
            simpleBlockItem(),
            null
    );
    // endregion

    // region: Trapdoor
    BlockType<TrapDoorBlock, BlockItem> TRAP_DOOR = new Impl<>(
            "trapdoor",
            false,
            properties(PLANKS),
            UnaryOperator.identity(),
            (furnitureSet, blockType, properties) -> new TrapDoorBlock(furnitureSet.blockSetType(), properties),
            simpleBlockItem(),
            null
    );
    // endregion

    // region: Pressure Plate
    BlockType<PressurePlateBlock, BlockItem> PRESSURE_PLATE = new Impl<>(
            "pressure_plate",
            false,
            properties(PLANKS),
            UnaryOperator.identity(),
            (furnitureSet, blockType, properties) -> new PressurePlateBlock(furnitureSet.blockSetType(), properties),
            simpleBlockItem(),
            null
    );
    // endregion

    // region: Button
    BlockType<ButtonBlock, BlockItem> BUTTON = new Impl<>(
            "button",
            false,
            properties(PLANKS),
            UnaryOperator.identity(),
            // SharedConstants.TICKS_PER_SECOND + 10 -> same as OAK -> 30 ticks
            (furnitureSet, blockType, properties) -> new ButtonBlock(furnitureSet.blockSetType(), SharedConstants.TICKS_PER_SECOND + 10, properties),
            simpleBlockItem(),
            null
    );
    // endregion

    // region: Wall Hanging Sign
    BlockType<WallHangingSignBlock, BlockItem> WALL_HANGING_SIGN = new Impl<>(
            "wall_hanging_sign",
            false,
            properties(PLANKS),
            UnaryOperator.identity(),
            (furnitureSet, blockType, properties) -> new WallHangingSignBlock(furnitureSet.woodType(), properties),
            null,
            () -> BlockEntityType.HANGING_SIGN
    );
    // endregion

    // region: Hanging Sign
    BlockType<CeilingHangingSignBlock, BlockItem> HANGING_SIGN = new Impl<>(
            "hanging_sign",
            false,
            properties(PLANKS),
            properties -> properties.stacksTo(16),
            (furnitureSet, blockType, properties) -> new CeilingHangingSignBlock(furnitureSet.woodType(), properties),
            (furnitureSet, blockType, block, properties) -> new HangingSignItem(block, furnitureSet.blockOrThrow(WALL_HANGING_SIGN).value(), properties),
            () -> BlockEntityType.HANGING_SIGN
    );
    // endregion

    // region: Wall Sign
    BlockType<WallSignBlock, BlockItem> WALL_SIGN = new Impl<>(
            "wall_sign",
            false,
            properties(PLANKS),
            UnaryOperator.identity(),
            (furnitureSet, blockType, properties) -> new WallSignBlock(furnitureSet.woodType(), properties),
            null,
            () -> BlockEntityType.SIGN
    );
    // endregion

    // region: Sign
    BlockType<StandingSignBlock, BlockItem> SIGN = new Impl<>(
            "sign",
            false,
            properties(PLANKS),
            properties -> properties.stacksTo(16),
            (furnitureSet, blockType, properties) -> new StandingSignBlock(furnitureSet.woodType(), properties),
            (furnitureSet, blockType, block, properties) -> new SignItem(block, furnitureSet.blockOrThrow(WALL_SIGN).value(), properties),
            () -> BlockEntityType.SIGN
    );
    // endregion

    // dumb way to define registration order
    // LinkedSet to retain insertion order
    // UnmodifiableSet to disallow modifications
    Set<BlockType<?, ?>> VALUES = Collections.unmodifiableSet(Util.make(Sets.newLinkedHashSet(), set -> {
        // region: Modded
        set.add(PLANKS);
        set.add(WOOL);
        set.add(CARPET);
        set.add(DRESSER);
        set.add(STOOL);
        set.add(CUSHION);
        set.add(BED_DOUBLE);
        set.add(BED_SINGLE);
        set.add(BOOKSHELF);
        set.add(CHAIR);
        set.add(CHANDELIER);
        set.add(CHEST);
        set.add(COUNTER);
        set.add(DESK_LEFT);
        set.add(DESK_RIGHT);
        set.add(DOOR_DOUBLE);
        set.add(DOOR_SINGLE);
        set.add(DRAWER);
        set.add(FLOOR_LIGHT);
        set.add(LOCKBOX);
        set.add(OVEN);
        set.add(PAINTING_SMALL);
        set.add(PAINTING_WIDE);
        set.add(SHELF);
        set.add(SOFA);
        set.add(TABLE_LARGE);
        set.add(TABLE_WIDE);
        set.add(TABLE_SMALL);
        set.add(WALL_LIGHT);
        set.add(BENCH);
        // endregion

        // region: Vanilla
        set.add(STAIRS);
        set.add(SLAB);
        set.add(FENCE);
        set.add(FENCE_GATE);
        set.add(TRAP_DOOR);
        set.add(PRESSURE_PLATE);
        set.add(BUTTON);
        set.add(WALL_HANGING_SIGN);
        set.add(HANGING_SIGN);
        set.add(WALL_SIGN);
        set.add(SIGN);
        // endregion
    }));

    String name();

    boolean forFurnitureStation();

    @ApiStatus.Internal
    @Nullable
    DeferredBlock<TBlock> registerBlock(Registree registree, FurnitureSet furnitureSet, Function<BlockBehaviour.Properties, BlockBehaviour.Properties> propertiesModifier);

    @ApiStatus.Internal
    @Nullable
    DeferredItem<TItem> registerItem(Registree registree, FurnitureSet furnitureSet, DeferredBlock<TBlock> blockHolder, Function<Item.Properties, Item.Properties> propertiesModifier);

    @Nullable Supplier<? extends BlockEntityType<?>> blockEntityType();

    static <TBlock extends Block> BlockItemFactory<TBlock, BlockItem> simpleBlockItem() {
        return (furnitureSet, blockType, block, properties) -> new BlockItem(block, properties);
    }

    private static Supplier<BlockBehaviour.Properties> properties(BlockType<?, ?> blockType, UnaryOperator<BlockBehaviour.Properties> modifier) {
        return () -> modifier.apply(((Impl<?, ?>) blockType).blockProperties.get());
    }

    private static Supplier<BlockBehaviour.Properties> properties(BlockType<?, ?> blockType) {
        return properties(blockType, UnaryOperator.identity());
    }

    record Impl<TBlock extends Block, TItem extends Item>(
            String name,
            boolean forFurnitureStation,
            Supplier<BlockBehaviour.Properties> blockProperties,
            UnaryOperator<Item.Properties> itemProperties,
            @Nullable BlockFactory<TBlock> blockFactory,
            @Nullable BlockItemFactory<TBlock, TItem> blockItemFactory,
            @Nullable Supplier<? extends BlockEntityType<?>> blockEntityType
    ) implements BlockType<TBlock, TItem> {
        @Override
        @Nullable
        public DeferredBlock<TBlock> registerBlock(Registree registree, FurnitureSet furnitureSet, Function<BlockBehaviour.Properties, BlockBehaviour.Properties> propertiesModifier) {
            if(blockFactory == null)
                return null;

            var blockProperties = propertiesModifier.apply(this.blockProperties.get());
            return registree.registerBlock(name, properties -> blockFactory.create(furnitureSet, this, properties), blockProperties);
        }

        @Override
        @Nullable
        public DeferredItem<TItem> registerItem(Registree registree, FurnitureSet furnitureSet, DeferredBlock<TBlock> blockHolder, Function<Item.Properties, Item.Properties> propertiesModifier) {
            if(blockItemFactory == null)
                return null;

            var itemProperties = this.itemProperties.andThen(propertiesModifier).apply(new Item.Properties());
            return registree.registerBlockItem(name, blockHolder, (block, properties) -> blockItemFactory.create(furnitureSet, this, block, properties), itemProperties);
        }

        @Override
        public boolean equals(Object obj) {
            return this == obj || (obj instanceof BlockType<?, ?> other && name.equals(other.name()));
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }

        @Override
        public String toString() {
            return "BlockType(" + name + ')';
        }
    }

    @FunctionalInterface
    interface BlockFactory<TBlock extends Block> {
        TBlock create(FurnitureSet furnitureSet, BlockType<?, ?> blockType, BlockBehaviour.Properties properties);
    }

    @FunctionalInterface
    interface BlockItemFactory<TBlock extends Block, TItem extends Item> {
        TItem create(FurnitureSet furnitureSet, BlockType<?, ?> blockType, TBlock block, Item.Properties properties);
    }
}
