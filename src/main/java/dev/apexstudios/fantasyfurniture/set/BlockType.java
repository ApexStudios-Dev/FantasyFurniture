package dev.apexstudios.fantasyfurniture.set;

import com.google.common.collect.Sets;
import com.google.errorprone.annotations.OverridingMethodsMustInvokeSuper;
import dev.apexstudios.apexcore.lib.data.ProviderType;
import dev.apexstudios.apexcore.lib.data.ProviderTypes;
import dev.apexstudios.apexcore.lib.data.pack.PackGenerator;
import dev.apexstudios.fantasyfurniture.set.function.BlockFactory;
import dev.apexstudios.fantasyfurniture.set.function.ItemFactory;
import dev.apexstudios.fantasyfurniture.set.function.ProviderListener;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.mixins.BlockEntityTypeAccessor;
import org.apache.commons.lang3.function.Consumers;
import org.jetbrains.annotations.Nullable;

public sealed class BlockType<TBlock extends Block> {
    protected final String registryName;
    protected final BiFunction<FurnitureSet, BlockBehaviour.Properties, BlockBehaviour.Properties> blockPropertiesModifier;
    protected final BlockFactory<TBlock> blockFactory;
    @Nullable protected final Supplier<? extends BlockEntityType<?>> blockEntityType;
    protected final Map<ProviderType<?>, ProviderListener<?, TBlock>> providerListeners;
    protected final BiConsumer<FurnitureSet, TBlock> onRegister;
    protected final BiConsumer<FurnitureSet, TBlock> onRegisterEnqueued;
    @Nullable protected final Supplier<? extends BlockBehaviour> baseBlock;
    protected final boolean usesMineableTag;
    protected final Set<TagKey<Block>> blockTags;

    private BlockType(BlockTypeBuilder<TBlock, ? extends BlockType<TBlock>, ?> builder) {
        registryName = builder.registryName;
        blockPropertiesModifier = builder.blockPropertiesModifier;
        blockFactory = builder.blockFactory;
        blockEntityType = builder.blockEntityType;
        providerListeners = Collections.unmodifiableMap(builder.providerListeners);
        onRegister = builder.onRegister;
        onRegisterEnqueued = builder.onRegisterEnqueued;
        baseBlock = builder.baseBlock;
        usesMineableTag = builder.usesMineableTag;
        blockTags = Set.copyOf(builder.blockTags);
    }

    public String registryName() {
        return registryName;
    }

    public BlockBehaviour.Properties blockProperties(FurnitureSet furnitureSet) {
        var baseBlock = Objects.requireNonNullElse(this.baseBlock, furnitureSet.baseBlock);
        return blockPropertiesModifier.apply(furnitureSet, BlockBehaviour.Properties.ofLegacyCopy(baseBlock.get()).sound(furnitureSet.blockSet().soundType()));
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj instanceof BlockType<?> other)
            return registryName.equals(other.registryName);
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
    void register(IEventBus modBus, FurnitureSet furnitureSet) {
        var blockRegistryName = furnitureSet.registrationName(registryName);
        furnitureSet.registree.register(Registries.BLOCK, blockRegistryName, registryName -> blockFactory.create(furnitureSet, blockProperties(furnitureSet).setId(ResourceKey.create(Registries.BLOCK, registryName))));
        furnitureSet.registree.listenFor(Registries.BLOCK, blockRegistryName, block -> onRegister.accept(furnitureSet, (TBlock) block));
        modBus.addListener(FMLCommonSetupEvent.class, event -> event.enqueueWork(() -> onRegisterEnqueued.accept(furnitureSet, furnitureSet.getOrThrow(this))));

        if(blockEntityType != null)
            modBus.addListener(BlockEntityTypeAddBlocksEvent.class, event -> markAsValidBlockEntityBlock(furnitureSet, blockEntityType.get()));
    }

    @OverridingMethodsMustInvokeSuper
    void registerDataGen(PackGenerator<?> generator, FurnitureSet furnitureSet) {
        providerListeners.keySet().forEach(providerType -> registerProvider(generator, providerType, furnitureSet));

        generator.providing(ProviderTypes.BLOCK_TAGS, (context, provider) -> blockTags.forEach(tag -> {
            var block = furnitureSet.getOrThrow(this);

            provider.tag(tag).withElement(block);

            if(block.defaultBlockState().canBeReplaced())
                provider.tag(BlockTags.REPLACEABLE).withElement(block);
        }));
    }

    private <TProvider> void registerProvider(PackGenerator<?> generator, ProviderType<TProvider> providerType, FurnitureSet furnitureSet) {
        generator.providing(providerType, (context, provider) -> ((ProviderListener<TProvider, TBlock>) providerListeners.get(providerType)).accept(context, provider, furnitureSet, furnitureSet.getOrThrow(this)));
    }

    private void markAsValidBlockEntityBlock(FurnitureSet furnitureSet, BlockEntityType<?> blockEntityType) {
        // 'BlockEntityTypeAddBlocksEvent' does some internal class comparison testing
        // to ensure blocks share a common super type
        // this leads to a issue where our BlockTypes which have been extended/copied
        // can change the common super type
        // for example
        // the nordic dresser block is registered using the class 'NordicDresserBlock'
        // and venthyr is registered using 'VenthyrDresserBlock'
        // while both extend 'DresserBlock'
        // neoforge extracts the common super type from the already existing valid blocks
        // which for us initially is empty, which causes the event to listen for the first valid block (which could vary between game launches)
        // nordic registers first now all 'dresser' blocks must extend 'NordicDresserBlock'
        // venthyr registers first now they must be 'VenthyrDresserBlocks'
        // not the actual super type we are after 'DresserBlock'
        var validBlocks = Sets.newHashSet(blockEntityType.getValidBlocks());
        validBlocks.add(furnitureSet.getOrThrow(this));
        ((BlockEntityTypeAccessor) blockEntityType).neoforge$setValidBlocks(validBlocks);
    }

    public static <TBlock extends Block, TItem extends Item> WithItem<TBlock, TItem> withItem(String registryName, BlockFactory<TBlock> blockFactory, ItemFactory<TBlock, TItem> itemFactory, Consumer<BlockTypeBuilder.WithItem<TBlock, TItem>> consumer) {
        var builder = new BlockTypeBuilder.WithItem<>(registryName, blockFactory, itemFactory);
        consumer.accept(builder);
        return builder.build();
    }

    public static <TBlock extends Block, TItem extends Item> WithItem<TBlock, TItem> withItem(String registryName, BlockFactory<TBlock> blockFactory, ItemFactory<TBlock, TItem> itemFactory) {
        return withItem(registryName, blockFactory, itemFactory, Consumers.nop());
    }

    public static <TBlock extends Block> WithItem<TBlock, BlockItem> withItem(String registryName, BlockFactory<TBlock> blockFactory, Consumer<BlockTypeBuilder.WithItem<TBlock, BlockItem>> consumer) {
        return withItem(registryName, blockFactory, (furnitureSet, block, properties) -> new BlockItem(block, properties), consumer);
    }

    public static <TBlock extends Block> WithItem<TBlock, BlockItem> withItem(String registryName, BlockFactory<TBlock> blockFactory) {
        return withItem(registryName, blockFactory, (furnitureSet, block, properties) -> new BlockItem(block, properties), Consumers.nop());
    }

    public static <TBlock extends Block> NoItem<TBlock> noItem(String registryName, BlockFactory<TBlock> blockFactory, Consumer<BlockTypeBuilder.NoItem<TBlock>> consumer) {
        var builder = new BlockTypeBuilder.NoItem<>(registryName, blockFactory);
        consumer.accept(builder);
        return builder.build();
    }

    public static <TBlock extends Block> NoItem<TBlock> noItem(String registryName, BlockFactory<TBlock> blockFactory) {
        return noItem(registryName, blockFactory, Consumers.nop());
    }

    private static <TBlock extends Block, TItem extends Item> BiFunction<String, BlockFactory<TBlock>, BlockTypeBuilder.WithItem<TBlock, TItem>> withItemFactory(ItemFactory<TBlock, TItem> itemFactory) {
        return (registryName, blockFactory) -> new BlockTypeBuilder.WithItem<>(registryName, blockFactory, itemFactory);
    }

    static {
        BlockTypes.register();
    }

    public static final class NoItem<TBlock extends Block> extends BlockType<TBlock> {
        NoItem(BlockTypeBuilder.NoItem<TBlock> builder) {
            super(builder);
        }

        public NoItem<TBlock> copy(Consumer<BlockTypeCopier.NoItem<TBlock>> consumer) {
            var copier = new BlockTypeCopier.NoItem<>(this);
            consumer.accept(copier);
            return copier.compile();
        }

        public NoItem<TBlock> copyWithSuffix(String suffix) {
            return copy(copier -> copier.registryName(registryName -> registryName + suffix));
        }

        public NoItem<TBlock> extend(BlockFactory<TBlock> blockFactory) {
            return copy(copier -> copier.blockFactory(blockFactory));
        }
    }

    public static final class WithItem<TBlock extends Block, TItem extends Item> extends BlockType<TBlock> {
        final BiFunction<FurnitureSet, Item.Properties, Item.Properties> itemPropertiesModifier;
        final ItemFactory<TBlock, TItem> itemFactory;
        final Set<TagKey<Item>> itemTags;

        WithItem(BlockTypeBuilder.WithItem<TBlock, TItem> builder) {
            super(builder);

            itemPropertiesModifier = builder.itemPropertiesModifier;
            itemFactory = builder.itemFactory;
            itemTags = Set.copyOf(builder.itemTags);
        }

        public Item.Properties itemProperties(FurnitureSet furnitureSet) {
            return itemPropertiesModifier.apply(furnitureSet, new Item.Properties());
        }

        public WithItem<TBlock, TItem> copy(Consumer<BlockTypeCopier.WithItem<TBlock, TItem>> consumer) {
            var copier = new BlockTypeCopier.WithItem<>(this);
            consumer.accept(copier);
            return copier.compile();
        }

        public WithItem<TBlock, TItem> copyWithSuffix(String suffix) {
            return copy(copier -> copier.registryName(registryName -> registryName + suffix));
        }

        public NoItem<TBlock> copyNoItem(Consumer<BlockTypeCopier.NoItem<TBlock>> consumer) {
            var copier = new BlockTypeCopier.NoItem<>(this);
            consumer.accept(copier);
            return copier.compile();
        }

        public WithItem<TBlock, TItem> extend(BlockFactory<TBlock> blockFactory, ItemFactory<TBlock, TItem> itemFactory) {
            return copy(copier -> copier.blockFactory(blockFactory).itemFactory(itemFactory));
        }

        public WithItem<TBlock, TItem> extend(BlockFactory<TBlock> blockFactory) {
            return copy(copier -> copier.blockFactory(blockFactory));
        }

        public WithItem<TBlock, TItem> extend(ItemFactory<TBlock, TItem> itemFactory) {
            return copy(copier -> copier.itemFactory(itemFactory));
        }

        @Override
        void register(IEventBus modBus, FurnitureSet furnitureSet) {
            super.register(modBus, furnitureSet);
            furnitureSet.registree.register(Registries.ITEM, furnitureSet.registrationName(registryName), registryName -> itemFactory.create(furnitureSet, furnitureSet.getOrThrow(this), itemProperties(furnitureSet).useBlockDescriptionPrefix().setId(ResourceKey.create(Registries.ITEM, registryName))));
        }

        @Override
        void registerDataGen(PackGenerator<?> generator, FurnitureSet furnitureSet) {
            super.registerDataGen(generator, furnitureSet);

            generator.providing(ProviderTypes.ITEM_TAGS, (context, provider) -> itemTags
                    .forEach(tag -> provider.tag(tag).withElement(furnitureSet.getOrThrow(this).asItem()))
            );
        }
    }
}
