package dev.apexstudios.fantasyfurniture.block;

import dev.apexstudios.apexcore.lib.component.ComponentRegistrar;
import dev.apexstudios.apexcore.lib.component.block.BlockComponent;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.base.SeatBlock;
import dev.apexstudios.fantasyfurniture.block.property.SofaConnection;
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
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SofaBlock extends SeatBlock {
    public static final VoxelShape LEFT_SHAPE = ApexShapes.join(
            box(0D, 3D, 0D, 16D, 6D, 16D),
            box(0D, 6D, 13D, 16D, 16D, 16D),
            box(14D, 10D, 0D, 16D, 12D, 13D),
            box(14D, 6D, 0D, 16D, 10D, 2D),
            box(13D, 0D, 1D, 15D, 3D, 3D),
            box(13D, 0D, 13D, 15D, 3D, 15D)
    );

    public static final VoxelShape RIGHT_SHAPE = ApexShapes.join(
            box(0D, 3D, 0D, 16D, 6D, 16D),
            box(0D, 6D, 13D, 16D, 16D, 16D),
            box(0D, 10D, 0D, 2D, 12D, 13D),
            box(0D, 6D, 0D, 2D, 10D, 2D),
            box(1D, 0D, 1D, 3D, 3D, 3D),
            box(1D, 0D, 13D, 3D, 3D, 15D)
    );

    public static final VoxelShape BOTH_SHAPE = ApexShapes.join(
            box(0D, 3D, 0D, 16D, 6D, 16D),
            box(0D, 6D, 13D, 16D, 16D, 16D)
    );

    public static final VoxelShape CORNER_SHAPE = ApexShapes.join(
            box(1D, 0D, 1D, 3D, 3D, 3D),
            box(1D, 0D, 13D, 3D, 3D, 15D),
            box(13D, 0D, 13D, 15D, 3D, 15D),
            box(13D, 0D, 1D, 15D, 3D, 3D),
            box(0D, 3D, 0D, 16D, 6D, 16D),
            box(0D, 6D, 13D, 16D, 16D, 16D),
            box(13D, 6D, 0D, 16D, 16D, 13D)
    );

    public static final VoxelShape SHAPE = ApexShapes.join(
            box(1D, 0D, 1D, 3D, 3D, 3D),
            box(1D, 0D, 13D, 3D, 3D, 15D),
            box(13D, 0D, 13D, 15D, 3D, 15D),
            box(13D, 0D, 1D, 15D, 3D, 3D),
            box(0D, 3D, 0D, 16D, 6D, 16D),
            box(0D, 6D, 13D, 16D, 16D, 16D),
            box(14D, 10D, 0D, 16D, 12D, 14D),
            box(0D, 10D, 0D, 2D, 12D, 14D),
            box(0D, 6D, 0D, 2D, 10D, 2D),
            box(14D, 6D, 0D, 16D, 10D, 2D)
    );

    public static final Map<Direction, VoxelShape> LEFT_FACING_SHAPES = Shapes.rotateHorizontal(LEFT_SHAPE);
    public static final Map<Direction, VoxelShape> RIGHT_FACING_SHAPES = Shapes.rotateHorizontal(RIGHT_SHAPE);
    public static final Map<Direction, VoxelShape> BOTH_FACING_SHAPES = Shapes.rotateHorizontal(BOTH_SHAPE);
    public static final Map<Direction, VoxelShape> CORNER_FACING_SHAPES = Shapes.rotateHorizontal(CORNER_SHAPE);
    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public SofaBlock(Properties properties) {
        super(properties);

        registerDefaultState(defaultBlockState().setValue(SofaConnection.PROPERTY, SofaConnection.BOTH));
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(switch (blockState.getValue(SofaConnection.PROPERTY)) {
            case LEFT -> LEFT_FACING_SHAPES;
            case RIGHT -> RIGHT_FACING_SHAPES;
            case BOTH -> BOTH_FACING_SHAPES;
            case CORNER_INNER, CORNER_OUTER -> CORNER_FACING_SHAPES;
            case NONE -> FACING_SHAPES;
        }, blockState, pos);
    }

    @Override
    protected void registerComponents(ComponentRegistrar<BlockComponent, Block> registrar) {
        super.registerComponents(registrar);

        registrar.register(BlockComponentTypes.BOUNCE);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SofaConnection.PROPERTY);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        var blockState = super.getStateForPlacement(context);

        if(blockState == null)
            return null;

        var level = context.getLevel();
        var pos = context.getClickedPos();
        var facingComponent = getComponentOrThrow(BlockComponentTypes.FACING);

        return SofaConnection.setConnection(level, pos, blockState, facingComponent::get, facingComponent::set);
    }

    @Override
    public BlockState updateShape(BlockState blockState, LevelReader level, ScheduledTickAccess tickAccess, BlockPos pos, Direction facing, BlockPos neighborPos, BlockState neighborBlockState, RandomSource random) {
        var result = blockState;

        if(facing.getAxis().isHorizontal()) {
            var facingComponent = getComponentOrThrow(BlockComponentTypes.FACING);
            result = SofaConnection.setConnection(level, pos, blockState, facingComponent::get, facingComponent::set);
        }

        return super.updateShape(result, level, tickAccess, pos, facing, neighborPos, neighborBlockState, random);
    }
}
