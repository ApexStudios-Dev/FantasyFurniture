package dev.apexstudios.fantasyfurniture.common;

import com.google.common.collect.Sets;
import dev.apexstudios.fantasyfurniture.common.ctm.CtmPacks;
import dev.apexstudios.fantasyfurniture.common.station.FurnitureStationSetup;
import dev.apexstudios.registree.api.Registree;
import java.util.Set;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoader;
import net.neoforged.fml.ModLoadingIssue;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(FantasyFurniture.ID)
public final class FantasyFurniture {
    public static final String ID = "fantasyfurniture";
    public static final Registree REGISTREE = Registree.create(ID);
    public static final TagKey<Item> FURNITURE_PLANKS = REGISTREE.tag(Registries.ITEM, "furniture_planks");
    public static final TagKey<Item> FURNITURE_WOOL = REGISTREE.tag(Registries.ITEM, "furniture_wool");
    public static final TagKey<Item> FURNITURE_BRICKS = REGISTREE.tag(Registries.ITEM, "furniture_bricks");

    public static final Set<String> FURNITURE_MODS = Sets.newHashSet();
    public static final String LOADING_ISSUE_KEY = ID + ".loading_issue.missing_furniture_sets";

    public static final Identifier EXPERIMENTAL_FLAG_ID = identifier("experimental");
    public static final String EXPERIMENTAL_FLAG_KEY = EXPERIMENTAL_FLAG_ID.toLanguageKey("feature_flag");
    public static final FeatureFlag EXPERIMENTAL = FeatureFlags.REGISTRY.getFlag(EXPERIMENTAL_FLAG_ID);

    public FantasyFurniture(IEventBus modBus) {
        REGISTREE.registerEvents(modBus);
        FurnitureStationSetup.register(modBus);
        FurnitureBlockEntities.register(modBus);
        FurnitureMenus.register(modBus);
        CtmPacks.register(modBus);

        modBus.addListener(FMLCommonSetupEvent.class, event -> {
            if(FURNITURE_MODS.isEmpty())
                ModLoader.addLoadingIssue(ModLoadingIssue.warning(LOADING_ISSUE_KEY));
        });
    }

    public static Identifier identifier(String identifier) {
        return REGISTREE.registryName(identifier);
    }

    public static String id(String identifier) {
        return ID + Identifier.NAMESPACE_SEPARATOR + identifier;
    }

    private record CtmPack(String packId, String packName) { }
}
