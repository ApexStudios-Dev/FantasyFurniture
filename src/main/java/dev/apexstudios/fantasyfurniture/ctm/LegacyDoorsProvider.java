package dev.apexstudios.fantasyfurniture.ctm;

import dev.apexstudios.apexcore.core.client.DyeColorItemTintSource;
import dev.apexstudios.apexcore.lib.block.Dyeable;
import dev.apexstudios.apexcore.lib.data.provider.model.ModelProvider;
import dev.apexstudios.fantasyfurniture.util.FurnitureUtil;
import dev.apexstudios.registree.api.Registree;
import java.util.stream.Stream;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public interface LegacyDoorsProvider {
    static void register(ModelProvider provider, Registree registree) {
        var dyeable = SimpleJsonProvider.isDyeable(registree);
        var blockModels = provider.blockModels();
        var blocks = FurnitureUtil.Names.blocks(registree, FurnitureUtil.Names.DOOR_SINGLE, FurnitureUtil.Names.DOOR_DOUBLE);

        provider.knownBlocks(() -> Stream.of(blocks).map(Block::builtInRegistryHolder))
                .knownItems(() -> Stream.of(blocks).map(Block::asItem).map(Item::builtInRegistryHolder));

        for(var block : blocks) {
            var model = ModelLocationUtils.getModelLocation(block.asItem(), "_3d");

            if(dyeable)
                blockModels.registerSimpleTintedItemModel(block, model, new DyeColorItemTintSource(Dyeable.DEFAULT_COLOR));
            else
                blockModels.registerSimpleItemModel(block, model);
        }
    }
}
