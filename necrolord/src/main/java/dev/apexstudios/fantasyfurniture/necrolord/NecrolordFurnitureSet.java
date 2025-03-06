package dev.apexstudios.fantasyfurniture.necrolord;

import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(NecrolordFurnitureSet.ID)
public class NecrolordFurnitureSet {
    public static final String ID = "fantasyfurniture_necrolord";
    public static final Registree REGISTREE = new Registree(ID);

    public static final FurnitureSet FURNITURE_SET = FurnitureSet.createStoneLike(REGISTREE, "necrolord");

    public NecrolordFurnitureSet(IEventBus modBus) {
        FURNITURE_SET.register(modBus);
        REGISTREE.registerEvents(modBus);
    }
}
