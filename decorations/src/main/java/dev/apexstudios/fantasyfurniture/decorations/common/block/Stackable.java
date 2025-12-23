package dev.apexstudios.fantasyfurniture.decorations.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;

public interface Stackable {
    IntegerProperty getStackableProperty();

    default void onStackChanged(Level level, BlockPos pos, BlockState blockState, int oldCount, int newCount) {

    }

    static void playerDestroy(Stackable stackable, Level level, BlockPos pos, BlockState blockState) {
        var property = stackable.getStackableProperty();

        if(!blockState.hasProperty(property)) {
            return;
        }

        var count = blockState.getValue(property);
        var newCount = Math.max(property.min, count - 1);

        if(count <= property.min) {
            level.destroyBlock(pos, false);
        } else {
            var newBlockState = blockState.setValue(property, newCount);
            level.setBlock(pos, newBlockState, Block.UPDATE_CLIENTS);
            level.gameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Context.of(newBlockState));
            stackable.onStackChanged(level, pos, newBlockState, count, newCount);
        }
    }

    static boolean canBeReplaced(Stackable stackable, BlockState blockState, BlockPlaceContext context) {
        if(context.isSecondaryUseActive()) {
            return false;
        }

        var property = stackable.getStackableProperty();

        if(!blockState.hasProperty(property)) {
            return false;
        }

        if(!context.getItemInHand().is(blockState.getBlock().asItem())) {
            return false;
        }

        return blockState.getValue(property) < property.max;
    }

    static int getCountForPlacement(Stackable stackable, BlockPlaceContext context) {
        var blockState = context.getLevel().getBlockState(context.getClickedPos());
        var property = stackable.getStackableProperty();

        if(blockState.hasProperty(property)) {
            var count = blockState.getValue(property);
            return Math.min(count + 1, property.max);
        }

        return 0;
    }
}
