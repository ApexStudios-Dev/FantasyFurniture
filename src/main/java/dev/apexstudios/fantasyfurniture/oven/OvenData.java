package dev.apexstudios.fantasyfurniture.oven;

import com.google.common.collect.Lists;
import dev.apexstudios.apexcore.lib.component.block.entity.BlockEntityComponentTypes;
import dev.apexstudios.fantasyfurniture.block.OvenBlock;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import java.util.Collections;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.crafting.SmokingRecipe;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.Nullable;
import org.joml.Math;

public final class OvenData implements ContainerData, RecipeCraftingHolder {
    public static final String NBT_COOKING_TIMER = "cooking_time_spent";
    public static final String NBT_COOKING_TOTAL_TIME = "cooking_total_time";
    public static final String NBT_LIT_TIME_REMAINING = "lit_time_remaining";
    public static final String NBT_LIT_TOTAL_TIME = "lit_total_time";
    public static final String NBT_RECIPES_USED = "RecipesUsed";

    private int litTimeRemaining;
    private int litTotalTime;
    int cookingTimer;
    int cookingTotalTime;
    private final Reference2IntOpenHashMap<ResourceKey<Recipe<?>>> recipesUsed = new Reference2IntOpenHashMap<>();
    private final RecipeManager.CachedCheck<SingleRecipeInput, SmokingRecipe> quickCheck = RecipeManager.createCheck(RecipeType.SMOKING);

    void serverTick(ServerLevel level, BlockPos pos, BlockState blockState, OvenBlockEntity oven) {
        var inventory = oven.getComponentOrThrow(BlockEntityComponentTypes.INVENTORY).getItemHandler();
        var wasLit = isLit();
        var changed = false;

        if (isLit())
            litTimeRemaining--;

        var fuel = inventory.getStackInSlot(OvenBlockEntity.SLOT_FUEL);
        var input = inventory.getStackInSlot(OvenBlockEntity.SLOT_INPUT);
        var hasNoInput = !input.isEmpty();
        var hasNoFuel = !fuel.isEmpty();

        if (isLit() || hasNoFuel && hasNoInput) {
            var singlerecipeinput = new SingleRecipeInput(input);
            var recipeholder = hasNoInput ? quickCheck.getRecipeFor(singlerecipeinput, level).orElse(null) : null;

            if (!isLit() && canBurn(level.registryAccess(), recipeholder, singlerecipeinput, inventory)) {
                litTimeRemaining = fuel.getBurnTime(RecipeType.SMOKING, level.fuelValues());
                litTotalTime = litTimeRemaining;

                if (isLit()) {
                    changed = true;
                    var remainder = fuel.getCraftingRemainder();

                    if (!remainder.isEmpty())
                        inventory.setStackInSlot(OvenBlockEntity.SLOT_FUEL, remainder);
                    else if (hasNoFuel) {
                        var item = fuel.getItem();
                        fuel.shrink(1);

                        if (fuel.isEmpty())
                            inventory.setStackInSlot(OvenBlockEntity.SLOT_FUEL, item.getCraftingRemainder());
                    }
                }
            }

            if (isLit() && canBurn(level.registryAccess(), recipeholder, singlerecipeinput, inventory)) {
                cookingTimer++;

                if (cookingTimer == cookingTotalTime) {
                    cookingTimer = AbstractFurnaceBlockEntity.DEFAULT_COOKING_TIMER;
                    cookingTotalTime = getTotalCookTime(level, inventory);

                    if (burn(level.registryAccess(), recipeholder, singlerecipeinput, inventory))
                        setRecipeUsed(recipeholder);

                    changed = true;
                }
            } else {
                cookingTimer = AbstractFurnaceBlockEntity.DEFAULT_COOKING_TIMER;
            }
        } else if (!isLit() && cookingTimer > 0) {
            cookingTimer = Mth.clamp(cookingTimer - AbstractFurnaceBlockEntity.BURN_COOL_SPEED, 0, cookingTotalTime);
        }

        if (wasLit != isLit()) {
            changed = true;
            level.setBlock(pos, blockState.setValue(OvenBlock.LIT, isLit()), Block.UPDATE_ALL);
        }

        if (changed)
            oven.setChanged();
    }

    void save(CompoundTag tag) {
        tag.putInt(NBT_COOKING_TIMER, cookingTimer);
        tag.putInt(NBT_COOKING_TOTAL_TIME, cookingTotalTime);
        tag.putInt(NBT_LIT_TIME_REMAINING, litTimeRemaining);
        tag.putInt(NBT_LIT_TOTAL_TIME, litTotalTime);

        tag.store(NBT_RECIPES_USED, AbstractFurnaceBlockEntity.RECIPES_USED_CODEC, recipesUsed);
    }

    void load(CompoundTag tag) {
        cookingTimer = tag.getIntOr(NBT_COOKING_TIMER, AbstractFurnaceBlockEntity.DEFAULT_COOKING_TIMER);
        cookingTotalTime = tag.getIntOr(NBT_COOKING_TOTAL_TIME, AbstractFurnaceBlockEntity.DEFAULT_COOKING_TOTAL_TIME);
        litTimeRemaining = tag.getIntOr(NBT_LIT_TIME_REMAINING, AbstractFurnaceBlockEntity.DEFAULT_LIT_TIME_REMAINING);
        litTotalTime = tag.getIntOr(NBT_LIT_TOTAL_TIME, AbstractFurnaceBlockEntity.DEFAULT_LIT_TOTAL_TIME);

        recipesUsed.clear();
        recipesUsed.putAll(tag.read(NBT_RECIPES_USED, AbstractFurnaceBlockEntity.RECIPES_USED_CODEC).orElseGet(Collections::emptyMap));
    }

    @Override
    public int get(int index) {
        return switch (index) {
            case AbstractFurnaceBlockEntity.DATA_LIT_TIME -> {
                if(litTotalTime > Short.MAX_VALUE)
                    yield Mth.floor(((double) litTimeRemaining / litTotalTime) * Short.MAX_VALUE);

                yield litTimeRemaining;
            }

            case AbstractFurnaceBlockEntity.DATA_LIT_DURATION -> Math.min(litTotalTime, Short.MAX_VALUE);
            case AbstractFurnaceBlockEntity.DATA_COOKING_PROGRESS -> cookingTimer;
            case AbstractFurnaceBlockEntity.DATA_COOKING_TOTAL_TIME -> cookingTotalTime;

            default -> -1;
        };
    }

    @Override
    public void set(int index, int value) {
        switch (index) {
            case AbstractFurnaceBlockEntity.DATA_LIT_TIME -> litTimeRemaining = value;
            case AbstractFurnaceBlockEntity.DATA_LIT_DURATION -> litTotalTime = value;
            case AbstractFurnaceBlockEntity.DATA_COOKING_PROGRESS -> cookingTimer = value;
            case AbstractFurnaceBlockEntity.DATA_COOKING_TOTAL_TIME -> cookingTotalTime = value;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public void setRecipeUsed(@Nullable RecipeHolder<?> recipe) {
        if(recipe != null)
            recipesUsed.addTo(recipe.id(), 1);
    }

    @Nullable
    @Override
    public RecipeHolder<?> getRecipeUsed() {
        return null;
    }

    private boolean isLit() {
        return litTimeRemaining > 0;
    }

    private boolean canBurn(RegistryAccess registryAccess, @Nullable RecipeHolder<? extends AbstractCookingRecipe> recipe, SingleRecipeInput recipeInput, IItemHandler items) {
        if (!items.getStackInSlot(OvenBlockEntity.SLOT_INPUT).isEmpty() && recipe != null) {
            var result = recipe.value().assemble(recipeInput, registryAccess);

            if (result.isEmpty())
                return false;

            var output = items.getStackInSlot(OvenBlockEntity.SLOT_OUTPUT);

            if (output.isEmpty())
                return true;
            if (!ItemStack.isSameItemSameComponents(output, result))
                return false;

            var slotLimit = items.getSlotLimit(OvenBlockEntity.SLOT_OUTPUT);

            return output.getCount() + result.getCount() <= slotLimit &&
                    (output.getCount() + result.getCount() <= output.getMaxStackSize() ||
                            output.getCount() + result.getCount() <= result.getMaxStackSize());
        }

        return false;
    }

    private boolean burn(RegistryAccess registryAccess, @Nullable RecipeHolder<? extends AbstractCookingRecipe> recipe, SingleRecipeInput recipeInput, IItemHandlerModifiable items) {
        if (recipe != null && canBurn(registryAccess, recipe, recipeInput, items)) {
            var input = items.getStackInSlot(OvenBlockEntity.SLOT_INPUT);
            var result = recipe.value().assemble(recipeInput, registryAccess);
            var output = items.getStackInSlot(OvenBlockEntity.SLOT_OUTPUT);
            var fuel = items.getStackInSlot(OvenBlockEntity.SLOT_FUEL);

            if (output.isEmpty())
                items.setStackInSlot(OvenBlockEntity.SLOT_OUTPUT, result.copy());
            else if (ItemStack.isSameItemSameComponents(output, result))
                output.grow(result.getCount());

            if (input.is(Blocks.WET_SPONGE.asItem()) && !fuel.isEmpty() && fuel.is(Items.BUCKET))
                items.setStackInSlot(OvenBlockEntity.SLOT_FUEL, new ItemStack(Items.WATER_BUCKET));

            input.shrink(1);
            return true;
        }

        return false;
    }

    int getTotalCookTime(ServerLevel level, IItemHandler itemHandler) {
        var input = new SingleRecipeInput(itemHandler.getStackInSlot(OvenBlockEntity.SLOT_INPUT));
        return quickCheck.getRecipeFor(input, level).map(recipe -> recipe.value().cookingTime()).orElse(AbstractFurnaceBlockEntity.BURN_TIME_STANDARD);
    }

    public void awardUsedRecipesAndPopExperience(ServerPlayer player, OvenBlockEntity oven) {
        var recipes = getRecipesToAwardAndPopExperience(player.serverLevel(), player.position());
        var inventory = oven.getComponentOrThrow(BlockEntityComponentTypes.INVENTORY).getItems();

        player.awardRecipes(recipes);

        recipes.forEach(recipe -> {
            if (recipe != null)
                player.triggerRecipeCrafted(recipe, inventory);
        });

        recipesUsed.clear();
    }

    public List<RecipeHolder<?>> getRecipesToAwardAndPopExperience(ServerLevel level, Vec3 popVec) {
        var list = Lists.<RecipeHolder<?>>newArrayList();

        recipesUsed.reference2IntEntrySet().forEach(entry -> level.recipeAccess().byKey(entry.getKey()).ifPresent(recipe -> {
            list.add(recipe);
            createExperience(level, popVec, entry.getIntValue(), ((AbstractCookingRecipe) recipe.value()).experience());
        }));

        return list;
    }

    private void createExperience(ServerLevel level, Vec3 popVec, int recipeIndex, float experience) {
        var i = Mth.floor((float)recipeIndex * experience);
        var f = Mth.frac((float)recipeIndex * experience);

        if (f != 0.0F && Math.random() < (double)f)
            i++;

        ExperienceOrb.award(level, popVec, i);
    }
}
