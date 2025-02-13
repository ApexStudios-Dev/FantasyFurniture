package dev.apexstudios.fantasyfurniture.set.function;

import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import java.util.function.BiFunction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

@FunctionalInterface
public interface ItemFactory<TBlock extends Block, TItem extends Item> {
    TItem create(FurnitureSet furnitureSet, TBlock block, Item.Properties properties);

    static <TBlock extends Block, TItem extends Item> ItemFactory<TBlock, TItem> wrapping(BiFunction<TBlock, Item.Properties, TItem> factory) {
        return (furnitureSet, block, properties) -> factory.apply(block, properties);
    }
}
