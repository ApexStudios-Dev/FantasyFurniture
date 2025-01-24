package dev.apexstudios.fantasyfurniture.set;

import com.google.common.collect.Sets;
import dev.apexstudios.apexcore.lib.registree.holder.DeferredBlockEntity;
import dev.apexstudios.apexcore.lib.registree.holder.DeferredMenu;
import dev.apexstudios.fantasyfurniture.FurnitureBlockEntities;
import dev.apexstudios.fantasyfurniture.FurnitureMenus;
import dev.apexstudios.fantasyfurniture.block.BedDoubleBlock;
import dev.apexstudios.fantasyfurniture.block.BedSingleBlock;
import dev.apexstudios.fantasyfurniture.block.BookshelfBlock;
import dev.apexstudios.fantasyfurniture.block.ChairBlock;
import dev.apexstudios.fantasyfurniture.block.DeskBlock;
import dev.apexstudios.fantasyfurniture.block.DoorBlock;
import dev.apexstudios.fantasyfurniture.block.DrawerBlock;
import dev.apexstudios.fantasyfurniture.block.DresserBlock;
import dev.apexstudios.fantasyfurniture.block.FurnitureBlock;
import dev.apexstudios.fantasyfurniture.block.FurnitureCarpetBlock;
import dev.apexstudios.fantasyfurniture.block.LockBoxBlock;
import dev.apexstudios.fantasyfurniture.block.OvenBlock;
import dev.apexstudios.fantasyfurniture.block.PaintingSmallBlock;
import dev.apexstudios.fantasyfurniture.block.PaintingWideBlock;
import dev.apexstudios.fantasyfurniture.block.SingleSeatBlock;
import java.util.Collections;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
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
            null,
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
            null,
            null
    );
    // endregion

    // region: Dresser
    BlockType<DresserBlock, BlockItem> DRESSER = new Impl<>(
            "dresser",
            () -> BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
                    .noOcclusion(),
            DresserBlock::new,
            BlockItem::new,
            FurnitureBlockEntities.DRESSER,
            FurnitureMenus.DRESSER
    );
    // endregion

    // region: Stool
    BlockType<SingleSeatBlock, BlockItem> STOOL = new Impl<>(
            "stool",
            () -> DRESSER.blockProperties().get(),
            SingleSeatBlock::new,
            BlockItem::new,
            null,
            null
    );
    // endregion

    // region: Cushion
    BlockType<SingleSeatBlock, BlockItem> CUSHION = new Impl<>(
            "cushion",
            () -> DRESSER.blockProperties().get(),
            SingleSeatBlock::new,
            BlockItem::new,
            null,
            null
    );
    // endregion

    // region: LockBox
    BlockType<LockBoxBlock, BlockItem> LOCKBOX = new Impl<>(
            "lockbox",
            () -> DRESSER.blockProperties().get(),
            LockBoxBlock::new,
            BlockItem::new,
            FurnitureBlockEntities.LOCKBOX,
            FurnitureMenus.LOCKBOX
    );
    // endregion

    // region: Drawer
    BlockType<DrawerBlock, BlockItem> DRAWER = new Impl<>(
            "drawer",
            () -> DRESSER.blockProperties().get(),
            DrawerBlock::new,
            BlockItem::new,
            FurnitureBlockEntities.DRAWER,
            FurnitureMenus.DRAWER
    );
    // endregion

    // region: Chair
    BlockType<ChairBlock, BlockItem> CHAIR = new Impl<>(
            "chair",
            () -> DRESSER.blockProperties().get(),
            ChairBlock::new,
            BlockItem::new,
            null,
            null
    );
    // endregion

    // region: Bookshelf
    BlockType<BookshelfBlock, BlockItem> BOOKSHELF = new Impl<>(
            "bookshelf",
            () -> DRESSER.blockProperties().get(),
            BookshelfBlock::new,
            BlockItem::new,
            FurnitureBlockEntities.BOOKSHELF,
            FurnitureMenus.BOOKSHELF
    );
    // endregion

    // region: Bed Single
    BlockType<BedSingleBlock, BlockItem> BED_SINGLE = new Impl<>(
            "bed_single",
            () -> DRESSER.blockProperties().get(),
            BedSingleBlock::new,
            BlockItem::new,
            null,
            null
    );
    // endregion

    // region: Bed Double
    BlockType<BedDoubleBlock, BlockItem> BED_DOUBLE = new Impl<>(
            "bed_double",
            () -> DRESSER.blockProperties().get(),
            BedDoubleBlock::new,
            BlockItem::new,
            null,
            null
    );
    // endregion

    // region: Door Single
    BlockType<DoorBlock, BlockItem> DOOR_SINGLE = new Impl<>(
            "door_single",
            () -> DRESSER.blockProperties().get(),
            DoorBlock::new,
            BlockItem::new,
            null,
            null
    );
    // endregion

    // region: Door Double
    BlockType<DoorBlock, BlockItem> DOOR_DOUBLE = new Impl<>(
            "door_double",
            () -> DRESSER.blockProperties().get(),
            DoorBlock::new,
            BlockItem::new,
            null,
            null
    );
    // endregion

    // region: Desk Left
    BlockType<DeskBlock, BlockItem> DESK_LEFT = new Impl<>(
            "desk_left",
            () -> DRESSER.blockProperties().get(),
            DeskBlock::new,
            BlockItem::new,
            FurnitureBlockEntities.DESK,
            FurnitureMenus.DESK
    );
    // endregion

    // region: Desk Right
    BlockType<DeskBlock, BlockItem> DESK_RIGHT = new Impl<>(
            "desk_right",
            () -> DRESSER.blockProperties().get(),
            DeskBlock::new,
            BlockItem::new,
            FurnitureBlockEntities.DESK,
            FurnitureMenus.DESK
    );
    // endregion

    // region: Painting Wide
    BlockType<PaintingWideBlock, BlockItem> PAINTING_WIDE = new Impl<>(
            "painting_wide",
            () -> DRESSER.blockProperties().get(),
            PaintingWideBlock::new,
            BlockItem::new,
            null,
            null
    );
    // endregion

    // region: Painting Small
    BlockType<PaintingSmallBlock, BlockItem> PAINTING_SMALL = new Impl<>(
            "painting_small",
            () -> DRESSER.blockProperties().get(),
            PaintingSmallBlock::new,
            BlockItem::new,
            null,
            null
    );
    // endregion

    // region: Oven
    BlockType<OvenBlock, BlockItem> OVEN = new Impl<>(
            "oven",
            () -> DRESSER.blockProperties().get(),
            OvenBlock::new,
            BlockItem::new,
            FurnitureBlockEntities.OVEN,
            FurnitureMenus.OVEN
    );
    // endregion

    // region: TODO
    BlockType<FurnitureBlock, BlockItem> CHANDELIER = new Impl<>("chandelier", BlockBehaviour.Properties::of, FurnitureBlock::new, BlockItem::new, null, null);
    BlockType<FurnitureBlock, BlockItem> CHEST = new Impl<>("chest", BlockBehaviour.Properties::of, FurnitureBlock::new, BlockItem::new, null, null);
    BlockType<FurnitureBlock, BlockItem> COUNTER = new Impl<>("counter", BlockBehaviour.Properties::of, FurnitureBlock::new, BlockItem::new, null, null);
    BlockType<FurnitureBlock, BlockItem> FLOOR_LIGHT = new Impl<>("floor_light", BlockBehaviour.Properties::of, FurnitureBlock::new, BlockItem::new, null, null);
    BlockType<FurnitureBlock, BlockItem> SHELF = new Impl<>("shelf", BlockBehaviour.Properties::of, FurnitureBlock::new, BlockItem::new, null, null);
    BlockType<FurnitureBlock, BlockItem> SOFA = new Impl<>("sofa", BlockBehaviour.Properties::of, FurnitureBlock::new, BlockItem::new, null, null);
    BlockType<FurnitureBlock, BlockItem> TABLE_LARGE = new Impl<>("table_large", BlockBehaviour.Properties::of, FurnitureBlock::new, BlockItem::new, null, null);
    BlockType<FurnitureBlock, BlockItem> TABLE_WIDE = new Impl<>("table_wide", BlockBehaviour.Properties::of, FurnitureBlock::new, BlockItem::new, null, null);
    BlockType<FurnitureBlock, BlockItem> TABLE_SMALL = new Impl<>("table_small", BlockBehaviour.Properties::of, FurnitureBlock::new, BlockItem::new, null, null);
    // endregion

    // dumb way to define registration order
    // LinkedSet to retain insertion order
    // UnmodifiableSet to disallow modifications
    Set<BlockType<?, ?>> VALUES = Collections.unmodifiableSet(Util.make(Sets.newLinkedHashSet(), set -> {
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

    @Nullable DeferredMenu<?> menuType();

    record Impl<TBlock extends Block, TItem extends Item>(
            String name,
            Supplier<BlockBehaviour.Properties> blockProperties,
            Supplier<Item.Properties> itemProperties,
            Function<BlockBehaviour.Properties, TBlock> blockFactory,
            BiFunction<TBlock, Item.Properties, TItem> blockItemFactory,
            @Nullable DeferredBlockEntity<?> blockEntityType,
            @Nullable DeferredMenu<?> menuType
    ) implements BlockType<TBlock, TItem> {
        private Impl(String name, Supplier<BlockBehaviour.Properties> blockProperties, Function<BlockBehaviour.Properties, TBlock> blockFactory, BiFunction<TBlock, Item.Properties, TItem> blockItemFactory, @Nullable DeferredBlockEntity<?> blockEntityType, @Nullable DeferredMenu<?> menuType) {
            this(name, blockProperties, Item.Properties::new, blockFactory, blockItemFactory, blockEntityType, menuType);
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
