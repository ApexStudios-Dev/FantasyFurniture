package dev.apexstudios.fantasyfurniture.dunmer.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.BedSingleBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class DunmerBedSingleBlock extends BedSingleBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(0D, 0D, 0D, 2D, 11D, 2D),
            box(2D, 3D, 0D, 14D, 11D, 2D),
            box(1D, 3D, 2D, 15D, 8D, 24D),
            box(2D, 3D, 24D, 14D, 7D, 30D),
            box(0D, 3D, 2D, 16D, 5D, 30D),
            box(14D, 0D, 0D, 16D, 11D, 2D),
            box(0D, 0D, 30D, 2D, 13D, 32D),
            box(2D, 3D, 30D, 14D, 13D, 32D),
            box(14D, 0D, 30D, 16D, 13D, 32D)
    );

    public DunmerBedSingleBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
