package dev.apexstudios.fantasyfurniture.util;

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
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
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
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.PLANKS, blocks::dropSelf);
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.BRICKS, blocks::dropSelf);
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.WOOL, blocks::dropSelf);
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.CARPET, blocks::dropSelf);
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.DRESSER, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.STOOL, blocks::dropSelf);
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.CUSHION, blocks::dropSelf);
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.LOCKBOX, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.DRAWER, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.CHAIR, blocks::dropSelf);
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.BOOKSHELF, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.BED_SINGLE, block -> blocks.accept(block, blocks.createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD)));
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.BED_DOUBLE, block -> blocks.accept(block, blocks.createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD)));
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.DOOR_SINGLE, block -> blocks.accept(block, blocks.createDoorTable(block)));
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.DOOR_DOUBLE, block -> blocks.accept(block, blocks.createDoorTable(block)));
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.DESK_LEFT, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.DESK_RIGHT, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.PAINTING_WIDE, blocks::dropSelf);
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.PAINTING_SMALL, blocks::dropSelf);
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.OVEN, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.CHEST, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.FLOOR_LIGHT, blocks::dropSelf);
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.CHANDELIER, blocks::dropSelf);
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.SHELF, blocks::dropSelf);
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.SOFA, blocks::dropSelf);
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.COUNTER, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.WALL_LIGHT, blocks::dropSelf);
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.BENCH, blocks::dropSelf);
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.WARDROBE, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.TABLE, blocks::dropSelf);
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.STAIRS, blocks::dropSelf);
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.SLAB, block -> blocks.accept(block, blocks.createSlabItemTable(block)));
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.FENCE, blocks::dropSelf);
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.FENCE_GATE, blocks::dropSelf);
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.TRAPDOOR, blocks::dropSelf);
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.PRESSURE_PLATE, blocks::dropSelf);
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.BUTTON, blocks::dropSelf);
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.HANGING_SIGN, blocks::dropSelf);
            // Names.block(context.registree, Names.WALL_HANGING_SIGN, blocks::dropSelf);
            FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.SIGN, blocks::dropSelf);
            // Names.block(context.registree, Names.WALL_SIGN, blocks::dropSelf);
        });
    }

    static void registerBlockTags(DataGenContext context, IntrusiveTagProvider<Block> provider) {
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.PLANKS, block -> tag(provider, block, context.mineableTag, BlockTags.PLANKS));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.BRICKS, block -> tag(provider, block, context.mineableTag, Tags.Blocks.STONES));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.WOOL, block -> tag(provider, block, BlockTags.WOOL));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.CARPET, block -> tag(provider, block, BlockTags.WOOL_CARPETS));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.DRESSER, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.LOCKBOX, block -> tag(provider, block, context.mineableTag));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.DRAWER, block -> tag(provider, block, context.mineableTag));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.CHAIR, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.SEAT_ORIGIN_ONLY, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.BOOKSHELF, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED, BlockTags.ENCHANTMENT_POWER_PROVIDER));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.BED_SINGLE, block -> tag(provider, block, context.mineableTag, BlockTags.BEDS, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.BED_DOUBLE, block -> tag(provider, block, context.mineableTag, BlockTags.BEDS, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.DOOR_SINGLE, block -> tag(provider, block, context.mineableTag, context.doorTag.block(), ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.DOOR_DOUBLE, block -> tag(provider, block, context.mineableTag, context.doorTag.block(), ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.DESK_LEFT, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.DESK_RIGHT, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.PAINTING_WIDE, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.PAINTING_SMALL, block -> tag(provider, block, context.mineableTag));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.OVEN, block -> tag(provider, block, BlockTags.MINEABLE_WITH_PICKAXE, Tags.Blocks.PLAYER_WORKSTATIONS_FURNACES));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.CHEST, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.FLOOR_LIGHT, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.CHANDELIER, block -> tag(provider, block, context.mineableTag));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.SHELF, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.SOFA, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.COUNTER, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.WALL_LIGHT, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.BENCH, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.SEAT_ORIGIN_ONLY, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.WARDROBE, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.TABLE, block -> tag(provider, block, context.mineableTag, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.STAIRS, block -> tag(provider, block, context.mineableTag, context.stairsTag));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.SLAB, block -> tag(provider, block, context.mineableTag, context.slabTag.block()));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.FENCE, block -> tag(provider, block, context.mineableTag, context.fenceTag.block()));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.FENCE_GATE, block -> tag(provider, block, context.mineableTag, BlockTags.FENCE_GATES));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.TRAPDOOR, block -> tag(provider, block, context.mineableTag, context.trapdoorTag.block()));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.PRESSURE_PLATE, block -> tag(provider, block, context.mineableTag, context.pressurePlateTag.block()));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.BUTTON, block -> tag(provider, block, context.mineableTag, context.buttonTag.block()));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.HANGING_SIGN, block -> tag(provider, block, context.mineableTag, BlockTags.CEILING_HANGING_SIGNS));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.WALL_HANGING_SIGN, block -> tag(provider, block, context.mineableTag, BlockTags.WALL_HANGING_SIGNS));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.SIGN, block -> tag(provider, block, context.mineableTag, BlockTags.STANDING_SIGNS));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.WALL_SIGN, block -> tag(provider, block, context.mineableTag, BlockTags.WALL_SIGNS));
    }

    static void registerItemTags(DataGenContext context, IntrusiveTagProvider<Item> provider) {
        FurnitureUtil.Names.item(context.registree, FurnitureUtil.Names.PLANKS, item -> tag(provider, item, FantasyFurniture.FURNITURE_PLANKS));
        FurnitureUtil.Names.item(context.registree, FurnitureUtil.Names.BRICKS, item -> tag(provider, item, FantasyFurniture.FURNITURE_BRICKS));
        FurnitureUtil.Names.item(context.registree, FurnitureUtil.Names.WOOL, item -> tag(provider, item, FantasyFurniture.FURNITURE_WOOL));
        FurnitureUtil.Names.item(context.registree, FurnitureUtil.Names.CARPET, item -> tag(provider, item, ItemTags.WOOL_CARPETS));
        FurnitureUtil.Names.item(context.registree, FurnitureUtil.Names.BED_SINGLE, item -> tag(provider, item, ItemTags.BEDS));
        FurnitureUtil.Names.item(context.registree, FurnitureUtil.Names.BED_DOUBLE, item -> tag(provider, item, ItemTags.BEDS));
        FurnitureUtil.Names.item(context.registree, FurnitureUtil.Names.DOOR_SINGLE, item -> tag(provider, item, context.doorTag.item()));
        FurnitureUtil.Names.item(context.registree, FurnitureUtil.Names.DOOR_DOUBLE, item -> tag(provider, item, context.doorTag.item()));
        FurnitureUtil.Names.item(context.registree, FurnitureUtil.Names.OVEN, item -> tag(provider, item, Tags.Items.PLAYER_WORKSTATIONS_FURNACES));
        FurnitureUtil.Names.item(context.registree, FurnitureUtil.Names.STAIRS, item -> tag(provider, item, ItemTags.STAIRS));
        FurnitureUtil.Names.item(context.registree, FurnitureUtil.Names.SLAB, item -> tag(provider, item, context.slabTag.item()));
        FurnitureUtil.Names.item(context.registree, FurnitureUtil.Names.FENCE, item -> tag(provider, item, context.fenceTag.item()));
        FurnitureUtil.Names.item(context.registree, FurnitureUtil.Names.FENCE_GATE, item -> tag(provider, item, ItemTags.FENCE_GATES));
        FurnitureUtil.Names.item(context.registree, FurnitureUtil.Names.TRAPDOOR, item -> tag(provider, item, context.trapdoorTag.item()));
        FurnitureUtil.Names.item(context.registree, FurnitureUtil.Names.PRESSURE_PLATE, item -> tag(provider, item, context.pressurePlateTag.item()));
        FurnitureUtil.Names.item(context.registree, FurnitureUtil.Names.BUTTON, item -> tag(provider, item, context.buttonTag.item()));
        FurnitureUtil.Names.item(context.registree, FurnitureUtil.Names.WALL_HANGING_SIGN, item -> tag(provider, item, ItemTags.HANGING_SIGNS));
        FurnitureUtil.Names.item(context.registree, FurnitureUtil.Names.SIGN, item -> tag(provider, item, ItemTags.SIGNS));
    }

    @SafeVarargs
    static <TRegistry> void tag(IntrusiveTagProvider<TRegistry> provider, TRegistry element, TagKey<TRegistry>... tags) {
        for(var tag : tags) {
            if(tag != null)
                provider.tag(tag).withElement(element);
        }
    }

    static void registerRecipes(DataGenContext context, RecipeProvider provider, FeatureFlagSet enabledFeatures) {
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.PLANKS, block -> conversionRecipe(ItemTags.PLANKS, FantasyFurniture.FURNITURE_PLANKS, "has_" + FurnitureUtil.Names.PLANKS, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.BRICKS, block -> conversionRecipe(ItemTags.STONE_CRAFTING_MATERIALS, FantasyFurniture.FURNITURE_BRICKS, "has_" + FurnitureUtil.Names.BRICKS, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.WOOL, block -> conversionRecipe(ItemTags.WOOL, FantasyFurniture.FURNITURE_WOOL, "has_" + FurnitureUtil.Names.WOOL, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.CARPET, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.DRESSER, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.STOOL, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.CUSHION, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.LOCKBOX, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.DRAWER, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.CHAIR, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.BOOKSHELF, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.BED_SINGLE, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.BED_DOUBLE, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.DOOR_SINGLE, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.DOOR_DOUBLE, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.DESK_LEFT, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.DESK_RIGHT, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.PAINTING_WIDE, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.PAINTING_SMALL, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.OVEN, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.CHEST, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.FLOOR_LIGHT, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.CHANDELIER, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.SHELF, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.SOFA, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.COUNTER, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.WALL_LIGHT, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.BENCH, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.WARDROBE, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.TABLE, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.STAIRS, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.SLAB, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.FENCE, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.FENCE_GATE, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.TRAPDOOR, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.PRESSURE_PLATE, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.BUTTON, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.HANGING_SIGN, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
        FurnitureUtil.Names.block(context.registree, FurnitureUtil.Names.SIGN, block -> furnitureStationRecipe(context, block, provider, enabledFeatures));
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
            TagPair slabTag
    ) { }
}
