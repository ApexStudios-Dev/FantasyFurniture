package dev.apexstudios.fantasyfurniture.dunmer.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.CushionBlock;
import java.util.Map;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class DunmerCushionBlock extends CushionBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(3D, 0D, 3D, 5D, 4D, 5D),
            box(3D, 0D, 11D, 5D, 4D, 13D),
            box(11D, 0D, 11D, 13D, 4D, 13D),
            box(11D, 0D, 3D, 13D, 4D, 5D),
            box(2D, 4D, 2D, 14D, 5D, 14D),
            box(2.5D, 5D, 2.5D, 13.5D, 7D, 13.5D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public DunmerCushionBlock(Properties properties) {
        super(properties);
    }
}
