package dev.apexstudios.fantasyfurniture.venthyr.data;

import dev.apexstudios.apexcore.lib.data.ResourceGenerator;
import dev.apexstudios.fantasyfurniture.venthyr.VenthyrFurnitureSet;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(VenthyrFurnitureSet.ID)
public final class VenthyrFurnitureSetDataEntryPoint {
    public VenthyrFurnitureSetDataEntryPoint(IEventBus modBus) {
        ResourceGenerator.of(modBus, VenthyrFurnitureSet.FURNITURE_SET::registerDataGen);
    }
}
