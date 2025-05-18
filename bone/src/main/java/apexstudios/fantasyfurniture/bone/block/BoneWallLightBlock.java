package apexstudios.fantasyfurniture.bone.block;

import dev.apexstudios.fantasyfurniture.block.WallLightBlock;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class BoneWallLightBlock extends WallLightBlock {
    public static final VoxelShape SHAPE = box(6D, 4D, 9D, 10D, 15D, 16D);

    public BoneWallLightBlock(Properties properties) {
        super(ParticleTypes.FLAME, properties, SHAPE);
    }
}
