package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.apexcore.lib.block.Dyeable;
import dev.apexstudios.apexcore.lib.multiblock.MultiBlock;
import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.ChairBlock;
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

public final class RoyalChairBlock extends ChairBlock implements Dyeable.Colored {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(1D, 0D, 1D, 3D, 3D, 4D),
            box(1D, 0D, 12D, 3D, 3D, 15D),
            box(13D, 0D, 12D, 15D, 3D, 15D),
            box(13D, 0D, 1D, 15D, 3D, 4D),
            box(13D, 3D, 2D, 15D, 5D, 4D),
            box(13D, 3D, 12D, 15D, 5D, 14D),
            box(1D, 3D, 12D, 3D, 5D, 14D),
            box(1D, 3D, 2D, 3D, 5D, 4D),
            box(1D, 5D, 1.5D, 15D, 7D, 14.5D),
            box(1D, 7D, 2D, 15D, 9D, 14D),
            box(13D, 9D, 2D, 15D, 12D, 4D),
            box(1D, 9D, 2D, 3D, 12D, 4D),
            box(1D, 12D, 1D, 3D, 15D, 4D),
            box(13D, 12D, 1D, 15D, 15D, 4D),
            box(13D, 12D, 4D, 15D, 14D, 12D),
            box(1D, 12D, 4D, 3D, 14D, 12D),
            box(1D, 7D, 12D, 15D, 14D, 14D),
            box(2D, 14D, 12D, 14D, 20D, 14D),
            box(1D, 20D, 12D, 15D, 25D, 14D),
            box(3D, 25D, 12D, 13D, 26D, 14D),
            box(5D, 26D, 12D, 11D, 27D, 14D)
    );

    public RoyalChairBlock(Properties properties) {
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
