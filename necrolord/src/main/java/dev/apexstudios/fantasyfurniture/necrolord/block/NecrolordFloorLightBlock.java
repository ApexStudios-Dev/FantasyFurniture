package dev.apexstudios.fantasyfurniture.necrolord.block;

import dev.apexstudios.apexcore.lib.multiblock.MultiBlock;
import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.FloorLightBlock;
import dev.apexstudios.fantasyfurniture.necrolord.NecrolordFurnitureSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NecrolordFloorLightBlock extends FloorLightBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(5D, 0D, 5D, 11D, 2D, 11D),
            box(6.5D, 2D, 6.5D, 9.5D, 4D, 9.5D),
            box(6.5D, 10D, 6.5D, 9.5D, 12D, 9.5D),
            box(7D, 4D, 7D, 9D, 18D, 9D),
            box(1.25D, 18D, 6.5D, 14.75D, 25D, 9.5D),
            box(12.25D, 25D, 7D, 14.25D, 28D, 9D),
            box(1.75D, 25D, 7D, 3.75D, 28D, 9D),
            box(7D, 25D, 7D, 9D, 29D, 9D)
    );

    public NecrolordFloorLightBlock(Properties properties) {
        super(properties, SHAPE);
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource random) {
        if(MultiBlock.getIndex(blockState) != 1)
            return;

        var facing = blockState.getValue(FACING).getClockWise();
        var x = pos.getX() + .5D;
        var y = pos.getY() + .95D;
        var z = pos.getZ() + .5D;
        var offset = .35D;
        var xOffset = (facing.getStepX() * offset);
        var zOffset = (facing.getStepZ() * offset);

        addParticles(level, x + xOffset, y, z + zOffset);
        addParticles(level, x, y + .1D, z);
        addParticles(level, x - xOffset, y, z - zOffset);
    }

    private void addParticles(Level level, double x, double y, double z) {
        level.addParticle(ParticleTypes.SMOKE, x, y, z, 0D, 0D, 0D);
        level.addParticle(NecrolordFurnitureSet.FLAME_PARTICLE.value(), x, y, z, 0D, 0D, 0D);
    }
}
