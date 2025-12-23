package dev.apexstudios.fantasyfurniture.decorations.common.block;

import dev.apexstudios.apexcore.api.block.SimpleHorizontalDirectionalBlock;
import dev.apexstudios.apexcore.api.util.ApexShapes;
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
import org.jspecify.annotations.Nullable;

public final class MushroomsBlock extends SimpleHorizontalDirectionalBlock implements Stackable {
    public static final IntegerProperty COUNT = IntegerProperty.create("count", 0, 2);

    public static final VoxelShape SHAPE_RED_0 = box(5D, 0D, 5D, 11D, 7D, 11D);

    public static final VoxelShape SHAPE_RED_1 = ApexShapes.join(
            box(9D, 0D, 7D, 15D, 7D, 13D),
            box(2D, 0D, 3D, 6D, 5D, 7D)
    );

    public static final VoxelShape SHAPE_RED_2 = ApexShapes.join(
            box(9D, 0D, 9D, 15D, 7D, 15D),
            box(2D, 0D, 5D, 6D, 5D, 9D),
            box(8D, 0D, 2D, 11D, 3D, 5D)
    );

    public static final VoxelShape SHAPE_BROWN_0 = ApexShapes.join(
            box(7D, 0D, 7D, 9D, 1D, 9D),
            box(6D, 1D, 6D, 10D, 3D, 10D)
    );

    public static final VoxelShape SHAPE_BROWN_1 = ApexShapes.join(
            box(10D, 0D, 5D, 12D, 1D, 7D),
            box(9D, 1D, 4D, 13D, 3D, 8D),
            box(3D, 2D, 9D, 7D, 4D, 13D),
            box(4D, 0D, 10D, 6D, 2D, 12D)
    );

    public static final VoxelShape SHAPE_BROWN_2 = ApexShapes.join(
            box(9D, 0D, 3D, 11D, 1D, 5D),
            box(8D, 1D, 2D, 12D, 3D, 6D),
            box(2D, 2D, 7D, 6D, 4D, 11D),
            box(3D, 0D, 8D, 5D, 2D, 10D),
            box(11D, 0D, 11D, 13D, 4D, 13D),
            box(9D, 4D, 9D, 15D, 6D, 15D)
    );

    public static final Map<Direction, VoxelShape> SHAPES_RED_0 = Shapes.rotateHorizontal(SHAPE_RED_0);
    public static final Map<Direction, VoxelShape> SHAPES_RED_1 = Shapes.rotateHorizontal(SHAPE_RED_1);
    public static final Map<Direction, VoxelShape> SHAPES_RED_2 = Shapes.rotateHorizontal(SHAPE_RED_2);

    public static final Map<Direction, VoxelShape> SHAPES_BROWN_0 = Shapes.rotateHorizontal(SHAPE_BROWN_0);
    public static final Map<Direction, VoxelShape> SHAPES_BROWN_1 = Shapes.rotateHorizontal(SHAPE_BROWN_1);
    public static final Map<Direction, VoxelShape> SHAPES_BROWN_2 = Shapes.rotateHorizontal(SHAPE_BROWN_2);

    private final boolean red;

    public MushroomsBlock(Properties properties, boolean red) {
        super(properties);

        this.red = red;

        registerDefaultState(defaultBlockState().setValue(COUNT, 0));
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        var facing = blockState.getValue(FACING);
        var count = blockState.getValue(COUNT);

        return (switch(count) {
            case 0 -> red ? SHAPES_RED_0 : SHAPES_BROWN_0;
            case 1 -> red ? SHAPES_RED_1 : SHAPES_BROWN_1;
            case 2 -> red ? SHAPES_RED_2 : SHAPES_BROWN_2;
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
