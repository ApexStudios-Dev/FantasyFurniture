package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.apexcore.lib.block.Dyeable;
import dev.apexstudios.apexcore.lib.multiblock.MultiBlock;
import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.WardrobeBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
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

public final class RoyalWardrobeBlock extends WardrobeBlock implements Dyeable {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(12D, 0D, 2D, 14D, 3D, 5D),
            box(-14D, 0D, 2D, -12D, 3D, 5D),
            box(-14D, 0D, 11D, -12D, 3D, 14D),
            box(12D, 0D, 11D, 14D, 3D, 14D),
            box(12D, 3D, 11D, 14D, 4D, 13D),
            box(12D, 3D, 3D, 14D, 4D, 5D),
            box(-14D, 3D, 3D, -12D, 4D, 5D),
            box(-14D, 3D, 11D, -12D, 4D, 13D),
            box(-15D, 4D, 1D, 15D, 6D, 15D),
            box(12D, 6D, 2D, 14D, 30D, 4D),
            box(-14D, 6D, 2D, -12D, 30D, 4D),
            box(-14D, 6D, 12D, -12D, 30D, 14D),
            box(12D, 6D, 12D, 14D, 30D, 14D),
            box(-12D, 6D, 3D, 12D, 30D, 13D),
            box(-15D, 30D, 1D, 15D, 32D, 15D),
            box(12D, 32D, 2D, 14D, 46D, 4D),
            box(-14D, 32D, 2D, -12D, 46D, 4D),
            box(-14D, 32D, 12D, -12D, 46D, 14D),
            box(12D, 32D, 12D, 14D, 46D, 14D),
            box(-15D, 46D, 1D, 15D, 48D, 15D),
            box(-13D, 32D, 3D, 13D, 46D, 13D)
    );

    public RoyalWardrobeBlock(Properties properties) {
        super(properties, SHAPE);

        registerDefaultState(defaultBlockState().setValue(Dyeable.PROPERTY, Dyeable.DEFAULT_COLOR));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(Dyeable.PROPERTY);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        var placementBlockState = super.getStateForPlacement(context);

        if(placementBlockState == null)
            return null;

        var color = Dyeable.getColorForPlacement(context);
        return placementBlockState.setValue(Dyeable.PROPERTY, color);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        var result = Dyeable.useItemOn(level, pos, blockState, stack);

        if(result.consumesAction())
            return result;

        return super.useItemOn(stack, blockState, level, pos, player, hand, hitResult);
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState blockState, boolean includeData, Player player) {
        return Dyeable.getCloneStack(this, blockState, player, includeData);
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState blockState, boolean includeData) {
        return Dyeable.getCloneStack(this, blockState, null, includeData);
    }

    @Override
    public void setDyedColor(Level level, BlockPos pos, BlockState blockState, DyeColor color) {
        MultiBlock.forEachPos(pos, blockState, (otherPos, otherBlockState) -> {
            if(otherBlockState.is(blockState.getBlock()))
                Dyeable.super.setDyedColor(level, otherPos, otherBlockState, color);
        });
    }
}
