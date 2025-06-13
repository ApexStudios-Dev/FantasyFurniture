package dev.apexstudios.fantasyfurniture.venthyr.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.DrawerBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrDrawerBlock extends DrawerBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(.5D, 0D, .5D, 3.5D, 2D, 3.5D),
            box(.5D, 0D, 12.5D, 3.5D, 2D, 15.5D),
            box(12.5D, 0D, 12.5D, 15.5D, 2D, 15.5D),
            box(12.5D, 0D, .5D, 15.5D, 2D, 3.5D),
            box(13D, 2D, 1D, 15D, 13D, 3D),
            box(1D, 2D, 1D, 3D, 13D, 3D),
            box(1D, 2D, 13D, 3D, 13D, 15D),
            box(13D, 2D, 13D, 15D, 13D, 15D),
            box(1D, 5D, 1D, 15D, 13D, 15D),
            box(0D, 13D, 0D, 16D, 16D, 16D)
    );

    public VenthyrDrawerBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
