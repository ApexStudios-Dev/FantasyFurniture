package dev.apexstudios.fantasyfurniture.decorations.common.grave;

import dev.apexstudios.fantasyfurniture.decorations.common.DecorationsFurnitureModule;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractSignEditScreen;
import net.minecraft.client.gui.screens.inventory.SignEditScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.joml.Vector3fc;

public final class GravestoneEditScreen extends AbstractSignEditScreen {
    public static final Identifier TEXTURE = DecorationsFurnitureModule.identifier("textures/gui/gravestone.png");
    public static final String TRANSLATION = DecorationsFurnitureModule.ID + ".gravestone.edit";

    public GravestoneEditScreen(GravestoneBlockEntity blockEntity, boolean isFrontText, boolean isFiltered) {
        super(blockEntity, isFrontText, isFiltered, Component.translatable(TRANSLATION));
    }

    @Override
    protected void extractSignBackground(GuiGraphicsExtractor graphics) {
        graphics.blit(
                RenderPipelines.GUI_TEXTURED,
                TEXTURE,
                -64, -64,
                0F, 0F,
                128, 128,
                128, 128
        );
    }

    @Override
    protected Vector3fc getSignTextScale() {
        return SignEditScreen.TEXT_SCALE;
    }

    @Override
    protected float getSignYOffset() {
        return 132F;
    }
}
