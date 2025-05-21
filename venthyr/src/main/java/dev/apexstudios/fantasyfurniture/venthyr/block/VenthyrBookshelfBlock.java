package dev.apexstudios.fantasyfurniture.venthyr.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.BookshelfBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrBookshelfBlock extends BookshelfBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(-16D, 0D, 2D, 16D, 3D, 16D),
            box(-15D, 3D, 3D, 15D, 29D, 16D),
            box(-16D, 29D, 2D, 16D, 32D, 16D)
    );

    public VenthyrBookshelfBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
