package dev.apexstudios.fantasyfurniture.set;

import com.google.common.collect.Maps;
import dev.apexstudios.apexcore.lib.component.ComponentHolder;
import dev.apexstudios.apexcore.lib.component.block.BlockComponent;
import dev.apexstudios.apexcore.lib.component.block.types.BedBlockComponent;
import dev.apexstudios.apexcore.lib.data.ProviderTypes;
import dev.apexstudios.apexcore.lib.data.ResourceGenerator;
import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.apexcore.lib.registree.holder.DeferredBlock;
import dev.apexstudios.apexcore.lib.registree.holder.DeferredItem;
import dev.apexstudios.apexcore.lib.util.WoodTypeBuilder;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamily;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import org.jetbrains.annotations.Nullable;

public final class FurnitureSet {
    private final Registree registree;
    private final Map<BlockType<?, ?>, Mapping<?, ?>> mappings;
    private final ResourceKey<CreativeModeTab> creativeModeTab;
    private final WoodType woodType;

    private FurnitureSet(String namespace, Builder builder) {
        registree = new Registree(namespace);
        woodType = builder.woodTypeBuilder.build(namespace + ResourceLocation.NAMESPACE_SEPARATOR + "wood_type");
        mappings = builder.register(this);

        creativeModeTab = registree.registerCreativeModeTab("items", () -> itemOrThrow(BlockType.WOOL).toStack(), (parameters, output) -> registree
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
                if(mapping.block == null)
                    return;

                var blockEntityType = blockType.blockEntityType();

                if(blockEntityType != null)
                    event.modify(blockEntityType.get(), mapping.block.value());
            });
        });

        registerPoi(modBus, BlockType.BED_SINGLE);
        registerPoi(modBus, BlockType.BED_DOUBLE);
    }

    private <TBlock extends Block & ComponentHolder<BlockComponent>>void registerPoi(IEventBus modBus, BlockType<TBlock, ?> blockType) {
        var block = block(blockType);

        if(block != null)
            BedBlockComponent.registerPoi(modBus, block);
    }

    public WoodType woodType() {
        return woodType;
    }

    public BlockSetType blockSetType() {
        return woodType.setType();
    }

    @Nullable
    private <TBlock extends Block, TItem extends Item> Mapping<TBlock, TItem> mapping(BlockType<TBlock, TItem> blockType) {
        return (Mapping<TBlock, TItem>) mappings.get(blockType);
    }

    private <TBlock extends Block, TItem extends Item> Mapping<TBlock, TItem> mappingOrThrow(BlockType<TBlock, TItem> blockType) {
        return Objects.requireNonNull(mapping(blockType));
    }

    @Nullable
    public <TBlock extends Block> DeferredBlock<TBlock> block(BlockType<TBlock, ?> blockType) {
        var mapping = mapping(blockType);
        return mapping == null ? null : mapping.block;
    }

    public <TBlock extends Block> DeferredBlock<TBlock> blockOrThrow(BlockType<TBlock, ?> blockType) {
        return Objects.requireNonNull(block(blockType));
    }

    @Nullable
    public <TItem extends Item> DeferredItem<TItem> item(BlockType<?, TItem> blockType) {
        var mapping = mapping(blockType);
        return mapping == null ? null : mapping.item;
    }

    public <TItem extends Item> DeferredItem<TItem> itemOrThrow(BlockType<?, TItem> blockType) {
        return Objects.requireNonNull(item(blockType));
    }

    public VoxelShape shape(BlockType<?, ?> blockType, BlockState blockState, Supplier<VoxelShape> defaultShape) {
        var mapping = mapping(blockType);

        if(mapping == null)
            return Shapes.block();

        var shapeGetter = mapping.shapeGetter;
        return shapeGetter == null ? defaultShape.get() : shapeGetter.apply(blockState);
    }

    public ResourceKey<CreativeModeTab> creativeModeTab() {
        return creativeModeTab;
    }

    public void registerDataGen(String englishName, ResourceGenerator generator) {
        var family = Util.make(() -> {
            var builder = new BlockFamily.Builder(blockOrThrow(BlockType.PLANKS).value())
                    .recipeGroupPrefix(registree.namespace())
                    .recipeUnlockedBy("has_planks");

            ifPresent(BlockType.STAIRS, builder::stairs);
            ifPresent(BlockType.SLAB, builder::slab);
            ifPresent(BlockType.FENCE, builder::fence);
            ifPresent(BlockType.FENCE_GATE, builder::fenceGate);
            ifPresent(BlockType.TRAP_DOOR, builder::trapdoor);
            ifPresent(BlockType.PRESSURE_PLATE, builder::pressurePlate);
            ifPresent(BlockType.BUTTON, builder::button);
            ifPresent(BlockType.SIGN, block -> builder.sign(block, blockOrThrow(BlockType.WALL_SIGN).value()));

            return builder.getFamily();
        });

        generator.pack()
                .providing(ProviderTypes.BLOCK_TAGS, (context, provider) -> FurnitureSetDataGen.blockTags(provider, this))
                .providing(ProviderTypes.ITEM_TAGS, (context, provider) -> FurnitureSetDataGen.itemTags(provider, this))
                .providing(ProviderTypes.LANGUAGE, (context, provider) -> FurnitureSetDataGen.language(provider, this, englishName))
                .providing(ProviderTypes.MODELS, (context, provider) -> FurnitureSetDataGen.models(provider, this, family))
                .providing(ProviderTypes.LOOT_TABLE, (context, provider) -> FurnitureSetDataGen.lootTables(provider, this));
    }

    private <TBlock extends Block> void ifPresent(BlockType<TBlock, ?> blockType, Consumer<TBlock> consumer) {
        var block = block(blockType);

        if(block != null)
            consumer.accept(block.value());
    }

    public static FurnitureSet create(String namespace, Function<Builder, Builder> builder) {
        return new FurnitureSet(namespace, builder.apply(new Builder()));
    }

    public static final class Builder {
        private final Map<BlockType<?, ?>, Consumer<? extends BlockTypeBuilder<?, ?>>> blockTypes = Maps.newHashMapWithExpectedSize(BlockType.VALUES.size());
        private final WoodTypeBuilder woodTypeBuilder = WoodTypeBuilder.builder();

        private Builder() {

        }

        public <TBlock extends Block, TItem extends Item> Builder blockType(BlockType<TBlock, TItem> blockType, Consumer<BlockTypeBuilder<TBlock, TItem>> builder) {
            blockTypes.put(blockType, builder);
            return this;
        }

        public Builder woodType(Consumer<WoodTypeBuilder> consumer) {
            consumer.accept(woodTypeBuilder);
            return this;
        }

        private Map<BlockType<?, ?>, Mapping<?, ?>> register(FurnitureSet furnitureSet) {
            var mappings = Maps.<BlockType<?, ?>, Mapping<?, ?>>newHashMap();

            BlockType.VALUES.forEach(blockType -> {
                var builder = new BlockTypeBuilder<>(blockType);
                var consumer = blockTypes.get(blockType);

                if(consumer != null)
                    ((Consumer<BlockTypeBuilder<?, ?>>) consumer).accept(builder);

                var mapping = builder.register(furnitureSet);

                if(mapping != null)
                    mappings.put(blockType, mapping);
            });

            return Collections.unmodifiableMap(mappings);
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

        @Nullable
        private Mapping<TBlock, TItem> register(FurnitureSet furnitureSet) {
            var block = blockType.registerBlock(furnitureSet.registree, furnitureSet, blockProperties);

            if(block == null)
                return null;

            var item = blockType.registerItem(furnitureSet.registree, furnitureSet, block, itemProperties);
            return new Mapping<>(block, item, shapeGetter);
        }
    }

    private record Mapping<
            TBlock extends Block,
            TItem extends Item
    >(@Nullable DeferredBlock<TBlock> block, @Nullable DeferredItem<TItem> item, @Nullable Function<BlockState, VoxelShape> shapeGetter) { }
}
