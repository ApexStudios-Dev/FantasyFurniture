package dev.apexstudios.fantasyfurniture.venthyr.block;

import dev.apexstudios.fantasyfurniture.block.ChandelierBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;

public final class VenthyrChandelierBlock extends ChandelierBlock {
    public VenthyrChandelierBlock(Properties properties) {
        super(properties, 8);
    }

    @Override
    protected void addParticle(Level level, BlockPos pos, int index) {
        var x = pos.getX() + .5D;
        var y = pos.getY() + .55D;
        var z = pos.getZ() + .5D;
        var hOffset = .3D;
        var even = index % 2 == 0;

        if(index < 4) {
            x = even ? x + hOffset : x - hOffset;
            z = index < 2 ? z + hOffset : z - hOffset;
        } else {
            var offset = hOffset / 2D;

            y += .2D;
            x = even ? x + offset : x - offset;
            z = index < 5 ? z + offset : z - offset;
        }

        level.addParticle(ParticleTypes.SMOKE, x, y, z, 0D, 0D, 0D);
        level.addParticle(ParticleTypes.FLAME, x, y, z, 0D, 0D, 0D);
    }
}
