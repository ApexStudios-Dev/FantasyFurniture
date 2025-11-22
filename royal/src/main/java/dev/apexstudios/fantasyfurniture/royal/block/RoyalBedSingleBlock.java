package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.apexcore.lib.block.Dyeable;
import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.BedSingleBlock;
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

public final class RoyalBedSingleBlock extends BedSingleBlock implements Dyeable.Colored {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(0D, 0D, 0D, 3D, 3D, 2D),
            box(1D, 3D, 0D, 3D, 9D, 2D),
            box(0D, 9D, 0D, 3D, 12D, 2D),
            box(0D, 9D, 30D, 3D, 12D, 32D),
            box(0D, 0D, 30D, 3D, 3D, 32D),
            box(1D, 3D, 30D, 3D, 9D, 32D),
            box(13D, 9D, 30D, 16D, 12D, 32D),
            box(13D, 0D, 30D, 16D, 3D, 32D),
            box(13D, 3D, 30D, 15D, 9D, 32D),
            box(13D, 9D, 0D, 16D, 12D, 2D),
            box(13D, 0D, 0D, 16D, 3D, 2D),
            box(13D, 3D, 0D, 15D, 9D, 2D),
            box(1D, 3D, 2D, 15D, 5D, 32D),
            box(3D, 2D, 30D, 4D, 3D, 32D),
            box(12D, 2D, 30D, 13D, 3D, 32D),
            box(12D, 2D, 0D, 13D, 3D, 2D),
            box(3D, 2D, 0D, 4D, 3D, 2D),
            box(2D, 3D, 0D, 14D, 15D, 2D),
            box(2D, 3D, 30D, 14D, 15D, 32D),
            box(2D, 3D, 2D, 14D, 8D, 30D)
    );

    public RoyalBedSingleBlock(Properties properties) {
        super(properties, SHAPE);

        registerDefaultState(setDyedColor(defaultBlockState(), DyedColor.WHITE));
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

    @Override
    public void setDyedColor(Level level, BlockPos pos, BlockState blockState, DyedColor color) {
        var direction = getConnectedDirection(blockState);
        var otherPos = pos.relative(direction);
        var otherBlockState = level.getBlockState(otherPos);

        Dyeable.Colored.super.setDyedColor(level, pos, blockState, color);

        if(otherBlockState.is(blockState.getBlock()))
            Dyeable.Colored.super.setDyedColor(level, otherPos, otherBlockState, color);
    }
}
