package dev.apexstudios.fantasyfurniture.set;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import dev.apexstudios.apexcore.lib.data.ProviderType;
import dev.apexstudios.fantasyfurniture.set.function.BlockFactory;
import dev.apexstudios.fantasyfurniture.set.function.ItemFactory;
import dev.apexstudios.fantasyfurniture.set.function.ProviderListener;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.apache.commons.lang3.function.Consumers;
import org.jetbrains.annotations.Nullable;

sealed class BlockTypeBuilderImpl<TBlock extends Block, TSelf extends BlockTypeBuilder<TBlock, TSelf>> implements BlockTypeBuilder<TBlock, TSelf> {
    final String registryName;
    final BlockFactory<TBlock> blockFactory;
    Function<BlockBehaviour.Properties, BlockBehaviour.Properties> blockPropertiesModifier = Function.identity();
    Supplier<BlockBehaviour.Properties> initialBlockProperties = () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS);
    @Nullable Supplier<? extends BlockEntityType<?>> blockEntityType = null;
    Map<ProviderType<?>, ProviderListener<?, TBlock>> providerListeners = Maps.newLinkedHashMap();
    Consumer<TBlock> onRegister = Consumers.nop();
    Consumer<TBlock> onRegisterEnqueued = Consumers.nop();
    Set<BlockType<?>> required = Sets.newLinkedHashSet();

    private BlockTypeBuilderImpl(String registryName, BlockFactory<TBlock> blockFactory) {
        this.registryName = registryName;
        this.blockFactory = blockFactory;
    }

    @Override
    public TSelf blockProperties(UnaryOperator<BlockBehaviour.Properties> propertiesModifier) {
        blockPropertiesModifier = blockPropertiesModifier.andThen(propertiesModifier);
        return (TSelf) this;
    }

    @Override
    public TSelf initialBlockProperties(Supplier<BlockBehaviour.Properties> initialProperties) {
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
    public TSelf onRegister(Consumer<TBlock> listener, boolean enqueued) {
        if(enqueued)
            onRegisterEnqueued = onRegisterEnqueued.andThen(listener);
        else
            onRegister = onRegister.andThen(listener);

        return (TSelf) this;
    }

    @Override
    public <TProvider> TSelf providing(ProviderType<TProvider> providerType, ProviderListener<TProvider, TBlock> listener) {
        providerListeners.put(providerType, listener);
        return (TSelf) this;
    }

    public static final class NoItem<TBlock extends Block> extends BlockTypeBuilderImpl<TBlock, BlockTypeBuilder.NoItem<TBlock>> implements BlockTypeBuilder.NoItem<TBlock> {
        public NoItem(String registryName, BlockFactory<TBlock> blockFactory) {
            super(registryName, blockFactory);
        }
    }

    public static final class WithItem<TBlock extends Block, TItem extends Item> extends BlockTypeBuilderImpl<TBlock, BlockTypeBuilder.WithItem<TBlock, TItem>> implements BlockTypeBuilder.WithItem<TBlock, TItem> {
        final ItemFactory<TBlock, TItem> itemFactory;
        Function<Item.Properties, Item.Properties> itemPropertiesModifier = Function.identity();
        Supplier<Item.Properties> initialItemProperties = Item.Properties::new;

        public WithItem(String registryName, BlockFactory<TBlock> blockFactory, ItemFactory<TBlock, TItem> itemFactory) {
            super(registryName, blockFactory);

            this.itemFactory = itemFactory;
        }

        @Override
        public BlockTypeBuilderImpl.WithItem<TBlock, TItem> itemProperties(UnaryOperator<Item.Properties> propertiesModifier) {
            itemPropertiesModifier = itemPropertiesModifier.andThen(propertiesModifier);
            return this;
        }

        @Override
        public BlockTypeBuilder.WithItem<TBlock, TItem> initialItemProperties(Supplier<Item.Properties> initialProperties) {
            this.initialItemProperties = initialProperties;
            return this;
        }
    }
}
