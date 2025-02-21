package dev.apexstudios.fantasyfurniture.set;

import com.google.common.collect.Maps;
import dev.apexstudios.apexcore.lib.data.ProviderType;
import dev.apexstudios.apexcore.lib.data.ProviderTypes;
import dev.apexstudios.fantasyfurniture.set.function.BlockFactory;
import dev.apexstudios.fantasyfurniture.set.function.BlockLootTableListener;
import dev.apexstudios.fantasyfurniture.set.function.ItemFactory;
import dev.apexstudios.fantasyfurniture.set.function.ModelProviderListener;
import dev.apexstudios.fantasyfurniture.set.function.ProviderListener;
import dev.apexstudios.fantasyfurniture.set.function.RecipeListener;
import dev.apexstudios.fantasyfurniture.set.function.TagListener;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

public abstract sealed class BlockTypeBuilder<TBlock extends Block, TType extends BlockType<TBlock>, TSelf extends BlockTypeBuilder<TBlock, TType, TSelf>> {
    final String registryName;
    final BlockFactory<TBlock> blockFactory;
    BiFunction<FurnitureSet, BlockBehaviour.Properties, BlockBehaviour.Properties> blockPropertiesModifier = (furnitureSet, properties) -> properties;
    Function<FurnitureSet, BlockBehaviour.Properties> initialBlockProperties = furnitureSet -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS);
    @Nullable Supplier<? extends BlockEntityType<?>> blockEntityType = null;
    Map<ProviderType<?>, ProviderListener<?, TBlock>> providerListeners = Maps.newLinkedHashMap();
    BiConsumer<FurnitureSet, TBlock> onRegister = (furnitureSet, block) -> { };
    BiConsumer<FurnitureSet, TBlock> onRegisterEnqueued = (furnitureSet, block) -> { };

    private BlockTypeBuilder(String registryName, BlockFactory<TBlock> blockFactory) {
        this.registryName = registryName;
        this.blockFactory = blockFactory;

        blockTags((provider, furnitureSet, block) -> {
            if(block.defaultBlockState().canBeReplaced())
                provider.tag(BlockTags.REPLACEABLE).withElement(block);
        });
    }

    public TSelf blockProperties(BiFunction<FurnitureSet, BlockBehaviour.Properties, BlockBehaviour.Properties> blockPropertiesModifier) {
        this.blockPropertiesModifier = andThen(this.blockPropertiesModifier, blockPropertiesModifier);
        return (TSelf) this;
    }

    public TSelf blockProperties(UnaryOperator<BlockBehaviour.Properties> blockPropertiesModifier) {
        return blockProperties((furnitureSet, properties) -> blockPropertiesModifier.apply(properties));
    }

    public TSelf initialBlockProperties(Function<FurnitureSet, BlockBehaviour.Properties> initialBlockProperties) {
        this.initialBlockProperties = initialBlockProperties;
        return (TSelf) this;
    }

    public TSelf initialBlockProperties(Supplier<BlockBehaviour.Properties> initialBlockProperties) {
        return initialBlockProperties(furnitureSet -> initialBlockProperties.get());
    }

    public TSelf initialBlockProperties(BlockBehaviour.Properties initialBlockProperties) {
        return initialBlockProperties(() -> initialBlockProperties);
    }

    public TSelf copyInitialBlockPropertiesFull(Function<FurnitureSet, BlockBehaviour> blockGetter) {
        return initialBlockProperties(furnitureSet -> BlockBehaviour.Properties.ofFullCopy(blockGetter.apply(furnitureSet)));
    }

    public TSelf copyInitialBlockPropertiesFull(Supplier<BlockBehaviour> block) {
        return copyInitialBlockPropertiesFull(furnitureSet -> block.get());
    }

    public TSelf copyInitialBlockPropertiesLegacy(Function<FurnitureSet, BlockBehaviour> blockGetter) {
        return initialBlockProperties(furnitureSet -> BlockBehaviour.Properties.ofLegacyCopy(blockGetter.apply(furnitureSet)));
    }

    public TSelf copyInitialBlockPropertiesLegacy(Supplier<BlockBehaviour> block) {
        return copyInitialBlockPropertiesLegacy(furnitureSet -> block.get());
    }

    public TSelf blockEntity(Supplier<? extends BlockEntityType<?>> blockEntityType) {
        this.blockEntityType = blockEntityType;
        return (TSelf) this;
    }

    public TSelf onRegister(BiConsumer<FurnitureSet, TBlock> listener, boolean enqueued) {
        if(enqueued)
            onRegisterEnqueued = onRegisterEnqueued.andThen(listener);
        else
            onRegister = onRegister.andThen(listener);

        return (TSelf) this;
    }

    public TSelf onRegister(BiConsumer<FurnitureSet, TBlock> listener) {
        return onRegister(listener, false);
    }

    public TSelf onRegister(Consumer<TBlock> listener, boolean enqueued) {
        return onRegister((furnitureSet, block) -> listener.accept(block), enqueued);
    }

    public TSelf onRegister(Consumer<TBlock> listener) {
        return onRegister((furnitureSet, block) -> listener.accept(block));
    }

    public <TProvider> TSelf providing(ProviderType<TProvider> providerType, ProviderListener<TProvider, TBlock> listener) {
        providerListeners.put(providerType, listener);
        return (TSelf) this;
    }

    public TSelf translation(String translation) {
        return providing(ProviderTypes.LANGUAGE, (context, provider, furnitureSet, block) -> {
            var furnitureSetName = StringUtils.capitalize(furnitureSet.name());
            var descriptionId = this instanceof WithItem ? block.asItem().getDescriptionId() : block.getDescriptionId();
            provider.add(descriptionId, furnitureSetName + ' ' + translation);
        });
    }

    public TSelf lootTable(BlockLootTableListener<TBlock> listener) {
        return providing(ProviderTypes.LOOT_TABLE, (context, provider, furnitureSet, block) -> provider.block(blocks -> listener.accept(blocks, furnitureSet, block)));
    }

    public TSelf model(Supplier<ModelProviderListener<TBlock>> listener) {
        return providing(ProviderTypes.MODELS, (context, provider, furnitureSet, block) -> listener.get().accept(context, provider.blockModels(), furnitureSet, block));
    }

    public TSelf blockTags(TagListener<Block, TBlock> listener) {
        return providing(ProviderTypes.BLOCK_TAGS, (context, provider, furnitureSet, block) -> listener.accept(provider, furnitureSet, block));
    }

    abstract TType build();

    private static <TLeft, TRight> BiFunction<TLeft, TRight, TRight> andThen(BiFunction<TLeft, TRight, TRight> before, BiFunction<TLeft, TRight, TRight> after) {
        return (furnitureSet, properties) -> after.apply(furnitureSet, before.apply(furnitureSet, properties));
    }

    public static final class NoItem<TBlock extends Block> extends BlockTypeBuilder<TBlock, BlockType.NoItem<TBlock>, NoItem<TBlock>> {
        NoItem(String registryName, BlockFactory<TBlock> blockFactory) {
            super(registryName, blockFactory);
        }

        @Override
        BlockType.NoItem<TBlock> build() {
            return new BlockType.NoItem<>(this);
        }
    }

    public static final class WithItem<TBlock extends Block, TItem extends Item> extends BlockTypeBuilder<TBlock, BlockType.WithItem<TBlock, TItem>, WithItem<TBlock, TItem>> {
        final ItemFactory<TBlock, TItem> itemFactory;
        BiFunction<FurnitureSet, Item.Properties, Item.Properties> itemPropertiesModifier = (furnitureSet, properties) -> properties;
        Function<FurnitureSet, Item.Properties> initialItemProperties = furnitureSet -> new Item.Properties();

        WithItem(String registryName, BlockFactory<TBlock> blockFactory, ItemFactory<TBlock, TItem> itemFactory) {
            super(registryName, blockFactory);

            this.itemFactory = itemFactory;
        }

        public WithItem<TBlock, TItem> itemProperties(BiFunction<FurnitureSet, Item.Properties, Item.Properties> itemPropertiesModifier) {
            this.itemPropertiesModifier = andThen(itemPropertiesModifier, itemPropertiesModifier);
            return this;
        }

        public WithItem<TBlock, TItem> itemProperties(UnaryOperator<Item.Properties> itemPropertiesModifier) {
            return itemProperties((furnitureSet, properties) -> itemPropertiesModifier.apply(properties));
        }

        public WithItem<TBlock, TItem> initialItemProperties(Function<FurnitureSet, Item.Properties> initialItemProperties) {
            this.initialItemProperties = initialItemProperties;
            return this;
        }

        public WithItem<TBlock, TItem> initialItemProperties(Supplier<Item.Properties> initialItemProperties) {
            return initialItemProperties(furnitureSet -> initialItemProperties.get());
        }

        public WithItem<TBlock, TItem> initialItemProperties(Item.Properties initialItemProperties) {
            return initialItemProperties(() -> initialItemProperties);
        }

        public WithItem<TBlock, TItem> itemTags(TagListener<Item, TItem> listener) {
            return providing(ProviderTypes.ITEM_TAGS, (context, provider, furnitureSet, block) -> listener.accept(provider, furnitureSet, (TItem) block.asItem()));
        }

        public WithItem<TBlock, TItem> recipe(RecipeListener<TItem> listener) {
            return providing(ProviderTypes.RECIPES, (context, provider, furnitureSet, block) -> listener.accept(provider, furnitureSet, (TItem) block.asItem()));
        }

        @Override
        BlockType.WithItem<TBlock, TItem> build() {
            return new BlockType.WithItem<>(this);
        }
    }
}
