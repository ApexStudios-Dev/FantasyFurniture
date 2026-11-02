package dev.apexstudios.fantasyfurniture.common.menu;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.world.inventory.StackCopySlot;

/// Fixed version of [StackCopySlot]. Bundles do not cause item duplication and allow scroll selecting.
public abstract class StackCopySlotFixed extends Slot {
    private static final Container emptyInventory = new SimpleContainer(0);

    // was nullable, made non-null and checking for `isEmpty` now
    private ItemStack cachedReturnStack = ItemStack.EMPTY;
    private ItemStack lastHandlerStack = ItemStack.EMPTY;

    public StackCopySlotFixed(int slot, int x, int y) {
        super(emptyInventory, slot, x, y);
    }

    protected abstract ItemStack getStackCopy();

    protected abstract void setStackCopy(ItemStack stackCopy);

    @Override
    public final ItemStack getItem() {
        setChanged();
        // Issue was here `cachedReturnStack` was always being set to the handlers stack
        // while the fix could have remained here, it was moved to `setChanged`
        // as thats more of a correct place.
        // the `cachedReturnStack` should only be updated when the handlers stack changes
        return cachedReturnStack;
    }

    @Override
    public final void set(ItemStack stack) {
        setStackCopy(stack);
        setChanged();
    }

    @Override
    public final void setChanged() {
        var handlerStack = getStackCopy();

        // cache stacks if they are empty or have changed
        if(cachedReturnStack.isEmpty() || lastHandlerStack.isEmpty() || !ItemStack.matches(lastHandlerStack, handlerStack)) {
            cachedReturnStack = handlerStack.copy(); // cache a copy of the handlers stack
            lastHandlerStack = handlerStack; // keep track of the handlers stack to validate it changed later
        }
    }

    @Override
    public ItemStack remove(int amount) {
        var stack = getStackCopy().copy();
        var ret = stack.split(amount);
        set(stack);
        return ret;
    }
}
