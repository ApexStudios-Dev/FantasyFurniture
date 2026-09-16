package dev.apexstudios.fantasyfurniture.venthyr.data;

import dev.apexstudios.apexcore.api.util.TagPair;
import dev.apexstudios.fantasyfurniture.common.data.DataGenContext;
import dev.apexstudios.fantasyfurniture.common.data.FurnitureItemTagsProvider;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import dev.apexstudios.fantasyfurniture.venthyr.common.VenthyrFurnitureSet;
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

@Mod(VenthyrFurnitureSet.ID)
public final class VenthyrFurnitureSetDataEntryPoint {
    public VenthyrFurnitureSetDataEntryPoint(IEventBus modBus) {
        modBus.addListener(GatherDataEvent.Client.class, event -> {
            var context = new DataGenContext(
                    VenthyrFurnitureSet.REGISTREE,
                    "Venthyr",
                    new BlockFamily.Builder(VenthyrFurnitureSet.PLANKS.value())
                            .recipeGroupPrefix("venthyr")
                            .recipeUnlockedBy("has_" + FurnitureUtil.Names.PLANKS)
                            .stairs(VenthyrFurnitureSet.STAIRS.value())
                            .slab(VenthyrFurnitureSet.SLAB.value())
                            .fence(VenthyrFurnitureSet.FENCE.value())
                            .fenceGate(VenthyrFurnitureSet.FENCE_GATE.value())
                            .trapdoor(VenthyrFurnitureSet.TRAPDOOR.value())
                            .pressurePlate(VenthyrFurnitureSet.PRESSURE_PLATE.value())
                            .sign(VenthyrFurnitureSet.SIGN.sign().value(), VenthyrFurnitureSet.SIGN.wall().value())
                            .customHangingSign(VenthyrFurnitureSet.HANGING_SIGN.sign().value(), VenthyrFurnitureSet.HANGING_SIGN.wall().value())
                            .getFamily(),
                    BlockTags.MINEABLE_WITH_AXE,
                    new TagPair(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS),
                    BlockTags.WOODEN_STAIRS,
                    new TagPair(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS),
                    new TagPair(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES),
                    new TagPair(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS),
                    new TagPair(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES),
                    new TagPair(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS)
            );

            event.createReloadableRegistryObjects(new RegistrySetBuilder()
                    .add(Registries.LOOT_TABLE, new LootTableProvider(Set.of(), List.of(context.forLootTable(VFBlockLootSubProvider::new))))
                    .add(context.forBootstrap(VFRecipeProvider::new))
            );

            event.createProvider(context.fromOutput(VFLanguageProvider::new));
            event.createProvider(context.fromOutput(VFModelProvider::new));
            event.createProvider(context.fromOutputLookup(VFBlockTagsProvider::new));
            event.createProvider(context.fromOutputLookup(FurnitureItemTagsProvider::new));
            event.createProvider(output -> PackMetadataGenerator.forFeaturePack(output, Component.literal("Venthyr Furniture Set resources")));
        });
    }
}
