package dev.apexstudios.fantasyfurniture.bone.client;

import dev.apexstudios.fantasyfurniture.bone.common.WitherFurnitureSet;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(value = WitherFurnitureSet.ID, dist = Dist.CLIENT)
public final class WitherFurnitureSetClientEntryPoint {
    public WitherFurnitureSetClientEntryPoint(IEventBus modBus) {
        BoneFurnitureSetClient.register(WitherFurnitureSet.FURNITURE_SET, modBus);
    }
}
