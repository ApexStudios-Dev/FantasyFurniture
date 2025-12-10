package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.apexcore.lib.block.Dyeable;
import dev.apexstudios.fantasyfurniture.block.TableBlock;
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

public final class RoyalTableBlock extends TableBlock implements Dyeable.Colored {
    public static final VoxelShape SHAPE_TABLE_TOP = box(0D, 14D, 0D, 16D, 16D, 16D);
    public static final VoxelShape SHAPE_TABLE_LEG = box(11D, 0D, 1D, 15D, 14D, 5D);

    public RoyalTableBlock(Properties properties) {
        super(properties);

        registerDefaultState(setDyedColor(defaultBlockState(), Dyeable.DyedColor.WHITE));
    }

    @Override
    protected VoxelShape getLegShape() {
        return SHAPE_TABLE_LEG;
    }

    @Override
    protected VoxelShape getTopShape() {
        return SHAPE_TABLE_TOP;
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
