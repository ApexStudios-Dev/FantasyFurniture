package dev.apexstudios.fantasyfurniture.common.util;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import dev.apexstudios.apexcore.api.data.ProviderTypes;
import dev.apexstudios.apexcore.api.data.pack.PackGenerator;
import dev.apexstudios.apexcore.api.data.provider.RecipeProvider;
import dev.apexstudios.apexcore.api.data.provider.loot.LootTableProvider;
import dev.apexstudios.apexcore.api.data.provider.tag.IntrusiveTagProvider;
import dev.apexstudios.apexcore.api.placement.BlockItemPlacementEvent;
import dev.apexstudios.apexcore.api.util.ApexTags;
import dev.apexstudios.fantasyfurniture.common.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.common.station.FurnitureStationRecipeBuilder;
import dev.apexstudios.fantasyfurniture.common.station.FurnitureStationSetup;
import dev.apexstudios.registree.BaseRegistree;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockItemTagId;
import net.minecraft.tags.BlockItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.crafting.DifferenceIngredient;
import org.jspecify.annotations.Nullable;

public interface FurnitureDataUtil {
    @SafeVarargs
    static <TRegistry> void tag(IntrusiveTagProvider<TRegistry> provider, TRegistry element, @Nullable TagKey<TRegistry>... tags) {
        for(var tag : tags) {
            if(tag != null)
                provider.tag(tag).withElement(element);
        }
    }

    static void conversionRecipe(TagKey<Item> baseTag, TagKey<Item> furnitureTag, String hasKey, ItemLike result, RecipeProvider provider) {
        SingleItemRecipeBuilder
                .stonecutting(DifferenceIngredient.of(provider.tag(baseTag), provider.tag(furnitureTag)), RecipeCategory.MISC, result, 1)
                .unlockedBy(hasKey, provider.has(baseTag))
                .save(provider.output(), RecipeProvider.recipeKeyWithPrefix(result, "conversion/"));
    }

    static DataGenContext.Builder context(BaseRegistree<?> registree, String englishName, String recipeGroup) {
        return new DataGenContext.Builder(registree, englishName, recipeGroup);
    }

    static DataGenContext createContext(BaseRegistree<?> registree, String englishName, String recipeGroup) {
        return context(registree, englishName, recipeGroup).build();
    }

    enum DataType {
        LOOT_TABLE,
        BLOCK_TAG,
        ITEM_TAG,
        RECIPE,

        MODEL,
        LANGUAGE
    }

    final class DataGenContext {
        final BaseRegistree<?> registree;
        public final String englishName;
        final BlockFamily family;
        public final TagKey<Block> baseMineableTag;
        private final Map<String, TagKey<Block>> blockMineableTag;
        public final BlockItemTagId doorTag;
        public final BlockItemTagId stairsTag;
        public final BlockItemTagId buttonTag;
        public final BlockItemTagId pressurePlateTag;
        public final BlockItemTagId trapdoorTag;
        public final BlockItemTagId fenceTag;
        public final BlockItemTagId slabTag;
        private final Multimap<DataType, String> exclusions;

        private DataGenContext(Builder builder) {
            registree = builder.registree;
            englishName = builder.englishName;

            baseMineableTag = builder.baseMineableTag;
            blockMineableTag = Map.copyOf(builder.blockMineableTag);
            doorTag = builder.doorTag;
            stairsTag = builder.stairsTag;
            buttonTag = builder.buttonTag;
            pressurePlateTag = builder.pressurePlateTag;
            trapdoorTag = builder.trapdoorTag;
            fenceTag = builder.fenceTag;
            slabTag = builder.slabTag;
            exclusions = ImmutableMultimap.copyOf(builder.exclusions);

            var baseBlock = Objects.requireNonNullElse(builder.baseBlock, FurnitureUtil.Names.PLANKS);

            family = new BlockFamily.Builder(FurnitureUtil.Names.blockOrThrow(registree, baseBlock))
                    .recipeGroupPrefix(builder.recipeGroup)
                    .recipeUnlockedBy(Objects.requireNonNullElse(builder.recipeUnlockedBy, "has_" + baseBlock))
                    .stairs(FurnitureUtil.Names.blockOrThrow(registree, FurnitureUtil.Names.STAIRS))
                    .slab(FurnitureUtil.Names.blockOrThrow(registree, FurnitureUtil.Names.SLAB))
                    .fence(FurnitureUtil.Names.blockOrThrow(registree, FurnitureUtil.Names.FENCE))
                    .fenceGate(FurnitureUtil.Names.blockOrThrow(registree, FurnitureUtil.Names.FENCE_GATE))
                    .trapdoor(FurnitureUtil.Names.blockOrThrow(registree, FurnitureUtil.Names.TRAPDOOR))
                    .pressurePlate(FurnitureUtil.Names.blockOrThrow(registree, FurnitureUtil.Names.PRESSURE_PLATE))
                    .sign(
                            FurnitureUtil.Names.blockOrThrow(registree, FurnitureUtil.Names.SIGN),
                            FurnitureUtil.Names.blockOrThrow(registree, FurnitureUtil.Names.WALL_SIGN)
                    )
                    .customHangingSign(
                            FurnitureUtil.Names.blockOrThrow(registree, FurnitureUtil.Names.HANGING_SIGN),
                            FurnitureUtil.Names.blockOrThrow(registree, FurnitureUtil.Names.WALL_HANGING_SIGN)
                    ).getFamily();
        }

        public TagKey<Block> mineableTag(String name) {
            return blockMineableTag.getOrDefault(name, baseMineableTag);
        }

        public boolean excluded(DataType dataType, String name) {
            return exclusions.get(dataType).contains(name);
        }

        public void ifAllowed(DataType dataType, String name, Runnable runnable) {
            if(!excluded(dataType, name)) {
                runnable.run();
            }
        }

        public <TRegistry> boolean ifPresent(DataType dataType, ResourceKey<? extends Registry<TRegistry>> registryType, String name, Consumer<? super TRegistry> action) {
            if(excluded(dataType, name)) {
                return true;
            }

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
            if(ifPresent(dataType, Registries.ITEM, name, action)) {
                return true;
            }

            return block(dataType, name, block -> action.accept(block.asItem()));
        }

        public void register(PackGenerator<?> pack) {
            pack.providing(ProviderTypes.LOOT_TABLE, ($, provider) -> registerLootTables(provider))
                    .providing(ProviderTypes.BLOCK_TAGS, ($, provider) -> registerBlockTags(provider))
                    .providing(ProviderTypes.ITEM_TAGS, ($, provider) -> registerItemTags(provider))
                    .providing(ProviderTypes.RECIPES, ($, provider) -> registerRecipes(provider));
        }

        public void registerLootTables(LootTableProvider provider) {
            provider.fromRegistree(registree);

            provider.block(blocks -> {
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.PLANKS, blocks::dropSelf);
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.BRICKS, blocks::dropSelf);
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.WOOL, blocks::dropSelf);
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.CARPET, blocks::dropSelf);
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.DRESSER, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.STOOL, blocks::dropSelf);
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.CUSHION, blocks::dropSelf);
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.LOCKBOX, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.DRAWER, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.CHAIR, blocks::dropSelf);
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.BOOKSHELF, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.BED_SINGLE, block -> blocks.accept(block, blocks.createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD)));
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.BED_DOUBLE, blocks::dropSelf);
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.DOOR_SINGLE, block -> blocks.accept(block, blocks.createDoorTable(block)));
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.DOOR_DOUBLE, block -> blocks.accept(block, blocks.createDoorTable(block)));
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.DESK_LEFT, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.DESK_RIGHT, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.PAINTING_WIDE, blocks::dropSelf);
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.PAINTING_SMALL, blocks::dropSelf);
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.OVEN, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.CHEST, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.FLOOR_LIGHT, blocks::dropSelf);
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.CHANDELIER, blocks::dropSelf);
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.SHELF, blocks::dropSelf);
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.SOFA, blocks::dropSelf);
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.COUNTER, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.WALL_LIGHT, blocks::dropSelf);
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.BENCH, blocks::dropSelf);
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.WARDROBE, block -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)));
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.TABLE, blocks::dropSelf);
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.STAIRS, blocks::dropSelf);
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.SLAB, block -> blocks.accept(block, blocks.createSlabItemTable(block)));
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.FENCE, blocks::dropSelf);
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.FENCE_GATE, blocks::dropSelf);
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.TRAPDOOR, blocks::dropSelf);
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.PRESSURE_PLATE, blocks::dropSelf);
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.BUTTON, blocks::dropSelf);
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.HANGING_SIGN, blocks::dropSelf);
                // block(DataType.LOOT_TABLE, Names.WALL_HANGING_SIGN, blocks::dropSelf);
                block(DataType.LOOT_TABLE, FurnitureUtil.Names.SIGN, blocks::dropSelf);
                // block(DataType.LOOT_TABLE, Names.WALL_SIGN, blocks::dropSelf);
            });
        }

        public void registerBlockTags(IntrusiveTagProvider<Block> provider) {
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.PLANKS, block -> tag(provider, block, BlockTags.PLANKS));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.BRICKS, block -> tag(provider, block, Tags.Blocks.STONES));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.WOOL, block -> tag(provider, block, BlockTags.WOOL));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.CARPET, block -> tag(provider, block, BlockTags.WOOL_CARPETS));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.DRESSER, block -> tag(provider, block, BlockItemPlacementEvent.RENDERABLES, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.LOCKBOX, block -> tag(provider, block));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.DRAWER, block -> tag(provider, block));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.CHAIR, block -> tag(provider, block, BlockItemPlacementEvent.RENDERABLES, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.BOOKSHELF, block -> tag(provider, block, BlockItemPlacementEvent.RENDERABLES, Tags.Blocks.RELOCATION_NOT_SUPPORTED, BlockTags.ENCHANTMENT_POWER_PROVIDER));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.BED_SINGLE, block -> tag(provider, block, BlockTags.BEDS, BlockItemPlacementEvent.RENDERABLES, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.BED_DOUBLE, block -> tag(provider, block, BlockTags.BEDS, BlockItemPlacementEvent.RENDERABLES, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.DOOR_SINGLE, block -> tag(provider, block, doorTag.block(), BlockItemPlacementEvent.RENDERABLES, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.DOOR_DOUBLE, block -> tag(provider, block, doorTag.block(), BlockItemPlacementEvent.RENDERABLES, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.DESK_LEFT, block -> tag(provider, block, BlockItemPlacementEvent.RENDERABLES, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.DESK_RIGHT, block -> tag(provider, block, BlockItemPlacementEvent.RENDERABLES, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.PAINTING_WIDE, block -> tag(provider, block, BlockItemPlacementEvent.RENDERABLES, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.PAINTING_SMALL, block -> tag(provider, block));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.OVEN, block -> tag(provider, block, Tags.Blocks.PLAYER_WORKSTATIONS_FURNACES));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.CHEST, block -> tag(provider, block, BlockItemPlacementEvent.RENDERABLES, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.FLOOR_LIGHT, block -> tag(provider, block, BlockItemPlacementEvent.RENDERABLES, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.CHANDELIER, block -> tag(provider, block));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.SHELF, block -> tag(provider, block, BlockItemPlacementEvent.RENDERABLES));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.SOFA, block -> tag(provider, block, BlockItemPlacementEvent.RENDERABLES));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.COUNTER, block -> tag(provider, block, BlockItemPlacementEvent.RENDERABLES));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.WALL_LIGHT, block -> tag(provider, block, BlockItemPlacementEvent.RENDERABLES));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.BENCH, block -> tag(provider, block, ApexTags.Blocks.SEAT_PER_BLOCK, BlockItemPlacementEvent.RENDERABLES, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.WARDROBE, block -> tag(provider, block, BlockItemPlacementEvent.RENDERABLES, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.TABLE, block -> tag(provider, block, BlockItemPlacementEvent.RENDERABLES));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.STAIRS, block -> tag(provider, block, stairsTag.block()));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.SLAB, block -> tag(provider, block, slabTag.block()));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.FENCE, block -> tag(provider, block, fenceTag.block()));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.FENCE_GATE, block -> tag(provider, block, BlockTags.FENCE_GATES));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.TRAPDOOR, block -> tag(provider, block, trapdoorTag.block()));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.PRESSURE_PLATE, block -> tag(provider, block, pressurePlateTag.block()));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.BUTTON, block -> tag(provider, block, buttonTag.block()));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.HANGING_SIGN, block -> tag(provider, block, BlockTags.CEILING_HANGING_SIGNS));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.WALL_HANGING_SIGN, block -> tag(provider, block, BlockTags.WALL_HANGING_SIGNS));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.SIGN, block -> tag(provider, block, BlockTags.STANDING_SIGNS));
            block(DataType.BLOCK_TAG, FurnitureUtil.Names.WALL_SIGN, block -> tag(provider, block, BlockTags.WALL_SIGNS));

            registerMineableTags(provider);
        }

        private void registerMineableTags(IntrusiveTagProvider<Block> provider) {
            registerMineableTag(provider, FurnitureUtil.Names.PLANKS);
            registerMineableTag(provider, FurnitureUtil.Names.BRICKS);
            // registerMineableTag(provider, FurnitureUtil.Names.WOOL);
            // registerMineableTag(provider, FurnitureUtil.Names.CARPET);
            registerMineableTag(provider, FurnitureUtil.Names.DRESSER);
            registerMineableTag(provider, FurnitureUtil.Names.LOCKBOX);
            registerMineableTag(provider, FurnitureUtil.Names.DRAWER);
            registerMineableTag(provider, FurnitureUtil.Names.CHAIR);
            registerMineableTag(provider, FurnitureUtil.Names.BOOKSHELF);
            registerMineableTag(provider, FurnitureUtil.Names.BED_SINGLE);
            registerMineableTag(provider, FurnitureUtil.Names.BED_DOUBLE);
            registerMineableTag(provider, FurnitureUtil.Names.DOOR_SINGLE);
            registerMineableTag(provider, FurnitureUtil.Names.DOOR_DOUBLE);
            registerMineableTag(provider, FurnitureUtil.Names.DESK_LEFT);
            registerMineableTag(provider, FurnitureUtil.Names.DESK_RIGHT);
            registerMineableTag(provider, FurnitureUtil.Names.PAINTING_WIDE);
            registerMineableTag(provider, FurnitureUtil.Names.PAINTING_SMALL);
            registerMineableTag(provider, FurnitureUtil.Names.OVEN);
            registerMineableTag(provider, FurnitureUtil.Names.CHEST);
            registerMineableTag(provider, FurnitureUtil.Names.FLOOR_LIGHT);
            registerMineableTag(provider, FurnitureUtil.Names.CHANDELIER);
            registerMineableTag(provider, FurnitureUtil.Names.SHELF);
            registerMineableTag(provider, FurnitureUtil.Names.SOFA);
            registerMineableTag(provider, FurnitureUtil.Names.COUNTER);
            registerMineableTag(provider, FurnitureUtil.Names.WALL_LIGHT);
            registerMineableTag(provider, FurnitureUtil.Names.BENCH);
            registerMineableTag(provider, FurnitureUtil.Names.WARDROBE);
            registerMineableTag(provider, FurnitureUtil.Names.TABLE);
            registerMineableTag(provider, FurnitureUtil.Names.STAIRS);
            registerMineableTag(provider, FurnitureUtil.Names.SLAB);
            registerMineableTag(provider, FurnitureUtil.Names.FENCE);
            registerMineableTag(provider, FurnitureUtil.Names.FENCE_GATE);
            registerMineableTag(provider, FurnitureUtil.Names.TRAPDOOR);
            registerMineableTag(provider, FurnitureUtil.Names.PRESSURE_PLATE);
            registerMineableTag(provider, FurnitureUtil.Names.BUTTON);
            registerMineableTag(provider, FurnitureUtil.Names.HANGING_SIGN);
            registerMineableTag(provider, FurnitureUtil.Names.WALL_HANGING_SIGN);
            registerMineableTag(provider, FurnitureUtil.Names.SIGN);
            registerMineableTag(provider, FurnitureUtil.Names.WALL_SIGN);
        }

        public void registerMineableTag(IntrusiveTagProvider<Block> provider, String name) {
            block(DataType.BLOCK_TAG, name, block -> tag(provider, block, mineableTag(name)));
        }

        public void registerItemTags(IntrusiveTagProvider<Item> provider) {
            item(DataType.ITEM_TAG, FurnitureUtil.Names.PLANKS, item -> tag(provider, item, FantasyFurniture.FURNITURE_PLANKS));
            item(DataType.ITEM_TAG, FurnitureUtil.Names.BRICKS, item -> tag(provider, item, FantasyFurniture.FURNITURE_BRICKS));
            item(DataType.ITEM_TAG, FurnitureUtil.Names.WOOL, item -> tag(provider, item, FantasyFurniture.FURNITURE_WOOL));
            item(DataType.ITEM_TAG, FurnitureUtil.Names.CARPET, item -> tag(provider, item, ItemTags.WOOL_CARPETS));
            item(DataType.ITEM_TAG, FurnitureUtil.Names.BED_SINGLE, item -> tag(provider, item, ItemTags.BEDS));
            item(DataType.ITEM_TAG, FurnitureUtil.Names.BED_DOUBLE, item -> tag(provider, item, ItemTags.BEDS));
            item(DataType.ITEM_TAG, FurnitureUtil.Names.DOOR_SINGLE, item -> tag(provider, item, doorTag.item()));
            item(DataType.ITEM_TAG, FurnitureUtil.Names.DOOR_DOUBLE, item -> tag(provider, item, doorTag.item()));
            item(DataType.ITEM_TAG, FurnitureUtil.Names.OVEN, item -> tag(provider, item, Tags.Items.PLAYER_WORKSTATIONS_FURNACES));
            item(DataType.ITEM_TAG, FurnitureUtil.Names.STAIRS, item -> tag(provider, item, stairsTag.item()));
            item(DataType.ITEM_TAG, FurnitureUtil.Names.SLAB, item -> tag(provider, item, slabTag.item()));
            item(DataType.ITEM_TAG, FurnitureUtil.Names.FENCE, item -> tag(provider, item, fenceTag.item()));
            item(DataType.ITEM_TAG, FurnitureUtil.Names.FENCE_GATE, item -> tag(provider, item, ItemTags.FENCE_GATES));
            item(DataType.ITEM_TAG, FurnitureUtil.Names.TRAPDOOR, item -> tag(provider, item, trapdoorTag.item()));
            item(DataType.ITEM_TAG, FurnitureUtil.Names.PRESSURE_PLATE, item -> tag(provider, item, pressurePlateTag.item()));
            item(DataType.ITEM_TAG, FurnitureUtil.Names.BUTTON, item -> tag(provider, item, buttonTag.item()));
            item(DataType.ITEM_TAG, FurnitureUtil.Names.WALL_HANGING_SIGN, item -> tag(provider, item, ItemTags.HANGING_SIGNS));
            item(DataType.ITEM_TAG, FurnitureUtil.Names.SIGN, item -> tag(provider, item, ItemTags.SIGNS));
        }

        public void registerRecipes(RecipeProvider provider) {
            block(DataType.RECIPE, FurnitureUtil.Names.PLANKS, block -> conversionRecipe(ItemTags.PLANKS, FantasyFurniture.FURNITURE_PLANKS, "has_" + FurnitureUtil.Names.PLANKS, block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.BRICKS, block -> conversionRecipe(ItemTags.STONE_CRAFTING_MATERIALS, FantasyFurniture.FURNITURE_BRICKS, "has_" + FurnitureUtil.Names.BRICKS, block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.WOOL, block -> conversionRecipe(ItemTags.WOOL, FantasyFurniture.FURNITURE_WOOL, "has_" + FurnitureUtil.Names.WOOL, block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.CARPET, block -> furnitureStationRecipe(block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.DRESSER, block -> furnitureStationRecipe(block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.STOOL, block -> furnitureStationRecipe(block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.CUSHION, block -> furnitureStationRecipe(block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.LOCKBOX, block -> furnitureStationRecipe(block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.DRAWER, block -> furnitureStationRecipe(block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.CHAIR, block -> furnitureStationRecipe(block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.BOOKSHELF, block -> furnitureStationRecipe(block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.BED_SINGLE, block -> furnitureStationRecipe(block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.BED_DOUBLE, block -> furnitureStationRecipe(block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.DOOR_SINGLE, block -> furnitureStationRecipe(block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.DOOR_DOUBLE, block -> furnitureStationRecipe(block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.DESK_LEFT, block -> furnitureStationRecipe(block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.DESK_RIGHT, block -> furnitureStationRecipe(block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.PAINTING_WIDE, block -> furnitureStationRecipe(block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.PAINTING_SMALL, block -> furnitureStationRecipe(block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.OVEN, block -> furnitureStationRecipe(block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.CHEST, block -> furnitureStationRecipe(block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.FLOOR_LIGHT, block -> furnitureStationRecipe(block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.CHANDELIER, block -> furnitureStationRecipe(block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.SHELF, block -> furnitureStationRecipe(block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.SOFA, block -> furnitureStationRecipe(block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.COUNTER, block -> furnitureStationRecipe(block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.WALL_LIGHT, block -> furnitureStationRecipe(block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.BENCH, block -> furnitureStationRecipe(block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.WARDROBE, block -> furnitureStationRecipe(block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.TABLE, block -> furnitureStationRecipe(block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.STAIRS, block -> furnitureStationRecipe(block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.SLAB, block -> furnitureStationRecipe(block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.FENCE, block -> furnitureStationRecipe(block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.FENCE_GATE, block -> furnitureStationRecipe(block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.TRAPDOOR, block -> furnitureStationRecipe(block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.PRESSURE_PLATE, block -> furnitureStationRecipe(block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.BUTTON, block -> furnitureStationRecipe(block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.HANGING_SIGN, block -> furnitureStationRecipe(block, provider));
            block(DataType.RECIPE, FurnitureUtil.Names.SIGN, block -> furnitureStationRecipe(block, provider));
        }

        public String recipeGroup() {
            return family.getRecipeGroupPrefix().orElseThrow();
        }

        public String recipeUnlockedBy() {
            return family.getRecipeUnlockedBy().orElseThrow();
        }

        public void furnitureStationRecipe(ItemLike result, RecipeProvider provider) {
            var wool = registree.getValue(Registries.BLOCK, FurnitureUtil.Names.WOOL);
            var woolIngredient = wool == null ? null : Ingredient.of(wool);
            var baseBlock = family.getBaseBlock();

            FurnitureStationRecipeBuilder
                    .builder(RecipeCategory.MISC, Ingredient.of(baseBlock), woolIngredient, provider.tag(FurnitureStationSetup.BINDING_AGENT), result)
                    .group(recipeGroup())
                    .unlockedBy(recipeUnlockedBy(), provider.has(baseBlock))
                    .save(provider.output(), RecipeProvider.recipeKeyWithPrefix(result, "furniture_station/"));
        }

        public static final class Builder {
            private final BaseRegistree<?> registree;
            private final String englishName;
            private final String recipeGroup;
            private String baseBlock = FurnitureUtil.Names.PLANKS;
            private @Nullable String recipeUnlockedBy = null;
            private TagKey<Block> baseMineableTag = BlockTags.MINEABLE_WITH_AXE;
            private final Map<String, TagKey<Block>> blockMineableTag = new HashMap<>();
            private BlockItemTagId doorTag = BlockItemTags.WOODEN_DOORS;
            private BlockItemTagId stairsTag = BlockItemTags.WOODEN_STAIRS;
            private BlockItemTagId buttonTag = BlockItemTags.WOODEN_BUTTONS;
            private BlockItemTagId pressurePlateTag = BlockItemTags.WOODEN_PRESSURE_PLATES;
            private BlockItemTagId trapdoorTag = BlockItemTags.WOODEN_TRAPDOORS;
            private BlockItemTagId fenceTag = BlockItemTags.WOODEN_FENCES;
            private BlockItemTagId slabTag = BlockItemTags.WOODEN_SLABS;
            private final Multimap<DataType, String> exclusions = HashMultimap.create();

            private Builder(BaseRegistree<?> registree, String englishName, String recipeGroup) {
                this.registree = registree;
                this.englishName = englishName;
                this.recipeGroup = recipeGroup;
            }

            public Builder baseBlock(String baseBlock) {
                this.baseBlock = baseBlock;
                return recipeUnlockedBy("has_" + baseBlock);
            }

            public Builder recipeUnlockedBy(String recipeUnlockedBy) {
                this.recipeUnlockedBy = recipeUnlockedBy;
                return this;
            }

            public Builder mineableTag(String name, TagKey<Block> mineableTag) {
                blockMineableTag.put(name, mineableTag);
                return this;
            }

            public Builder removeMineableTag(String name) {
                blockMineableTag.remove(name);
                return this;
            }

            public Builder clearMineableTags() {
                blockMineableTag.clear();
                return this;
            }

            public Builder mineableTag(TagKey<Block> mineableTag) {
                baseMineableTag = mineableTag;
                return this;
            }

            public Builder doorTag(BlockItemTagId doorTag) {
                this.doorTag = doorTag;
                return this;
            }

            public Builder stairsTag(BlockItemTagId stairsTag) {
                this.stairsTag = stairsTag;
                return this;
            }

            public Builder buttonTag(BlockItemTagId buttonTag) {
                this.buttonTag = buttonTag;
                return this;
            }

            public Builder pressurePlateTag(BlockItemTagId pressurePlateTag) {
                this.pressurePlateTag = pressurePlateTag;
                return this;
            }

            public Builder trapdoorTag(BlockItemTagId trapdoorTag) {
                this.trapdoorTag = trapdoorTag;
                return this;
            }

            public Builder fenceTag(BlockItemTagId fenceTag) {
                this.fenceTag = fenceTag;
                return this;
            }

            public Builder slabTag(BlockItemTagId slabTag) {
                this.slabTag = slabTag;
                return this;
            }

            public Builder exclude(DataType dataType, String name) {
                exclusions.put(dataType, name);
                return this;
            }

            public Builder excludeAll(String name) {
                for(var dataType : DataType.values()) {
                    exclude(dataType, name);
                }

                return this;
            }

            public Builder include(DataType dataType, String name) {
                exclusions.remove(dataType, name);
                return this;
            }

            public Builder includeAll(DataType dataType) {
                exclusions.removeAll(dataType);
                return this;
            }

            public Builder includeAll() {
                exclusions.clear();
                return this;
            }

            public Builder reset() {
                return clearMineableTags().includeAll();
            }

            @SuppressWarnings("DataFlowIssue")
            public Builder stoneLike() {
                return reset()
                        .baseBlock(FurnitureUtil.Names.BRICKS)
                        .mineableTag(BlockTags.MINEABLE_WITH_AXE)
                        .doorTag(BlockItemTags.DOORS)
                        .stairsTag(BlockItemTags.STAIRS)
                        .buttonTag(BlockItemTags.STONE_BUTTONS)
                        .pressurePlateTag(new BlockItemTagId(BlockTags.STONE_PRESSURE_PLATES, null))
                        .trapdoorTag(BlockItemTags.TRAPDOORS)
                        .fenceTag(BlockItemTags.FENCES)
                        .slabTag(BlockItemTags.SLABS);
            }

            public DataGenContext build() {
                return new DataGenContext(this);
            }
        }
    }
}
