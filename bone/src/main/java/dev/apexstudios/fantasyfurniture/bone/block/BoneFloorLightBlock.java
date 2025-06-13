package dev.apexstudios.fantasyfurniture.bone.block;

import dev.apexstudios.apexcore.lib.multiblock.MultiBlock;
import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.FloorLightBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class BoneFloorLightBlock extends FloorLightBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(6D, 0D, 6D, 10D, 2D, 10D),
            box(7D, 2D, 7D, 9D, 23D, 9D),
            box(2.5D, 21.75D, 6.5D, 5.5D, 23.75D, 9.5D),
            box(3.25D, 23.75D, 7.25D, 4.75D, 27.75D, 8.75D),
            box(7.25D, 24.75D, 7.25D, 8.75D, 28.75D, 8.75D),
            box(11.25D, 23.75D, 7.25D, 12.75D, 27.75D, 8.75D),
            box(10.5D, 21.75D, 6.5D, 13.5D, 23.75D, 9.5D),
            box(6.5D, 22.75D, 6.5D, 9.5D, 24.75D, 9.5D),
            box(3D, 17.75D, 7D, 7D, 21.75D, 9D),
            box(9D, 17.75D, 7D, 13D, 21.75D, 9D)
    );

    public BoneFloorLightBlock(Properties properties) {
        super(properties, SHAPE);
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource random) {
        if(MultiBlock.getIndex(blockState) != 1)
            return;

        var facing = blockState.getValue(FACING).getClockWise();
        var x = pos.getX() + .5D;
        var y = pos.getY() + .85D;
        var z = pos.getZ() + .5D;
        var offset = .25D;
        var xOffset = (facing.getStepX() * offset);
        var zOffset = (facing.getStepZ() * offset);

        addParticles(level, x + xOffset, y, z + zOffset);
        addParticles(level, x, y + .1D, z);
        addParticles(level, x - xOffset, y, z - zOffset);
    }

    private void addParticles(Level level, double x, double y, double z) {
        level.addParticle(ParticleTypes.SMOKE, x, y, z, 0D, 0D, 0D);
        level.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x, y, z, 0D, 0D, 0D);
    }
}
