package dev.apexstudios.fantasyfurniture.dunmer.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.BookshelfBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class DunmerBookshelfBlock extends BookshelfBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(-14D, 0D, 2D, -12D, 30D, 4D),
            box(-14D, 0D, 12D, -12D, 30D, 14D),
            box(12D, 0D, 12D, 14D, 30D, 14D),
            box(12D, 0D, 2D, 14D, 30D, 4D),
            box(-12D, 9D, 4D, 12D, 32D, 12D),
            box(-15D, 9D, 1D, 15D, 11D, 15D),
            box(-15D, 19D, 1D, 15D, 21D, 15D),
            box(-15D, 30D, 1D, 15D, 32D, 15D)
    );

    public DunmerBookshelfBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
