package dev.apexstudios.fantasyfurniture.decorations.block;

import dev.apexstudios.apexcore.lib.block.Dyeable;
import dev.apexstudios.apexcore.lib.block.SimpleHorizontalDirectionalBlock;
import dev.apexstudios.apexcore.lib.util.ApexShapes;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.ARGB;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public final class BrewingCauldronBlock extends SimpleHorizontalDirectionalBlock implements Dyeable.Colored {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(3D, 0D, 3D, 5D, 2D, 5D),
            box(3D, 0D, 11D, 5D, 2D, 13D),
            box(11D, 0D, 11D, 13D, 2D, 13D),
            box(11D, 0D, 3D, 13D, 2D, 5D),
            box(2D, 2D, 2D, 14D, 9D, 14D),
            box(1D, 9D, 1D, 15D, 11D, 15D)
    );

    public static final Map<Direction, VoxelShape> SHAPES = Shapes.rotateHorizontal(SHAPE);

    public BrewingCauldronBlock(Properties properties) {
        super(properties);

        registerDefaultState(setDyedColor(defaultBlockState(), Dyeable.DyedColor.WHITE));
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource random) {
        var color = ARGB.color(145, Dyeable.getColor(blockState).getTextureDiffuseColor());

        var particle = ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, color);

        var x = pos.getX() + .5D;
        var y = pos.getY() + .4D;
        var z = pos.getZ() + .5D;

        for(var i = 0; i < 2; i ++) {
            var rand = random.nextInt(0, 4);

            if(rand == 0)
                level.addParticle(particle, x + .25D, y, z, 0D, 0D, 0D);
            else if(rand == 1)
                level.addParticle(particle, x - .25D, y, z, 0D, 0D, 0D);
            else if(rand == 2)
                level.addParticle(particle, x, y, z + .25D, 0D, 0D, 0D);
            else
                level.addParticle(particle, x, y, z - .25D, 0D, 0D, 0D);
        }
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        var facing = blockState.getValue(FACING);
        return SHAPES.get(facing);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder.add(DYED_COLOR));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        var placementBlockState = super.getStateForPlacement(context);
        return placementBlockState == null ? null : setDyedColor(placementBlockState, getDyedColorForPlacement(context));
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        var result = tryDyeBlock(stack, blockState, level, pos, player);

        if(!result.consumesAction()) {
            result = super.useItemOn(stack, blockState, level, pos, player, hand, hitResult);
        }

        return result;
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState blockState, boolean includeData, Player player) {
        var stack = new ItemStack(this);
        appendDyedColor(stack, blockState, player, includeData);
        return stack;
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState blockState, boolean includeData) {
        var stack = new ItemStack(this);
        appendDyedColor(stack, blockState, null, includeData);
        return stack;
    }
}
