package dev.apexstudios.fantasyfurniture.decorations.util;

import dev.apexstudios.fantasyfurniture.util.FurnitureUtil;
import dev.apexstudios.registree.api.Registree;
import dev.apexstudios.registree.api.holder.DeferredBlock;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public interface DecorationUtil {
    Supplier<BlockBehaviour.Properties> BERRY_BASKET_PROPERTIES = FurnitureUtil.PLANK_PROPERTIES;

    static <TBlock extends Block> DeferredBlock<TBlock> berryBasket(Registree registree, String identifier, Function<BlockBehaviour.Properties, TBlock> factory) {
        var block = registree.registerBlock(identifier, factory, BERRY_BASKET_PROPERTIES);
        registree.registerSimpleBlockItem(block);
        return block;
    }
}
