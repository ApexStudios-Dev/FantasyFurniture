package dev.apexstudios.fantasyfurniture.royal.data;

import dev.apexstudios.apexcore.lib.data.ResourceGenerator;
import dev.apexstudios.fantasyfurniture.royal.RoyalFurnitureSet;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(RoyalFurnitureSet.ID)
public final class RoyalFurnitureSetDataEntryPoint {
    public RoyalFurnitureSetDataEntryPoint(IEventBus modBus) {
        ResourceGenerator.of(modBus, RoyalFurnitureSet.FURNITURE_SET::registerDataGen);
    }
}
