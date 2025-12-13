package dev.apexstudios.fantasyfurniture.decorations.block;

import dev.apexstudios.apexcore.lib.block.SimpleHorizontalDirectionalBlock;
import dev.apexstudios.apexcore.lib.util.ApexShapes;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3d;
import org.joml.Vector3dc;

public final class CandlesBlock extends SimpleHorizontalDirectionalBlock implements Lightable {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(5D, 0D, 4D, 8D, 3D, 7D),
            box(9D, 0D, 5D, 12D, 6D, 8D),
            box(8D, 0D, 10D, 11D, 8D, 13D),
            box(4D, 0D, 9D, 7D, 5D, 12D)
    );

    public static final Map<Direction, VoxelShape> SHAPES = Shapes.rotateHorizontal(SHAPE);

    public CandlesBlock(Properties properties) {
        super(properties);

        registerDefaultState(setLit(defaultBlockState(), false));
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        var facing = blockState.getValue(FACING);
        return SHAPES.get(facing);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder.add(LIT));
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(blockState, level, pos, random);
        Lightable.animateTick(this, blockState, level, pos, random);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        var result = Lightable.useItemOn(this, stack, blockState, level, pos, player, hand, hitResult);
        return result.consumesAction() ? result : super.useItemOn(stack, blockState, level, pos, player, hand, hitResult);
    }

    @Override
    protected void onProjectileHit(Level level, BlockState blockState, BlockHitResult hit, Projectile projectile) {
        super.onProjectileHit(level, blockState, hit, projectile);
        Lightable.onProjectileHit(this, level, blockState, hit, projectile);
    }

    @Override
    protected void onExplosionHit(BlockState blockState, ServerLevel level, BlockPos pos, Explosion explosion, BiConsumer<ItemStack, BlockPos> dropConsumer) {
        Lightable.onExplosionHit(this, blockState, level, pos, explosion, dropConsumer);
        super.onExplosionHit(blockState, level, pos, explosion, dropConsumer);
    }

    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState blockState, UseOnContext context, ItemAbility ability, boolean simulate) {
        var result = Lightable.getToolModifiedState(this, blockState, context, ability, simulate);
        return result == null ? super.getToolModifiedState(blockState, context, ability, simulate) : result;
    }

    @Override
    public void forEachLightPos(BlockState blockState, BlockPos pos, Consumer<Vector3dc> consumer) {
        var x = pos.getX();
        var y = pos.getY();
        var z = pos.getZ();

        switch (blockState.getValue(FACING)) {
            case NORTH -> {
                consumer.accept(new Vector3d(x + .4D, y + .313D, z + .35D));
                consumer.accept(new Vector3d(x + .65D, y + .5D, z + .4D));
                consumer.accept(new Vector3d(x + .375D, y + .45D, z + .65D));
                consumer.accept(new Vector3d(x + .6D, y + .65D, z + .725D));
            }

            case EAST -> {
                consumer.accept(new Vector3d(x + .65D, y + .313D, z + .4D));
                consumer.accept(new Vector3d(x + .6D, y + .5D, z + .65D));
                consumer.accept(new Vector3d(x + .35D, y + .45D, z + .35D));
                consumer.accept(new Vector3d(x + .3D, y + .65D, z + .6D));
            }

            case SOUTH -> {
                consumer.accept(new Vector3d(x + .6D, y + .313D, z + .65D));
                consumer.accept(new Vector3d(x + .35D, y + .5D, z + .6D));
                consumer.accept(new Vector3d(x + .4D, y + .65D, z + .3D));
                consumer.accept(new Vector3d(x + .65D, y + .45D, z + .3D));
            }

            case WEST -> {
                consumer.accept(new Vector3d(x + .35D, y + .313D, z + .6D));
                consumer.accept(new Vector3d(x + .4D, y + .5D, z + .35D));
                consumer.accept(new Vector3d(x + .7D, y + .65D, z + .4D));
                consumer.accept(new Vector3d(x + .65D, y + .45D, z + .65D));
            }
        }
    }
}
