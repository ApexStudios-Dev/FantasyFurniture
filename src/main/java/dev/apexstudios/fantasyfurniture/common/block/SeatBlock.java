package dev.apexstudios.fantasyfurniture.common.block;

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
import org.jetbrains.annotations.MustBeInvokedByOverriders;

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

    protected boolean isBouncy(BlockState blockState) {
        return true;
    }

    @MustBeInvokedByOverriders
    protected boolean shouldBounce(BlockState blockState) {
        return isBouncy(blockState);
    }

    @Override
    public void fallOn(Level level, BlockState blockState, BlockPos pos, Entity entity, double fallDistance) {
        if(shouldBounce(blockState)) {
            super.fallOn(level, blockState, pos, entity, fallDistance * .5F);
        } else {
            super.fallOn(level, blockState, pos, entity, fallDistance);
        }
    }

    // TODO: validate against beds
    /*@Override
    public void updateEntityMovementAfterFallOn(BlockGetter level, Entity entity) {
        // same code as to how Entity gets the BlockState
        var effectPos = entity.getOnPosLegacy();
        var effectBlockState = level.getBlockState(effectPos);

        if(entity.isSuppressingBounce() || !shouldBounce(effectBlockState)) {
            super.updateEntityMovementAfterFallOn(level, entity);
        } else {
            var movement = entity.getDeltaMovement();

            if(movement.y() < 0D) {
                var factor = entity instanceof LivingEntity ? 1D : 0D;
                entity.setDeltaMovement(
                        movement.x(),
                        -movement.y() * .66F * factor,
                        movement.z()
                );
            }
        }
    }*/
}
