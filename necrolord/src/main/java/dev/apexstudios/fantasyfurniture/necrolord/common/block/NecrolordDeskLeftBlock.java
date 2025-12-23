package dev.apexstudios.fantasyfurniture.necrolord.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.DeskBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NecrolordDeskLeftBlock extends DeskBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(12D, 0D, 1D, 15D, 2D, 4D),
            box(12D, 0D, 12D, 15D, 2D, 15D),
            box(1D, 0D, 12D, 4D, 2D, 15D),
            box(1D, 0D, 1D, 4D, 2D, 4D),
            box(1D, 12D, 1D, 4D, 14D, 4D),
            box(1D, 12D, 12D, 4D, 14D, 15D),
            box(12D, 12D, 12D, 15D, 14D, 15D),
            box(12D, 12D, 1D, 15D, 14D, 4D),
            box(2D, 0D, 2D, 14D, 14D, 14D),
            box(-15D, 0D, 1D, -11D, 2D, 5D),
            box(-15D, 12D, 1D, -11D, 14D, 5D),
            box(-15D, 12D, 11D, -11D, 14D, 15D),
            box(-14D, 2D, 12D, -12D, 12D, 14D),
            box(-14D, 2D, 2D, -12D, 12D, 4D),
            box(-16D, 14D, 0D, 16D, 16D, 16D),
            box(-15D, 0D, 11D, -11D, 2D, 15D)
    );

    public NecrolordDeskLeftBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
