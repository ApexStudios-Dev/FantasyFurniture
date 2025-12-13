package dev.apexstudios.fantasyfurniture.decorations.block;

import dev.apexstudios.apexcore.lib.block.SimpleHorizontalDirectionalBlock;
import dev.apexstudios.apexcore.lib.util.ApexShapes;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public sealed class ChalicesBlock extends SimpleHorizontalDirectionalBlock implements Stackable {
    public static final IntegerProperty COUNT = IntegerProperty.create("count", 0, 2);

    public static final VoxelShape SHAPE_0 = box(6.5D, 0D, 6.5D, 9.5D, 8D, 9.5D);

    public static final VoxelShape SHAPE_1 = ApexShapes.join(
            box(9.5D, 0D, 5.5D, 12.5D, 8D, 8.5D),
            box(2D, 0D, 8D, 6D, 8D, 12D)
    );

    public static final VoxelShape SHAPE_2 = ApexShapes.join(
            box(10.5D, 0D, 6.5D, 13.5D, 8D, 9.5D),
            box(2D, 0D, 9D, 6D, 8D, 13D),
            box(5D, 0D, 2D, 9D, 8D, 6D)
    );

    public static final Map<Direction, VoxelShape> SHAPES_0 = Shapes.rotateHorizontal(SHAPE_0);
    public static final Map<Direction, VoxelShape> SHAPES_1 = Shapes.rotateHorizontal(SHAPE_1);
    public static final Map<Direction, VoxelShape> SHAPES_2 = Shapes.rotateHorizontal(SHAPE_2);

    public ChalicesBlock(Properties properties) {
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

    public static final class Dyeable extends ChalicesBlock implements dev.apexstudios.apexcore.lib.block.Dyeable.Colored {
        public Dyeable(Properties properties) {
            super(properties);

            registerDefaultState(setDyedColor(defaultBlockState(), DyedColor.WHITE));
        }

        @Override
        protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
            super.createBlockStateDefinition(builder.add(DYED_COLOR));
        }

        @Override
        public BlockState getStateForPlacement(BlockPlaceContext context) {
            var placementBlockState = super.getStateForPlacement(context);

            if(placementBlockState == null) {
                return null;
            }

            var color = getDyedColorForPlacement(context);

            if(color == DyedColor.NONE || color == DyedColor.WHITE) {
                var existing = context.getLevel().getBlockState(context.getClickedPos());

                if(existing.is(this)) {
                    color = getDyedColor(existing);
                }
            }

            return setDyedColor(placementBlockState, color);
        }

        @Override
        protected InteractionResult useItemOn(ItemStack stack, BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
            var result = tryDyeBlock(stack, blockState, level, pos, player);

            if(!result.consumesAction()) {
                result = super.useItemOn(stack, blockState, level, pos, player, hand, hitResult);
            }

            return result;
        }

        @Override
        public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState blockState, boolean includeData, Player player) {
            var stack = new ItemStack(this);
            appendDyedColor(stack, blockState, player, includeData);
            return stack;
        }

        @Override
        protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState blockState, boolean includeData) {
            var stack = new ItemStack(this);
            appendDyedColor(stack, blockState, null, includeData);
            return stack;
        }
    }

    public static final class AltModel extends ChalicesBlock {
        public static final VoxelShape SHAPE_0 = ApexShapes.join(
                box(6.5D, 0D, 6.5D, 9.5D, 1D, 9.5D),
                box(7.25D, 1D, 7.25D, 8.75D, 4D, 8.75D),
                box(6D, 4D, 6D, 10D, 8D, 10D)
        );

        public static final VoxelShape SHAPE_1 = ApexShapes.join(
                box(3.5D, 0D, 8.5D, 6.5D, 1D, 11.5D),
                box(4.25D, 1D, 9.25D, 5.75D, 4D, 10.75D),
                box(3D, 4D, 8D, 7D, 8D, 12D),
                box(8.25D, 4D, 4.25D, 13.75D, 8D, 9.75D),
                box(9D, 0D, 5D, 13D, 1D, 9D),
                box(10D, 1D, 6D, 12D, 4D, 8D)
        );

        public static final VoxelShape SHAPE_2 = ApexShapes.join(
                box(4.5D, 0D, 10.5D, 7.5D, 1D, 13.5D),
                box(5.25D, 1D, 11.25D, 6.75D, 4D, 12.75D),
                box(4D, 4D, 10D, 8D, 8D, 14D),
                box(9.25D, 4D, 5.25D, 14.75D, 8D, 10.75D),
                box(10D, 0D, 6D, 14D, 1D, 10D),
                box(11D, 1D, 7D, 13D, 4D, 9D),
                box(1.25D, 4D, 2.25D, 6.75D, 8D, 7.75D),
                box(2D, 0D, 3D, 6D, 1D, 7D),
                box(3D, 1D, 4D, 5D, 4D, 6D)
        );

        public static final Map<Direction, VoxelShape> SHAPES_0 = Shapes.rotateHorizontal(SHAPE_0);
        public static final Map<Direction, VoxelShape> SHAPES_1 = Shapes.rotateHorizontal(SHAPE_1);
        public static final Map<Direction, VoxelShape> SHAPES_2 = Shapes.rotateHorizontal(SHAPE_2);

        public AltModel(Properties properties) {
            super(properties);
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
    }
}
