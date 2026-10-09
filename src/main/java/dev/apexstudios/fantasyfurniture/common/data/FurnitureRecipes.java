package dev.apexstudios.fantasyfurniture.common.data;

import dev.apexstudios.apexcore.api.data.RecipeProvider;
import dev.apexstudios.fantasyfurniture.common.station.FurnitureStationRecipeBuilder;
import dev.apexstudios.fantasyfurniture.common.station.FurnitureStationSetup;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.crafting.DifferenceIngredient;

public interface FurnitureRecipes {
    static void conversionRecipe(RecipeProvider provider, DataGenContext context, TagKey<Item> baseTag, TagKey<Item> furnitureTag, String hasKey, ItemLike result) {
        SingleItemRecipeBuilder
                .stonecutting(DifferenceIngredient.of(provider.tag(baseTag), provider.tag(furnitureTag)), RecipeCategory.MISC, result, 1)
                .unlockedBy(hasKey, provider.has(baseTag))
                .save(provider.output(), RecipeProvider.recipeKeyWithPrefix(result, "conversion/"));
    }

    static void furnitureStationRecipe(RecipeProvider provider, DataGenContext context, ItemLike result) {
        var wool = context.registree.getValue(Registries.BLOCK, FurnitureUtil.Names.WOOL);
        var woolIngredient = wool == null ? null : Ingredient.of(wool);

        FurnitureStationRecipeBuilder
                .builder(RecipeCategory.MISC, Ingredient.of(context.family().getBaseBlock()), woolIngredient, provider.tag(FurnitureStationSetup.BINDING_AGENT), result)
                .group(context.family().getRecipeGroupPrefix().orElse(null))
                .unlockedBy(context.family().getRecipeUnlockedBy().orElseGet(() -> RecipeProvider.getHasName(context.family().getBaseBlock())), provider.has(context.family().getBaseBlock()))
                .save(provider.output(), RecipeProvider.recipeKeyWithPrefix(result, "furniture_station/"));
    }
}
