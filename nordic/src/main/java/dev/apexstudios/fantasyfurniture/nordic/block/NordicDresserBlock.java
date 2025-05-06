package dev.apexstudios.fantasyfurniture.nordic.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.DresserBlock;
import java.util.Map;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NordicDresserBlock extends DresserBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(-15D, 0D, 1D, 15D, 16D, 15D),
            box(-16D, 13D, 14D, 16D, 16D, 16D),
            box(-16D, 13D, 0D, 16D, 16D, 2D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public NordicDresserBlock(Properties properties) {
        super(properties);
    }
}
