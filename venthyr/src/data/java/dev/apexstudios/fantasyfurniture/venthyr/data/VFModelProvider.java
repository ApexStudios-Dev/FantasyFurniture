package dev.apexstudios.fantasyfurniture.venthyr.data;

import dev.apexstudios.fantasyfurniture.common.data.DataGenContext;
import dev.apexstudios.fantasyfurniture.common.data.FurnitureModelProvider;
import dev.apexstudios.fantasyfurniture.venthyr.common.VenthyrFurnitureSet;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.data.PackOutput;

final class VFModelProvider extends FurnitureModelProvider {
    VFModelProvider(PackOutput output, DataGenContext furniture) {
        super(output, furniture);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        super.registerModels(blockModels, itemModels);

        createTableModel(VenthyrFurnitureSet.TABLE_CLOTH.value(), blockModels);
    }
}
