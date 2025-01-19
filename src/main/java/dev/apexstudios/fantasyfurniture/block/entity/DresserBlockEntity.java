package dev.apexstudios.fantasyfurniture.block.entity;

import dev.apexstudios.apexcore.lib.component.ComponentRegistrar;
import dev.apexstudios.apexcore.lib.component.block.entity.BaseBlockEntityComponentHolder;
import dev.apexstudios.apexcore.lib.component.block.entity.BlockEntityComponent;
import dev.apexstudios.apexcore.lib.component.block.entity.BlockEntityComponentHelper;
import dev.apexstudios.apexcore.lib.component.block.entity.BlockEntityComponentTypes;
import dev.apexstudios.apexcore.lib.menu.SimpleMenu;
import dev.apexstudios.fantasyfurniture.FurnitureBlockEntities;
import dev.apexstudios.fantasyfurniture.FurnitureMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public final class DresserBlockEntity extends BaseBlockEntityComponentHolder {
    public static final int ROWS = 3;

    public DresserBlockEntity(BlockPos pos, BlockState blockState) {
        super(FurnitureBlockEntities.DRESSER.value(), pos, blockState);
    }

    @Override
    protected AbstractContainerMenu createMenu(int windowId, Player player) {
        return new SimpleMenu(FurnitureMenus.DRESSER.value(), windowId, player.getInventory(), getComponentOrThrow(BlockEntityComponentTypes.INVENTORY).getItemHandler(), ROWS);
    }

    @Override
    protected void registerComponents(ComponentRegistrar<BlockEntityComponent> registrar) {
        BlockEntityComponentHelper.registerInventoryComponents(registrar, builder -> builder.slots(ROWS * AbstractContainerMenu.SLOTS_PER_ROW));
    }
}
