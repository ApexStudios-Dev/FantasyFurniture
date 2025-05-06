package dev.apexstudios.fantasyfurniture.nordic.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.CounterBlock;
import java.util.Map;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NordicCounterBlock extends CounterBlock {
    public static final VoxelShape CORNER_SHAPE = ApexShapes.join(
            box(0D, 0D, 0D, 13D, 13D, 4D),
            box(0D, 0D, 3D, 16D, 13D, 16D),
            box(0D, 13D, 0D, 16D, 16D, 16D)
    );

    public static final VoxelShape SHAPE = ApexShapes.join(
            box(0D, 0D, 3D, 16D, 13D, 16D),
            box(0D, 13D, 0D, 16D, 16D, 16D),
            box(1D, 1D, 2D, 15D, 12D, 3D)
    );

    public static final Map<Direction, VoxelShape> CORNER_FACING_SHAPES = Shapes.rotateHorizontal(CORNER_SHAPE);
    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public NordicCounterBlock(Properties properties) {
        super(properties);
    }
}
