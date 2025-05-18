package dev.apexstudios.fantasyfurniture.util;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import dev.apexstudios.apexcore.lib.data.ProviderTypes;
import dev.apexstudios.apexcore.lib.data.pack.PackGenerator;
import dev.apexstudios.apexcore.lib.data.provider.RecipeProvider;
import dev.apexstudios.apexcore.lib.data.provider.loot.LootTableProvider;
import dev.apexstudios.apexcore.lib.data.provider.tag.IntrusiveTagProvider;
import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.apexcore.lib.util.ApexTags;
import dev.apexstudios.apexcore.lib.util.TagPair;
import dev.apexstudios.fantasyfurniture.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.station.FurnitureStationRecipeBuilder;
import dev.apexstudios.fantasyfurniture.station.FurnitureStationSetup;
import java.util.function.Consumer;
import net.minecraft.Util;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.NeoForgeConditions;
import net.neoforged.neoforge.common.crafting.DifferenceIngredient;

public interface FurnitureDataUtil {
    static void registerDataGen(DataGenContext context, PackGenerator<?> pack) {
        pack.providing(ProviderTypes.LOOT_TABLE, ($, provider) -> registerLootTables(context, provider))
                .providing(ProviderTypes.BLOCK_TAGS, ($, provider) -> registerBlockTags(context, provider))
                .providing(ProviderTypes.ITEM_TAGS, ($, provider) -> registerItemTags(context, provider))
                .providing(ProviderTypes.RECIPES, ($, provider) -> registerRecipes(context, provider, $.enabledFeatures()));
    }

    static void registerLootTables(DataGenContext context, LootTableProvider provider) {
        provider.fromRegistree(context.registree);

        provider.block(blocks -> {
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.PLANKS, blocks::dropSelf);
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.BRICKS, blocks::dropSelf);
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.WOOL, blocks::dropSelf);
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.CARPET, blocks::dropSelf);
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.DRESSER, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.STOOL, blocks::dropSelf);
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.CUSHION, blocks::dropSelf);
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.LOCKBOX, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.DRAWER, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.CHAIR, blocks::dropSelf);
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.BOOKSHELF, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.BED_SINGLE, block -> blocks.accept(block, blocks.createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD)));
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.BED_DOUBLE, block -> blocks.accept(block, blocks.createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD)));
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.DOOR_SINGLE, block -> blocks.accept(block, blocks.createDoorTable(block)));
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.DOOR_DOUBLE, block -> blocks.accept(block, blocks.createDoorTable(block)));
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.DESK_LEFT, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.DESK_RIGHT, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.PAINTING_WIDE, blocks::dropSelf);
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.PAINTING_SMALL, blocks::dropSelf);
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.OVEN, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.CHEST, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.FLOOR_LIGHT, blocks::dropSelf);
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.CHANDELIER, blocks::dropSelf);
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.SHELF, blocks::dropSelf);
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.SOFA, blocks::dropSelf);
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.COUNTER, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.WALL_LIGHT, blocks::dropSelf);
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.BENCH, blocks::dropSelf);
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.WARDROBE, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.TABLE, blocks::dropSelf);
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.STAIRS, blocks::dropSelf);
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.SLAB, block -> blocks.accept(block, blocks.createSlabItemTable(block)));
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.FENCE, blocks::dropSelf);
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.FENCE_GATE, blocks::dropSelf);
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.TRAPDOOR, blocks::dropSelf);
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.PRESSURE_PLATE, blocks::dropSelf);
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.BUTTON, blocks::dropSelf);
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.HANGING_SIGN, blocks::dropSelf);
            // context.block(DataType.LOOT_TABLE, Names.WALL_HANGING_SIGN, blocks::dropSelf);
            context.block(DataType.LOOT_TABLE, FurnitureUtil.Names.SIGN, blocks::dropSelf);
            // context.block(DataType.LOOT_TABLE, Names.WALL_SIGN, blocks::dropSelf);
        });
    }

    static void registerBlockTags(DataGenContext context, IntrusiveTagProvider<Block> provider) {
        context.block(DataType.RECIPE, FurnitureUtil.Names.PLANKS, block -> tag(provider, block, context.mineableTag, BlockTags.PLANKS));
        context.block(DataType.RECIPE, FurnitureUtil.Names.BRICKS, block -> tag(provider, block, context.mineableTag, Tags.Blocks.STONES));
        context.block(DataType.RECIPE, FurnitureUtil.Names.WOOL, block -> tag(provider, block, BlockTags.WOOL));
        context.block(DataType.RECIPE, FurnitureUtil.Names.CARPET, block -> tag(provider, block, BlockTags.WOOL_CARPETS));
        context.block(DataType.RECIPE, FurnitureUtil.Names.DRESSER, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        context.block(DataType.RECIPE, FurnitureUtil.Names.LOCKBOX, block -> tag(provider, block, context.mineableTag));
        context.block(DataType.RECIPE, FurnitureUtil.Names.DRAWER, block -> tag(provider, block, context.mineableTag));
        context.block(DataType.RECIPE, FurnitureUtil.Names.CHAIR, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.SEAT_ORIGIN_ONLY, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        context.block(DataType.RECIPE, FurnitureUtil.Names.BOOKSHELF, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED, BlockTags.ENCHANTMENT_POWER_PROVIDER));
        context.block(DataType.RECIPE, FurnitureUtil.Names.BED_SINGLE, block -> tag(provider, block, context.mineableTag, BlockTags.BEDS, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        context.block(DataType.RECIPE, FurnitureUtil.Names.BED_DOUBLE, block -> tag(provider, block, context.mineableTag, BlockTags.BEDS, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        context.block(DataType.RECIPE, FurnitureUtil.Names.DOOR_SINGLE, block -> tag(provider, block, context.mineableTag, context.doorTag.block(), ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        context.block(DataType.RECIPE, FurnitureUtil.Names.DOOR_DOUBLE, block -> tag(provider, block, context.mineableTag, context.doorTag.block(), ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        context.block(DataType.RECIPE, FurnitureUtil.Names.DESK_LEFT, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        context.block(DataType.RECIPE, FurnitureUtil.Names.DESK_RIGHT, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        context.block(DataType.RECIPE, FurnitureUtil.Names.PAINTING_WIDE, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        context.block(DataType.RECIPE, FurnitureUtil.Names.PAINTING_SMALL, block -> tag(provider, block, context.mineableTag));
        context.block(DataType.RECIPE, FurnitureUtil.Names.OVEN, block -> tag(provider, block, BlockTags.MINEABLE_WITH_PICKAXE, Tags.Blocks.PLAYER_WORKSTATIONS_FURNACES));
        context.block(DataType.RECIPE, FurnitureUtil.Names.CHEST, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        context.block(DataType.RECIPE, FurnitureUtil.Names.FLOOR_LIGHT, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        context.block(DataType.RECIPE, FurnitureUtil.Names.CHANDELIER, block -> tag(provider, block, context.mineableTag));
        context.block(DataType.RECIPE, FurnitureUtil.Names.SHELF, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST));
        context.block(DataType.RECIPE, FurnitureUtil.Names.SOFA, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST));
        context.block(DataType.RECIPE, FurnitureUtil.Names.COUNTER, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST));
        context.block(DataType.RECIPE, FurnitureUtil.Names.WALL_LIGHT, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST));
        context.block(DataType.RECIPE, FurnitureUtil.Names.BENCH, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.SEAT_ORIGIN_ONLY, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        context.block(DataType.RECIPE, FurnitureUtil.Names.WARDROBE, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        context.block(DataType.RECIPE, FurnitureUtil.Names.TABLE, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST));
        context.block(DataType.RECIPE, FurnitureUtil.Names.STAIRS, block -> tag(provider, block, context.mineableTag, context.stairsTag));
        context.block(DataType.RECIPE, FurnitureUtil.Names.SLAB, block -> tag(provider, block, context.mineableTag, context.slabTag.block()));
        context.block(DataType.RECIPE, FurnitureUtil.Names.FENCE, block -> tag(provider, block, context.mineableTag, context.fenceTag.block()));
        context.block(DataType.RECIPE, FurnitureUtil.Names.FENCE_GATE, block -> tag(provider, block, context.mineableTag, BlockTags.FENCE_GATES));
        context.block(DataType.RECIPE, FurnitureUtil.Names.TRAPDOOR, block -> tag(provider, block, context.mineableTag, context.trapdoorTag.block()));
        context.block(DataType.RECIPE, FurnitureUtil.Names.PRESSURE_PLATE, block -> tag(provider, block, context.mineableTag, context.pressurePlateTag.block()));
        context.block(DataType.RECIPE, FurnitureUtil.Names.BUTTON, block -> tag(provider, block, context.mineableTag, context.buttonTag.block()));
        context.block(DataType.RECIPE, FurnitureUtil.Names.HANGING_SIGN, block -> tag(provider, block, context.mineableTag, BlockTags.CEILING_HANGING_SIGNS));
        context.block(DataType.RECIPE, FurnitureUtil.Names.WALL_HANGING_SIGN, block -> tag(provider, block, context.mineableTag, BlockTags.WALL_HANGING_SIGNS));
        context.block(DataType.RECIPE, FurnitureUtil.Names.SIGN, block -> tag(provider, block, context.mineableTag, BlockTags.STANDING_SIGNS));
        context.block(DataType.RECIPE, FurnitureUtil.Names.WALL_SIGN, block -> tag(provider, block, context.mineableTag, BlockTags.WALL_SIGNS));
    }

    static void registerItemTags(DataGenContext context, IntrusiveTagProvider<Item> provider) {
        context.item(DataType.ITEM_TAG, FurnitureUtil.Names.PLANKS, item -> tag(provider, item, FantasyFurniture.FURNITURE_PLANKS));
        context.item(DataType.ITEM_TAG, FurnitureUtil.Names.BRICKS, item -> tag(provider, item, FantasyFurniture.FURNITURE_BRICKS));
        context.item(DataType.ITEM_TAG, FurnitureUtil.Names.WOOL, item -> tag(provider, item, FantasyFurniture.FURNITURE_WOOL));
        context.item(DataType.ITEM_TAG, FurnitureUtil.Names.CARPET, item -> tag(provider, item, ItemTags.WOOL_CARPETS));
        context.item(DataType.ITEM_TAG, FurnitureUtil.Names.BED_SINGLE, item -> tag(provider, item, ItemTags.BEDS));
        context.item(DataType.ITEM_TAG, FurnitureUtil.Names.BED_DOUBLE, item -> tag(provider, item, ItemTags.BEDS));
        context.item(DataType.ITEM_TAG, FurnitureUtil.Names.DOOR_SINGLE, item -> tag(provider, item, context.doorTag.item()));
        context.item(DataType.ITEM_TAG, FurnitureUtil.Names.DOOR_DOUBLE, item -> tag(provider, item, context.doorTag.item()));
        context.item(DataType.ITEM_TAG, FurnitureUtil.Names.OVEN, item -> tag(provider, item, Tags.Items.PLAYER_WORKSTATIONS_FURNACES));
        context.item(DataType.ITEM_TAG, FurnitureUtil.Names.STAIRS, item -> tag(provider, item, ItemTags.STAIRS));
        context.item(DataType.ITEM_TAG, FurnitureUtil.Names.SLAB, item -> tag(provider, item, context.slabTag.item()));
        context.item(DataType.ITEM_TAG, FurnitureUtil.Names.FENCE, item -> tag(provider, item, context.fenceTag.item()));
        context.item(DataType.ITEM_TAG, FurnitureUtil.Names.FENCE_GATE, item -> tag(provider, item, ItemTags.FENCE_GATES));
        context.item(DataType.ITEM_TAG, FurnitureUtil.Names.TRAPDOOR, item -> tag(provider, item, context.trapdoorTag.item()));
        context.item(DataType.ITEM_TAG, FurnitureUtil.Names.PRESSURE_PLATE, item -> tag(provider, item, context.pressurePlateTag.item()));
        context.item(DataType.ITEM_TAG, FurnitureUtil.Names.BUTTON, item -> tag(provider, item, context.buttonTag.item()));
        context.item(DataType.ITEM_TAG, FurnitureUtil.Names.WALL_HANGING_SIGN, item -> tag(provider, item, ItemTags.HANGING_SIGNS));
        context.item(DataType.ITEM_TAG, FurnitureUtil.Names.SIGN, item -> tag(provider, item, ItemTags.SIGNS));
    }

    @SafeVarargs
    static <TRegistry> void tag(IntrusiveTagProvider<TRegistry> provider, TRegistry element, TagKey<TRegistry>... tags) {
        for(var tag : tags) {
            if(tag != null)
                provider.tag(tag).withElement(element);
        }
    }

    static void registerRecipes(DataGenContext context, RecipeProvider provider, FeatureFlagSet enabledFeatures) {
        context.block(DataType.RECIPE, FurnitureUtil.Names.PLANKS, block -> conversionRecipe(ItemTags.PLANKS, FantasyFurniture.FURNITURE_PLANKS, "has_" + FurnitureUtil.Names.PLANKS, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.BRICKS, block -> conversionRecipe(ItemTags.STONE_CRAFTING_MATERIALS, FantasyFurniture.FURNITURE_BRICKS, "has_" + FurnitureUtil.Names.BRICKS, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.WOOL, block -> conversionRecipe(ItemTags.WOOL, FantasyFurniture.FURNITURE_WOOL, "has_" + FurnitureUtil.Names.WOOL, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.CARPET, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.DRESSER, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.STOOL, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.CUSHION, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.LOCKBOX, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.DRAWER, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.CHAIR, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.BOOKSHELF, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.BED_SINGLE, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.BED_DOUBLE, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.DOOR_SINGLE, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.DOOR_DOUBLE, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.DESK_LEFT, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.DESK_RIGHT, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.PAINTING_WIDE, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.PAINTING_SMALL, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.OVEN, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.CHEST, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.FLOOR_LIGHT, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.CHANDELIER, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.SHELF, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.SOFA, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.COUNTER, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.WALL_LIGHT, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.BENCH, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.WARDROBE, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.TABLE, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.STAIRS, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.SLAB, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.FENCE, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.FENCE_GATE, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.TRAPDOOR, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.PRESSURE_PLATE, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.BUTTON, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.HANGING_SIGN, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        context.block(DataType.RECIPE, FurnitureUtil.Names.SIGN, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
    }

    static void conversionRecipe(TagKey<Item> baseTag, TagKey<Item> furnitureTag, String hasKey, ItemLike result, RecipeProvider provider, FeatureFlagSet enabledFeatures) {
        var output = result.asItem().isEnabled(enabledFeatures) ? provider.output() : provider.output().withConditions(NeoForgeConditions.featureFlagsEnabled(FantasyFurniture.EXPERIMENTAL));

        SingleItemRecipeBuilder
                .stonecutting(DifferenceIngredient.of(provider.tag(baseTag), provider.tag(furnitureTag)), RecipeCategory.MISC, result)
                .unlockedBy(hasKey, provider.has(baseTag))
                .save(output, RecipeProvider.recipeKeyWithPrefix(result, "conversion/"));
    }

    static void furnitureStationRecipe(DataGenContext context, ItemLike result, RecipeProvider provider, FeatureFlagSet enabledFeatures) {
        var wool = context.registree.getValue(Registries.BLOCK, FurnitureUtil.Names.WOOL);
        var woolIngredient = wool == null ? null : Ingredient.of(wool);
        var output = result.asItem().isEnabled(enabledFeatures) ? provider.output() : provider.output().withConditions(NeoForgeConditions.featureFlagsEnabled(FantasyFurniture.EXPERIMENTAL));

        FurnitureStationRecipeBuilder
                .builder(RecipeCategory.MISC, Ingredient.of(context.family.getBaseBlock()), woolIngredient, provider.tag(FurnitureStationSetup.BINDING_AGENT), result)
                .group(context.family.getRecipeGroupPrefix().orElse(null))
                .unlockedBy(context.family.getRecipeUnlockedBy().orElseGet(() -> RecipeProvider.getHasName(context.family.getBaseBlock())), provider.has(context.family.getBaseBlock()))
                .save(output, RecipeProvider.recipeKeyWithPrefix(result, "furniture_station/"));
    }

    record DataGenContext(
            Registree registree,
            String englishName,
            BlockFamily family,
            TagKey<Block> mineableTag,
            TagPair doorTag,
            TagKey<Block> stairsTag,
            TagPair buttonTag,
            TagPair pressurePlateTag,
            TagPair trapdoorTag,
            TagPair fenceTag,
            TagPair slabTag,
            Multimap<DataType, String> exclusions
    ) {
        public DataGenContext {
            exclusions = Multimaps.unmodifiableMultimap(exclusions);
        }

        public DataGenContext(
                Registree registree,
                String englishName,
                BlockFamily family,
                TagKey<Block> mineableTag,
                TagPair doorTag,
                TagKey<Block> stairsTag,
                TagPair buttonTag,
                TagPair pressurePlateTag,
                TagPair trapdoorTag,
                TagPair fenceTag,
                TagPair slabTag
        ) {
            this(registree, englishName, family, mineableTag, doorTag, stairsTag, buttonTag, pressurePlateTag, trapdoorTag, fenceTag, slabTag, HashMultimap.create());
        }

        public DataGenContext(
                Registree registree,
                String englishName,
                BlockFamily family,
                TagKey<Block> mineableTag,
                TagPair doorTag,
                TagKey<Block> stairsTag,
                TagPair buttonTag,
                TagPair pressurePlateTag,
                TagPair trapdoorTag,
                TagPair fenceTag,
                TagPair slabTag,
                Consumer<Multimap<DataType, String>> exclusions
        ) {
            this(registree, englishName, family, mineableTag, doorTag, stairsTag, buttonTag, pressurePlateTag, trapdoorTag, fenceTag, slabTag, Util.make(HashMultimap.create(), exclusions));
        }

        public boolean excluded(DataType dataType, String name) {
            return exclusions.get(dataType).contains(name);
        }

        public void ifAllowed(DataType dataType, String name, Runnable runnable) {
            if(!excluded(dataType, name))
                runnable.run();
        }

        public <TRegistry> boolean ifPresent(DataType dataType, ResourceKey<? extends Registry<TRegistry>> registryType, String name, Consumer<? super TRegistry> action) {
            if(excluded(dataType, name))
                return true;

            var value = registree.getValue(registryType, name);

            if(value != null) {
                action.accept(value);
                return true;
            }

            return false;
        }

        public boolean block(DataType dataType, String name, Consumer<? super Block> action) {
            return ifPresent(dataType, Registries.BLOCK, name, action);
        }

        public boolean item(DataType dataType, String name, Consumer<? super Item> action) {
            if(ifPresent(dataType, Registries.ITEM, name, action))
                return true;

            return block(dataType, name, block -> action.accept(block.asItem()));
        }
    }

    enum DataType {
        LOOT_TABLE,
        BLOCK_TAG,
        ITEM_TAG,
        RECIPE,

        MODEL,
        LANGUAGE
    }
}
