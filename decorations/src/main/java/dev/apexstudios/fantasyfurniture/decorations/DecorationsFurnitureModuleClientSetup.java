package dev.apexstudios.fantasyfurniture.decorations;

import dev.apexstudios.apexcore.lib.block.Dyeable;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@Mod(value = DecorationsFurnitureModule.ID, dist = Dist.CLIENT)
public final class DecorationsFurnitureModuleClientSetup {
    public DecorationsFurnitureModuleClientSetup(IEventBus modBus) {
        modBus.addListener(FMLClientSetupEvent.class, event -> event.enqueueWork(() -> DecorationsFurnitureModule.REGISTREE
                .listElements(Registries.BLOCK)
                .map(Holder::value)
                .forEach(block -> ItemBlockRenderTypes.setRenderLayer(block, ChunkSectionLayer.CUTOUT))
        ));

        modBus.addListener(RegisterColorHandlersEvent.Block.class, event -> Dyeable.registerBlockColor(DecorationsFurnitureModule.REGISTREE, event));
    }
}
