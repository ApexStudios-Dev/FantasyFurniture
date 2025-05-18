package dev.apexstudios.fantasyfurniture.nordic.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.WardrobeBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NordicWardrobeBlock extends WardrobeBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(-14.75D, 0D, .25D, -12.25D, 31D, 2.75D),
            box(-14.75D, 0D, 13.25D, -12.25D, 31D, 15.75D),
            box(12.25D, 0D, .25D, 14.75D, 31D, 2.75D),
            box(-14D, 2D, 1D, 14D, 31D, 15D),
            box(-15D, 31D, 0D, 16D, 45.75D, 16D),
            box(12.25D, 0D, 13.25D, 14.75D, 31D, 15.75D)
    );

    public NordicWardrobeBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
