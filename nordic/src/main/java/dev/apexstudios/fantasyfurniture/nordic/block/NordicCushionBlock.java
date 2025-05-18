package dev.apexstudios.fantasyfurniture.nordic.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.CushionBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NordicCushionBlock extends CushionBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(2D, 0D, 2D, 4D, 2D, 4D),
            box(2D, 0D, 12D, 4D, 2D, 14D),
            box(12D, 0D, 12D, 14D, 2D, 14D),
            box(12D, 0D, 2D, 14D, 2D, 4D),
            box(2D, 5D, 2.25D, 14D, 7D, 13.75D),
            box(1.75D, 4D, 2D, 14.25D, 5D, 14D),
            box(2D, 2D, 2.5D, 4D, 4D, 4.5D),
            box(12D, 2D, 2.5D, 14D, 4D, 4.5D),
            box(12D, 2D, 11.5D, 14D, 4D, 13.5D),
            box(2D, 2D, 11.5D, 4D, 4D, 13.5D),
            box(2.5D, 2.5D, 4.5D, 3.5D, 3.5D, 11.5D),
            box(12.5D, 2.5D, 4.5D, 13.5D, 3.5D, 11.5D)
    );

    public NordicCushionBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
