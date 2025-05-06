package dev.apexstudios.fantasyfurniture.royal.data;

import dev.apexstudios.apexcore.lib.data.ResourceGenerator;
import dev.apexstudios.apexcore.lib.util.TagPair;
import dev.apexstudios.fantasyfurniture.FurnitureUtil;
import dev.apexstudios.fantasyfurniture.royal.RoyalFurnitureSet;
import net.minecraft.data.BlockFamily;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(RoyalFurnitureSet.ID)
public final class RoyalFurnitureSetDataEntryPoint {
    public RoyalFurnitureSetDataEntryPoint(IEventBus modBus) {
        ResourceGenerator.of(modBus, generator -> FurnitureUtil.registerDataGen(
                new FurnitureUtil.DataGenContext(
                        RoyalFurnitureSet.REGISTREE,
                        "Royal",
                        new BlockFamily.Builder(RoyalFurnitureSet.BRICKS.value())
                                .recipeGroupPrefix("royal")
                                .recipeUnlockedBy("has_bricks")
                                .stairs(RoyalFurnitureSet.STAIRS.value())
                                .slab(RoyalFurnitureSet.SLAB.value())
                                .fence(RoyalFurnitureSet.FENCE.value())
                                .fenceGate(RoyalFurnitureSet.FENCE_GATE.value())
                                .trapdoor(RoyalFurnitureSet.TRAPDOOR.value())
                                .pressurePlate(RoyalFurnitureSet.PRESSURE_PLATE.value())
                                .sign(RoyalFurnitureSet.SIGN.sign().value(), RoyalFurnitureSet.SIGN.wall().value())
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
