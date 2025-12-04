package dev.apexstudios.fantasyfurniture.decorations.ber;

import dev.apexstudios.fantasyfurniture.decorations.DecorationsFurnitureModule;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public sealed class SimpleBlockEntity extends BlockEntity {
    protected SimpleBlockEntity(BlockEntityType<? extends SimpleBlockEntity> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public static final class WidowBloom extends SimpleBlockEntity {
        public WidowBloom(BlockPos pos, BlockState blockState) {
            super(DecorationsFurnitureModule.WIDOW_BLOOM_BLOCK_ENTITY.value(), pos, blockState);
        }
    }

    public static final class SkullBlossom extends SimpleBlockEntity {
        public SkullBlossom(BlockPos pos, BlockState blockState) {
            super(DecorationsFurnitureModule.SKULL_BLOSSOM_BLOCK_ENTITY.value(), pos, blockState);
        }
    }
}
