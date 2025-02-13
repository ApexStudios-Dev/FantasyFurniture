package dev.apexstudios.fantasyfurniture.block;

import dev.apexstudios.fantasyfurniture.FurnitureBlockEntities;
import dev.apexstudios.fantasyfurniture.block.base.InventoryBlock;
import dev.apexstudios.fantasyfurniture.oven.OvenBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SmokerBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;

public class OvenBlock extends InventoryBlock {
    public static final BooleanProperty LIT = SmokerBlock.LIT;

    public OvenBlock(Properties properties) {
        super(properties);

        registerDefaultState(defaultBlockState().setValue(LIT, false));
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState blockState) {
        return new OvenBlockEntity(pos, blockState);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return level instanceof ServerLevel sLevel ?
                createTickerHelper(blockEntityType, FurnitureBlockEntities.OVEN.value(), (level1, pos, state, blockEntity) -> blockEntity.serverTick(sLevel, pos, state)) :
                null;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }
}
