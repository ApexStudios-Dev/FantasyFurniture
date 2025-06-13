package dev.apexstudios.fantasyfurniture.venthyr.block;

import dev.apexstudios.fantasyfurniture.block.PaintingWideBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrPaintingWideBlock extends PaintingWideBlock {
    public static final VoxelShape SHAPE = box(-16D, 0D, 14D, 16D, 16D, 16D);

    public VenthyrPaintingWideBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
