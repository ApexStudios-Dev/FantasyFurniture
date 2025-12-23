package dev.apexstudios.fantasyfurniture.bone.common;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(WitherFurnitureSet.ID)
public final class WitherFurnitureSet {
    public static final String ID = BoneFurnitureSet.ID + "_wither";
    public static final BoneFurnitureSet FURNITURE_SET = new BoneFurnitureSet(ID, "wither");

    public WitherFurnitureSet(IEventBus modBus) {
        FURNITURE_SET.register(modBus);
    }
}
