package dev.apexstudios.fantasyfurniture.common.data;

import com.google.common.base.Suppliers;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.mojang.datafixers.util.Pair;
import dev.apexstudios.apexcore.api.block.Dyeable;
import dev.apexstudios.apexcore.api.data.BlockLootProvider;
import dev.apexstudios.apexcore.api.data.BlockTagsProvider;
import dev.apexstudios.apexcore.api.data.ItemTagsProvider;
import dev.apexstudios.apexcore.api.data.RecipeProvider;
import dev.apexstudios.apexcore.api.util.ApexTags;
import dev.apexstudios.apexcore.api.util.ApexUtil;
import dev.apexstudios.apexcore.api.util.StringHelper;
import dev.apexstudios.fantasyfurniture.common.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.common.block.property.CounterConnection;
import dev.apexstudios.fantasyfurniture.common.block.property.ShelfConnection;
import dev.apexstudios.fantasyfurniture.common.block.property.SofaConnection;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import dev.apexstudios.registree.api.Registree;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.PackType;
import net.minecraft.tags.BlockItemTagId;
import net.minecraft.tags.BlockItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.apache.commons.lang3.function.TriConsumer;
import org.jspecify.annotations.Nullable;

public final class DataGenContext {
    public final Registree registree;
    public final String englishName;
    private final Supplier<BlockFamily> family;
    public final @Nullable TagKey<Block> mineableTag;
    public final @Nullable BlockItemTagId doorTag;
    public final @Nullable BlockItemTagId stairsTag;
    public final @Nullable BlockItemTagId buttonTag;
    public final @Nullable BlockItemTagId pressurePlateTag;
    public final @Nullable BlockItemTagId trapdoorTag;
    public final @Nullable BlockItemTagId fenceTag;
    public final @Nullable BlockItemTagId fenceGateTag;
    public final @Nullable BlockItemTagId slabTag;
    private final BiConsumer<GatherDataEvent, DataGenContext> extraData;
    private final TriConsumer<BlockModelGenerators, ItemModelGenerators, DataGenContext> extraModels;
    private final BiConsumer<BlockLootProvider, DataGenContext> extraBlockLoot;
    private final BiConsumer<RecipeProvider, DataGenContext> extraRecipes;
    private final BiConsumer<LanguageProvider, DataGenContext> extraLanguage;
    private final BiConsumer<BlockTagsProvider, DataGenContext> extraBlockTags;
    private final BiConsumer<ItemTagsProvider, DataGenContext> extraItemTags;
    private final Multimap<DataGenType, String> exclusions;

    private DataGenContext(Builder builder) {
        registree = builder.registree;
        englishName = builder.englishName;
        mineableTag = builder.mineableTag;
        doorTag = builder.doorTag;
        stairsTag = builder.stairsTag;
        buttonTag = builder.buttonTag;
        pressurePlateTag = builder.pressurePlateTag;
        trapdoorTag = builder.trapdoorTag;
        fenceTag = builder.fenceTag;
        fenceGateTag = builder.fenceGateTag;
        slabTag = builder.slabTag;
        extraData = builder.extraData;
        extraModels = builder.extraModels;
        extraBlockLoot = builder.extraBlockLoot;
        extraRecipes = builder.extraRecipes;
        extraLanguage = builder.extraLanguage;
        extraBlockTags = builder.extraBlockTags;
        extraItemTags = builder.extraItemTags;
        exclusions = Multimaps.unmodifiableMultimap(builder.exclusions);

        family = Suppliers.memoize(() -> {
            var baseBlock = registree.get(Registries.BLOCK, builder.baseBlockName)
                    .orElseThrow(() -> new IllegalStateException("Unable to determine base block for: " + registree.namespace()));

            var familyBuilder = new BlockFamily.Builder(baseBlock.value())
                    .recipeGroupPrefix(builder.shortName)
                    .recipeUnlockedBy("has_" + builder.baseBlockName);

            FurnitureUtil.Names.block(registree, FurnitureUtil.Names.STAIRS, familyBuilder::stairs);
            FurnitureUtil.Names.block(registree, FurnitureUtil.Names.SLAB, familyBuilder::slab);
            FurnitureUtil.Names.block(registree, FurnitureUtil.Names.FENCE, familyBuilder::fence);
            FurnitureUtil.Names.block(registree, FurnitureUtil.Names.FENCE_GATE, familyBuilder::fenceGate);
            FurnitureUtil.Names.block(registree, FurnitureUtil.Names.TRAPDOOR, familyBuilder::trapdoor);
            FurnitureUtil.Names.block(registree, FurnitureUtil.Names.PRESSURE_PLATE, familyBuilder::pressurePlate);

            FurnitureUtil.Names.block(registree, FurnitureUtil.Names.SIGN, sign -> {
                var wallSign = registree.getValueOrThrow(Registries.BLOCK, FurnitureUtil.Names.WALL_SIGN);
                familyBuilder.sign(sign, wallSign);
            });

            FurnitureUtil.Names.block(registree, FurnitureUtil.Names.HANGING_SIGN, hangingSign -> {
                var wallHangingSign = registree.getValueOrThrow(Registries.BLOCK, FurnitureUtil.Names.WALL_HANGING_SIGN);
                familyBuilder.customHangingSign(hangingSign, wallHangingSign);
            });

            return familyBuilder.getFamily();
        });
    }

    public BlockFamily family() {
        return family.get();
    }

    public boolean excluded(DataGenType dataType, String name) {
        return exclusions.get(dataType).contains(name);
    }

    public void ifAllowed(DataGenType dataType, String name, Runnable runnable) {
        if(!excluded(dataType, name)) {
            runnable.run();
        }
    }

    public <TRegistry> boolean ifPresent(DataGenType dataType, ResourceKey<? extends Registry<TRegistry>> registryType, String name, Consumer<? super TRegistry> action) {
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

    public boolean block(DataGenType dataType, String name, Consumer<? super Block> action) {
        return ifPresent(dataType, Registries.BLOCK, name, action);
    }

    public boolean item(DataGenType dataType, String name, Consumer<? super Item> action) {
        if(ifPresent(dataType, Registries.ITEM, name, action)) {
            return true;
        }

        return block(dataType, name, block -> action.accept(block.asItem()));
    }

    private void registerEvents(IEventBus modBus, @Nullable Pair<String, String> packInfo) {
        modBus.addListener(GatherDataEvent.Client.class, event -> gatherPack(event, packInfo));
        modBus.addListener(GatherDataEvent.Server.class, event -> gatherPack(event, packInfo));
    }

    private void gatherPack(GatherDataEvent event, @Nullable Pair<String, String> packInfo) {
        // TODO: Validate sub packs dont throw duplicate provider issues
        // var pack = packInfo == null ? event.getDefaultPackGenerator() : event.getPackGenerator(event.getGenerator().getPackOutput());
        var pack = packInfo == null ? event.getDefaultPackGenerator() : event.getPackGenerator(event.getGenerator().getPackOutput(packInfo.getSecond()));

        pack.createReloadableRegistryObjects(new RegistrySetBuilder()
                .add(Registries.LOOT_TABLE, new LootTableProvider(Set.of(), List.of(new LootTableProvider.SubProviderEntry(
                        output -> new BlockLootProvider(output) {
                            @Override
                            protected void generate() {
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.PLANKS, this::dropSelf);
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.BRICKS, this::dropSelf);
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.WOOL, this::dropSelf);
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.CARPET, this::dropSelf);
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.DRESSER, block -> add(block, this::createNameableBlockEntityTable));
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.STOOL, this::dropSelf);
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.CUSHION, this::dropSelf);
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.LOCKBOX, block -> add(block, this::createNameableBlockEntityTable));
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.DRAWER, block -> add(block, this::createNameableBlockEntityTable));
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.CHAIR, this::dropSelf);
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.BOOKSHELF, block -> add(block, this::createNameableBlockEntityTable));
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.BED_SINGLE, block -> add(block, $ -> createSinglePropConditionTable($, BedBlock.PART, BedPart.HEAD)));
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.BED_DOUBLE, this::dropSelf);
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.DOOR_SINGLE, block -> add(block, this::createDoorTable));
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.DOOR_DOUBLE, block -> add(block, this::createDoorTable));
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.DESK_LEFT, block -> add(block, this::createNameableBlockEntityTable));
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.DESK_RIGHT, block -> add(block, this::createNameableBlockEntityTable));
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.PAINTING_WIDE, this::dropSelf);
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.PAINTING_SMALL, this::dropSelf);
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.OVEN, block -> add(block, this::createNameableBlockEntityTable));
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.CHEST, block -> add(block, this::createNameableBlockEntityTable));
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.FLOOR_LIGHT, this::dropSelf);
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.CHANDELIER, this::dropSelf);
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.SHELF, this::dropSelf);
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.SOFA, this::dropSelf);
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.COUNTER, block -> add(block, this::createNameableBlockEntityTable));
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.WALL_LIGHT, this::dropSelf);
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.BENCH, this::dropSelf);
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.WARDROBE, block -> add(block, this::createNameableBlockEntityTable));
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.TABLE, this::dropSelf);
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.STAIRS, this::dropSelf);
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.SLAB, block -> add(block, this::createSlabItemTable));
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.FENCE, this::dropSelf);
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.FENCE_GATE, this::dropSelf);
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.TRAPDOOR, this::dropSelf);
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.PRESSURE_PLATE, this::dropSelf);
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.BUTTON, this::dropSelf);
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.HANGING_SIGN, this::dropSelf);
                                block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.SIGN, this::dropSelf);

                                extraBlockLoot.accept(this, DataGenContext.this);
                            }

                            @Override
                            public Iterable<Block> getKnownBlocks() {
                                return registree.listElements(Registries.BLOCK).map(Holder::value).toList();
                            }
                        },
                        LootContextParamSets.BLOCK
                ))))
                .add(RecipeProvider.asBootstrap((recipeContext, advancementContext) -> new RecipeProvider(recipeContext, advancementContext) {
                    @Override
                    protected void buildRecipes() {
                        block(DataGenType.RECIPE, FurnitureUtil.Names.PLANKS, block -> FurnitureRecipes.conversionRecipe(this, DataGenContext.this, ItemTags.PLANKS, FantasyFurniture.FURNITURE_PLANKS, "has_" + FurnitureUtil.Names.PLANKS, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.BRICKS, block -> FurnitureRecipes.conversionRecipe(this, DataGenContext.this, ItemTags.STONE_CRAFTING_MATERIALS, FantasyFurniture.FURNITURE_BRICKS, "has_" + FurnitureUtil.Names.BRICKS, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.WOOL, block -> FurnitureRecipes.conversionRecipe(this, DataGenContext.this, ItemTags.WOOL, FantasyFurniture.FURNITURE_WOOL, "has_" + FurnitureUtil.Names.WOOL, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.CARPET, block -> FurnitureRecipes.furnitureStationRecipe(this, DataGenContext.this, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.DRESSER, block -> FurnitureRecipes.furnitureStationRecipe(this, DataGenContext.this, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.STOOL, block -> FurnitureRecipes.furnitureStationRecipe(this, DataGenContext.this, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.CUSHION, block -> FurnitureRecipes.furnitureStationRecipe(this, DataGenContext.this, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.LOCKBOX, block -> FurnitureRecipes.furnitureStationRecipe(this, DataGenContext.this, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.DRAWER, block -> FurnitureRecipes.furnitureStationRecipe(this, DataGenContext.this, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.CHAIR, block -> FurnitureRecipes.furnitureStationRecipe(this, DataGenContext.this, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.BOOKSHELF, block -> FurnitureRecipes.furnitureStationRecipe(this, DataGenContext.this, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.BED_SINGLE, block -> FurnitureRecipes.furnitureStationRecipe(this, DataGenContext.this, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.BED_DOUBLE, block -> FurnitureRecipes.furnitureStationRecipe(this, DataGenContext.this, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.DOOR_SINGLE, block -> FurnitureRecipes.furnitureStationRecipe(this, DataGenContext.this, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.DOOR_DOUBLE, block -> FurnitureRecipes.furnitureStationRecipe(this, DataGenContext.this, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.DESK_LEFT, block -> FurnitureRecipes.furnitureStationRecipe(this, DataGenContext.this, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.DESK_RIGHT, block -> FurnitureRecipes.furnitureStationRecipe(this, DataGenContext.this, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.PAINTING_WIDE, block -> FurnitureRecipes.furnitureStationRecipe(this, DataGenContext.this, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.PAINTING_SMALL, block -> FurnitureRecipes.furnitureStationRecipe(this, DataGenContext.this, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.OVEN, block -> FurnitureRecipes.furnitureStationRecipe(this, DataGenContext.this, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.CHEST, block -> FurnitureRecipes.furnitureStationRecipe(this, DataGenContext.this, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.FLOOR_LIGHT, block -> FurnitureRecipes.furnitureStationRecipe(this, DataGenContext.this, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.CHANDELIER, block -> FurnitureRecipes.furnitureStationRecipe(this, DataGenContext.this, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.SHELF, block -> FurnitureRecipes.furnitureStationRecipe(this, DataGenContext.this, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.SOFA, block -> FurnitureRecipes.furnitureStationRecipe(this, DataGenContext.this, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.COUNTER, block -> FurnitureRecipes.furnitureStationRecipe(this, DataGenContext.this, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.WALL_LIGHT, block -> FurnitureRecipes.furnitureStationRecipe(this, DataGenContext.this, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.BENCH, block -> FurnitureRecipes.furnitureStationRecipe(this, DataGenContext.this, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.WARDROBE, block -> FurnitureRecipes.furnitureStationRecipe(this, DataGenContext.this, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.TABLE, block -> FurnitureRecipes.furnitureStationRecipe(this, DataGenContext.this, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.STAIRS, block -> FurnitureRecipes.furnitureStationRecipe(this, DataGenContext.this, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.SLAB, block -> FurnitureRecipes.furnitureStationRecipe(this, DataGenContext.this, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.FENCE, block -> FurnitureRecipes.furnitureStationRecipe(this, DataGenContext.this, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.FENCE_GATE, block -> FurnitureRecipes.furnitureStationRecipe(this, DataGenContext.this, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.TRAPDOOR, block -> FurnitureRecipes.furnitureStationRecipe(this, DataGenContext.this, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.PRESSURE_PLATE, block -> FurnitureRecipes.furnitureStationRecipe(this, DataGenContext.this, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.BUTTON, block -> FurnitureRecipes.furnitureStationRecipe(this, DataGenContext.this, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.HANGING_SIGN, block -> FurnitureRecipes.furnitureStationRecipe(this, DataGenContext.this, block));
                        block(DataGenType.RECIPE, FurnitureUtil.Names.SIGN, block -> FurnitureRecipes.furnitureStationRecipe(this, DataGenContext.this, block));

                        extraRecipes.accept(this, DataGenContext.this);
                    }
                }))
        );

        pack.createProvider(output -> new LanguageProvider(output, registree.namespace(), "en_us") {
            @Override
            protected void addTranslations() {
                FurnitureUtil.Names.creativeModeTab(registree, key -> addKey(key, "itemGroup", "Fantasy's Furniture - " + englishName));

                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.PLANKS, "Planks");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.BRICKS, "Bricks");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.WOOL, "Wool");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.CARPET, "Carpet");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.DRESSER, "Dresser");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.STOOL, "Stool");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.CUSHION, "Cushion");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.LOCKBOX, "Lockbox");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.DRAWER, "Drawer");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.CHAIR, "Chair");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.BOOKSHELF, "Bookshelf");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.BED_SINGLE, "Bed Single");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.BED_DOUBLE, "Bed Double");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.DOOR_SINGLE, "Door Single");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.DOOR_DOUBLE, "Door Double");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.DESK_LEFT, "Desk Left");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.DESK_RIGHT, "Desk Right");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.PAINTING_WIDE, "Painting Wide");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.PAINTING_SMALL, "Painting Small");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.OVEN, "Oven");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.CHEST, "Chest");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.FLOOR_LIGHT, "Floor Light");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.CHANDELIER, "Chandelier");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.SHELF, "Shelf");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.SOFA, "Sofa");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.COUNTER, "Counter");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.WALL_LIGHT, "Wall Light");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.BENCH, "Bench");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.WARDROBE, "Wardrobe");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.TABLE, "Table");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.STAIRS, "Stairs");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.SLAB, "Slab");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.FENCE, "Fence");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.FENCE_GATE, "Fence Gate");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.TRAPDOOR, "Trapdoor");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.PRESSURE_PLATE, "Pressure Plate");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.BUTTON, "Button");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.HANGING_SIGN, "Hanging Sign");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.WALL_HANGING_SIGN, "Wall Hanging Sign");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.SIGN, "Sign");
                FurnitureLanguage.addFurniture(this, DataGenContext.this, FurnitureUtil.Names.WALL_SIGN, "Wall Sign");

                extraLanguage.accept(this, DataGenContext.this);
            }
        });

        pack.createProvider(output -> new ModelProvider(output, registree.namespace()) {
            @Override
            protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
                block(DataGenType.MODEL, FurnitureUtil.Names.PLANKS, blockModels::createTrivialCube);
                block(DataGenType.MODEL, FurnitureUtil.Names.BRICKS, blockModels::createTrivialCube);
                block(DataGenType.MODEL, FurnitureUtil.Names.WOOL, blockModels::createTrivialCube);

                block(DataGenType.MODEL, FurnitureUtil.Names.CARPET, block -> {
                    var wool = registree.getValueOrThrow(Registries.BLOCK, FurnitureUtil.Names.WOOL);
                    FurnitureModels.createCarpetModel(block, wool, blockModels);
                });

                block(DataGenType.MODEL, FurnitureUtil.Names.DRESSER, block -> {
                    FurnitureModels.createLeftRightModel(block, blockModels);
                    FurnitureModels.registerSimpleBlockItemModel(block, blockModels);
                });

                block(DataGenType.MODEL, FurnitureUtil.Names.STOOL, blockModels::createNonTemplateHorizontalBlock);
                block(DataGenType.MODEL, FurnitureUtil.Names.CUSHION, blockModels::createNonTemplateHorizontalBlock);
                block(DataGenType.MODEL, FurnitureUtil.Names.LOCKBOX, blockModels::createNonTemplateHorizontalBlock);
                block(DataGenType.MODEL, FurnitureUtil.Names.DRAWER, blockModels::createNonTemplateHorizontalBlock);

                block(DataGenType.MODEL, FurnitureUtil.Names.CHAIR, block -> {
                    FurnitureModels.createBottomTopModel(block, blockModels);
                    FurnitureModels.registerSimpleBlockItemModel(block, blockModels);
                });

                block(DataGenType.MODEL, FurnitureUtil.Names.BOOKSHELF, block -> {
                    FurnitureModels.createBookshelfModel(block, blockModels);
                    FurnitureModels.registerSimpleBlockItemModel(block, blockModels);
                });

                block(DataGenType.MODEL, FurnitureUtil.Names.BED_SINGLE, block -> {
                    FurnitureModels.createBedSingleModel(block, blockModels);
                    FurnitureModels.registerSimpleBlockItemModel(block, blockModels);
                });

                block(DataGenType.MODEL, FurnitureUtil.Names.BED_DOUBLE, block -> {
                    FurnitureModels.createBedDoubleModel(block, blockModels);
                    FurnitureModels.registerSimpleBlockItemModel(block, blockModels);
                });

                block(DataGenType.MODEL, FurnitureUtil.Names.DOOR_SINGLE, block -> {
                    FurnitureModels.createDoorModel(block, blockModels);
                    blockModels.registerSimpleFlatItemModel(block.asItem());
                });

                block(DataGenType.MODEL, FurnitureUtil.Names.DOOR_DOUBLE, block -> {
                    FurnitureModels.createDoorModel(block, blockModels);
                    blockModels.registerSimpleFlatItemModel(block.asItem());
                });

                block(DataGenType.MODEL, FurnitureUtil.Names.DESK_LEFT, block -> {
                    FurnitureModels.createLeftRightModel(block, blockModels);
                    FurnitureModels.registerSimpleBlockItemModel(block, blockModels);
                });

                block(DataGenType.MODEL, FurnitureUtil.Names.DESK_RIGHT, block -> {
                    FurnitureModels.createLeftRightModel(block, blockModels);
                    FurnitureModels.registerSimpleBlockItemModel(block, blockModels);
                });

                block(DataGenType.MODEL, FurnitureUtil.Names.PAINTING_WIDE, block -> {
                    FurnitureModels.createLeftRightModel(block, blockModels);
                    FurnitureModels.registerSimpleBlockItemModel(block, blockModels);
                });

                block(DataGenType.MODEL, FurnitureUtil.Names.PAINTING_SMALL, blockModels::createNonTemplateHorizontalBlock);
                block(DataGenType.MODEL, FurnitureUtil.Names.OVEN, blockModels::createNonTemplateHorizontalBlock);

                block(DataGenType.MODEL, FurnitureUtil.Names.CHEST, block -> {
                    FurnitureModels.createLeftRightModel(block, blockModels);
                    FurnitureModels.registerSimpleBlockItemModel(block, blockModels);
                });

                block(DataGenType.MODEL, FurnitureUtil.Names.FLOOR_LIGHT, block -> {
                    FurnitureModels.createBottomTopModel(block, blockModels);
                    FurnitureModels.registerSimpleBlockItemModel(block, blockModels);
                });

                block(DataGenType.MODEL, FurnitureUtil.Names.CHANDELIER, blockModels::createNonTemplateModelBlock);

                block(DataGenType.MODEL, FurnitureUtil.Names.SHELF, block -> {
                    FurnitureModels.createShelfModel(block, blockModels);
                    blockModels.registerSimpleItemModel(block, ModelLocationUtils.getModelLocation(block, ShelfConnection.NONE.getModelSuffix()));
                });

                block(DataGenType.MODEL, FurnitureUtil.Names.SOFA, block -> {
                    FurnitureModels.createSofaModel(block, blockModels);
                    blockModels.registerSimpleItemModel(block, ModelLocationUtils.getModelLocation(block, SofaConnection.NONE.getModelSuffix()));
                });

                block(DataGenType.MODEL, FurnitureUtil.Names.COUNTER, block -> {
                    FurnitureModels.createCounterModel(block, blockModels);
                    blockModels.registerSimpleItemModel(block, ModelLocationUtils.getModelLocation(block, CounterConnection.NONE.getModelSuffix()));
                });

                block(DataGenType.MODEL, FurnitureUtil.Names.WALL_LIGHT, block -> FurnitureModels.createWallLightModel(block, blockModels));

                block(DataGenType.MODEL, FurnitureUtil.Names.BENCH, block -> {
                    FurnitureModels.createLeftRightModel(block, blockModels);
                    FurnitureModels.registerSimpleBlockItemModel(block, blockModels);
                });

                block(DataGenType.MODEL, FurnitureUtil.Names.WARDROBE, block -> {
                    FurnitureModels.createWardrobeModel(block, blockModels);
                    FurnitureModels.registerSimpleBlockItemModel(block, blockModels);
                });

                block(DataGenType.MODEL, FurnitureUtil.Names.TABLE, block -> FurnitureModels.createTableModel(block, blockModels));

                blockModels.familyWithExistingFullBlock(family().getBaseBlock()).generateFor(family());

                extraModels.accept(blockModels, itemModels, DataGenContext.this);
            }

            @Override
            protected Stream<? extends Holder<Block>> getKnownBlocks() {
                return registree.listElements(Registries.BLOCK);
            }

            @Override
            protected Stream<? extends Holder<Item>> getKnownItems() {
                return registree.listElements(Registries.ITEM);
            }
        });

        var blockTags = pack.createProvider((output, registries) -> new BlockTagsProvider(output, registries, registree.namespace()) {
            @Override
            protected void addTags(HolderLookup.Provider registries) {
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.PLANKS, block -> FurnitureBlockTags.tag(this, block, mineableTag, BlockTags.PLANKS));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.BRICKS, block -> FurnitureBlockTags.tag(this, block, mineableTag, Tags.Blocks.STONES));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.WOOL, block -> FurnitureBlockTags.tag(this, block, BlockTags.WOOL));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.CARPET, block -> FurnitureBlockTags.tag(this, block, BlockTags.WOOL_CARPETS));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.DRESSER, block -> FurnitureBlockTags.tag(this, block, mineableTag, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.LOCKBOX, block -> FurnitureBlockTags.tag(this, block, mineableTag));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.DRAWER, block -> FurnitureBlockTags.tag(this, block, mineableTag));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.CHAIR, block -> FurnitureBlockTags.tag(this, block, mineableTag, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.BOOKSHELF, block -> FurnitureBlockTags.tag(this, block, mineableTag, Tags.Blocks.RELOCATION_NOT_SUPPORTED, BlockTags.ENCHANTMENT_POWER_PROVIDER));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.BED_SINGLE, block -> FurnitureBlockTags.tag(this, block, mineableTag, BlockTags.BEDS, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.BED_DOUBLE, block -> FurnitureBlockTags.tag(this, block, mineableTag, BlockTags.BEDS, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.DOOR_SINGLE, block -> FurnitureBlockTags.tag(this, block, mineableTag, doorTag, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.DOOR_DOUBLE, block -> FurnitureBlockTags.tag(this, block, mineableTag, doorTag, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.DESK_LEFT, block -> FurnitureBlockTags.tag(this, block, mineableTag, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.DESK_RIGHT, block -> FurnitureBlockTags.tag(this, block, mineableTag, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.PAINTING_WIDE, block -> FurnitureBlockTags.tag(this, block, mineableTag, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.PAINTING_SMALL, block -> FurnitureBlockTags.tag(this, block, mineableTag));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.OVEN, block -> FurnitureBlockTags.tag(this, block, BlockTags.MINEABLE_WITH_PICKAXE, Tags.Blocks.PLAYER_WORKSTATIONS_FURNACES));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.CHEST, block -> FurnitureBlockTags.tag(this, block, mineableTag, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.FLOOR_LIGHT, block -> FurnitureBlockTags.tag(this, block, mineableTag, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.CHANDELIER, block -> FurnitureBlockTags.tag(this, block, mineableTag));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.SHELF, block -> FurnitureBlockTags.tag(this, block, mineableTag));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.SOFA, block -> FurnitureBlockTags.tag(this, block, mineableTag));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.COUNTER, block -> FurnitureBlockTags.tag(this, block, mineableTag));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.WALL_LIGHT, block -> FurnitureBlockTags.tag(this, block, mineableTag));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.BENCH, block -> FurnitureBlockTags.tag(this, block, mineableTag, ApexTags.Blocks.SEAT_PER_BLOCK, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.WARDROBE, block -> FurnitureBlockTags.tag(this, block, mineableTag, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.TABLE, block -> FurnitureBlockTags.tag(this, block, mineableTag));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.STAIRS, block -> FurnitureBlockTags.tag(this, block, mineableTag, stairsTag));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.SLAB, block -> FurnitureBlockTags.tag(this, block, mineableTag, slabTag));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.FENCE, block -> FurnitureBlockTags.tag(this, block, mineableTag, fenceTag));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.FENCE_GATE, block -> FurnitureBlockTags.tag(this, block, mineableTag, fenceGateTag));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.TRAPDOOR, block -> FurnitureBlockTags.tag(this, block, mineableTag, trapdoorTag));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.PRESSURE_PLATE, block -> FurnitureBlockTags.tag(this, block, mineableTag, pressurePlateTag));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.BUTTON, block -> FurnitureBlockTags.tag(this, block, mineableTag, buttonTag));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.HANGING_SIGN, block -> FurnitureBlockTags.tag(this, block, mineableTag, BlockTags.CEILING_HANGING_SIGNS));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.WALL_HANGING_SIGN, block -> FurnitureBlockTags.tag(this, block, mineableTag, BlockTags.WALL_HANGING_SIGNS));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.SIGN, block -> FurnitureBlockTags.tag(this, block, mineableTag, BlockTags.STANDING_SIGNS));
                block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.WALL_SIGN, block -> FurnitureBlockTags.tag(this, block, mineableTag, BlockTags.WALL_SIGNS));

                Dyeable.dyeableBlocks(registree).forEach(item -> tag(Tags.Blocks.DYEABLE_REDYEABLE_SIMPLE).add(item.builtInRegistryHolder().key()));

                extraBlockTags.accept(this, DataGenContext.this);
            }
        });

        pack.createProvider((output, registries) -> new ItemTagsProvider(output, registries, blockTags.contentsGetter(), registree.namespace()) {
            @Override
            protected void addTags(HolderLookup.Provider registries) {
                item(DataGenType.ITEM_TAG, FurnitureUtil.Names.PLANKS, item -> FurnitureItemTags.tag(this, item, FantasyFurniture.FURNITURE_PLANKS));
                item(DataGenType.ITEM_TAG, FurnitureUtil.Names.BRICKS, item -> FurnitureItemTags.tag(this, item, FantasyFurniture.FURNITURE_BRICKS));
                item(DataGenType.ITEM_TAG, FurnitureUtil.Names.WOOL, item -> FurnitureItemTags.tag(this, item, FantasyFurniture.FURNITURE_WOOL));
                item(DataGenType.ITEM_TAG, FurnitureUtil.Names.CARPET, item -> FurnitureItemTags.tag(this, item, ItemTags.WOOL_CARPETS));
                item(DataGenType.ITEM_TAG, FurnitureUtil.Names.BED_SINGLE, item -> FurnitureItemTags.tag(this, item, ItemTags.BEDS));
                item(DataGenType.ITEM_TAG, FurnitureUtil.Names.BED_DOUBLE, item -> FurnitureItemTags.tag(this, item, ItemTags.BEDS));
                item(DataGenType.ITEM_TAG, FurnitureUtil.Names.DOOR_SINGLE, item -> FurnitureItemTags.tag(this, item, doorTag));
                item(DataGenType.ITEM_TAG, FurnitureUtil.Names.DOOR_DOUBLE, item -> FurnitureItemTags.tag(this, item, doorTag));
                item(DataGenType.ITEM_TAG, FurnitureUtil.Names.OVEN, item -> FurnitureItemTags.tag(this, item, Tags.Items.PLAYER_WORKSTATIONS_FURNACES));
                item(DataGenType.ITEM_TAG, FurnitureUtil.Names.STAIRS, item -> FurnitureItemTags.tag(this, item, stairsTag));
                item(DataGenType.ITEM_TAG, FurnitureUtil.Names.SLAB, item -> FurnitureItemTags.tag(this, item, slabTag));
                item(DataGenType.ITEM_TAG, FurnitureUtil.Names.FENCE, item -> FurnitureItemTags.tag(this, item, fenceTag));
                item(DataGenType.ITEM_TAG, FurnitureUtil.Names.FENCE_GATE, item -> FurnitureItemTags.tag(this, item, fenceGateTag));
                item(DataGenType.ITEM_TAG, FurnitureUtil.Names.TRAPDOOR, item -> FurnitureItemTags.tag(this, item, trapdoorTag));
                item(DataGenType.ITEM_TAG, FurnitureUtil.Names.PRESSURE_PLATE, item -> FurnitureItemTags.tag(this, item, pressurePlateTag));
                item(DataGenType.ITEM_TAG, FurnitureUtil.Names.BUTTON, item -> FurnitureItemTags.tag(this, item, buttonTag));
                item(DataGenType.ITEM_TAG, FurnitureUtil.Names.WALL_HANGING_SIGN, item -> FurnitureItemTags.tag(this, item, ItemTags.HANGING_SIGNS));
                item(DataGenType.ITEM_TAG, FurnitureUtil.Names.SIGN, item -> FurnitureItemTags.tag(this, item, ItemTags.SIGNS));

                Dyeable.dyeableItems(registree).forEach(item -> tag(Tags.Items.DYEABLE_REDYEABLE_SIMPLE).add(item.builtInRegistryHolder().key()));

                extraItemTags.accept(this, DataGenContext.this);
            }
        });

        pack.createProvider(output -> ApexUtil.createMetadataProvider(output, Component.literal(englishName + " Furniture Set resources"), PackType.SERVER_DATA));

        extraData.accept(event, this);
    }

    public static Builder builder(Registree registree, String shortName, String englishName) {
        return new Builder(registree, shortName, englishName);
    }

    public static Builder builder(Registree registree, String shortName) {
        return builder(registree, shortName, StringHelper.toEnglishName(shortName));
    }

    public static Builder wooden(Registree registree, String shortName, String englishName) {
        return builder(registree, shortName, englishName);
    }

    public static Builder wooden(Registree registree, String shortName) {
        return wooden(registree, shortName, StringHelper.toEnglishName(shortName));
    }

    public static Builder stone(Registree registree, String shortName, String englishName) {
        return builder(registree, shortName, englishName)
                .baseBlock(FurnitureUtil.Names.BRICKS)
                .mineableTag(BlockTags.MINEABLE_WITH_PICKAXE)
                .doorTag(BlockItemTags.DOORS)
                .stairsTag(BlockItemTags.STAIRS)
                .buttonTag(BlockItemTags.STONE_BUTTONS)
                .pressurePlateTag(BlockTags.PRESSURE_PLATES, null)
                .trapdoorTag(BlockItemTags.TRAPDOORS)
                .fenceTag(new BlockItemTagId(Tags.Blocks.FENCES, Tags.Items.FENCES))
                .fenceGateTag(new BlockItemTagId(Tags.Blocks.FENCE_GATES, Tags.Items.FENCE_GATES))
                .slabTag(BlockItemTags.SLABS);
    }

    public static Builder stone(Registree registree, String shortName) {
        return stone(registree, shortName, StringHelper.toEnglishName(shortName));
    }

    public static final class Builder {
        private final Registree registree;
        private final String shortName;
        private final String englishName;
        private String baseBlockName = FurnitureUtil.Names.PLANKS;
        private @Nullable TagKey<Block> mineableTag = BlockTags.MINEABLE_WITH_AXE;
        private @Nullable BlockItemTagId doorTag = BlockItemTags.WOODEN_DOORS;
        private @Nullable BlockItemTagId stairsTag = BlockItemTags.WOODEN_STAIRS;
        private @Nullable BlockItemTagId buttonTag = BlockItemTags.WOODEN_BUTTONS;
        private @Nullable BlockItemTagId pressurePlateTag = BlockItemTags.WOODEN_PRESSURE_PLATES;
        private @Nullable BlockItemTagId trapdoorTag = BlockItemTags.WOODEN_TRAPDOORS;
        private @Nullable BlockItemTagId fenceTag = new BlockItemTagId(Tags.Blocks.FENCES_WOODEN, Tags.Items.FENCES_WOODEN);
        private @Nullable BlockItemTagId fenceGateTag = new BlockItemTagId(Tags.Blocks.FENCE_GATES_WOODEN, Tags.Items.FENCE_GATES_WOODEN);
        private @Nullable BlockItemTagId slabTag = BlockItemTags.WOODEN_SLABS;
        private BiConsumer<GatherDataEvent, DataGenContext> extraData = (event, context) -> { };
        private TriConsumer<BlockModelGenerators, ItemModelGenerators, DataGenContext> extraModels = (blockModels, itemModels, context) -> { };
        private BiConsumer<BlockLootProvider, DataGenContext> extraBlockLoot = (provider, context) -> { };
        private BiConsumer<RecipeProvider, DataGenContext> extraRecipes = (provider, context) -> { };
        private BiConsumer<LanguageProvider, DataGenContext> extraLanguage = (provider, context) -> { };
        private BiConsumer<BlockTagsProvider, DataGenContext> extraBlockTags = (provider, context) -> { };
        private BiConsumer<ItemTagsProvider, DataGenContext> extraItemTags = (provider, context) -> { };
        private final Multimap<DataGenType, String> exclusions = HashMultimap.create();

        private Builder(Registree registree, String shortName, String englishName) {
            this.registree = registree;
            this.shortName = shortName;
            this.englishName = englishName;
        }

        public Builder baseBlock(String baseBlockName) {
            this.baseBlockName = baseBlockName;
            return this;
        }

        public Builder mineableTag(@Nullable TagKey<Block> mineableTag) {
            this.mineableTag = mineableTag;
            return this;
        }

        public Builder doorTag(@Nullable TagKey<Block> doorTagBlock, @Nullable TagKey<Item> doorTagItem) {
            if(doorTagBlock == null && doorTagItem == null) {
                doorTag = null;
                return this;
            }

            return doorTag(new BlockItemTagId(doorTagBlock, doorTagItem));
        }

        public Builder doorTag(@Nullable BlockItemTagId doorTag) {
            this.doorTag = doorTag;
            return this;
        }

        public Builder stairsTag(@Nullable TagKey<Block> stairsTagBlock, @Nullable TagKey<Item> stairsTagItem) {
            if(stairsTagBlock == null && stairsTagItem == null) {
                stairsTag = null;
                return this;
            }

            return stairsTag(new BlockItemTagId(stairsTagBlock, stairsTagItem));
        }

        public Builder stairsTag(@Nullable BlockItemTagId stairsTag) {
            this.stairsTag = stairsTag;
            return this;
        }

        public Builder buttonTag(@Nullable TagKey<Block> buttonTagBlock, @Nullable TagKey<Item> buttonTagItem) {
            if(buttonTagBlock == null && buttonTagItem == null) {
                buttonTag = null;
                return this;
            }

            return buttonTag(new BlockItemTagId(buttonTagBlock, buttonTagItem));
        }

        public Builder buttonTag(@Nullable BlockItemTagId buttonTag) {
            this.buttonTag = buttonTag;
            return this;
        }

        public Builder pressurePlateTag(@Nullable TagKey<Block> pressurePlateTagBlock, @Nullable TagKey<Item> pressurePlateTagItem) {
            if(pressurePlateTagBlock == null && pressurePlateTagItem == null) {
                pressurePlateTag = null;
                return this;
            }

            return pressurePlateTag(new BlockItemTagId(pressurePlateTagBlock, pressurePlateTagItem));
        }

        public Builder pressurePlateTag(@Nullable BlockItemTagId pressurePlateTag) {
            this.pressurePlateTag = pressurePlateTag;
            return this;
        }

        public Builder trapdoorTag(@Nullable TagKey<Block> trapdoorTagBlock, TagKey<Item> trapdoorTagItem) {
            if(trapdoorTagBlock == null && trapdoorTagItem == null) {
                trapdoorTag = null;
                return this;
            }

            return trapdoorTag(new BlockItemTagId(trapdoorTagBlock, trapdoorTagItem));
        }

        public Builder trapdoorTag(@Nullable BlockItemTagId trapdoorTag) {
            this.trapdoorTag = trapdoorTag;
            return this;
        }

        public Builder fenceTag(@Nullable TagKey<Block> fenceTagBlock, @Nullable TagKey<Item> fenceTagItem) {
            if(fenceTagBlock == null && fenceTagItem == null) {
                fenceTag = null;
                return this;
            }

            return fenceTag(new BlockItemTagId(fenceTagBlock, fenceTagItem));
        }

        public Builder fenceTag(@Nullable BlockItemTagId fenceTag) {
            this.fenceTag = fenceTag;
            return this;
        }

        public Builder fenceGateTag(@Nullable TagKey<Block> fenceGateTagBlock, @Nullable TagKey<Item> fenceGateTagItem) {
            if(fenceGateTagBlock == null && fenceGateTagItem == null) {
                fenceGateTag = null;
                return this;
            }

            return fenceGateTag(new BlockItemTagId(fenceGateTagBlock, fenceGateTagItem));
        }

        public Builder fenceGateTag(@Nullable BlockItemTagId fenceGateTag) {
            this.fenceGateTag = fenceGateTag;
            return this;
        }

        public Builder slabTag(@Nullable TagKey<Block> slabTagBlock, @Nullable TagKey<Item> slabTagItem) {
            if(slabTagBlock == null && slabTagItem == null) {
                slabTag = null;
                return this;
            }

            return slabTag(new BlockItemTagId(slabTagBlock, slabTagItem));
        }

        public Builder slabTag(@Nullable BlockItemTagId slabTag) {
            this.slabTag = slabTag;
            return this;
        }

        public Builder exclude(DataGenType type, String identifier) {
            exclusions.put(type, identifier);
            return this;
        }

        public Builder extraData(BiConsumer<GatherDataEvent, DataGenContext> extraData) {
            this.extraData = this.extraData.andThen(extraData);
            return this;
        }

        public Builder extraModels(TriConsumer<BlockModelGenerators, ItemModelGenerators, DataGenContext> extraModels) {
            this.extraModels = this.extraModels.andThen(extraModels);
            return this;
        }

        public Builder extraBlockLoot(BiConsumer<BlockLootProvider, DataGenContext> extraBlockLoot) {
            this.extraBlockLoot = this.extraBlockLoot.andThen(extraBlockLoot);
            return this;
        }

        public Builder extraRecipes(BiConsumer<RecipeProvider, DataGenContext> extraRecipes) {
            this.extraRecipes = this.extraRecipes.andThen(extraRecipes);
            return this;
        }

        public Builder extraLanguage(BiConsumer<LanguageProvider, DataGenContext> extraLanguage) {
            this.extraLanguage = this.extraLanguage.andThen(extraLanguage);
            return this;
        }

        public Builder extraBlockTags(BiConsumer<BlockTagsProvider, DataGenContext> extraBlockTags) {
            this.extraBlockTags = this.extraBlockTags.andThen(extraBlockTags);
            return this;
        }

        public Builder extraItemTags(BiConsumer<ItemTagsProvider, DataGenContext> extraItemTags) {
            this.extraItemTags = this.extraItemTags.andThen(extraItemTags);
            return this;
        }

        public DataGenContext build(IEventBus modBus, String packId, String packPath) {
            var context = new DataGenContext(this);
            context.registerEvents(modBus, Pair.of(packId, packPath));
            return context;
        }

        public DataGenContext build(IEventBus modBus) {
            var context = new DataGenContext(this);
            context.registerEvents(modBus, null);
            return context;
        }
    }
}
