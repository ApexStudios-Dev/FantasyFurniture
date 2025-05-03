package dev.apexstudios.fantasyfurniture.decorations;

import dev.apexstudios.apexcore.lib.registree.Registree;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(DecorationsFurnitureModule.ID)
public class DecorationsFurnitureModule {
    public static final String ID = "fantasyfurniture_decorations";
    public static final Registree REGISTREE = new Registree(ID);

    public DecorationsFurnitureModule(IEventBus modBus) {
        REGISTREE.registerEvents(modBus);
    }
}
