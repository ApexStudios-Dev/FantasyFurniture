package dev.apexstudios.fantasyfurniture.decorations.cookie;

import dev.apexstudios.fantasyfurniture.decorations.DecorationsFurnitureModule;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.transfer.IndexModifier;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.StacksResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.item.ResourceHandlerSlot;

public final class CookieJarMenu extends AbstractContainerMenu {
    private CookieJarMenu(int containerId, Inventory inventory, ResourceHandler<ItemResource> itemHandler, IndexModifier<ItemResource> indexModifier) {
        super(DecorationsFurnitureModule.COOKIE_JAR_MENU.value(), containerId);

        for(var i = 0; i < 3; i++) {
            for(var j = 0; j < 3; j++) {
                addSlot(new ResourceHandlerSlot(itemHandler, indexModifier, j + i * 3, 62 + j * 18, 17 + i * 18));
            }
        }

        addStandardInventorySlots(inventory, 8, 84);
    }

    CookieJarMenu(int containerId, Inventory inventory, StacksResourceHandler<ItemStack, ItemResource> resourceHandler) {
        this(containerId, inventory, resourceHandler, resourceHandler::set);
    }

    public CookieJarMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, new ItemStacksResourceHandler(CookieJarBlockEntity.SLOTS));
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        var result = ItemStack.EMPTY;
        var slot = slots.get(index);

        if(slot != null && slot.hasItem()) {
            var stack = slot.getItem();
            result = stack.copy();

            if(index < 9) {
                if (!moveItemStackTo(stack, 9, 45, true)) {
                    return ItemStack.EMPTY;
                }
            } else if(!moveItemStackTo(stack, 0, 9, false)) {
                return ItemStack.EMPTY;
            }

            if(stack.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if(stack.getCount() == result.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stack);
        }

        return result;
    }
}
