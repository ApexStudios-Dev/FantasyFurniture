package dev.apexstudios.fantasyfurniture.station;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record FurnitureStationRecipeInput(ItemStack planks, ItemStack wool, ItemStack bindingAgent) implements RecipeInput {
    @Override
    public ItemStack getItem(int index) {
        return switch (index) {
            case FurnitureStationSetup.SLOT_PLANKS -> planks;
            case FurnitureStationSetup.SLOT_WOOL -> wool;
            case FurnitureStationSetup.SLOT_BINDING_AGENT -> bindingAgent;
            default -> throw new IllegalArgumentException("Recipe does not contain slot: " + index);
        };
    }

    @Override
    public int size() {
        return FurnitureStationSetup.SLOTS;
    }
}
