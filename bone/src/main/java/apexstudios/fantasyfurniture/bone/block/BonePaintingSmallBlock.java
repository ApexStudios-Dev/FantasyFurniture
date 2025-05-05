package apexstudios.fantasyfurniture.bone.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.PaintingSmallBlock;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class BonePaintingSmallBlock extends PaintingSmallBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(0D, 0D, 13D, 3D, 3D, 16D),
            box(13D, 0D, 13D, 16D, 3D, 16D),
            box(13D, 13D, 13D, 16D, 16D, 16D),
            box(0D, 13D, 13D, 3D, 16D, 16D),
            box(3D, 13D, 13.5D, 13D, 15.5D, 15.5D),
            box(3D, .5D, 13.5D, 13D, 3D, 15.5D),
            box(.5D, 3D, 13.5D, 15.5D, 13D, 15.5D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public BonePaintingSmallBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(FACING_SHAPES, blockState, facingProperty(), pos);
    }
}
