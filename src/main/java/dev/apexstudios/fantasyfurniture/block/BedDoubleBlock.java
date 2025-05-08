package dev.apexstudios.fantasyfurniture.block;

import dev.apexstudios.apexcore.lib.multiblock.BedMultiBlock;
import dev.apexstudios.apexcore.lib.multiblock.MultiBlockProperty;

public class BedDoubleBlock extends BedMultiBlock {
    public static final MultiBlockProperty MULTI_BLOCK = MultiBlockProperty.create("multi_block_index", builder -> builder
            .with(-1, 0, 0)
            .with(-1, 0, -1)
            .with(0, 0, -1)
    );

    public BedDoubleBlock(Properties properties) {
        super(properties);
    }

    @Override
    public MultiBlockProperty getMultiBlockProperty() {
        return MULTI_BLOCK;
    }
}
