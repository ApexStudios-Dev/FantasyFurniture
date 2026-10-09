package dev.apexstudios.fantasyfurniture.necrolord.data;

import dev.apexstudios.fantasyfurniture.common.data.DataGenContext;
import dev.apexstudios.fantasyfurniture.necrolord.common.NecrolordFurnitureSet;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(NecrolordFurnitureSet.ID)
public final class NecrolordFurnitureSetDataEntryPoint {
    public NecrolordFurnitureSetDataEntryPoint(IEventBus modBus) {
        DataGenContext.stone(NecrolordFurnitureSet.REGISTREE, "necrolord")
                .extraData((event, context) -> event.createProvider(NFParticleProvider::new))
                .build(modBus);
    }
}
