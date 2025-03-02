package dev.apexstudios.fantasyfurniture.dunmer;

import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.component.block.types.MultiBlockComponent;
import dev.apexstudios.apexcore.lib.data.provider.model.ModelUtil;
import dev.apexstudios.apexcore.lib.placement.BlockPlacementRenderer;
import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.fantasyfurniture.block.OvenBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerOvenBlock;
import dev.apexstudios.fantasyfurniture.set.BlockTypes;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import dev.apexstudios.fantasyfurniture.set.function.BlockFactory;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.blockstates.Variant;
import net.minecraft.client.data.models.blockstates.VariantProperties;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.tags.BlockTags;
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
                            .blockTags((provider, furnitureSet, block) -> {
                                provider.tag(BlockPlacementRenderer.BLOCK_WHITELIST).withElement(block);
                                provider.tag(Tags.Blocks.RELOCATION_NOT_SUPPORTED).withElement(block);
                                provider.tag(BlockTags.MINEABLE_WITH_AXE).withElement(block);
                                provider.tag(Tags.Blocks.PLAYER_WORKSTATIONS_FURNACES).withElement(block);
                            })
                    )
            ))
    );

    public DunmerFurnitureSet(IEventBus modBus) {
        FURNITURE_SET.register(modBus);
        REGISTREE.registerEvents(modBus);
    }
}
