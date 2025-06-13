package dev.apexstudios.fantasyfurniture.venthyr.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.BenchBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrBenchBlock extends BenchBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(-14D, 0D, 2D, -11D, 4D, 5D),
            box(-14D, 0D, 11D, -11D, 4D, 14D),
            box(11D, 0D, 11D, 14D, 4D, 14D),
            box(11D, 0D, 2D, 14D, 4D, 5D),
            box(-15D, 4D, 1D, 15D, 7D, 15D)
    );

    public VenthyrBenchBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
