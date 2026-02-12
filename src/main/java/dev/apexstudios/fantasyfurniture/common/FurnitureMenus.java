package dev.apexstudios.fantasyfurniture.common;

import dev.apexstudios.apexcore.api.menu.SimpleMenu;
import dev.apexstudios.apexcore.api.menu.SimpleMenuScreen;
import dev.apexstudios.fantasyfurniture.common.block.entity.FurnitureInventoryBlockEntity;
import dev.apexstudios.registree.holder.DeferredMenuType;

public interface FurnitureMenus {
    DeferredMenuType<SimpleMenu> INVENTORY = DeferredMenuType.createMenuType(FantasyFurniture.identifier("inventory"));

    static void register() {
        FantasyFurniture.MENU_TYPES.register(
                INVENTORY.getId().getPath(),
                (containerId, inventory) -> new SimpleMenu(INVENTORY.value(), containerId, inventory, FurnitureInventoryBlockEntity.ROWS),
                () -> () -> SimpleMenuScreen::new
        );
    }
}
