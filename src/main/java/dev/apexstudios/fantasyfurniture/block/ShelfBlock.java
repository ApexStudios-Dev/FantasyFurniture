package dev.apexstudios.fantasyfurniture.block;

import dev.apexstudios.apexcore.lib.component.ComponentRegistrar;
import dev.apexstudios.apexcore.lib.component.block.BlockComponent;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.component.block.types.FacingBlockComponent;
import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureBlockComponentHolder;
import dev.apexstudios.fantasyfurniture.block.property.ShelfConnection;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ShelfBlock extends FurnitureBlockComponentHolder {
    public static final VoxelShape LEFT_SHAPE = ApexShapes.join(
            box(13.5D, 9D, 2D, 15.5D, 14D, 13D),
            box(0D, 14D, 0D, 16D, 16D, 16D),
            box(13D, 6D, 13D, 16D, 14D, 16D)
    );

    public static final VoxelShape RIGHT_SHAPE = ApexShapes.join(
            box(.5D, 9D, 2D, 2.5D, 14D, 13D),
            box(0D, 14D, 0D, 16D, 16D, 16D),
            box(0D, 6D, 13D, 3D, 14D, 16D)
    );

    public static final VoxelShape BOTH_SHAPE = box(0D, 14D, 0D, 16D, 16D, 16D);

    public static final VoxelShape SHAPE = ApexShapes.join(
            box(.5D, 9D, 2D, 2.5D, 14D, 13D),
            box(13.5D, 9D, 2D, 15.5D, 14D, 13D),
            box(0D, 14D, 0D, 16D, 16D, 16D),
            box(13D, 6D, 13D, 16D, 14D, 16D),
            box(0D, 6D, 13D, 3D, 14D, 16D)
    );

    public static final Map<Direction, VoxelShape> LEFT_FACING_SHAPES = ApexShapes.rotateHorizontal(LEFT_SHAPE);
    public static final Map<Direction, VoxelShape> RIGHT_FACING_SHAPES = ApexShapes.rotateHorizontal(RIGHT_SHAPE);
    public static final Map<Direction, VoxelShape> BOTH_FACING_SHAPES = ApexShapes.rotateHorizontal(BOTH_SHAPE);
    public static final Map<Direction, VoxelShape> FACING_SHAPES = ApexShapes.rotateHorizontal(SHAPE);

    public ShelfBlock(Properties properties) {
        super(properties);

        registerDefaultState(defaultBlockState().setValue(ShelfConnection.PROPERTY, ShelfConnection.BOTH));
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(switch (blockState.getValue(ShelfConnection.PROPERTY)) {
            case LEFT -> LEFT_FACING_SHAPES;
            case RIGHT -> RIGHT_FACING_SHAPES;
            case BOTH -> BOTH_FACING_SHAPES;
            case NONE -> FACING_SHAPES;
        }, blockState, pos);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ShelfConnection.PROPERTY);
    }

    @Override
    protected void registerComponents(ComponentRegistrar<BlockComponent> registrar) {
        super.registerComponents(registrar);

        FacingBlockComponent.registerHorizontal(registrar);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        var blockState = super.getStateForPlacement(context);

        if(blockState == null)
            return null;

        var level = context.getLevel();
        var pos = context.getClickedPos();
        var facingComponent = getComponentOrThrow(BlockComponentTypes.FACING);
        return ShelfConnection.setConnection(level, pos, blockState, facingComponent::get);
    }

    @Override
    public BlockState updateShape(BlockState blockState, LevelReader level, ScheduledTickAccess tickAccess, BlockPos pos, Direction facing, BlockPos neighborPos, BlockState neighborBlockState, RandomSource random) {
        var result = blockState;

        if(facing.getAxis().isHorizontal()) {
            var facingComponent = getComponentOrThrow(BlockComponentTypes.FACING);
            result = ShelfConnection.setConnection(level, pos, result, facingComponent::get);
        }

        return super.updateShape(result, level, tickAccess, pos, facing, neighborPos, neighborBlockState, random);
    }
}
