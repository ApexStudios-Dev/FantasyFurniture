package dev.apexstudios.fantasyfurniture.set;

import dev.apexstudios.fantasyfurniture.set.function.BlockFactory;
import dev.apexstudios.fantasyfurniture.set.function.ItemFactory;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.apache.commons.lang3.function.Consumers;

public abstract sealed class BlockTypeCopier<TBlock extends Block, TType extends BlockType<TBlock>, TBuilder extends BlockTypeBuilder<TBlock, TType, TBuilder>, TSelf extends BlockTypeCopier<TBlock, TType, TBuilder, TSelf>> {
    protected final String registryName;
    protected Function<String, String> registryNameMutator = Function.identity();
    protected BlockFactory<TBlock> blockFactory;
    protected Consumer<TBuilder> builder = Consumers.nop();

    private BlockTypeCopier(BlockType<TBlock> blockType) {
        registryName = blockType.registryName;
        blockFactory = blockType.blockFactory;

        builder(builder -> {
            builder.blockProperties(blockType.blockPropertiesModifier)
                    .initialBlockProperties(blockType.initialBlockProperties)
                    .blockEntity(blockType.blockEntityType)
                    .onRegister(blockType.onRegister, false)
                    .onRegister(blockType.onRegisterEnqueued, true);

            builder.providerListeners.putAll(blockType.providerListeners);
        });
    }

    public TSelf registryName(UnaryOperator<String> registryNameMutator) {
        this.registryNameMutator = registryNameMutator;
        return (TSelf) this;
    }

    public TSelf registryName(String registryName) {
        return registryName($ -> registryName);
    }

    public TSelf blockFactory(BlockFactory<TBlock> blockFactory) {
        this.blockFactory = blockFactory;
        return (TSelf) this;
    }

    public TSelf builder(Consumer<TBuilder> builder) {
        this.builder = this.builder.andThen(builder);
        return (TSelf) this;
    }

    abstract TType compile();

    public static final class NoItem<TBlock extends Block> extends BlockTypeCopier<TBlock, BlockType.NoItem<TBlock>, BlockTypeBuilder.NoItem<TBlock>, NoItem<TBlock>> {
        NoItem(BlockType<TBlock> blockType) {
            super(blockType);
        }

        @Override
        BlockType.NoItem<TBlock> compile() {
            return BlockType.noItem(registryNameMutator.apply(registryName), blockFactory, builder);
        }
    }

    public static final class WithItem<TBlock extends Block, TItem extends Item> extends BlockTypeCopier<TBlock, BlockType.WithItem<TBlock, TItem>, BlockTypeBuilder.WithItem<TBlock, TItem>, WithItem<TBlock, TItem>> {
        private ItemFactory<TBlock, TItem> itemFactory;

        WithItem(BlockType.WithItem<TBlock, TItem> blockType) {
            super(blockType);

            itemFactory = blockType.itemFactory;

            builder(builder -> builder
                    .itemProperties(blockType.itemPropertiesModifier)
                    .initialItemProperties(blockType.initialItemProperties)
            );
        }

        public WithItem<TBlock, TItem> itemFactory(ItemFactory<TBlock, TItem> itemFactory) {
            this.itemFactory = itemFactory;
            return this;
        }

        @Override
        BlockType.WithItem<TBlock, TItem> compile() {
            return BlockType.withItem(registryNameMutator.apply(registryName), blockFactory, itemFactory, builder);
        }
    }
}
