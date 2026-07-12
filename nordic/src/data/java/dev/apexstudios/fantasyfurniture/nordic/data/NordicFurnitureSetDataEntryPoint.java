package dev.apexstudios.fantasyfurniture.nordic.data;

import dev.apexstudios.apexcore.api.data.ResourceGenerator;
import dev.apexstudios.fantasyfurniture.common.ctm.CtmPacks;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureClientDataUtil;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureDataUtil;
import dev.apexstudios.fantasyfurniture.nordic.common.NordicFurnitureSet;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(NordicFurnitureSet.ID)
public final class NordicFurnitureSetDataEntryPoint {
    public NordicFurnitureSetDataEntryPoint(IEventBus modBus) {
        ResourceGenerator.of(modBus, generator -> {
            var pack = generator.pack();
            var context = FurnitureDataUtil.createContext(NordicFurnitureSet.REGISTREE, "Nordic", "nordic");

            context.register(pack);
            FurnitureClientDataUtil.registerDataGen(context, pack);
            CtmPacks.registerDataGen(NordicFurnitureSet.REGISTREE, pack, false);
        });
    }
}
