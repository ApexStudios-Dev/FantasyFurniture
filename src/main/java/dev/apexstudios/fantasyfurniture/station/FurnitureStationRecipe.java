package dev.apexstudios.fantasyfurniture.station;

import java.util.List;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public final class FurnitureStationRecipe implements Recipe<FurnitureStationRecipeInput> {
    private final String group;
    private final Ingredient planks;
    private final Ingredient wool;
    private final Ingredient bindingAgent;
    private final ItemStack result;

    FurnitureStationRecipe(String group, Ingredient planks, Ingredient wool, Ingredient bindingAgent, ItemStack result) {
        this.group = group;
        this.planks = planks;
        this.wool = wool;
        this.bindingAgent = bindingAgent;
        this.result = result;
    }

    public Ingredient planks() {
        return planks;
    }

    public Ingredient wool() {
        return wool;
    }

    public Ingredient bindingAgent() {
        return bindingAgent;
    }

    public ItemStack result() {
        return result;
    }

    private boolean matches(FurnitureStationRecipeInput input) {
        return planks.test(input.planks()) && wool.test(input.wool()) && bindingAgent.test(input.bindingAgent());
    }

    @Override
    public boolean matches(FurnitureStationRecipeInput input, Level level) {
        return matches(input);
    }

    @Override
    public ItemStack assemble(FurnitureStationRecipeInput input, HolderLookup.Provider registries) {
        return matches(input) ? result.copy() : ItemStack.EMPTY;
    }

    @Override
    public RecipeSerializer<FurnitureStationRecipe> getSerializer() {
        return FurnitureStationSetup.RECIPE_SERIALIZER.value();
    }

    @Override
    public RecipeType<FurnitureStationRecipe> getType() {
        return FurnitureStationSetup.RECIPE_TYPE.value();
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.create(List.of(planks, wool, bindingAgent));
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return FurnitureStationSetup.RECIPE_BOOK_CATEGORY.value();
    }

    @Override
    public String group() {
        return group;
    }
}
