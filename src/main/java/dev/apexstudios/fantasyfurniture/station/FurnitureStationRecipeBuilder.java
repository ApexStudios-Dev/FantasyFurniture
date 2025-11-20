package dev.apexstudios.fantasyfurniture.station;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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
    private final ItemStack result;
    private final Map<String, Criterion<?>> criteria = Maps.newLinkedHashMap();

    private FurnitureStationRecipeBuilder(RecipeCategory category, Ingredient planks, @Nullable Ingredient wool, Ingredient bindingAgent, ItemStack result) {
        this.category = category;
        this.planks = planks;
        this.wool = Optional.ofNullable(wool);
        this.bindingAgent = bindingAgent;
        this.result = result;
    }

    @Override
    public FurnitureStationRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        criteria.put(name, criterion);
        return this;
    }

    @Override
    public FurnitureStationRecipeBuilder group(@Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public Item getResult() {
        return result.getItem();
    }

    @Override
    public void save(RecipeOutput output, ResourceKey<Recipe<?>> recipeKey) {
        if(criteria.isEmpty())
            throw new IllegalStateException("No way of obtaining recipe: " + recipeKey.identifier());

        var advancement = output.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeKey))
                .rewards(AdvancementRewards.Builder.recipe(recipeKey))
                .requirements(AdvancementRequirements.Strategy.OR);

        criteria.forEach(advancement::addCriterion);

        var recipe = new FurnitureStationRecipe(Objects.requireNonNullElse(group, ""), planks, wool, bindingAgent, result);
        output.accept(recipeKey, recipe, advancement.build(recipeKey.identifier().withPrefix("recipes/" + category.getFolderName() + '/')));
    }

    public static FurnitureStationRecipeBuilder builder(RecipeCategory category, Ingredient planks, @Nullable Ingredient wool, Ingredient bindingAgent, ItemStack result) {
        return new FurnitureStationRecipeBuilder(category, planks, wool, bindingAgent, result);
    }

    public static FurnitureStationRecipeBuilder builder(RecipeCategory category, Ingredient planks, @Nullable Ingredient wool, Ingredient bindingAgent, ItemLike result, int count) {
        return builder(category, planks, wool, bindingAgent, new ItemStack(result, count));
    }

    public static FurnitureStationRecipeBuilder builder(RecipeCategory category, Ingredient planks, @Nullable Ingredient wool, Ingredient bindingAgent, ItemLike result) {
        return builder(category, planks, wool, bindingAgent, new ItemStack(result));
    }
}
