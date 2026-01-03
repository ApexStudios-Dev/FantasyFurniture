package dev.apexstudios.fantasyfurniture.nordic.common.block;

import dev.apexstudios.apexcore.api.multiblock.MultiBlock;
import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.FloorLightBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NordicFloorLightBlock extends FloorLightBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(6D, 0D, 6D, 10D, 2D, 10D),
            box(7D, 2D, 7D, 9D, 20D, 9D),
            box(6.5D, 20.75D, 2.5D, 9.5D, 22.75D, 5.5D),
            box(2.5D, 20.75D, 6.5D, 5.5D, 22.75D, 9.5D),
            box(7.25D, 22.75D, 3.25D, 8.75D, 26.75D, 4.75D),
            box(3.25D, 22.75D, 7.25D, 4.75D, 26.75D, 8.75D),
            box(7.25D, 22.75D, 11.25D, 8.75D, 26.75D, 12.75D),
            box(11.25D, 22.75D, 7.25D, 12.75D, 26.75D, 8.75D),
            box(10.5D, 20.75D, 6.5D, 13.5D, 22.75D, 9.5D),
            box(6.5D, 20.75D, 10.5D, 9.5D, 22.75D, 13.5D),
            box(3D, 16.75D, 7D, 7D, 20.75, 9D),
            box(9D, 16.75D, 7D, 13D, 20.75, 9D),
            box(7D, 16.75D, 3D, 9D, 20.75, 7D),
            box(7D, 16.75D, 9D, 9D, 20.75, 13D)
    );

    public NordicFloorLightBlock(Properties properties) {
        super(properties, SHAPE);
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource random) {
        if(MultiBlock.getIndex(blockState) != 1)
            return;

        var x = pos.getX() + .5D;
        var y = pos.getY() + .85D;
        var z = pos.getZ() + .5D;
        var offset = .25D;

        addParticles(level, x + offset, y, z);
        addParticles(level, x - offset, y, z);
        addParticles(level, x, y, z + offset);
        addParticles(level, x, y, z - offset);
    }

    private void addParticles(Level level, double x, double y, double z) {
        level.addParticle(ParticleTypes.SMOKE, x, y, z, 0D, 0D, 0D);
        level.addParticle(ParticleTypes.FLAME, x, y, z, 0D, 0D, 0D);
    }
}
