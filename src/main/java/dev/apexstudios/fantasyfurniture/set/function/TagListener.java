package dev.apexstudios.fantasyfurniture.set.function;

import dev.apexstudios.apexcore.lib.data.provider.tag.IntrusiveTagProvider;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;

@FunctionalInterface
public interface TagListener<TRegistry, TEntry extends TRegistry> {
    void accept(IntrusiveTagProvider<TRegistry> provider, FurnitureSet furnitureSet, TEntry entry);
}
