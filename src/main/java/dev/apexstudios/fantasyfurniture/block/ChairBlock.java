package dev.apexstudios.fantasyfurniture.block;

import dev.apexstudios.apexcore.lib.multiblock.MultiBlockProperties;
import dev.apexstudios.apexcore.lib.multiblock.MultiBlockProperty;

public class ChairBlock extends SeatMultiBlock {
    public ChairBlock(Properties properties) {
        super(properties);
    }

    @Override
    public MultiBlockProperty getMultiBlockProperty() {
        return MultiBlockProperties.MB_1x2x1;
    }
}
