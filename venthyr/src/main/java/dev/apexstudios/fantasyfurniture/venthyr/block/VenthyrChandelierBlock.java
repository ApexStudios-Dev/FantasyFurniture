package dev.apexstudios.fantasyfurniture.venthyr.block;

import dev.apexstudios.fantasyfurniture.block.ChandelierBlock;
import dev.apexstudios.fantasyfurniture.venthyr.VenthyrFurnitureSet;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public final class VenthyrChandelierBlock extends ChandelierBlock {
    public VenthyrChandelierBlock(Properties properties) {
        super(VenthyrFurnitureSet.FURNITURE_SET, properties, 8);
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

        playParticles(level, x, y, z);
    }
}
