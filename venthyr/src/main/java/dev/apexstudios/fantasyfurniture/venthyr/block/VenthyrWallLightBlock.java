package dev.apexstudios.fantasyfurniture.venthyr.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.WallLightBlock;
import java.util.Map;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrWallLightBlock extends WallLightBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(6D, 1D, 15D, 10D, 3D, 16D),
            box(5D, 3D, 15D, 11D, 12D, 16D),
            box(6D, 12D, 15D, 10D, 14D, 16D),
            box(7D, 3.5D, 14D, 9D, 5.5D, 15D),
            box(4.25D, 2.5D, 10.5D, 11.75D, 11.5D, 14D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public VenthyrWallLightBlock(Properties properties) {
        super(properties);
    }
}
