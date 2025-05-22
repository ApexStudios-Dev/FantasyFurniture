package dev.apexstudios.fantasyfurniture.bone.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.OvenBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class BoneOvenBlock extends OvenBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(0D, 0D, 0D, 3D, 2D, 3D),
            box(0D, 0D, 13D, 3D, 2D, 16D),
            box(13D, 0D, 13D, 16D, 2D, 16D),
            box(13D, 0D, 0D, 16D, 2D, 3D),
            box(13D, 12D, 0D, 16D, 14D, 3D),
            box(13D, 12D, 13D, 16D, 14D, 16D),
            box(0D, 12D, 13D, 3D, 14D, 16D),
            box(0D, 12D, 0D, 3D, 14D, 3D),
            box(.5D, .5D, .5D, 15.5D, 14D, 15.5D),
            box(0D, 14D, 0D, 16D, 16D, 16D)
    );

    public BoneOvenBlock(Properties properties) {
        super(properties, SHAPE);
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource random) {
        if(!blockState.getValue(LIT))
            return;

        var facing = blockState.getValue(FACING);
        var offset = .5D;

        var x = pos.getX() + .5D + (facing.getStepX() * offset);
        var y = pos.getY();
        var z = pos.getZ() + .5D + (facing.getStepZ() * offset);

        if (random.nextDouble() < .1D)
            level.playLocalSound(x, y, z, SoundEvents.SMOKER_SMOKE, SoundSource.BLOCKS, 1F, 1F, false);

        level.addParticle(ParticleTypes.SMOKE, x, y + .1D, z, 0D, 0D, 0D);
    }
}
