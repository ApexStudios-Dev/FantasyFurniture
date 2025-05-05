package dev.apexstudios.fantasyfurniture.royal;

import dev.apexstudios.apexcore.core.client.DyeColorItemTintSource;
import dev.apexstudios.apexcore.lib.component.ComponentHolder;
import dev.apexstudios.apexcore.lib.component.block.BlockComponent;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentHelper;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.data.provider.context.ProviderListenerContext;
import dev.apexstudios.apexcore.lib.data.provider.model.ApexModelTemplates;
import dev.apexstudios.apexcore.lib.data.provider.model.ModelUtil;
import dev.apexstudios.fantasyfurniture.block.BedDoubleBlock;
import dev.apexstudios.fantasyfurniture.block.BedSingleBlock;
import dev.apexstudios.fantasyfurniture.block.BenchBlock;
import dev.apexstudios.fantasyfurniture.block.BookshelfBlock;
import dev.apexstudios.fantasyfurniture.block.ChairBlock;
import dev.apexstudios.fantasyfurniture.block.ChestBlock;
import dev.apexstudios.fantasyfurniture.block.CounterBlock;
import dev.apexstudios.fantasyfurniture.block.CushionBlock;
import dev.apexstudios.fantasyfurniture.block.DeskBlock;
import dev.apexstudios.fantasyfurniture.block.DrawerBlock;
import dev.apexstudios.fantasyfurniture.block.DresserBlock;
import dev.apexstudios.fantasyfurniture.block.LockBoxBlock;
import dev.apexstudios.fantasyfurniture.block.OvenBlock;
import dev.apexstudios.fantasyfurniture.block.ShelfBlock;
import dev.apexstudios.fantasyfurniture.block.SofaBlock;
import dev.apexstudios.fantasyfurniture.block.TableBlock;
import dev.apexstudios.fantasyfurniture.block.WardrobeBlock;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureDoorBlockComponentHolder;
import dev.apexstudios.fantasyfurniture.block.base.SeatBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalCarpetBlock;
import dev.apexstudios.fantasyfurniture.set.BlockTypes;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.CommonColors;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@Mod(value = RoyalFurnitureSet.ID, dist = Dist.CLIENT)
public final class RoyalFurnitureSetClientSetup {
    public RoyalFurnitureSetClientSetup(IEventBus modBus) {
        modBus.addListener(RegisterColorHandlersEvent.Block.class, event -> {
            var dyeableBlocks = RoyalFurnitureSet.FURNITURE_SET.blockTypes().stream()
                    .map(RoyalFurnitureSet.FURNITURE_SET::getOrThrow)
                    .filter(block -> block instanceof ComponentHolder && ((ComponentHolder<BlockComponent, Block>) block).hasComponent(BlockComponentTypes.DYEABLE))
                    .toArray(Block[]::new);

            event.register((blockState, level, pos, tintIndex) -> {
                if(tintIndex == 0) {
                    var component = BlockComponentHelper.getComponent(blockState, BlockComponentTypes.DYEABLE);
                    return component == null ? CommonColors.WHITE : component.get(blockState).getTextureDiffuseColor();
                }

                return CommonColors.WHITE;
            }, dyeableBlocks);

            event.register(
                    (blockState, level, pos, tintIndex) -> tintIndex == 0 ? blockState.getValue(RoyalCarpetBlock.COLOR).getTextureDiffuseColor() : CommonColors.WHITE,
                    RoyalFurnitureSet.FURNITURE_SET.getOrThrow(BlockTypes.CARPET)
            );
        });
    }

    static void woolModel(ProviderListenerContext context, BlockModelGenerators models, FurnitureSet furnitureSet, Block block) {
        models.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, BlockModelGenerators.plainVariant(ApexModelTemplates.Textured.CUBE_ALL_TINTED
                .updateTemplate(template -> template.extend()
                        .renderType("cutout")
                        .build()
                )
                .create(block, models.modelOutput)
        )));

        dyeableItemModel(block, ModelLocationUtils.getModelLocation(block), models);
    }

    static void carpetModel(ProviderListenerContext context, BlockModelGenerators models, FurnitureSet furnitureSet, CarpetBlock block) {
        var wool = furnitureSet.getOrThrow(BlockTypes.WOOL);

        models.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, BlockModelGenerators.plainVariant(ApexModelTemplates.Textured.CARPET_TINTED
                .updateTexture(mapping -> mapping
                        .put(TextureSlot.WOOL, TextureMapping.getBlockTexture(wool))
                        .put(ApexModelTemplates.SLOT_WOOL_TINTED, TextureMapping.getBlockTexture(wool).withSuffix("_tint"))
                )
                .updateTemplate(template -> template.extend()
                        .renderType("cutout")
                        .build()
                )
                .create(block, models.modelOutput)
        )));

        dyeableItemModel(block, ModelLocationUtils.getModelLocation(block), models);
    }

    static void bedDoubleModel(ProviderListenerContext context, BlockModelGenerators models, FurnitureSet furnitureSet, BedDoubleBlock block) {
        BlockTypes.bedDoubleModel(block, models);
        dyeableItemModel(block, ModelLocationUtils.getModelLocation(block.asItem()), models);
    }

    static void bedSingleModel(ProviderListenerContext context, BlockModelGenerators models, FurnitureSet furnitureSet, BedSingleBlock block) {
        BlockTypes.bedSingleModel(block, models);
        dyeableItemModel(block, ModelLocationUtils.getModelLocation(block.asItem()), models);
    }

    static void benchModel(ProviderListenerContext context, BlockModelGenerators models, FurnitureSet furnitureSet, BenchBlock block) {
        BlockTypes.benchModel(block, models);
        dyeableItemModel(block, ModelLocationUtils.getModelLocation(block.asItem()), models);
    }

    static void bookshelfModel(ProviderListenerContext context, BlockModelGenerators models, FurnitureSet furnitureSet, BookshelfBlock block) {
        BlockTypes.bookshelfModel(block, models);
        dyeableItemModel(block, ModelLocationUtils.getModelLocation(block.asItem()), models);
    }

    static void chairModel(ProviderListenerContext context, BlockModelGenerators models, FurnitureSet furnitureSet, ChairBlock block) {
        BlockTypes.chairModel(block, models);
        dyeableItemModel(block, ModelLocationUtils.getModelLocation(block.asItem()), models);
    }

    static void chestModel(ProviderListenerContext context, BlockModelGenerators models, FurnitureSet furnitureSet, ChestBlock block) {
        BlockTypes.chestModel(block, models);
        dyeableItemModel(block, ModelLocationUtils.getModelLocation(block.asItem()), models);
    }

    static void counterModel(ProviderListenerContext context, BlockModelGenerators models, FurnitureSet furnitureSet, CounterBlock block) {
        BlockTypes.counterModel(block, models);
        dyeableItemModel(block, ModelLocationUtils.getModelLocation(block, "_single"), models);
    }

    static void cushionModel(ProviderListenerContext context, BlockModelGenerators models, FurnitureSet furnitureSet, CushionBlock block) {
        ModelUtil.horizontalFacingBlock(block, models);
        dyeableItemModel(block, ModelLocationUtils.getModelLocation(block), models);
    }

    static void deskModel(ProviderListenerContext context, BlockModelGenerators models, FurnitureSet furnitureSet, DeskBlock block) {
        BlockTypes.deskModel(block, models);
        dyeableItemModel(block, ModelLocationUtils.getModelLocation(block.asItem()), models);
    }

    static void doorModel(ProviderListenerContext context, BlockModelGenerators models, FurnitureSet furnitureSet, FurnitureDoorBlockComponentHolder block) {
        BlockTypes.doorModel(block, models);
        dyeableItemModel(block, models.createFlatItemModel(block.asItem()), models);
    }

    static void drawerModel(ProviderListenerContext context, BlockModelGenerators models, FurnitureSet furnitureSet, DrawerBlock block) {
        models.blockStateOutput.accept(ModelUtil.facingBlock(block));
        dyeableItemModel(block, ModelLocationUtils.getModelLocation(block), models);
    }

    static void dresserModel(ProviderListenerContext context, BlockModelGenerators models, FurnitureSet furnitureSet, DresserBlock block) {
        BlockTypes.dresserModel(block, models);
        dyeableItemModel(block, ModelLocationUtils.getModelLocation(block.asItem()), models);
    }

    static void lockboxModel(ProviderListenerContext context, BlockModelGenerators models, FurnitureSet furnitureSet, LockBoxBlock block) {
        models.blockStateOutput.accept(ModelUtil.facingBlock(block));
        dyeableItemModel(block, ModelLocationUtils.getModelLocation(block), models);
    }

    static void ovenModel(ProviderListenerContext context, BlockModelGenerators models, FurnitureSet furnitureSet, OvenBlock block) {
        models.blockStateOutput.accept(ModelUtil.facingBlock(block));
        dyeableItemModel(block, ModelLocationUtils.getModelLocation(block), models);
    }

    static void shelfModel(ProviderListenerContext context, BlockModelGenerators models, FurnitureSet furnitureSet, ShelfBlock block) {
        BlockTypes.shelfModel(block, models);
        dyeableItemModel(block, ModelLocationUtils.getModelLocation(block, "_single"), models);
    }

    static void sofaModel(ProviderListenerContext context, BlockModelGenerators models, FurnitureSet furnitureSet, SofaBlock block) {
        BlockTypes.sofaModel(block, models);
        dyeableItemModel(block, ModelLocationUtils.getModelLocation(block, "_single"), models);
    }

    static void stoolModel(ProviderListenerContext context, BlockModelGenerators models, FurnitureSet furnitureSet, SeatBlock block) {
        ModelUtil.horizontalFacingBlock(block, models);
        dyeableItemModel(block, ModelLocationUtils.getModelLocation(block), models);
    }

    static void tableModel(ProviderListenerContext context, BlockModelGenerators models, FurnitureSet furnitureSet, TableBlock block) {
        BlockTypes.tableModel(block, models);
        dyeableItemModel(block, ModelLocationUtils.getModelLocation(block), models);
    }

    static void wardrobeModel(ProviderListenerContext context, BlockModelGenerators models, FurnitureSet furnitureSet, WardrobeBlock block) {
        BlockTypes.wardrobeModel(block, models);
        dyeableItemModel(block, ModelLocationUtils.getModelLocation(block.asItem()), models);
    }

    private static void dyeableItemModel(Block block, ResourceLocation modelPath, BlockModelGenerators models) {
        models.registerSimpleTintedItemModel(block, modelPath, new DyeColorItemTintSource(DyeColor.WHITE));
    }
}
