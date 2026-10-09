package dev.apexstudios.fantasyfurniture.nordic.data;

import dev.apexstudios.fantasyfurniture.common.data.DataGenContext;
import dev.apexstudios.fantasyfurniture.nordic.common.NordicFurnitureSet;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(NordicFurnitureSet.ID)
public final class NordicFurnitureSetDataEntryPoint {
    public NordicFurnitureSetDataEntryPoint(IEventBus modBus) {
        DataGenContext.wooden(NordicFurnitureSet.REGISTREE, "nordic")
                .build(modBus);
    }
}
