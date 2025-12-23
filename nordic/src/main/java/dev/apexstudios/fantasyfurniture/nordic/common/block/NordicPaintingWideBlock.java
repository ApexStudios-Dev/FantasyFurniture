package dev.apexstudios.fantasyfurniture.nordic.common.block;

import dev.apexstudios.fantasyfurniture.common.block.PaintingWideBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NordicPaintingWideBlock extends PaintingWideBlock {
    public static final VoxelShape SHAPE = box(-16D, 0D, 14D, 16D, 16D, 16D);

    public NordicPaintingWideBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
