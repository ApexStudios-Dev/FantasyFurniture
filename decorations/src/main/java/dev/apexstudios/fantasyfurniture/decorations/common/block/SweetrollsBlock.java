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
import org.jetbrains.annotations.Nullable;

public final class SweetrollsBlock extends SimpleHorizontalDirectionalBlock implements Stackable {
    public static final IntegerProperty COUNT = IntegerProperty.create("count", 0, 2);

    public static final VoxelShape SHAPE_0 = box(6D, 0D, 6D, 10D, 4D, 10D);

    public static final VoxelShape SHAPE_1 = ApexShapes.join(
            box(9.75D, 0D, 7D, 14D, 4D, 11D),
            box(1.9071067811865463D, 1.0217939117040942e-7D, 4.492893218813454D, 7.507106781186546D, 4.000000102179391D, 10.092893218813455D)
    );

    public static final VoxelShape SHAPE_2 = ApexShapes.join(
            box(10D, 0D, 9D, 14D, 4D, 13D),
            box(1.8071067811865476D, 1.0217939117040942e-7D, 6.3928932188134535D, 7.607106781186548D, 4.000000102179391D, 12.192893218813454D),
            box(7.3D, 0D, 1.2999999999999998D, 12.7D, 4D, 6.699999999999999D)
    );

    public static final Map<Direction, VoxelShape> SHAPES_0 = Shapes.rotateHorizontal(SHAPE_0);
    public static final Map<Direction, VoxelShape> SHAPES_1 = Shapes.rotateHorizontal(SHAPE_1);
    public static final Map<Direction, VoxelShape> SHAPES_2 = Shapes.rotateHorizontal(SHAPE_2);

    public SweetrollsBlock(Properties properties) {
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
