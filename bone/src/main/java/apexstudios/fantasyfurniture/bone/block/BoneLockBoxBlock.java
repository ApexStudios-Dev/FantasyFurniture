package apexstudios.fantasyfurniture.bone.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.LockBoxBlock;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureBlockComponentHolder;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class BoneLockBoxBlock extends LockBoxBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(2D, 0D, 4D, 5D, 2D, 7D),
            box(2D, 0D, 9D, 5D, 2D, 12D),
            box(11D, 0D, 9D, 14D, 2D, 12D),
            box(11D, 0D, 4D, 14D, 2D, 7D),
            box(1D, 2D, 3D, 15D, 4D, 13D),
            box(1.5D, 4D, 3.5D, 14.5D, 12.25D, 12.5D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public BoneLockBoxBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return FurnitureBlockComponentHolder.getShape(FACING_SHAPES, blockState, pos);
    }
}
