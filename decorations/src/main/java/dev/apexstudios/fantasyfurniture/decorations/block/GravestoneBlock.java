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

public final class GravestoneBlock extends SimpleHorizontalDirectionalBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(1.5D, 0D, 6D, 14.5D, 2D, 10D),
            box(2D, 2D, 6.5D, 14D, 15D, 9.5D),
            box(4D, 15D, 6.5D, 12D, 16D, 9.5D)
    );

    public static final Map<Direction, VoxelShape> SHAPES = Shapes.rotateHorizontal(SHAPE);

    public GravestoneBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        var facing = blockState.getValue(FACING);
        return SHAPES.get(facing);
    }
}
