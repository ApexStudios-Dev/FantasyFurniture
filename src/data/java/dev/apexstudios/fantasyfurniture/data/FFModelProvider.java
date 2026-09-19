package dev.apexstudios.fantasyfurniture.data;

import dev.apexstudios.fantasyfurniture.common.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.common.station.FurnitureStationSetup;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.data.PackOutput;

final class FFModelProvider extends ModelProvider {
    FFModelProvider(PackOutput output) {
        super(output, FantasyFurniture.ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        blockModels.createNonTemplateHorizontalBlock(FurnitureStationSetup.BLOCK.value());
    }
}
