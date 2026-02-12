package dev.apexstudios.fantasyfurniture.royal.data;

import dev.apexstudios.apexcore.api.block.Dyeable;
import dev.apexstudios.apexcore.api.data.ProviderTypes;
import dev.apexstudios.apexcore.api.data.ResourceGenerator;
import dev.apexstudios.apexcore.api.data.provider.context.ProviderListenerContext;
import dev.apexstudios.apexcore.api.data.provider.model.ApexModelTemplates;
import dev.apexstudios.apexcore.api.data.provider.model.ModelProvider;
import dev.apexstudios.apexcore.api.data.provider.tag.IntrusiveTagProvider;
import dev.apexstudios.apexcore.api.util.TagPair;
import dev.apexstudios.apexcore.client.DyeColorItemTintSource;
import dev.apexstudios.fantasyfurniture.common.block.property.CounterConnection;
import dev.apexstudios.fantasyfurniture.common.block.property.ShelfConnection;
import dev.apexstudios.fantasyfurniture.common.block.property.SofaConnection;
import dev.apexstudios.fantasyfurniture.common.ctm.CtmPacks;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureClientDataUtil;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureDataUtil;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import dev.apexstudios.fantasyfurniture.royal.common.RoyalFurnitureSet;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.data.BlockFamily;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;

@Mod(RoyalFurnitureSet.ID)
public final class RoyalFurnitureSetDataEntryPoint {
    public RoyalFurnitureSetDataEntryPoint(IEventBus modBus) {
        ResourceGenerator.of(modBus, generator -> {
            var pack = generator.pack();
            var context = new FurnitureDataUtil.DataGenContext(
                    RoyalFurnitureSet.REGISTREE,
                    "Royal",
                    new BlockFamily.Builder(RoyalFurnitureSet.BRICKS.value())
                            .recipeGroupPrefix("royal")
                            .recipeUnlockedBy("has_" + FurnitureUtil.Names.BRICKS)
                            .stairs(RoyalFurnitureSet.STAIRS.value())
                            .slab(RoyalFurnitureSet.SLAB.value())
                            .fence(RoyalFurnitureSet.FENCE.value())
                            .fenceGate(RoyalFurnitureSet.FENCE_GATE.value())
                            .trapdoor(RoyalFurnitureSet.TRAPDOOR.value())
                            .pressurePlate(RoyalFurnitureSet.PRESSURE_PLATE.value())
                            .sign(RoyalFurnitureSet.SIGN.sign().value(), RoyalFurnitureSet.SIGN.wall().value())
                    .getFamily(),
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    new TagPair(BlockTags.DOORS, ItemTags.DOORS),
                    BlockTags.STAIRS,
                    new TagPair(BlockTags.BUTTONS, ItemTags.BUTTONS),
                    new TagPair(BlockTags.PRESSURE_PLATES, null),
                    new TagPair(BlockTags.TRAPDOORS, ItemTags.TRAPDOORS),
                    new TagPair(BlockTags.FENCES, ItemTags.FENCES),
                    new TagPair(BlockTags.SLABS, ItemTags.SLABS),
                    exclusions -> {
                        exclusions.put(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.WOOL);
                        exclusions.put(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.CARPET);
                        exclusions.put(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.DRESSER);
                        exclusions.put(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.CHAIR);
                        exclusions.put(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.BOOKSHELF);
                        exclusions.put(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.BED_SINGLE);
                        exclusions.put(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.BED_DOUBLE);
                        exclusions.put(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.DOOR_SINGLE);
                        exclusions.put(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.DOOR_DOUBLE);
                        exclusions.put(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.DESK_LEFT);
                        exclusions.put(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.DESK_RIGHT);
                        exclusions.put(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.CHEST);
                        exclusions.put(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.SHELF);
                        exclusions.put(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.SOFA);
                        exclusions.put(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.COUNTER);
                        exclusions.put(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.BENCH);
                        exclusions.put(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.WARDROBE);
                    }
            );

            FurnitureDataUtil.registerDataGen(context, pack);
            FurnitureClientDataUtil.registerDataGen(context, pack);
            CtmPacks.registerDataGen(RoyalFurnitureSet.BLOCKS, pack, true);

            pack.providing(ProviderTypes.MODELS, this::registerModels)
                    .providing(ProviderTypes.BLOCK_TAGS, this::registerBlockTags)
                    .providing(ProviderTypes.ITEM_TAGS, this::registerItemTags);
        });
    }

    private void registerModels(ProviderListenerContext context, ModelProvider provider) {
        var blockModels = provider.blockModels();
        var wool = RoyalFurnitureSet.WOOL.value();

        createDyeableModel(wool, ApexModelTemplates.Textured.CUBE_ALL_TINTED, blockModels);

        createDyeableModel(RoyalFurnitureSet.CARPET.value(), ApexModelTemplates.Textured.CARPET_TINTED.updateTexture(textures -> textures
                .put(TextureSlot.WOOL, TextureMapping.getBlockTexture(wool))
                .put(ApexModelTemplates.SLOT_WOOL_TINTED, TextureMapping.getBlockTexture(wool, "_tint"))
        ), blockModels);

        FurnitureClientDataUtil.createLeftRightModel(RoyalFurnitureSet.DRESSER.value(), blockModels);
        FurnitureClientDataUtil.createBottomTopModel(RoyalFurnitureSet.CHAIR.value(), blockModels);
        FurnitureClientDataUtil.createBookshelfModel(RoyalFurnitureSet.BOOKSHELF.value(), blockModels);
        FurnitureClientDataUtil.createBedSingleModel(RoyalFurnitureSet.BED_SINGLE.value(), blockModels);
        FurnitureClientDataUtil.createBedDoubleModel(RoyalFurnitureSet.BED_DOUBLE.value(), blockModels);
        FurnitureClientDataUtil.createDoorModel(RoyalFurnitureSet.DOOR_SINGLE.value(), blockModels);
        FurnitureClientDataUtil.createDoorModel(RoyalFurnitureSet.DOOR_DOUBLE.value(), blockModels);
        FurnitureClientDataUtil.createLeftRightModel(RoyalFurnitureSet.DESK_LEFT.value(), blockModels);
        FurnitureClientDataUtil.createLeftRightModel(RoyalFurnitureSet.DESK_RIGHT.value(), blockModels);
        FurnitureClientDataUtil.createLeftRightModel(RoyalFurnitureSet.CHEST.value(), blockModels);
        FurnitureClientDataUtil.createShelfModel(RoyalFurnitureSet.SHELF.value(), blockModels);
        FurnitureClientDataUtil.createSofaModel(RoyalFurnitureSet.SOFA.value(), blockModels);
        FurnitureClientDataUtil.createCounterModel(RoyalFurnitureSet.COUNTER.value(), blockModels);
        FurnitureClientDataUtil.createLeftRightModel(RoyalFurnitureSet.BENCH.value(), blockModels);
        FurnitureClientDataUtil.createWardrobeModel(RoyalFurnitureSet.WARDROBE.value(), blockModels);

        registerDyeableItemModel(RoyalFurnitureSet.WOOL.value(), false, blockModels);
        registerDyeableItemModel(RoyalFurnitureSet.CARPET.value(), false, blockModels);
        registerDyeableItemModel(RoyalFurnitureSet.DRESSER.value(), true, blockModels);
        registerDyeableItemModel(RoyalFurnitureSet.STOOL.value(), false, blockModels);
        registerDyeableItemModel(RoyalFurnitureSet.CUSION.value(), false, blockModels);
        registerDyeableItemModel(RoyalFurnitureSet.LOCKBOX.value(), false, blockModels);
        registerDyeableItemModel(RoyalFurnitureSet.DRAWER.value(), false, blockModels);
        registerDyeableItemModel(RoyalFurnitureSet.CHAIR.value(), true, blockModels);
        registerDyeableItemModel(RoyalFurnitureSet.BOOKSHELF.value(), true, blockModels);
        registerDyeableItemModel(RoyalFurnitureSet.BED_SINGLE.value(), true, blockModels);
        registerDyeableItemModel(RoyalFurnitureSet.BED_DOUBLE.value(), true, blockModels);
        registerDyeableFlatItemModel(RoyalFurnitureSet.DOOR_SINGLE.value(), blockModels);
        registerDyeableFlatItemModel(RoyalFurnitureSet.DOOR_DOUBLE.value(), blockModels);
        registerDyeableItemModel(RoyalFurnitureSet.DESK_LEFT.value(), true, blockModels);
        registerDyeableItemModel(RoyalFurnitureSet.DESK_RIGHT.value(), true, blockModels);
        registerDyeableItemModel(RoyalFurnitureSet.OVEN.value(), false, blockModels);
        registerDyeableItemModel(RoyalFurnitureSet.CHEST.value(), true, blockModels);
        registerDyeableItemModel(RoyalFurnitureSet.SHELF.value(), ShelfConnection.NONE.getModelSuffix(), blockModels);
        registerDyeableItemModel(RoyalFurnitureSet.SOFA.value(), SofaConnection.NONE.getModelSuffix(), blockModels);
        registerDyeableItemModel(RoyalFurnitureSet.COUNTER.value(), CounterConnection.NONE.getModelSuffix(), blockModels);
        registerDyeableItemModel(RoyalFurnitureSet.BENCH.value(), true, blockModels);
        registerDyeableItemModel(RoyalFurnitureSet.WARDROBE.value(), true, blockModels);
        registerDyeableItemModel(RoyalFurnitureSet.TABLE.value(), false, blockModels);
    }

    private void createDyeableModel(Block block, TexturedModel.Provider modelProvider, BlockModelGenerators blockModels) {
        var modelPath = modelProvider.create(block, blockModels.modelOutput);
        var variant = BlockModelGenerators.plainVariant(modelPath);
        var variantGenerator = BlockModelGenerators.createSimpleBlock(block, variant);

        blockModels.blockStateOutput.accept(variantGenerator);
    }

    private void registerDyeableItemModel(Block block, Identifier modelPath, BlockModelGenerators blockModels) {
        blockModels.registerSimpleTintedItemModel(block, modelPath, new DyeColorItemTintSource());
    }

    private void registerDyeableItemModel(Block block, String modelSuffix, BlockModelGenerators blockModels) {
        registerDyeableItemModel(block, ModelLocationUtils.getModelLocation(block, modelSuffix), blockModels);
    }

    private void registerDyeableItemModel(Block block, boolean asItem, BlockModelGenerators blockModels) {
        var modelPath = asItem ? ModelLocationUtils.getModelLocation(block.asItem()) : ModelLocationUtils.getModelLocation(block);
        registerDyeableItemModel(block, modelPath, blockModels);
    }

    private void registerDyeableFlatItemModel(Block block, BlockModelGenerators blockModels) {
        var item = block.asItem();
        var modelPath = ModelTemplates.TWO_LAYERED_ITEM.create(
                ModelLocationUtils.getModelLocation(item),
                TextureMapping.layered(TextureMapping.getItemTexture(item, "_tint"), TextureMapping.getItemTexture(item)),
                blockModels.modelOutput
        );

        registerDyeableItemModel(block, modelPath, blockModels);
    }

    private void registerBlockTags(ProviderListenerContext context, IntrusiveTagProvider<Block> provider) {
        var dyedTag = provider.tag(Tags.Blocks.DYED);
        Dyeable.dyeableBlocks(RoyalFurnitureSet.REGISTREE).forEach(dyedTag::withElement);
    }

    private void registerItemTags(ProviderListenerContext context, IntrusiveTagProvider<Item> provider) {
        var dyedTag = provider.tag(Tags.Items.DYED);
        Dyeable.dyeableItems(RoyalFurnitureSet.REGISTREE).forEach(dyedTag::withElement);
    }
}
