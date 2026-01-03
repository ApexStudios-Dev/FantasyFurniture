package dev.apexstudios.fantasyfurniture.royal.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.PaintingSmallBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class RoyalPaintingSmallBlock extends PaintingSmallBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(0D, 0D, 14D, 3D, 3D, 16D),
            box(0D, 13D, 14D, 3D, 16D, 16D),
            box(13D, 13D, 14D, 16D, 16D, 16D),
            box(13D, 0D, 14D, 16D, 3D, 16D),
            box(3D, 1D, 14D, 13D, 3D, 16D),
            box(3D, 13D, 14D, 13D, 15D, 16D),
            box(1D, 3D, 14D, 15D, 13D, 16D)
    );

    public RoyalPaintingSmallBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
