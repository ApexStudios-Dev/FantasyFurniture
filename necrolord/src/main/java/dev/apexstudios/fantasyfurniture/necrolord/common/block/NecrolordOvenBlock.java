package dev.apexstudios.fantasyfurniture.necrolord.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.OvenBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NecrolordOvenBlock extends OvenBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(1D, 0D, 1D, 4D, 2D, 4D),
            box(1D, 0D, 12D, 4D, 2D, 15D),
            box(12D, 0D, 12D, 15D, 2D, 15D),
            box(12D, 0D, 1D, 15D, 2D, 4D),
            box(0D, 2D, 0D, 16D, 16D, 16D)
    );

    public NecrolordOvenBlock(Properties properties) {
        super(properties, SHAPE);
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource random) {
        if(!blockState.getValue(LIT))
            return;

        var x = pos.getX() + .5D;
        var y = pos.getY();
        var z = pos.getZ() + .5D;
        var offset = .25D;

        if (random.nextDouble() < .1D)
            level.playLocalSound(x, y, z, SoundEvents.SMOKER_SMOKE, SoundSource.BLOCKS, 1F, 1F, false);

        level.addParticle(ParticleTypes.SMOKE, x + offset, y + 1.1D, z - offset, 0D, 0D, 0D);
        level.addParticle(ParticleTypes.SMOKE, x + offset, y + 1.1D, z + offset, 0D, 0D, 0D);
        level.addParticle(ParticleTypes.SMOKE, x - offset, y + 1.1D, z - offset, 0D, 0D, 0D);
        level.addParticle(ParticleTypes.SMOKE, x - offset, y + 1.1D, z + offset, 0D, 0D, 0D);
    }
}
