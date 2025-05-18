package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.fantasyfurniture.block.WallLightBlock;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class RoyalWallLightBlock extends WallLightBlock {
    public static final VoxelShape SHAPE = box(4.75D, 1D, 11.75D, 11.25D, 13D, 16D);

    public RoyalWallLightBlock(Properties properties) {
        super(ParticleTypes.FLAME, properties, SHAPE);
    }
}
