package dev.apexstudios.fantasyfurniture.decorations.data;

import dev.apexstudios.fantasyfurniture.common.data.FurnitureRecipeProvider;
import dev.apexstudios.fantasyfurniture.common.station.FurnitureStationRecipeBuilder;
import dev.apexstudios.fantasyfurniture.common.station.FurnitureStationSetup;
import dev.apexstudios.fantasyfurniture.decorations.common.DecorationsFurnitureModule;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Recipe;
import net.neoforged.neoforge.common.Tags;

final class DFRecipeProvider extends RecipeProvider {
    DFRecipeProvider(BootstrapContext<Recipe<?>> recipeOutput, BootstrapContext<Advancement> advancementOutput) {
        super(recipeOutput, advancementOutput);
    }

    @Override
    protected void buildRecipes() {
        DecorationsFurnitureModule.REGISTREE
                .listElements(Registries.ITEM)
                .map(Holder::value)
                .forEach(this::furnitureStation);
    }

    private void furnitureStation(Item item) {
        FurnitureStationRecipeBuilder
                .builder(RecipeCategory.DECORATIONS, tag(Tags.Items.DYES), null, tag(FurnitureStationSetup.BINDING_AGENT), item)
                .unlockedBy(FurnitureRecipeProvider.getHasName(Tags.Items.DYES), has(Tags.Items.DYES))
                .save(output, FurnitureRecipeProvider.recipeKeyWithPrefix(item, "furniture_station/"));
    }
}
