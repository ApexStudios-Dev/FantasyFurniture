package dev.apexstudios.fantasyfurniture.decorations.data;

import dev.apexstudios.apexcore.api.block.Dyeable;
import dev.apexstudios.fantasyfurniture.decorations.common.DecorationsFurnitureModule;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

final class DFItemTagsProvider extends ItemTagsProvider {
    DFItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, DecorationsFurnitureModule.ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        Dyeable.dyeableItems(DecorationsFurnitureModule.REGISTREE)
                .map(ItemLike::asItem)
                .forEach(block -> tag(Tags.Items.DYED).add(block.builtInRegistryHolder().key()));

        tag(Tags.Items.CHAINS).add(DecorationsFurnitureModule.BRONZE_CHAIN.value().asItem().builtInRegistryHolder().key());
    }
}
