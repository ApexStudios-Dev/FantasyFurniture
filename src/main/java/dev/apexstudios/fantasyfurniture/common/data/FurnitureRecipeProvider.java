package dev.apexstudios.fantasyfurniture.common.data;

import dev.apexstudios.fantasyfurniture.common.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.common.station.FurnitureStationRecipeBuilder;
import dev.apexstudios.fantasyfurniture.common.station.FurnitureStationSetup;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.crafting.DifferenceIngredient;

public class FurnitureRecipeProvider extends RecipeProvider {
    private final DataGenContext furniture;

    public FurnitureRecipeProvider(BootstrapContext<Recipe<?>> recipeOutput, BootstrapContext<Advancement> advancementOutput, DataGenContext furniture) {
        super(recipeOutput, advancementOutput);

        this.furniture = furniture;
    }

    @Override
    protected void buildRecipes() {
        addFurnitureRecipes();
    }

    protected void addFurnitureRecipes() {
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.PLANKS, block -> conversionRecipe(ItemTags.PLANKS, FantasyFurniture.FURNITURE_PLANKS, "has_" + FurnitureUtil.Names.PLANKS, block));
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.BRICKS, block -> conversionRecipe(ItemTags.STONE_CRAFTING_MATERIALS, FantasyFurniture.FURNITURE_BRICKS, "has_" + FurnitureUtil.Names.BRICKS, block));
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.WOOL, block -> conversionRecipe(ItemTags.WOOL, FantasyFurniture.FURNITURE_WOOL, "has_" + FurnitureUtil.Names.WOOL, block));
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.CARPET, this::furnitureStationRecipe);
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.DRESSER, this::furnitureStationRecipe);
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.STOOL, this::furnitureStationRecipe);
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.CUSHION, this::furnitureStationRecipe);
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.LOCKBOX, this::furnitureStationRecipe);
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.DRAWER, this::furnitureStationRecipe);
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.CHAIR, this::furnitureStationRecipe);
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.BOOKSHELF, this::furnitureStationRecipe);
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.BED_SINGLE, this::furnitureStationRecipe);
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.BED_DOUBLE, this::furnitureStationRecipe);
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.DOOR_SINGLE, this::furnitureStationRecipe);
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.DOOR_DOUBLE, this::furnitureStationRecipe);
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.DESK_LEFT, this::furnitureStationRecipe);
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.DESK_RIGHT, this::furnitureStationRecipe);
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.PAINTING_WIDE, this::furnitureStationRecipe);
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.PAINTING_SMALL, this::furnitureStationRecipe);
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.OVEN, this::furnitureStationRecipe);
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.CHEST, this::furnitureStationRecipe);
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.FLOOR_LIGHT, this::furnitureStationRecipe);
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.CHANDELIER, this::furnitureStationRecipe);
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.SHELF, this::furnitureStationRecipe);
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.SOFA, this::furnitureStationRecipe);
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.COUNTER, this::furnitureStationRecipe);
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.WALL_LIGHT, this::furnitureStationRecipe);
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.BENCH, this::furnitureStationRecipe);
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.WARDROBE, this::furnitureStationRecipe);
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.TABLE, this::furnitureStationRecipe);
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.STAIRS, this::furnitureStationRecipe);
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.SLAB, this::furnitureStationRecipe);
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.FENCE, this::furnitureStationRecipe);
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.FENCE_GATE, this::furnitureStationRecipe);
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.TRAPDOOR, this::furnitureStationRecipe);
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.PRESSURE_PLATE, this::furnitureStationRecipe);
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.BUTTON, this::furnitureStationRecipe);
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.HANGING_SIGN, this::furnitureStationRecipe);
        furniture.block(DataGenType.RECIPE, FurnitureUtil.Names.SIGN, this::furnitureStationRecipe);
    }

    protected void conversionRecipe(TagKey<Item> baseTag, TagKey<Item> furnitureTag, String hasKey, ItemLike result) {
        SingleItemRecipeBuilder
                .stonecutting(DifferenceIngredient.of(tag(baseTag), tag(furnitureTag)), RecipeCategory.MISC, result, 1)
                .unlockedBy(hasKey, has(baseTag))
                .save(output, recipeKeyWithPrefix(result, "conversion/"));
    }

    protected void furnitureStationRecipe(ItemLike result) {
        var wool = furniture.registree().getValue(Registries.BLOCK, FurnitureUtil.Names.WOOL);
        var woolIngredient = wool == null ? null : Ingredient.of(wool);

        FurnitureStationRecipeBuilder
                .builder(RecipeCategory.MISC, Ingredient.of(furniture.family().getBaseBlock()), woolIngredient, tag(FurnitureStationSetup.BINDING_AGENT), result)
                .group(furniture.family().getRecipeGroupPrefix().orElse(null))
                .unlockedBy(furniture.family().getRecipeUnlockedBy().orElseGet(() -> RecipeProvider.getHasName(furniture.family().getBaseBlock())), has(furniture.family().getBaseBlock()))
                .save(output, recipeKeyWithPrefix(result, "furniture_station/"));
    }

    public static String getHasName(TagKey<Item> tag) {
        return "has_" + tag.location().getPath().replace("/", "_");
    }

    public static ResourceKey<Recipe<?>> recipeKey(Identifier recipeId) {
        return ResourceKey.create(Registries.RECIPE, recipeId);
    }

    public static ResourceKey<Recipe<?>> recipeKeyWithPrefix(ItemLike item, String prefix) {
        return recipeKey(getDefaultRecipeId(item).withPrefix(prefix));
    }

    public static Identifier getDefaultRecipeId(ItemLike item) {
        return item.asItem().builtInRegistryHolder().key().identifier();
    }
}
