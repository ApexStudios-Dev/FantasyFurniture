package dev.apexstudios.fantasyfurniture.nordic;

import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import dev.apexstudios.fantasyfurniture.set.FurnitureSetBuilder;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(NordicFurnitureSet.ID)
public class NordicFurnitureSet {
    public static final String ID = "fantasyfurniture_nordic";
    public static final Registree REGISTREE = new Registree(ID);

    public static final FurnitureSet FURNITURE_SET = FurnitureSet.create(REGISTREE, "nordic", FurnitureSetBuilder::withDefault);

    public NordicFurnitureSet(IEventBus modBus) {
        FURNITURE_SET.register(modBus);
        REGISTREE.registerEvents(modBus);
    }
}
