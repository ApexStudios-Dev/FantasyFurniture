package dev.apexstudios.fantasyfurniture.dunmer.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.DrawerBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class DunmerDrawerBlock extends DrawerBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(1D, 0D, 1D, 3D, 14D, 3D),
            box(1D, 0D, 13D, 3D, 14D, 15D),
            box(13D, 0D, 13D, 15D, 14D, 15D),
            box(13D, 0D, 1D, 15D, 14D, 3D),
            box(0D, 14D, 0D, 16D, 16D, 16D),
            box(1D, 6D, 1D, 15D, 8D, 15D),
            box(2D, 6D, 2D, 14D, 14D, 14D)
    );

    public DunmerDrawerBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
