package dev.apexstudios.fantasyfurniture.royal.common.block;

import dev.apexstudios.apexcore.api.block.Dyeable;
import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.SofaBlock;
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

public final class RoyalSofaBlock extends SofaBlock implements Dyeable.Colored {
    public static final VoxelShape LEFT_SHAPE = ApexShapes.join(
            box(13D, 0D, 1D, 15D, 3D, 4D),
            box(13D, 0D, 12D, 15D, 3D, 15D),
            box(13D, 3D, 12D, 15D, 4D, 14D),
            box(13D, 3D, 2D, 15D, 4D, 4D),
            box(0D, 4D, 1.5D, 15D, 6D, 14.5D),
            box(0D, 6D, 12D, 15D, 13D, 14D),
            box(0D, 13D, 12D, 14D, 15D, 14D),
            box(0D, 15D, 12D, 12D, 16D, 14D),
            box(13D, 6D, 2D, 15D, 9D, 12D)
    );

    public static final VoxelShape RIGHT_SHAPE = ApexShapes.join(
            box(1D, 0D, 1D, 3D, 3D, 4D),
            box(1D, 0D, 12D, 3D, 3D, 15D),
            box(1D, 3D, 12D, 3D, 4D, 14D),
            box(1D, 3D, 2D, 3D, 4D, 4D),
            box(1D, 4D, 1.5D, 16D, 6D, 14.5D),
            box(1D, 6D, 12D, 16D, 13D, 14D),
            box(2D, 13D, 12D, 16D, 15D, 14D),
            box(4D, 15D, 12D, 16D, 16D, 14D),
            box(1D, 6D, 2D, 3D, 9D, 12D)
    );

    public static final VoxelShape BOTH_SHAPE = ApexShapes.join(
            box(0D, 4D, 1.5D, 16D, 6D, 14.5D),
            box(0D, 6D, 12D, 16D, 16D, 14D)
    );

    public static final VoxelShape CORNER_SHAPE = ApexShapes.join(
            box(.5D, 0D, .5D, 4.25D, 4D, 4.25D),
            box(11.5D, 0D, 11.25D, 15.25D, 4D, 15D),
            box(12D, 0D, 1D, 15D, 3D, 3D),
            box(12D, 3D, 1D, 14D, 4D, 3D),
            box(1D, 3D, 12D, 3D, 4D, 14D),
            box(1D, 0D, 12D, 3D, 3D, 15D),
            box(0D, 4D, 1.5D, 1.5D, 6D, 14.5D),
            box(1.5D, 4D, 0D, 14.5D, 6D, 14.5D),
            box(12D, 6D, 0D, 14D, 16D, 14D),
            box(0D, 6D, 12D, 12D, 16D, 14D)
    );

    public static final VoxelShape SHAPE = ApexShapes.join(
            box(13D, 0D, 12D, 15D, 3D, 15D),
            box(13D, 0D, 1D, 15D, 3D, 4D),
            box(1D, 0D, 1D, 3D, 3D, 4D),
            box(1D, 0D, 12D, 3D, 3D, 15D),
            box(1D, 3D, 12D, 3D, 4D, 14D),
            box(13D, 3D, 12D, 15D, 4D, 14D),
            box(13D, 3D, 2D, 15D, 4D, 4D),
            box(1D, 3D, 2D, 3D, 4D, 4D),
            box(1D, 4D, 1.5D, 15D, 6D, 14.5D),
            box(13D, 6D, 2D, 15D, 9D, 12D),
            box(1D, 6D, 2D, 3D, 9D, 12D),
            box(1D, 6D, 12D, 15D, 13D, 14D),
            box(2D, 13D, 12D, 14D, 15D, 14D),
            box(4D, 15D, 12D, 12D, 16D, 14D)
    );

    public RoyalSofaBlock(Properties properties) {
        super(properties, LEFT_SHAPE, RIGHT_SHAPE, BOTH_SHAPE, CORNER_SHAPE, SHAPE);

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
