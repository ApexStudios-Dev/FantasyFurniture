package dev.apexstudios.fantasyfurniture.nordic.data;

import dev.apexstudios.apexcore.lib.data.ResourceGenerator;
import dev.apexstudios.fantasyfurniture.nordic.NordicFurnitureSet;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(NordicFurnitureSet.ID)
public final class NordicFurnitureSetDataEntryPoint {
    public NordicFurnitureSetDataEntryPoint(IEventBus modBus) {
        ResourceGenerator.of(modBus, NordicFurnitureSet.FURNITURE_SET::registerDataGen);
    }
}
