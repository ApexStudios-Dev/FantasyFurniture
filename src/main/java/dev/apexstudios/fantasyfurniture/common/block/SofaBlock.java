package dev.apexstudios.fantasyfurniture.common.block;

import dev.apexstudios.fantasyfurniture.common.block.property.SofaConnection;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SofaBlock extends SeatBlock {
    private final Map<Direction, VoxelShape> leftShapes;
    private final Map<Direction, VoxelShape> rightShapes;
    private final Map<Direction, VoxelShape> centerShapes;
    private final Map<Direction, VoxelShape> cornerShapes;
    private final Map<Direction, VoxelShape> singleShapes;

    public SofaBlock(Properties properties, VoxelShape leftShape, VoxelShape rightShape, VoxelShape centerShape, VoxelShape cornerShape, VoxelShape singleShape) {
        super(properties);

        leftShapes = Shapes.rotateHorizontal(leftShape);
        rightShapes = Shapes.rotateHorizontal(rightShape);
        centerShapes = Shapes.rotateHorizontal(centerShape);
        cornerShapes = Shapes.rotateHorizontal(cornerShape);
        singleShapes = Shapes.rotateHorizontal(singleShape);

        registerDefaultState(defaultBlockState().setValue(SofaConnection.PROPERTY, SofaConnection.BOTH));
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        var shapes = switch (blockState.getValue(SofaConnection.PROPERTY)) {
            case LEFT -> leftShapes;
            case RIGHT -> rightShapes;
            case BOTH -> centerShapes;
            case NONE -> singleShapes;
            case CORNER_INNER, CORNER_OUTER -> cornerShapes;
        };

        return FurnitureUtil.getShape(shapes, blockState, pos);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(SofaConnection.PROPERTY);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        var blockState = super.getStateForPlacement(context);

        if(blockState == null)
            return null;

        var level = context.getLevel();
        var pos = context.getClickedPos();

        return SofaConnection.setConnection(level, pos, blockState);
    }

    @Override
    public BlockState updateShape(BlockState blockState, LevelReader level, ScheduledTickAccess tickAccess, BlockPos pos, Direction facing, BlockPos neighborPos, BlockState neighborBlockState, RandomSource random) {
        var result = blockState;

        if(facing.getAxis().isHorizontal())
            result = SofaConnection.setConnection(level, pos, blockState);

        return super.updateShape(result, level, tickAccess, pos, facing, neighborPos, neighborBlockState, random);
    }
}
