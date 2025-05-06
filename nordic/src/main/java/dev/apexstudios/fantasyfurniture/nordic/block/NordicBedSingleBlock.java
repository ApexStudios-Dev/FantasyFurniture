package dev.apexstudios.fantasyfurniture.nordic.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.BedSingleBlock;
import java.util.Map;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NordicBedSingleBlock extends BedSingleBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(0D, 0D, 0D, 16D, 14D, 2D),
            box(0D, 0D, 30D, 16D, 14D, 32D),
            box(0D, 3D, 2D, 16D, 5D, 30D),
            box(1D, 5D, 2D, 15D, 8D, 30D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public NordicBedSingleBlock(Properties properties) {
        super(properties);
    }
}
