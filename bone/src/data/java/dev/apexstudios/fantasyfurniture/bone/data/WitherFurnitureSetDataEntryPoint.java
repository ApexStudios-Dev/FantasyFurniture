package dev.apexstudios.fantasyfurniture.bone.data;

import apexstudios.fantasyfurniture.bone.WitherFurnitureSet;
import dev.apexstudios.apexcore.lib.data.pack.ModPackGenerator;
import dev.apexstudios.apexcore.lib.util.TagPair;
import dev.apexstudios.fantasyfurniture.FurnitureUtil;
import net.minecraft.data.BlockFamily;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;

interface WitherFurnitureSetDataEntryPoint {
    static void register(ModPackGenerator generator) {
        FurnitureUtil.registerDataGen(
                new FurnitureUtil.DataGenContext(
                        WitherFurnitureSet.REGISTREE,
                        "Bone Wither",
                        new BlockFamily.Builder(WitherFurnitureSet.BRICKS.value())
                                .recipeGroupPrefix("wither")
                                .recipeUnlockedBy("has_bricks")
                                .stairs(WitherFurnitureSet.STAIRS.value())
                                .slab(WitherFurnitureSet.SLAB.value())
                                .fence(WitherFurnitureSet.FENCE.value())
                                .fenceGate(WitherFurnitureSet.FENCE_GATE.value())
                                .trapdoor(WitherFurnitureSet.TRAPDOOR.value())
                                .pressurePlate(WitherFurnitureSet.PRESSURE_PLATE.value())
                                .sign(WitherFurnitureSet.SIGN.sign().value(), WitherFurnitureSet.SIGN.wall().value())
                        .getFamily(),
                        BlockTags.MINEABLE_WITH_PICKAXE,
                        new TagPair(BlockTags.DOORS, ItemTags.DOORS),
                        BlockTags.STAIRS,
                        new TagPair(BlockTags.BUTTONS, ItemTags.BUTTONS),
                        new TagPair(BlockTags.PRESSURE_PLATES, null),
                        new TagPair(BlockTags.TRAPDOORS, ItemTags.TRAPDOORS),
                        new TagPair(BlockTags.FENCES, ItemTags.FENCES),
                        new TagPair(BlockTags.SLABS, ItemTags.SLABS)
                ), generator
        );
    }
}
