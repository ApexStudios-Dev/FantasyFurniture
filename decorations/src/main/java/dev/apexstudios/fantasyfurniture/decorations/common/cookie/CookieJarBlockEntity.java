package dev.apexstudios.fantasyfurniture.decorations.common.cookie;

import dev.apexstudios.apexcore.api.block.entity.InventoryBlockEntity;
import dev.apexstudios.fantasyfurniture.decorations.common.DecorationsFurnitureModule;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerListener;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;

public final class CookieJarBlockEntity extends InventoryBlockEntity implements ContainerListener {
    public static final int SLOTS = 9;

    public CookieJarBlockEntity(BlockPos pos, BlockState blockState) {
        super(DecorationsFurnitureModule.COOKIE_JAR_BLOCK_ENTITY.value(), pos, blockState, SLOTS);
    }

    @Override
    protected MenuType<?> menuType() {
        return DecorationsFurnitureModule.COOKIE_JAR_MENU.value();
    }

    @Override
    public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player player) {
        var menu = new CookieJarMenu(windowId, playerInventory, inventory);
        menu.addSlotListener(this);
        return menu;
    }

    @Override
    public void slotChanged(AbstractContainerMenu menu, int slot, ItemStack stack) {
        if(level == null) {
            return;
        }

        var fullness = getFullness(inventory);
        var blockState = getBlockState();
        var currentFullness = blockState.getValue(CookieJarBlock.FULLNESS);

        if(fullness != currentFullness) {
            level.setBlockAndUpdate(worldPosition, blockState.setValue(CookieJarBlock.FULLNESS, fullness));
            setChanged();
        }
    }

    @Override
    public void dataChanged(AbstractContainerMenu menu, int slot, int value) {

    }

    public static CookieJarBlock.Fullness getFullness(ResourceHandler<ItemResource> handler) {
        var size = handler.size();
        var maxItems = 0L;
        var filledItems = 0L;

        for(var i = 0; i < size; i++) {
            var resource = handler.getResource(i);
            maxItems += handler.getCapacityAsInt(i, resource);

            if(!resource.isEmpty()) {
                filledItems += handler.getAmountAsLong(i);
            }
        }

        var fillPercent = filledItems * 100L / maxItems;

        if(fillPercent < 33L) {
            return CookieJarBlock.Fullness.EMPTY;
        } else if(fillPercent < 66L) {
            return CookieJarBlock.Fullness.HALF;
        } else {
            return CookieJarBlock.Fullness.FULL;
        }
    }
}
