package dev.apexstudios.fantasyfurniture.necrolord.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.DrawerBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NecrolordDrawerBlock extends DrawerBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(0D, 0D, 0D, 3D, 2D, 3D),
            box(0D, 14D, 0D, 3D, 16D, 3D),
            box(0D, 14D, 13D, 3D, 16D, 16D),
            box(0D, 0D, 13D, 3D, 2D, 16D),
            box(13D, 0D, 13D, 16D, 2D, 16D),
            box(13D, 14D, 13D, 16D, 16D, 16D),
            box(13D, 14D, 0D, 16D, 16D, 3D),
            box(13D, 0D, 0D, 16D, 2D, 3D),
            box(1D, 0D, 1D, 15D, 16D, 15D)
    );

    public NecrolordDrawerBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
