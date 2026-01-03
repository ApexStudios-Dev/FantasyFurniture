package dev.apexstudios.fantasyfurniture.royal.common.block;

import dev.apexstudios.apexcore.api.block.Dyeable;
import dev.apexstudios.apexcore.api.multiblock.MultiBlock;
import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.BedDoubleBlock;
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

public final class RoyalBedDoubleBlock extends BedDoubleBlock implements Dyeable.Colored {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(-16D, 0D, 0D, -13D, 3D, 2D),
            box(-15D, 3D, 0D, -13D, 9D, 2D),
            box(-16D, 9D, 0D, -13D, 12D, 2D),
            box(-16D, 9D, 30D, -13D, 12D, 32D),
            box(-16D, 0D, 30D, -13D, 3D, 32D),
            box(-15D, 3D, 30D, -13D, 9D, 32D),
            box(13D, 9D, 30D, 16D, 12D, 32D),
            box(13D, 0D, 30D, 16D, 3D, 32D),
            box(13D, 3D, 30D, 15D, 9D, 32D),
            box(13D, 9D, 0D, 16D, 12D, 2D),
            box(13D, 0D, 0D, 16D, 3D, 2D),
            box(13D, 3D, 0D, 15D, 9D, 2D),
            box(-15D, 3D, 2D, 15D, 5D, 32D),
            box(-13D, 2D, 30D, -12D, 3D, 32D),
            box(12D, 2D, 30D, 13D, 3D, 32D),
            box(12D, 2D, 0D, 13D, 3D, 2D),
            box(-13D, 2D, 0D, -12D, 3D, 2D),
            box(-14D, 3D, 0D, 14D, 15D, 2D),
            box(-14D, 3D, 30D, 14D, 15D, 32D),
            box(-14D, 3D, 2D, 14D, 8D, 30D)
    );

    public RoyalBedDoubleBlock(Properties properties) {
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
        MultiBlock.forEachPos(pos, blockState, (otherPos, otherBlockState) -> {
            if(otherBlockState.is(blockState.getBlock()))
                Dyeable.Colored.super.setDyedColor(level, otherPos, otherBlockState, color);
        });
    }
}
