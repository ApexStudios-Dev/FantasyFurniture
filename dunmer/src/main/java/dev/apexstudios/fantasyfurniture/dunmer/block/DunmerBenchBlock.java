package dev.apexstudios.fantasyfurniture.dunmer.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.BenchBlock;
import java.util.Map;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class DunmerBenchBlock extends BenchBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(12D, 0D, 2D, 14D, 5D, 4D),
            box(-14D, 0D, 2D, -12D, 5D, 4D),
            box(-14D, 0D, 12D, -12D, 5D, 14D),
            box(12D, 0D, 12D, 14D, 5D, 14D),
            box(-15D, 5D, 1D, 15D, 7D, 15D),
            box(12.5D, 2.5D, 4D, 13.5D, 3.5, 12D),
            box(-13.5D, 2.5D, 4D, -12.5D, 3.5, 12D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public DunmerBenchBlock(Properties properties) {
        super(properties);
    }
}
