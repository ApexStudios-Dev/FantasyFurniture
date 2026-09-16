package dev.apexstudios.fantasyfurniture.royal.data;

import dev.apexstudios.apexcore.api.util.ApexUtil;
import dev.apexstudios.fantasyfurniture.common.data.DataGenContext;
import dev.apexstudios.fantasyfurniture.common.data.DataGenType;
import dev.apexstudios.fantasyfurniture.common.data.FurnitureBlockLootSubProvider;
import dev.apexstudios.fantasyfurniture.common.data.FurnitureLanguageProviderUS;
import dev.apexstudios.fantasyfurniture.common.data.FurnitureRecipeProvider;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import dev.apexstudios.fantasyfurniture.royal.common.RoyalFurnitureSet;
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

@Mod(RoyalFurnitureSet.ID)
public final class RoyalFurnitureSetDataEntryPoint {
    public RoyalFurnitureSetDataEntryPoint(IEventBus modBus) {
        modBus.addListener(GatherDataEvent.Client.class, event -> {
            var context = new DataGenContext(
                    RoyalFurnitureSet.REGISTREE,
                    "Royal",
                    new BlockFamily.Builder(RoyalFurnitureSet.BRICKS.value())
                            .recipeGroupPrefix("royal")
                            .recipeUnlockedBy("has_" + FurnitureUtil.Names.BRICKS)
                            .stairs(RoyalFurnitureSet.STAIRS.value())
                            .slab(RoyalFurnitureSet.SLAB.value())
                            .fence(RoyalFurnitureSet.FENCE.value())
                            .fenceGate(RoyalFurnitureSet.FENCE_GATE.value())
                            .trapdoor(RoyalFurnitureSet.TRAPDOOR.value())
                            .pressurePlate(RoyalFurnitureSet.PRESSURE_PLATE.value())
                            .sign(RoyalFurnitureSet.SIGN.sign().value(), RoyalFurnitureSet.SIGN.wall().value())
                            .customHangingSign(RoyalFurnitureSet.HANGING_SIGN.sign().value(), RoyalFurnitureSet.HANGING_SIGN.wall().value())
                            .getFamily(),
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    BlockItemTags.DOORS,
                    BlockItemTags.STAIRS,
                    BlockItemTags.STONE_BUTTONS,
                    new BlockItemTagId(BlockTags.PRESSURE_PLATES, null),
                    BlockItemTags.TRAPDOORS,
                    new BlockItemTagId(Tags.Blocks.FENCES, Tags.Items.FENCES),
                    new BlockItemTagId(Tags.Blocks.FENCE_GATES, Tags.Items.FENCE_GATES),
                    BlockItemTags.SLABS,
                    exclusions -> {
                        exclusions.put(DataGenType.MODEL, FurnitureUtil.Names.WOOL);
                        exclusions.put(DataGenType.MODEL, FurnitureUtil.Names.CARPET);
                        exclusions.put(DataGenType.MODEL, FurnitureUtil.Names.DRESSER);
                        exclusions.put(DataGenType.MODEL, FurnitureUtil.Names.CHAIR);
                        exclusions.put(DataGenType.MODEL, FurnitureUtil.Names.BOOKSHELF);
                        exclusions.put(DataGenType.MODEL, FurnitureUtil.Names.BED_SINGLE);
                        exclusions.put(DataGenType.MODEL, FurnitureUtil.Names.BED_DOUBLE);
                        exclusions.put(DataGenType.MODEL, FurnitureUtil.Names.DOOR_SINGLE);
                        exclusions.put(DataGenType.MODEL, FurnitureUtil.Names.DOOR_DOUBLE);
                        exclusions.put(DataGenType.MODEL, FurnitureUtil.Names.DESK_LEFT);
                        exclusions.put(DataGenType.MODEL, FurnitureUtil.Names.DESK_RIGHT);
                        exclusions.put(DataGenType.MODEL, FurnitureUtil.Names.CHEST);
                        exclusions.put(DataGenType.MODEL, FurnitureUtil.Names.SHELF);
                        exclusions.put(DataGenType.MODEL, FurnitureUtil.Names.SOFA);
                        exclusions.put(DataGenType.MODEL, FurnitureUtil.Names.COUNTER);
                        exclusions.put(DataGenType.MODEL, FurnitureUtil.Names.BENCH);
                        exclusions.put(DataGenType.MODEL, FurnitureUtil.Names.WARDROBE);
                    }
            );

            event.createReloadableRegistryObjects(new RegistrySetBuilder()
                    .add(Registries.LOOT_TABLE, new LootTableProvider(Set.of(), List.of(context.forLootTable(FurnitureBlockLootSubProvider::new))))
                    .add(context.forBootstrap(FurnitureRecipeProvider::new))
            );

            event.createProvider(context.fromOutput(FurnitureLanguageProviderUS::new));
            event.createProvider(context.fromOutput(RFModelProvider::new));
            event.createProvider(context.fromOutputLookup(RFBlockTagsProvider::new));
            event.createProvider(context.fromOutputLookup(RFItemTagsProvider::new));
            event.createProvider(output -> ApexUtil.createMetadataProvider(output, Component.literal("Royal Furniture Set resources"), PackType.SERVER_DATA));
        });
    }
}
