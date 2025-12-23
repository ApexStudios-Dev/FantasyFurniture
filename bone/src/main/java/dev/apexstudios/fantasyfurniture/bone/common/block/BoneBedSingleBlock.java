package dev.apexstudios.fantasyfurniture.bone.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.BedSingleBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class BoneBedSingleBlock extends BedSingleBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(0D, 0D, 0D, 4D, 2D, 4D),
            box(0D, 0D, 28D, 4D, 2D, 32D),
            box(12D, 0D, 28D, 16D, 2D, 32D),
            box(12D, 0D, 0D, 16D, 2D, 4D),
            box(12D, 12D, 28D, 16D, 14D, 32D),
            box(0D, 12D, 28D, 4D, 14D, 32D),
            box(0D, 12D, 0D, 4D, 14D, 4D),
            box(12D, 12D, 0D, 16D, 14D, 4D),
            box(13D, 2D, 29D, 15D, 12D, 31D),
            box(1D, 2D, 29D, 3D, 12D, 31D),
            box(1D, 2D, 1D, 3D, 12D, 3D),
            box(13D, 2D, 1D, 15D, 12D, 3D),
            box(4D, 4D, 1D, 12D, 13D, 3D),
            box(4D, 4D, 29D, 12D, 13D, 31D),
            box(1D, 4D, 3D, 15D, 8D, 29D)
    );

    public BoneBedSingleBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
