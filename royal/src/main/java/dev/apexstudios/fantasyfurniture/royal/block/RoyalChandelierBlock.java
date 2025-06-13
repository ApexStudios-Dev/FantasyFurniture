package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.fantasyfurniture.block.ChandelierBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;

public final class RoyalChandelierBlock extends ChandelierBlock {
    public RoyalChandelierBlock(Properties properties) {
        super(properties.noOcclusion(), Shapes.block());
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource random) {
        var x = pos.getX() + .5D;
        var y = pos.getY() + .8D;
        var z = pos.getZ() + .5D;
        var offset = .3D;

        addParticles(level, x + offset, y, z - offset);
        addParticles(level, x - offset, y, z - offset);
        addParticles(level, x + offset, y, z + offset);
        addParticles(level, x - offset, y, z + offset);
    }

    private void addParticles(Level level, double x, double y, double z) {
        level.addParticle(ParticleTypes.SMOKE, x, y, z, 0D, 0D, 0D);
        level.addParticle(ParticleTypes.FLAME, x, y, z, 0D, 0D, 0D);
    }
}
