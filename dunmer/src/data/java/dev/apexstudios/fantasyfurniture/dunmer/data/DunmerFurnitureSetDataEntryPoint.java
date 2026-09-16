package dev.apexstudios.fantasyfurniture.dunmer.data;

import dev.apexstudios.apexcore.api.util.TagPair;
import dev.apexstudios.fantasyfurniture.common.data.DataGenContext;
import dev.apexstudios.fantasyfurniture.common.data.DataGenType;
import dev.apexstudios.fantasyfurniture.common.data.FurnitureBlockLootSubProvider;
import dev.apexstudios.fantasyfurniture.common.data.FurnitureBlockTagsProvider;
import dev.apexstudios.fantasyfurniture.common.data.FurnitureItemTagsProvider;
import dev.apexstudios.fantasyfurniture.common.data.FurnitureLanguageProviderUS;
import dev.apexstudios.fantasyfurniture.common.data.FurnitureRecipeProvider;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import dev.apexstudios.fantasyfurniture.dunmer.common.DunmerFurnitureSet;
import java.util.List;
import java.util.Set;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod(DunmerFurnitureSet.ID)
public final class DunmerFurnitureSetDataEntryPoint {
    public DunmerFurnitureSetDataEntryPoint(IEventBus modBus) {
        modBus.addListener(GatherDataEvent.Client.class, event -> {
            var context = new DataGenContext(
                    DunmerFurnitureSet.REGISTREE,
                    "Dunmer",
                    new BlockFamily.Builder(DunmerFurnitureSet.PLANKS.value())
                            .recipeGroupPrefix("dunmer")
                            .recipeUnlockedBy("has_" + FurnitureUtil.Names.PLANKS)
                            .stairs(DunmerFurnitureSet.STAIRS.value())
                            .slab(DunmerFurnitureSet.SLAB.value())
                            .fence(DunmerFurnitureSet.FENCE.value())
                            .fenceGate(DunmerFurnitureSet.FENCE_GATE.value())
                            .trapdoor(DunmerFurnitureSet.TRAPDOOR.value())
                            .pressurePlate(DunmerFurnitureSet.PRESSURE_PLATE.value())
                            .sign(DunmerFurnitureSet.SIGN.sign().value(), DunmerFurnitureSet.SIGN.wall().value())
                            .customHangingSign(DunmerFurnitureSet.HANGING_SIGN.sign().value(), DunmerFurnitureSet.HANGING_SIGN.wall().value())
                            .getFamily(),
                    BlockTags.MINEABLE_WITH_AXE,
                    new TagPair(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS),
                    BlockTags.WOODEN_STAIRS,
                    new TagPair(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS),
                    new TagPair(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES),
                    new TagPair(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS),
                    new TagPair(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES),
                    new TagPair(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS),
                    exclusions -> exclusions.put(DataGenType.MODEL, FurnitureUtil.Names.OVEN)
            );

            event.createReloadableRegistryObjects(new RegistrySetBuilder()
                    .add(Registries.LOOT_TABLE, registry -> new LootTableProvider(Set.of(), List.of(context.forLootTable(FurnitureBlockLootSubProvider::new))))
                    .add(context.forBootstrap(FurnitureRecipeProvider::new))
            );

            event.createProvider(context.fromOutput(FurnitureLanguageProviderUS::new));
            event.createProvider(context.fromOutput(DFModelProvider::new));
            event.createProvider(context.fromOutputLookup(FurnitureBlockTagsProvider::new));
            event.createProvider(context.fromOutputLookup(FurnitureItemTagsProvider::new));
            event.createProvider(output -> PackMetadataGenerator.forFeaturePack(output, Component.literal("Dunmer Furniture Set resources")));
        });
    }
}
