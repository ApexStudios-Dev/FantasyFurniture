package dev.apexstudios.fantasyfurniture;

import dev.apexstudios.apexcore.lib.menu.SimpleMenu;
import dev.apexstudios.apexcore.lib.menu.SimpleMenuScreen;
import dev.apexstudios.apexcore.lib.registree.holder.DeferredMenu;
import dev.apexstudios.fantasyfurniture.block.entity.DresserBlockEntity;
import dev.apexstudios.fantasyfurniture.block.entity.LockBoxBlockEntity;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

public interface FurnitureMenus {
    DeferredMenu<SimpleMenu> DRESSER = new DeferredMenu<>(FantasyFurniture.REGISTREE.registryKey(Registries.MENU, "dresser"));
    DeferredMenu<SimpleMenu> LOCKBOX = new DeferredMenu<>(FantasyFurniture.REGISTREE.registryKey(Registries.MENU, "lockbox"));

    static void register(IEventBus modBus) {
        FantasyFurniture.REGISTREE.registerMenu("dresser", (containerId, inventory) -> new SimpleMenu(DRESSER.value(), containerId, inventory, DresserBlockEntity.SLOTS));
        FantasyFurniture.REGISTREE.registerMenu("lockbox", (containerId, inventory) -> new SimpleMenu(LOCKBOX.value(), containerId, inventory, LockBoxBlockEntity.SLOTS));

        modBus.addListener(RegisterMenuScreensEvent.class, event -> {
            event.register(DRESSER.value(), SimpleMenuScreen::new);
            event.register(LOCKBOX.value(), SimpleMenuScreen::new);
        });
    }
}
