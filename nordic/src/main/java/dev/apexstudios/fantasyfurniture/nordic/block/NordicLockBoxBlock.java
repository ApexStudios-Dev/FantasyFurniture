package dev.apexstudios.fantasyfurniture.nordic.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.LockBoxBlock;
import java.util.Map;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NordicLockBoxBlock extends LockBoxBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(2D, 0D, 3D, 14D, 9D, 13D),
            box(2D, 9D, 5D, 14D, 10D, 11D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public NordicLockBoxBlock(Properties properties) {
        super(properties);
    }
}
