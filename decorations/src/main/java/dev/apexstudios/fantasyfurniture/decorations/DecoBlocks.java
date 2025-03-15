package dev.apexstudios.fantasyfurniture.decorations;

import dev.apexstudios.apexcore.lib.registree.holder.DeferredBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.BerryBasketBlock;
import java.util.function.Function;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public interface DecoBlocks {
    DeferredBlock<BerryBasketBlock> BERRY_BASKET_EMPTY = berryBasket("berry_basket_empty");
    DeferredBlock<BerryBasketBlock> BERRY_BASKET_SWEETBERRY = berryBasket("berry_basket_sweetberry");
    DeferredBlock<BerryBasketBlock> BERRY_BASKET_BLUEBERRY = berryBasket("berry_basket_blueberry");
    DeferredBlock<BerryBasketBlock> BERRY_BASKET_STRAWBERRY = berryBasket("berry_basket_strawberry");

    static void register() {

    }

    private static <TBlock extends Block> DeferredBlock<TBlock> register(String registryName, Function<BlockBehaviour.Properties, TBlock> factory) {
        return DecorationsModule.REGISTREE.registerBlock(registryName, factory, () -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS));
    }

    private static DeferredBlock<BerryBasketBlock> berryBasket(String registryName) {
        return register(registryName, BerryBasketBlock::new);
    }
}
