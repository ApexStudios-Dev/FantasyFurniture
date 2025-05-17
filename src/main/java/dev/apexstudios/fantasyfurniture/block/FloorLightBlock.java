package dev.apexstudios.fantasyfurniture.block;

import dev.apexstudios.apexcore.lib.multiblock.MultiBlockProperties;
import dev.apexstudios.apexcore.lib.multiblock.MultiBlockProperty;
import dev.apexstudios.apexcore.lib.multiblock.SimpleHorizontalDirectionalMultiBlock;

public class FloorLightBlock extends SimpleHorizontalDirectionalMultiBlock {
    public FloorLightBlock(Properties properties) {
        super(properties);
    }

    @Override
    public MultiBlockProperty getMultiBlockProperty() {
        return MultiBlockProperties.MB_1x2x1;
    }
}
