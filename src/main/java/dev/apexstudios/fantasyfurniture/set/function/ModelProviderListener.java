package dev.apexstudios.fantasyfurniture.set.function;

import dev.apexstudios.apexcore.lib.data.provider.context.ProviderListenerContext;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.world.level.block.Block;

@FunctionalInterface
public interface ModelProviderListener<TBlock extends Block> {
    void accept(ProviderListenerContext context, BlockModelGenerators models, FurnitureSet furnitureSet, TBlock block);
}
