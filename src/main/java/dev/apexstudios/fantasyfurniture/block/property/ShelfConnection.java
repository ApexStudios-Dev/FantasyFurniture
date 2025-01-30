package dev.apexstudios.fantasyfurniture.block.property;

import java.util.function.Function;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public enum ShelfConnection implements StringRepresentable {
    LEFT("left"),
    RIGHT("right"),
    BOTH("center"),
    NONE("single");

    public static final EnumProperty<ShelfConnection> PROPERTY = EnumProperty.create("connection", ShelfConnection.class);

    private final String serializedName;

    ShelfConnection(String serializedName) {
        this.serializedName = serializedName;
    }

    @Override
    public String getSerializedName() {
        return serializedName;
    }

    public String getModelSuffix() {
        return '_' + serializedName;
    }

    public static BlockState setConnection(BlockGetter level, BlockPos pos, BlockState blockState, Function<BlockState, Direction> getFacing) {
        return blockState.setValue(PROPERTY, determine(level, pos, blockState, getFacing));
    }

    private static ShelfConnection determine(BlockGetter level, BlockPos pos, BlockState blockState, Function<BlockState, Direction> getFacing) {
        var hasLeft = canConnect(level, pos, blockState, getFacing, true);
        var hasRight = canConnect(level, pos, blockState, getFacing, false);

        if(hasLeft && hasRight)
            return BOTH;
        if(hasLeft)
            return LEFT;
        if(hasRight)
            return RIGHT;

        return NONE;
    }

    private static boolean canConnect(BlockGetter level, BlockPos pos, BlockState blockState, Function<BlockState, Direction> getFacing, boolean left) {
        var facing = getFacing.apply(blockState);
        var offset = left ? facing.getCounterClockWise() : facing.getClockWise();
        var otherPos = pos.relative(offset);
        var otherBlockState = level.getBlockState(otherPos);

        if(!otherBlockState.is(blockState.getBlock()))
            return false;

        return getFacing.apply(blockState) == facing;
    }
}
