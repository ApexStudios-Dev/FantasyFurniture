package dev.apexstudios.fantasyfurniture.data;

import com.google.gson.JsonObject;
import dev.apexstudios.fantasyfurniture.common.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.common.util.OptionalPacks;
import java.util.List;
import java.util.Set;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.util.Util;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod(FantasyFurniture.ID)
public final class FantasyFurnitureDataEntryPoint {
    public FantasyFurnitureDataEntryPoint(IEventBus modBus) {
        modBus.addListener(GatherDataEvent.Client.class, event -> {
            event.createReloadableRegistryObjects(new RegistrySetBuilder()
                    .add(Registries.LOOT_TABLE, new LootTableProvider(Set.of(), List.of(new LootTableProvider.SubProviderEntry(FFBlockLootSubProvider::new, LootContextParamSets.BLOCK))))
                    .add(RecipeProvider.asBootstrap(FFRecipeProvider::new))
            );

            event.createProvider(FFItemTagsProvider::new);
            event.createProvider(FFLanguageProvider::new);
            event.createProvider(FFModelProvider::new);
            event.createProvider(FFBlockTagsProvider::new);
            event.createProvider(output -> PackMetadataGenerator.forFeaturePack(output, Component.literal("Fantasy's Furniture resources")));

            pack(
                    event,
                    OptionalPacks.CONTEX,
                    ConTexProvider.create(
                            event.getResourceManager(PackType.CLIENT_RESOURCES),
                            OptionalPacks.CONTEX,
                            "definition",
                            OptionalPacks.CONTEX.modId +  "_meta",
                            "carpet_full",
                            (textures, main, ctmBase) -> {
                                textures.addProperty("main_texture", main.toString());

                                textures.add("ct_textures", Util.make(new JsonObject(), ctmTextures -> {
                                    ctmTextures.addProperty("compact/hor", ctmBase.withSuffix(ConTexProvider.HORIZONTAL_SUFFIX).toString());
                                    ctmTextures.addProperty("compact/vert", ctmBase.withSuffix(ConTexProvider.VERTICAL_SUFFIX).toString());
                                    ctmTextures.addProperty("compact/cross", ctmBase.withSuffix(ConTexProvider.CENTER_SUFFIX).toString());
                                    ctmTextures.addProperty("compact/full", ctmBase.withSuffix(ConTexProvider.EMPTY_SUFFIX).toString());
                                }));
                            })
            );

            pack(
                    event,
                    OptionalPacks.CONTEXT_MATTERS,
                    ConTexProvider.create(
                            event.getResourceManager(PackType.CLIENT_RESOURCES),
                            OptionalPacks.CONTEXT_MATTERS,
                            "connected_definition",
                            "metadata",
                            "carpet_full_down",
                            (textures, main, ctmBase) -> {
                                textures.addProperty("none", main.toString());
                                textures.addProperty("horizontal", ctmBase.withSuffix(ConTexProvider.HORIZONTAL_SUFFIX).toString());
                                textures.addProperty("vertical", ctmBase.withSuffix(ConTexProvider.VERTICAL_SUFFIX).toString());
                                textures.addProperty("full", ctmBase.withSuffix(ConTexProvider.EMPTY_SUFFIX).toString());
                                textures.addProperty("cardinal", ctmBase.withSuffix(ConTexProvider.CENTER_SUFFIX).toString());
                            })
            );

            pack(event, OptionalPacks.LEGACY_DOORS, LegacyDoorsProvider::new);
        });
    }

    private <T extends DataProvider> void pack(GatherDataEvent event, OptionalPacks pack, DataProvider.Factory<T> factory) {
        var generator = event.getGenerator();
        var generated = generator.getPackGenerator(true, pack.id + "-providers",pack.packPath());
        generated.addProvider(output -> PackMetadataGenerator.forFeaturePack(output, Component.literal(pack.description)));
        generated.addProvider(factory);
    }
}
