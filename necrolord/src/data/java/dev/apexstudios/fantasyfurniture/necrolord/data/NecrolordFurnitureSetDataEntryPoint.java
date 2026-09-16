package dev.apexstudios.fantasyfurniture.necrolord.data;

import dev.apexstudios.fantasyfurniture.common.data.DataGenContext;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import dev.apexstudios.fantasyfurniture.necrolord.common.NecrolordFurnitureSet;
import net.minecraft.data.BlockFamily;
import net.minecraft.tags.BlockItemTagId;
import net.minecraft.tags.BlockItemTags;
import net.minecraft.tags.BlockTags;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;
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
                    BlockItemTags.DOORS,
                    BlockItemTags.STAIRS,
                    BlockItemTags.STONE_BUTTONS,
                    new BlockItemTagId(BlockTags.PRESSURE_PLATES, null),
                    BlockItemTags.TRAPDOORS,
                    new BlockItemTagId(Tags.Blocks.FENCES, Tags.Items.FENCES),
                    new BlockItemTagId(Tags.Blocks.FENCE_GATES, Tags.Items.FENCE_GATES),
                    BlockItemTags.SLABS
            ).registerBasicDataGen(event);

            event.createProvider(NFParticleProvider::new);
        });
    }
}
