package dev.apexstudios.fantasyfurniture.set.function;

import dev.apexstudios.apexcore.lib.data.provider.loot.BlockLootSubProvider;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import net.minecraft.world.level.block.Block;

@FunctionalInterface
public interface BlockLootTableListener<TBlock extends Block> {
    void accept(BlockLootSubProvider blocks, FurnitureSet furnitureSet, TBlock block);
}
