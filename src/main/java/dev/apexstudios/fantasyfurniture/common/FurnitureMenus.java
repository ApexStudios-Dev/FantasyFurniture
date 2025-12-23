package dev.apexstudios.fantasyfurniture.common;

import dev.apexstudios.apexcore.api.menu.SimpleMenu;
import dev.apexstudios.apexcore.api.menu.SimpleMenuScreen;
import dev.apexstudios.fantasyfurniture.common.block.entity.FurnitureInventoryBlockEntity;
import dev.apexstudios.registree.api.holder.DeferredMenu;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

public interface FurnitureMenus {
    DeferredMenu<SimpleMenu> INVENTORY = new DeferredMenu<>(FantasyFurniture.REGISTREE.registryKey(Registries.MENU, "inventory"));

    static void register(IEventBus modBus) {
        FantasyFurniture.REGISTREE.registerMenu(INVENTORY.getId().getPath(), (containerId, inventory) -> new SimpleMenu(INVENTORY.value(), containerId, inventory, FurnitureInventoryBlockEntity.ROWS));

        modBus.addListener(RegisterMenuScreensEvent.class, event -> event.register(INVENTORY.value(), SimpleMenuScreen::new));
    }
}
