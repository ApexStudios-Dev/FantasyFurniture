package xyz.apex.forge.fantasyfurniture.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import xyz.apex.forge.apexcore.revamp.block.entity.InventoryBlockEntity;
import xyz.apex.forge.fantasyfurniture.menu.SmallInventoryMenu;

public final class DrawerBlockEntity extends InventoryBlockEntity
{
	public DrawerBlockEntity(BlockEntityType<? extends DrawerBlockEntity> blockEntityType, BlockPos pos, BlockState blockState)
	{
		super(blockEntityType, pos, blockState, SmallInventoryMenu.SIZE);
	}
}