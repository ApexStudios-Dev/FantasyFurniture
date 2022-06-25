package xyz.apex.forge.fantasyfurniture.client.screen;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import xyz.apex.forge.apexcore.revamp.client.screen.BaseMenuScreen;
import xyz.apex.forge.fantasyfurniture.container.SetLockboxContainer;
import xyz.apex.forge.fantasyfurniture.init.FFElements;

public final class SetLockboxContainerScreen extends BaseMenuScreen<SetLockboxContainer>
{
	public SetLockboxContainerScreen(SetLockboxContainer menu, Inventory playerInventory, Component titleComponent)
	{
		super(menu, playerInventory, titleComponent, FFElements.SMALL_STORAGE_TEXTURE);
	}

	@Override
	protected void init()
	{
		imageWidth = 176;
		imageHeight = 166;

		super.init();
	}
}
