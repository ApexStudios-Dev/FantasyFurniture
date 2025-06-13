package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.apexcore.lib.multiblock.MultiBlock;
import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.FloorLightBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class RoyalFloorLightBlock extends FloorLightBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(6D, 0D, 6D, 10D, 2D, 10D),
            box(7D, 2D, 7D, 9D, 30D, 9D),
            box(3D, 20D, 7D, 13D, 22D, 9D),
            box(11D, 22D, 7D, 13D, 29D, 9D),
            box(3D, 22D, 7D, 5D, 29D, 9D),
            box(2.5D, 24D, 6.5D, 5.5D, 25D, 9.5D),
            box(6.5D, 25D, 6.5D, 9.5D, 26D, 9.5D),
            box(10.5D, 24D, 6.5D, 13.5D, 25D, 9.5D)
    );

    public RoyalFloorLightBlock(Properties properties) {
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
        var offset = .25D;
        var xOffset = (facing.getStepX() * offset);
        var zOffset = (facing.getStepZ() * offset);

        addParticles(level, x + xOffset, y, z + zOffset);
        addParticles(level, x, y + .1D, z);
        addParticles(level, x - xOffset, y, z - zOffset);
    }

    private void addParticles(Level level, double x, double y, double z) {
        level.addParticle(ParticleTypes.SMOKE, x, y, z, 0D, 0D, 0D);
        level.addParticle(ParticleTypes.FLAME, x, y, z, 0D, 0D, 0D);
    }
}
