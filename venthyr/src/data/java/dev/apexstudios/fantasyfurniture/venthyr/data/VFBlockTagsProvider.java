package dev.apexstudios.fantasyfurniture.venthyr.data;

import dev.apexstudios.fantasyfurniture.common.data.DataGenContext;
import dev.apexstudios.fantasyfurniture.common.data.FurnitureBlockTagsProvider;
import dev.apexstudios.fantasyfurniture.venthyr.common.VenthyrFurnitureSet;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;

final class VFBlockTagsProvider extends FurnitureBlockTagsProvider {
    VFBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, DataGenContext furniture) {
        super(output, lookupProvider, furniture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        super.addTags(registries);

        tag(VenthyrFurnitureSet.TABLE_CLOTH.value(), furniture.mineableTag());
    }
}
