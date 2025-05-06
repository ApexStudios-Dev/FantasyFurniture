package dev.apexstudios.fantasyfurniture.block.property;

import net.minecraft.core.BlockPos;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
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

    public static BlockState setConnection(BlockGetter level, BlockPos pos, BlockState blockState) {
        return blockState.setValue(PROPERTY, determine(level, pos, blockState));
    }

    private static ShelfConnection determine(BlockGetter level, BlockPos pos, BlockState blockState) {
        var hasLeft = canConnect(level, pos, blockState, true);
        var hasRight = canConnect(level, pos, blockState, false);

        if(hasLeft && hasRight)
            return BOTH;
        if(hasLeft)
            return LEFT;
        if(hasRight)
            return RIGHT;

        return NONE;
    }

    private static boolean canConnect(BlockGetter level, BlockPos pos, BlockState blockState, boolean left) {
        var facing = blockState.getValue(HorizontalDirectionalBlock.FACING);
        var offset = left ? facing.getCounterClockWise() : facing.getClockWise();
        var otherPos = pos.relative(offset);
        var otherBlockState = level.getBlockState(otherPos);

        if(!otherBlockState.is(blockState.getBlock()))
            return false;

        // TODO: should this be otherBlockState
        return blockState.getValue(HorizontalDirectionalBlock.FACING) == facing;
    }
}
