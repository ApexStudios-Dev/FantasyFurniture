package dev.apexstudios.fantasyfurniture.dunmer.data;

import dev.apexstudios.fantasyfurniture.common.block.OvenBlock;
import dev.apexstudios.fantasyfurniture.common.data.DataGenContext;
import dev.apexstudios.fantasyfurniture.common.data.FurnitureModelProvider;
import dev.apexstudios.fantasyfurniture.dunmer.common.DunmerFurnitureSet;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.data.PackOutput;

final class DFModelProvider extends FurnitureModelProvider {
    DFModelProvider(PackOutput output, DataGenContext furniture) {
        super(output, furniture);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        super.registerModels(blockModels, itemModels);

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

        registerSimpleBlockItemModel(block, blockModels);
    }
}
