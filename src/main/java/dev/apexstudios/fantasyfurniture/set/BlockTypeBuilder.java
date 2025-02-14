package dev.apexstudios.fantasyfurniture.set;

import dev.apexstudios.apexcore.lib.data.ProviderType;
import dev.apexstudios.apexcore.lib.data.ProviderTypes;
import dev.apexstudios.fantasyfurniture.set.function.BlockLootTableListener;
import dev.apexstudios.fantasyfurniture.set.function.ModelProviderListener;
import dev.apexstudios.fantasyfurniture.set.function.ProviderListener;
import dev.apexstudios.fantasyfurniture.set.function.RecipeListener;
import dev.apexstudios.fantasyfurniture.set.function.TagListener;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.apache.commons.lang3.StringUtils;

public sealed interface BlockTypeBuilder<TBlock extends Block, TSelf extends BlockTypeBuilder<TBlock, TSelf>> permits BlockTypeBuilder.NoItem, BlockTypeBuilder.WithItem, BlockTypeBuilderImpl {
    TSelf blockProperties(BiFunction<FurnitureSet, BlockBehaviour.Properties, BlockBehaviour.Properties> propertiesModifier);

    default TSelf blockProperties(UnaryOperator<BlockBehaviour.Properties> propertiesModifier) {
        return blockProperties((furnitureSet, properties) -> propertiesModifier.apply(properties));
    }

    TSelf initialBlockProperties(Function<FurnitureSet, BlockBehaviour.Properties> initialProperties);

    default TSelf initialBlockProperties(Supplier<BlockBehaviour.Properties> initialProperties) {
        return initialBlockProperties(furnitureSet -> initialProperties.get());
    }

    default TSelf initialBlockProperties(BlockBehaviour.Properties initialProperties) {
        return initialBlockProperties(() -> initialProperties);
    }

    default TSelf copyInitialBlockPropertiesFull(Function<FurnitureSet, BlockBehaviour> blockGetter) {
        return initialBlockProperties(furnitureSet -> BlockBehaviour.Properties.ofFullCopy(blockGetter.apply(furnitureSet)));
    }

    default TSelf copyInitialBlockPropertiesFull(Supplier<BlockBehaviour> block) {
        return copyInitialBlockPropertiesFull(furnitureSet -> block.get());
    }

    default TSelf copyInitialBlockPropertiesLegacy(Function<FurnitureSet, BlockBehaviour> blockGetter) {
        return initialBlockProperties(furnitureSet -> BlockBehaviour.Properties.ofLegacyCopy(blockGetter.apply(furnitureSet)));
    }

    default TSelf copyInitialBlockPropertiesLegacy(Supplier<BlockBehaviour> block) {
        return copyInitialBlockPropertiesLegacy(furnitureSet -> block.get());
    }

    TSelf blockEntity(Supplier<? extends BlockEntityType<?>> blockEntityType);

    TSelf onRegister(BiConsumer<FurnitureSet, TBlock> listener, boolean enqueued);

    default TSelf onRegister(BiConsumer<FurnitureSet, TBlock> listener) {
        return onRegister(listener, false);
    }

    default TSelf onRegister(Consumer<TBlock> listener, boolean enqueued) {
        return onRegister((furnitureSet, block) -> listener.accept(block), enqueued);
    }

    default TSelf onRegister(Consumer<TBlock> listener) {
        return onRegister((furnitureSet, block) -> listener.accept(block));
    }

    <TProvider> TSelf providing(ProviderType<TProvider> providerType, ProviderListener<TProvider, TBlock> listener);

    default TSelf translation(String translation) {
        return providing(ProviderTypes.LANGUAGE, (context, provider, furnitureSet, block) -> {
            var furnitureSetName = StringUtils.capitalize(furnitureSet.name());
            var descriptionId = this instanceof WithItem ? block.asItem().getDescriptionId() : block.getDescriptionId();
            provider.add(descriptionId, furnitureSetName + ' ' + translation);
        });
    }

    default TSelf lootTable(BlockLootTableListener<TBlock> listener) {
        return providing(ProviderTypes.LOOT_TABLE, (context, provider, furnitureSet, block) -> provider.block(blocks -> listener.accept(blocks, furnitureSet, block)));
    }

    default TSelf model(Supplier<ModelProviderListener<TBlock>> listener) {
        return providing(ProviderTypes.MODELS, (context, provider, furnitureSet, block) -> listener.get().accept(context, provider.blockModels(), furnitureSet, block));
    }

    default TSelf blockTags(TagListener<Block, TBlock> listener) {
        return providing(ProviderTypes.BLOCK_TAGS, (context, provider, furnitureSet, block) -> listener.accept(provider, furnitureSet, block));
    }

    sealed interface NoItem<TBlock extends Block> extends BlockTypeBuilder<TBlock, NoItem<TBlock>> permits BlockTypeBuilderImpl.NoItem { }

    sealed interface WithItem<TBlock extends Block, TItem extends Item> extends BlockTypeBuilder<TBlock, WithItem<TBlock, TItem>> permits BlockTypeBuilderImpl.WithItem {
        WithItem<TBlock, TItem> itemProperties(BiFunction<FurnitureSet, Item.Properties, Item.Properties> propertiesModifier);

        default WithItem<TBlock, TItem> itemProperties(UnaryOperator<Item.Properties> propertiesModifier) {
            return itemProperties((furnitureSet, properties) -> propertiesModifier.apply(properties));
        }

        WithItem<TBlock, TItem> initialItemProperties(Function<FurnitureSet, Item.Properties> initialProperties);

        default WithItem<TBlock, TItem> initialItemProperties(Supplier<Item.Properties> initialProperties) {
            return initialItemProperties(furnitureSet -> initialProperties.get());
        }

        default WithItem<TBlock, TItem> initialItemProperties(Item.Properties initialProperties) {
            return initialItemProperties(() -> initialProperties);
        }

        default WithItem<TBlock, TItem> itemTags(TagListener<Item, TItem> listener) {
            return providing(ProviderTypes.ITEM_TAGS, (context, provider, furnitureSet, block) -> listener.accept(provider, furnitureSet, (TItem) block.asItem()));
        }

        default WithItem<TBlock, TItem> recipe(RecipeListener<TItem> listener) {
            return providing(ProviderTypes.RECIPES, (context, provider, furnitureSet, block) -> listener.accept(provider, furnitureSet, (TItem) block.asItem()));
        }
    }
}
