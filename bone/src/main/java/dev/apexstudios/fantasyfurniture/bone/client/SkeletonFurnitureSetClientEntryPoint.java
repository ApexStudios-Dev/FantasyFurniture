package dev.apexstudios.fantasyfurniture.bone.client;

import dev.apexstudios.fantasyfurniture.bone.common.SkeletonFurnitureSet;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(value = SkeletonFurnitureSet.ID, dist = Dist.CLIENT)
public final class SkeletonFurnitureSetClientEntryPoint {
    public SkeletonFurnitureSetClientEntryPoint(IEventBus modBus) {
        BoneFurnitureSetClient.register(SkeletonFurnitureSet.FURNITURE_SET, modBus);
    }
}
