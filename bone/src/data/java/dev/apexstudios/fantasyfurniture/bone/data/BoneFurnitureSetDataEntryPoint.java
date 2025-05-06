package dev.apexstudios.fantasyfurniture.bone.data;

import dev.apexstudios.apexcore.lib.data.ResourceGenerator;
import dev.apexstudios.fantasyfurniture.FantasyFurniture;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(FantasyFurniture.ID + "_bone")
public final class BoneFurnitureSetDataEntryPoint {
    public BoneFurnitureSetDataEntryPoint(IEventBus modBus) {
        ResourceGenerator.of(modBus, generator -> {
            var pack = generator.pack();

            SkeletonFurnitureSetDataEntryPoint.register(pack);
            WitherFurnitureSetDataEntryPoint.register(pack);
        });
    }
}
