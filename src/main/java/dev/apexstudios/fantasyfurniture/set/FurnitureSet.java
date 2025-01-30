package dev.apexstudios.fantasyfurniture.set;

import com.google.common.collect.Maps;
import dev.apexstudios.apexcore.lib.component.block.types.BedBlockComponent;
import dev.apexstudios.apexcore.lib.data.ProviderTypes;
import dev.apexstudios.apexcore.lib.data.ResourceGenerator;
import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.apexcore.lib.registree.holder.DeferredBlock;
import dev.apexstudios.apexcore.lib.registree.holder.DeferredItem;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureBlock;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import org.jetbrains.annotations.Nullable;

public final class FurnitureSet {
    private final Registree registree;
    private final Map<BlockType<?, ?>, Mapping<?, ?>> mappings;
    private final ResourceKey<CreativeModeTab> creativeModeTab;

    private FurnitureSet(String namespace, Builder builder) {
        registree = new Registree(namespace);
        mappings = builder.register(this);

        creativeModeTab = registree.registerCreativeModeTab("items", () -> item(BlockType.WOOL).toStack(), (parameters, output) -> registree
                .asLookup(Registries.ITEM)
                .filterFeatures(parameters.enabledFeatures())
                .listElements()
                .map(Holder::value)
                .forEach(output::accept)
        );
    }

    public Registree registree() {
        return registree;
    }

    public void register(IEventBus modBus) {
        registree.registerEvents(modBus);

        modBus.addListener(BlockEntityTypeAddBlocksEvent.class, event -> {
            mappings.forEach((blockType, mapping) -> {
                var blockEntityType = blockType.blockEntityType();

                if(blockEntityType != null)
                    event.modify(blockEntityType.value(), mapping.block.value());
            });
        });

        BedBlockComponent.registerPoi(modBus, block(BlockType.BED_SINGLE));
        BedBlockComponent.registerPoi(modBus, block(BlockType.BED_DOUBLE));
    }

    private <TBlock extends Block, TItem extends Item> Mapping<TBlock, TItem> mapping(BlockType<TBlock, TItem> blockType) {
        return (Mapping<TBlock, TItem>) mappings.get(blockType);
    }

    public <TBlock extends Block> DeferredBlock<TBlock> block(BlockType<TBlock, ?> blockType) {
        return mapping(blockType).block;
    }

    public <TItem extends Item> DeferredItem<TItem> item(BlockType<?, TItem> blockType) {
        return mapping(blockType).item;
    }

    public VoxelShape shape(BlockType<?, ?> blockType, BlockState blockState, Supplier<VoxelShape> defaultShape) {
        var shapeGetter = mapping(blockType).shapeGetter;
        return shapeGetter == null ? defaultShape.get() : shapeGetter.apply(blockState);
    }

    public ResourceKey<CreativeModeTab> creativeModeTab() {
        return creativeModeTab;
    }

    public void registerDataGen(String englishName, ResourceGenerator generator) {
        generator.pack()
                .providing(ProviderTypes.BLOCK_TAGS, (context, provider) -> FurnitureSetDataGen.blockTags(provider, this))
                .providing(ProviderTypes.ITEM_TAGS, (context, provider) -> FurnitureSetDataGen.itemTags(provider, this))
                .providing(ProviderTypes.LANGUAGE, (context, provider) -> FurnitureSetDataGen.language(provider, this, englishName))
                .providing(ProviderTypes.MODELS, (context, provider) -> FurnitureSetDataGen.models(provider, this))
                .providing(ProviderTypes.LOOT_TABLE, (context, provider) -> FurnitureSetDataGen.lootTables(provider, this));
    }

    public static FurnitureSet create(String namespace, Function<Builder, Builder> builder) {
        return new FurnitureSet(namespace, builder.apply(new Builder()));
    }

    public static final class Builder {
        private final Map<BlockType<?, ?>, Consumer<? extends BlockTypeBuilder<?, ?>>> blockTypes = Maps.newHashMapWithExpectedSize(BlockType.VALUES.size());

        private Builder() {

        }

        public <TBlock extends Block, TItem extends Item> Builder blockType(BlockType<TBlock, TItem> blockType, Consumer<BlockTypeBuilder<TBlock, TItem>> builder) {
            blockTypes.put(blockType, builder);
            return this;
        }

        private Map<BlockType<?, ?>, Mapping<?, ?>> register(FurnitureSet furnitureSet) {
            return BlockType.VALUES.stream().collect(Collectors.toUnmodifiableMap(Function.identity(), blockType -> {
                var builder = new BlockTypeBuilder<>(blockType);
                var consumer = blockTypes.get(blockType);

                if(consumer != null)
                    ((Consumer<BlockTypeBuilder<?, ?>>) consumer).accept(builder);

                return builder.register(furnitureSet);
            }));
        }
    }

    public static final class BlockTypeBuilder<TBlock extends Block, TItem extends Item> {
        private final BlockType<TBlock, TItem> blockType;
        @Nullable private Function<BlockState, VoxelShape> shapeGetter = null;
        private Function<BlockBehaviour.Properties, BlockBehaviour.Properties> blockProperties = Function.identity();
        private Function<Item.Properties, Item.Properties> itemProperties = Function.identity();

        private BlockTypeBuilder(BlockType<TBlock, TItem> blockType) {
            this.blockType = blockType;
        }

        public BlockTypeBuilder<TBlock, TItem> shape(Function<BlockState, VoxelShape> shapeGetter) {
            this.shapeGetter = shapeGetter;
            return this;
        }

        public BlockTypeBuilder<TBlock, TItem> shape(Supplier<VoxelShape> shapeGetter) {
            return shape(blockState -> shapeGetter.get());
        }

        public BlockTypeBuilder<TBlock, TItem> blockProperties(UnaryOperator<BlockBehaviour.Properties> blockProperties) {
            this.blockProperties = this.blockProperties.andThen(blockProperties);
            return this;
        }

        public BlockTypeBuilder<TBlock, TItem> itemProperties(UnaryOperator<Item.Properties> itemProperties) {
            this.itemProperties = this.itemProperties.andThen(itemProperties);
            return this;
        }

        private Mapping<TBlock, TItem> register(FurnitureSet furnitureSet) {
            var blockProperties = this.blockProperties.apply(blockType.blockProperties().get());
            var itemProperties = this.itemProperties.apply(blockType.itemProperties().get());

            ((FurnitureBlock.Injector) blockProperties).FantasyFurniture$setFurnitureSet(furnitureSet);
            ((FurnitureBlock.Injector) blockProperties).FantasyFurniture$setBlockType(blockType);

            var block = furnitureSet.registree.registerBlock(blockType.name(), blockType::newBlock, blockProperties);
            var item = furnitureSet.registree.registerBlockItem(blockType.name(), block, blockType::newBlockItem, itemProperties);

            return new Mapping<>(block, item, shapeGetter);
        }
    }

    private record Mapping<
            TBlock extends Block,
            TItem extends Item
    >(DeferredBlock<TBlock> block, DeferredItem<TItem> item, @Nullable Function<BlockState, VoxelShape> shapeGetter) { }
}
