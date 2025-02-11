package dev.apexstudios.fantasyfurniture.set.function;

import dev.apexstudios.apexcore.lib.data.provider.RecipeProvider;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import net.minecraft.world.item.Item;

@FunctionalInterface
public interface RecipeListener<TItem extends Item> {
    void accept(RecipeProvider provider, FurnitureSet furnitureSet, TItem item);
}
