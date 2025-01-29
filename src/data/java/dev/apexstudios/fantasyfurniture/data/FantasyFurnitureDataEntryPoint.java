package dev.apexstudios.fantasyfurniture.data;

import dev.apexstudios.apexcore.lib.data.ResourceGenerator;
import dev.apexstudios.fantasyfurniture.FantasyFurniture;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(value = FantasyFurniture.ID, dist = Dist.CLIENT)
public final class FantasyFurnitureDataEntryPoint {
    public FantasyFurnitureDataEntryPoint(IEventBus modBus) {
        ResourceGenerator.of(modBus, generator -> {
            generator.pack();
        });
    }
}
