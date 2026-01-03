package dev.apexstudios.fantasyfurniture.necrolord.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.ShelfBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NecrolordShelfBlock extends ShelfBlock {
    public static final VoxelShape LEFT_SHAPE = ApexShapes.join(
            box(12D, 4D, 13D, 15D, 6D, 16D),
            box(12.5D, 6D, 14D, 14.5D, 11D, 16D),
            box(12D, 11D, 13D, 15D, 14D, 16D),
            box(12.5D, 12D, 8D, 14.5D, 14D, 13D)
    );

    public static final VoxelShape RIGHT_SHAPE = ApexShapes.join(
            box(1D, 4D, 13D, 4D, 6D, 16D),
            box(1.5D, 6D, 14D, 3.5D, 11D, 16D),
            box(1D, 11D, 13D, 4D, 14D, 16D),
            box(1.5D, 12D, 8D, 3.5D, 14D, 13D)
    );

    public static final VoxelShape TOP_SHAPE = box(0D, 14D, 0D, 16D, 16D, 16D);

    public NecrolordShelfBlock(Properties properties) {
        super(properties, LEFT_SHAPE, RIGHT_SHAPE, TOP_SHAPE);
    }
}
