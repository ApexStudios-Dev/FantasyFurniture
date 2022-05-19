package xyz.apex.forge.fantasyfurniture.client.screen;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

import xyz.apex.forge.fantasyfurniture.container.SetChestContainer;
import xyz.apex.forge.fantasyfurniture.init.FFElements;

public final class SetChestContainerScreen extends InventoryContainerScreen<SetChestContainer>
{
	public SetChestContainerScreen(SetChestContainer menu, PlayerInventory playerInventory, ITextComponent titleComponent)
	{
		super(menu, playerInventory, titleComponent, FFElements.LARGE_STORAGE_TEXTURE);
	}

	@Override
	protected void init()
	{
		imageWidth = 176;
		imageHeight = 222;

		super.init();

		inventoryLabelY = imageHeight - 94;
	}
}
