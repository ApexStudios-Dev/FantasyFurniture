package dev.apexstudios.fantasyfurniture.royal;

import dev.apexstudios.apexcore.core.client.DyeColorItemTintSource;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentHelper;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.data.provider.context.ProviderListenerContext;
import dev.apexstudios.apexcore.lib.data.provider.model.ApexModelTemplates;
import dev.apexstudios.fantasyfurniture.set.BlockTypes;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.util.CommonColors;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@Mod(value = RoyalFurnitureSet.ID, dist = Dist.CLIENT)
public final class RoyalFurnitureSetClientSetup {
    public RoyalFurnitureSetClientSetup(IEventBus modBus) {
        modBus.addListener(RegisterColorHandlersEvent.Block.class, event -> {
            event.register((blockState, level, pos, tintIndex) -> {
                if(tintIndex == 0) {
                    var component = BlockComponentHelper.getComponent(blockState, BlockComponentTypes.DYEABLE);
                    return component == null ? CommonColors.WHITE : component.get(blockState).getTextureDiffuseColor();
                }

                return CommonColors.WHITE;
            }, RoyalFurnitureSet.FURNITURE_SET.getOrThrow(BlockTypes.WOOL));
        });
    }

    static void woolModel(ProviderListenerContext context, BlockModelGenerators models, FurnitureSet furnitureSet, Block block) {
        var model = ApexModelTemplates.Textured.CUBE_ALL_TINTED.create(block, models.modelOutput);
        models.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, model));
        models.registerSimpleTintedItemModel(block, model, new DyeColorItemTintSource(DyeColor.WHITE));
    }
}
