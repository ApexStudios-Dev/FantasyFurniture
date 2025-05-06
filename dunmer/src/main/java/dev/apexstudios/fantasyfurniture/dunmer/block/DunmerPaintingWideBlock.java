package dev.apexstudios.fantasyfurniture.dunmer.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.PaintingWideBlock;
import java.util.Map;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class DunmerPaintingWideBlock extends PaintingWideBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(-16D, 0D, 14D, 16D, 2D, 16D),
            box(-16D, 14D, 14D, 16D, 16D, 16D),
            box(-15D, 2D, 14D, 15D, 14D, 16D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public DunmerPaintingWideBlock(Properties properties) {
        super(properties);
    }
}
