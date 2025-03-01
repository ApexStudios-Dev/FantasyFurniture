package dev.apexstudios.fantasyfurniture.dunmer.data;

import dev.apexstudios.apexcore.lib.data.ResourceGenerator;
import dev.apexstudios.fantasyfurniture.dunmer.DunmerFurnitureSet;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(DunmerFurnitureSet.ID)
public final class DunmerFurnitureSetDataEntryPoint {
    public DunmerFurnitureSetDataEntryPoint(IEventBus modBus) {
        ResourceGenerator.of(modBus, DunmerFurnitureSet.FURNITURE_SET::registerDataGen);
    }
}
