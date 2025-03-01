package dev.apexstudios.fantasyfurniture.dunmer;

import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(DunmerFurnitureSet.ID)
public class DunmerFurnitureSet {
    public static final String ID = "fantasyfurniture_dunmer";
    public static final Registree REGISTREE = new Registree(ID);

    public static final FurnitureSet FURNITURE_SET = FurnitureSet.createWoodLike(REGISTREE, "dunmer");

    public DunmerFurnitureSet(IEventBus modBus) {
        FURNITURE_SET.register(modBus);
        REGISTREE.registerEvents(modBus);
    }
}
