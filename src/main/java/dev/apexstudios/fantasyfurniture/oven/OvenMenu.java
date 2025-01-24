package dev.apexstudios.fantasyfurniture.oven;

import dev.apexstudios.fantasyfurniture.FurnitureMenus;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.crafting.RecipePropertySet;
import net.minecraft.world.item.crafting.RecipeType;

public final class OvenMenu extends AbstractFurnaceMenu {
    public OvenMenu(int windowId, Inventory inventory, Container container, ContainerData data) {
        super(FurnitureMenus.OVEN.value(), RecipeType.SMOKING, RecipePropertySet.SMOKER_INPUT, RecipeBookType.SMOKER, windowId, inventory, container, data);
    }

    public OvenMenu(int windowId, Inventory inventory, OvenBlockEntity oven) {
        this(windowId, inventory, new OvenMenuContainer(oven), oven.data);
    }
}
