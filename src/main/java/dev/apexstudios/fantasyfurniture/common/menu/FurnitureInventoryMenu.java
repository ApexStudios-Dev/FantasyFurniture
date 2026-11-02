package dev.apexstudios.fantasyfurniture.common.menu;

import dev.apexstudios.apexcore.api.menu.SimpleMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.transfer.IndexModifier;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.StacksResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;

public final class FurnitureInventoryMenu extends SimpleMenu {
    public FurnitureInventoryMenu(MenuType<? extends SimpleMenu> menuType, int containerId, Inventory inventory, StacksResourceHandler<ItemStack, ItemResource> resourceHandler) {
        super(menuType, containerId, inventory, resourceHandler);
    }

    public FurnitureInventoryMenu(MenuType<? extends SimpleMenu> menuType, int containerId, Inventory inventory, int rowCount) {
        super(menuType, containerId, inventory, rowCount);
    }

    @Override
    protected void addItemHandlerSlots(ResourceHandler<ItemResource> itemHandler, IndexModifier<ItemResource> indexModifier) {
        for(var i = 0; i < rowCount(); i++) {
            for(var j = 0; j < SLOTS_PER_ROW; j++) {
                addSlot(new ResourceHandlerSlotFixed(itemHandler, indexModifier, j + i * SLOTS_PER_ROW, 8 + j * SLOT_SIZE, 18 + i * SLOT_SIZE));
            }
        }
    }
}
