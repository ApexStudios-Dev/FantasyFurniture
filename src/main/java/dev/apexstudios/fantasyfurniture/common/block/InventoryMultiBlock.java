package dev.apexstudios.fantasyfurniture.common.block;

import dev.apexstudios.apexcore.api.block.entity.InventoryBlockEntity;
import dev.apexstudios.apexcore.api.multiblock.MultiBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.neoforged.neoforge.transfer.ResourceHandlerUtil;
import org.jspecify.annotations.Nullable;

public abstract class InventoryMultiBlock extends InventoryBlock implements MultiBlock {
    public InventoryMultiBlock(Properties properties) {
        super(properties);

        registerDefaultState(defaultBlockState().setValue(getMultiBlockProperty(), 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(getMultiBlockProperty());
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        var placementBlockState = super.getStateForPlacement(context);

        if(placementBlockState == null || !MultiBlock.canPlace(context, placementBlockState))
            return null;

        return placementBlockState;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState blockState, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, blockState, placer, stack);
        MultiBlock.setBlockStates(level, pos, blockState);
    }

    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState blockState) {
        super.destroy(level, pos, blockState);
        MultiBlock.destroyBlocks(level, pos, blockState);
    }

    // region: BlockEntity
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState blockState) {
        return blockState.getValue(getMultiBlockProperty()) == 0 ? super.newBlockEntity(pos, blockState) : null;
    }

    @Override
    protected void affectNeighborsAfterRemoval(BlockState blockState, ServerLevel level, BlockPos pos, boolean movedByPiston) {
        super.affectNeighborsAfterRemoval(blockState, level, pos, movedByPiston);

        var property = getMultiBlockProperty();
        int index = blockState.getValue(property);

        MultiBlock.forEachPos(pos, blockState, (otherPos, otherBlockState) -> {
            if(index != otherBlockState.getValue(property)) // 'index' is updated via super
                level.updateNeighbourForOutputSignal(otherPos, otherBlockState.getBlock());
        });
    }

    @Override
    protected int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos pos, Direction facing) {
        var blockEntity = MultiBlock.getBlockEntity(level, pos, blockState);
        return blockEntity instanceof InventoryBlockEntity inventory ? ResourceHandlerUtil.getRedstoneSignalFromResourceHandler(inventory.getResourceHandler()) : super.getAnalogOutputSignal(blockState, level, pos, facing);
    }

    @Override
    protected boolean triggerEvent(BlockState blockState, Level level, BlockPos pos, int id, int param) {
        var blockEntity = MultiBlock.getBlockEntity(level, pos, blockState);
        return blockEntity != null && blockEntity.triggerEvent(id, param);
    }

    @Nullable
    @Override
    protected MenuProvider getMenuProvider(BlockState blockState, Level level, BlockPos pos) {
        return MultiBlock.getBlockEntity(level, pos, blockState) instanceof MenuProvider provider ? provider : null;
    }
    // endregion
}
