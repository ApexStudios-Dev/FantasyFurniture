package dev.apexstudios.fantasyfurniture.necrolord.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.DeskBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NecrolordDeskRightBlock extends DeskBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(-15D, 0D, 1D, -12D, 2D, 4D),
            box(-15D, 0D, 12D, -12D, 2D, 15D),
            box(-4D, 0D, 12D, -1D, 2D, 15D),
            box(-4D, 0D, 1D, -1D, 2D, 4D),
            box(-4D, 12D, 1D, -1D, 14D, 4D),
            box(-4D, 12D, 12D, -1D, 14D, 15D),
            box(-15D, 12D, 12D, -12D, 14D, 15D),
            box(-15D, 12D, 1D, -12D, 14D, 4D),
            box(-14D, 0D, 2D, -2D, 14D, 14D),
            box(11D, 0D, 1D, 15D, 2D, 5D),
            box(11D, 12D, 1D, 15D, 14D, 5D),
            box(11D, 12D, 11D, 15D, 14D, 15D),
            box(12D, 2D, 12D, 14D, 12D, 14D),
            box(12D, 2D, 2D, 14D, 12D, 4D),
            box(-16D, 14D, 0D, 16D, 16D, 16D),
            box(11D, 0D, 11D, 15D, 2D, 15D)
    );

    public NecrolordDeskRightBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
