package dev.apexstudios.fantasyfurniture;

import dev.apexstudios.apexcore.lib.menu.SimpleMenu;
import dev.apexstudios.apexcore.lib.menu.SimpleMenuScreen;
import dev.apexstudios.apexcore.lib.registree.holder.DeferredMenu;
import dev.apexstudios.fantasyfurniture.block.entity.FurnitureInventoryBlockEntity;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

public interface FurnitureMenus {
    DeferredMenu<SimpleMenu> INVENTORY = new DeferredMenu<>(FantasyFurniture.REGISTREE.registryKey(Registries.MENU, "inventory"));

    static void register(IEventBus modBus) {
        FantasyFurniture.REGISTREE.registerMenu(INVENTORY.getId().getPath(), (containerId, inventory) -> SimpleMenu.forNetwork(INVENTORY.value(), containerId, inventory, FurnitureInventoryBlockEntity.ROWS));

        modBus.addListener(RegisterMenuScreensEvent.class, event -> event.register(INVENTORY.value(), SimpleMenuScreen::new));
    }
}
