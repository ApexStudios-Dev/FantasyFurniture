package dev.apexstudios.fantasyfurniture.necrolord.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.PaintingSmallBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NecrolordPaintingSmallBlock extends PaintingSmallBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(0D, 0D, 13D, 3D, 3D, 16D),
            box(0D, 13D, 13D, 3D, 16D, 16D),
            box(13D, 13D, 13D, 16D, 16D, 16D),
            box(13D, 0D, 13D, 16D, 3D, 16D),
            box(1D, 1D, 14D, 15D, 15D, 16D)
    );

    public NecrolordPaintingSmallBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
