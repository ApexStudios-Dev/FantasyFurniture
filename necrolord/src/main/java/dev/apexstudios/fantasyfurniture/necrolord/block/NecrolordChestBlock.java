package dev.apexstudios.fantasyfurniture.necrolord.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.ChestBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NecrolordChestBlock extends ChestBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(10D, 0D, 1D, 14D, 2D, 5D),
            box(-14D, 0D, 1D, -10D, 2D, 5D),
            box(-14D, 0D, 11D, -10D, 2D, 15D),
            box(10D, 0D, 11D, 14D, 2D, 15D),
            box(10D, 5D, 11D, 14D, 7D, 15D),
            box(10D, 5D, 1D, 14D, 7D, 5D),
            box(-14D, 5D, 1D, -10D, 7D, 5D),
            box(-14D, 5D, 11D, -10D, 7D, 15D),
            box(-12D, 0D, 3D, 12D, 12D, 13D),
            box(11D, 0D, 2D, 13D, 9D, 4D),
            box(-13D, 0D, 2D, -11D, 9D, 4D),
            box(-13D, 0D, 12D, -11D, 9D, 14D),
            box(11D, 0D, 12D, 13D, 9D, 14D),
            box(10D, 9D, 11D, 14D, 11D, 15D),
            box(10D, 9D, 1D, 14D, 11D, 5D),
            box(-14D, 9D, 1D, -10D, 11D, 5D),
            box(-14D, 9D, 11D, -10D, 11D, 15D)
    );

    public NecrolordChestBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
