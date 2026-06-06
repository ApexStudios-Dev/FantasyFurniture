package dev.apexstudios.fantasyfurniture.dunmer.data;

import dev.apexstudios.apexcore.api.data.ProviderTypes;
import dev.apexstudios.apexcore.api.data.ResourceGenerator;
import dev.apexstudios.apexcore.api.data.provider.context.ProviderListenerContext;
import dev.apexstudios.apexcore.api.data.provider.model.ModelProvider;
import dev.apexstudios.apexcore.api.placement.BlockItemPlacementEvent;
import dev.apexstudios.apexcore.api.util.TagPair;
import dev.apexstudios.fantasyfurniture.common.block.OvenBlock;
import dev.apexstudios.fantasyfurniture.common.ctm.CtmPacks;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureClientDataUtil;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureDataUtil;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import dev.apexstudios.fantasyfurniture.dunmer.common.DunmerFurnitureSet;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.data.BlockFamily;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(DunmerFurnitureSet.ID)
public final class DunmerFurnitureSetDataEntryPoint {
    public DunmerFurnitureSetDataEntryPoint(IEventBus modBus) {
        ResourceGenerator.of(modBus, generator -> {
            var pack = generator.pack();
            var context = new FurnitureDataUtil.DataGenContext(
                    DunmerFurnitureSet.REGISTREE,
                    "Dunmer",
                    new BlockFamily.Builder(DunmerFurnitureSet.PLANKS.value())
                            .recipeGroupPrefix("dunmer")
                            .recipeUnlockedBy("has_" + FurnitureUtil.Names.PLANKS)
                            .stairs(DunmerFurnitureSet.STAIRS.value())
                            .slab(DunmerFurnitureSet.SLAB.value())
                            .fence(DunmerFurnitureSet.FENCE.value())
                            .fenceGate(DunmerFurnitureSet.FENCE_GATE.value())
                            .trapdoor(DunmerFurnitureSet.TRAPDOOR.value())
                            .pressurePlate(DunmerFurnitureSet.PRESSURE_PLATE.value())
                            .sign(DunmerFurnitureSet.SIGN.sign().value(), DunmerFurnitureSet.SIGN.wall().value())
                            .customHangingSign(DunmerFurnitureSet.HANGING_SIGN.sign().value(), DunmerFurnitureSet.HANGING_SIGN.wall().value())
                    .getFamily(),
                    BlockTags.MINEABLE_WITH_AXE,
                    new TagPair(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS),
                    BlockTags.WOODEN_STAIRS,
                    new TagPair(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS),
                    new TagPair(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES),
                    new TagPair(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS),
                    new TagPair(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES),
                    new TagPair(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS),
                    exclusions -> exclusions.put(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.OVEN)
            );

            FurnitureDataUtil.registerDataGen(context, pack);
            FurnitureClientDataUtil.registerDataGen(context, pack);
            CtmPacks.registerDataGen(DunmerFurnitureSet.REGISTREE, pack, false);

            pack.providing(ProviderTypes.MODELS, this::generateModels)
                    .providing(ProviderTypes.BLOCK_TAGS, (ctx, provider) -> provider
                            .tag(BlockItemPlacementEvent.RENDERABLES)
                            .withElement(DunmerFurnitureSet.OVEN)
                    );
        });
    }

    private void generateModels(ProviderListenerContext context, ModelProvider provider) {
        var blockModels = provider.blockModels();

        var block = DunmerFurnitureSet.OVEN.value();

        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
                .with(PropertyDispatch.initial(block.getMultiBlockProperty(), OvenBlock.LIT).generate((index, lit) -> {
                    var suffix = index == 0 ? "_left" : "_right";

                    if(lit)
                        suffix = "_lit" + suffix;

                    return BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(block, suffix));
                }))
                .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
        );

        FurnitureClientDataUtil.registerSimpleBlockItemModel(block, blockModels);
    }
}
