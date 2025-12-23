package dev.apexstudios.fantasyfurniture.bone.common;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(SkeletonFurnitureSet.ID)
public final class SkeletonFurnitureSet {
    public static final String ID = BoneFurnitureSet.ID + "_skeleton";
    public static final BoneFurnitureSet FURNITURE_SET = new BoneFurnitureSet(ID, "skeleton");

    public SkeletonFurnitureSet(IEventBus modBus) {
        FURNITURE_SET.register(modBus);
    }
}
