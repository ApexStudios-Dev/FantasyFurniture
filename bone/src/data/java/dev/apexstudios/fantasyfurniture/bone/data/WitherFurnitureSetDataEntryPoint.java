package dev.apexstudios.fantasyfurniture.bone.data;

import dev.apexstudios.fantasyfurniture.bone.common.WitherFurnitureSet;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(WitherFurnitureSet.ID)
public final class WitherFurnitureSetDataEntryPoint {
    public WitherFurnitureSetDataEntryPoint(IEventBus modBus) {
        BoneFurnitureSetDataEntryPoint.register(modBus, WitherFurnitureSet.FURNITURE_SET);
    }
}
