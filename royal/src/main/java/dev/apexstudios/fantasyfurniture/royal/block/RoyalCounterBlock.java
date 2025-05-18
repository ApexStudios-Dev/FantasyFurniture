package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.CounterBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class RoyalCounterBlock extends CounterBlock {
    public static final VoxelShape CORNER_SHAPE = ApexShapes.join(
            box(0D, 0D, 0D, 13D, 13D, 3D),
            box(0D, 0D, 3D, 16D, 13D, 16D),
            box(0D, 13D, 0D, 16D, 16D, 16D)
    );

    public static final VoxelShape SHAPE = ApexShapes.join(
            box(0D, 0D, 3D, 16D, 13D, 16D),
            box(0D, 13D, 0D, 16D, 16D, 16D)
    );

    public RoyalCounterBlock(Properties properties) {
        super(properties, SHAPE, CORNER_SHAPE);
    }
}
