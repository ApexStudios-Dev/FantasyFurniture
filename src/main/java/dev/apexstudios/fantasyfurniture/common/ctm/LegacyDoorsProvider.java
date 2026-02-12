package dev.apexstudios.fantasyfurniture.common.ctm;

import dev.apexstudios.apexcore.api.data.provider.model.ModelProvider;
import dev.apexstudios.apexcore.client.DyeColorItemTintSource;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import dev.apexstudios.registree.registrar.BlockRegistrar;
import java.util.stream.Stream;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public interface LegacyDoorsProvider {
    static void register(ModelProvider provider, BlockRegistrar blocks) {
        var dyeable = SimpleJsonProvider.isDyeable(blocks);
        var blockModels = provider.blockModels();
        var doors = FurnitureUtil.Names.blocks(blocks, FurnitureUtil.Names.DOOR_SINGLE, FurnitureUtil.Names.DOOR_DOUBLE);

        provider.knownBlocks(() -> Stream.of(doors).map(Block::builtInRegistryHolder))
                .knownItems(() -> Stream.of(doors).map(Block::asItem).map(Item::builtInRegistryHolder));

        for(var block : doors) {
            var model = ModelLocationUtils.getModelLocation(block.asItem(), "_3d");

            if(dyeable) {
                blockModels.registerSimpleTintedItemModel(block, model, new DyeColorItemTintSource());
            } else {
                blockModels.registerSimpleItemModel(block, model);
            }
        }
    }
}
