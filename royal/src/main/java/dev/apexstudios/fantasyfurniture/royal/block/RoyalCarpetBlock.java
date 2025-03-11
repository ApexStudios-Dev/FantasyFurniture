package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.apexcore.lib.component.block.types.DyeableBlockComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;

public final class RoyalCarpetBlock extends CarpetBlock {
    public static final Property<DyeColor> COLOR = EnumProperty.create("color", DyeColor.class);

    public RoyalCarpetBlock(Properties properties) {
        super(properties);

        registerDefaultState(defaultBlockState().setValue(COLOR, DyeColor.WHITE));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(COLOR);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return DyeableBlockComponent.getStateForPlacement(COLOR, DyeColor.WHITE, context, defaultBlockState());
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        return DyeableBlockComponent.useItemOn(COLOR, stack, blockState, level, pos);
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState blockState, boolean includeData) {
        var stack = super.getCloneItemStack(level, pos, blockState, includeData);
        DyeableBlockComponent.modifyCloneItemStack(COLOR, stack, blockState, includeData, null);
        return stack;
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState blockState, boolean includeData, Player player) {
        var stack = super.getCloneItemStack(level, pos, blockState, includeData);
        DyeableBlockComponent.modifyCloneItemStack(COLOR, stack, blockState, includeData, player);
        return stack;
    }
}
