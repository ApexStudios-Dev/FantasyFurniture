package dev.apexstudios.fantasyfurniture;

import dev.apexstudios.apexcore.lib.menu.SimpleMenu;
import dev.apexstudios.apexcore.lib.menu.SimpleMenuScreen;
import dev.apexstudios.apexcore.lib.registree.holder.DeferredMenu;
import dev.apexstudios.fantasyfurniture.block.entity.FurnitureInventoryBlockEntity;
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
    DeferredMenu<SimpleMenu> INVENTORY = new DeferredMenu<>(FantasyFurniture.REGISTREE.registryKey(Registries.MENU, "inventory"));
    DeferredMenu<OvenMenu> OVEN = FantasyFurniture.REGISTREE.registerMenu("oven", (containerId, inventory) -> new OvenMenu(containerId, inventory, new SimpleContainer(OvenBlockEntity.SLOTS), new SimpleContainerData(AbstractFurnaceBlockEntity.NUM_DATA_VALUES)));

    static void register(IEventBus modBus) {
        FantasyFurniture.REGISTREE.registerMenu(INVENTORY.getId().getPath(), (containerId, inventory) -> new SimpleMenu(INVENTORY.value(), containerId, inventory, FurnitureInventoryBlockEntity.ROWS));

        modBus.addListener(RegisterMenuScreensEvent.class, event -> {
            event.register(INVENTORY.value(), SimpleMenuScreen::new);
            event.register(OVEN.value(), OvenScreen::new);
        });
    }
}
