package dev.apexstudios.fantasyfurniture.royal;

import dev.apexstudios.apexcore.lib.block.Dyeable;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.core.Holder;
import net.minecraft.util.CommonColors;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@Mod(value = RoyalFurnitureSet.ID, dist = Dist.CLIENT)
public final class RoyalFurnitureSetClientSetup {
    public RoyalFurnitureSetClientSetup(IEventBus modBus) {
        modBus.addListener(FMLClientSetupEvent.class, event -> event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(RoyalFurnitureSet.WOOL.value(), ChunkSectionLayer.CUTOUT);
            ItemBlockRenderTypes.setRenderLayer(RoyalFurnitureSet.CARPET.value(), ChunkSectionLayer.CUTOUT);
            ItemBlockRenderTypes.setRenderLayer(RoyalFurnitureSet.TRAPDOOR.value(), ChunkSectionLayer.CUTOUT);
        }));

        modBus.addListener(RegisterColorHandlersEvent.Block.class, event -> event.register(
                (blockState, level, pos, tintIndex) -> tintIndex == 0 ? Dyeable.getColor(blockState).getTextureDiffuseColor() : CommonColors.WHITE,
                RoyalFurnitureSet.DYEABLE_BLOCKS.stream().map(Holder::value).toArray(Block[]::new)
        ));
    }
}
