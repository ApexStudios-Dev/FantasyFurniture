package dev.apexstudios.fantasyfurniture.set;

import dev.apexstudios.apexcore.core.seat.SeatSetup;
import dev.apexstudios.apexcore.lib.component.ComponentHolder;
import dev.apexstudios.apexcore.lib.component.block.BlockComponent;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.component.block.DoorBlockComponentHolder;
import dev.apexstudios.apexcore.lib.component.block.types.MultiBlockComponent;
import dev.apexstudios.apexcore.lib.data.provider.LanguageProvider;
import dev.apexstudios.apexcore.lib.data.provider.ModelProvider;
import dev.apexstudios.apexcore.lib.data.provider.loot.LootTableProvider;
import dev.apexstudios.apexcore.lib.data.provider.tag.IntrusiveTagProvider;
import dev.apexstudios.apexcore.lib.data.provider.tag.TagProvider;
import dev.apexstudios.apexcore.lib.placement.BlockPlacementRenderer;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureDoorBlockComponentHolder;
import it.unimi.dsi.fastutil.ints.Int2ObjectFunction;
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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.neoforge.common.Tags;
import org.apache.commons.lang3.StringUtils;

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

    static void models(ModelProvider provider, FurnitureSet furnitureSet) {
        provider.fromRegistree(furnitureSet.registree());

        var blockModels = provider.blockModels();

        run(furnitureSet, BlockType.WOOL, wool -> blockModels.createFullAndCarpetBlocks(wool, furnitureSet.block(BlockType.CARPET).value()));
        run(furnitureSet, BlockType.PLANKS, blockModels::createTrivialCube);
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
        run(furnitureSet, BlockType.TABLE_LARGE, block -> multiBlockModel(block, blockModels, index -> ModelLocationUtils.getModelLocation(block, switch (index) {
            case 1 -> "_top_left";
            case 2 -> "_bottom_right";
            case 3 -> "_top_right";
            default -> "_bottom_left";
        })));
        run(furnitureSet, BlockType.TABLE_WIDE, block -> multiBlockModel(block, blockModels, index -> ModelLocationUtils.getModelLocation(block, index == MultiBlockComponent.ORIGIN_INDEX ? "_left" : "_right")));
        run(furnitureSet, BlockType.TABLE_SMALL, block -> horizontalFacingBlock(block, block.getComponentOrThrow(BlockComponentTypes.FACING).getProperty(), blockModels));
        run(furnitureSet, BlockType.FLOOR_LIGHT, block -> multiBlockModel(block, blockModels, index -> ModelLocationUtils.getModelLocation(block, index == MultiBlockComponent.ORIGIN_INDEX ? "_bottom" : "_top")));

        run(furnitureSet, BlockType.CHANDELIER, block -> blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, ModelLocationUtils.getModelLocation(block))));
        run(furnitureSet, BlockType.COUNTER, block -> blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, ModelLocationUtils.getModelLocation(block))));
        run(furnitureSet, BlockType.SHELF, block -> blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, ModelLocationUtils.getModelLocation(block))));
        run(furnitureSet, BlockType.SOFA, block -> blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, ModelLocationUtils.getModelLocation(block))));
    }

    static void language(LanguageProvider provider, FurnitureSet furnitureSet, String englishName) {
        BlockType.VALUES.forEach(blockType -> {
            var name = Stream.of(StringUtils.split(blockType.name(), '_')).map(StringUtils::capitalize).collect(Collectors.joining(" "));
            provider.addItem(furnitureSet.item(blockType), englishName + ' ' + name);
        });

        provider.addCreativeModeTab(furnitureSet.creativeModeTab(), englishName + " Furniture Set");
    }

    static void blockTags(IntrusiveTagProvider<Block> provider, FurnitureSet furnitureSet) {
        tag(provider, BlockTags.PLANKS, furnitureSet::block, BlockType.PLANKS);
        tag(provider, BlockTags.WOOL, furnitureSet::block, BlockType.WOOL);
        tag(provider, BlockTags.WOOL_CARPETS, furnitureSet::block, BlockType.CARPET);
        tag(provider, BlockTags.WOODEN_DOORS, furnitureSet::block, BlockType.DOOR_SINGLE, BlockType.DOOR_DOUBLE);
        tag(provider, BlockTags.BEDS, furnitureSet::block, BlockType.BED_SINGLE, BlockType.BED_DOUBLE);
        tag(provider, Tags.Blocks.CHESTS_WOODEN, furnitureSet::block, BlockType.CHEST, BlockType.COUNTER, BlockType.DESK_LEFT, BlockType.DESK_RIGHT, BlockType.DRAWER, BlockType.DRESSER, BlockType.LOCKBOX);
        tag(provider, Tags.Blocks.PLAYER_WORKSTATIONS_FURNACES, furnitureSet::block, BlockType.OVEN);
        tag(provider, SeatSetup.ORIGIN_ONLY, furnitureSet::block, BlockType.CHAIR);

        var placementRender = provider.tag(BlockPlacementRenderer.BLOCK_WHITELIST);
        var relocation = provider.tag(Tags.Blocks.RELOCATION_NOT_SUPPORTED);
        var mineableWithAxe = provider.tag(BlockTags.MINEABLE_WITH_AXE);

        furnitureSet.registree().listElements(Registries.BLOCK).map(Holder::value).forEach(block -> {
            if(block instanceof ComponentHolder && ((ComponentHolder<BlockComponent>) block).hasComponent(BlockComponentTypes.MULTI_BLOCK)) {
                placementRender.withElement(block);
                relocation.withElement(block);
            }

            if(!furnitureSet.block(BlockType.WOOL).is(block) && !furnitureSet.block(BlockType.CARPET).is(block))
                mineableWithAxe.withElement(block);
        });
    }

    static void itemTags(IntrusiveTagProvider<Item> provider, FurnitureSet furnitureSet) {
        tag(provider, ItemTags.PLANKS, furnitureSet::item, BlockType.PLANKS);
        tag(provider, ItemTags.WOOL, furnitureSet::item, BlockType.WOOL);
        tag(provider, ItemTags.WOOL_CARPETS, furnitureSet::item, BlockType.CARPET);
        tag(provider, ItemTags.WOODEN_DOORS, furnitureSet::item, BlockType.DOOR_SINGLE, BlockType.DOOR_DOUBLE);
        tag(provider, ItemTags.BEDS, furnitureSet::item, BlockType.BED_SINGLE, BlockType.BED_DOUBLE);
        tag(provider, Tags.Items.CHESTS_WOODEN, furnitureSet::item, BlockType.CHEST, BlockType.COUNTER, BlockType.DESK_LEFT, BlockType.DESK_RIGHT, BlockType.DRAWER, BlockType.DRESSER, BlockType.LOCKBOX);
        tag(provider, Tags.Items.PLAYER_WORKSTATIONS_FURNACES, furnitureSet::item, BlockType.OVEN);
    }

    private static <TBlock extends Block & ComponentHolder<BlockComponent>> void multiBlockModel(TBlock block, BlockModelGenerators blockModels, IntFunction<ResourceLocation> modelGetter) {
        var multiBlock = block.getComponentOrThrow(BlockComponentTypes.MULTI_BLOCK);
        var facingProperty = block.getComponentOrThrow(BlockComponentTypes.FACING).getProperty();

        blockModels.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block)
                .with(createMultiBlockPropertyDispatch(multiBlock, modelGetter::apply))
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

    private static void registerSimpleBlockItemModel(Block block, BlockModelGenerators blockModels) {
        blockModels.registerSimpleItemModel(block, ModelLocationUtils.getModelLocation(block.asItem()));
    }

    private static PropertyDispatch createMultiBlockPropertyDispatch(MultiBlockComponent multiBlock, Int2ObjectFunction<ResourceLocation> blockModelPathFactory) {
        return PropertyDispatch.property(multiBlock.property())
                .generate(index -> Variant.variant().with(VariantProperties.MODEL, blockModelPathFactory.apply(index)));
    }

    private static PropertyDispatch createHorizontalFacingDispatch(Property<Direction> property, BiFunction<Direction, Variant, Variant> modifier) {
        return PropertyDispatch.property(property)
                .select(Direction.EAST, modifier.apply(Direction.EAST, Variant.variant().with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)))
                .select(Direction.SOUTH, modifier.apply(Direction.SOUTH, Variant.variant().with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)))
                .select(Direction.WEST, modifier.apply(Direction.WEST, Variant.variant().with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)))
                .select(Direction.NORTH, modifier.apply(Direction.NORTH, Variant.variant()));
    }

    private static <TRegistry> void tag(TagProvider<TRegistry, ?> provider, TagKey<TRegistry> tag, Function<BlockType<?, ?>, Holder<TRegistry>> holderGetter, BlockType<?, ?>... blockTypes) {
        for(var blockType : blockTypes) {
            provider.tag(tag).withElement(holderGetter.apply(blockType));
        }
    }

    private static <TBlock extends Block> void run(FurnitureSet furnitureSet, BlockType<TBlock, ?> blockType, Consumer<TBlock> consumer) {
        consumer.accept(furnitureSet.block(blockType).value());
    }
}
