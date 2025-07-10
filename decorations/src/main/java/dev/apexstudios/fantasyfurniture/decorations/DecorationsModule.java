package dev.apexstudios.fantasyfurniture.decorations;

import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.apexcore.lib.registree.holder.DeferredBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.BerryBasketBlock;
import dev.apexstudios.fantasyfurniture.util.FurnitureUtil;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(DecorationsModule.ID)
public class DecorationsModule {
    public static final String ID = "fantasyfurniture_decorations";
    public static final Registree REGISTREE = new Registree(ID);

    public static final DeferredBlock<BerryBasketBlock> EMPTY_BERRY_BASKET = berryBasket("empty_berry");
    public static final DeferredBlock<BerryBasketBlock> BLUEBERRY_BASKET = berryBasket("blueberry");
    public static final DeferredBlock<BerryBasketBlock> STRAWBERRY_BASKET = berryBasket("strawberry");
    public static final DeferredBlock<BerryBasketBlock> SWEETBERRY_BASKET = berryBasket("sweetberry");

    public static final ResourceKey<CreativeModeTab> CREATIVE_MODE_TAB = FurnitureUtil.creativeModeTab(REGISTREE, EMPTY_BERRY_BASKET);

    public DecorationsModule(IEventBus modBus) {
        REGISTREE.registerEvents(modBus);
    }

    private static DeferredBlock<BerryBasketBlock> berryBasket(String type) {
        var block = REGISTREE.registerBlock(type + "_basket", BerryBasketBlock::new, () -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS));
        REGISTREE.registerSimpleBlockItem(block);
        return block;
    }
}
