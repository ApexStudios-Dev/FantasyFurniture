package dev.apexstudios.fantasyfurniture.block.entity;

import dev.apexstudios.apexcore.lib.block.entity.InventoryBlockEntity;
import dev.apexstudios.apexcore.lib.menu.SimpleMenu;
import dev.apexstudios.fantasyfurniture.FurnitureBlockEntities;
import dev.apexstudios.fantasyfurniture.FurnitureMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class FurnitureInventoryBlockEntity extends InventoryBlockEntity {
    public static final int ROWS = 3;

    protected FurnitureInventoryBlockEntity(BlockEntityType<? extends InventoryBlockEntity> blockEntityType, BlockPos pos, BlockState blockState) {
        super(blockEntityType, pos, blockState, ROWS, AbstractContainerMenu.SLOTS_PER_ROW);
    }

    public FurnitureInventoryBlockEntity(BlockPos pos, BlockState blockState) {
        this(FurnitureBlockEntities.INVENTORY.value(), pos, blockState);
    }

    @Override
    protected MenuType<? extends SimpleMenu> menuType() {
        return FurnitureMenus.INVENTORY.value();
    }
}
