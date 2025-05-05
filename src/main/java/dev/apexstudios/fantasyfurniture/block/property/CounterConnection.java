package dev.apexstudios.fantasyfurniture.block.property;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;

public enum CounterConnection implements StringRepresentable {
    NONE("single"),
    CORNER_INNER("corner_inner"),
    CORNER_OUTER("corner_outer");

    public static final EnumProperty<CounterConnection> PROPERTY = EnumProperty.create("connection", CounterConnection.class);

    private final String serializedName;

    CounterConnection(String serializedName) {
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

    public static BlockState setConnection(BlockGetter level, BlockPos pos, BlockState blockState, Property<Direction> facingProperty) {
        var connection = determine(level, pos, blockState, facingProperty);

        if(connection == CORNER_INNER) {
            var facing = blockState.getValue(facingProperty);
            blockState = blockState.setValue(facingProperty, facing.getCounterClockWise());
        }

        return blockState.setValue(PROPERTY, connection);
    }

    private static CounterConnection determine(BlockGetter level, BlockPos pos, BlockState blockState, Property<Direction> facingProperty) {
        var hasLeft = canConnect(level, pos, blockState, facingProperty, true);
        var hasRight = canConnect(level, pos, blockState, facingProperty, false);

        if(hasLeft || hasRight)
            return asCorner(level, pos, blockState, facingProperty);

        return NONE;
    }

    private static boolean canConnect(BlockGetter level, BlockPos pos, BlockState blockState, Property<Direction> facingProperty, boolean left) {
        var facing = blockState.getValue(facingProperty);
        var offset = left ? facing.getCounterClockWise() : facing.getClockWise();
        var otherPos = pos.relative(offset);
        var otherBlockState = level.getBlockState(otherPos);

        if(!otherBlockState.is(blockState.getBlock()))
            return false;
        if(otherBlockState.getValue(facingProperty) == facing)
            return true;
        return otherBlockState.getValue(PROPERTY).isCorner();
    }

    private static CounterConnection asCorner(BlockGetter level, BlockPos pos, BlockState blockState, Property<Direction> facingProperty) {
        var facing = blockState.getValue(facingProperty);
        var frontPos = pos.relative(facing);
        var frontBlockState = level.getBlockState(frontPos);

        if(!frontBlockState.is(blockState.getBlock()))
            return NONE;

        var frontFacing = frontBlockState.getValue(facingProperty);

        if(frontFacing == facing.getCounterClockWise())
            return CORNER_INNER;
        if(frontFacing.getCounterClockWise() == facing)
            return CORNER_OUTER;

        return NONE;
    }
}
