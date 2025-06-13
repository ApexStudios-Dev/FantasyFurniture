package dev.apexstudios.fantasyfurniture.dunmer.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.ChestBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class DunmerChestBlock extends ChestBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(12D, 0D, 2D, 14D, 14D, 4D),
            box(-14D, 0D, 2D, -12D, 14D, 4D),
            box(-14D, 0D, 12D, -12D, 14D, 14D),
            box(12D, 0D, 12D, 14D, 14D, 14D),
            box(-15D, 4D, 1D, 15D, 6D, 15D),
            box(-15D, 14D, 1D, 15D, 16D, 15D),
            box(-13D, 6D, 3D, 13D, 14D, 13D),
            box(-2D, 11D, 2D, 2D, 14D, 3D)
    );

    public DunmerChestBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
