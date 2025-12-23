package dev.apexstudios.fantasyfurniture.royal.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.PaintingWideBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class RoyalPaintingWideBlock extends PaintingWideBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(-16D, 0D, 14D, -13D, 3D, 16D),
            box(-16D, 13D, 14D, -13D, 16D, 16D),
            box(13D, 13D, 14D, 16D, 16D, 16D),
            box(13D, 0D, 14D, 16D, 3D, 16D),
            box(-13D, 1D, 14D, 13D, 3D, 16D),
            box(-13D, 13D, 14D, 13D, 15D, 16D),
            box(-15D, 3D, 14D, 15D, 13D, 16D)
    );

    public RoyalPaintingWideBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
