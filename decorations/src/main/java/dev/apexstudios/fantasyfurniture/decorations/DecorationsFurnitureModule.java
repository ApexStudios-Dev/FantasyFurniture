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

    public static final DeferredBlock<BerryBasketBlock> BERRY_BASKET = DecorationUtil.berryBasket(REGISTREE, "berry", BerryBasketBlock::new);
    public static final DeferredBlock<BerryBasketBlock> BLUEBERRY_BASKET = DecorationUtil.berryBasket(REGISTREE, "blueberry", BerryBasketBlock::new);
    public static final DeferredBlock<BerryBasketBlock> STRAWBERRY_BASKET = DecorationUtil.berryBasket(REGISTREE, "strawberry", BerryBasketBlock::new);
    public static final DeferredBlock<BerryBasketBlock> SWEETBERRY_BASKET = DecorationUtil.berryBasket(REGISTREE, "sweetberry", BerryBasketBlock::new);

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

// TODO: Porting List
// - [X] Berry Baskets
// - [ ] Bolts of Cloth *
// - [ ] Book Stack
// - [ ] Bowls
// - [ ] Tankards
// - [ ] Mushrooms
// - [ ] Coin Stacks *
// - [ ] Muffins
// - [ ] Paper Stack *
// - [ ] Cookie Jar
// - [ ] Brewing Cauldron
// - [ ] Floating Tomes
// - [ ] Gravestone
// - [ ] Hanging Herbs
// - [ ] Spider Webs
// - [ ] Stackable Pumpkins
// - [ ] Bronze Chain
// - [ ] Potion Bottles *
// - [ ] Fairy Lights
// - [ ] Present Stacks
// - [ ] Snowballs
// - [ ] Stocking
//
// Nordic
// - [ ] Boiled Creme Treats
// - [ ] Sweetrolls
// - [ ] Mead Bottles *
// - [ ] Soul Gems *
//
// Venthyr
// - [ ] Foods *
// - [ ] Tea Set *
// - [ ] Platter
// - [ ] Widow Bloom
// - [ ] Tomes *
// - [ ] Chalices
// - [ ] Candles *
// - [ ] Banner
//
// Dunmer
// - [ ] Pottery *
//
// Bone
// - [ ] Chalices
// - [ ] Pile
// - [ ] Skull Blossoms
//
// Royal
// - [ ] Crown
// - [ ] Candelabra
// - [ ] Chalices
// - [ ] Cushioned Crown
// - [ ] Food *
// - [ ] Platter
// - [ ] Floor Cushion
// - [ ] Wall Mirrors