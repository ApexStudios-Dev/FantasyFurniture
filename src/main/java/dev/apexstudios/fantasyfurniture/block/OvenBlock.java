package dev.apexstudios.fantasyfurniture.block;

import net.minecraft.world.level.block.SmokerBlock;

public class OvenBlock extends SmokerBlock {
    public OvenBlock(Properties properties) {
        super(properties);

        registerDefaultState(defaultBlockState().setValue(LIT, false));
    }
}
