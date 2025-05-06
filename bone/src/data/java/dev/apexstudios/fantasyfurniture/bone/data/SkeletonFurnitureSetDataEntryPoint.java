package dev.apexstudios.fantasyfurniture.bone.data;

import apexstudios.fantasyfurniture.bone.SkeletonFurnitureSet;
import dev.apexstudios.apexcore.lib.data.pack.ModPackGenerator;
import dev.apexstudios.apexcore.lib.util.TagPair;
import dev.apexstudios.fantasyfurniture.FurnitureUtil;
import net.minecraft.data.BlockFamily;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;

interface SkeletonFurnitureSetDataEntryPoint {
    static void register(ModPackGenerator generator) {
        FurnitureUtil.registerDataGen(
                new FurnitureUtil.DataGenContext(
                        SkeletonFurnitureSet.REGISTREE,
                        "Bone Skeleton",
                        new BlockFamily.Builder(SkeletonFurnitureSet.BRICKS.value())
                                .recipeGroupPrefix("skeleton")
                                .recipeUnlockedBy("has_bricks")
                                .stairs(SkeletonFurnitureSet.STAIRS.value())
                                .slab(SkeletonFurnitureSet.SLAB.value())
                                .fence(SkeletonFurnitureSet.FENCE.value())
                                .fenceGate(SkeletonFurnitureSet.FENCE_GATE.value())
                                .trapdoor(SkeletonFurnitureSet.TRAPDOOR.value())
                                .pressurePlate(SkeletonFurnitureSet.PRESSURE_PLATE.value())
                                .sign(SkeletonFurnitureSet.SIGN.sign().value(), SkeletonFurnitureSet.SIGN.wall().value())
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
