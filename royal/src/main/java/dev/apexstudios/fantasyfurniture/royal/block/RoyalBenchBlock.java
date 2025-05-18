package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.BenchBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class RoyalBenchBlock extends BenchBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(-15D, 0D, 1D, -11D, 4D, 5D),
            box(-15D, 0D, 11D, -11D, 4D, 15D),
            box(11D, 0D, 11D, 15D, 4D, 15D),
            box(11D, 0D, 1D, 15D, 4D, 5D),
            box(-14.5D, 4D, 1.5D, 14.5D, 6D, 14.5D)
    );

    public RoyalBenchBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
