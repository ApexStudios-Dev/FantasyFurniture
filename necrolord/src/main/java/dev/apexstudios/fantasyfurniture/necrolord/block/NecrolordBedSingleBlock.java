package dev.apexstudios.fantasyfurniture.necrolord.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.BedSingleBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NecrolordBedSingleBlock extends BedSingleBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(0D, 0D, 0D, 4D, 2D, 4D),
            box(0D, 0D, 28D, 4D, 2D, 32D),
            box(12D, 0D, 28D, 16D, 2D, 32D),
            box(12D, 0D, 0D, 16D, 2D, 4D),
            box(12D, 13D, 0D, 16D, 15D, 4D),
            box(0D, 13D, 0D, 4D, 15D, 4D),
            box(0D, 12D, 28D, 4D, 14D, 32D),
            box(12D, 12D, 28D, 16D, 14D, 32D),
            box(13D, 2D, 29D, 15D, 12D, 31D),
            box(1D, 2D, 29D, 3D, 12D, 31D),
            box(1D, 2D, 1D, 3D, 13D, 3D),
            box(13D, 2D, 1D, 15D, 13D, 3D),
            box(3D, 3D, 1D, 13D, 12D, 3D),
            box(3D, 3D, 29D, 13D, 11D, 31D),
            box(1D, 3D, 3D, 15D, 8D, 29D)
    );

    public NecrolordBedSingleBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
