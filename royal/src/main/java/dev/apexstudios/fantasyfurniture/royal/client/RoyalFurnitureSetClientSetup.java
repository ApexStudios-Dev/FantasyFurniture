package dev.apexstudios.fantasyfurniture.royal.client;

import dev.apexstudios.apexcore.api.block.Dyeable;
import dev.apexstudios.fantasyfurniture.royal.common.RoyalFurnitureSet;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@Mod(value = RoyalFurnitureSet.ID, dist = Dist.CLIENT)
public final class RoyalFurnitureSetClientSetup {
    public RoyalFurnitureSetClientSetup(IEventBus modBus) {
        modBus.addListener(RegisterColorHandlersEvent.BlockTintSources.class, event -> Dyeable.registerBlockColor(RoyalFurnitureSet.REGISTREE, event));
    }
}
