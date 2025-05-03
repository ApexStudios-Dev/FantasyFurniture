package dev.apexstudios.fantasyfurniture.decorations.data;

import dev.apexstudios.apexcore.lib.data.ResourceGenerator;
import dev.apexstudios.fantasyfurniture.decorations.DecorationsFurnitureModule;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(DecorationsFurnitureModule.ID)
public final class DecorationsFurnitureModuleDataEntryPoint {
    public DecorationsFurnitureModuleDataEntryPoint(IEventBus modBus) {
        ResourceGenerator.of(modBus, generator -> {

        });
    }
}
