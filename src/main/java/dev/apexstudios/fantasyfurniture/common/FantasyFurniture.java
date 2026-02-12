package dev.apexstudios.fantasyfurniture.common;

import com.google.common.collect.Sets;
import dev.apexstudios.fantasyfurniture.common.ctm.CtmPacks;
import dev.apexstudios.fantasyfurniture.common.station.FurnitureStationSetup;
import dev.apexstudios.registree.Registree;
import dev.apexstudios.registree.registrar.BlockEntityTypeRegistrar;
import dev.apexstudios.registree.registrar.BlockRegistrar;
import dev.apexstudios.registree.registrar.ItemRegistrar;
import dev.apexstudios.registree.registrar.MenuTypeRegistrar;
import dev.apexstudios.registree.registrar.RecipeBookCategoryRegistrar;
import dev.apexstudios.registree.registrar.RecipeSerializerRegistrar;
import dev.apexstudios.registree.registrar.RecipeTypeRegistrar;
import java.util.Set;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
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
    public static final BlockRegistrar BLOCKS = REGISTREE.blocks();
    public static final ItemRegistrar ITEMS = REGISTREE.items();
    public static final BlockEntityTypeRegistrar BLOCK_ENTITY_TYPES = REGISTREE.blockEntityTypes();
    public static final MenuTypeRegistrar MENU_TYPES = REGISTREE.menuTypes();
    public static final RecipeSerializerRegistrar RECIPE_SERIALIZERS = REGISTREE.recipeSerializers();
    public static final RecipeTypeRegistrar RECIPE_TYPES = REGISTREE.recipeTypes();
    public static final RecipeBookCategoryRegistrar RECIPE_BOOK_CATEGORIES = REGISTREE.recipeBookCategories();

    public static final TagKey<Item> FURNITURE_PLANKS = ITEMS.tag("furniture_planks");
    public static final TagKey<Item> FURNITURE_WOOL = ITEMS.tag("furniture_wool");
    public static final TagKey<Item> FURNITURE_BRICKS = ITEMS.tag("furniture_bricks");

    public static final Set<String> FURNITURE_MODS = Sets.newHashSet();
    public static final String LOADING_ISSUE_KEY = ID + ".loading_issue.missing_furniture_sets";

    public FantasyFurniture(IEventBus modBus) {
        REGISTREE.registerEvents(modBus);
        FurnitureStationSetup.register();
        FurnitureBlockEntities.register();
        FurnitureMenus.register();
        CtmPacks.register();

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
}
