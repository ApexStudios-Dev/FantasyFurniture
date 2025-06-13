package dev.apexstudios.fantasyfurniture.venthyr.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.WardrobeBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrWardrobeBlock extends WardrobeBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(-15D, 32D, 1D, 15D, 41D, 15D),
            box(-16D, 41D, 0D, 16D, 43D, 16D),
            box(-16D, 0D, 0D, -12D, 3D, 4D),
            box(-16D, 0D, 12D, -12D, 3D, 16D),
            box(12D, 0D, 12D, 16D, 3D, 16D),
            box(12D, 0D, 0D, 16D, 3D, 4D),
            box(-15D, 1D, 1D, 15D, 29D, 15D),
            box(-16D, 29D, 0D, 16D, 32D, 16D)
    );

    public VenthyrWardrobeBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
