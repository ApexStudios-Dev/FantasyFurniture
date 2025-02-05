package dev.apexstudios.fantasyfurniture.set;

import dev.apexstudios.apexcore.core.seat.SeatSetup;
import dev.apexstudios.apexcore.lib.component.ComponentHolder;
import dev.apexstudios.apexcore.lib.component.block.BlockComponent;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.component.block.DoorBlockComponentHolder;
import dev.apexstudios.apexcore.lib.component.block.types.MultiBlockComponent;
import dev.apexstudios.apexcore.lib.data.provider.LanguageProvider;
import dev.apexstudios.apexcore.lib.data.provider.ModelProvider;
import dev.apexstudios.apexcore.lib.data.provider.RecipeProvider;
import dev.apexstudios.apexcore.lib.data.provider.context.ProviderListenerContext;
import dev.apexstudios.apexcore.lib.data.provider.loot.LootTableProvider;
import dev.apexstudios.apexcore.lib.data.provider.tag.IntrusiveTagProvider;
import dev.apexstudios.apexcore.lib.data.provider.tag.TagProvider;
import dev.apexstudios.apexcore.lib.placement.BlockPlacementRenderer;
import dev.apexstudios.fantasyfurniture.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.block.TableBlock;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureDoorBlockComponentHolder;
import dev.apexstudios.fantasyfurniture.block.property.CounterConnection;
import dev.apexstudios.fantasyfurniture.block.property.ShelfConnection;
import dev.apexstudios.fantasyfurniture.block.property.SofaConnection;
import dev.apexstudios.fantasyfurniture.station.FurnitureStationRecipeBuilder;
import dev.apexstudios.fantasyfurniture.station.FurnitureStationSetup;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.blockstates.Variant;
import net.minecraft.client.data.models.blockstates.VariantProperties;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.crafting.DifferenceIngredient;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

interface FurnitureSetDataGen {
    boolean USE_MULTIBLOCK_ITEM_MODELS = false;

    static void lootTables(LootTableProvider provider, FurnitureSet furnitureSet) {
        provider.fromRegistree(furnitureSet.registree());

        provider.block(blocks -> furnitureSet
                .registree()
                .listElements(Registries.BLOCK)
                .map(Holder::value)
                .forEach(blocks::dropSelf)
        );
    }

    static void models(ModelProvider provider, FurnitureSet furnitureSet, BlockFamily family) {
        provider.fromRegistree(furnitureSet.registree());

        var blockModels = provider.blockModels();

        run(furnitureSet, BlockType.WOOL, wool -> blockModels.createFullAndCarpetBlocks(wool, furnitureSet.blockOrThrow(BlockType.CARPET).value()));
        // run(furnitureSet, BlockType.PLANKS, blockModels::createTrivialCube);
        run(furnitureSet, BlockType.DRESSER,  block -> multiBlockModel(block, blockModels, index -> ModelLocationUtils.getModelLocation(block, index == MultiBlockComponent.ORIGIN_INDEX ? "_left" : "_right")));
        run(furnitureSet, BlockType.STOOL, block -> horizontalFacingBlock(block, block.getComponentOrThrow(BlockComponentTypes.FACING).getProperty(), blockModels));
        run(furnitureSet, BlockType.CUSHION, block -> horizontalFacingBlock(block, block.getComponentOrThrow(BlockComponentTypes.FACING).getProperty(), blockModels));
        run(furnitureSet, BlockType.LOCKBOX, block -> horizontalFacingBlock(block, block.getComponentOrThrow(BlockComponentTypes.FACING).getProperty(), blockModels));
        run(furnitureSet, BlockType.DRAWER, block -> horizontalFacingBlock(block, block.getComponentOrThrow(BlockComponentTypes.FACING).getProperty(), blockModels));
        run(furnitureSet, BlockType.CHAIR, block -> multiBlockModel(block, blockModels, index -> ModelLocationUtils.getModelLocation(block, index == MultiBlockComponent.ORIGIN_INDEX ? "_bottom" : "_top")));
        run(furnitureSet, BlockType.BOOKSHELF, block -> multiBlockModel(block, blockModels, index -> ModelLocationUtils.getModelLocation(block, switch (index) {
            case 1 -> "_bottom_right";
            case 2 -> "_top_right";
            case 3 -> "_top_left";
            default -> "_bottom_left";
        })));
        run(furnitureSet, BlockType.BED_SINGLE, block -> multiBlockModel(block, blockModels, index -> ModelLocationUtils.getModelLocation(block, index == MultiBlockComponent.ORIGIN_INDEX ? "_bottom" : "_top")));
        run(furnitureSet, BlockType.BED_DOUBLE, block -> multiBlockModel(block, blockModels, index -> ModelLocationUtils.getModelLocation(block, switch (index) {
            case 1 -> "_top_left";
            case 2 -> "_top_right";
            case 3 -> "_bottom_right";
            default -> "_bottom_left";
        })));
        run(furnitureSet, BlockType.DOOR_DOUBLE, block -> doorModel(block, blockModels));
        run(furnitureSet, BlockType.DOOR_SINGLE, block -> doorModel(block, blockModels));
        run(furnitureSet, BlockType.DESK_LEFT, block -> multiBlockModel(block, blockModels, index -> ModelLocationUtils.getModelLocation(block, index == MultiBlockComponent.ORIGIN_INDEX ? "_left" : "_right")));
        run(furnitureSet, BlockType.DESK_RIGHT, block -> multiBlockModel(block, blockModels, index -> ModelLocationUtils.getModelLocation(block, index == MultiBlockComponent.ORIGIN_INDEX ? "_left" : "_right")));
        run(furnitureSet, BlockType.PAINTING_WIDE, block -> multiBlockModel(block, blockModels, index -> ModelLocationUtils.getModelLocation(block, index == MultiBlockComponent.ORIGIN_INDEX ? "_left" : "_right")));
        run(furnitureSet, BlockType.PAINTING_SMALL, block -> horizontalFacingBlock(block, block.getComponentOrThrow(BlockComponentTypes.FACING).getProperty(), blockModels));
        run(furnitureSet, BlockType.OVEN, block -> horizontalFacingBlock(block, block.getComponentOrThrow(BlockComponentTypes.FACING).getProperty(), blockModels));
        run(furnitureSet, BlockType.CHEST, block -> multiBlockModel(block, blockModels, index -> ModelLocationUtils.getModelLocation(block, index == MultiBlockComponent.ORIGIN_INDEX ? "_left" : "_right")));
        run(furnitureSet, BlockType.FLOOR_LIGHT, block -> multiBlockModel(block, blockModels, index -> ModelLocationUtils.getModelLocation(block, index == MultiBlockComponent.ORIGIN_INDEX ? "_bottom" : "_top")));
        run(furnitureSet, BlockType.CHANDELIER, block -> horizontalFacingBlock(block, block.getComponentOrThrow(BlockComponentTypes.FACING).getProperty(), blockModels));
        run(furnitureSet, BlockType.SHELF, block -> facingPropertyModel(block, blockModels, $ -> ShelfConnection.PROPERTY, connection -> ModelLocationUtils.getModelLocation(block, connection.getModelSuffix()), ShelfConnection.NONE));
        run(furnitureSet, BlockType.SOFA, block -> facingPropertyModel(block, blockModels, $ -> SofaConnection.PROPERTY, connection -> ModelLocationUtils.getModelLocation(block, connection.getModelSuffix()), SofaConnection.NONE));
        run(furnitureSet, BlockType.COUNTER, block -> facingPropertyModel(block, blockModels, $ -> CounterConnection.PROPERTY, connection -> ModelLocationUtils.getModelLocation(block, connection.getModelSuffix()), CounterConnection.NONE));
        run(furnitureSet, BlockType.WALL_LIGHT, block -> horizontalFacingBlock(block, block.getComponentOrThrow(BlockComponentTypes.FACING).getProperty(), blockModels));
        run(furnitureSet, BlockType.BENCH, block -> multiBlockModel(block, blockModels, index -> ModelLocationUtils.getModelLocation(block, index == MultiBlockComponent.ORIGIN_INDEX ? "_left" : "_right")));
        run(furnitureSet, BlockType.WARDROBE, block -> multiBlockModel(block, blockModels, index -> ModelLocationUtils.getModelLocation(block, switch (index) {
            case 1 -> "_bottom_right";
            case 2 -> "_middle_right";
            case 3 -> "_middle_left";
            case 4 -> "_top_right";
            case 5 -> "_top_left";
            default -> "_bottom_left";
        })));
        run(furnitureSet, BlockType.TABLE, block -> tableModel(block, blockModels));

        var planks = furnitureSet.blockOrThrow(BlockType.PLANKS).value();
        blockModels.family(planks).generateFor(family);
        blockModels.createHangingSign(planks, furnitureSet.blockOrThrow(BlockType.HANGING_SIGN).value(), furnitureSet.blockOrThrow(BlockType.WALL_HANGING_SIGN).value());
    }

    static void language(LanguageProvider provider, FurnitureSet furnitureSet, String englishName) {
        BlockType.VALUES.forEach(blockType -> {
            var item = furnitureSet.item(blockType);

            if(item == null)
                return;

            var name = Stream.of(StringUtils.split(blockType.name(), '_')).map(StringUtils::capitalize).collect(Collectors.joining(" "));
            provider.addItem(item, englishName + ' ' + name);
        });

        provider.addCreativeModeTab(furnitureSet.creativeModeTab(), englishName + " Furniture Set");
    }

    static void blockTags(IntrusiveTagProvider<Block> provider, FurnitureSet furnitureSet) {
        tag(provider, BlockTags.PLANKS, furnitureSet::block, BlockType.PLANKS);
        tag(provider, BlockTags.WOOL, furnitureSet::block, BlockType.WOOL);
        tag(provider, BlockTags.WOOL_CARPETS, furnitureSet::block, BlockType.CARPET);
        tag(provider, BlockTags.WOODEN_DOORS, furnitureSet::block, BlockType.DOOR_SINGLE, BlockType.DOOR_DOUBLE);
        tag(provider, BlockTags.WOODEN_BUTTONS, furnitureSet::block, BlockType.BUTTON);
        tag(provider, BlockTags.WOODEN_STAIRS, furnitureSet::block, BlockType.STAIRS);
        tag(provider, BlockTags.WOODEN_SLABS, furnitureSet::block, BlockType.SLAB);
        tag(provider, BlockTags.WOODEN_FENCES, furnitureSet::block, BlockType.FENCE);
        tag(provider, BlockTags.FENCE_GATES, furnitureSet::block, BlockType.FENCE_GATE);
        tag(provider, BlockTags.WOODEN_PRESSURE_PLATES, furnitureSet::block, BlockType.PRESSURE_PLATE);
        tag(provider, BlockTags.WOODEN_TRAPDOORS, furnitureSet::block, BlockType.TRAP_DOOR);
        tag(provider, BlockTags.STANDING_SIGNS, furnitureSet::block, BlockType.SIGN);
        tag(provider, BlockTags.WALL_SIGNS, furnitureSet::block, BlockType.WALL_SIGN);
        tag(provider, BlockTags.CEILING_HANGING_SIGNS, furnitureSet::block, BlockType.HANGING_SIGN);
        tag(provider, BlockTags.WALL_HANGING_SIGNS, furnitureSet::block, BlockType.WALL_HANGING_SIGN);
        tag(provider, BlockTags.BEDS, furnitureSet::block, BlockType.BED_SINGLE, BlockType.BED_DOUBLE);
        tag(provider, Tags.Blocks.CHESTS_WOODEN, furnitureSet::block, BlockType.CHEST, BlockType.COUNTER, BlockType.DESK_LEFT, BlockType.DESK_RIGHT, BlockType.DRAWER, BlockType.DRESSER, BlockType.LOCKBOX);
        tag(provider, Tags.Blocks.PLAYER_WORKSTATIONS_FURNACES, furnitureSet::block, BlockType.OVEN);
        tag(provider, SeatSetup.ORIGIN_ONLY, furnitureSet::block, BlockType.CHAIR, BlockType.BENCH);

        var placementRender = provider.tag(BlockPlacementRenderer.BLOCK_WHITELIST);
        var relocation = provider.tag(Tags.Blocks.RELOCATION_NOT_SUPPORTED);
        var mineableWithAxe = provider.tag(BlockTags.MINEABLE_WITH_AXE);

        furnitureSet.registree().listElements(Registries.BLOCK).map(Holder::value).forEach(block -> {
            if(usesPlacementRenderer(block))
                placementRender.withElement(block);
            if(block instanceof ComponentHolder && ((ComponentHolder<BlockComponent>) block).hasComponent(BlockComponentTypes.MULTI_BLOCK))
                relocation.withElement(block);
            if(!matches(furnitureSet, BlockType.WOOL, block) && !matches(furnitureSet, BlockType.CARPET, block))
                mineableWithAxe.withElement(block);
        });
    }

    static void itemTags(IntrusiveTagProvider<Item> provider, FurnitureSet furnitureSet) {
        tag(provider, FantasyFurniture.FURNITURE_PLANKS, furnitureSet::item, BlockType.PLANKS);
        tag(provider, FantasyFurniture.FURNITURE_WOOL, furnitureSet::item, BlockType.WOOL);
        tag(provider, ItemTags.WOOL_CARPETS, furnitureSet::item, BlockType.CARPET);
        tag(provider, ItemTags.WOODEN_DOORS, furnitureSet::item, BlockType.DOOR_SINGLE, BlockType.DOOR_DOUBLE);
        tag(provider, ItemTags.WOODEN_BUTTONS, furnitureSet::item, BlockType.BUTTON);
        tag(provider, ItemTags.WOODEN_STAIRS, furnitureSet::item, BlockType.STAIRS);
        tag(provider, ItemTags.WOODEN_SLABS, furnitureSet::item, BlockType.SLAB);
        tag(provider, ItemTags.WOODEN_FENCES, furnitureSet::item, BlockType.FENCE);
        tag(provider, ItemTags.FENCE_GATES, furnitureSet::item, BlockType.FENCE_GATE);
        tag(provider, ItemTags.WOODEN_PRESSURE_PLATES, furnitureSet::item, BlockType.PRESSURE_PLATE);
        tag(provider, ItemTags.WOODEN_TRAPDOORS, furnitureSet::item, BlockType.TRAP_DOOR);
        tag(provider, ItemTags.SIGNS, furnitureSet::item, BlockType.SIGN);
        tag(provider, ItemTags.HANGING_SIGNS, furnitureSet::item, BlockType.HANGING_SIGN);
        // TODO
        // tag(provider, ItemTags.BOATS, furnitureSet::item, ItemType.BOAT);
        // tag(provider, ItemTags.CHEST_BOATS, furnitureSet::item, ItemType.CHEST_BOAT);
        tag(provider, ItemTags.BEDS, furnitureSet::item, BlockType.BED_SINGLE, BlockType.BED_DOUBLE);
        tag(provider, Tags.Items.CHESTS_WOODEN, furnitureSet::item, BlockType.CHEST, BlockType.COUNTER, BlockType.DESK_LEFT, BlockType.DESK_RIGHT, BlockType.DRAWER, BlockType.DRESSER, BlockType.LOCKBOX);
        tag(provider, Tags.Items.PLAYER_WORKSTATIONS_FURNACES, furnitureSet::item, BlockType.OVEN);
    }

    static void recipes(ProviderListenerContext context, RecipeProvider provider, FurnitureSet furnitureSet, BlockFamily family) {
        provider.generateRecipes(family, context.enabledFeatures());

        var planks = furnitureSet.blockOrThrow(BlockType.PLANKS);
        SingleItemRecipeBuilder.stonecutting(DifferenceIngredient.of(provider.tag(ItemTags.PLANKS), provider.tag(FantasyFurniture.FURNITURE_PLANKS)), RecipeCategory.MISC, planks)
                .unlockedBy("has_planks", provider.has(ItemTags.PLANKS))
                .save(provider.output(), ResourceKey.create(Registries.RECIPE, RecipeBuilder.getDefaultRecipeId(planks).withPrefix("conversion/")));

        var wool = furnitureSet.blockOrThrow(BlockType.WOOL);
        SingleItemRecipeBuilder.stonecutting(DifferenceIngredient.of(provider.tag(ItemTags.WOOL), provider.tag(FantasyFurniture.FURNITURE_WOOL)), RecipeCategory.MISC, wool)
                .unlockedBy("has_wool", provider.has(ItemTags.WOOL))
                .save(provider.output(), ResourceKey.create(Registries.RECIPE, RecipeBuilder.getDefaultRecipeId(wool).withPrefix("conversion/")));

        run(furnitureSet, BlockType.CARPET, carpet -> provider.carpet(carpet, wool));

        BlockType.VALUES.stream()
                .filter(BlockType::forFurnitureStation)
                .map(furnitureSet::item)
                .filter(Objects::nonNull)
                .forEach(item -> FurnitureStationRecipeBuilder
                        .builder(RecipeCategory.MISC, Ingredient.of(planks), Ingredient.of(wool), provider.tag(FurnitureStationSetup.BINDING_AGENT), item)
                        .unlockedBy("has_planks", provider.has(planks))
                        .save(provider.output(), ResourceKey.create(Registries.RECIPE, RecipeBuilder.getDefaultRecipeId(item).withPrefix("furniture_station/")))
                );
    }

    private static boolean usesPlacementRenderer(Block block) {
        if(block instanceof TableBlock)
            return true;
        if(block instanceof ComponentHolder && ((ComponentHolder<BlockComponent>) block).hasComponent(BlockComponentTypes.MULTI_BLOCK))
            return true;

        var defaultBlockState = block.defaultBlockState();
        return defaultBlockState.hasProperty(ShelfConnection.PROPERTY) || defaultBlockState.hasProperty(SofaConnection.PROPERTY) || defaultBlockState.hasProperty(CounterConnection.PROPERTY);
    }

    private static <TBlock extends Block & ComponentHolder<BlockComponent>, TValue extends Comparable<TValue>> void facingPropertyModel(TBlock block, BlockModelGenerators blockModels, Function<TBlock, Property<TValue>> propertyGetter, Function<TValue, ResourceLocation> modelGetter, TValue itemValue) {
        var facingProperty = block.getComponentOrThrow(BlockComponentTypes.FACING).getProperty();

        blockModels.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block)
                .with(createPropertyDispatch(block, propertyGetter, modelGetter))
                .with(createHorizontalFacingDispatch(facingProperty, (facing, variant) -> variant))
        );

        blockModels.registerSimpleItemModel(block, modelGetter.apply(itemValue));
    }

    private static <TBlock extends Block & ComponentHolder<BlockComponent>> void multiBlockModel(TBlock block, BlockModelGenerators blockModels, IntFunction<ResourceLocation> modelGetter) {
        var multiBlock = block.getComponentOrThrow(BlockComponentTypes.MULTI_BLOCK);
        var facingProperty = block.getComponentOrThrow(BlockComponentTypes.FACING).getProperty();

        blockModels.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block)
                .with(createPropertyDispatch(block, b -> block.getComponentOrThrow(BlockComponentTypes.MULTI_BLOCK).property(), modelGetter::apply))
                .with(createHorizontalFacingDispatch(facingProperty, (facing, variant) -> variant))
        );

        if(USE_MULTIBLOCK_ITEM_MODELS) {
            blockModels.itemModelOutput.accept(block.asItem(), ItemModelUtils.composite(IntStream
                    .range(0, multiBlock.size())
                    .mapToObj(modelGetter)
                    .map(ItemModelUtils::plainModel)
                    .toArray(ItemModel.Unbaked[]::new)
            ));
        } else {
            registerSimpleBlockItemModel(block, blockModels);
        }
    }

    private static void doorModel(FurnitureDoorBlockComponentHolder block, BlockModelGenerators blockModels) {
        var multiBlock = block.getComponentOrThrow(BlockComponentTypes.MULTI_BLOCK);
        var facingProperty = block.getComponentOrThrow(BlockComponentTypes.FACING).getProperty();

        blockModels.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block)
                .with(PropertyDispatch.properties(facingProperty, multiBlock.property(), DoorBlockComponentHolder.HINGE, DoorBlockComponentHolder.OPEN).generate((facing, index, hinge, open) -> {
                    var indexName = index == MultiBlockComponent.ORIGIN_INDEX ? "bottom" : "top";
                    var openName = open ? "open" : "closed";
                    var modelPath = ModelLocationUtils.getModelLocation(block, '_' + hinge.getSerializedName() + '_' + indexName + '_' + openName);

                    if(open)
                        facing = hinge == DoorHingeSide.LEFT ? facing.getClockWise() : facing.getCounterClockWise();

                    var rot = switch (facing) {
                        case NORTH -> VariantProperties.Rotation.R270;
                        case SOUTH -> VariantProperties.Rotation.R90;
                        case WEST -> VariantProperties.Rotation.R180;
                        default -> VariantProperties.Rotation.R0;
                    };

                    return Variant.variant().with(VariantProperties.MODEL, modelPath).with(VariantProperties.Y_ROT, rot);
                }))
        );

        if(USE_MULTIBLOCK_ITEM_MODELS) {
            blockModels.itemModelOutput.accept(block.asItem(), ItemModelUtils.composite(
                    ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(block, "_left_top")),
                    ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(block, "_left_bottom")),
                    ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(block, "_right_bottom")),
                    ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(block, "_right_bottom"))
            ));
        } else {
            registerSimpleBlockItemModel(block, blockModels);
        }
    }

    private static void horizontalFacingBlock(Block block, Property<Direction> property, BlockModelGenerators blockModels) {
        blockModels.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block)
                .with(createHorizontalFacingDispatch(property, (facing, variant) -> variant.with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(block))))
        );
    }

    private static void tableModel(TableBlock block, BlockModelGenerators blockModels) {
        var facingProperty = block.getComponentOrThrow(BlockComponentTypes.FACING).getProperty();

        blockModels.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block)
                .with(PropertyDispatch.properties(facingProperty, TableBlock.NORTH, TableBlock.EAST, TableBlock.SOUTH, TableBlock.WEST).generate((facing, north, east, south, west) -> {
                    var connections = EnumSet.noneOf(Direction.class);
                    var facingForConnection = TableBlock.getFacingForConnection(facing);

                    if(north)
                        connections.add(Direction.NORTH);
                    if(east)
                        connections.add(Direction.EAST);
                    if(south)
                        connections.add(Direction.SOUTH);
                    if(west)
                        connections.add(Direction.WEST);

                    var rotation = switch (facingForConnection) {
                        case EAST -> Rotation.CLOCKWISE_90;
                        case SOUTH -> Rotation.CLOCKWISE_180;
                        case WEST -> Rotation.COUNTERCLOCKWISE_90;
                        default -> Rotation.NONE;
                    };

                    // might not be the best way to do this but its datagen so who cares about performance
                    // collects connected sides
                    // rotates them to be in correct orientation for facing direction
                    // sorts them into N<-E<-S<-W order
                    // truncates down to single letter per direction
                    // joins entries to single string
                    var suffix = connections.stream().map(rotation::rotate).sorted(Comparator.comparingInt(connection -> switch (connection) {
                        case NORTH -> 0;
                        case EAST -> 1;
                        case SOUTH -> 2;
                        case WEST -> 3;
                        default -> -1;
                    })).map(connection -> switch (connection) {
                        case NORTH -> 'n';
                        case EAST -> 'e';
                        case SOUTH -> 's';
                        case WEST -> 'w';
                        default -> null;
                    }).filter(Objects::nonNull).map(String::valueOf).collect(Collectors.joining(""));

                    return Variant.variant()
                            .with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(block, suffix.isBlank() ? "" : '_' + suffix))
                            .with(VariantProperties.Y_ROT, switch (facing) {
                                case EAST -> VariantProperties.Rotation.R90;
                                case SOUTH -> VariantProperties.Rotation.R180;
                                case WEST -> VariantProperties.Rotation.R270;
                                default -> VariantProperties.Rotation.R0;
                            });
                }))
        );
    }

    private static void registerSimpleBlockItemModel(Block block, BlockModelGenerators blockModels) {
        blockModels.registerSimpleItemModel(block, ModelLocationUtils.getModelLocation(block.asItem()));
    }

    private static <TBlock extends Block, TValue extends Comparable<TValue>> PropertyDispatch createPropertyDispatch(TBlock block, Function<TBlock, Property<TValue>> propertyGetter, Function<TValue, ResourceLocation> modelGetter) {
        return PropertyDispatch.property(propertyGetter.apply(block))
                .generate(value -> Variant.variant().with(VariantProperties.MODEL, modelGetter.apply(value)));
    }

    private static PropertyDispatch createHorizontalFacingDispatch(Property<Direction> property, BiFunction<Direction, Variant, Variant> modifier) {
        return PropertyDispatch.property(property)
                .select(Direction.EAST, modifier.apply(Direction.EAST, Variant.variant().with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)))
                .select(Direction.SOUTH, modifier.apply(Direction.SOUTH, Variant.variant().with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)))
                .select(Direction.WEST, modifier.apply(Direction.WEST, Variant.variant().with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)))
                .select(Direction.NORTH, modifier.apply(Direction.NORTH, Variant.variant()));
    }

    private static <TRegistry> void tag(TagProvider<TRegistry, ?> provider, TagKey<TRegistry> tag, Function<BlockType<?, ?>, @Nullable Holder<TRegistry>> holderGetter, BlockType<?, ?>... blockTypes) {
        for(var blockType : blockTypes) {
            var holder = holderGetter.apply(blockType);

            if(holder != null)
                provider.tag(tag).withElement(holder);
        }
    }

    private static <TBlock extends Block> void run(FurnitureSet furnitureSet, BlockType<TBlock, ?> blockType, Consumer<TBlock> consumer) {
        var block = furnitureSet.block(blockType);

        if(block != null)
            consumer.accept(block.value());
    }

    private static boolean matches(FurnitureSet furnitureSet, BlockType<?, ?> blockType, Block block) {
        var otherBlock = furnitureSet.block(blockType);
        return otherBlock != null && otherBlock.is(block);
    }
}
