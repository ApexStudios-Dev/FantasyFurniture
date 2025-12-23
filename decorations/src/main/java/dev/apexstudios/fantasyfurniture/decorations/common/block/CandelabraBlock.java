package dev.apexstudios.fantasyfurniture.decorations.common.block;

import dev.apexstudios.apexcore.api.block.SimpleHorizontalDirectionalBlock;
import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.decorations.common.DecorationsFurnitureModule;
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
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.jspecify.annotations.Nullable;

public final class CandelabraBlock extends SimpleHorizontalDirectionalBlock implements Lightable {
    public static final VoxelShape SHAPE_0 = ApexShapes.join(
            box(6D, 0D, 6D, 10D, 2D, 10D),
            box(7D, 2D, 7D, 9D, 14D, 9D),
            box(3D, 5D, 7D, 13D, 7D, 9D),
            box(11D, 7D, 7D, 13D, 13D, 9D),
            box(3D, 7D, 7D, 5D, 13D, 9D),
            box(2.5D, 9D, 6.5D, 5.5D, 10D, 9.5D),
            box(6.5D, 10D, 6.5D, 9.5D, 11D, 9.5D),
            box(10.5D, 9D, 6.5D, 13.5D, 10D, 9.5D)
    );

    public static final VoxelShape SHAPE_1 = ApexShapes.join(
            box(6D, 0D, 6D, 10D, 2D, 10D),
            box(7D, 2D, 7D, 9D, 5D, 9D),
            box(1.25D, 5D, 6.5D, 14.75D, 12D, 9.5D),
            box(12.25D, 12D, 7D, 14.25D, 15D, 9D),
            box(1.75D, 12D, 7D, 3.75D, 15D, 9D),
            box(7D, 12D, 7D, 9D, 16D, 9D)
    );

    private final Map<Direction, VoxelShape> shapes;

    public CandelabraBlock(Properties properties, VoxelShape baseShape) {
        super(properties);

        shapes = Shapes.rotateHorizontal(baseShape);

        registerDefaultState(setLit(defaultBlockState(), false));
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        var facing = blockState.getValue(FACING);
        return shapes.get(facing);
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
        var x = pos.getX() + .5D;
        var y = pos.getY() + .5D + .45D + .075D;
        var z = pos.getZ() + .5D;

        var facing = blockState.getValue(FACING).getClockWise();
        var stepX = facing.getStepX();
        var stepZ = facing.getStepZ();
        var stepOffset = .25D;

        if(DecorationsFurnitureModule.CANDELABRA_1.is(blockState)) {
            y += .1D;
            stepOffset += .1D;
        }

        consumer.accept(new Vector3d(x, y, z));
        consumer.accept(new Vector3d(x + (stepX * stepOffset), y - .05D, z + (stepZ * stepOffset)));
        consumer.accept(new Vector3d(x - (stepX * stepOffset), y - .05D, z - (stepZ * stepOffset)));
    }
}
