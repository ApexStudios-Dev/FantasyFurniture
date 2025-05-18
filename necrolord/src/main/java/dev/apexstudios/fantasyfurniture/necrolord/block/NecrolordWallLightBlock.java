package dev.apexstudios.fantasyfurniture.necrolord.block;

import dev.apexstudios.fantasyfurniture.block.WallLightBlock;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NecrolordWallLightBlock extends WallLightBlock {
    public static final VoxelShape SHAPE = box(6D, 4D, 8D, 10D, 14.5D, 16D);

    public NecrolordWallLightBlock(Properties properties) {
        super(ParticleTypes.FLAME, properties, SHAPE);
    }
}
