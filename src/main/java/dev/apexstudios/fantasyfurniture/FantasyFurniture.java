package dev.apexstudios.fantasyfurniture;

import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.fantasyfurniture.station.ClientboundSyncFurnitureStation;
import dev.apexstudios.fantasyfurniture.station.FurnitureStationSetup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

@Mod(FantasyFurniture.ID)
public final class FantasyFurniture {
    public static final String ID = "fantasyfurniture";
    public static final Registree REGISTREE = new Registree(ID);
    public static final TagKey<Item> FURNITURE_PLANKS = REGISTREE.tag(Registries.ITEM, "furniture_planks");
    public static final TagKey<Item> FURNITURE_WOOL = REGISTREE.tag(Registries.ITEM, "furniture_wool");

    public FantasyFurniture(IEventBus modBus) {
        REGISTREE.registerEvents(modBus);
        FurnitureStationSetup.register(modBus);
        FurnitureBlockEntities.register(modBus);
        FurnitureMenus.register(modBus);

        modBus.addListener(RegisterPayloadHandlersEvent.class, event -> event
                .registrar("1.0")
                .playToClient(ClientboundSyncFurnitureStation.TYPE, ClientboundSyncFurnitureStation.STREAM_CODEC, ClientboundSyncFurnitureStation::handle)
        );
    }

    public static ResourceLocation identifier(String identifier) {
        return REGISTREE.registryName(identifier);
    }

    public static String id(String identifier) {
        return ID + ResourceLocation.NAMESPACE_SEPARATOR + identifier;
    }
}
