package dev.apexstudios.fantasyfurniture.necrolord.common.block;

import dev.apexstudios.fantasyfurniture.common.block.ChandelierBlock;
import dev.apexstudios.fantasyfurniture.necrolord.common.NecrolordFurnitureSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NecrolordChandelierBlock extends ChandelierBlock {
    public static final VoxelShape SHAPE = box(3D, 0D, 3D, 13D, 16D, 13D);

    public NecrolordChandelierBlock(Properties properties) {
        super(properties, SHAPE);
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource random) {
        var x = pos.getX() + .5D;
        var y = pos.getY() + .7D;
        var z = pos.getZ() + .5D;

        addParticles(level, x, y, z);
    }

    private void addParticles(Level level, double x, double y, double z) {
        level.addParticle(ParticleTypes.SMOKE, x, y, z, 0D, 0D, 0D);
        level.addParticle(NecrolordFurnitureSet.FLAME_PARTICLE.value(), x, y, z, 0D, 0D, 0D);
    }
}
