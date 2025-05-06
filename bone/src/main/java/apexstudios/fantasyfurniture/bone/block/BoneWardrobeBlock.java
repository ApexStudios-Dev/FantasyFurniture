package apexstudios.fantasyfurniture.bone.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.WardrobeBlock;
import java.util.Map;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class BoneWardrobeBlock extends WardrobeBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(-15D, 0D, 1, -11D, 2D, 5D),
            box(-14D, 2D, 2D, -12D, 3D, 4D),
            box(-15D, 0D, 11D, -11D, 2D, 15D),
            box(-14D, 2D, 12D, -12D, 3D, 14D),
            box(11D, 0D, 11D, 15D, 2D, 15D),
            box(12D, 2D, 12D, 14D, 3D, 14D),
            box(12D, 2D, 2D, 14D, 3D, 4D),
            box(11D, 0D, 1D, 15D, 2D, 5D),
            box(-15D, 3D, 1D, 15D, 5D, 15D),
            box(-12D, 5D, 3D, 12D, 30D, 13D),
            box(-15D, 30D, 1D, 15D, 32D, 15D),
            box(-15D, 39D, 1D, -11D, 41D, 5D),
            box(-15D, 42D, 1D, -11D, 44D, 5D),
            box(-15D, 42D, 11D, -11D, 44D, 15D),
            box(-15D, 39D, 11D, -11D, 41D, 15D),
            box(11D, 42D, 11D, 15D, 44D, 15D),
            box(11D, 39D, 11D, 15D, 41D, 15D),
            box(11D, 39D, 1D, 15D, 41D, 5D),
            box(-14D, 32D, 2D, 14D, 42D, 14D),
            box(11D, 42D, 1D, 15D, 44D, 5D),
            box(12D, 5D, 2D, 14D, 30D, 4D),
            box(-1D, 5D, 2D, 1D, 30D, 4D),
            box(-14D, 5D, 2D, -12D, 30D, 4D),
            box(-14D, 5D, 12D, -12D, 30D, 14D),
            box(12D, 5D, 12D, 14D, 30D, 14D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public BoneWardrobeBlock(Properties properties) {
        super(properties);
    }
}
