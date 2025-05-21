package dev.apexstudios.fantasyfurniture.venthyr.block;

import dev.apexstudios.fantasyfurniture.block.PaintingSmallBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrPaintingSmallBlock extends PaintingSmallBlock {
    public static final VoxelShape SHAPE = box(0D, 0D, 14D, 16D, 16D, 16D);

    public VenthyrPaintingSmallBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
