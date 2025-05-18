package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.FloorLightBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class RoyalFloorLightBlock extends FloorLightBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(6D, 0D, 6D, 10D, 2D, 10D),
            box(7D, 2D, 7D, 9D, 30D, 9D),
            box(3D, 20D, 7D, 13D, 22D, 9D),
            box(11D, 22D, 7D, 13D, 29D, 9D),
            box(3D, 22D, 7D, 5D, 29D, 9D),
            box(2.5D, 24D, 6.5D, 5.5D, 25D, 9.5D),
            box(6.5D, 25D, 6.5D, 9.5D, 26D, 9.5D),
            box(10.5D, 24D, 6.5D, 13.5D, 25D, 9.5D)
    );

    public RoyalFloorLightBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
