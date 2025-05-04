package dev.apexstudios.fantasyfurniture.decorations;

import dev.apexstudios.apexcore.lib.registree.holder.DeferredBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.BerryBasketBlock;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public interface DecoBlocks {
    Supplier<BlockBehaviour.Properties> PROPERTIES = () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS);

    DeferredBlock<BerryBasketBlock> BERRY_BASKET = register("berry_basket", BerryBasketBlock::new, BlockBehaviour.Properties::noOcclusion);
    DeferredBlock<BerryBasketBlock> BERRY_BASKET_STRAWBERRY = register("berry_basket_strawberry", BerryBasketBlock::new, BlockBehaviour.Properties::noOcclusion);
    DeferredBlock<BerryBasketBlock> BERRY_BASKET_SWEETBERRY = register("berry_basket_sweetberry", BerryBasketBlock::new, BlockBehaviour.Properties::noOcclusion);
    DeferredBlock<BerryBasketBlock> BERRY_BASKET_BLUEBERRY = register("berry_basket_blueberry", BerryBasketBlock::new, BlockBehaviour.Properties::noOcclusion);

    static void register() {

    }

    private static <TBlock extends Block> DeferredBlock<TBlock> register(String name, Function<BlockBehaviour.Properties, TBlock> factory, UnaryOperator<BlockBehaviour.Properties> properties) {
        return DecorationsFurnitureModule.REGISTREE.registerBlock(name, factory, () -> properties.apply(PROPERTIES.get()));
    }

    private static <TBlock extends Block> DeferredBlock<TBlock> register(String name, Function<BlockBehaviour.Properties, TBlock> factory) {
        return register(name, factory, UnaryOperator.identity());
    }
}
