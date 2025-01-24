package dev.apexstudios.fantasyfurniture.oven;

import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screens.inventory.SmokerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public final class OvenScreen extends AbstractFurnaceScreen<OvenMenu> {
    public OvenScreen(OvenMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, SmokerScreen.FILTER_NAME, SmokerScreen.TEXTURE, SmokerScreen.LIT_PROGRESS_SPRITE, SmokerScreen.BURN_PROGRESS_SPRITE, SmokerScreen.TABS);
    }
}
