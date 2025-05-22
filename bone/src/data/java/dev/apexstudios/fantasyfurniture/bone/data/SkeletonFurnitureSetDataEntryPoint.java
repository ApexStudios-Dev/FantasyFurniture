package dev.apexstudios.fantasyfurniture.bone.data;

import dev.apexstudios.apexcore.lib.data.ResourceGenerator;
import dev.apexstudios.fantasyfurniture.bone.SkeletonFurnitureSet;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(SkeletonFurnitureSet.ID)
public final class SkeletonFurnitureSetDataEntryPoint {
    public SkeletonFurnitureSetDataEntryPoint(IEventBus modBus) {
        ResourceGenerator.of(modBus, generator -> BoneFurnitureSetDataEntryPoint.register(generator, SkeletonFurnitureSet.get(), "Skeleton"));
    }
}
