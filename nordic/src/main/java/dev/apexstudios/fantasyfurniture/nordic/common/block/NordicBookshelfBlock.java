package dev.apexstudios.fantasyfurniture.nordic.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.BookshelfBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NordicBookshelfBlock extends BookshelfBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(-15D, 0D, 1D, 15D, 30D, 15D),
            box(-16D, 30D, 0D, 16D, 32D, 16D)
    );

    public NordicBookshelfBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
