package dev.apexstudios.fantasyfurniture.decorations.common.block;

import dev.apexstudios.apexcore.api.block.Dyeable;
import dev.apexstudios.apexcore.api.multiblock.MultiBlock;
import dev.apexstudios.apexcore.api.multiblock.MultiBlockProperties;
import dev.apexstudios.apexcore.api.multiblock.MultiBlockProperty;
import dev.apexstudios.apexcore.api.multiblock.SimpleHorizontalDirectionalMultiBlock;
import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public final class WallMirrorLargeBlock extends SimpleHorizontalDirectionalMultiBlock implements Dyeable.Colored {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(1D, 2D, 15D, 4D, 5D, 16D),
            box(1D, 27D, 15D, 4D, 30D, 16D),
            box(12D, 27D, 15D, 15D, 30D, 16D),
            box(12D, 2D, 15D, 15D, 5D, 16D),
            box(2D, 3D, 15D, 14D, 29D, 16D)
    );

    public static final Map<Direction, VoxelShape> SHAPES = Shapes.rotateHorizontal(SHAPE);

    public WallMirrorLargeBlock(Properties properties) {
        super(properties);

        registerDefaultState(setDyedColor(defaultBlockState(), Dyeable.DyedColor.WHITE));
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        return FurnitureUtil.getShape(SHAPES, blockState, pos);
    }

    @Override
    public MultiBlockProperty getMultiBlockProperty() {
        return MultiBlockProperties.MB_1x2x1;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder.add(DYED_COLOR));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        var placementBlockState = super.getStateForPlacement(context);
        return placementBlockState == null ? null : setDyedColor(placementBlockState, getDyedColorForPlacement(context));
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

    @Override
    public void setDyedColor(Level level, BlockPos pos, BlockState blockState, DyedColor color) {
        MultiBlock.forEachPos(pos, blockState, (otherPos, otherBlockState) -> Colored.super.setDyedColor(level, otherPos, otherBlockState, color));
    }
}
