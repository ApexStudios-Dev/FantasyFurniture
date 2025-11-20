package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.apexcore.lib.block.Dyeable;
import dev.apexstudios.fantasyfurniture.block.FurnitureDoorBlock;
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
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.Nullable;

public final class RoyalDoorBlock extends FurnitureDoorBlock implements Dyeable {
    public RoyalDoorBlock(Properties properties, BlockSetType blockSet) {
        super(properties, blockSet);

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
        var half = blockState.getValue(HALF);
        var otherPos = half == DoubleBlockHalf.UPPER ? pos.below() : pos.above();
        var otherBlockState = level.getBlockState(otherPos);

        Dyeable.super.setDyedColor(level, pos, blockState, color);

        if(otherBlockState.is(blockState.getBlock()))
            Dyeable.super.setDyedColor(level, otherPos, otherBlockState, color);
    }
}
