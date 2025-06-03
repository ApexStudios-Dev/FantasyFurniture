package dev.apexstudios.fantasyfurniture.station;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.RecipesReceivedEvent;
import net.neoforged.neoforge.common.NeoForge;

public interface FurnitureStationClientSetup {
    List<RecipeHolder<FurnitureStationRecipe>> RECIPES = Lists.newArrayList();

    static void register() {
        NeoForge.EVENT_BUS.addListener(RecipesReceivedEvent.class, event -> {
            RECIPES.clear();
            RECIPES.addAll(event.getRecipeMap().byType(FurnitureStationSetup.RECIPE_TYPE.value()));
        });

        NeoForge.EVENT_BUS.addListener(ClientPlayerNetworkEvent.LoggingOut.class, event -> RECIPES.clear());
    }
}
