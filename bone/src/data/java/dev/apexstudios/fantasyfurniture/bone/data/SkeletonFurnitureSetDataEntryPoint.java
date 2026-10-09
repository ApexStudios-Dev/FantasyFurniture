package dev.apexstudios.fantasyfurniture.bone.data;

import dev.apexstudios.fantasyfurniture.bone.common.SkeletonFurnitureSet;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(SkeletonFurnitureSet.ID)
public final class SkeletonFurnitureSetDataEntryPoint {
    public SkeletonFurnitureSetDataEntryPoint(IEventBus modBus) {
        BoneFurnitureSetDataEntryPoint.register(modBus, SkeletonFurnitureSet.FURNITURE_SET);
    }
}
