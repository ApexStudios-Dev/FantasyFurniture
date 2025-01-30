package dev.apexstudios.fantasyfurniture.block.property;

import java.util.function.BiFunction;
import java.util.function.Function;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public enum SofaConnection implements StringRepresentable {
    LEFT("left"),
    RIGHT("right"),
    BOTH("center"),
    NONE("single"),
    CORNER_INNER("corner_inner"),
    CORNER_OUTER("corner_outer");

    public static final EnumProperty<SofaConnection> PROPERTY = EnumProperty.create("connection", SofaConnection.class);

    private final String serializedName;

    SofaConnection(String serializedName) {
        this.serializedName = serializedName;
    }

    @Override
    public String getSerializedName() {
        return serializedName;
    }

    public boolean isCorner() {
        return this == CORNER_INNER || this == CORNER_OUTER;
    }

    public String getModelSuffix() {
        if(isCorner())
            return "_corner";

        return '_' + serializedName;
    }

    public static BlockState setConnection(BlockGetter level, BlockPos pos, BlockState blockState, Function<BlockState, Direction> getFacing, BiFunction<BlockState, Direction, BlockState> setFacing) {
        var connection = determine(level, pos, blockState, getFacing);

        if(connection == CORNER_OUTER) {
            var facing = getFacing.apply(blockState);
            blockState = setFacing.apply(blockState, facing.getClockWise());
        }

        return blockState.setValue(PROPERTY, connection);
    }

    private static SofaConnection determine(BlockGetter level, BlockPos pos, BlockState blockState, Function<BlockState, Direction> getFacing) {
        var hasLeft = canConnect(level, pos, blockState, getFacing, true);
        var hasRight = canConnect(level, pos, blockState, getFacing, false);

        if(hasLeft && hasRight)
            return BOTH;
        if(hasLeft)
            return asCorner(level, pos, blockState, getFacing, LEFT);
        if(hasRight)
            return asCorner(level, pos, blockState, getFacing, RIGHT);

        return NONE;
    }

    private static boolean canConnect(BlockGetter level, BlockPos pos, BlockState blockState, Function<BlockState, Direction> getFacing, boolean left) {
        var facing = getFacing.apply(blockState);
        var offset = left ? facing.getCounterClockWise() : facing.getClockWise();
        var otherPos = pos.relative(offset);
        var otherBlockState = level.getBlockState(otherPos);

        if(!otherBlockState.is(blockState.getBlock()))
            return false;
        if(getFacing.apply(otherBlockState) == facing)
            return true;
        return otherBlockState.getValue(PROPERTY).isCorner();
    }

    private static SofaConnection asCorner(BlockGetter level, BlockPos pos, BlockState blockState, Function<BlockState, Direction> getFacing, SofaConnection connection) {
        var facing = getFacing.apply(blockState);
        var frontPos = pos.relative(facing);
        var frontBlockState = level.getBlockState(frontPos);

        if(!frontBlockState.is(blockState.getBlock()))
            return connection;

        var frontFacing = getFacing.apply(frontBlockState);

        if(frontFacing == facing.getCounterClockWise())
            return CORNER_INNER;
        if(frontFacing.getCounterClockWise() == facing)
            return CORNER_OUTER;

        return connection;
    }
}
