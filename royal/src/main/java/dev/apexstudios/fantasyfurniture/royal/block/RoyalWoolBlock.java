package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.apexcore.lib.component.block.BlockComponentRegistrar;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureBlockComponentHolder;

public final class RoyalWoolBlock extends FurnitureBlockComponentHolder {
    public RoyalWoolBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void registerComponents(BlockComponentRegistrar registrar) {
        registrar.register(BlockComponentTypes.DYEABLE);
    }
}
