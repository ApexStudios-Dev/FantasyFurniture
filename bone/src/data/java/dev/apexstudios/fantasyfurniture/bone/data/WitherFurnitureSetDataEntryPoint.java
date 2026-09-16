package dev.apexstudios.fantasyfurniture.bone.data;

import dev.apexstudios.fantasyfurniture.bone.common.WitherFurnitureSet;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod(WitherFurnitureSet.ID)
public final class WitherFurnitureSetDataEntryPoint {
    public WitherFurnitureSetDataEntryPoint(IEventBus modBus) {
        modBus.addListener(GatherDataEvent.Client.class, event -> BoneFurnitureSetDataEntryPoint.register(event, WitherFurnitureSet.FURNITURE_SET, "Wither"));
    }
}
