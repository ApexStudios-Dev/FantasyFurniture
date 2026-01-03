package dev.apexstudios.fantasyfurniture.royal.client;

import dev.apexstudios.apexcore.api.block.Dyeable;
import dev.apexstudios.fantasyfurniture.royal.common.RoyalFurnitureSet;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
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

        modBus.addListener(RegisterColorHandlersEvent.Block.class, event -> Dyeable.registerBlockColor(RoyalFurnitureSet.REGISTREE, event));
    }
}
