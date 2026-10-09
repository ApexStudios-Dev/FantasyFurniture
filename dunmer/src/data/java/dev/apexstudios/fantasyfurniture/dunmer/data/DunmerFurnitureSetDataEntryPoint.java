package dev.apexstudios.fantasyfurniture.dunmer.data;

import dev.apexstudios.fantasyfurniture.common.block.OvenBlock;
import dev.apexstudios.fantasyfurniture.common.data.DataGenContext;
import dev.apexstudios.fantasyfurniture.common.data.DataGenType;
import dev.apexstudios.fantasyfurniture.common.data.FurnitureModels;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import dev.apexstudios.fantasyfurniture.dunmer.common.DunmerFurnitureSet;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(DunmerFurnitureSet.ID)
public final class DunmerFurnitureSetDataEntryPoint {
    public DunmerFurnitureSetDataEntryPoint(IEventBus modBus) {
        DataGenContext.wooden(DunmerFurnitureSet.REGISTREE, "dunmer")
                .exclude(DataGenType.MODEL, FurnitureUtil.Names.OVEN)
                .extraModels((blockModels, itemModels, context) -> {
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

                    FurnitureModels.registerSimpleBlockItemModel(block, blockModels);
                })
                .build(modBus);
    }
}
