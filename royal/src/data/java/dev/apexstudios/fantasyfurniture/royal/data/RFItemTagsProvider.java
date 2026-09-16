package dev.apexstudios.fantasyfurniture.royal.data;

import dev.apexstudios.apexcore.api.block.Dyeable;
import dev.apexstudios.fantasyfurniture.common.data.DataGenContext;
import dev.apexstudios.fantasyfurniture.common.data.FurnitureItemTagsProvider;
import dev.apexstudios.fantasyfurniture.royal.common.RoyalFurnitureSet;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.Tags;

final class RFItemTagsProvider extends FurnitureItemTagsProvider {
    RFItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, DataGenContext furniture) {
        super(output, lookupProvider, furniture);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void addTags(HolderLookup.Provider registries) {
        super.addTags(registries);

        Dyeable.dyeableItems(RoyalFurnitureSet.REGISTREE).forEach(item -> tag(Tags.Items.DYED).add(item.builtInRegistryHolder().key()));
    }
}
