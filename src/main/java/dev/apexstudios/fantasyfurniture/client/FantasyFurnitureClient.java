package dev.apexstudios.fantasyfurniture.client;

import dev.apexstudios.fantasyfurniture.common.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.common.station.FurnitureStationClientSetup;
import net.neoforged.fml.common.Mod;

@Mod(FantasyFurniture.ID)
public final class FantasyFurnitureClient {
    public FantasyFurnitureClient() {
        FurnitureStationClientSetup.register();
    }
}
