package dev.apexstudios.fantasyfurniture.royal.common.block;

import dev.apexstudios.fantasyfurniture.common.block.WallLightBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class RoyalWallLightBlock extends WallLightBlock {
    public static final VoxelShape SHAPE = box(4.75D, 1D, 11.75D, 11.25D, 13D, 16D);

    public RoyalWallLightBlock(Properties properties) {
        super(ParticleTypes.FLAME, properties, SHAPE);
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource random) {
        var x = pos.getX() + .5D;
        var y = pos.getY() + .7D;
        var z = pos.getZ() + .5D;

        var facing = blockState.getValue(FACING).getOpposite();
        var attachFacing = facing.getClockWise();

        var offset = .4D;
        var lightOffset = .15D;

        x += offset * facing.getStepX();
        z += offset * facing.getStepZ();

        var offsetX = lightOffset * attachFacing.getStepX();
        var offsetZ = lightOffset * attachFacing.getStepZ();

        addParticles(level, x + offsetX, y, z + offsetZ);
        addParticles(level, x - offsetX, y, z - offsetZ);
    }

    private void addParticles(Level level, double x, double y, double z) {
        level.addParticle(ParticleTypes.SMOKE, x, y, z, 0D, 0D, 0D);
        level.addParticle(flameParticle, x, y, z, 0D, 0D, 0D);
    }
}
