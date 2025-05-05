package dev.apexstudios.fantasyfurniture.oven;

import dev.apexstudios.apexcore.lib.block.entity.InventoryBlockEntity;
import dev.apexstudios.fantasyfurniture.FurnitureBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.Tags;

public final class OvenBlockEntity extends InventoryBlockEntity {
    public static final int SLOT_INPUT = 0;
    public static final int SLOT_FUEL = 1;
    public static final int SLOT_OUTPUT = 2;
    public static final int SLOTS = 3;

    final OvenData data = new OvenData();

    public OvenBlockEntity(BlockPos pos, BlockState blockState) {
        super(FurnitureBlockEntities.OVEN.value(), pos, blockState, SLOTS);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        data.save(tag);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        data.load(tag);
    }

    @Override
    public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player player) {
        return new OvenMenu(windowId, playerInventory, this);
    }

    @Override
    protected boolean canInsert(int slot, ItemStack stack) {
        if(slot == SLOT_FUEL) {
            if(stack.is(Tags.Items.BUCKETS_EMPTY) && !inventory.getStackInSlot(SLOT_FUEL).is(Tags.Items.BUCKETS_EMPTY))
                return true;
            if(level != null && stack.getBurnTime(null, level.fuelValues()) > 0)
                return true;
            return false;
        } else if(slot == SLOT_OUTPUT)
            return false;

        return super.canInsert(slot, stack);
    }

    @Override
    protected void onSlotChanged(int slot) {
        if(level instanceof ServerLevel level) {
            data.cookingTotalTime = data.getTotalCookTime(level, inventory);
            data.cookingTimer = 0;
        }
    }

    public void serverTick(ServerLevel level, BlockPos pos, BlockState blockState) {
        data.serverTick(level, pos, blockState, this);
    }

    @Override
    public void preRemoveSideEffects(BlockPos pos, BlockState blockState) {
        if(level instanceof ServerLevel sLevel)
            data.getRecipesToAwardAndPopExperience(sLevel, Vec3.atCenterOf(pos));

        super.preRemoveSideEffects(pos, blockState);
    }
}
