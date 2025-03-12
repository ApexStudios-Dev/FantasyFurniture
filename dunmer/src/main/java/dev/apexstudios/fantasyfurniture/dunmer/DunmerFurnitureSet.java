package dev.apexstudios.fantasyfurniture.dunmer;

import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.component.block.types.MultiBlockComponent;
import dev.apexstudios.apexcore.lib.data.provider.model.ModelUtil;
import dev.apexstudios.apexcore.lib.placement.BlockPlacementRenderer;
import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.fantasyfurniture.block.OvenBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerBedDoubleBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerBedSingleBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerBenchBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerBookshelfBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerChairBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerChandelierBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerChestBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerCounterBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerCushionBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerDeskBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerDrawerBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerDresserBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerFloorLightBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerLockBoxBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerOvenBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerPaintingSmallBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerPaintingWideBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerShelfBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerSofaBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerStoolBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerTableBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerWallLightBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerWardrobeBlock;
import dev.apexstudios.fantasyfurniture.set.BlockTypes;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import dev.apexstudios.fantasyfurniture.set.function.BlockFactory;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.blockstates.Variant;
import net.minecraft.client.data.models.blockstates.VariantProperties;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;

@Mod(DunmerFurnitureSet.ID)
public class DunmerFurnitureSet {
    public static final String ID = "fantasyfurniture_dunmer";
    public static final Registree REGISTREE = new Registree(ID);

    public static final FurnitureSet FURNITURE_SET = FurnitureSet.createWoodLike(REGISTREE, "dunmer", builder -> builder
            .with(BlockTypes.OVEN.copy($ -> $
                    .blockFactory(BlockFactory.wrapping(DunmerOvenBlock::new))
                    .builder($$ -> $$
                            .model(() -> (context, models, furnitureSet, block) -> {
                                models.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block)
                                        .with(PropertyDispatch.properties(block.getComponentOrThrow(BlockComponentTypes.MULTI_BLOCK).property(), OvenBlock.LIT).generate((index, lit) -> {
                                            var halfName = index == MultiBlockComponent.ORIGIN_INDEX ? "_left" : "_right";
                                            var litPrefix = lit ? "_lit" : "";
                                            return Variant.variant().with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(block, litPrefix + halfName));
                                        }))
                                        .with(ModelUtil.createHorizontalFacingDispatch(block))
                                );

                                ModelUtil.registerBlockItemModel(block, models);
                            })
                            .blockTags(BlockPlacementRenderer.BLOCK_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED)
                    )
            ))
            .with(BlockTypes.DRESSER.extend(BlockFactory.wrapping(DunmerDresserBlock::new)))
            .with(BlockTypes.STOOL.extend(BlockFactory.wrapping(DunmerStoolBlock::new)))
            .with(BlockTypes.CUSHION.extend(BlockFactory.wrapping(DunmerCushionBlock::new)))
            .with(BlockTypes.LOCKBOX.extend(BlockFactory.wrapping(DunmerLockBoxBlock::new)))
            .with(BlockTypes.DRAWER.extend(BlockFactory.wrapping(DunmerDrawerBlock::new)))
            .with(BlockTypes.CHAIR.extend(BlockFactory.wrapping(DunmerChairBlock::new)))
            .with(BlockTypes.BOOKSHELF.extend(BlockFactory.wrapping(DunmerBookshelfBlock::new)))
            .with(BlockTypes.BED_SINGLE.extend(BlockFactory.wrapping(DunmerBedSingleBlock::new)))
            .with(BlockTypes.BED_DOUBLE.extend(BlockFactory.wrapping(DunmerBedDoubleBlock::new)))
            .with(BlockTypes.DESK_LEFT.extend((furnitureSet, properties) -> new DunmerDeskBlock(properties, true)))
            .with(BlockTypes.DESK_RIGHT.extend((furnitureSet, properties) -> new DunmerDeskBlock(properties, false)))
            .with(BlockTypes.PAINTING_WIDE.extend(BlockFactory.wrapping(DunmerPaintingWideBlock::new)))
            .with(BlockTypes.PAINTING_SMALL.extend(BlockFactory.wrapping(DunmerPaintingSmallBlock::new)))
            .with(BlockTypes.CHEST.extend(BlockFactory.wrapping(DunmerChestBlock::new)))
            .with(BlockTypes.FLOOR_LIGHT.extend(BlockFactory.wrapping(DunmerFloorLightBlock::new)))
            .with(BlockTypes.CHANDELIER.extend(BlockFactory.wrapping(DunmerChandelierBlock::new)))
            .with(BlockTypes.SHELF.extend(BlockFactory.wrapping(DunmerShelfBlock::new)))
            .with(BlockTypes.SOFA.extend(BlockFactory.wrapping(DunmerSofaBlock::new)))
            .with(BlockTypes.COUNTER.extend(BlockFactory.wrapping(DunmerCounterBlock::new)))
            .with(BlockTypes.WALL_LIGHT.extend(BlockFactory.wrapping(DunmerWallLightBlock::new)))
            .with(BlockTypes.BENCH.extend(BlockFactory.wrapping(DunmerBenchBlock::new)))
            .with(BlockTypes.WARDROBE.extend(BlockFactory.wrapping(DunmerWardrobeBlock::new)))
            .with(BlockTypes.TABLE.extend(BlockFactory.wrapping(DunmerTableBlock::new)))
    );

    public DunmerFurnitureSet(IEventBus modBus) {
        FURNITURE_SET.register(modBus);
        REGISTREE.registerEvents(modBus);
    }
}
