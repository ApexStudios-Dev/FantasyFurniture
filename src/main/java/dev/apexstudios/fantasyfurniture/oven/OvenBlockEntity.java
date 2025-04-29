package dev.apexstudios.fantasyfurniture.oven;

import dev.apexstudios.apexcore.lib.component.block.entity.BaseBlockEntityComponentHolder;
import dev.apexstudios.apexcore.lib.component.block.entity.BlockEntityComponentHelper;
import dev.apexstudios.apexcore.lib.component.block.entity.BlockEntityComponentRegistrar;
import dev.apexstudios.fantasyfurniture.FurnitureBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.Tags;

public final class OvenBlockEntity extends BaseBlockEntityComponentHolder {
    public static final int SLOT_INPUT = 0;
    public static final int SLOT_FUEL = 1;
    public static final int SLOT_OUTPUT = 2;
    public static final int SLOTS = 3;

    final OvenData data = new OvenData();

    public OvenBlockEntity(BlockPos pos, BlockState blockState) {
        super(FurnitureBlockEntities.OVEN.value(), pos, blockState);
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
    protected AbstractContainerMenu createMenu(int windowId, Player player) {
        return new OvenMenu(windowId, player.getInventory(), this);
    }

    @Override
    protected void registerComponents(BlockEntityComponentRegistrar registrar) {
        BlockEntityComponentHelper.registerInventoryComponents(registrar, builder -> builder
                .slot(SLOT_INPUT, slot -> slot.listener((index, inventory) -> {
                    if(level instanceof ServerLevel level) {
                        data.cookingTotalTime = data.getTotalCookTime(level, inventory);
                        data.cookingTimer = 0;
                        setChanged();
                    }
                }))
                .slot(SLOT_FUEL, slot -> slot.validator((index, inventory, stack) ->
                        (level != null && stack.getBurnTime(null, level.fuelValues()) > 0) ||
                                (stack.is(Tags.Items.BUCKETS_EMPTY) && !inventory.getStackInSlot(index).is(Tags.Items.BUCKETS_EMPTY))
                ))
                .slot(SLOT_OUTPUT, slot -> slot.validator((index, inventory, stack) -> false))
        );
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
