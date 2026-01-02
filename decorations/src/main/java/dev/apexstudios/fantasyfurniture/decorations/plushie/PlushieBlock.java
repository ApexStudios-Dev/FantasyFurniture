package dev.apexstudios.fantasyfurniture.decorations.plushie;

import com.mojang.serialization.MapCodec;
import dev.apexstudios.fantasyfurniture.decorations.DecorationsFurnitureModule;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import org.jspecify.annotations.Nullable;

public final class PlushieBlock extends BaseEntityBlock {
    public static final int MAX_INDEX = RotationSegment.getMaxSegmentIndex();
    public static final int ROTATIONS = MAX_INDEX + 1;
    public static final IntegerProperty ROTATION = SkullBlock.ROTATION;

    public PlushieBlock(Properties properties) {
        super(properties);

        registerDefaultState(defaultBlockState().setValue(ROTATION, 0));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState blockState) {
        return new PlushieBlockEntity(pos, blockState);
    }

    @Override
    protected BlockState rotate(BlockState blockState, Rotation rotation) {
        return blockState.setValue(ROTATION, rotation.rotate(blockState.getValue(ROTATION), ROTATIONS));
    }

    @Override
    protected BlockState mirror(BlockState blockState, Mirror mirror) {
        return blockState.setValue(ROTATION, mirror.mirror(blockState.getValue(ROTATION), ROTATIONS));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder.add(ROTATION));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(ROTATION, RotationSegment.convertToSegment(context.getRotation()));
    }

    @Override
    protected RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.INVISIBLE;
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData, Player player) {
        var stack = DecorationsFurnitureModule.PLUSHIE_BLOCK.toStack();
        appendItemData(level, pos, stack, includeData, player);
        return stack;
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
        var stack = DecorationsFurnitureModule.PLUSHIE_BLOCK.toStack();
        appendItemData(level, pos, stack, includeData, null);
        return stack;
    }

    private static void appendItemData(LevelReader level, BlockPos pos, ItemStack stack, boolean includeData, @Nullable Player player) {
        if(!(level.getBlockEntity(pos) instanceof PlushieBlockEntity blockEntity)) {
            return;
        }

        if(includeData || (player != null && player.isCreative())) {
            PlushieBlockItem.setProfile(stack, blockEntity.getProfile());
        }
    }
}
