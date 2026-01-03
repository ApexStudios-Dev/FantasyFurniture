package dev.apexstudios.fantasyfurniture.nordic.client;

import dev.apexstudios.fantasyfurniture.nordic.common.NordicFurnitureSet;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@Mod(value = NordicFurnitureSet.ID, dist = Dist.CLIENT)
public final class NordicFurnitureSetClientEntryPoint {
    public NordicFurnitureSetClientEntryPoint(IEventBus modBus) {
        modBus.addListener(FMLClientSetupEvent.class, event -> event.enqueueWork(() ->
                ItemBlockRenderTypes.setRenderLayer(NordicFurnitureSet.TRAPDOOR.value(), ChunkSectionLayer.CUTOUT)
        ));
    }
}
