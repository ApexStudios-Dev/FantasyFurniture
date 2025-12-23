package dev.apexstudios.fantasyfurniture.royal.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class RoyalDeskLeftBlock extends RoyalDeskBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(-16D, 14D, 0D, 16D, 16D, 16D),
            box(13D, 11D, 1D, 15D, 14D, 4D),
            box(13D, 3D, 2D, 15D, 11D, 4D),
            box(13D, 0D, 1D, 15D, 3D, 4D),
            box(-15D, 0D, 1D, -13D, 3D, 4D),
            box(-15D, 11D, 1D, -13D, 14D, 4D),
            box(-15D, 3D, 2D, -13D, 11D, 4D),
            box(-15D, 0D, 12D, -13D, 3D, 15D),
            box(-15D, 11D, 12D, -13D, 14D, 15D),
            box(-15D, 3D, 12D, -13D, 11D, 14D),
            box(13D, 0D, 12D, 15D, 3D, 15D),
            box(13D, 11D, 12D, 15D, 14D, 15D),
            box(13D, 3D, 12D, 15D, 11D, 14D),
            box(4D, 10D, 2D, 12D, 14D, 14D),
            box(6D, 12D, 1D, 10D, 13D, 2D)
    );

    public RoyalDeskLeftBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
