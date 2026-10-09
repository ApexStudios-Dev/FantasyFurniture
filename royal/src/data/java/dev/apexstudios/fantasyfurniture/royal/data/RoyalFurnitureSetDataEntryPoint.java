package dev.apexstudios.fantasyfurniture.royal.data;

import dev.apexstudios.apexcore.client.DyeColorItemTintSource;
import dev.apexstudios.apexcore.common.ApexCore;
import dev.apexstudios.fantasyfurniture.common.block.property.CounterConnection;
import dev.apexstudios.fantasyfurniture.common.block.property.ShelfConnection;
import dev.apexstudios.fantasyfurniture.common.block.property.SofaConnection;
import dev.apexstudios.fantasyfurniture.common.data.DataGenContext;
import dev.apexstudios.fantasyfurniture.common.data.DataGenType;
import dev.apexstudios.fantasyfurniture.common.data.FurnitureModels;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import dev.apexstudios.fantasyfurniture.royal.common.RoyalFurnitureSet;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(RoyalFurnitureSet.ID)
public final class RoyalFurnitureSetDataEntryPoint {
    public RoyalFurnitureSetDataEntryPoint(IEventBus modBus) {
        DataGenContext.stone(RoyalFurnitureSet.REGISTREE, "royal")
                .extraModels(this::models)
                .exclude(DataGenType.MODEL, FurnitureUtil.Names.WOOL)
                .exclude(DataGenType.MODEL, FurnitureUtil.Names.CARPET)
                .exclude(DataGenType.MODEL, FurnitureUtil.Names.DRESSER)
                .exclude(DataGenType.MODEL, FurnitureUtil.Names.CHAIR)
                .exclude(DataGenType.MODEL, FurnitureUtil.Names.BOOKSHELF)
                .exclude(DataGenType.MODEL, FurnitureUtil.Names.BED_SINGLE)
                .exclude(DataGenType.MODEL, FurnitureUtil.Names.BED_DOUBLE)
                .exclude(DataGenType.MODEL, FurnitureUtil.Names.DOOR_SINGLE)
                .exclude(DataGenType.MODEL, FurnitureUtil.Names.DOOR_DOUBLE)
                .exclude(DataGenType.MODEL, FurnitureUtil.Names.DESK_LEFT)
                .exclude(DataGenType.MODEL, FurnitureUtil.Names.DESK_RIGHT)
                .exclude(DataGenType.MODEL, FurnitureUtil.Names.CHEST)
                .exclude(DataGenType.MODEL, FurnitureUtil.Names.SHELF)
                .exclude(DataGenType.MODEL, FurnitureUtil.Names.SOFA)
                .exclude(DataGenType.MODEL, FurnitureUtil.Names.COUNTER)
                .exclude(DataGenType.MODEL, FurnitureUtil.Names.BENCH)
                .exclude(DataGenType.MODEL, FurnitureUtil.Names.WARDROBE)
                .build(modBus);
    }

    private void models(BlockModelGenerators blockModels, ItemModelGenerators itemModels, DataGenContext context) {
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

        FurnitureModels.createLeftRightModel(RoyalFurnitureSet.DRESSER.value(), blockModels);
        FurnitureModels.createBottomTopModel(RoyalFurnitureSet.CHAIR.value(), blockModels);
        FurnitureModels.createBookshelfModel(RoyalFurnitureSet.BOOKSHELF.value(), blockModels);
        FurnitureModels.createBedSingleModel(RoyalFurnitureSet.BED_SINGLE.value(), blockModels);
        FurnitureModels.createBedDoubleModel(RoyalFurnitureSet.BED_DOUBLE.value(), blockModels);
        FurnitureModels.createDoorModel(RoyalFurnitureSet.DOOR_SINGLE.value(), blockModels);
        FurnitureModels.createDoorModel(RoyalFurnitureSet.DOOR_DOUBLE.value(), blockModels);
        FurnitureModels.createLeftRightModel(RoyalFurnitureSet.DESK_LEFT.value(), blockModels);
        FurnitureModels.createLeftRightModel(RoyalFurnitureSet.DESK_RIGHT.value(), blockModels);
        FurnitureModels.createLeftRightModel(RoyalFurnitureSet.CHEST.value(), blockModels);
        FurnitureModels.createShelfModel(RoyalFurnitureSet.SHELF.value(), blockModels);
        FurnitureModels.createSofaModel(RoyalFurnitureSet.SOFA.value(), blockModels);
        FurnitureModels.createCounterModel(RoyalFurnitureSet.COUNTER.value(), blockModels);
        FurnitureModels.createLeftRightModel(RoyalFurnitureSet.BENCH.value(), blockModels);
        FurnitureModels.createWardrobeModel(RoyalFurnitureSet.WARDROBE.value(), blockModels);

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
