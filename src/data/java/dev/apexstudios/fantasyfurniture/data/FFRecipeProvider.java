package dev.apexstudios.fantasyfurniture.data;

import dev.apexstudios.fantasyfurniture.common.station.FurnitureStationSetup;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.crafting.Recipe;
import net.neoforged.neoforge.common.Tags;

final class FFRecipeProvider extends RecipeProvider {
    FFRecipeProvider(BootstrapContext<Recipe<?>> recipeOutput, BootstrapContext<Advancement> advancementOutput) {
        super(recipeOutput, advancementOutput);
    }

    @Override
    protected void buildRecipes() {
        shapeless(RecipeCategory.MISC, FurnitureStationSetup.BLOCK.value())
                .requires(Tags.Items.PLAYER_WORKSTATIONS_CRAFTING_TABLES)
                .requires(Tags.Items.LEATHERS)
                .unlockedBy("has_crafting_table", has(Tags.Items.PLAYER_WORKSTATIONS_CRAFTING_TABLES))
                .save(output);
    }
}
