package dev.apexstudios.fantasyfurniture.common.station;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Runnables;
import java.util.List;
import java.util.function.Function;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jspecify.annotations.Nullable;

public final class FurnitureStationMenu extends AbstractContainerMenu {
    private final ContainerLevelAccess levelAccess;
    private final Player player;

    private final Container inputContainer = new SimpleContainer(FurnitureStationSetup.SLOTS);
    private final Container resultContainer = new ResultContainer();

    private final DataSlot selectedRecipe = DataSlot.standalone();
    private Runnable listener = Runnables.doNothing();
    private long lastSoundTime = 0L;
    private final List<FurnitureStationRecipe> recipes = Lists.newArrayList();

    FurnitureStationMenu(int windowId, Inventory inventory, ContainerLevelAccess levelAccess) {
        super(FurnitureStationSetup.MENU.value(), windowId);

        this.levelAccess = levelAccess;
        player = inventory.player;

        var slotX = 8;

        addSlot(new InputSlot(FurnitureStationSetup.SLOT_PLANKS, slotX + 8 + SLOT_SIZE, FurnitureStationRecipe::planks));
        addSlot(new InputSlot(FurnitureStationSetup.SLOT_WOOL, slotX + 8 + SLOT_SIZE * 2, recipe -> recipe.wool().orElse(null)));
        addSlot(new InputSlot(FurnitureStationSetup.SLOT_BINDING_AGENT, slotX, FurnitureStationRecipe::bindingAgent));

        addSlot(new Slot(resultContainer, 0, 150, 8) {
            @Override
            public void onTake(Player player, ItemStack stack) {
                stack.onCraftedBy(player, stack.getCount());

                inputContainer.removeItem(FurnitureStationSetup.SLOT_PLANKS, 1);
                inputContainer.removeItem(FurnitureStationSetup.SLOT_WOOL, 1);
                inputContainer.removeItem(FurnitureStationSetup.SLOT_BINDING_AGENT, 1);

                setupResultSlot();

                levelAccess.execute((level, pos) -> {
                    var gameTime = level.getGameTime();

                    if(lastSoundTime != gameTime) {
                        lastSoundTime = gameTime;
                        level.playSound(null, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1F, 1F);
                    }
                });

                super.onTake(player, stack);
            }

            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });

        addStandardInventorySlots(inventory, 8, 115);

        addDataSlot(selectedRecipe);

        selectedRecipe.set(-1);
    }

    FurnitureStationMenu(int windowId, Inventory inventory) {
        this(windowId, inventory, ContainerLevelAccess.NULL);
    }

    public int selectedRecipe() {
        return selectedRecipe.get();
    }

    public List<FurnitureStationRecipe> recipes() {
        return recipes;
    }

    public boolean hasInput() {
        return !inputContainer.isEmpty();
    }

    public void registerListener(Runnable listener) {
        this.listener = listener;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        var stack = ItemStack.EMPTY;
        var slot = slots.get(index);

        if (slot.hasItem()) {
            var stack2 = slot.getItem();
            var item = stack2.getItem();
            stack = stack2.copy();

            var result = FurnitureStationSetup.SLOT_BINDING_AGENT + 1;
            var playerStart = result + 1;
            var playerEnd = playerStart + (9 * 3) - 1;
            var hotbarStart = playerEnd + 1;
            var hotbarEnd = hotbarStart + 8;

            if (index == result) {
                item.onCraftedBy(stack2, player);

                if (!moveItemStackTo(stack2, playerStart, hotbarEnd, true))
                    return ItemStack.EMPTY;

                slot.onQuickCraft(stack2, stack);
            } else if (index >= FurnitureStationSetup.SLOT_PLANKS && index < result) {
                if (!moveItemStackTo(stack2, playerStart, hotbarEnd, false))
                    return ItemStack.EMPTY;
            } else if (index >= playerStart && index < hotbarStart) {
                if (!moveItemStackTo(stack2, FurnitureStationSetup.SLOT_PLANKS, result, false))
                    return ItemStack.EMPTY;
                if (!moveItemStackTo(stack2, hotbarStart, hotbarEnd, false))
                    return ItemStack.EMPTY;
            } else if (index >= hotbarStart && index < hotbarEnd) {
                if (!moveItemStackTo(stack2, FurnitureStationSetup.SLOT_PLANKS, result, false))
                    return ItemStack.EMPTY;
                if (!moveItemStackTo(stack2, playerStart, playerEnd, false))
                    return ItemStack.EMPTY;
            }

            if (stack2.isEmpty())
                slot.setByPlayer(ItemStack.EMPTY);

            slot.setChanged();

            if (stack2.getCount() == stack.getCount())
                return ItemStack.EMPTY;

            slot.onTake(player, stack2);
            broadcastChanges();
        }

        return stack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(levelAccess, player, FurnitureStationSetup.BLOCK.value());
    }

    @Override
    public boolean clickMenuButton(Player player, int id) {
        if(isValidRecipeIndex(recipes, id)) {
            selectedRecipe.set(id);
            setupResultSlot();
        }

        return true;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);

        resultContainer.setItem(0, ItemStack.EMPTY);
        clearContainer(player, inputContainer);
    }

    private FurnitureStationRecipeInput asInput() {
        return new FurnitureStationRecipeInput(
                inputContainer.getItem(FurnitureStationSetup.SLOT_PLANKS),
                inputContainer.getItem(FurnitureStationSetup.SLOT_WOOL),
                inputContainer.getItem(FurnitureStationSetup.SLOT_BINDING_AGENT)
        );
    }

    private void setupRecipes() {
        resultContainer.setItem(0, ItemStack.EMPTY);
        selectedRecipe.set(-1);
        recipes.clear();
        FurnitureStationSetup.recipes(asInput(), player.level()).map(RecipeHolder::value).forEach(recipes::add);
        broadcastChanges();
    }

    private void setupResultSlot() {
        var index = selectedRecipe();
        resultContainer.setItem(0, ItemStack.EMPTY);

        if(hasInput() && isValidRecipeIndex(recipes, index)) {
            var recipe = recipes.get(index);
            var result = recipe.assemble(asInput());
            resultContainer.setItem(0, result);
        } else {
            setupRecipes();
        }

        broadcastChanges();
    }

    public static boolean isValidRecipeIndex(List<FurnitureStationRecipe> recipes, int index) {
        return index >= 0 && index < recipes.size();
    }

    private class InputSlot extends Slot {
        private final Function<FurnitureStationRecipe, @Nullable Ingredient> ingredientGetter;

        private InputSlot(int index, int x, Function<FurnitureStationRecipe, @Nullable Ingredient> ingredientGetter) {
            super(inputContainer, index, x, 8);

            this.ingredientGetter = ingredientGetter;
        }

        @Override
        public void setChanged() {
            super.setChanged();
            setupRecipes();
            listener.run();
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            if(!(player instanceof ServerPlayer sPlayer))
                return false;

            var recipes = sPlayer.level().recipeAccess().recipeMap().byType(FurnitureStationSetup.RECIPE_TYPE.value());

            for(var holder : recipes) {
                var ingredient = ingredientGetter.apply(holder.value());

                if(ingredient != null && ingredient.test(stack))
                    return true;
            }

            return false;
        }
    }
}
