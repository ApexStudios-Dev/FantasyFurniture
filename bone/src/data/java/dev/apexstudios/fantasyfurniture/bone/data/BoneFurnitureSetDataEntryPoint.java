package dev.apexstudios.fantasyfurniture.bone.data;

import dev.apexstudios.apexcore.api.data.ResourceGenerator;
import dev.apexstudios.apexcore.api.data.pack.FeaturePackGenerator;
import dev.apexstudios.fantasyfurniture.bone.common.BoneFurnitureSet;
import dev.apexstudios.fantasyfurniture.common.ctm.CtmPacks;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureClientDataUtil;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureDataUtil;
import net.minecraft.server.packs.PackType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(BoneFurnitureSet.ID)
public final class BoneFurnitureSetDataEntryPoint {
    public BoneFurnitureSetDataEntryPoint(IEventBus modBus) {
        ResourceGenerator.simple(modBus);
    }

    public static void register(ResourceGenerator generator, BoneFurnitureSet furnitureSet, String englishName) {
        generator.pack().markDummy();
        var assetPack = createPack(generator, PackType.CLIENT_RESOURCES, furnitureSet, englishName);
        var dataPack = createPack(generator, PackType.SERVER_DATA, furnitureSet, englishName);

        var context = FurnitureDataUtil.context(furnitureSet.registree, "Bone " + englishName, furnitureSet.id)
                .stoneLike()
                .build();

        context.register(dataPack);
        FurnitureClientDataUtil.registerDataGen(context, assetPack);
        CtmPacks.registerDataGen(furnitureSet.registree, assetPack, false);
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
