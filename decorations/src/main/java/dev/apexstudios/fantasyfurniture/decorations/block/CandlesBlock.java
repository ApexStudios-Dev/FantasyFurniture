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

public final class CandlesBlock extends SimpleHorizontalDirectionalBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(5D, 0D, 4D, 8D, 3D, 7D),
            box(9D, 0D, 5D, 12D, 6D, 8D),
            box(8D, 0D, 10D, 11D, 8D, 13D),
            box(4D, 0D, 9D, 7D, 5D, 12D)
    );

    public static final Map<Direction, VoxelShape> SHAPES = Shapes.rotateHorizontal(SHAPE);

    public CandlesBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        var facing = blockState.getValue(FACING);
        return SHAPES.get(facing);
    }
}
