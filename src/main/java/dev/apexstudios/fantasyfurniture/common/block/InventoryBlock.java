package dev.apexstudios.fantasyfurniture.common.block;

import com.mojang.serialization.MapCodec;
import dev.apexstudios.apexcore.api.block.SimpleHorizontalDirectionalBlock;
import dev.apexstudios.apexcore.api.block.entity.InventoryBlockEntity;
import dev.apexstudios.fantasyfurniture.common.block.entity.FurnitureInventoryBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.transfer.ResourceHandlerUtil;

public class InventoryBlock extends BaseEntityBlock {
    public static final MapCodec<InventoryBlock> CODEC = simpleCodec(InventoryBlock::new);
    public static final EnumProperty<Direction> FACING = SimpleHorizontalDirectionalBlock.FACING;

    public InventoryBlock(Properties properties) {
        super(properties);

        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState blockState) {
        return new FurnitureInventoryBlockEntity(pos, blockState);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        var menuProvider = blockState.getMenuProvider(level, pos);

        if(menuProvider != null) {
            player.openMenu(menuProvider);

            if(level instanceof ServerLevel sLevel)
                PiglinAi.angerNearbyPiglins(sLevel, player, true);

            return InteractionResult.SUCCESS;
        }

        return super.useWithoutItem(blockState, level, pos, player, hitResult);
    }

    @Override
    protected void affectNeighborsAfterRemoval(BlockState blockState, ServerLevel level, BlockPos pos, boolean movedByPiston) {
        super.affectNeighborsAfterRemoval(blockState, level, pos, movedByPiston);
        Containers.updateNeighboursAfterDestroy(blockState, level, pos);
    }

    @Override
    protected boolean hasAnalogOutputSignal(BlockState blockState) {
        return true;
    }

    @Override
    protected int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos pos, Direction facing) {
        var itemHandler = level.getBlockEntity(pos) instanceof InventoryBlockEntity blockEntity ? blockEntity.getResourceHandler() : null;
        return itemHandler == null ? super.getAnalogOutputSignal(blockState, level, pos, facing) : ResourceHandlerUtil.getRedstoneSignalFromResourceHandler(itemHandler);
    }

    // region: Facing
    @Override
    protected BlockState rotate(BlockState blockState, Rotation rot) {
        return blockState.setValue(FACING, rot.rotate(blockState.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState blockState, Mirror mirror) {
        return blockState.rotate(mirror.getRotation(blockState.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
    // endregion
}
