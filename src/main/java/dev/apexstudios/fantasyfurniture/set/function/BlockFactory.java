package dev.apexstudios.fantasyfurniture.set.function;

import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import java.util.function.Function;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

@FunctionalInterface
public interface BlockFactory<TBlock extends Block> {
    TBlock create(FurnitureSet furnitureSet, BlockBehaviour.Properties properties);

    static <TBlock extends Block> BlockFactory<TBlock> wrapping(Function<BlockBehaviour.Properties, TBlock> factory) {
        return (furnitureSet, properties) -> factory.apply(properties);
    }
}
