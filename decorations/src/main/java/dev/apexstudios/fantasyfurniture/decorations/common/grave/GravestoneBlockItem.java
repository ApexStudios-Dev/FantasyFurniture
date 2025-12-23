package dev.apexstudios.fantasyfurniture.decorations.common.grave;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

public final class GravestoneBlockItem extends BlockItem {
    public GravestoneBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    protected boolean updateCustomBlockEntityTag(BlockPos pos, Level level, @Nullable Player player, ItemStack stack, BlockState blockState) {
        var flag = super.updateCustomBlockEntityTag(pos, level, player, stack, blockState);

        if(!level.isClientSide() && !flag && player != null && level.getBlockEntity(pos) instanceof GravestoneBlockEntity blockEntity && level.getBlockState(pos).getBlock() instanceof GravestoneBlock block) {
            block.openTextEdit(player, blockEntity, true);
        }

        return flag;
    }
}
