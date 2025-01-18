package dev.apexstudios.fantasyfurniture;

import dev.apexstudios.apexcore.lib.menu.SimpleMenu;
import dev.apexstudios.apexcore.lib.menu.SimpleMenuScreen;
import dev.apexstudios.apexcore.lib.registree.holder.DeferredMenu;
import dev.apexstudios.fantasyfurniture.block.entity.DresserBlockEntity;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

public interface FurnitureMenus {
    DeferredMenu<SimpleMenu> DRESSER = new DeferredMenu<>(FantasyFurniture.REGISTREE.registryKey(Registries.MENU, "dresser"));

    static void register(IEventBus modBus) {
        FantasyFurniture.REGISTREE.registerMenu("dresser", (containerId, inventory) -> new SimpleMenu(DRESSER.value(), containerId, inventory, DresserBlockEntity.SLOTS));

        modBus.addListener(RegisterMenuScreensEvent.class, event -> {
            event.register(DRESSER.value(), SimpleMenuScreen::new);
        });
    }
}
