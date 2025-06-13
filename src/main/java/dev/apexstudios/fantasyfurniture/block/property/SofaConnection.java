package dev.apexstudios.fantasyfurniture.block.property;

import dev.apexstudios.apexcore.lib.block.SimpleHorizontalDirectionalBlock;
import net.minecraft.core.BlockPos;
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

    public static BlockState setConnection(BlockGetter level, BlockPos pos, BlockState blockState) {
        var connection = determine(level, pos, blockState);

        if(connection == CORNER_OUTER) {
            var facing = blockState.getValue(SimpleHorizontalDirectionalBlock.FACING);
            blockState = blockState.setValue(SimpleHorizontalDirectionalBlock.FACING, facing.getClockWise());
        }

        return blockState.setValue(PROPERTY, connection);
    }

    private static SofaConnection determine(BlockGetter level, BlockPos pos, BlockState blockState) {
        var hasLeft = canConnect(level, pos, blockState, true);
        var hasRight = canConnect(level, pos, blockState, false);

        if(hasLeft && hasRight)
            return BOTH;
        if(hasLeft)
            return asCorner(level, pos, blockState, LEFT);
        if(hasRight)
            return asCorner(level, pos, blockState, RIGHT);

        return NONE;
    }

    private static boolean canConnect(BlockGetter level, BlockPos pos, BlockState blockState, boolean left) {
        var facing = blockState.getValue(SimpleHorizontalDirectionalBlock.FACING);
        var offset = left ? facing.getCounterClockWise() : facing.getClockWise();
        var otherPos = pos.relative(offset);
        var otherBlockState = level.getBlockState(otherPos);

        if(!otherBlockState.is(blockState.getBlock()))
            return false;
        if(otherBlockState.getValue(SimpleHorizontalDirectionalBlock.FACING) == facing)
            return true;
        return otherBlockState.getValue(PROPERTY).isCorner();
    }

    private static SofaConnection asCorner(BlockGetter level, BlockPos pos, BlockState blockState, SofaConnection connection) {
        var facing = blockState.getValue(SimpleHorizontalDirectionalBlock.FACING);
        var frontPos = pos.relative(facing);
        var frontBlockState = level.getBlockState(frontPos);

        if(!frontBlockState.is(blockState.getBlock()))
            return connection;

        var frontFacing = frontBlockState.getValue(SimpleHorizontalDirectionalBlock.FACING);

        if(frontFacing == facing.getCounterClockWise())
            return CORNER_INNER;
        if(frontFacing.getCounterClockWise() == facing)
            return CORNER_OUTER;

        return connection;
    }
}
