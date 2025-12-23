package dev.apexstudios.fantasyfurniture.venthyr.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.SofaBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrSofaBlock extends SofaBlock {
    public static final VoxelShape LEFT_SHAPE = ApexShapes.join(
            box(12D, 0D, 1D, 15D, 2D, 4D),
            box(12D, 0D, 12D, 15D, 2D, 15D),
            box(0D, 2D, 0D, 16D, 6D, 16D),
            box(13D, 6D, 0D, 16D, 10D, 13D),
            box(0D, 6D, 13D, 16D, 16D, 16D)
    );

    public static final VoxelShape RIGHT_SHAPE = ApexShapes.join(
            box(1D, 0D, 1D, 4D, 2D, 4D),
            box(1D, 0D, 12D, 4D, 2D, 15D),
            box(0D, 2D, 13D, 16D, 16D, 16D),
            box(0D, 2D, 0D, 3D, 10D, 13D),
            box(3D, 2D, 0D, 16D, 6D, 13D)
    );

    public static final VoxelShape BOTH_SHAPE = ApexShapes.join(
            box(0D, 2D, 0D, 16D, 6D, 16D),
            box(0D, 6D, 13D, 16D, 16D, 16D)
    );

    public static final VoxelShape CORNER_SHAPE = ApexShapes.join(
            box(1D, 0D, 1D, 4D, 2D, 4D),
            box(1D, 0D, 12D, 4D, 2D, 15D),
            box(12D, 0D, 12D, 15D, 2D, 15D),
            box(12D, 0D, 1D, 15D, 2D, 4D),
            box(13D, 6D, 0D, 16D, 16D, 16D),
            box(0D, 6D, 13D, 13D, 16D, 16D),
            box(0D, 2D, 0D, 16D, 6D, 16D)
    );

    public static final VoxelShape SHAPE = ApexShapes.join(
            box(1D, 0D, 1D, 4D, 2D, 4D),
            box(1D, 0D, 12D, 4D, 2D, 15D),
            box(12D, 0D, 12D, 15D, 2D, 15D),
            box(12D, 0D, 1D, 15D, 2D, 4D),
            box(0D, 2D, 0D, 16D, 6D, 16D),
            box(13D, 6D, 0D, 16D, 10D, 13D),
            box(0D, 6D, 0D, 3D, 10D, 13D),
            box(0D, 6D, 13D, 16D, 16D, 16D)
    );

    public VenthyrSofaBlock(Properties properties) {
        super(properties, LEFT_SHAPE, RIGHT_SHAPE, BOTH_SHAPE, CORNER_SHAPE, SHAPE);
    }
}
