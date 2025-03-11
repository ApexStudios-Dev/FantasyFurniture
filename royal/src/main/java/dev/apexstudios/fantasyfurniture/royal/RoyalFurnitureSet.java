package dev.apexstudios.fantasyfurniture.royal;

import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalWoolBlock;
import dev.apexstudios.fantasyfurniture.set.BlockTypes;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import dev.apexstudios.fantasyfurniture.set.function.BlockFactory;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.DyeColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(RoyalFurnitureSet.ID)
public class RoyalFurnitureSet {
    public static final String ID = "fantasyfurniture_royal";
    public static final Registree REGISTREE = new Registree(ID);
    public static final FurnitureSet FURNITURE_SET = FurnitureSet.createStoneLike(REGISTREE, "royal", $ -> $
            .remove(BlockTypes.FENCE_GATE)
            .with(BlockTypes.WOOL.copy($$ -> $$
                    .blockFactory(BlockFactory.wrapping(RoyalWoolBlock::new))
                    .builder($$$ -> $$$
                            .model(() -> RoyalFurnitureSetClientSetup::woolModel)
                            .itemProperties(properties -> properties.component(DataComponents.BASE_COLOR, DyeColor.WHITE))
                    )
            ))
    );

    public RoyalFurnitureSet(IEventBus modBus) {
        FURNITURE_SET.register(modBus);
        REGISTREE.registerEvents(modBus);
    }
}
