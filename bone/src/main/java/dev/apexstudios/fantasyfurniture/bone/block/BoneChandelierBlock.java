package dev.apexstudios.fantasyfurniture.bone.block;

import dev.apexstudios.fantasyfurniture.block.ChandelierBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;

public final class BoneChandelierBlock extends ChandelierBlock {
    public BoneChandelierBlock(Properties properties) {
        super(properties.noOcclusion(), Shapes.block());
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource random) {
        var x = pos.getX() + .5D;
        var y = pos.getY() + .75D;
        var z = pos.getZ() + .5D;
        var offset = .35D;

        addParticles(level, x + offset, y, z);
        addParticles(level, x - offset, y, z);
        addParticles(level, x, y, z + offset);
        addParticles(level, x, y, z - offset);
    }

    private void addParticles(Level level, double x, double y, double z) {
        level.addParticle(ParticleTypes.SMOKE, x, y, z, 0D, 0D, 0D);
        level.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x, y, z, 0D, 0D, 0D);
    }
}
