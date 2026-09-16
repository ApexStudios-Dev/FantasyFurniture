package dev.apexstudios.fantasyfurniture.data;

import dev.apexstudios.fantasyfurniture.common.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.common.station.FurnitureStationSetup;
import net.minecraft.ChatFormatting;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

final class FFLanguageProvider extends LanguageProvider {
    FFLanguageProvider(PackOutput output) {
        super(output, FantasyFurniture.ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addBlock(FurnitureStationSetup.BLOCK, "Furniture Station");
        add(FantasyFurniture.FURNITURE_PLANKS, "Planks (Furniture Input)");
        add(FantasyFurniture.FURNITURE_WOOL, "Wools (Furniture Input)");
        add(FantasyFurniture.FURNITURE_BRICKS, "Bricks (Furniture Input)");
        add(FurnitureStationSetup.BINDING_AGENT, "Furniture Binding Agents");
        add(FantasyFurniture.LOADING_ISSUE_KEY, "" + ChatFormatting.RED +
                ChatFormatting.BOLD +
                ChatFormatting.UNDERLINE +
                "No Furniture Set modules detected." +
                ChatFormatting.RESET +
                "\n\nFantasy's Furniture does nothing on it's own, " +
                ChatFormatting.BOLD +
                "at least 1" +
                ChatFormatting.RESET +
                " Furniture Set module must be installed."
        );
    }
}
