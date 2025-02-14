package dev.apexstudios.fantasyfurniture.set;

import com.google.errorprone.annotations.OverridingMethodsMustInvokeSuper;
import dev.apexstudios.apexcore.lib.data.ProviderType;
import dev.apexstudios.apexcore.lib.data.pack.ModPackGenerator;
import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.fantasyfurniture.set.function.BlockFactory;
import dev.apexstudios.fantasyfurniture.set.function.ItemFactory;
import dev.apexstudios.fantasyfurniture.set.function.ProviderListener;
import java.util.Collections;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import org.jetbrains.annotations.Nullable;

abstract sealed class BlockTypeImpl<TBlock extends Block> implements BlockType<TBlock> {
    private final String registryName;
    private final BiFunction<FurnitureSet, BlockBehaviour.Properties, BlockBehaviour.Properties> blockPropertiesModifier;
    private final Function<FurnitureSet, BlockBehaviour.Properties> initialBlockProperties;
    protected final BlockFactory<TBlock> blockFactory;
    @Nullable private final Supplier<? extends BlockEntityType<?>> blockEntityType;
    private final Map<ProviderType<?>, ProviderListener<?, TBlock>> providerListeners;
    private final BiConsumer<FurnitureSet, TBlock> onRegister;
    private final BiConsumer<FurnitureSet, TBlock> onRegisterEnqueued;

    private BlockTypeImpl(BlockTypeBuilderImpl<TBlock, ?> builder) {
        registryName = builder.registryName;
        blockPropertiesModifier = builder.blockPropertiesModifier;
        initialBlockProperties = builder.initialBlockProperties;
        blockFactory = builder.blockFactory;
        blockEntityType = builder.blockEntityType;
        providerListeners = Collections.unmodifiableMap(builder.providerListeners);
        onRegister = builder.onRegister;
        onRegisterEnqueued = builder.onRegisterEnqueued;
    }

    @Override
    public String registryName() {
        return registryName;
    }

    @Override
    public BlockBehaviour.Properties blockProperties(FurnitureSet furnitureSet) {
        return blockPropertiesModifier.apply(furnitureSet, initialBlockProperties.apply(furnitureSet));
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj instanceof BlockType<?> other)
            return registryName.equals(other.registryName());
        return false;
    }

    @Override
    public int hashCode() {
        return registryName.hashCode();
    }

    @Override
    public String toString() {
        return "BlockType{" + registryName + '}';
    }

    @OverridingMethodsMustInvokeSuper
    protected void register(IEventBus modBus, FurnitureSet furnitureSet, Registree registree) {
        registree.register(Registries.BLOCK, registryName, registryName -> blockFactory.create(furnitureSet, blockProperties(furnitureSet).setId(ResourceKey.create(Registries.BLOCK, registryName))));
        registree.listenFor(Registries.BLOCK, registryName, block -> onRegister.accept(furnitureSet, (TBlock) block));
        modBus.addListener(FMLCommonSetupEvent.class, event -> event.enqueueWork(() -> onRegisterEnqueued.accept(furnitureSet, furnitureSet.getOrThrow(this))));

        if(blockEntityType != null)
            modBus.addListener(BlockEntityTypeAddBlocksEvent.class, event -> event.modify(blockEntityType.get(), furnitureSet.getOrThrow(this)));
    }

    void registerDataGen(ModPackGenerator generator, FurnitureSet furnitureSet) {
        providerListeners.keySet().forEach(providerType -> registerProvider(generator, providerType, furnitureSet));
    }

    private <TProvider> void registerProvider(ModPackGenerator generator, ProviderType<TProvider> providerType, FurnitureSet furnitureSet) {
        generator.providing(providerType, (context, provider) -> ((ProviderListener<TProvider, TBlock>) providerListeners.get(providerType)).accept(context, provider, furnitureSet, furnitureSet.getOrThrow(this)));
    }

    protected final <TBuilder extends BlockTypeBuilderImpl<TBlock, ? super TBuilder>> TBuilder copyInto(TBuilder builder) {
        var result = (TBuilder) builder.initialBlockProperties(initialBlockProperties)
                .blockProperties(blockPropertiesModifier)
                .blockEntity(blockEntityType)
                .onRegister(onRegister, false)
                .onRegister(onRegisterEnqueued, true);

        result.providerListeners.putAll(providerListeners);
        return result;
    }

    static {
        BlockTypes.register();
    }

    public static final class NoItem<TBlock extends Block> extends BlockTypeImpl<TBlock> implements BlockType.NoItem<TBlock> {
        public NoItem(BlockTypeBuilderImpl.NoItem<TBlock> builder) {
            super(builder);
        }

        @Override
        public BlockType.NoItem<TBlock> extend(BlockFactory<TBlock> blockFactory, Consumer<BlockTypeBuilder.NoItem<TBlock>> consumer) {
            return BlockType.noItem(registryName(), blockFactory, builder -> consumer.accept(copyInto((BlockTypeBuilderImpl.NoItem<TBlock>) builder)));
        }
    }

    public static final class WithItem<TBlock extends Block, TItem extends Item> extends BlockTypeImpl<TBlock> implements BlockType.WithItem<TBlock, TItem> {
        private final BiFunction<FurnitureSet, Item.Properties, Item.Properties> itemPropertiesModifier;
        private final Function<FurnitureSet, Item.Properties> initialItemProperties;
        private final ItemFactory<TBlock, TItem> itemFactory;

        public WithItem(BlockTypeBuilderImpl.WithItem<TBlock, TItem> builder) {
            super(builder);

            itemPropertiesModifier = builder.itemPropertiesModifier;
            initialItemProperties = builder.initialItemProperties;
            itemFactory = builder.itemFactory;
        }

        @Override
        public Item.Properties itemProperties(FurnitureSet furnitureSet) {
            return itemPropertiesModifier.apply(furnitureSet, initialItemProperties.apply(furnitureSet));
        }

        @Override
        public BlockType.WithItem<TBlock, TItem> extend(BlockFactory<TBlock> blockFactory, ItemFactory<TBlock, TItem> itemFactory, Consumer<BlockTypeBuilder.WithItem<TBlock, TItem>> consumer) {
            return BlockType.withItem(registryName(), blockFactory, itemFactory, builder -> consumer.accept(copyInto((BlockTypeBuilderImpl.WithItem<TBlock, TItem>) builder)
                    .initialItemProperties(initialItemProperties)
                    .itemProperties(itemPropertiesModifier)
            ));
        }

        @Override
        public BlockType.WithItem<TBlock, TItem> extend(BlockFactory<TBlock> blockFactory, Consumer<BlockTypeBuilder.WithItem<TBlock, TItem>> consumer) {
            return extend(blockFactory, itemFactory, consumer);
        }

        @Override
        public BlockType.WithItem<TBlock, TItem> extend(ItemFactory<TBlock, TItem> itemFactory, Consumer<BlockTypeBuilder.WithItem<TBlock, TItem>> consumer) {
            return extend(blockFactory, itemFactory, consumer);
        }

        @Override
        protected void register(IEventBus modBus, FurnitureSet furnitureSet, Registree registree) {
            super.register(modBus, furnitureSet, registree);
            registree.register(Registries.ITEM, registryName(), registryName -> itemFactory.create(furnitureSet, furnitureSet.getOrThrow(this), itemProperties(furnitureSet).useBlockDescriptionPrefix().setId(ResourceKey.create(Registries.ITEM, registryName))));
        }
    }
}
