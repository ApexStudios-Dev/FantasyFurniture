package dev.apexstudios.fantasyfurniture.venthyr.data;

import dev.apexstudios.fantasyfurniture.common.data.DataGenContext;
import dev.apexstudios.fantasyfurniture.common.data.FurnitureBlockLootSubProvider;
import dev.apexstudios.fantasyfurniture.venthyr.common.VenthyrFurnitureSet;

final class VFBlockLootSubProvider extends FurnitureBlockLootSubProvider {
    VFBlockLootSubProvider(Context output, DataGenContext furniture) {
        super(output, furniture);
    }

    @Override
    protected void generate() {
        super.generate();

        dropSelf(VenthyrFurnitureSet.TABLE_CLOTH.value());
    }
}
