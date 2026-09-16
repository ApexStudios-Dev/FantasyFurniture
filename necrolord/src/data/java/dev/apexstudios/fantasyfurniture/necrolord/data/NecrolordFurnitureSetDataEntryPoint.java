package dev.apexstudios.fantasyfurniture.necrolord.data;

import dev.apexstudios.apexcore.api.util.TagPair;
import dev.apexstudios.fantasyfurniture.common.data.DataGenContext;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import dev.apexstudios.fantasyfurniture.necrolord.common.NecrolordFurnitureSet;
import net.minecraft.data.BlockFamily;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod(NecrolordFurnitureSet.ID)
public final class NecrolordFurnitureSetDataEntryPoint {
    public NecrolordFurnitureSetDataEntryPoint(IEventBus modBus) {
        modBus.addListener(GatherDataEvent.Client.class, event -> {
            new DataGenContext(
                    NecrolordFurnitureSet.REGISTREE,
                    "Necrolord",
                    new BlockFamily.Builder(NecrolordFurnitureSet.BRICKS.value())
                            .recipeGroupPrefix("necrolord")
                            .recipeUnlockedBy("has_" + FurnitureUtil.Names.BRICKS)
                            .stairs(NecrolordFurnitureSet.STAIRS.value())
                            .slab(NecrolordFurnitureSet.SLAB.value())
                            .fence(NecrolordFurnitureSet.FENCE.value())
                            .fenceGate(NecrolordFurnitureSet.FENCE_GATE.value())
                            .trapdoor(NecrolordFurnitureSet.TRAPDOOR.value())
                            .pressurePlate(NecrolordFurnitureSet.PRESSURE_PLATE.value())
                            .sign(NecrolordFurnitureSet.SIGN.sign().value(), NecrolordFurnitureSet.SIGN.wall().value())
                            .customHangingSign(NecrolordFurnitureSet.HANGING_SIGN.sign().value(), NecrolordFurnitureSet.HANGING_SIGN.wall().value())
                            .getFamily(),
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    new TagPair(BlockTags.DOORS, ItemTags.WOODEN_DOORS),
                    BlockTags.STAIRS,
                    new TagPair(BlockTags.BUTTONS, ItemTags.WOODEN_BUTTONS),
                    new TagPair(BlockTags.PRESSURE_PLATES, null),
                    new TagPair(BlockTags.TRAPDOORS, ItemTags.WOODEN_TRAPDOORS),
                    new TagPair(BlockTags.FENCES, ItemTags.WOODEN_FENCES),
                    new TagPair(BlockTags.SLABS, ItemTags.WOODEN_SLABS)
            ).registerBasicDataGen(event);

            event.createProvider(NFParticleProvider::new);
        });
    }
}
