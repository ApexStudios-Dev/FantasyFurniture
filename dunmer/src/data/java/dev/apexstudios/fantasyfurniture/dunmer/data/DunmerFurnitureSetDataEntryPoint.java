package dev.apexstudios.fantasyfurniture.dunmer.data;

import dev.apexstudios.apexcore.api.data.ProviderTypes;
import dev.apexstudios.apexcore.api.data.ResourceGenerator;
import dev.apexstudios.apexcore.api.data.provider.context.ProviderListenerContext;
import dev.apexstudios.apexcore.api.data.provider.model.ModelProvider;
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
import net.minecraft.tags.BlockTags;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;

@Mod(DunmerFurnitureSet.ID)
public final class DunmerFurnitureSetDataEntryPoint {
    public DunmerFurnitureSetDataEntryPoint(IEventBus modBus) {
        ResourceGenerator.of(modBus, generator -> {
            var pack = generator.pack();

            var context = FurnitureDataUtil.context(DunmerFurnitureSet.REGISTREE, "Dunmer", "dunmer")
                    .exclude(FurnitureDataUtil.DataType.BLOCK_TAG, FurnitureUtil.Names.OVEN)
                    .exclude(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.OVEN)
                    .build();

            context.register(pack);
            FurnitureClientDataUtil.registerDataGen(context, pack);
            CtmPacks.registerDataGen(DunmerFurnitureSet.REGISTREE, pack, false);

            pack.providing(ProviderTypes.MODELS, this::generateModels)
                    .providing(ProviderTypes.BLOCK_TAGS, (ctx, provider) -> {
                        provider.tag(BlockTags.MINEABLE_WITH_AXE).withElement(DunmerFurnitureSet.OVEN);
                        provider.tag(Tags.Blocks.PLAYER_WORKSTATIONS_FURNACES).withElement(DunmerFurnitureSet.OVEN);
                    });
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
