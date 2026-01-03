package dev.apexstudios.fantasyfurniture.necrolord.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.BookshelfBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NecrolordBookshelfBlock extends BookshelfBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(12D, 0D, 0D, 16D, 2D, 4D),
            box(-16D, 0D, 0D, -12D, 2D, 4D),
            box(-16D, 0D, 12D, -12D, 2D, 16D),
            box(12D, 0D, 12D, 16D, 2D, 16D),
            box(12D, 15D, 12D, 16D, 17D, 16D),
            box(12D, 15D, 0D, 16D, 17D, 4D),
            box(-16D, 15D, 0D, -12D, 17D, 4D),
            box(-16D, 15D, 12D, -12D, 17D, 16D),
            box(-16D, 30D, 12D, -12D, 32D, 16D),
            box(12D, 30D, 12D, 16D, 32D, 16D),
            box(12D, 30D, 0D, 16D, 32D, 4D),
            box(-15D, 0D, 1D, 15D, 32D, 15D)
    );

    public NecrolordBookshelfBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
