package dev.apexstudios.fantasyfurniture.common.block;

import dev.apexstudios.apexcore.api.block.BlockHelper;
import dev.apexstudios.apexcore.api.block.Seat;
import dev.apexstudios.apexcore.api.block.SimpleHorizontalDirectionalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;

public class SeatBlock extends SimpleHorizontalDirectionalBlock {
    public SeatBlock(Properties properties) {
        super(properties);

        registerDefaultState(defaultBlockState().setValue(Seat.DEFAULT_PROPERTY, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(Seat.DEFAULT_PROPERTY);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        var result = Seat.interactWith(level, pos, player);

        if(result.consumesAction())
            return result;

        return super.useWithoutItem(blockState, level, pos, player, hitResult);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState blockState, Entity entity) {
        super.stepOn(level, pos, blockState, entity);

        if(!(entity instanceof Player) && entity instanceof LivingEntity living)
            Seat.trySit(level, pos, living);
    }

    @Override
    public void fallOn(Level level, BlockState blockState, BlockPos pos, Entity entity, double fallDistance) {
        if(BlockHelper.shouldBounceOnBlock(entity, blockState)) {
            super.fallOn(level, blockState, pos, entity, fallDistance * .5F);
        } else {
            super.fallOn(level, blockState, pos, entity, fallDistance);
        }
    }
}
