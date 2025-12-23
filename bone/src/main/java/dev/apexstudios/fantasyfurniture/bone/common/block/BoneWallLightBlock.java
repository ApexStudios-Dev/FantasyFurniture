package dev.apexstudios.fantasyfurniture.bone.common.block;

import dev.apexstudios.fantasyfurniture.common.block.WallLightBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class BoneWallLightBlock extends WallLightBlock {
    public static final VoxelShape SHAPE = box(6D, 4D, 9D, 10D, 15D, 16D);

    public BoneWallLightBlock(Properties properties) {
        super(ParticleTypes.SOUL_FIRE_FLAME, properties, SHAPE);
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource random) {
        var x = pos.getX() + .5D;
        var y = pos.getY() + .7D;
        var z = pos.getZ() + .5D;

        var facing = blockState.getValue(FACING).getOpposite();
        var offset = .1D;
        var offsetZ = offset * facing.getStepZ();
        var offsetX = offset * facing.getStepX();

        addParticles(level, x + offsetX, y + .35D, z + offsetZ);
    }

    private void addParticles(Level level, double x, double y, double z) {
        level.addParticle(ParticleTypes.SMOKE, x, y, z, 0D, 0D, 0D);
        level.addParticle(flameParticle, x, y, z, 0D, 0D, 0D);
    }
}
