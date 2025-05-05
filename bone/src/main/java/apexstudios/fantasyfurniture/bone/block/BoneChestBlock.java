package apexstudios.fantasyfurniture.bone.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.ChestBlock;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class BoneChestBlock extends ChestBlock {
    public static final VoxelShape SHAPE = ApexShapes. join(
            box(11D, 0D, 2D, 14D, 2D, 5D),
            box(-14D, 0D, 2D, -11D, 2D, 5D),
            box(-14D, 0D, 11D, -11D, 2D, 14D),
            box(11D, 0D, 11D, 14D, 2D, 14D),
            box(11.5D, 2D, 11.5D, 13.5D, 11D, 13.5D),
            box(11.5D, 2D, 2.5D, 13.5D, 11D, 4.5D),
            box(-13.5D, 2D, 2.5D, -11.5D, 11D, 4.5D),
            box(-13.5D, 2D, 11.5D, -11.5D, 11D, 13.5D),
            box(-14D, 11D, 2D, 14D, 13D, 14D),
            box(-14D, 3D, 2D, 14D, 5D, 14D),
            box(-13D, 5D, 3D, 13D, 11D, 13D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public BoneChestBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(FACING_SHAPES, blockState, facingProperty(), pos);
    }
}
