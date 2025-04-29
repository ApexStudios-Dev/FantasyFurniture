package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.apexcore.lib.component.block.BlockComponentRegistrar;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.fantasyfurniture.block.OvenBlock;

public class RoyalOvenBlock extends OvenBlock {
    public RoyalOvenBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void registerComponents(BlockComponentRegistrar registrar) {
        super.registerComponents(registrar);

        registrar.register(BlockComponentTypes.DYEABLE);
    }
}
