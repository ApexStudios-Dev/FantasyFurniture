package dev.apexstudios.fantasyfurniture;

import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.fantasyfurniture.set.BlockTypes;
import dev.apexstudios.fantasyfurniture.station.ClientboundSyncFurnitureStation;
import dev.apexstudios.fantasyfurniture.station.FurnitureStationSetup;
import java.util.Map;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

@Mod(FantasyFurniture.ID)
public final class FantasyFurniture {
    public static final String ID = "fantasyfurniture";
    public static final Registree REGISTREE = new Registree(ID);
    public static final TagKey<Item> FURNITURE_PLANKS = REGISTREE.tag(Registries.ITEM, "furniture_planks");
    public static final TagKey<Item> FURNITURE_WOOL = REGISTREE.tag(Registries.ITEM, "furniture_wool");
    public static final TagKey<Item> FURNITURE_BRICKS = REGISTREE.tag(Registries.ITEM, "furniture_bricks");

    private static final Map<String, CtmPack> CTM_PACKS = Map.of(
            "athena", new CtmPack("ctm-athena", "Athena CTM"),
            "fusion", new CtmPack("ctm-fusion", "Fusion CTM"),
            "ctm", new CtmPack("ctm", "CTM")
    );

    public FantasyFurniture(IEventBus modBus) {
        REGISTREE.registerEvents(modBus);
        FurnitureStationSetup.register(modBus);
        FurnitureBlockEntities.register(modBus);
        FurnitureMenus.register(modBus);
        BlockTypes.register();

        modBus.addListener(RegisterPayloadHandlersEvent.class, event -> event
                .registrar("1.0")
                .playToClient(ClientboundSyncFurnitureStation.TYPE, ClientboundSyncFurnitureStation.STREAM_CODEC, ClientboundSyncFurnitureStation::handle)
        );

        modBus.addListener(AddPackFindersEvent.class, event -> CTM_PACKS.entrySet().stream()
                .filter(entry -> ModList.get().isLoaded(entry.getKey()))
                .map(Map.Entry::getValue)
                .forEach(pack -> event.addPackFinders(
                        identifier("packs/" + pack.packId),
                        PackType.CLIENT_RESOURCES,
                        Component.literal(pack.packName),
                        PackSource.BUILT_IN,
                        false,
                        Pack.Position.TOP
                ))
        );
    }

    public static ResourceLocation identifier(String identifier) {
        return REGISTREE.registryName(identifier);
    }

    public static String id(String identifier) {
        return ID + ResourceLocation.NAMESPACE_SEPARATOR + identifier;
    }

    private record CtmPack(String packId, String packName) { }
}
