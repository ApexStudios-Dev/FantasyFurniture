package dev.apexstudios.fantasyfurniture.dunmer.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.PaintingWideBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class DunmerPaintingWideBlock extends PaintingWideBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(-16D, 0D, 14D, 16D, 2D, 16D),
            box(-16D, 14D, 14D, 16D, 16D, 16D),
            box(-15D, 2D, 14D, 15D, 14D, 16D)
    );

    public DunmerPaintingWideBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
