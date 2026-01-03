package dev.apexstudios.fantasyfurniture.common.block.entity;

import dev.apexstudios.apexcore.api.block.entity.InventoryBlockEntity;
import dev.apexstudios.apexcore.api.menu.SimpleMenu;
import dev.apexstudios.fantasyfurniture.common.FurnitureBlockEntities;
import dev.apexstudios.fantasyfurniture.common.FurnitureMenus;
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
