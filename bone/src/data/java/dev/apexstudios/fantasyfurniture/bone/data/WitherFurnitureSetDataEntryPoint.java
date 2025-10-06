package dev.apexstudios.fantasyfurniture.bone.data;

import dev.apexstudios.apexcore.lib.data.ResourceGenerator;
import dev.apexstudios.fantasyfurniture.bone.WitherFurnitureSet;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(WitherFurnitureSet.ID)
public final class WitherFurnitureSetDataEntryPoint {
    public WitherFurnitureSetDataEntryPoint(IEventBus modBus) {
        ResourceGenerator.of(modBus, generator -> BoneFurnitureSetDataEntryPoint.register(generator, WitherFurnitureSet.FURNITURE_SET, "Wither"));
    }
}
