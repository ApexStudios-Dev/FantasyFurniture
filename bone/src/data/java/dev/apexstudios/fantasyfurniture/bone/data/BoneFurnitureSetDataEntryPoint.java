package dev.apexstudios.fantasyfurniture.bone.data;

import apexstudios.fantasyfurniture.bone.BoneFurnitureSet;
import dev.apexstudios.apexcore.lib.data.ResourceGenerator;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(BoneFurnitureSet.ID)
public final class BoneFurnitureSetDataEntryPoint {
    public BoneFurnitureSetDataEntryPoint(IEventBus modBus) {
        ResourceGenerator.of(modBus, generator -> {
            BoneFurnitureSet.SKELETON.registerDataGen(generator);
            BoneFurnitureSet.WITHER.registerDataGen(generator);
        });
    }
}
