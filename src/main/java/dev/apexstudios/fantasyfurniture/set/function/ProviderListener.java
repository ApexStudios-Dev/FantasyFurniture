package dev.apexstudios.fantasyfurniture.set.function;

import dev.apexstudios.apexcore.lib.data.provider.context.ProviderListenerContext;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import net.minecraft.world.level.block.Block;

@FunctionalInterface
public interface ProviderListener<TProvider, TBlock extends Block> {
    void accept(ProviderListenerContext context, TProvider provider, FurnitureSet furnitureSet, TBlock block);

    default ProviderListener<TProvider, TBlock> andThen(ProviderListener<TProvider, TBlock> other) {
        return (context, provider, furnitureSet, block) -> {
            accept(context, provider, furnitureSet, block);
            other.accept(context, provider, furnitureSet, block);
        };
    }
}
