package dev.apexstudios.fantasyfurniture.block;

import dev.apexstudios.apexcore.lib.component.ComponentRegistrar;
import dev.apexstudios.apexcore.lib.component.block.BlockComponent;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.fantasyfurniture.block.entity.WardrobeBlockEntity;
import dev.apexstudios.fantasyfurniture.block.base.InventoryBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class WardrobeBlock extends InventoryBlock {
    public WardrobeBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void registerComponents(ComponentRegistrar<BlockComponent> registrar) {
        super.registerComponents(registrar);

        registrar.register(BlockComponentTypes.MULTI_BLOCK, builder -> builder
                .with(0, 0, 1)
                .with(0, 1, 1)
                .with(0, 1, 0)
                .with(0, 2, 1)
                .with(0, 2, 0)
                .rotatingFromComponent()
        );
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState blockState) {
        return new WardrobeBlockEntity(pos, blockState);
    }
}
