package dev.apexstudios.fantasyfurniture.nordic.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.DeskBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NordicDeskLeftBlock extends DeskBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(13D, 0D, 0D, 15D, 9D, 2D),
            box(13D, 7D, 1D, 15D, 13D, 3D),
            box(13D, 7D, 13D, 15D, 13D, 15D),
            box(-15D, 7D, 13D, -13D, 13D, 15D),
            box(-15D, 0D, 0D, -13D, 9D, 2D),
            box(-15D, 0D, 14D, -13D, 9D, 16D),
            box(13D, 0D, 14D, 15D, 9D, 16D),
            box(-16D, 13D, 0D, 16D, 16D, 16D),
            box(-15D, 7D, 1D, -13D, 13D, 3D),
            box(5D, 9D, 2D, 12D, 13D, 11D)
    );

    public NordicDeskLeftBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
