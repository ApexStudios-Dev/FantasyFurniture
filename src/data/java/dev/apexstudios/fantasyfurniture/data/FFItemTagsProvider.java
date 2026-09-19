package dev.apexstudios.fantasyfurniture.data;

import dev.apexstudios.fantasyfurniture.common.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.common.station.FurnitureStationSetup;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.references.ItemIds;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

class FFItemTagsProvider extends ItemTagsProvider {
    FFItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, FantasyFurniture.ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(FantasyFurniture.FURNITURE_PLANKS);
        tag(FantasyFurniture.FURNITURE_WOOL);
        tag(ItemTags.PLANKS).addTag(FantasyFurniture.FURNITURE_PLANKS);
        tag(ItemTags.WOOL).addTag(FantasyFurniture.FURNITURE_WOOL);
        tag(FurnitureStationSetup.BINDING_AGENT).add(ItemIds.CLAY_BALL);
    }
}
