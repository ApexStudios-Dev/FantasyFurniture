package dev.apexstudios.fantasyfurniture.nordic.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.FloorLightBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NordicFloorLightBlock extends FloorLightBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(6D, 0D, 6D, 10D, 2D, 10D),
            box(7D, 2D, 7D, 9D, 20D, 9D),
            box(6.5D, 20.75D, 2.5D, 9.5D, 22.75D, 5.5D),
            box(2.5D, 20.75D, 6.5D, 5.5D, 22.75D, 9.5D),
            box(7.25D, 22.75D, 3.25D, 8.75D, 26.75D, 4.75D),
            box(3.25D, 22.75D, 7.25D, 4.75D, 26.75D, 8.75D),
            box(7.25D, 22.75D, 11.25D, 8.75D, 26.75D, 12.75D),
            box(11.25D, 22.75D, 7.25D, 12.75D, 26.75D, 8.75D),
            box(10.5D, 20.75D, 6.5D, 13.5D, 22.75D, 9.5D),
            box(6.5D, 20.75D, 10.5D, 9.5D, 22.75D, 13.5D),
            box(3D, 16.75D, 7D, 7D, 20.75, 9D),
            box(9D, 16.75D, 7D, 13D, 20.75, 9D),
            box(7D, 16.75D, 3D, 9D, 20.75, 7D),
            box(7D, 16.75D, 9D, 9D, 20.75, 13D)
    );

    public NordicFloorLightBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
