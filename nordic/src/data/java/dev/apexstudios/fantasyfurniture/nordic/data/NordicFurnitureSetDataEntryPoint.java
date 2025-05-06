package dev.apexstudios.fantasyfurniture.nordic.data;

import dev.apexstudios.apexcore.lib.data.ResourceGenerator;
import dev.apexstudios.apexcore.lib.util.TagPair;
import dev.apexstudios.fantasyfurniture.FurnitureUtil;
import dev.apexstudios.fantasyfurniture.nordic.NordicFurnitureSet;
import net.minecraft.data.BlockFamily;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(NordicFurnitureSet.ID)
public final class NordicFurnitureSetDataEntryPoint {
    public NordicFurnitureSetDataEntryPoint(IEventBus modBus) {
        ResourceGenerator.of(modBus, generator -> FurnitureUtil.registerDataGen(
                new FurnitureUtil.DataGenContext(
                        NordicFurnitureSet.REGISTREE,
                        "Nordic",
                        new BlockFamily.Builder(NordicFurnitureSet.PLANKS.value())
                                .recipeGroupPrefix("nordic")
                                .recipeUnlockedBy("has_planks")
                                .stairs(NordicFurnitureSet.STAIRS.value())
                                .slab(NordicFurnitureSet.SLAB.value())
                                .fence(NordicFurnitureSet.FENCE.value())
                                .fenceGate(NordicFurnitureSet.FENCE_GATE.value())
                                .trapdoor(NordicFurnitureSet.TRAPDOOR.value())
                                .pressurePlate(NordicFurnitureSet.PRESSURE_PLATE.value())
                                .sign(NordicFurnitureSet.SIGN.sign().value(), NordicFurnitureSet.SIGN.wall().value())
                        .getFamily(),
                        BlockTags.MINEABLE_WITH_AXE,
                        new TagPair(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS),
                        BlockTags.WOODEN_STAIRS,
                        new TagPair(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS),
                        new TagPair(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES),
                        new TagPair(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS),
                        new TagPair(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES),
                        new TagPair(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS)
                ), generator.pack()
        ));
    }
}
