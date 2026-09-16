package dev.apexstudios.fantasyfurniture.data;

import dev.apexstudios.apexcore.api.block.Dyeable;
import dev.apexstudios.apexcore.client.DyeColorItemTintSource;
import dev.apexstudios.fantasyfurniture.common.FantasyFurniture;
import java.util.stream.Stream;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;

final class LegacyDoorsProvider extends ModelProvider {
    LegacyDoorsProvider(PackOutput output) {
        super(output, FantasyFurniture.ID);
    }

    private boolean isFurnitureDoor(Block block) {
        var modId = block.builtInRegistryHolder().key().identifier().getNamespace();

        if(!FantasyFurniture.FURNITURE_MODS.contains(modId)) {
            return false;
        }

        return block instanceof DoorBlock;
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        getKnownBlocks().map(Holder::value).forEach(block -> {
            var model = ModelLocationUtils.getModelLocation(block.asItem(), "_3d");

            if(block instanceof Dyeable) {
                blockModels.registerSimpleTintedItemModel(block, model, new DyeColorItemTintSource());
            } else {
                blockModels.registerSimpleItemModel(block, model);
            }
        });
    }

    @Override
    protected Stream<? extends Holder<Block>> getKnownBlocks() {
        return BuiltInRegistries.BLOCK
                .filterElements(this::isFurnitureDoor)
                .listElements();
    }

    @Override
    protected Stream<? extends Holder<Item>> getKnownItems() {
        return getKnownBlocks()
                .map(Holder::value)
                .map(Block::asItem)
                .map(Item::builtInRegistryHolder);
    }
}
