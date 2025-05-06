package dev.apexstudios.fantasyfurniture.nordic.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.WallLightBlock;
import java.util.Map;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NordicWallLightBlock extends WallLightBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(6D, 5D, 15D, 10D, 11D, 16D),
            box(6D, 2D, 8D, 10D, 15D, 15D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public NordicWallLightBlock(Properties properties) {
        super(properties);
    }
}
