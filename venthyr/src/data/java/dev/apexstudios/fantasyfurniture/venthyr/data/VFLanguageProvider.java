package dev.apexstudios.fantasyfurniture.venthyr.data;

import dev.apexstudios.fantasyfurniture.common.data.DataGenContext;
import dev.apexstudios.fantasyfurniture.common.data.FurnitureLanguageProviderUS;
import dev.apexstudios.fantasyfurniture.venthyr.common.VenthyrFurnitureSet;
import net.minecraft.data.PackOutput;

final class VFLanguageProvider extends FurnitureLanguageProviderUS {
    public VFLanguageProvider(PackOutput output, DataGenContext furniture) {
        super(output, furniture);
    }

    @Override
    protected void addTranslations() {
        super.addTranslations();

        addBlock(VenthyrFurnitureSet.TABLE_CLOTH, furniture.englishName() + " Table Cloth");
    }
}
