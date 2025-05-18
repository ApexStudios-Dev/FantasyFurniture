package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.BookshelfBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class RoyalBookshelfBlock extends BookshelfBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(12D, 0D, 0D, 16D, 4D, 4D),
            box(-16D, 0D, 0D, -12D, 4D, 4D),
            box(-16D, 0D, 12D, -12D, 4D, 16D),
            box(12D, 0D, 12D, 16D, 4D, 16D),
            box(-15D, 4D, 1D, 15D, 6D, 15D),
            box(-15D, 30D, 1D, 15D, 32D, 15D),
            box(12D, 6D, 2D, 14D, 30D, 4D),
            box(12D, 6D, 12D, 14D, 30D, 14D),
            box(-14D, 6D, 12D, -12D, 30D, 14D),
            box(-14D, 6D, 2D, -12D, 30D, 4D),
            box(-13D, 6D, 4D, 13D, 30D, 13D),
            box(-13D, 17D, 2D, 13D, 19D, 4D)
    );

    public RoyalBookshelfBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
