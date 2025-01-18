package dev.apexstudios.fantasyfurniture;

import dev.apexstudios.apexcore.core.seat.SeatSetup;
import dev.apexstudios.apexcore.lib.registree.Registree;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(FantasyFurniture.ID)
public final class FantasyFurniture {
    public static final String ID = "fantasyfurniture";
    public static final Registree REGISTREE = new Registree(ID);

    public FantasyFurniture(IEventBus modBus) {
        REGISTREE.registerEvents(modBus);
        FurnitureBlockEntities.register(modBus);
        FurnitureMenus.register(modBus);
        SeatSetup.register(modBus);
    }

    public static ResourceLocation identifier(String identifier) {
        return REGISTREE.registryName(identifier);
    }

    public static String id(String identifier) {
        return ID + ResourceLocation.NAMESPACE_SEPARATOR + identifier;
    }
}
