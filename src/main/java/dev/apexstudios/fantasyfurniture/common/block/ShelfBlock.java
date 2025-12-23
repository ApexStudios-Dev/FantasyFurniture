package dev.apexstudios.fantasyfurniture.common.block;

import dev.apexstudios.apexcore.api.block.SimpleHorizontalDirectionalBlock;
import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.property.ShelfConnection;
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

public class ShelfBlock extends SimpleHorizontalDirectionalBlock {
    private final Map<Direction, VoxelShape> leftShapes;
    private final Map<Direction, VoxelShape> rightShapes;
    private final Map<Direction, VoxelShape> topShapes;

    public ShelfBlock(Properties properties, VoxelShape leftShape, VoxelShape rightShape, VoxelShape topShape) {
        super(properties);

        leftShapes = Shapes.rotateHorizontal(leftShape);
        rightShapes = Shapes.rotateHorizontal(rightShape);
        topShapes = Shapes.rotateHorizontal(topShape);

        registerDefaultState(defaultBlockState().setValue(ShelfConnection.PROPERTY, ShelfConnection.BOTH));
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        var facing = blockState.getValue(FACING);
        var leftShape = leftShapes.get(facing);
        var rightShape = rightShapes.get(facing);
        var topShape = topShapes.get(facing);

        return switch (blockState.getValue(ShelfConnection.PROPERTY)) {
            case LEFT -> ApexShapes.join(leftShape, topShape);
            case RIGHT -> ApexShapes.join(rightShape, topShape);
            case NONE -> ApexShapes.join(leftShape, rightShape, topShape);
            case BOTH -> topShape;
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(ShelfConnection.PROPERTY);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        var blockState = super.getStateForPlacement(context);

        if(blockState == null)
            return null;

        var level = context.getLevel();
        var pos = context.getClickedPos();
        return ShelfConnection.setConnection(level, pos, blockState);
    }

    @Override
    public BlockState updateShape(BlockState blockState, LevelReader level, ScheduledTickAccess tickAccess, BlockPos pos, Direction facing, BlockPos neighborPos, BlockState neighborBlockState, RandomSource random) {
        var result = blockState;

        if(facing.getAxis().isHorizontal())
            result = ShelfConnection.setConnection(level, pos, result);

        return super.updateShape(result, level, tickAccess, pos, facing, neighborPos, neighborBlockState, random);
    }
}
