package dev.apexstudios.fantasyfurniture.set;

import dev.apexstudios.fantasyfurniture.set.function.BlockFactory;
import dev.apexstudios.fantasyfurniture.set.function.ItemFactory;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.apache.commons.lang3.function.Consumers;

public sealed interface BlockType<TBlock extends Block> permits BlockType.NoItem, BlockType.WithItem, BlockTypeImpl {
    String registryName();

    BlockBehaviour.Properties blockProperties(FurnitureSet furnitureSet);

    static <TBlock extends Block, TItem extends Item> BlockType.WithItem<TBlock, TItem> withItem(String registryName, BlockFactory<TBlock> blockFactory, ItemFactory<TBlock, TItem> itemFactory, Consumer<BlockTypeBuilder.WithItem<TBlock, TItem>> builder) {
        return register(registryName, blockFactory, withItemFactory(itemFactory), BlockTypeImpl.WithItem::new, builder);
    }

    static <TBlock extends Block, TItem extends Item> BlockType.WithItem<TBlock, TItem> withItem(String registryName, BlockFactory<TBlock> blockFactory, ItemFactory<TBlock, TItem> itemFactory) {
        return withItem(registryName, blockFactory, itemFactory, Consumers.nop());
    }

    static <TBlock extends Block> BlockType.WithItem<TBlock, BlockItem> withItem(String registryName, BlockFactory<TBlock> blockFactory, Consumer<BlockTypeBuilder.WithItem<TBlock, BlockItem>> builder) {
        return withItem(registryName, blockFactory, (furnitureSet, block, properties) -> new BlockItem(block, properties), builder);
    }

    static <TBlock extends Block> BlockType.WithItem<TBlock, BlockItem> withItem(String registryName, BlockFactory<TBlock> blockFactory) {
        return withItem(registryName, blockFactory, (furnitureSet, block, properties) -> new BlockItem(block, properties), Consumers.nop());
    }

    static <TBlock extends Block> BlockType.NoItem<TBlock> noItem(String registryName, BlockFactory<TBlock> blockFactory, Consumer<BlockTypeBuilder.NoItem<TBlock>> builder) {
        return register(registryName, blockFactory, BlockTypeBuilderImpl.NoItem::new, BlockTypeImpl.NoItem::new, builder);
    }

    static <TBlock extends Block> BlockType.NoItem<TBlock> noItem(String registryName, BlockFactory<TBlock> blockFactory) {
        return noItem(registryName, blockFactory, Consumers.nop());
    }

    private static <TBlock extends Block, TType extends BlockType<TBlock>, TBuilder extends BlockTypeBuilder<TBlock, TBuilder>, TBuilderImpl extends TBuilder> TType register(String registryName, BlockFactory<TBlock> blockFactory, BiFunction<String, BlockFactory<TBlock>, TBuilderImpl> builderFactory, Function<TBuilderImpl, TType> factory, Consumer<TBuilder> consumer) {
        var builder = builderFactory.apply(registryName, blockFactory);
        consumer.accept(builder);
        var blockType = factory.apply(builder);

        if(!BlockTypeImpl.REGISTRY.add(blockType))
            throw new IllegalStateException("Duplicate BlockType registration: " + blockType.registryName());

        return blockType;
    }

    private static <TBlock extends Block, TItem extends Item> BiFunction<String, BlockFactory<TBlock>, BlockTypeBuilderImpl.WithItem<TBlock, TItem>> withItemFactory(ItemFactory<TBlock, TItem> itemFactory) {
        return (registryName, blockFactory) -> new BlockTypeBuilderImpl.WithItem<>(registryName, blockFactory, itemFactory);
    }

    sealed interface NoItem<TBlock extends Block> extends BlockType<TBlock> permits BlockTypeImpl.NoItem {
        NoItem<TBlock> extend(BlockFactory<TBlock> blockFactory);
    }

    sealed interface WithItem<TBlock extends Block, TItem extends Item> extends BlockType<TBlock> permits BlockTypeImpl.WithItem {
        Item.Properties itemProperties(FurnitureSet furnitureSet);

        WithItem<TBlock, TItem> extend(BlockFactory<TBlock> blockFactory, ItemFactory<TBlock, TItem> itemFactory);

        WithItem<TBlock, TItem> extend(BlockFactory<TBlock> blockFactory);

        WithItem<TBlock, TItem> extend(ItemFactory<TBlock, TItem> itemFactory);
    }
}
