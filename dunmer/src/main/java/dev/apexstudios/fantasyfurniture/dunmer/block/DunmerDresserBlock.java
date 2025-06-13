package dev.apexstudios.fantasyfurniture.dunmer.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.DresserBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class DunmerDresserBlock extends DresserBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(-15D, 0D, 1D, -13D, 14D, 3D),
            box(-15D, 0D, 13D, -13D, 14D, 15D),
            box(13D, 0D, 13D, 15D, 14D, 15D),
            box(13D, 0D, 1D, 15D, 14D, 3D),
            box(-16D, 14D, 0D, 16D, 16D, 16D),
            box(-15D, 6D, 1D, 15D, 8D, 15D),
            box(-14D, 8D, 2D, 14D, 14D, 14D)
    );

    public DunmerDresserBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
