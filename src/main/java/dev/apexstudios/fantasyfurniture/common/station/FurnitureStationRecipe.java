package dev.apexstudios.fantasyfurniture.common.station;

import java.util.List;
import java.util.Optional;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
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
    private final Optional<Ingredient> wool;
    private final Ingredient bindingAgent;
    private final ItemStackTemplate result;

    FurnitureStationRecipe(String group, Ingredient planks, Optional<Ingredient> wool, Ingredient bindingAgent, ItemStackTemplate result) {
        this.group = group;
        this.planks = planks;
        this.wool = wool;
        this.bindingAgent = bindingAgent;
        this.result = result;
    }

    public Ingredient planks() {
        return planks;
    }

    public Optional<Ingredient> wool() {
        return wool;
    }

    public Ingredient bindingAgent() {
        return bindingAgent;
    }

    public ItemStackTemplate result() {
        return result;
    }

    private boolean matches(FurnitureStationRecipeInput input) {
        if(wool.isPresent() && !wool.get().test(input.wool()))
            return false;

        return planks.test(input.planks()) && bindingAgent.test(input.bindingAgent());
    }

    @Override
    public boolean matches(FurnitureStationRecipeInput input, Level level) {
        return matches(input);
    }

    @Override
    public ItemStack assemble(FurnitureStationRecipeInput input) {
        return matches(input) ? result.create() : ItemStack.EMPTY;
    }

    @Override
    public boolean showNotification() {
        return true;
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
        return PlacementInfo.create(wool.map(wool -> List.of(planks, wool, bindingAgent)).orElseGet(() -> List.of(planks, bindingAgent)));
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
