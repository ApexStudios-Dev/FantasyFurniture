package dev.apexstudios.fantasyfurniture.decorations;

import dev.apexstudios.apexcore.lib.block.Dyeable;
import dev.apexstudios.fantasyfurniture.decorations.ber.DecorLayerDefinitions;
import dev.apexstudios.fantasyfurniture.decorations.ber.SimpleBlockEntityRenderer;
import dev.apexstudios.fantasyfurniture.decorations.ber.SimpleBlockEntitySpecialRenderer;
import dev.apexstudios.fantasyfurniture.decorations.grave.GravestoneBlockEntity;
import dev.apexstudios.fantasyfurniture.decorations.grave.GravestoneBlockEntityRenderer;
import dev.apexstudios.fantasyfurniture.decorations.grave.GravestoneEditScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractSignEditScreen;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterSpecialModelRendererEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod(value = DecorationsFurnitureModule.ID, dist = Dist.CLIENT)
public final class DecorationsFurnitureModuleClientSetup {
    public DecorationsFurnitureModuleClientSetup(IEventBus modBus) {
        modBus.addListener(FMLClientSetupEvent.class, event -> event.enqueueWork(() -> DecorationsFurnitureModule.REGISTREE
                .listElements(Registries.BLOCK)
                .map(Holder::value)
                .forEach(block -> ItemBlockRenderTypes.setRenderLayer(block, ChunkSectionLayer.CUTOUT))
        ));

        modBus.addListener(RegisterColorHandlersEvent.Block.class, event -> Dyeable.registerBlockColor(DecorationsFurnitureModule.REGISTREE, event));

        modBus.addListener(EntityRenderersEvent.RegisterRenderers.class, event -> {
            event.registerBlockEntityRenderer(DecorationsFurnitureModule.GRAVESTONE_BLOCK_ENTITY.value(), GravestoneBlockEntityRenderer::new);
            event.registerBlockEntityRenderer(DecorationsFurnitureModule.WIDOW_BLOOM_BLOCK_ENTITY.value(), SimpleBlockEntityRenderer.create(DecorLayerDefinitions.WIDOW_BLOOM, blockEntity -> DecorLayerDefinitions.WIDOW_BLOOM_TEXTURE));

            event.registerBlockEntityRenderer(DecorationsFurnitureModule.SKULL_BLOSSOM_BLOCK_ENTITY.value(), SimpleBlockEntityRenderer.create(
                    DecorLayerDefinitions.SKULL_BLOSSOM,
                    blockEntity -> DecorationsFurnitureModule.SKULL_BLOSSOM_SKELETON_BLOCK.is(blockEntity.getBlockState()) ? DecorLayerDefinitions.SKULL_BLOSSOM_SKELETON_TEXTURE : DecorLayerDefinitions.SKULL_BLOSSOM_WITHER_TEXTURE
            ));
        });

        modBus.addListener(RegisterSpecialModelRendererEvent.class, event -> {
            event.register(DecorationsFurnitureModule.identifier("widow_bloom"), SimpleBlockEntitySpecialRenderer.WidowBloom.MAP_CODEC);
            event.register(DecorationsFurnitureModule.identifier("skull_blossom"), SimpleBlockEntitySpecialRenderer.SkullBlossom.MAP_CODEC);
        });

        modBus.addListener(EntityRenderersEvent.RegisterLayerDefinitions.class, event -> {
            event.registerLayerDefinition(DecorLayerDefinitions.WIDOW_BLOOM, DecorLayerDefinitions::createWidowBloom);
            event.registerLayerDefinition(DecorLayerDefinitions.SKULL_BLOSSOM, DecorLayerDefinitions::createSkullBlossom);
        });

        NeoForge.EVENT_BUS.addListener(ScreenEvent.Opening.class, event -> {
            if(event.getNewScreen() instanceof AbstractSignEditScreen screen && !(screen instanceof GravestoneEditScreen) && DecorationsFurnitureModule.GRAVESTONE_BLOCK_ENTITY.is(screen.sign.getType())) {
                event.setNewScreen(new GravestoneEditScreen(
                        (GravestoneBlockEntity) screen.sign,
                        screen.isFrontText,
                        Minecraft.getInstance().isTextFilteringEnabled()
                ));
            }
        });
    }
}
