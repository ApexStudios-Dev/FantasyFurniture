package dev.apexstudios.fantasyfurniture.nordic.block;

import dev.apexstudios.fantasyfurniture.block.PaintingWideBlock;
import java.util.Map;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NordicPaintingWideBlock extends PaintingWideBlock {
    public static final VoxelShape SHAPE = box(-16D, 0D, 14D, 16D, 16D, 16D);
    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public NordicPaintingWideBlock(Properties properties) {
        super(properties);
    }
}
