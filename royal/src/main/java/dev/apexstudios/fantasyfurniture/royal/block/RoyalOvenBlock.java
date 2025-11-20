package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.apexcore.lib.block.Dyeable;
import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.OvenBlock;
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

public final class RoyalOvenBlock extends OvenBlock {
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
