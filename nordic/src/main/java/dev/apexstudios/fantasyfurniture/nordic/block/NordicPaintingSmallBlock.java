package dev.apexstudios.fantasyfurniture.nordic.block;

import dev.apexstudios.fantasyfurniture.block.PaintingSmallBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NordicPaintingSmallBlock extends PaintingSmallBlock {
    public static final VoxelShape SHAPE = box(0D, 0D, 14D, 16D, 16D, 16D);

    public NordicPaintingSmallBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
