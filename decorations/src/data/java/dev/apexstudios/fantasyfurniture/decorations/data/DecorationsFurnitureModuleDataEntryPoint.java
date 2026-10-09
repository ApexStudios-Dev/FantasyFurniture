package dev.apexstudios.fantasyfurniture.decorations.data;

import dev.apexstudios.apexcore.api.util.ApexUtil;
import dev.apexstudios.fantasyfurniture.decorations.common.DecorationsFurnitureModule;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.data.event.GatherDataRegistryEntriesEvent;

@Mod(DecorationsFurnitureModule.ID)
public final class DecorationsFurnitureModuleDataEntryPoint {
    public DecorationsFurnitureModuleDataEntryPoint(IEventBus modBus) {
        modBus.addListener(GatherDataEvent.Client.class, event -> {
            event.createProvider(DFLanguageProvider::new);
            event.createProvider(DFModelProvider::new);
            event.createBlockAndItemTags(DFBlockTagsProvider::new, DFItemTagsProvider::new);
            event.createProvider(output -> ApexUtil.createMetadataProvider(output, Component.literal("Decorations Furniture Set resources"), PackType.SERVER_DATA));
        });

        modBus.addListener(GatherDataRegistryEntriesEvent.class, event -> event
                .lootTable(new LootTableProvider.SubProviderEntry(DFBlockLootSubProvider::new, LootContextParamSets.BLOCK))
                .recipe(DFRecipeProvider::new)
        );
    }
}
