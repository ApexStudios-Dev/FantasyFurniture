package dev.apexstudios.fantasyfurniture.royal.common.block;

import dev.apexstudios.apexcore.api.block.Dyeable;
import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.OvenBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public final class RoyalOvenBlock extends OvenBlock implements Dyeable.Colored {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(0D, 0D, 0D, 3.5D, 4D, 3.5D),
            box(0D, 0D, 12.5D, 3.5D, 4D, 16D),
            box(12.5D, 0D, 12.5D, 16D, 4D, 16D),
            box(12.5D, 0D, 0D, 16D, 4D, 3.5D),
            box(0D, 4D, 0D, 16D, 6D, 16D),
            box(0D, 14D, 0D, 16D, 16D, 16D),
            box(.5D, 6D, .5D, 15.5D, 14D, 15.5D),
            box(2.5D, 7D, -.5D, 12.5D, 13D, .5D)
    );

    public RoyalOvenBlock(Properties properties) {
        super(properties, SHAPE);

        registerDefaultState(setDyedColor(defaultBlockState(), Dyeable.DyedColor.WHITE));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder.add(DYED_COLOR));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        var placementBlockState = super.getStateForPlacement(context);
        return placementBlockState == null ? null : setDyedColor(placementBlockState, getDyedColorForPlacement(context));
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        var result = tryDyeBlock(stack, blockState, level, pos, player);

        if(!result.consumesAction()) {
            result = super.useItemOn(stack, blockState, level, pos, player, hand, hitResult);
        }

        return result;
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState blockState, boolean includeData, Player player) {
        var stack = new ItemStack(this);
        appendDyedColor(stack, blockState, player, includeData);
        return stack;
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState blockState, boolean includeData) {
        var stack = new ItemStack(this);
        appendDyedColor(stack, blockState, null, includeData);
        return stack;
    }
}
