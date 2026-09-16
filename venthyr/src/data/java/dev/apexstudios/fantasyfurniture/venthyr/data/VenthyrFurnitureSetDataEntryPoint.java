package dev.apexstudios.fantasyfurniture.venthyr.data;

import dev.apexstudios.apexcore.api.util.ApexUtil;
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
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.tags.BlockItemTagId;
import net.minecraft.tags.BlockItemTags;
import net.minecraft.tags.BlockTags;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;
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
                    BlockItemTags.WOODEN_DOORS,
                    BlockItemTags.WOODEN_STAIRS,
                    BlockItemTags.WOODEN_BUTTONS,
                    BlockItemTags.WOODEN_PRESSURE_PLATES,
                    BlockItemTags.WOODEN_TRAPDOORS,
                    new BlockItemTagId(Tags.Blocks.FENCES_WOODEN, Tags.Items.FENCES_WOODEN),
                    new BlockItemTagId(Tags.Blocks.FENCE_GATES_WOODEN, Tags.Items.FENCE_GATES_WOODEN),
                    BlockItemTags.WOODEN_SLABS
            );

            event.createReloadableRegistryObjects(new RegistrySetBuilder()
                    .add(Registries.LOOT_TABLE, new LootTableProvider(Set.of(), List.of(context.forLootTable(VFBlockLootSubProvider::new))))
                    .add(context.forBootstrap(VFRecipeProvider::new))
            );

            event.createProvider(context.fromOutput(VFLanguageProvider::new));
            event.createProvider(context.fromOutput(VFModelProvider::new));
            event.createProvider(context.fromOutputLookup(VFBlockTagsProvider::new));
            event.createProvider(context.fromOutputLookup(FurnitureItemTagsProvider::new));
            event.createProvider(output -> ApexUtil.createMetadataProvider(output, Component.literal("Venthyr Furniture Set resources"), PackType.SERVER_DATA));
        });
    }
}
