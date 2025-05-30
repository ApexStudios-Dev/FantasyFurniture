package dev.apexstudios.fantasyfurniture.station;

import dev.apexstudios.apexcore.lib.menu.SimpleMenuScreen;
import dev.apexstudios.fantasyfurniture.FantasyFurniture;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import org.joml.Math;

public final class FurnitureStationScreen extends AbstractContainerScreen<FurnitureStationMenu> {
    public static final ResourceLocation SPRITE_ARROW = FantasyFurniture.identifier("container/furniture_station/arrow");
    public static final ResourceLocation SPRITE_RECIPE = FantasyFurniture.identifier("container/furniture_station/recipe");
    public static final ResourceLocation SPRITE_RECIPE_BACKGROUND = FantasyFurniture.identifier("container/furniture_station/recipe_background");
    public static final ResourceLocation SPRITE_RECIPE_HIGHLIGHTED = FantasyFurniture.identifier("container/furniture_station/recipe_highlighted");
    public static final ResourceLocation SPRITE_RECIPE_SELECTED = FantasyFurniture.identifier("container/furniture_station/recipe_selected");
    public static final ResourceLocation SPRITE_SCROLLER = FantasyFurniture.identifier("container/furniture_station/scroller");
    public static final ResourceLocation SPRITE_SCROLLER_BACKGROUND = FantasyFurniture.identifier("container/furniture_station/scroller_background");
    public static final ResourceLocation SPRITE_SCROLLER_DISABLED = FantasyFurniture.identifier("container/furniture_station/scroller_disabled");

    private float scrollOffs = 0F;
    private boolean scrolling = false;
    private int startIndex = 0;
    private boolean displayRecipes = false;

    private int recipeBackgroundX;
    private int recipeBackgroundY;
    private int recipeBackgroundWidth;
    private int recipeBackgroundHeight;
    private int recipeX;
    private int recipeY;
    private int recipeColumns;
    private int recipeRows;
    private int scrollBarX;
    private int scrollBarY;
    private int scrollBarWidth;
    private int scrollBarHeight;
    private int scrollBarBackgroundWidth;
    private int scrollBarBackgroundHeight;
    private int scrollBarFullHeight;

    FurnitureStationScreen(FurnitureStationMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);

        menu.registerListener(this::containerChanged);
    }

    @Override
    protected void init() {
        super.init();
        updatePositions();
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);
        renderTooltip(graphics, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {

    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        renderWindow(graphics);

        graphics.blitSprite(RenderType::guiTextured, SPRITE_ARROW, recipeX + (18 * 3) + 15, recipeY - 18 - 7, 60, 16);
        graphics.blitSprite(RenderType::guiTextured, SPRITE_RECIPE_BACKGROUND, recipeBackgroundX, recipeBackgroundY, recipeBackgroundWidth, recipeBackgroundHeight);
        graphics.blitSprite(RenderType::guiTextured, SPRITE_SCROLLER_BACKGROUND, scrollBarX, scrollBarY, scrollBarBackgroundWidth, scrollBarBackgroundHeight);

        renderButtons(graphics, mouseX, mouseY, false);

        var k = (int) (57F * scrollOffs);
        var scrollerSprite = isScrollBarActive() ? SPRITE_SCROLLER : SPRITE_SCROLLER_DISABLED;
        graphics.blitSprite(RenderType::guiTextured, scrollerSprite, scrollBarX + 1, scrollBarY + 1 + k, scrollBarWidth, scrollBarHeight);

        renderButtons(graphics, mouseX, mouseY, true);
    }

    @Override
    protected void renderTooltip(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        super.renderTooltip(guiGraphics, mouseX, mouseY);

        if (menu.getCarried().isEmpty() && hoveredSlot != null && hoveredSlot.hasItem())
            return;

        if (displayRecipes) {
            var maxSlots = recipeColumns * recipeRows;
            var lastIndex = startIndex + maxSlots;

            for (var index = startIndex; index < lastIndex && index < menu.recipes().size(); index++) {
                var slotIndex = index - startIndex;
                var slotX = recipeX + slotIndex % recipeColumns * AbstractContainerMenu.SLOT_SIZE;
                var slotY = recipeY + slotIndex / recipeColumns * AbstractContainerMenu.SLOT_SIZE;

                if (mouseX >= slotX && mouseY >= slotY && mouseX < slotX + AbstractContainerMenu.SLOT_SIZE && mouseY < slotY + AbstractContainerMenu.SLOT_SIZE) {
                    guiGraphics.renderTooltip(font, displayStack(index), mouseX, mouseY);
                    break;
                }
            }
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        scrolling = false;

        if (displayRecipes) {
            var maxSlots = recipeColumns * recipeRows;
            var lastIndex = startIndex + maxSlots;

            for (var index = startIndex; index < lastIndex && index < menu.recipes().size(); index++) {
                if(menu.selectedRecipe() == index)
                    continue;

                var slotIndex = index - startIndex;
                var slotX = (double) (recipeX + slotIndex % recipeColumns * AbstractContainerMenu.SLOT_SIZE);
                var slotY = (double) (recipeY + slotIndex / recipeColumns * AbstractContainerMenu.SLOT_SIZE);

                if (mouseX >= slotX && mouseY >= slotY && mouseX < slotX + AbstractContainerMenu.SLOT_SIZE && mouseY < slotY + AbstractContainerMenu.SLOT_SIZE) {
                    if (!menu.clickMenuButton(minecraft.player, index))
                        continue;

                    minecraft.getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1F));
                    minecraft.gameMode.handleInventoryButtonClick(menu.containerId, index);
                    return true;
                }
            }

            if (mouseX >= scrollBarX && mouseY >= scrollBarY && mouseX < scrollBarX + scrollBarWidth && mouseY < scrollBarY + scrollBarFullHeight)
                scrolling = true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (scrolling && isScrollBarActive()) {
            var scrollEndY = recipeY + scrollBarFullHeight;
            scrollOffs = (float) (mouseY - recipeY - (scrollBarHeight / 2F)) / ((scrollEndY - recipeY) - scrollBarHeight);
            scrollOffs = Math.clamp(0F, 1F, scrollOffs);
            startIndex = (int) ((scrollOffs * getOffscreenRows()) + .5D) * recipeColumns;
            return true;
        }

        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        if (super.mouseScrolled(mouseX, mouseY, scrollX, scrollY))
            return true;

        if (isScrollBarActive()) {
            var offscreenRows = getOffscreenRows();
            var amount = (float) scrollY / offscreenRows;
            scrollOffs = Math.clamp(0F, 1F, scrollOffs - amount);
            startIndex = (int) ((scrollOffs * offscreenRows) + .5D) * recipeColumns;
        }

        return true;
    }

    private void updatePositions() {
        recipeColumns = 8;
        recipeRows = 4;

        recipeBackgroundWidth = AbstractContainerMenu.SLOT_SIZE * recipeColumns + 1;
        recipeBackgroundHeight = AbstractContainerMenu.SLOT_SIZE * recipeRows + 2;

        scrollBarBackgroundWidth = 14;
        scrollBarBackgroundHeight = recipeBackgroundHeight;
        scrollBarFullHeight = scrollBarBackgroundHeight - 2;

        scrollBarWidth = scrollBarBackgroundWidth - 2;
        scrollBarHeight = 15;

        recipeBackgroundX = leftPos - 3 + ((imageWidth / 2) - (recipeBackgroundWidth / 2) - (scrollBarWidth / 2));
        recipeBackgroundY = topPos + 32;

        recipeX = recipeBackgroundX + 1;
        recipeY = recipeBackgroundY + 1;

        scrollBarX = recipeBackgroundX + recipeBackgroundWidth + 1;
        scrollBarY = recipeBackgroundY;

        imageHeight = 166 + AbstractContainerMenu.SLOT_SIZE + 12;
    }

    private void renderWindow(GuiGraphics graphics) {
        graphics.pose().pushPose();
        graphics.pose().translate(leftPos, topPos, 0);
        graphics.blitSprite(RenderType::guiTextured, SimpleMenuScreen.WINDOW_SPRITE, 0, 0, imageWidth, imageHeight + 1);

        for(var slot : menu.slots) {
            graphics.blitSprite(RenderType::guiTextured, SimpleMenuScreen.SLOT_SPRITE, slot.x - 1, slot.y - 1, AbstractContainerMenu.SLOT_SIZE, AbstractContainerMenu.SLOT_SIZE);
        }

        graphics.pose().popPose();
    }

    private void renderButtons(GuiGraphics graphics, int mouseX, int mouseY, boolean drawItem) {
        var maxSlots = recipeColumns * recipeRows;
        var lastIndex = startIndex + maxSlots;

        for (var index = startIndex; index < lastIndex && index < menu.recipes().size(); index++) {
            var slotIndex = index - startIndex;
            var slotX = recipeX + slotIndex % recipeColumns * AbstractContainerMenu.SLOT_SIZE;
            var slotY = recipeY + slotIndex / recipeColumns * AbstractContainerMenu.SLOT_SIZE;

            if (drawItem) {
                graphics.renderItem(displayStack(index), slotX + 1, slotY + 1);
            } else {
                var slotSprite = SPRITE_RECIPE;

                if (index == menu.selectedRecipe())
                    slotSprite = SPRITE_RECIPE_SELECTED;
                else if (mouseX >= slotX && mouseY >= slotY && mouseX < slotX + AbstractContainerMenu.SLOT_SIZE && mouseY < slotY + AbstractContainerMenu.SLOT_SIZE)
                    slotSprite = SPRITE_RECIPE_HIGHLIGHTED;

                graphics.blitSprite(RenderType::guiTextured, slotSprite, slotX, slotY, AbstractContainerMenu.SLOT_SIZE, AbstractContainerMenu.SLOT_SIZE);
            }
        }
    }

    private boolean isScrollBarActive() {
        var maxSlots = recipeColumns * recipeRows;
        return displayRecipes && menu.recipes().size() > maxSlots;
    }

    private int getOffscreenRows() {
        return (menu.recipes().size() + recipeColumns - 1) / recipeColumns - recipeRows;
    }

    private void containerChanged() {
        displayRecipes = menu.hasInput();

        if (!displayRecipes) {
            scrollOffs = 0F;
            startIndex = 0;
        }

        updatePositions();
    }

    private ItemStack displayStack(int index) {
        var recipes = menu.recipes();

        if(recipes.isEmpty() || index > recipes.size())
            return ItemStack.EMPTY;

        return recipes.get(index).value().result();
    }
}
