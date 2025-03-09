package dev.apexstudios.fantasyfurniture.necrolord.data;

import dev.apexstudios.apexcore.lib.data.ProviderTypes;
import dev.apexstudios.apexcore.lib.data.ResourceGenerator;
import dev.apexstudios.fantasyfurniture.necrolord.NecrolordFurnitureSet;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(NecrolordFurnitureSet.ID)
public final class NordicFurnitureSetDataEntryPoint {
    public NordicFurnitureSetDataEntryPoint(IEventBus modBus) {
        ResourceGenerator.of(modBus, generator -> {
            NecrolordFurnitureSet.FURNITURE_SET.registerDataGen(generator);

            generator.pack().providing(ProviderTypes.PARTICLES, (context, provider) -> provider
                    .sprite(NecrolordFurnitureSet.FLAME_PARTICLE.value(), NecrolordFurnitureSet.REGISTREE.registryName("flame"))
            );
        });
    }
}
