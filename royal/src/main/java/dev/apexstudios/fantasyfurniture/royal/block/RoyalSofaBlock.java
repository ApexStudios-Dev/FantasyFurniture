package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.apexcore.lib.block.Dyeable;
import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.SofaBlock;
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

public final class RoyalSofaBlock extends SofaBlock {
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
}
