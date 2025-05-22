package apexstudios.fantasyfurniture.bone;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(value = SkeletonFurnitureSet.ID, dist = Dist.CLIENT)
public final class SkeletonFurnitureSetClientEntryPoint {
    public SkeletonFurnitureSetClientEntryPoint(IEventBus modBus) {
        BoneFurnitureSetClient.register(SkeletonFurnitureSet.get(), modBus);
    }
}
