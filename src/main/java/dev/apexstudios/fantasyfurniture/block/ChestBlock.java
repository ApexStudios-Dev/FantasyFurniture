package dev.apexstudios.fantasyfurniture.block;

import dev.apexstudios.apexcore.lib.multiblock.MultiBlockProperties;
import dev.apexstudios.apexcore.lib.multiblock.MultiBlockProperty;

public class ChestBlock extends InventoryMultiBlock {
    public ChestBlock(Properties properties) {
        super(properties);
    }

    @Override
    public MultiBlockProperty getMultiBlockProperty() {
        return MultiBlockProperties.MB_1x1x2;
    }
}
