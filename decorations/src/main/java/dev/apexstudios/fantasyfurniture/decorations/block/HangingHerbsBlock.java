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

public final class HangingHerbsBlock extends SimpleHorizontalDirectionalBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(2D, 14D, 12.5D, 3D, 16D, 13.5D),
            box(13D, 14D, 12.5D, 14D, 16D, 13.5D),
            box(1D, 12D, 12D, 15D, 14D, 14D),
            box(.5D, 1D, 10.5D, 15.5D, 12D, 15.5D)
    );

    public static final Map<Direction, VoxelShape> SHAPES = Shapes.rotateHorizontal(SHAPE);

    public HangingHerbsBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        var facing = blockState.getValue(FACING);
        return SHAPES.get(facing);
    }
}
