package dev.apexstudios.fantasyfurniture.nordic.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.BedDoubleBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NordicBedDoubleBlock extends BedDoubleBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(-16D, 3D, 2D, 16D, 5D, 30D),
            box(-14D, 5D, 2D, 14D, 8D, 30D),
            box(-16D, 3D, 0D, 16D, 5D, 2D),
            box(-16D, 0D, 0D, -14D, 8D, 2D),
            box(14D, 0D, 0D, 16D, 8D, 2D),
            box(-16D, 12D, 0D, -8D, 14D, 2D),
            box(8D, 12D, 0D, 16D, 14D, 2D),
            box(-10D, 12D, 0D, 10D, 16D, 2D),
            box(-15D, 5D, 0D, 15D, 12D, 2D),
            box(-15D, 5D, 30D, 15D, 12D, 32D),
            box(-16D, 3D, 30D, 16D, 5D, 32D),
            box(-16D, 0D, 30D, -14D, 8D, 32D),
            box(14D, 0D, 30D, 16D, 8D, 32D),
            box(-16D, 12D, 30D, -8D, 14D, 32D),
            box(8D, 12D, 30D, 16D, 14D, 32D),
            box(-10D, 12D, 30D, 10D, 16D, 32D)
    );

    public NordicBedDoubleBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
