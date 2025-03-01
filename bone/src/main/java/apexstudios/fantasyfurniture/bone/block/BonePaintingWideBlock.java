package apexstudios.fantasyfurniture.bone.block;

import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.PaintingWideBlock;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class BonePaintingWideBlock extends PaintingWideBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(-16D, 0D, 13D, -13D, 3D, 16D),
            box(13D, 0D, 13D, 16D, 3D, 16D),
            box(13D, 13D, 13D, 16D, 16D, 16D),
            box(-16D, 13D, 13D, -13D, 16D, 16D),
            box(-13D, 13D, 13.5D, 13D, 15.5D, 15.5D),
            box(-13D, .5D, 13.5D, 13D, 3D, 15.5D),
            box(-15.5D, 3D, 13.5D, 15.5D, 13D, 15.5D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = ApexShapes.rotateHorizontal(SHAPE);

    public BonePaintingWideBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(FACING_SHAPES, blockState, pos);
    }
}
