package dev.apexstudios.fantasyfurniture.decorations.common.plushie;

import com.mojang.serialization.MapCodec;
import dev.apexstudios.fantasyfurniture.decorations.common.DecorationsFurnitureModule;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ResolvableProfile;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
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
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.Tags;
import org.jspecify.annotations.Nullable;

public final class PlushieBlock extends BaseEntityBlock {
    public static final int MAX_INDEX = RotationSegment.getMaxSegmentIndex();
    public static final int ROTATIONS = MAX_INDEX + 1;
    public static final IntegerProperty ROTATION = SkullBlock.ROTATION;
    public static final VoxelShape SHAPE = box(4D, 0D, 4D, 14D, 12D, 14D);

    public PlushieBlock(Properties properties) {
        super(properties);

        registerDefaultState(defaultBlockState().setValue(ROTATION, 0));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        var blockEntity = DecorationsFurnitureModule.PLUSHIE_BLOCK_ENTITY.get(level, pos);

        if(blockEntity != null) {
            var changed = false;

            if(stack.is(Items.NAME_TAG)) {
                changed = blockEntity.setRenderName(true);
                var customName = stack.getCustomName();

                if(customName != null) {
                    var profile = PlushieBlockItem.profileFrom(customName.plainCopy().tryCollapseToString());

                    if(profile != null) {
                        changed = blockEntity.setProfile(profile) || changed;
                    }
                }
            } else if(stack.is(ItemTags.SKULLS)) {
                var profile = stack.get(DataComponents.PROFILE);

                if(profile != null) {
                    changed = blockEntity.setProfile(profile);
                }
            } else if(stack.is(Tags.Items.SLIME_BALLS)) {
                changed = blockEntity.setRenderName(false);
            }

            if(changed) {
                blockEntity.setChanged();
                return InteractionResult.SUCCESS;
            }
        }

        return super.useItemOn(stack, blockState, level, pos, player, hand, hitResult);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        var blockEntity = DecorationsFurnitureModule.PLUSHIE_BLOCK_ENTITY.get(level, pos);

        if(player.isSecondaryUseActive() && blockEntity != null && blockEntity.setProfile(ResolvableProfile.createResolved(player.getGameProfile()))) {
            blockEntity.setChanged();
            return InteractionResult.SUCCESS;
        }

        return super.useWithoutItem(blockState, level, pos, player, hitResult);
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
        var stack = new ItemStack(DecorationsFurnitureModule.PLUSHIE_BLOCK.value());
        appendItemData(level, pos, stack, includeData, player);
        return stack;
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
        var stack = new ItemStack(DecorationsFurnitureModule.PLUSHIE_BLOCK.value());
        appendItemData(level, pos, stack, includeData, null);
        return stack;
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    private static void appendItemData(LevelReader level, BlockPos pos, ItemStack stack, boolean includeData, @Nullable Player player) {
        if(!(level.getBlockEntity(pos) instanceof PlushieBlockEntity blockEntity)) {
            return;
        }

        if(includeData || (player != null && player.isCreative())) {
            stack.applyComponents(blockEntity.collectComponents());
        }
    }
}
