package dev.apexstudios.fantasyfurniture.venthyr.block;

import dev.apexstudios.fantasyfurniture.block.ChandelierBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrChandelierBlock extends ChandelierBlock {
    public static final VoxelShape SHAPE = box(1D, 0D, 1D, 15, 16D, 15D);

    public VenthyrChandelierBlock(Properties properties) {
        super(properties, SHAPE);
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource random) {
        var x = pos.getX() + .5D;
        var y = pos.getY() + .55D;
        var z = pos.getZ() + .5D;
        var offset = .3D;

        addParticles(level, x + offset, y, z - offset);
        addParticles(level, x - offset, y, z - offset);
        addParticles(level, x + offset, y, z + offset);
        addParticles(level, x - offset, y, z + offset);

        var yOffset = .2D;
        offset -= .125D;

        addParticles(level, x + offset, y + yOffset, z - offset);
        addParticles(level, x - offset, y + yOffset, z - offset);
        addParticles(level, x + offset, y + yOffset, z + offset);
        addParticles(level, x - offset, y + yOffset, z + offset);
    }

    private void addParticles(Level level, double x, double y, double z) {
        level.addParticle(ParticleTypes.SMOKE, x, y, z, 0D, 0D, 0D);
        level.addParticle(ParticleTypes.FLAME, x, y, z, 0D, 0D, 0D);
    }
}
