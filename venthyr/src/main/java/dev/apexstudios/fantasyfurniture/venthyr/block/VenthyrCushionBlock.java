package dev.apexstudios.fantasyfurniture.venthyr.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.CushionBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrCushionBlock extends CushionBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(2D, 0D, 2D, 5D, 3D, 5D),
            box(2D, 0D, 11D, 5D, 3D, 14D),
            box(11D, 0D, 11D, 14D, 3D, 14D),
            box(11D, 0D, 2D, 14D, 3D, 5D),
            box(1D, 3D, 1D, 15D, 4D, 15D),
            box(2D, 4D, 2D, 14D, 7D, 14D)
    );

    public VenthyrCushionBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
