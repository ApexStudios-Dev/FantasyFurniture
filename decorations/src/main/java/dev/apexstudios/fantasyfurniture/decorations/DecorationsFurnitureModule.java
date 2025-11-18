package dev.apexstudios.fantasyfurniture.decorations;

import dev.apexstudios.fantasyfurniture.decorations.block.BerryBasketBlock;
import dev.apexstudios.fantasyfurniture.decorations.util.DecorationUtil;
import dev.apexstudios.fantasyfurniture.util.FurnitureUtil;
import dev.apexstudios.registree.api.Registree;
import dev.apexstudios.registree.api.holder.DeferredBlock;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(DecorationsFurnitureModule.ID)
public class DecorationsFurnitureModule {
    public static final String ID = "fantasyfurniture_decorations";
    public static final Registree REGISTREE = Registree.create(ID);

    public static final DeferredBlock<BerryBasketBlock> BERRY_BASKET = DecorationUtil.berryBasket(REGISTREE, "berry_basket", BerryBasketBlock::new);
    public static final DeferredBlock<BerryBasketBlock> BLUEBERRY_BASKET = DecorationUtil.berryBasket(REGISTREE, "blueberry_basket", BerryBasketBlock::new);
    public static final DeferredBlock<BerryBasketBlock> STRAWBERRY_BASKET = DecorationUtil.berryBasket(REGISTREE, "strawberry_basket", BerryBasketBlock::new);
    public static final DeferredBlock<BerryBasketBlock> SWEETBERRY_BASKET = DecorationUtil.berryBasket(REGISTREE, "sweetberry_basket", BerryBasketBlock::new);

    public static final ResourceKey<CreativeModeTab> CREATIVE_MODE_TAB = FurnitureUtil.creativeModeTab(REGISTREE, BERRY_BASKET);

    public DecorationsFurnitureModule(IEventBus modBus) {
        REGISTREE.registerEvents(modBus);
    }

    public static ResourceLocation identifier(String identifier) {
        return ResourceLocation.fromNamespaceAndPath(ID, identifier);
    }

    public static String id(String identifier) {
        return ID + ResourceLocation.NAMESPACE_SEPARATOR + identifier;
    }
}
