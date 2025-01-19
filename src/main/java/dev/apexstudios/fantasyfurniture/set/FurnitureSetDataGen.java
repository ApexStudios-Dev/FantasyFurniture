package dev.apexstudios.fantasyfurniture.set;

import dev.apexstudios.apexcore.core.seat.SeatSetup;
import dev.apexstudios.apexcore.lib.component.ComponentHolder;
import dev.apexstudios.apexcore.lib.component.ComponentType;
import dev.apexstudios.apexcore.lib.component.block.BlockComponent;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.data.provider.LanguageProvider;
import dev.apexstudios.apexcore.lib.data.provider.ModelProvider;
import dev.apexstudios.apexcore.lib.data.provider.context.ProviderListenerContext;
import dev.apexstudios.apexcore.lib.data.provider.loot.LootTableProvider;
import dev.apexstudios.apexcore.lib.data.provider.tag.IntrusiveTagProvider;
import dev.apexstudios.apexcore.lib.data.provider.tag.TagProvider;
import dev.apexstudios.apexcore.lib.multiblock.MultiBlock;
import dev.apexstudios.apexcore.lib.placement.BlockPlacementRenderer;
import dev.apexstudios.apexcore.lib.registree.holder.DeferredBlock;
import dev.apexstudios.fantasyfurniture.block.BookshelfBlock;
import dev.apexstudios.fantasyfurniture.block.ChairBlock;
import dev.apexstudios.fantasyfurniture.block.DresserBlock;
import it.unimi.dsi.fastutil.ints.Int2ObjectFunction;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.blockstates.Variant;
import net.minecraft.client.data.models.blockstates.VariantProperties;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.flag.FeatureElement;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.neoforge.common.Tags;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

interface FurnitureSetDataGen {
    boolean USE_MULTIBLOCK_ITEM_MODELS = false;

    static void lootTables(ProviderListenerContext context, LootTableProvider provider, FurnitureSet furnitureSet) {
        provider.fromRegistree(furnitureSet.registree());

        provider.block(blocks -> {
            blocks.dropSelf(furnitureSet.block(BlockType.WOOL).value());
            blocks.dropSelf(furnitureSet.block(BlockType.CARPET).value());
            blocks.dropSelf(furnitureSet.block(BlockType.DRESSER).value());
            blocks.dropSelf(furnitureSet.block(BlockType.STOOL).value());
            blocks.dropSelf(furnitureSet.block(BlockType.CUSHION).value());

            blocks.dropSelf(furnitureSet.block(BlockType.BED_DOUBLE).value());
            blocks.dropSelf(furnitureSet.block(BlockType.BED_SINGLE).value());
            blocks.dropSelf(furnitureSet.block(BlockType.BOOKSHELF).value());
            blocks.dropSelf(furnitureSet.block(BlockType.CHAIR).value());
            blocks.dropSelf(furnitureSet.block(BlockType.CHANDELIER).value());
            blocks.dropSelf(furnitureSet.block(BlockType.CHEST).value());
            blocks.dropSelf(furnitureSet.block(BlockType.COUNTER).value());
            blocks.dropSelf(furnitureSet.block(BlockType.DESK).value());
            blocks.dropSelf(furnitureSet.block(BlockType.DOOR_DOUBLE).value());
            blocks.dropSelf(furnitureSet.block(BlockType.DOOR_SINGLE).value());
            blocks.dropSelf(furnitureSet.block(BlockType.DRAWER).value());
            blocks.dropSelf(furnitureSet.block(BlockType.FLOOR_LIGHT).value());
            blocks.dropSelf(furnitureSet.block(BlockType.LOCKBOX).value());
            blocks.dropSelf(furnitureSet.block(BlockType.OVEN).value());
            blocks.dropSelf(furnitureSet.block(BlockType.PAINTING_SMALL).value());
            blocks.dropSelf(furnitureSet.block(BlockType.PAINTING_WIDE).value());
            blocks.dropSelf(furnitureSet.block(BlockType.SHELF).value());
            blocks.dropSelf(furnitureSet.block(BlockType.SOFA).value());
            blocks.dropSelf(furnitureSet.block(BlockType.TABLE_LARGE).value());
            blocks.dropSelf(furnitureSet.block(BlockType.TABLE_WIDE).value());
            blocks.dropSelf(furnitureSet.block(BlockType.TABLE_SMALL).value());
        });
    }

    static void models(ProviderListenerContext context, ModelProvider provider, FurnitureSet furnitureSet) {
        provider.fromRegistree(furnitureSet.registree());

        var blockModels = provider.blockModels();

        blockModels.createFullAndCarpetBlocks(furnitureSet.block(BlockType.WOOL).value(), furnitureSet.block(BlockType.CARPET).value());
        dresserModel(context, furnitureSet.block(BlockType.DRESSER), blockModels);
        componentBlock(context, furnitureSet.block(BlockType.STOOL), BlockComponentTypes.FACING, (block, component) -> horizontalFacingBlock(block, component.getProperty(), blockModels));
        componentBlock(context, furnitureSet.block(BlockType.CUSHION), BlockComponentTypes.FACING, (block, component) -> horizontalFacingBlock(block, component.getProperty(), blockModels));
        componentBlock(context, furnitureSet.block(BlockType.LOCKBOX), BlockComponentTypes.FACING, (block, component) -> horizontalFacingBlock(block, component.getProperty(), blockModels));
        componentBlock(context, furnitureSet.block(BlockType.DRAWER), BlockComponentTypes.FACING, (block, component) -> horizontalFacingBlock(block, component.getProperty(), blockModels));
        chairModel(context, furnitureSet.block(BlockType.CHAIR), blockModels);
        bookshelfModel(context, furnitureSet.block(BlockType.BOOKSHELF), blockModels);

        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(furnitureSet.block(BlockType.BED_DOUBLE).value(), ModelLocationUtils.getModelLocation(furnitureSet.block(BlockType.BED_DOUBLE).value())));
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(furnitureSet.block(BlockType.BED_SINGLE).value(), ModelLocationUtils.getModelLocation(furnitureSet.block(BlockType.BED_SINGLE).value())));
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(furnitureSet.block(BlockType.CHANDELIER).value(), ModelLocationUtils.getModelLocation(furnitureSet.block(BlockType.CHANDELIER).value())));
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(furnitureSet.block(BlockType.CHEST).value(), ModelLocationUtils.getModelLocation(furnitureSet.block(BlockType.CHEST).value())));
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(furnitureSet.block(BlockType.COUNTER).value(), ModelLocationUtils.getModelLocation(furnitureSet.block(BlockType.COUNTER).value())));
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(furnitureSet.block(BlockType.DESK).value(), ModelLocationUtils.getModelLocation(furnitureSet.block(BlockType.DESK).value())));
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(furnitureSet.block(BlockType.DOOR_DOUBLE).value(), ModelLocationUtils.getModelLocation(furnitureSet.block(BlockType.DOOR_DOUBLE).value())));
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(furnitureSet.block(BlockType.DOOR_SINGLE).value(), ModelLocationUtils.getModelLocation(furnitureSet.block(BlockType.DOOR_SINGLE).value())));
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(furnitureSet.block(BlockType.FLOOR_LIGHT).value(), ModelLocationUtils.getModelLocation(furnitureSet.block(BlockType.FLOOR_LIGHT).value())));
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(furnitureSet.block(BlockType.OVEN).value(), ModelLocationUtils.getModelLocation(furnitureSet.block(BlockType.OVEN).value())));
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(furnitureSet.block(BlockType.PAINTING_SMALL).value(), ModelLocationUtils.getModelLocation(furnitureSet.block(BlockType.PAINTING_SMALL).value())));
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(furnitureSet.block(BlockType.PAINTING_WIDE).value(), ModelLocationUtils.getModelLocation(furnitureSet.block(BlockType.PAINTING_WIDE).value())));
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(furnitureSet.block(BlockType.SHELF).value(), ModelLocationUtils.getModelLocation(furnitureSet.block(BlockType.SHELF).value())));
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(furnitureSet.block(BlockType.SOFA).value(), ModelLocationUtils.getModelLocation(furnitureSet.block(BlockType.SOFA).value())));
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(furnitureSet.block(BlockType.TABLE_LARGE).value(), ModelLocationUtils.getModelLocation(furnitureSet.block(BlockType.TABLE_LARGE).value())));
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(furnitureSet.block(BlockType.TABLE_WIDE).value(), ModelLocationUtils.getModelLocation(furnitureSet.block(BlockType.TABLE_WIDE).value())));
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(furnitureSet.block(BlockType.TABLE_SMALL).value(), ModelLocationUtils.getModelLocation(furnitureSet.block(BlockType.TABLE_SMALL).value())));
    }

    static void language(ProviderListenerContext context, LanguageProvider provider, FurnitureSet furnitureSet, String englishName) {
        BlockType.VALUES.forEach(blockType -> {
            var item = furnitureSet.item(blockType);

            if(isEnabled(context, item)) {
                var name = Stream.of(StringUtils.split(blockType.name(), '_')).map(StringUtils::capitalize).collect(Collectors.joining(" "));
                provider.addItem(item, englishName + ' ' + name);
            }
        });

        provider.addCreativeModeTab(furnitureSet.creativeModeTab(), englishName + " Furniture Set");
    }

    static void blockTags(ProviderListenerContext context, IntrusiveTagProvider<Block> provider, FurnitureSet furnitureSet) {
        tag(context, provider, BlockTags.WOOL, furnitureSet::block, BlockType.WOOL);
        tag(context, provider, BlockTags.WOOL_CARPETS, furnitureSet::block, BlockType.CARPET);
        tag(context, provider, BlockTags.WOODEN_DOORS, furnitureSet::block, BlockType.DOOR_SINGLE, BlockType.DOOR_DOUBLE);
        tag(context, provider, BlockTags.BEDS, furnitureSet::block, BlockType.BED_SINGLE, BlockType.BED_DOUBLE);
        tag(context, provider, Tags.Blocks.CHESTS_WOODEN, furnitureSet::block, BlockType.CHEST, BlockType.COUNTER, BlockType.DESK, BlockType.DRAWER, BlockType.DRESSER, BlockType.LOCKBOX);
        tag(context, provider, Tags.Blocks.PLAYER_WORKSTATIONS_FURNACES, furnitureSet::block, BlockType.OVEN);
        tag(context, provider, SeatSetup.ORIGIN_ONLY, furnitureSet::block, BlockType.CHAIR);

        var placementRender = provider.tag(BlockPlacementRenderer.BLOCK_WHITELIST);
        var relocation = provider.tag(Tags.Blocks.RELOCATION_NOT_SUPPORTED);
        var mineableWithAxe = provider.tag(BlockTags.MINEABLE_WITH_AXE);

        listElements(context, furnitureSet, Registries.BLOCK).map(Holder::value).forEach(block -> {
            if(block instanceof ComponentHolder && ((ComponentHolder<BlockComponent>) block).hasComponent(BlockComponentTypes.MULTI_BLOCK)) {
                placementRender.withElement(block);
                relocation.withElement(block);
            }

            if(!furnitureSet.block(BlockType.WOOL).is(block) && !furnitureSet.block(BlockType.CARPET).is(block))
                mineableWithAxe.withElement(block);
        });
    }

    static void itemTags(ProviderListenerContext context, IntrusiveTagProvider<Item> provider, FurnitureSet furnitureSet) {
        tag(context, provider, ItemTags.WOOL, furnitureSet::item, BlockType.WOOL);
        tag(context, provider, ItemTags.WOOL_CARPETS, furnitureSet::item, BlockType.CARPET);
        tag(context, provider, ItemTags.WOODEN_DOORS, furnitureSet::item, BlockType.DOOR_SINGLE, BlockType.DOOR_DOUBLE);
        tag(context, provider, ItemTags.BEDS, furnitureSet::item, BlockType.BED_SINGLE, BlockType.BED_DOUBLE);
        tag(context, provider, Tags.Items.CHESTS_WOODEN, furnitureSet::item, BlockType.CHEST, BlockType.COUNTER, BlockType.DESK, BlockType.DRAWER, BlockType.DRESSER, BlockType.LOCKBOX);
        tag(context, provider, Tags.Items.PLAYER_WORKSTATIONS_FURNACES, furnitureSet::item, BlockType.OVEN);
    }

    private static void dresserModel(ProviderListenerContext context, DeferredBlock<DresserBlock> holder, BlockModelGenerators blockModels) {
        if(!isEnabled(context, holder))
            return;

        var block = holder.value();
        var multiBlock = block.getComponentOrThrow(BlockComponentTypes.MULTI_BLOCK);
        var facing = block.getComponentOrThrow(BlockComponentTypes.FACING).getProperty();

        blockModels.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block)
                .with(createMultiBlockPropertyDispatch(multiBlock, index -> ModelLocationUtils.getModelLocation(block, index == MultiBlock.ORIGIN_INDEX ? "_left" : "_right")))
                .with(createHorizontalFacingDispatch(facing))
        );

        if(USE_MULTIBLOCK_ITEM_MODELS) {
            blockModels.itemModelOutput.accept(block.asItem(), ItemModelUtils.composite(
                    ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(block, "_left")),
                    ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(block, "_right"))
            ));
        } else {
            registerSimpleBlockItemModel(block, blockModels);
        }
    }

    private static void chairModel(ProviderListenerContext context, DeferredBlock<ChairBlock> holder, BlockModelGenerators blockModels) {
        if(!isEnabled(context, holder))
            return;

        var block = holder.value();
        var multiBlock = block.getComponentOrThrow(BlockComponentTypes.MULTI_BLOCK);
        var facing = block.getComponentOrThrow(BlockComponentTypes.FACING).getProperty();

        blockModels.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block)
                .with(createMultiBlockPropertyDispatch(multiBlock, index -> ModelLocationUtils.getModelLocation(block, index == MultiBlock.ORIGIN_INDEX ? "_bottom" : "_top")))
                .with(createHorizontalFacingDispatch(facing))
        );

        if(USE_MULTIBLOCK_ITEM_MODELS) {
            blockModels.itemModelOutput.accept(block.asItem(), ItemModelUtils.composite(
                    ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(block, "_bottom")),
                    ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(block, "_top"))
            ));
        } else {
            registerSimpleBlockItemModel(block, blockModels);
        }
    }

    private static void bookshelfModel(ProviderListenerContext context, DeferredBlock<BookshelfBlock> holder, BlockModelGenerators blockModels) {
        if(!isEnabled(context, holder))
            return;

        var block = holder.value();
        var multiBlock = block.getComponentOrThrow(BlockComponentTypes.MULTI_BLOCK);
        var facing = block.getComponentOrThrow(BlockComponentTypes.FACING).getProperty();

        blockModels.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block)
                .with(createMultiBlockPropertyDispatch(multiBlock, index -> switch (index) {
                    case 1 -> ModelLocationUtils.getModelLocation(block, "_bottom_right");
                    case 2 -> ModelLocationUtils.getModelLocation(block, "_top_right");
                    case 3 -> ModelLocationUtils.getModelLocation(block, "_top_left");
                    default -> ModelLocationUtils.getModelLocation(block, "_bottom_left");
                }))
                .with(createHorizontalFacingDispatch(facing))
        );

        if(USE_MULTIBLOCK_ITEM_MODELS) {
            blockModels.itemModelOutput.accept(block.asItem(), ItemModelUtils.composite(
                    ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(block, "_bottom_left")),
                    ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(block, "_bottom_right")),
                    ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(block, "_top_left")),
                    ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(block, "_top_right"))
            ));
        } else {
            registerSimpleBlockItemModel(block, blockModels);
        }
    }

    private static <TBlock extends Block & ComponentHolder<BlockComponent>, TComponent extends BlockComponent> void componentBlock(ProviderListenerContext context, Supplier<TBlock> blockSupplier, ComponentType<BlockComponent, TComponent, ?> componentType, BiConsumer<TBlock, TComponent> consumer) {
        if(!isEnabled(context, blockSupplier))
            return;

        var block = blockSupplier.get();
        var component = block.getComponentOrThrow(componentType);
        consumer.accept(block, component);
    }

    private static void horizontalFacingBlock(Block block, Property<Direction> property, BlockModelGenerators blockModels) {
        blockModels.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block)
                .with(createHorizontalFacingDispatch(property, variant -> variant.with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(block))))
        );
    }

    private static void registerSimpleBlockItemModel(Block block, BlockModelGenerators blockModels) {
        blockModels.registerSimpleItemModel(block, ModelLocationUtils.getModelLocation(block.asItem()));
    }

    private static PropertyDispatch createMultiBlockPropertyDispatch(MultiBlock multiBlock, Int2ObjectFunction<ResourceLocation> blockModelPathFactory) {
        return PropertyDispatch.property(multiBlock.getMultiBlockType().property())
                .generate(index -> Variant.variant()
                        .with(VariantProperties.MODEL, blockModelPathFactory.apply(index))
                );
    }

    private static PropertyDispatch createHorizontalFacingDispatch(Property<Direction> property, BiFunction<Direction, Variant, Variant> modifier) {
        return PropertyDispatch.property(property)
                .select(Direction.EAST, modifier.apply(Direction.EAST, Variant.variant().with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)))
                .select(Direction.SOUTH, modifier.apply(Direction.SOUTH, Variant.variant().with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)))
                .select(Direction.WEST, modifier.apply(Direction.WEST, Variant.variant().with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)))
                .select(Direction.NORTH, modifier.apply(Direction.NORTH, Variant.variant()));
    }

    private static PropertyDispatch createHorizontalFacingDispatch(Property<Direction> property, UnaryOperator<Variant> modifier) {
        return createHorizontalFacingDispatch(property, (facing, variant) -> modifier.apply(variant));
    }

    private static PropertyDispatch createHorizontalFacingDispatch(Property<Direction> property) {
        return createHorizontalFacingDispatch(property, UnaryOperator.identity());
    }

    private static <TRegistry> void tag(ProviderListenerContext context, TagProvider<TRegistry, ?> provider, TagKey<TRegistry> tag, Function<BlockType<?, ?>, Holder<TRegistry>> holderGetter, Stream<BlockType<?, ?>> blockTypes) {
        blockTypes.map(holderGetter).filter(holder -> isEnabled(context, holder)).forEach(holder -> provider.tag(tag).withElement(holder));
    }

    private static <TRegistry> void tag(ProviderListenerContext context, TagProvider<TRegistry, ?> provider, TagKey<TRegistry> tag, Function<BlockType<?, ?>, Holder<TRegistry>> holderGetter, BlockType<?, ?>... blockTypes) {
        tag(context, provider, tag, holderGetter, Stream.of(blockTypes));
    }

    private static <TRegistry> Stream<Holder.Reference<TRegistry>> listElements(ProviderListenerContext context, FurnitureSet furnitureSet, ResourceKey<? extends Registry<TRegistry>> registryType) {
        return furnitureSet.registree()
                .asLookup(registryType)
                .filterFeatures(context.enabledFeatures())
                .listElements();
    }

    private static boolean isEnabled(ProviderListenerContext context, @Nullable Object object) {
        return isEnabled(context.enabledFeatures(), object);
    }

    private static boolean isEnabled(FeatureFlagSet enabledFeatures, @Nullable Object object) {
        if(object instanceof Holder<?> holder)
            return isEnabled(enabledFeatures, holder.value());
        else if(object instanceof Supplier<?> supplier)
            return isEnabled(enabledFeatures, supplier.get());
        else if(object instanceof FeatureElement element)
            return element.isEnabled(enabledFeatures);

        return object != null;
    }
}
