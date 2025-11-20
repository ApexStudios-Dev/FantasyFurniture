package dev.apexstudios.fantasyfurniture.decorations;

import dev.apexstudios.apexcore.lib.block.Dyeable;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.util.CommonColors;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@Mod(value = DecorationsFurnitureModule.ID, dist = Dist.CLIENT)
public final class DecorationsFurnitureModuleClientSetup {
    public DecorationsFurnitureModuleClientSetup(IEventBus modBus) {
        modBus.addListener(FMLClientSetupEvent.class, event -> event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(DecorationsFurnitureModule.COOKIE_JAR_BLOCK.value(), ChunkSectionLayer.CUTOUT);
            ItemBlockRenderTypes.setRenderLayer(DecorationsFurnitureModule.BREWING_CAULDRON.value(), ChunkSectionLayer.CUTOUT);
        }));

        modBus.addListener(RegisterColorHandlersEvent.Block.class, event -> event.register(
                (blockState, level, pos, tintIndex) -> tintIndex == 1 ? Dyeable.getColor(blockState).getTextureDiffuseColor() : CommonColors.WHITE,
                DecorationsFurnitureModule.dyeables(FeatureFlags.DEFAULT_FLAGS).toArray(Block[]::new)
        ));
    }
}
