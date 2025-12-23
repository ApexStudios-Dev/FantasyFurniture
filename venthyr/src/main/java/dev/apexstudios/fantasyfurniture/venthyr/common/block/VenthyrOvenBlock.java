package dev.apexstudios.fantasyfurniture.venthyr.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.OvenBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrOvenBlock extends OvenBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(1D, 0D, 1D, 4D, 2D, 4D),
            box(1D, 0D, 12D, 4D, 2D, 15D),
            box(12D, 0D, 12D, 15D, 2D, 15D),
            box(12D, 0D, 1D, 15D, 2D, 4D),
            box(0D, 2D, 0D, 16D, 4D, 16D),
            box(0D, 14D, 0D, 16D, 16D, 16D),
            box(1D, 4D, 1D, 15D, 14D, 15D),
            box(3D, 5D, 0D, 13D, 13D, 1D)
    );

    public VenthyrOvenBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
