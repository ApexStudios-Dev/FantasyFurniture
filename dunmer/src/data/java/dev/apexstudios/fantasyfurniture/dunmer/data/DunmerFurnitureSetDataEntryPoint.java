package dev.apexstudios.fantasyfurniture.dunmer.data;

import dev.apexstudios.apexcore.api.util.ApexUtil;
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
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.tags.BlockItemTagId;
import net.minecraft.tags.BlockItemTags;
import net.minecraft.tags.BlockTags;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;
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
                    BlockItemTags.WOODEN_DOORS,
                    BlockItemTags.WOODEN_STAIRS,
                    BlockItemTags.WOODEN_BUTTONS,
                    BlockItemTags.WOODEN_PRESSURE_PLATES,
                    BlockItemTags.WOODEN_TRAPDOORS,
                    new BlockItemTagId(Tags.Blocks.FENCES_WOODEN, Tags.Items.FENCES_WOODEN),
                    new BlockItemTagId(Tags.Blocks.FENCE_GATES_WOODEN, Tags.Items.FENCE_GATES_WOODEN),
                    BlockItemTags.WOODEN_SLABS,
                    exclusions -> exclusions.put(DataGenType.MODEL, FurnitureUtil.Names.OVEN)
            );

            event.createReloadableRegistryObjects(new RegistrySetBuilder()
                    .add(Registries.LOOT_TABLE, new LootTableProvider(Set.of(), List.of(context.forLootTable(FurnitureBlockLootSubProvider::new))))
                    .add(context.forBootstrap(FurnitureRecipeProvider::new))
            );

            event.createProvider(context.fromOutput(FurnitureLanguageProviderUS::new));
            event.createProvider(context.fromOutput(DFModelProvider::new));
            event.createProvider(context.fromOutputLookup(FurnitureBlockTagsProvider::new));
            event.createProvider(context.fromOutputLookup(FurnitureItemTagsProvider::new));
            event.createProvider(output -> ApexUtil.createMetadataProvider(output, Component.literal("Dunmer Furniture Set resources"), PackType.SERVER_DATA));
        });
    }
}
