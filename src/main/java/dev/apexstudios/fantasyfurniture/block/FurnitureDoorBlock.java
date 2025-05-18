package dev.apexstudios.fantasyfurniture.block;

import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FurnitureDoorBlock extends DoorBlock {
    private static final Map<Direction, VoxelShape> SHAPES = Shapes.rotateHorizontal(box(0D, 0D, 0D, 3D, 32D, 16D));

    public FurnitureDoorBlock(Properties properties, BlockSetType blockSet) {
        super(blockSet, properties);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        var facing = blockState.getValue(FACING).getCounterClockWise();
        var dir = blockState.getValue(OPEN) ? (blockState.getValue(HINGE) == DoorHingeSide.RIGHT ? facing.getCounterClockWise() : facing.getClockWise()) : facing;

        var shape = SHAPES.get(dir);

        if(blockState.getValue(HALF) == DoubleBlockHalf.UPPER)
            return shape.move(0D, -1D, 0D);

        return shape;
    }
}
