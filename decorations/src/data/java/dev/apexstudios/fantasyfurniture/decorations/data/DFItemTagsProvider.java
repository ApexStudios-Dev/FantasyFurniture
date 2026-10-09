package dev.apexstudios.fantasyfurniture.decorations.data;

import dev.apexstudios.apexcore.api.data.ItemTagsProvider;
import dev.apexstudios.fantasyfurniture.decorations.common.DecorationsFurnitureModule;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;

final class DFItemTagsProvider extends ItemTagsProvider {
    DFItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags) {
        super(output, lookupProvider, blockTags, DecorationsFurnitureModule.ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        copy(Tags.Blocks.DYED, Tags.Items.DYED);
        copy(Tags.Blocks.CHAINS, Tags.Items.CHAINS);
    }
}
