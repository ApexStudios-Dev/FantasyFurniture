package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.apexcore.lib.block.Dyeable;
import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.ShelfBlock;
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
import org.jspecify.annotations.Nullable;

public final class RoyalShelfBlock extends ShelfBlock {
    public static final VoxelShape LEFT_SHAPE = ApexShapes.join(
            box(13D, 11D, 3D, 15D, 14D, 6D),
            box(13D, 12D, 6D, 15D, 14D, 16D),
            box(13D, 8D, 14D, 15D, 12D, 16D),
            box(13D, 5D, 13D, 15D, 8D, 16D)
    );

    public static final VoxelShape RIGHT_SHAPE = ApexShapes.join(
            box(1D, 11D, 3D, 3D, 14D, 6D),
            box(1D, 12D, 6D, 3D, 14D, 16D),
            box(1D, 8D, 14D, 3D, 12D, 16D),
            box(1D, 5D, 13D, 3D, 8D, 16D)
    );

    public static final VoxelShape TOP_SHAPE = box(0D, 14D, 0D, 16D, 16D, 16D);

    public RoyalShelfBlock(Properties properties) {
        super(properties, LEFT_SHAPE, RIGHT_SHAPE, TOP_SHAPE);

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
        return Dyeable.useItemOn(level, pos, blockState, stack);
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
