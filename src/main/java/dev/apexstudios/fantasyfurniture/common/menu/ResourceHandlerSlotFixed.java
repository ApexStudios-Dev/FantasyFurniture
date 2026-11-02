package dev.apexstudios.fantasyfurniture.common.menu;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.transfer.IndexModifier;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.transaction.Transaction;

/// No changes fix is upstream in [StackCopySlotFixed]
public class ResourceHandlerSlotFixed extends StackCopySlotFixed {
    private final ResourceHandler<ItemResource> handler;
    private final IndexModifier<ItemResource> slotModifier;

    public ResourceHandlerSlotFixed(ResourceHandler<ItemResource> handler, IndexModifier<ItemResource> slotModifier, int handlerSlot, int xPosition, int yPosition) {
        super(handlerSlot, xPosition, yPosition);

        this.handler = handler;
        this.slotModifier = slotModifier;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        if(stack.isEmpty()) {
            return false;
        }

        return handler.isValid(getSlotIndex(), ItemResource.of(stack));
    }

    @Override
    protected ItemStack getStackCopy() {
        return handler.getResource(getSlotIndex()).toStack(handler.getAmountAsInt(getSlotIndex()));
    }

    @Override
    protected void setStackCopy(ItemStack stack) {
        slotModifier.set(getSlotIndex(), ItemResource.of(stack), stack.count());
    }

    @Override
    protected void onQuickCraft(ItemStack picked, int count) {
    }

    @Override
    public int getMaxStackSize() {
        return handler.getCapacityAsInt(getSlotIndex(), ItemResource.EMPTY);
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return handler.getCapacityAsInt(getSlotIndex(), ItemResource.of(stack));
    }

    @Override
    public boolean mayPickup(Player player) {
        var resource = handler.getResource(getSlotIndex());

        if(resource.isEmpty()) {
            return false;
        }

        try(var tx = Transaction.openRoot()) {
            return handler.extract(getSlotIndex(), resource, 1, tx) == 1;
        }
    }

    public ResourceHandler<ItemResource> getResourceHandler() {
        return handler;
    }

    @Override
    public boolean isSameInventory(Slot other) {
        return other instanceof ResourceHandlerSlotFixed rhs && rhs.handler == handler;
    }
}
