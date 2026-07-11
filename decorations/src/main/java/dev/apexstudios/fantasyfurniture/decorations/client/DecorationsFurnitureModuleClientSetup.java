package dev.apexstudios.fantasyfurniture.decorations.client;

import dev.apexstudios.apexcore.api.block.Dyeable;
import dev.apexstudios.fantasyfurniture.decorations.common.DecorationsFurnitureModule;
import dev.apexstudios.fantasyfurniture.decorations.common.ber.DecorLayerDefinitions;
import dev.apexstudios.fantasyfurniture.decorations.common.ber.SimpleBlockEntityRenderer;
import dev.apexstudios.fantasyfurniture.decorations.common.ber.SimpleBlockEntitySpecialRenderer;
import dev.apexstudios.fantasyfurniture.decorations.common.grave.GravestoneBlockEntity;
import dev.apexstudios.fantasyfurniture.decorations.common.grave.GravestoneBlockEntityRenderer;
import dev.apexstudios.fantasyfurniture.decorations.common.grave.GravestoneEditScreen;
import dev.apexstudios.fantasyfurniture.decorations.common.plushie.PlushieBlockEntityRenderer;
import dev.apexstudios.fantasyfurniture.decorations.common.plushie.PlushieSpecialModelRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractSignEditScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterSpecialModelRendererEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod(value = DecorationsFurnitureModule.ID, dist = Dist.CLIENT)
public final class DecorationsFurnitureModuleClientSetup {
    public DecorationsFurnitureModuleClientSetup(IEventBus modBus) {
        modBus.addListener(RegisterColorHandlersEvent.BlockTintSources.class, event -> Dyeable.registerBlockColor(DecorationsFurnitureModule.REGISTREE, event));

        modBus.addListener(EntityRenderersEvent.RegisterRenderers.class, event -> {
            event.registerBlockEntityRenderer(DecorationsFurnitureModule.GRAVESTONE_BLOCK_ENTITY.value(), GravestoneBlockEntityRenderer::new);
            event.registerBlockEntityRenderer(DecorationsFurnitureModule.WIDOW_BLOOM_BLOCK_ENTITY.value(), SimpleBlockEntityRenderer.create(DecorLayerDefinitions.WIDOW_BLOOM, blockEntity -> DecorLayerDefinitions.WIDOW_BLOOM_TEXTURE));
            event.registerBlockEntityRenderer(DecorationsFurnitureModule.PLUSHIE_BLOCK_ENTITY.value(), PlushieBlockEntityRenderer::new);

            event.registerBlockEntityRenderer(DecorationsFurnitureModule.SKULL_BLOSSOM_BLOCK_ENTITY.value(), SimpleBlockEntityRenderer.create(
                    DecorLayerDefinitions.SKULL_BLOSSOM,
                    blockEntity -> blockEntity.getBlockState().is(DecorationsFurnitureModule.SKULL_BLOSSOM_SKELETON_BLOCK) ? DecorLayerDefinitions.SKULL_BLOSSOM_SKELETON_TEXTURE : DecorLayerDefinitions.SKULL_BLOSSOM_WITHER_TEXTURE
            ));
        });

        modBus.addListener(RegisterSpecialModelRendererEvent.class, event -> {
            event.register(DecorationsFurnitureModule.identifier("widow_bloom"), SimpleBlockEntitySpecialRenderer.WidowBloom.MAP_CODEC);
            event.register(DecorationsFurnitureModule.identifier("skull_blossom"), SimpleBlockEntitySpecialRenderer.SkullBlossom.MAP_CODEC);
            event.register(DecorationsFurnitureModule.identifier("plushie"), PlushieSpecialModelRenderer.Unbaked.MAP_CODEC);
        });

        modBus.addListener(EntityRenderersEvent.RegisterLayerDefinitions.class, event -> {
            event.registerLayerDefinition(DecorLayerDefinitions.WIDOW_BLOOM, DecorLayerDefinitions::createWidowBloom);
            event.registerLayerDefinition(DecorLayerDefinitions.SKULL_BLOSSOM, DecorLayerDefinitions::createSkullBlossom);
        });

        NeoForge.EVENT_BUS.addListener(ScreenEvent.Opening.class, event -> {
            if(event.getNewScreen() instanceof AbstractSignEditScreen screen && !(screen instanceof GravestoneEditScreen) && screen.sign.getType() == DecorationsFurnitureModule.GRAVESTONE_BLOCK_ENTITY.value()) {
                event.setNewScreen(new GravestoneEditScreen(
                        (GravestoneBlockEntity) screen.sign,
                        screen.isFrontText,
                        Minecraft.getInstance().isTextFilteringEnabled()
                ));
            }
        });
    }
}
