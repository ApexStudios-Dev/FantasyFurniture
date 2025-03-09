package dev.apexstudios.fantasyfurniture.royal;

import dev.apexstudios.apexcore.lib.component.block.BlockComponentHelper;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.fantasyfurniture.set.BlockTypes;
import net.minecraft.util.CommonColors;
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

        modBus.addListener(RegisterColorHandlersEvent.ItemTintSources.class, event -> {

        });
    }
}
