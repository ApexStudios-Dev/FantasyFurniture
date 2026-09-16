package dev.apexstudios.fantasyfurniture.royal.data;

import dev.apexstudios.apexcore.client.DyeColorItemTintSource;
import dev.apexstudios.apexcore.common.ApexCore;
import dev.apexstudios.fantasyfurniture.common.block.property.CounterConnection;
import dev.apexstudios.fantasyfurniture.common.block.property.ShelfConnection;
import dev.apexstudios.fantasyfurniture.common.block.property.SofaConnection;
import dev.apexstudios.fantasyfurniture.common.data.DataGenContext;
import dev.apexstudios.fantasyfurniture.common.data.FurnitureModelProvider;
import dev.apexstudios.fantasyfurniture.royal.common.RoyalFurnitureSet;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;

final class RFModelProvider extends FurnitureModelProvider {
    RFModelProvider(PackOutput output, DataGenContext furniture) {
        super(output, furniture);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        super.registerModels(blockModels, itemModels);

        var slotAllTinted = TextureSlot.create("all_tinted", TextureSlot.ALL);
        var slotWoolTinted = TextureSlot.create("wool_tinted", TextureSlot.WOOL);

        var templateCubeAllTinted = ModelTemplates.create(ApexCore.id("cube_all_tinted"), TextureSlot.ALL, slotAllTinted);
        var templateCarpetTinted = ModelTemplates.create(ApexCore.id("carpet_tinted"), TextureSlot.WOOL, slotWoolTinted);

        var cubeAllTinted = TexturedModel.createDefault(
                block -> new TextureMapping()
                        .put(TextureSlot.ALL, TextureMapping.getBlockTexture(block))
                        .put(slotAllTinted, TextureMapping.getBlockTexture(block, "_tint")),
                templateCubeAllTinted
        );

        var carpetTinted = TexturedModel.createDefault(
                block -> new TextureMapping()
                        .put(TextureSlot.WOOL, TextureMapping.getBlockTexture(block))
                        .put(slotWoolTinted, TextureMapping.getBlockTexture(block, "_tint")),
                templateCarpetTinted
        );

        var wool = RoyalFurnitureSet.WOOL.value();

        createDyeableModel(wool, cubeAllTinted, blockModels);

        createDyeableModel(RoyalFurnitureSet.CARPET.value(), carpetTinted.updateTexture(textures -> textures
                .put(TextureSlot.WOOL, TextureMapping.getBlockTexture(wool))
                .put(slotWoolTinted, TextureMapping.getBlockTexture(wool, "_tint"))
        ), blockModels);

        createLeftRightModel(RoyalFurnitureSet.DRESSER.value(), blockModels);
        createBottomTopModel(RoyalFurnitureSet.CHAIR.value(), blockModels);
        createBookshelfModel(RoyalFurnitureSet.BOOKSHELF.value(), blockModels);
        createBedSingleModel(RoyalFurnitureSet.BED_SINGLE.value(), blockModels);
        createBedDoubleModel(RoyalFurnitureSet.BED_DOUBLE.value(), blockModels);
        createDoorModel(RoyalFurnitureSet.DOOR_SINGLE.value(), blockModels);
        createDoorModel(RoyalFurnitureSet.DOOR_DOUBLE.value(), blockModels);
        createLeftRightModel(RoyalFurnitureSet.DESK_LEFT.value(), blockModels);
        createLeftRightModel(RoyalFurnitureSet.DESK_RIGHT.value(), blockModels);
        createLeftRightModel(RoyalFurnitureSet.CHEST.value(), blockModels);
        createShelfModel(RoyalFurnitureSet.SHELF.value(), blockModels);
        createSofaModel(RoyalFurnitureSet.SOFA.value(), blockModels);
        createCounterModel(RoyalFurnitureSet.COUNTER.value(), blockModels);
        createLeftRightModel(RoyalFurnitureSet.BENCH.value(), blockModels);
        createWardrobeModel(RoyalFurnitureSet.WARDROBE.value(), blockModels);

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
}
