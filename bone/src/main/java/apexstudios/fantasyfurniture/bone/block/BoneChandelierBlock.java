package apexstudios.fantasyfurniture.bone.block;

import dev.apexstudios.fantasyfurniture.block.ChandelierBlock;
import java.util.Map;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class BoneChandelierBlock extends ChandelierBlock {
    public static final VoxelShape SHAPE = box(0D, 0D, 0D, 16D, 16D, 16D);
    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public BoneChandelierBlock(Properties properties) {
        super(properties);
    }
}
