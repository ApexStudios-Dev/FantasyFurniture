package dev.apexstudios.fantasyfurniture.necrolord.data;

import dev.apexstudios.apexcore.lib.data.ResourceGenerator;
import dev.apexstudios.apexcore.lib.util.TagPair;
import dev.apexstudios.fantasyfurniture.FurnitureUtil;
import dev.apexstudios.fantasyfurniture.necrolord.NecrolordFurnitureSet;
import net.minecraft.data.BlockFamily;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(NecrolordFurnitureSet.ID)
public final class NecrolordFurnitureSetDataEntryPoint {
    public NecrolordFurnitureSetDataEntryPoint(IEventBus modBus) {
        ResourceGenerator.of(modBus, generator -> FurnitureUtil.registerDataGen(
                new FurnitureUtil.DataGenContext(
                        NecrolordFurnitureSet.REGISTREE,
                        "Necrolord",
                        new BlockFamily.Builder(NecrolordFurnitureSet.BRICKS.value())
                                .recipeGroupPrefix("necrolord")
                                .recipeUnlockedBy("has_bricks")
                                .stairs(NecrolordFurnitureSet.STAIRS.value())
                                .slab(NecrolordFurnitureSet.SLAB.value())
                                .fence(NecrolordFurnitureSet.FENCE.value())
                                .fenceGate(NecrolordFurnitureSet.FENCE_GATE.value())
                                .trapdoor(NecrolordFurnitureSet.TRAPDOOR.value())
                                .pressurePlate(NecrolordFurnitureSet.PRESSURE_PLATE.value())
                                .sign(NecrolordFurnitureSet.SIGN.sign().value(), NecrolordFurnitureSet.SIGN.wall().value())
                        .getFamily(),
                        BlockTags.MINEABLE_WITH_PICKAXE,
                        new TagPair(BlockTags.DOORS, ItemTags.DOORS),
                        BlockTags.STAIRS,
                        new TagPair(BlockTags.BUTTONS, ItemTags.BUTTONS),
                        new TagPair(BlockTags.PRESSURE_PLATES, null),
                        new TagPair(BlockTags.TRAPDOORS, ItemTags.TRAPDOORS),
                        new TagPair(BlockTags.FENCES, ItemTags.FENCES),
                        new TagPair(BlockTags.SLABS, ItemTags.SLABS)
                ), generator.pack()
        ));
    }
}
