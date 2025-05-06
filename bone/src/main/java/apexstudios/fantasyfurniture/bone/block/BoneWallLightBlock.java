package apexstudios.fantasyfurniture.bone.block;

import dev.apexstudios.fantasyfurniture.block.WallLightBlock;
import java.util.Map;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class BoneWallLightBlock extends WallLightBlock {
    public static final VoxelShape SHAPE = box(6D, 4D, 9D, 10D, 15D, 16D);

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public BoneWallLightBlock(Properties properties) {
        super(properties);
    }
}
