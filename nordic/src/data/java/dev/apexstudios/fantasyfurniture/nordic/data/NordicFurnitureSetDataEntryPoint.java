package dev.apexstudios.fantasyfurniture.nordic.data;

import dev.apexstudios.fantasyfurniture.common.data.DataGenContext;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import dev.apexstudios.fantasyfurniture.nordic.common.NordicFurnitureSet;
import net.minecraft.data.BlockFamily;
import net.minecraft.tags.BlockItemTagId;
import net.minecraft.tags.BlockItemTags;
import net.minecraft.tags.BlockTags;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod(NordicFurnitureSet.ID)
public final class NordicFurnitureSetDataEntryPoint {
    public NordicFurnitureSetDataEntryPoint(IEventBus modBus) {
        modBus.addListener(GatherDataEvent.Client.class, event -> new DataGenContext(
                NordicFurnitureSet.REGISTREE,
                "Nordic",
                new BlockFamily.Builder(NordicFurnitureSet.PLANKS.value())
                        .recipeGroupPrefix("nordic")
                        .recipeUnlockedBy("has_" + FurnitureUtil.Names.PLANKS)
                        .stairs(NordicFurnitureSet.STAIRS.value())
                        .slab(NordicFurnitureSet.SLAB.value())
                        .fence(NordicFurnitureSet.FENCE.value())
                        .fenceGate(NordicFurnitureSet.FENCE_GATE.value())
                        .trapdoor(NordicFurnitureSet.TRAPDOOR.value())
                        .pressurePlate(NordicFurnitureSet.PRESSURE_PLATE.value())
                        .sign(NordicFurnitureSet.SIGN.sign().value(), NordicFurnitureSet.SIGN.wall().value())
                        .customHangingSign(NordicFurnitureSet.HANGING_SIGN.sign().value(), NordicFurnitureSet.HANGING_SIGN.wall().value())
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
        ).registerBasicDataGen(event));
    }
}
