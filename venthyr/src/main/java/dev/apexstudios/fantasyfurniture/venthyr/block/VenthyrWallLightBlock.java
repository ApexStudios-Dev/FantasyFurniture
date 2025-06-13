package dev.apexstudios.fantasyfurniture.venthyr.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.WallLightBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrWallLightBlock extends WallLightBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(6D, 1D, 15D, 10D, 3D, 16D),
            box(5D, 3D, 15D, 11D, 12D, 16D),
            box(6D, 12D, 15D, 10D, 14D, 16D),
            box(7D, 3.5D, 14D, 9D, 5.5D, 15D),
            box(4.25D, 2.5D, 10.5D, 11.75D, 11.5D, 14D)
    );

    public VenthyrWallLightBlock(Properties properties) {
        super(ParticleTypes.FLAME, properties, SHAPE);
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource random) {
        var x = pos.getX() + .5D;
        var y = pos.getY() + .8D;
        var z = pos.getZ() + .5D;

        var facing = blockState.getValue(FACING).getOpposite();
        var attachFacing = facing.getClockWise();

        var offset = .4D;
        var lightOffset = .14D;

        x += offset * facing.getStepX();
        z += offset * facing.getStepZ();

        var offsetX = lightOffset * attachFacing.getStepX();
        var offsetZ = lightOffset * attachFacing.getStepZ();

        addParticles(level, x + offsetX, y, z + offsetZ);
        addParticles(level, x - offsetX, y, z - offsetZ);
    }

    private void addParticles(Level level, double x, double y, double z) {
        level.addParticle(ParticleTypes.SMOKE, x, y, z, 0D, 0D, 0D);
        level.addParticle(flameParticle, x, y, z, 0D, 0D, 0D);
    }
}
