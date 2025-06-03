package dev.apexstudios.fantasyfurniture;

import dev.apexstudios.fantasyfurniture.station.FurnitureStationClientSetup;
import net.neoforged.fml.common.Mod;

@Mod(FantasyFurniture.ID)
public final class FantasyFurnitureClient {
    public FantasyFurnitureClient() {
        FurnitureStationClientSetup.register();
    }
}
