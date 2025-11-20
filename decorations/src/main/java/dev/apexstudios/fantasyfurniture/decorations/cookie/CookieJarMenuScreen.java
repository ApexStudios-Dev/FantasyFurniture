package dev.apexstudios.fantasyfurniture.decorations.cookie;

import dev.apexstudios.apexcore.lib.menu.SimpleMenuScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

public final class CookieJarMenuScreen extends AbstractContainerScreen<CookieJarMenu> {
    public CookieJarMenuScreen(CookieJarMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void init() {
        imageHeight = 114 + 3 * 18;
        inventoryLabelY = imageHeight - 94;
        super.init();
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);
        renderTooltip(graphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        graphics.pose().pushMatrix();
        graphics.pose().translate(leftPos, topPos);
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, SimpleMenuScreen.WINDOW_SPRITE, 0, -2, imageWidth, imageHeight + 1);

        for(var slot : menu.slots) {
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, SimpleMenuScreen.SLOT_SPRITE, slot.x - 1, slot.y - 1, AbstractContainerMenu.SLOT_SIZE, AbstractContainerMenu.SLOT_SIZE);
        }

        graphics.pose().popMatrix();
    }
}
