package dev.apexstudios.fantasyfurniture.block;

import dev.apexstudios.apexcore.lib.component.ComponentRegistrar;
import dev.apexstudios.apexcore.lib.component.block.BlockComponent;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentHelper;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.component.block.types.FacingBlockComponent;
import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureBlockComponentHolder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class TableBlock extends FurnitureBlockComponentHolder {
    public static final VoxelShape SHAPE_TABLE_TOP = box(0D, 13D, 0D, 16D, 16D, 16D);
    public static final VoxelShape SHAPE_TABLE_LEG = box(13D, 0D, 1D, 15D, 13D, 3D);

    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;

    public TableBlock(Properties properties) {
        super(properties);

        registerDefaultState(defaultBlockState()
                .setValue(NORTH, false)
                .setValue(EAST, false)
                .setValue(SOUTH, false)
                .setValue(WEST, false)
        );
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        var facing = getComponentOrThrow(BlockComponentTypes.FACING).get(blockState);
        return ApexShapes.rotateHorizontal(getShape(blockState, SHAPE_TABLE_TOP, SHAPE_TABLE_LEG), facing);
    }

    @Override
    protected void registerComponents(ComponentRegistrar<BlockComponent> registrar) {
        super.registerComponents(registrar);

        FacingBlockComponent.registerHorizontal(registrar);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, SOUTH, WEST);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        var blockState = super.getStateForPlacement(context);

        if(blockState != null)
            return get(context.getLevel(), context.getClickedPos(), blockState);

        return null;
    }

    @Override
    public BlockState updateShape(BlockState blockState, LevelReader level, ScheduledTickAccess tickAccess, BlockPos pos, Direction facing, BlockPos neighborPos, BlockState neighborBlockState, RandomSource random) {
        var result = get(level, pos, blockState, facing);
        return super.updateShape(result, level, tickAccess, pos, facing, neighborPos, neighborBlockState, random);
    }

    public static BlockState get(BlockGetter level, BlockPos pos, BlockState blockState) {
        var result = blockState;

        for(var direction : Direction.Plane.HORIZONTAL) {
            result = get(level, pos, result, direction);
        }

        return result;
    }

    public static BlockState get(BlockGetter level, BlockPos pos, BlockState blockState, Direction side) {
        if(!side.getAxis().isHorizontal())
            return blockState;

        var result = blockState;

        var facing = BlockComponentHelper.getComponentOrThrow(blockState, BlockComponentTypes.FACING).get(blockState);
        var frontPos = pos.relative(facing);
        var frontBlockState = level.getBlockState(frontPos);
        var frontProperty = property(facing);

        result = result.setValue(frontProperty, canConnect(blockState, frontBlockState));

        if(side == facing)
            return result;

        var sidePos = pos.relative(side);
        var sideBlockState = level.getBlockState(sidePos);
        var sideProperty = property(side);

        result = result.setValue(sideProperty, canConnect(blockState, sideBlockState));

        return result;
    }

    public static BooleanProperty property(Direction direction) {
        return switch (direction) {
            case NORTH -> NORTH;
            case EAST -> EAST;
            case SOUTH -> SOUTH;
            case WEST -> WEST;
            default -> throw new IllegalStateException("Illegal connection direction: " + direction.name());
        };
    }

    public static boolean canConnect(BlockState source, BlockState target) {
        if(!target.is(source.getBlock()))
            return false;

        var facing = BlockComponentHelper.getComponentOrThrow(source, BlockComponentTypes.FACING);
        return target.hasProperty(facing.getProperty()) && facing.get(source) == facing.get(target);
    }

    public static Direction getFacingForConnection(Direction facing) {
        return facing.getAxis() == Direction.Axis.X ? facing.getOpposite() : facing;
    }

    public static VoxelShape getShape(BlockState blockState, VoxelShape top, VoxelShape leg) {
        var result = top;

        var facing = BlockComponentHelper.getComponentOrThrow(blockState, BlockComponentTypes.FACING).get(blockState);
        var north = blockState.getValue(NORTH);
        var east = blockState.getValue(EAST);
        var south = blockState.getValue(SOUTH);
        var west = blockState.getValue(WEST);

        var count = 0;

        if(north)
            count++;
        if(east)
            count++;
        if(south)
            count++;
        if(west)
            count++;

        // single
        if(count == 0) {
            for(var direction : Direction.Plane.HORIZONTAL) {
                result = ApexShapes.join(result, ApexShapes.rotateHorizontal(leg, direction));
            }
        }
        // caps
        else if(count == 1) {
            Direction face = null;

            if(north)
                face = Direction.EAST;
            else if(east)
                face = Direction.SOUTH;
            else if(south)
                face = Direction.WEST;
            else if(west)
                face = Direction.NORTH;

            if(face != null)
                result = ApexShapes.join(result, rotateShape(leg, face, facing), rotateShape(leg, face.getClockWise(), facing));
        }
        // corners
        else if(count == 2) {
            Direction face = null;

            if(north && west)
                face = Direction.EAST;
            else if(north && east)
                face = Direction.SOUTH;
            else if(east && south)
                face = Direction.WEST;
            else if(south && west)
                face = Direction.NORTH;

            if(face != null)
                result = ApexShapes.join(result, rotateShape(leg, face, facing));
        }

        return result;
    }

    private static VoxelShape rotateShape(VoxelShape shape, Direction direction, Direction facing) {
        var rotation = switch (getFacingForConnection(facing)) {
            case EAST -> Rotation.CLOCKWISE_90;
            case SOUTH -> Rotation.CLOCKWISE_180;
            case WEST -> Rotation.COUNTERCLOCKWISE_90;
            default -> Rotation.NONE;
        };

        return ApexShapes.rotateHorizontal(shape, rotation.rotate(direction));
    }
}
