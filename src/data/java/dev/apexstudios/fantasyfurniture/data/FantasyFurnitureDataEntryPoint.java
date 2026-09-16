package dev.apexstudios.fantasyfurniture.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dev.apexstudios.apexcore.api.util.ApexUtil;
import dev.apexstudios.fantasyfurniture.common.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.common.util.OptionalPacks;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import net.minecraft.DetectedVersion;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.Util;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.jspecify.annotations.Nullable;

@Mod(FantasyFurniture.ID)
public final class FantasyFurnitureDataEntryPoint {
    private static final Gson GSON = new GsonBuilder().create();

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
            event.createProvider(output -> ApexUtil.createMetadataProvider(output, Component.literal("Fantasy's Furniture resources"), PackType.SERVER_DATA));

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

            pack(event, OptionalPacks.LEGACY_DOORS, output -> new LegacyDoorsProvider(output, event.getResourceManager(PackType.CLIENT_RESOURCES)));
        });
    }

    private <T extends DataProvider> void pack(GatherDataEvent event, OptionalPacks pack, DataProvider.Factory<T> factory) {
        var generator = event.getGenerator();
        var generated = generator.getPackGenerator(true, pack.id + "-providers",pack.packPath());
        generated.addProvider(output -> new PackMetadataGenerator(output).add(PackMetadataSection.CLIENT_TYPE, new PackMetadataSection(Component.literal(pack.description), DetectedVersion.BUILT_IN.packVersion(PackType.CLIENT_RESOURCES).minorRange())));
        generated.addProvider(factory);
    }

    public static void forEachModule(BiConsumer<String, Boolean> action) {
        // action.accept(FantasyFurniture.ID + "_bone", false);
        action.accept(FantasyFurniture.ID + "_bone_skeleton", false);
        action.accept(FantasyFurniture.ID + "_bone_wither", false);
        // action.accept(FantasyFurniture.ID + "_decorations", false);
        action.accept(FantasyFurniture.ID + "_dunmer", false);
        action.accept(FantasyFurniture.ID + "_necrolord", false);
        action.accept(FantasyFurniture.ID + "_nordic", false);
        action.accept(FantasyFurniture.ID + "_royal", true);
        action.accept(FantasyFurniture.ID + "_venthyr", false);
    }

    public static void copyInto(@Nullable JsonObject from, JsonObject into) {
        if(from == null || from.isEmpty()) {
            return;
        }

        from.keySet().forEach(key -> into.add(key, from.get(key).deepCopy()));
    }

    public static @Nullable JsonObject loadExisting(ResourceManager resourceManager, String type, Identifier id) {
        try(var reader = resourceManager.openAsReader(id.withPath(path -> type + '/' + path + ".json"))) {
            return GsonHelper.fromJson(GSON, reader, JsonObject.class);
        } catch (IOException e) {
            return null;
        }
    }
}
