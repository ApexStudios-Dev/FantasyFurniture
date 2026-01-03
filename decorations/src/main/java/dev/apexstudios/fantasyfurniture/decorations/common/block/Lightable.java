package dev.apexstudios.fantasyfurniture.decorations.common.block;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.jspecify.annotations.Nullable;

public interface Lightable {
    BooleanProperty LIT = BlockStateProperties.LIT;

    default BlockState setLit(BlockState blockState, boolean lit) {
        return blockState.setValue(LIT, lit);
    }

    default boolean isLit(BlockState blockState) {
        return blockState.getValue(LIT);
    }

    default boolean canBeLit(BlockState blockState) {
        return !isLit(blockState);
    }

    default void onLightLit(LevelAccessor level, BlockPos pos, BlockState blockState, @Nullable Player player) {
        level.setBlock(pos, setLit(blockState, true), Block.UPDATE_ALL_IMMEDIATE);
    }

    default void onLightExtinguished(LevelAccessor level, BlockPos pos, BlockState blockState, @Nullable Player player) {
        level.setBlock(pos, setLit(blockState, false), Block.UPDATE_ALL_IMMEDIATE);
        forEachLightPos(blockState, pos, ambientPos -> level.addParticle(ParticleTypes.SMOKE, ambientPos.x(), ambientPos.y(), ambientPos.z(), 0D, .1D, 0D));
        level.playSound(null, pos, SoundEvents.CANDLE_EXTINGUISH, SoundSource.BLOCKS, 1F, 1F);
        level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
    }

    default void forEachLightPos(BlockState blockState, BlockPos pos, Consumer<Vector3dc> consumer) {
        consumer.accept(new Vector3d(pos.getX() + .5D, pos.getY() + .5D, pos.getZ() + .5D));
    }

    static void setLit(Lightable lightable, LevelAccessor level, BlockPos pos, BlockState blockState, @Nullable Player player, boolean lit) {
        if(lightable.isLit(blockState) == lit) {
            return;
        }

        if(lit) {
            if(lightable.canBeLit(blockState)) {
                lightable.onLightLit(level, pos, blockState, player);
            }
        } else {
            lightable.onLightExtinguished(level, pos, blockState, player);
        }
    }

    static InteractionResult useItemOn(Lightable lightable, ItemStack stack, BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(stack.isEmpty() && player.mayBuild() && lightable.isLit(blockState)) {
            setLit(lightable, level, pos, blockState, player, false);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    static void onProjectileHit(Lightable lightable, Level level, BlockState blockState, BlockHitResult hit, Projectile projectile) {
        if(!level.isClientSide() && projectile.isOnFire()) {
            setLit(lightable, level, projectile.blockPosition(), blockState, null, true);
        }
    }

    static void onExplosionHit(Lightable lightable, BlockState blockState, ServerLevel level, BlockPos pos, Explosion explosion, BiConsumer<ItemStack, BlockPos> dropConsumer) {
        if(explosion.canTriggerBlocks()) {
            setLit(lightable, level, pos, blockState, null, false);
        }
    }

    @Nullable
    static BlockState getToolModifiedState(Lightable lightable, BlockState blockState, UseOnContext context, ItemAbility ability, boolean simulate) {
        if(ability == ItemAbilities.FIRESTARTER_LIGHT && !lightable.isLit(blockState) && lightable.canBeLit(blockState)) {
            return lightable.setLit(blockState, true);
        }

        return null;
    }

    static void animateTick(Lightable lightable, BlockState blockState, Level level, BlockPos pos, RandomSource random) {
        if(!lightable.isLit(blockState)) {
            return;
        }

        var chance = random.nextFloat();

        lightable.forEachLightPos(blockState, pos, ambientPos -> {
            if(chance < .3F) {
                level.addParticle(ParticleTypes.SMOKE, ambientPos.x(), ambientPos.y(), ambientPos.z(), 0D, 0D, 0D);
            }

            if(chance < .17F) {
                level.playLocalSound(
                        ambientPos.x() + .5D,
                        ambientPos.y() + .5D,
                        ambientPos.z() + .5D,
                        SoundEvents.CANDLE_AMBIENT,
                        SoundSource.BLOCKS,
                        1F + random.nextFloat(),
                        random.nextFloat() * .7F + .3F,
                        false
                );
            }

            level.addParticle(ParticleTypes.SMALL_FLAME, ambientPos.x(), ambientPos.y(), ambientPos.z(), 0D, 0D, 0D);
        });
    }
}
