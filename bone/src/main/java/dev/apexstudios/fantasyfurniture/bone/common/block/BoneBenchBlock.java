package dev.apexstudios.fantasyfurniture.bone.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.BenchBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class BoneBenchBlock extends BenchBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(12D, 0D, 1D, 15D, 2D, 4D),
            box(-15D, 0D, 1D, -12D, 2D, 4D),
            box(-15D, 0D, 12D, -12D, 2D, 15D),
            box(12D, 0D, 12D, 15D, 2D, 15D),
            box(-14.5D, 2D, 1.5D, -12.5D, 5D, 3.5D),
            box(12.5D, 2D, 1.5D, 14.5D, 5D, 3.5D),
            box(12.5D, 2D, 12.5D, 14.5D, 5D, 14.5D),
            box(-14.5D, 2D, 12.5D, -12.5D, 5D, 14.5D),
            box(-15.5D, 4.5D, -.5D, 15.5D, 7.5D, 16.5D)
    );

    public BoneBenchBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
