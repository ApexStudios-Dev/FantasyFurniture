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
    DeferredMenu<SimpleMenu> DRESSER = simple("dresser");
    DeferredMenu<SimpleMenu> LOCKBOX = simple("lockbox");
    DeferredMenu<SimpleMenu> DRAWER = simple("drawer");
    DeferredMenu<SimpleMenu> BOOKSHELF = simple("bookshelf");
    DeferredMenu<SimpleMenu> DESK = simple("desk");
    DeferredMenu<OvenMenu> OVEN = FantasyFurniture.REGISTREE.registerMenu("oven", (containerId, inventory) -> new OvenMenu(containerId, inventory, new SimpleContainer(OvenBlockEntity.SLOTS), new SimpleContainerData(AbstractFurnaceBlockEntity.NUM_DATA_VALUES)));

    static void register(IEventBus modBus) {
        simple(DRESSER, DresserBlockEntity.ROWS);
        simple(LOCKBOX, LockBoxBlockEntity.ROWS);
        simple(DRAWER, DrawerBlockEntity.ROWS);
        simple(BOOKSHELF, BookshelfBlockEntity.ROWS);
        simple(DESK, DeskBlockEntity.ROWS);

        modBus.addListener(RegisterMenuScreensEvent.class, event -> {
            simple(event, DRESSER, LOCKBOX, DRAWER, BOOKSHELF, DESK);
            event.register(OVEN.value(), OvenScreen::new);
        });
    }

    private static void simple(DeferredMenu<SimpleMenu> holder, int rows) {
        FantasyFurniture.REGISTREE.registerMenu(holder.getId().getPath(), (containerId, inventory) -> new SimpleMenu(holder.value(), containerId, inventory, rows));
    }

    @SafeVarargs
    private static void simple(RegisterMenuScreensEvent event, DeferredMenu<SimpleMenu>... holders) {
        for(var holder : holders) {
            event.register(holder.value(), SimpleMenuScreen::new);
        }
    }

    private static DeferredMenu<SimpleMenu> simple(String registryName) {
        return new DeferredMenu<>(FantasyFurniture.REGISTREE.registryKey(Registries.MENU, registryName));
    }
}
