package dev.apexstudios.fantasyfurniture.bone.data;

import dev.apexstudios.fantasyfurniture.bone.common.SkeletonFurnitureSet;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod(SkeletonFurnitureSet.ID)
public final class SkeletonFurnitureSetDataEntryPoint {
    public SkeletonFurnitureSetDataEntryPoint(IEventBus modBus) {
        modBus.addListener(GatherDataEvent.Client.class, event -> BoneFurnitureSetDataEntryPoint.register(event, SkeletonFurnitureSet.FURNITURE_SET, "Skeleton"));
    }
}
