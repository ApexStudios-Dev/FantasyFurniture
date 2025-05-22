package apexstudios.fantasyfurniture.bone;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

public interface BoneFurnitureSetClient {
    static void register(BoneFurnitureSet furnitureSet, IEventBus modBus) {
        modBus.addListener(FMLClientSetupEvent.class, event -> event.enqueueWork(() ->
                ItemBlockRenderTypes.setRenderLayer(furnitureSet.trapdoor.value(), RenderType.cutout())
        ));
    }
}
