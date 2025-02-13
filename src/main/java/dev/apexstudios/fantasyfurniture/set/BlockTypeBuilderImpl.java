package dev.apexstudios.fantasyfurniture.set;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import dev.apexstudios.apexcore.lib.data.ProviderType;
import dev.apexstudios.fantasyfurniture.set.function.BlockFactory;
import dev.apexstudios.fantasyfurniture.set.function.ItemFactory;
import dev.apexstudios.fantasyfurniture.set.function.ProviderListener;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.Nullable;

sealed class BlockTypeBuilderImpl<TBlock extends Block, TSelf extends BlockTypeBuilder<TBlock, TSelf>> implements BlockTypeBuilder<TBlock, TSelf> {
    final String registryName;
    final BlockFactory<TBlock> blockFactory;
    BiFunction<FurnitureSet, BlockBehaviour.Properties, BlockBehaviour.Properties> blockPropertiesModifier = (furnitureSet, properties) -> properties;
    Function<FurnitureSet, BlockBehaviour.Properties> initialBlockProperties = furnitureSet -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS);
    @Nullable Supplier<? extends BlockEntityType<?>> blockEntityType = null;
    Map<ProviderType<?>, ProviderListener<?, TBlock>> providerListeners = Maps.newLinkedHashMap();
    BiConsumer<FurnitureSet, TBlock> onRegister = (furnitureSet, block) -> { };
    BiConsumer<FurnitureSet, TBlock> onRegisterEnqueued = (furnitureSet, block) -> { };
    Set<BlockType<?>> required = Sets.newLinkedHashSet();

    private BlockTypeBuilderImpl(String registryName, BlockFactory<TBlock> blockFactory) {
        this.registryName = registryName;
        this.blockFactory = blockFactory;

        blockTags((provider, furnitureSet, block) -> {
            if(block.defaultBlockState().canBeReplaced())
                provider.tag(BlockTags.REPLACEABLE).withElement(block);
        });
    }

    @Override
    public TSelf blockProperties(BiFunction<FurnitureSet, BlockBehaviour.Properties, BlockBehaviour.Properties> propertiesModifier) {
        blockPropertiesModifier = andThen(blockPropertiesModifier, propertiesModifier);
        return (TSelf) this;
    }

    @Override
    public TSelf initialBlockProperties(Function<FurnitureSet, BlockBehaviour.Properties> initialProperties) {
        initialBlockProperties = initialProperties;
        return (TSelf) this;
    }

    @Override
    public TSelf blockEntity(Supplier<? extends BlockEntityType<?>> blockEntityType) {
        this.blockEntityType = blockEntityType;
        return (TSelf) this;
    }

    @Override
    public TSelf require(BlockType<?> blockType) {
        required.add(blockType);
        return (TSelf) this;
    }

    @Override
    public TSelf onRegister(BiConsumer<FurnitureSet, TBlock> listener, boolean enqueued) {
        if(enqueued)
            onRegisterEnqueued = onRegisterEnqueued.andThen(listener);
        else
            onRegister = onRegister.andThen(listener);

        return (TSelf) this;
    }

    @Override
    public <TProvider> TSelf providing(ProviderType<TProvider> providerType, ProviderListener<TProvider, TBlock> listener) {
        providerListeners.compute(providerType, ($, existing) -> existing == null ? listener : ((ProviderListener<TProvider, TBlock>) existing).andThen(listener));
        return (TSelf) this;
    }

    private static <TLeft, TRight> BiFunction<TLeft, TRight, TRight> andThen(BiFunction<TLeft, TRight, TRight> before, BiFunction<TLeft, TRight, TRight> after) {
        return (furnitureSet, properties) -> after.apply(furnitureSet, before.apply(furnitureSet, properties));
    }

    public static final class NoItem<TBlock extends Block> extends BlockTypeBuilderImpl<TBlock, BlockTypeBuilder.NoItem<TBlock>> implements BlockTypeBuilder.NoItem<TBlock> {
        public NoItem(String registryName, BlockFactory<TBlock> blockFactory) {
            super(registryName, blockFactory);
        }
    }

    public static final class WithItem<TBlock extends Block, TItem extends Item> extends BlockTypeBuilderImpl<TBlock, BlockTypeBuilder.WithItem<TBlock, TItem>> implements BlockTypeBuilder.WithItem<TBlock, TItem> {
        final ItemFactory<TBlock, TItem> itemFactory;
        BiFunction<FurnitureSet, Item.Properties, Item.Properties> itemPropertiesModifier = (furnitureSet, properties) -> properties;
        Function<FurnitureSet, Item.Properties> initialItemProperties = furnitureSet -> new Item.Properties();

        public WithItem(String registryName, BlockFactory<TBlock> blockFactory, ItemFactory<TBlock, TItem> itemFactory) {
            super(registryName, blockFactory);

            this.itemFactory = itemFactory;
        }

        @Override
        public BlockTypeBuilderImpl.WithItem<TBlock, TItem> itemProperties(BiFunction<FurnitureSet, Item.Properties, Item.Properties> propertiesModifier) {
            itemPropertiesModifier = andThen(itemPropertiesModifier, propertiesModifier);
            return this;
        }

        @Override
        public BlockTypeBuilder.WithItem<TBlock, TItem> initialItemProperties(Function<FurnitureSet, Item.Properties> initialProperties) {
            this.initialItemProperties = initialProperties;
            return this;
        }
    }
}
