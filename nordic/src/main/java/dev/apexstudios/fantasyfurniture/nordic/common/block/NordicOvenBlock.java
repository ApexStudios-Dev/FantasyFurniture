package dev.apexstudios.fantasyfurniture.nordic.common.block;

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

public final class NordicOvenBlock extends OvenBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(0D, 0D, 0D, 16D, 1D, 16D),
            box(0D, 1D, 1D, 16D, 9D, 16D),
            box(0D, 9D, 0D, 16D, 10D, 16D),
            box(1D, 10D, 3D, 15D, 14D, 16D),
            box(2D, 14D, 3D, 14D, 16D, 16D)
    );

    public NordicOvenBlock(Properties properties) {
        super(properties, SHAPE);
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource random) {
        if(!blockState.getValue(LIT))
            return;

        var facing = blockState.getValue(FACING);
        var offset = .25D;

        var x = pos.getX() + .5D + (facing.getStepX() * offset);
        var y = pos.getY();
        var z = pos.getZ() + .5D + (facing.getStepZ() * offset);

        if (random.nextDouble() < .1D)
            level.playLocalSound(x, y, z, SoundEvents.SMOKER_SMOKE, SoundSource.BLOCKS, 1F, 1F, false);

        level.addParticle(ParticleTypes.SMOKE, x, y + 1.1D, z, 0D, 0D, 0D);
    }
}
