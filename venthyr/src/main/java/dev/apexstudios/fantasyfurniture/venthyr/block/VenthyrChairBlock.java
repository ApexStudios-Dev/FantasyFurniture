package dev.apexstudios.fantasyfurniture.venthyr.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.ChairBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrChairBlock extends ChairBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(1D, 0D, 1D, 4D, 5D, 4D),
            box(12D, 0D, 1D, 15D, 5D, 4D),
            box(12D, 0D, 12D, 15D, 5D, 15D),
            box(1D, 0D, 12D, 4D, 5D, 15D),
            box(.5D, 5D, .5D, 15.5D, 9D, 15.5D),
            box(1D, 9D, 12D, 15D, 31D, 15D)
    );

    public VenthyrChairBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
