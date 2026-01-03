package dev.apexstudios.fantasyfurniture.necrolord.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.PaintingWideBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NecrolordPaintingWideBlock extends PaintingWideBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(-16D, 0D, 13D, -13D, 3D, 16D),
            box(-16D, 13D, 13D, -13D, 16D, 16D),
            box(13D, 13D, 13D, 16D, 16D, 16D),
            box(13D, 0D, 13D, 16D, 3D, 16D),
            box(-15D, 1D, 14D, 15D, 15D, 16D)
    );

    public NecrolordPaintingWideBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
