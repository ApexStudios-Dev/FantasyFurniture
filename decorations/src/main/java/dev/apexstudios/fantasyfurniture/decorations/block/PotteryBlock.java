package dev.apexstudios.fantasyfurniture.decorations.block;

import dev.apexstudios.apexcore.lib.block.SimpleHorizontalDirectionalBlock;
import dev.apexstudios.apexcore.lib.util.ApexShapes;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class PotteryBlock extends SimpleHorizontalDirectionalBlock {
    public static final VoxelShape SHAPE_0 = ApexShapes.join(
            box(2D, 0D, 2D, 7D, 3D, 7D),
            box(3.5D, 3D, 3.5D, 5.5D, 4D, 5.5D),
            box(3D, 4D, 3D, 6D, 5D, 6D),
            box(7D, 0D, 6D, 15D, 6D, 14D),
            box(9.5D, 6D, 8.5D, 12.5D, 8D, 11.5D),
            box(9D, 8D, 8D, 13D, 9D, 12D)
    );

    public static final VoxelShape SHAPE_1 = ApexShapes.join(
            box(5.5D, 0D, 1.5D, 9.5D, 3D, 5.75D),
            box(6.75D, 3D, 2.75D, 8.25D, 4D, 4.25D),
            box(6.15D, 4D, 2.15D, 8.85D, 5D, 4.85D),
            box(3.1500000000000004D, 7D, 7.450000000000001D, 5.85D, 8D, 10.15D),
            box(2.55D, 0D, 7.1D, 6.549999999999999D, 5, 11.1),
            box(3.75D, 5D, 8.05D, 5.25D, 7D, 9.55D),
            box(9.5D, 5D, 8.5D, 12.5D, 6D, 11.5D),
            box(10D, 4D, 9D, 12D, 5D, 11D),
            box(8D, 0D, 7D, 14D, 4D, 13D)
    );

    private final Map<Direction, VoxelShape> shapes;

    public PotteryBlock(Properties properties, VoxelShape baseShape) {
        super(properties);

        shapes = Shapes.rotateHorizontal(baseShape);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        var facing = blockState.getValue(FACING);
        return shapes.get(facing);
    }
}
