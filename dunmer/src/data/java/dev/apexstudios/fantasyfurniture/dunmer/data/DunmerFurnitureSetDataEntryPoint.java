package dev.apexstudios.fantasyfurniture.dunmer.data;

import dev.apexstudios.apexcore.lib.data.ResourceGenerator;
import dev.apexstudios.apexcore.lib.util.TagPair;
import dev.apexstudios.fantasyfurniture.FurnitureUtil;
import dev.apexstudios.fantasyfurniture.dunmer.DunmerFurnitureSet;
import net.minecraft.data.BlockFamily;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(DunmerFurnitureSet.ID)
public final class DunmerFurnitureSetDataEntryPoint {
    public DunmerFurnitureSetDataEntryPoint(IEventBus modBus) {
        ResourceGenerator.of(modBus, generator -> FurnitureUtil.registerDataGen(
                new FurnitureUtil.DataGenContext(
                        DunmerFurnitureSet.REGISTREE,
                        "Dunmer",
                        new BlockFamily.Builder(DunmerFurnitureSet.PLANKS.value())
                                .recipeGroupPrefix("dunmer")
                                .recipeUnlockedBy("has_planks")
                                .stairs(DunmerFurnitureSet.STAIRS.value())
                                .slab(DunmerFurnitureSet.SLAB.value())
                                .fence(DunmerFurnitureSet.FENCE.value())
                                .fenceGate(DunmerFurnitureSet.FENCE_GATE.value())
                                .trapdoor(DunmerFurnitureSet.TRAPDOOR.value())
                                .pressurePlate(DunmerFurnitureSet.PRESSURE_PLATE.value())
                                .sign(DunmerFurnitureSet.SIGN.sign().value(), DunmerFurnitureSet.SIGN.wall().value())
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
