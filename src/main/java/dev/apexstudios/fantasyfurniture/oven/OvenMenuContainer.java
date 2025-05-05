package dev.apexstudios.fantasyfurniture.oven;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandlerModifiable;

public record OvenMenuContainer(OvenBlockEntity oven) implements Container {
    private IItemHandlerModifiable getItemHandler() {
        return oven.getItemHandler();
    }

    public void awardUsedRecipesAndPopExperience(ServerPlayer player) {
        oven.data.awardUsedRecipesAndPopExperience(player, oven);
    }

    @Override
    public int getContainerSize() {
        return getItemHandler().getSlots();
    }

    @Override
    public boolean isEmpty() {
        var itemHandler = getItemHandler();

        for (var i = 0; i < itemHandler.getSlots(); i++) {
            if (!itemHandler.getStackInSlot(i).isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public ItemStack getItem(int slot) {
        return getItemHandler().getStackInSlot(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        return getItemHandler().extractItem(slot, amount, false);
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return removeItem(slot, getMaxStackSize());
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        getItemHandler().setStackInSlot(slot, stack);
    }

    @Override
    public void setChanged() {
        oven.setChanged();
    }

    @Override
    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(oven, player);
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        return getItemHandler().isItemValid(slot, stack);
    }

    @Override
    public void clearContent() {
        var itemHandler = getItemHandler();

        for (var i = 0; i < itemHandler.getSlots(); i++) {
            itemHandler.setStackInSlot(i, ItemStack.EMPTY);
        }
    }
}
