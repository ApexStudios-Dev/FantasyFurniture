package dev.apexstudios.fantasyfurniture.bone.data;

import dev.apexstudios.apexcore.lib.data.ResourceGenerator;
import dev.apexstudios.apexcore.lib.data.pack.FeaturePackGenerator;
import dev.apexstudios.apexcore.lib.util.TagPair;
import dev.apexstudios.fantasyfurniture.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.bone.BoneFurnitureSet;
import dev.apexstudios.fantasyfurniture.util.FurnitureClientDataUtil;
import dev.apexstudios.fantasyfurniture.util.FurnitureDataUtil;
import dev.apexstudios.fantasyfurniture.util.FurnitureUtil;
import net.minecraft.data.BlockFamily;
import net.minecraft.server.packs.PackType;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(FantasyFurniture.ID + "_bone")
public final class BoneFurnitureSetDataEntryPoint {
    public BoneFurnitureSetDataEntryPoint(IEventBus modBus) {
        ResourceGenerator.simple(modBus);
    }

    public static void register(ResourceGenerator generator, BoneFurnitureSet furnitureSet, String englishName) {
        generator.pack().markDummy();
        var assetPack = createPack(generator, PackType.CLIENT_RESOURCES, furnitureSet, englishName);
        var dataPack = createPack(generator, PackType.SERVER_DATA, furnitureSet, englishName);

        var context = new FurnitureDataUtil.DataGenContext(
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
                .getFamily(),
                BlockTags.MINEABLE_WITH_PICKAXE,
                new TagPair(BlockTags.DOORS, ItemTags.DOORS),
                BlockTags.STAIRS,
                new TagPair(BlockTags.BUTTONS, ItemTags.BUTTONS),
                new TagPair(BlockTags.PRESSURE_PLATES, null),
                new TagPair(BlockTags.TRAPDOORS, ItemTags.TRAPDOORS),
                new TagPair(BlockTags.FENCES, ItemTags.FENCES),
                new TagPair(BlockTags.SLABS, ItemTags.SLABS)
        );

        FurnitureDataUtil.registerDataGen(context, dataPack);
        FurnitureClientDataUtil.registerDataGen(context, assetPack);
    }

    private static FeaturePackGenerator createPack(ResourceGenerator generator, PackType packType, BoneFurnitureSet furnitureSet, String englishName) {
        var typeName = switch (packType) {
            case CLIENT_RESOURCES -> "assets";
            case SERVER_DATA -> "data-files";
        };

        return generator.pack(furnitureSet.id + '-' + typeName)
                .description(englishName + ' ' + typeName)
                .packType(packType)
                .path(BoneFurnitureSet.packPath(packType, furnitureSet));
    }
}
