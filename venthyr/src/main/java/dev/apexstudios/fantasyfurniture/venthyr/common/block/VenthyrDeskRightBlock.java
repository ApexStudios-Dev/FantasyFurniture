package dev.apexstudios.fantasyfurniture.venthyr.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.DeskBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrDeskRightBlock extends DeskBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(-15D, 0D, 1D, -12D, 2D, 4D),
            box(-15D, 0D, 12D, -12D, 2D, 15D),
            box(12D, 0D, 12D, 15D, 2D, 15D),
            box(12D, 0D, 1D, 15D, 2D, 4D),
            box(12.5D, 2D, 1.5D, 14.5D, 13D, 3.5D),
            box(-14.5D, 2D, 1.5D, -12.5D, 13D, 3.5D),
            box(-14.5D, 2D, 12.5D, -12.5D, 13D, 14.5D),
            box(12.5D, 2D, 12.5D, 14.5D, 13D, 14.5D),
            box(-16D, 13D, 0D, 16D, 16D, 16D),
            box(-12D, 9D, 2D, -5D, 13D, 11D),
            box(12.5D, 9D, 3.5D, 14.5D, 13D, 12.5D),
            box(-14.5D, 9D, 3.5D, -12.5D, 13D, 12.5D)
    );

    public VenthyrDeskRightBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
