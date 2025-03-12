package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.WallLightBlock;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class RoyalWallLightBlock extends WallLightBlock {
    public static final VoxelShape SHAPE = box(4.75D, 1D, 11.75D, 11.25D, 13D, 16D);
    public static final Map<Direction, VoxelShape> FACING_SHAPES = ApexShapes.rotateHorizontal(SHAPE);

    public RoyalWallLightBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(FACING_SHAPES, blockState, pos);
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource random) {
        var x = pos.getX() + .5D;
        var y = pos.getY() + .5D + .25D;
        var z = pos.getZ() + .5D;

        var facing = getComponentOrThrow(BlockComponentTypes.FACING).get(blockState).getOpposite();
        var face = facing.getClockWise();

        var xOffset = .15D * face.getStepX();
        var zOffset = .15D * face.getStepZ();

        x += .35D * facing.getStepX();
        z += .35D * facing.getStepZ();

        level.addParticle(ParticleTypes.SMOKE, x + xOffset, y, z + zOffset, 0D, 0D, 0D);
        level.addParticle(ParticleTypes.FLAME, x + xOffset, y, z + zOffset, 0D, 0D, 0D);

        level.addParticle(ParticleTypes.SMOKE, x - xOffset, y, z - zOffset, 0D, 0D, 0D);
        level.addParticle(ParticleTypes.FLAME, x - xOffset, y, z - zOffset, 0D, 0D, 0D);
    }
}
