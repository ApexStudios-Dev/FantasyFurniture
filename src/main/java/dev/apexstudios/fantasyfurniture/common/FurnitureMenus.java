package dev.apexstudios.fantasyfurniture.common;

import dev.apexstudios.apexcore.api.menu.SimpleMenu;
import dev.apexstudios.apexcore.api.menu.SimpleMenuScreen;
import dev.apexstudios.fantasyfurniture.common.block.entity.FurnitureInventoryBlockEntity;
import dev.apexstudios.registree.holder.DeferredMenu;
import dev.apexstudios.registree.holder.Holders;

public interface FurnitureMenus {
    DeferredMenu<SimpleMenu> INVENTORY = Holders.createMenu(FantasyFurniture.identifier("menu"));

    static void register() {
        FantasyFurniture.REGISTREE.menu(INVENTORY.getId().getPath(), (containerId, inventory) -> new SimpleMenu(INVENTORY.value(), containerId, inventory, FurnitureInventoryBlockEntity.ROWS))
                .screen(() -> () -> SimpleMenuScreen::new)
                .register();
    }
}
