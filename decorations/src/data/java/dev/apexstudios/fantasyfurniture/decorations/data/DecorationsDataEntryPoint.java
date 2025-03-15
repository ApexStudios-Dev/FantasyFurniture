package dev.apexstudios.fantasyfurniture.decorations.data;

import dev.apexstudios.apexcore.lib.data.ResourceGenerator;
import dev.apexstudios.fantasyfurniture.decorations.DecorationsModule;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(DecorationsModule.ID)
public final class DecorationsDataEntryPoint {
    public DecorationsDataEntryPoint(IEventBus modBus) {
        ResourceGenerator.of(modBus, generator -> {
            generator.pack();
        });
    }
}
