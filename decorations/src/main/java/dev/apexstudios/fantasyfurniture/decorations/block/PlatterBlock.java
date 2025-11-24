package dev.apexstudios.fantasyfurniture.decorations.block;

import dev.apexstudios.apexcore.lib.block.SimpleHorizontalDirectionalBlock;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public final class PlatterBlock extends SimpleHorizontalDirectionalBlock implements Stackable {
    public static final IntegerProperty COUNT = IntegerProperty.create("count", 0, 15);

    public static final VoxelShape SHAPE_0 = box(2D, 0D, 2D, 14D, 1D, 14D);
    public static final VoxelShape SHAPE_1 = box(2D, 0D, 2D, 14D, 2D, 14D);
    public static final VoxelShape SHAPE_2 = box(2D, 0D, 2D, 14D, 3D, 14D);
    public static final VoxelShape SHAPE_3 = box(2, 0, 2, 14, 4, 14);
    public static final VoxelShape SHAPE_4 = box(2, 0, 2, 14, 5, 14);
    public static final VoxelShape SHAPE_5 = box(2D, 0D, 2D, 14D, 6D, 14D);
    public static final VoxelShape SHAPE_6 = box(2D, 0D, 2D, 14D, 7D, 14D);
    public static final VoxelShape SHAPE_7 = box(2D, 0D, 2D, 14D, 8D, 14D);
    public static final VoxelShape SHAPE_8 = box(2D, 0D, 2D, 14D, 9D, 14D);
    public static final VoxelShape SHAPE_9 = box(2D, 0D, 2D, 14D, 10D, 14D);
    public static final VoxelShape SHAPE_10 = box(2D, 0D, 2D, 14D, 11D, 14D);
    public static final VoxelShape SHAPE_11 = box(2D, 0D, 2D, 14D, 12D, 14D);
    public static final VoxelShape SHAPE_12 = box(2D, 0D, 2D, 14D, 13D, 14D);
    public static final VoxelShape SHAPE_13 = box(2D, 0D, 2D, 14D, 14D, 14D);
    public static final VoxelShape SHAPE_14 = box(2D, 0D, 2D, 14D, 15D, 14D);
    public static final VoxelShape SHAPE_15 = box(2D, 0D, 2D, 14D, 16D, 14D);

    public static final Map<Direction, VoxelShape> SHAPES_0 = Shapes.rotateHorizontal(SHAPE_0);
    public static final Map<Direction, VoxelShape> SHAPES_1 = Shapes.rotateHorizontal(SHAPE_1);
    public static final Map<Direction, VoxelShape> SHAPES_2 = Shapes.rotateHorizontal(SHAPE_2);
    public static final Map<Direction, VoxelShape> SHAPES_3 = Shapes.rotateHorizontal(SHAPE_3);
    public static final Map<Direction, VoxelShape> SHAPES_4 = Shapes.rotateHorizontal(SHAPE_4);
    public static final Map<Direction, VoxelShape> SHAPES_5 = Shapes.rotateHorizontal(SHAPE_5);
    public static final Map<Direction, VoxelShape> SHAPES_6 = Shapes.rotateHorizontal(SHAPE_6);
    public static final Map<Direction, VoxelShape> SHAPES_7 = Shapes.rotateHorizontal(SHAPE_7);
    public static final Map<Direction, VoxelShape> SHAPES_8 = Shapes.rotateHorizontal(SHAPE_8);
    public static final Map<Direction, VoxelShape> SHAPES_9 = Shapes.rotateHorizontal(SHAPE_9);
    public static final Map<Direction, VoxelShape> SHAPES_10 = Shapes.rotateHorizontal(SHAPE_10);
    public static final Map<Direction, VoxelShape> SHAPES_11 = Shapes.rotateHorizontal(SHAPE_11);
    public static final Map<Direction, VoxelShape> SHAPES_12 = Shapes.rotateHorizontal(SHAPE_12);
    public static final Map<Direction, VoxelShape> SHAPES_13 = Shapes.rotateHorizontal(SHAPE_13);
    public static final Map<Direction, VoxelShape> SHAPES_14 = Shapes.rotateHorizontal(SHAPE_14);
    public static final Map<Direction, VoxelShape> SHAPES_15 = Shapes.rotateHorizontal(SHAPE_15);

    public PlatterBlock(Properties properties) {
        super(properties);

        registerDefaultState(defaultBlockState().setValue(COUNT, 0));
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        var facing = blockState.getValue(FACING);
        var count = blockState.getValue(COUNT);

        return (switch(count) {
            case 0 -> SHAPES_0;
            case 1 -> SHAPES_1;
            case 2 -> SHAPES_2;
            case 3 -> SHAPES_3;
            case 4 -> SHAPES_4;
            case 5 -> SHAPES_5;
            case 6 -> SHAPES_6;
            case 7 -> SHAPES_7;
            case 8 -> SHAPES_8;
            case 9 -> SHAPES_9;
            case 10 -> SHAPES_10;
            case 11 -> SHAPES_11;
            case 12 -> SHAPES_12;
            case 13 -> SHAPES_13;
            case 14 -> SHAPES_14;
            case 15 -> SHAPES_15;
            default -> throw new IllegalStateException("Unexpected value: " + count);
        }).get(facing);
    }

    @Override
    public IntegerProperty getStackableProperty() {
        return COUNT;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder.add(COUNT));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        var blockState = super.getStateForPlacement(context);

        if(blockState == null) {
            return null;
        }

        var count = Stackable.getCountForPlacement(this, context);
        return blockState.setValue(COUNT, count);
    }

    @Override
    protected boolean canBeReplaced(BlockState blockState, BlockPlaceContext context) {
        if(Stackable.canBeReplaced(this, blockState, context)) {
            return true;
        }

        return super.canBeReplaced(blockState, context);
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState blockState, @Nullable BlockEntity blockEntity, ItemStack tool) {
        super.playerDestroy(level, player, pos, blockState, blockEntity, tool);
        Stackable.playerDestroy(this, level, pos, blockState);
    }
}
