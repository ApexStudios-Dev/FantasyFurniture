package dev.apexstudios.fantasyfurniture.set;

import com.google.common.collect.Sets;
import dev.apexstudios.apexcore.lib.registree.holder.DeferredBlockEntity;
import dev.apexstudios.fantasyfurniture.FurnitureBlockEntities;
import dev.apexstudios.fantasyfurniture.block.BedDoubleBlock;
import dev.apexstudios.fantasyfurniture.block.BedSingleBlock;
import dev.apexstudios.fantasyfurniture.block.BookshelfBlock;
import dev.apexstudios.fantasyfurniture.block.ChairBlock;
import dev.apexstudios.fantasyfurniture.block.ChestBlock;
import dev.apexstudios.fantasyfurniture.block.DeskBlock;
import dev.apexstudios.fantasyfurniture.block.DrawerBlock;
import dev.apexstudios.fantasyfurniture.block.DresserBlock;
import dev.apexstudios.fantasyfurniture.block.LockBoxBlock;
import dev.apexstudios.fantasyfurniture.block.OvenBlock;
import dev.apexstudios.fantasyfurniture.block.PaintingSmallBlock;
import dev.apexstudios.fantasyfurniture.block.PaintingWideBlock;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureBlock;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureCarpetBlock;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureDoorBlockComponentHolder;
import dev.apexstudios.fantasyfurniture.block.base.SeatBlock;
import java.util.Collections;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import net.minecraft.Util;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.Nullable;

public sealed interface BlockType<TBlock extends Block, TItem extends Item> permits BlockType.Impl {
    // region: Planks
    BlockType<FurnitureBlock, BlockItem> PLANKS = new Impl<>(
            "planks",
            () -> BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2F, 3F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            FurnitureBlock::new,
            BlockItem::new,
            null
    );
    // endregion

    // region: Wool
    BlockType<FurnitureBlock, BlockItem> WOOL = new Impl<>(
            "wool",
            () -> BlockBehaviour.Properties.of()
                    .mapColor(MapColor.SNOW)
                    .instrument(NoteBlockInstrument.GUITAR)
                    .strength(.8F)
                    .sound(SoundType.WOOL)
                    .ignitedByLava(),
            FurnitureBlock::new,
            BlockItem::new,
            null
    );
    // endregion

    // region: Carpet
    BlockType<FurnitureCarpetBlock, BlockItem> CARPET = new Impl<>(
            "carpet",
            () -> BlockBehaviour.Properties.of()
                    .mapColor(MapColor.SNOW)
                    .strength(.1F)
                    .sound(SoundType.WOOL)
                    .ignitedByLava(),
            FurnitureCarpetBlock::new,
            BlockItem::new,
            null
    );
    // endregion

    // region: Dresser
    BlockType<DresserBlock, BlockItem> DRESSER = new Impl<>(
            "dresser",
            properties(PLANKS),
            DresserBlock::new,
            BlockItem::new,
            FurnitureBlockEntities.DRESSER
    );
    // endregion

    // region: Stool
    BlockType<SeatBlock, BlockItem> STOOL = new Impl<>(
            "stool",
            properties(PLANKS),
            SeatBlock::new,
            BlockItem::new,
            null
    );
    // endregion

    // region: Cushion
    BlockType<SeatBlock, BlockItem> CUSHION = new Impl<>(
            "cushion",
            properties(PLANKS),
            SeatBlock::new,
            BlockItem::new,
            null
    );
    // endregion

    // region: LockBox
    BlockType<LockBoxBlock, BlockItem> LOCKBOX = new Impl<>(
            "lockbox",
            properties(PLANKS),
            LockBoxBlock::new,
            BlockItem::new,
            FurnitureBlockEntities.LOCKBOX
    );
    // endregion

    // region: Drawer
    BlockType<DrawerBlock, BlockItem> DRAWER = new Impl<>(
            "drawer",
            properties(PLANKS),
            DrawerBlock::new,
            BlockItem::new,
            FurnitureBlockEntities.DRAWER
    );
    // endregion

    // region: Chair
    BlockType<ChairBlock, BlockItem> CHAIR = new Impl<>(
            "chair",
            properties(PLANKS),
            ChairBlock::new,
            BlockItem::new,
            null
    );
    // endregion

    // region: Bookshelf
    BlockType<BookshelfBlock, BlockItem> BOOKSHELF = new Impl<>(
            "bookshelf",
            properties(PLANKS),
            BookshelfBlock::new,
            BlockItem::new,
            FurnitureBlockEntities.BOOKSHELF
    );
    // endregion

    // region: Bed Single
    BlockType<BedSingleBlock, BlockItem> BED_SINGLE = new Impl<>(
            "bed_single",
            properties(PLANKS),
            BedSingleBlock::new,
            BlockItem::new,
            null
    );
    // endregion

    // region: Bed Double
    BlockType<BedDoubleBlock, BlockItem> BED_DOUBLE = new Impl<>(
            "bed_double",
            properties(PLANKS),
            BedDoubleBlock::new,
            BlockItem::new,
            null
    );
    // endregion

    // region: Door Single
    BlockType<FurnitureDoorBlockComponentHolder, BlockItem> DOOR_SINGLE = new Impl<>(
            "door_single",
            properties(PLANKS, BlockBehaviour.Properties::noOcclusion),
            FurnitureDoorBlockComponentHolder::new,
            BlockItem::new,
            null
    );
    // endregion

    // region: Door Double
    BlockType<FurnitureDoorBlockComponentHolder, BlockItem> DOOR_DOUBLE = new Impl<>(
            "door_double",
            properties(PLANKS, BlockBehaviour.Properties::noOcclusion),
            FurnitureDoorBlockComponentHolder::new,
            BlockItem::new,
            null
    );
    // endregion

    // region: Desk Left
    BlockType<DeskBlock, BlockItem> DESK_LEFT = new Impl<>(
            "desk_left",
            properties(PLANKS),
            DeskBlock::new,
            BlockItem::new,
            FurnitureBlockEntities.DESK
    );
    // endregion

    // region: Desk Right
    BlockType<DeskBlock, BlockItem> DESK_RIGHT = new Impl<>(
            "desk_right",
            properties(PLANKS),
            DeskBlock::new,
            BlockItem::new,
            FurnitureBlockEntities.DESK
    );
    // endregion

    // region: Painting Wide
    BlockType<PaintingWideBlock, BlockItem> PAINTING_WIDE = new Impl<>(
            "painting_wide",
            properties(PLANKS),
            PaintingWideBlock::new,
            BlockItem::new,
            null
    );
    // endregion

    // region: Painting Small
    BlockType<PaintingSmallBlock, BlockItem> PAINTING_SMALL = new Impl<>(
            "painting_small",
            properties(PLANKS),
            PaintingSmallBlock::new,
            BlockItem::new,
            null
    );
    // endregion

    // region: Oven
    BlockType<OvenBlock, BlockItem> OVEN = new Impl<>(
            "oven",
            properties(PLANKS, BlockBehaviour.Properties::noOcclusion),
            OvenBlock::new,
            BlockItem::new,
            FurnitureBlockEntities.OVEN
    );
    // endregion

    // region: Oven
    BlockType<ChestBlock, BlockItem> CHEST = new Impl<>(
            "chest",
            properties(PLANKS),
            ChestBlock::new,
            BlockItem::new,
            FurnitureBlockEntities.CHEST
    );
    // endregion

    // region: TODO
    BlockType<FurnitureBlock, BlockItem> CHANDELIER = new Impl<>("chandelier", BlockBehaviour.Properties::of, FurnitureBlock::new, BlockItem::new, null);
    BlockType<FurnitureBlock, BlockItem> COUNTER = new Impl<>("counter", BlockBehaviour.Properties::of, FurnitureBlock::new, BlockItem::new, null);
    BlockType<FurnitureBlock, BlockItem> FLOOR_LIGHT = new Impl<>("floor_light", BlockBehaviour.Properties::of, FurnitureBlock::new, BlockItem::new, null);
    BlockType<FurnitureBlock, BlockItem> SHELF = new Impl<>("shelf", BlockBehaviour.Properties::of, FurnitureBlock::new, BlockItem::new, null);
    BlockType<FurnitureBlock, BlockItem> SOFA = new Impl<>("sofa", BlockBehaviour.Properties::of, FurnitureBlock::new, BlockItem::new, null);
    BlockType<FurnitureBlock, BlockItem> TABLE_LARGE = new Impl<>("table_large", BlockBehaviour.Properties::of, FurnitureBlock::new, BlockItem::new, null);
    BlockType<FurnitureBlock, BlockItem> TABLE_WIDE = new Impl<>("table_wide", BlockBehaviour.Properties::of, FurnitureBlock::new, BlockItem::new, null);
    BlockType<FurnitureBlock, BlockItem> TABLE_SMALL = new Impl<>("table_small", BlockBehaviour.Properties::of, FurnitureBlock::new, BlockItem::new, null);
    // endregion

    // dumb way to define registration order
    // LinkedSet to retain insertion order
    // UnmodifiableSet to disallow modifications
    Set<BlockType<?, ?>> VALUES = Collections.unmodifiableSet(Util.make(Sets.newLinkedHashSet(), set -> {
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
    }));

    String name();

    Supplier<BlockBehaviour.Properties> blockProperties();

    Supplier<Item.Properties> itemProperties();

    TBlock newBlock(BlockBehaviour.Properties properties);

    TItem newBlockItem(TBlock block, Item.Properties properties);

    @Nullable DeferredBlockEntity<?> blockEntityType();

    private static Supplier<BlockBehaviour.Properties> properties(BlockType<?, ?> blockType, UnaryOperator<BlockBehaviour.Properties> modifier) {
        return () -> modifier.apply(blockType.blockProperties().get());
    }

    private static Supplier<BlockBehaviour.Properties> properties(BlockType<?, ?> blockType) {
        return properties(blockType, UnaryOperator.identity());
    }

    record Impl<TBlock extends Block, TItem extends Item>(
            String name,
            Supplier<BlockBehaviour.Properties> blockProperties,
            Supplier<Item.Properties> itemProperties,
            Function<BlockBehaviour.Properties, TBlock> blockFactory,
            BiFunction<TBlock, Item.Properties, TItem> blockItemFactory,
            @Nullable DeferredBlockEntity<?> blockEntityType
    ) implements BlockType<TBlock, TItem> {
        private Impl(String name, Supplier<BlockBehaviour.Properties> blockProperties, Function<BlockBehaviour.Properties, TBlock> blockFactory, BiFunction<TBlock, Item.Properties, TItem> blockItemFactory, @Nullable DeferredBlockEntity<?> blockEntityType) {
            this(name, blockProperties, Item.Properties::new, blockFactory, blockItemFactory, blockEntityType);
        }

        @Override
        public TBlock newBlock(BlockBehaviour.Properties properties) {
            return blockFactory.apply(properties);
        }

        @Override
        public TItem newBlockItem(TBlock block, Item.Properties properties) {
            return blockItemFactory.apply(block, properties);
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
}
