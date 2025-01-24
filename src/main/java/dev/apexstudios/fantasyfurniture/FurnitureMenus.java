package dev.apexstudios.fantasyfurniture;

import dev.apexstudios.apexcore.lib.menu.SimpleMenu;
import dev.apexstudios.apexcore.lib.menu.SimpleMenuScreen;
import dev.apexstudios.apexcore.lib.registree.holder.DeferredMenu;
import dev.apexstudios.fantasyfurniture.block.entity.BookshelfBlockEntity;
import dev.apexstudios.fantasyfurniture.block.entity.DeskBlockEntity;
import dev.apexstudios.fantasyfurniture.block.entity.DrawerBlockEntity;
import dev.apexstudios.fantasyfurniture.block.entity.DresserBlockEntity;
import dev.apexstudios.fantasyfurniture.block.entity.LockBoxBlockEntity;
import dev.apexstudios.fantasyfurniture.oven.OvenBlockEntity;
import dev.apexstudios.fantasyfurniture.oven.OvenMenu;
import dev.apexstudios.fantasyfurniture.oven.OvenScreen;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

public interface FurnitureMenus {
    DeferredMenu<SimpleMenu> DRESSER = new DeferredMenu<>(FantasyFurniture.REGISTREE.registryKey(Registries.MENU, "dresser"));
    DeferredMenu<SimpleMenu> LOCKBOX = new DeferredMenu<>(FantasyFurniture.REGISTREE.registryKey(Registries.MENU, "lockbox"));
    DeferredMenu<SimpleMenu> DRAWER = new DeferredMenu<>(FantasyFurniture.REGISTREE.registryKey(Registries.MENU, "drawer"));
    DeferredMenu<SimpleMenu> BOOKSHELF = new DeferredMenu<>(FantasyFurniture.REGISTREE.registryKey(Registries.MENU, "bookshelf"));
    DeferredMenu<SimpleMenu> DESK = new DeferredMenu<>(FantasyFurniture.REGISTREE.registryKey(Registries.MENU, "desk"));
    DeferredMenu<OvenMenu> OVEN = FantasyFurniture.REGISTREE.registerMenu("oven", (containerId, inventory) -> new OvenMenu(containerId, inventory, new SimpleContainer(OvenBlockEntity.SLOTS), new SimpleContainerData(AbstractFurnaceBlockEntity.NUM_DATA_VALUES)));

    static void register(IEventBus modBus) {
        FantasyFurniture.REGISTREE.registerMenu("dresser", (containerId, inventory) -> new SimpleMenu(DRESSER.value(), containerId, inventory, DresserBlockEntity.ROWS));
        FantasyFurniture.REGISTREE.registerMenu("lockbox", (containerId, inventory) -> new SimpleMenu(LOCKBOX.value(), containerId, inventory, LockBoxBlockEntity.ROWS));
        FantasyFurniture.REGISTREE.registerMenu("drawer", (containerId, inventory) -> new SimpleMenu(DRAWER.value(), containerId, inventory, DrawerBlockEntity.ROWS));
        FantasyFurniture.REGISTREE.registerMenu("bookshelf", (containerId, inventory) -> new SimpleMenu(BOOKSHELF.value(), containerId, inventory, BookshelfBlockEntity.ROWS));
        FantasyFurniture.REGISTREE.registerMenu("desk", (containerId, inventory) -> new SimpleMenu(DESK.value(), containerId, inventory, DeskBlockEntity.ROWS));

        modBus.addListener(RegisterMenuScreensEvent.class, event -> {
            event.register(DRESSER.value(), SimpleMenuScreen::new);
            event.register(LOCKBOX.value(), SimpleMenuScreen::new);
            event.register(DRAWER.value(), SimpleMenuScreen::new);
            event.register(BOOKSHELF.value(), SimpleMenuScreen::new);
            event.register(DESK.value(), SimpleMenuScreen::new);
            event.register(OVEN.value(), OvenScreen::new);
        });
    }
}
