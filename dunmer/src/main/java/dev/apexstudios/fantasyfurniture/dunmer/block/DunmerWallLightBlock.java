package dev.apexstudios.fantasyfurniture.dunmer.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.WallLightBlock;
import java.util.Map;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class DunmerWallLightBlock extends WallLightBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(4.5D, 1D, 7.5D, 11.5D, 2D, 14.5D),
            box(4.5D, 8D, 7.5D, 11.5D, 9D, 14.5D),
            box(5D, 2D, 8D, 11D, 8D, 14D),
            box(6D, 9D, 9D, 10D, 10D, 13D),
            box(7.5D, 10D, 10.5D, 8.5D, 13D, 11.5D),
            box(7D, 13D, 9D, 9D, 15D, 15D),
            box(6D, 12D, 15D, 10D, 16D, 16D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public DunmerWallLightBlock(Properties properties) {
        super(ParticleTypes.FLAME, properties);
    }
}
