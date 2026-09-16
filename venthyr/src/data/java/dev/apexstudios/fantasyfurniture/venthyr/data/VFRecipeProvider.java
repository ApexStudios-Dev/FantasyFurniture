package dev.apexstudios.fantasyfurniture.venthyr.data;

import dev.apexstudios.fantasyfurniture.common.data.DataGenContext;
import dev.apexstudios.fantasyfurniture.common.data.FurnitureRecipeProvider;
import dev.apexstudios.fantasyfurniture.venthyr.common.VenthyrFurnitureSet;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.crafting.Recipe;

final class VFRecipeProvider extends FurnitureRecipeProvider {
    VFRecipeProvider(BootstrapContext<Recipe<?>> recipeOutput, BootstrapContext<Advancement> advancementOutput, DataGenContext furniture) {
        super(recipeOutput, advancementOutput, furniture);
    }

    @Override
    protected void buildRecipes() {
        super.buildRecipes();

        furnitureStationRecipe(VenthyrFurnitureSet.TABLE_CLOTH.value());
    }
}
