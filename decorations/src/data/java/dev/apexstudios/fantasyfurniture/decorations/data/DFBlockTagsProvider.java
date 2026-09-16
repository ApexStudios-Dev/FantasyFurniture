package dev.apexstudios.fantasyfurniture.decorations.data;

import dev.apexstudios.apexcore.api.block.Dyeable;
import dev.apexstudios.apexcore.api.multiblock.MultiBlock;
import dev.apexstudios.apexcore.api.util.ApexTags;
import dev.apexstudios.fantasyfurniture.decorations.common.DecorationsFurnitureModule;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

final class DFBlockTagsProvider extends BlockTagsProvider {
    DFBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, DecorationsFurnitureModule.ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        DecorationsFurnitureModule.REGISTREE.listElements(Registries.BLOCK).map(Holder::value).forEach(block -> {
            tag(BlockTags.MINEABLE_WITH_AXE).add(block.builtInRegistryHolder().key());

            if(block instanceof Dyeable) {
                tag(Tags.Blocks.DYED).add(block.builtInRegistryHolder().key());
            }

            if(block instanceof MultiBlock) {
                tag(Tags.Blocks.RELOCATION_NOT_SUPPORTED).add(block.builtInRegistryHolder().key());
            }
        });

        tag(Tags.Blocks.CHAINS).add(DecorationsFurnitureModule.BRONZE_CHAIN.getKey());
        tag(Tags.Blocks.CHAINS).add(DecorationsFurnitureModule.BRONZE_CHAIN.getKey());

        tag(ApexTags.Blocks.SHEARS_EFFICIENT).add(DecorationsFurnitureModule.PLUSHIE_BLOCK.getKey());
    }
}
