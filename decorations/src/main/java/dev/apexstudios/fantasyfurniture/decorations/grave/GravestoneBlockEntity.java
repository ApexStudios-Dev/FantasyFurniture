package dev.apexstudios.fantasyfurniture.decorations.grave;

import dev.apexstudios.fantasyfurniture.decorations.DecorationsFurnitureModule;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public final class GravestoneBlockEntity extends SignBlockEntity {
    public GravestoneBlockEntity(BlockPos pos, BlockState blockState) {
        super(DecorationsFurnitureModule.GRAVESTONE_BLOCK_ENTITY.value(), pos, blockState);
    }

    @Override
    public int getMaxTextLineWidth() {
        return 50;
    }
}
