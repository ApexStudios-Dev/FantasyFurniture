package dev.apexstudios.fantasyfurniture.common.station;

import java.util.Objects;
import java.util.Optional;
import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeUnlockAdvancementBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import org.jspecify.annotations.Nullable;

public final class FurnitureStationRecipeBuilder implements RecipeBuilder {
    private final RecipeCategory category;
    @Nullable private String group = null;
    private final Ingredient planks;
    private final Optional<Ingredient> wool;
    private final Ingredient bindingAgent;
    private final ItemStackTemplate result;
    private final RecipeUnlockAdvancementBuilder criteria = new RecipeUnlockAdvancementBuilder();

    private FurnitureStationRecipeBuilder(RecipeCategory category, Ingredient planks, @Nullable Ingredient wool, Ingredient bindingAgent, ItemStackTemplate result) {
        this.category = category;
        this.planks = planks;
        this.wool = Optional.ofNullable(wool);
        this.bindingAgent = bindingAgent;
        this.result = result;
    }

    @Override
    public FurnitureStationRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        criteria.unlockedBy(name, criterion);
        return this;
    }

    @Override
    public FurnitureStationRecipeBuilder group(@Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public ResourceKey<Recipe<?>> defaultId() {
        return RecipeBuilder.getDefaultRecipeId(result);
    }

    @Override
    public void save(RecipeOutput output, ResourceKey<Recipe<?>> recipeKey) {
        var recipe = new FurnitureStationRecipe(Objects.requireNonNullElse(group, ""), planks, wool, bindingAgent, result);
        output.accept(recipeKey, recipe, criteria.build(output, recipeKey, category));
    }

    public static FurnitureStationRecipeBuilder builder(RecipeCategory category, Ingredient planks, @Nullable Ingredient wool, Ingredient bindingAgent, ItemStackTemplate result) {
        return new FurnitureStationRecipeBuilder(category, planks, wool, bindingAgent, result);
    }

    public static FurnitureStationRecipeBuilder builder(RecipeCategory category, Ingredient planks, @Nullable Ingredient wool, Ingredient bindingAgent, ItemLike result, int count) {
        return builder(category, planks, wool, bindingAgent, new ItemStackTemplate(result.asItem(), count));
    }

    public static FurnitureStationRecipeBuilder builder(RecipeCategory category, Ingredient planks, @Nullable Ingredient wool, Ingredient bindingAgent, ItemLike result) {
        return builder(category, planks, wool, bindingAgent, result, 1);
    }
}
