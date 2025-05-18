package dev.apexstudios.fantasyfurniture.dunmer.block;

import dev.apexstudios.apexcore.lib.multiblock.MultiBlock;
import dev.apexstudios.apexcore.lib.multiblock.MultiBlockProperties;
import dev.apexstudios.apexcore.lib.multiblock.MultiBlockProperty;
import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.OvenBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SmokerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public final class DunmerOvenBlock extends OvenBlock implements MultiBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(12.5D, 0D, 6.5D, 15.5D, 3D, 9.5D),
            box(-15.5D, 0D, 6.5D, -12.5D, 3D, 9.5D),
            box(-15D, 3D, 7D, -13D, 16D, 9D),
            box(13D, 3D, 7D, 15D, 16D, 9D),
            box(-16D, 12D, 7D, 16D, 14D, 9D),
            box(-5D, 10.5D, 5.5D, 5D, 15.5D, 10.5D),
            box(-6D, 0D, 2D, 6D, 3D, 14D)
    );

    public DunmerOvenBlock(Properties properties) {
        super(properties, SHAPE);

        registerDefaultState(defaultBlockState().setValue(getMultiBlockProperty(), 0));
    }

    @Override
    public MultiBlockProperty getMultiBlockProperty() {
        return MultiBlockProperties.MB_1x1x2;
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

    @Override
    protected void spawnDestroyParticles(Level level, Player player, BlockPos pos, BlockState blockState) {
        super.spawnDestroyParticles(level, player, pos, blockState);
        MultiBlock.spawnDestroyParticles(level, pos, blockState, player);
    }

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
    protected int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos pos) {
        var blockEntity = MultiBlock.getBlockEntity(level, pos, blockState);
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(blockEntity);
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

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level lvl, BlockState state, BlockEntityType<T> blockEntityType) {
        var vanillaTicker = state.getValue(getMultiBlockProperty()) == 0 ? super.getTicker(lvl, state, blockEntityType) : null;

        // simple hook to ensure 'lit' status is set for all multi block positions
        return vanillaTicker == null ? null : (level, pos, blockState, blockEntity) -> {
            var wasLit = blockState.getValue(LIT); // old lit status
            vanillaTicker.tick(level, pos, blockState, blockEntity);
            var isLit = level.getBlockState(pos).getValueOrElse(LIT, wasLit); // new lit status

            // only if it changed
            if(isLit == wasLit)
                return;

            // set all other positions to the new lit status
            var index = MultiBlock.getIndex(blockState);

            MultiBlock.forEachPos(pos, blockState, (otherPos, otherBlockState) -> {
                if(index != MultiBlock.getIndex(otherBlockState))
                    level.setBlockAndUpdate(otherPos, otherBlockState.setValue(LIT, isLit));
            });
        };
    }

    @Override
    protected void openContainer(Level level, BlockPos pos, Player player) {
        if(MultiBlock.getBlockEntity(level, pos) instanceof SmokerBlockEntity blockEntity) {
            player.openMenu(blockEntity);
            player.awardStat(Stats.INTERACT_WITH_SMOKER);
        }
    }
}
