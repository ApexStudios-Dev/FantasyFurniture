package dev.apexstudios.fantasyfurniture.nordic.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.DrawerBlock;
import java.util.Map;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NordicDrawerBlock extends DrawerBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(1D, 0D, 1D, 15D, 13D, 15D),
            box(0D, 13D, 0D, 16D, 16D, 16D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public NordicDrawerBlock(Properties properties) {
        super(properties);
    }
}
