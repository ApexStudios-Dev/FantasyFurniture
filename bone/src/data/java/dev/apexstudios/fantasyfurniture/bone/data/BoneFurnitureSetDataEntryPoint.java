package dev.apexstudios.fantasyfurniture.bone.data;

import dev.apexstudios.apexcore.api.util.TagPair;
import dev.apexstudios.fantasyfurniture.bone.common.BoneFurnitureSet;
import dev.apexstudios.fantasyfurniture.common.data.DataGenContext;
import dev.apexstudios.fantasyfurniture.common.data.FurnitureBlockLootSubProvider;
import dev.apexstudios.fantasyfurniture.common.data.FurnitureBlockTagsProvider;
import dev.apexstudios.fantasyfurniture.common.data.FurnitureItemTagsProvider;
import dev.apexstudios.fantasyfurniture.common.data.FurnitureLanguageProviderUS;
import dev.apexstudios.fantasyfurniture.common.data.FurnitureModelProvider;
import dev.apexstudios.fantasyfurniture.common.data.FurnitureRecipeProvider;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import java.util.List;
import java.util.Set;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod(BoneFurnitureSet.ID)
public final class BoneFurnitureSetDataEntryPoint {
    public BoneFurnitureSetDataEntryPoint(IEventBus modBus) {
        modBus.addListener(GatherDataEvent.Client.class, event -> event.createProvider(output -> PackMetadataGenerator.forFeaturePack(
                output,
                Component.literal("Bone Furniture Set resources")
        )));
    }

    public static void register(GatherDataEvent event, BoneFurnitureSet furnitureSet, String englishName) {
        var context = new DataGenContext(
                furnitureSet.registree,
                "Bone " + englishName,
                new BlockFamily.Builder(furnitureSet.bricks.value())
                        .recipeGroupPrefix(furnitureSet.id)
                        .recipeUnlockedBy("has_" + FurnitureUtil.Names.BRICKS)
                        .stairs(furnitureSet.stairs.value())
                        .slab(furnitureSet.slab.value())
                        .fence(furnitureSet.fence.value())
                        .fenceGate(furnitureSet.fenceGate.value())
                        .trapdoor(furnitureSet.trapdoor.value())
                        .pressurePlate(furnitureSet.pressurePlate.value())
                        .sign(furnitureSet.sign.sign().value(), furnitureSet.sign.wall().value())
                        .customHangingSign(furnitureSet.hangingSign.sign().value(), furnitureSet.hangingSign.wall().value())
                        .getFamily(),
                BlockTags.MINEABLE_WITH_PICKAXE,
                new TagPair(BlockTags.DOORS, ItemTags.WOODEN_DOORS),
                BlockTags.STAIRS,
                new TagPair(BlockTags.BUTTONS, ItemTags.WOODEN_BUTTONS),
                new TagPair(BlockTags.PRESSURE_PLATES, null),
                new TagPair(BlockTags.TRAPDOORS, ItemTags.WOODEN_TRAPDOORS),
                new TagPair(BlockTags.FENCES, ItemTags.WOODEN_FENCES),
                new TagPair(BlockTags.SLABS, ItemTags.WOODEN_SLABS)
        );

        var pack = event.getGenerator().getPackGenerator(true, furnitureSet.id + "/asset-providers", BoneFurnitureSet.packPath(furnitureSet));
        var reloadableRegistries =  pack.addProvider(output -> DatapackBuiltinEntriesProvider.forReloadableLayer(
                output,
                "reloadable",
                event.getWorldLookupProvider(),
                event.getReloadableLookupProvider(),
                new RegistrySetBuilder()
                        .add(Registries.LOOT_TABLE, registry -> new LootTableProvider(Set.of(), List.of(context.forLootTable(FurnitureBlockLootSubProvider::new))))
                        .add(context.forBootstrap(FurnitureRecipeProvider::new)),
                Set.of(furnitureSet.registree.namespace())
        ));

        pack.addProvider(context.fromOutput(FurnitureLanguageProviderUS::new)::create);
        pack.addProvider(context.fromOutput(FurnitureModelProvider::new)::create);
        pack.addProvider(output -> new FurnitureBlockTagsProvider(output, event.getWorldLookupProvider(), context));
        pack.addProvider(output -> new FurnitureItemTagsProvider(output, event.getWorldLookupProvider(), context));
        pack.addProvider(output -> PackMetadataGenerator.forFeaturePack(output, Component.literal("Bone " + englishName + " Furniture Set resources")));
    }
}
