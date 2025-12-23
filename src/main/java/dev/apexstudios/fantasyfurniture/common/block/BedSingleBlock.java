package dev.apexstudios.fantasyfurniture.common.block;

import dev.apexstudios.apexcore.api.block.SimpleBedBlock;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BedSingleBlock extends SimpleBedBlock {
    private final Map<Direction, VoxelShape> shapes;

    public BedSingleBlock(Properties properties, VoxelShape baseShape) {
        super(properties);

        shapes = Shapes.rotateHorizontal(baseShape);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        var direction = getConnectedDirection(blockState);
        var shape = shapes.get(direction);
        return shape.move(direction.getStepX(), direction.getStepY(), direction.getStepZ());
    }
}
