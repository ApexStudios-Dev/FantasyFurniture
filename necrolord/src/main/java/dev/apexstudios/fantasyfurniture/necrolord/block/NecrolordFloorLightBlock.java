package dev.apexstudios.fantasyfurniture.necrolord.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.FloorLightBlock;
import java.util.Map;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.Shapes;
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

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public NecrolordFloorLightBlock(Properties properties) {
        super(properties);
    }
}
