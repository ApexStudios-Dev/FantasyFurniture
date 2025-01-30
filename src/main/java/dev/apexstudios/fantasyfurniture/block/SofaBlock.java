package dev.apexstudios.fantasyfurniture.block;

import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.fantasyfurniture.block.base.SeatBlock;
import dev.apexstudios.fantasyfurniture.block.property.SofaConnection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public final class SofaBlock extends SeatBlock {
    public SofaBlock(Properties properties) {
        super(properties);

        registerDefaultState(defaultBlockState().setValue(SofaConnection.PROPERTY, SofaConnection.BOTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SofaConnection.PROPERTY);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        var blockState = super.getStateForPlacement(context);

        if(blockState == null)
            return null;

        var level = context.getLevel();
        var pos = context.getClickedPos();
        var facingComponent = getComponentOrThrow(BlockComponentTypes.FACING);

        return SofaConnection.setConnection(level, pos, blockState, facingComponent::get, facingComponent::set);
    }

    @Override
    public BlockState updateShape(BlockState blockState, LevelReader level, ScheduledTickAccess tickAccess, BlockPos pos, Direction facing, BlockPos neighborPos, BlockState neighborBlockState, RandomSource random) {
        var result = blockState;

        if(facing.getAxis().isHorizontal()) {
            var facingComponent = getComponentOrThrow(BlockComponentTypes.FACING);
            result = SofaConnection.setConnection(level, pos, blockState, facingComponent::get, facingComponent::set);
        }

        return super.updateShape(result, level, tickAccess, pos, facing, neighborPos, neighborBlockState, random);
    }
}
