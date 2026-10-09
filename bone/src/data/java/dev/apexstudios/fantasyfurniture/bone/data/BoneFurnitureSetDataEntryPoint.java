package dev.apexstudios.fantasyfurniture.bone.data;

import dev.apexstudios.apexcore.api.util.ApexUtil;
import dev.apexstudios.fantasyfurniture.bone.common.BoneFurnitureSet;
import dev.apexstudios.fantasyfurniture.common.data.DataGenContext;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod(BoneFurnitureSet.ID)
public final class BoneFurnitureSetDataEntryPoint {
    public BoneFurnitureSetDataEntryPoint(IEventBus modBus) {
        modBus.addListener(GatherDataEvent.Client.class, event -> event.createProvider(output -> ApexUtil.createMetadataProvider(output, Component.literal("Bone Furniture Set resources"), PackType.SERVER_DATA)));
    }

    public static DataGenContext register(IEventBus modBus, BoneFurnitureSet furnitureSet) {
        return DataGenContext.stone(furnitureSet.registree, furnitureSet.id)
                .build(modBus, furnitureSet.id + "/asset-providers", BoneFurnitureSet.packPath(furnitureSet));
    }
}
