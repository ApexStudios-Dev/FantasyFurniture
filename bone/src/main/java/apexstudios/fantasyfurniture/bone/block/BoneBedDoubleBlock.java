package apexstudios.fantasyfurniture.bone.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.BedDoubleBlock;
import java.util.Map;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class BoneBedDoubleBlock extends BedDoubleBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(-16D, 0D, 0D, -12D, 2D, 4D),
            box(-16D, 0D, 28D, -12D, 2D, 32D),
            box(12D, 0D, 28D, 16D, 2D, 32D),
            box(12D, 0D, 0D, 16D, 2D, 4D),
            box(12D, 12D, 28D, 16D, 14D, 32D),
            box(-16D, 12D, 28D, -12D, 14D, 32D),
            box(12D, 12D, 0D, 16D, 14D, 4D),
            box(-16D, 12D, 0D, -12D, 14D, 4D),
            box(13D, 2D, 29D, 15D, 12D, 31D),
            box(-15D, 2D, 29D, -13D, 12D, 31D),
            box(-15D, 2D, 1D, -13D, 12D, 3D),
            box(13D, 2D, 1D, 15D, 12D, 3D),
            box(-13D, 4D, 1D, 13D, 13D, 3D),
            box(-13D, 4D, 29D, 13D, 13D, 31D),
            box(-15D, 4D, 3D, 15D, 8D, 29D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public BoneBedDoubleBlock(Properties properties) {
        super(properties);
    }
}
