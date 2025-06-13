package dev.apexstudios.fantasyfurniture.necrolord.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.SofaBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NecrolordSofaBlock extends SofaBlock {
    public static final VoxelShape LEFT_SHAPE = ApexShapes.join(
            box(12D, 0D, 1D, 15D, 2D, 4D),
            box(12D, 2D, 2D, 14D, 3D, 4D),
            box(12D, 2D, 12D, 14D, 3D, 14D),
            box(12D, 0D, 12D, 15D, 2D, 15D),
            box(0D, 3D, 1D, 15D, 6D, 15D),
            box(0D, 6D, 12D, 15D, 16D, 15D),
            box(13D, 9D, 1D, 15D, 11D, 12D),
            box(13D, 6D, 2D, 15D, 9D, 4D)
    );

    public static final VoxelShape RIGHT_SHAPE = ApexShapes.join(
            box(1D, 0D, 1D, 4D, 2D, 4D),
            box(2D, 2D, 2D, 4D, 3D, 4D),
            box(2D, 2D, 12D, 4D, 3D, 14D),
            box(1D, 0D, 12D, 4D, 2D, 15D),
            box(1D, 3D, 1D, 16D, 6D, 15D),
            box(1D, 6D, 12D, 16D, 16D, 15D),
            box(1D, 9D, 1D, 3D, 11D, 12D),
            box(1D, 6D, 2D, 3D, 9D, 4D)
    );

    public static final VoxelShape BOTH_SHAPE = ApexShapes.join(
            box(0D, 3D, 1D, 16D, 6D, 15D),
            box(0D, 6D, 12D, 16D, 16D, 15D)
    );

    public static final VoxelShape CORNER_SHAPE = ApexShapes.join(
            box(12D, 0D, 12D, 15D, 2D, 15D),
            box(12D, 2D, 12D, 14D, 3D, 14D),
            box(0D, 3D, 12D, 15D, 16D, 15D),
            box(12D, 3D, 0D, 15D, 16D, 12D),
            box(1D, 3D, 0D, 15D, 6D, 12D),
            box(0D, 3D, 1D, 14D, 6D, 12D)
    );

    public static final VoxelShape SHAPE = ApexShapes.join(
            box(1D, 0D, 1D, 4D, 2D, 4D),
            box(2D, 2D, 2D, 4D, 3D, 4D),
            box(12D, 0D, 1D, 15D, 2D, 4D),
            box(12D, 2D, 2D, 14D, 3D, 4D),
            box(2D, 2D, 12D, 4D, 3D, 14D),
            box(1D, 0D, 12D, 4D, 2D, 15D),
            box(12D, 0D, 12D, 15D, 2D, 15D),
            box(12D, 2D, 12D, 14D, 3D, 14D),
            box(1D, 3D, 1D, 15D, 6D, 15D),
            box(1D, 6D, 12D, 15D, 16D, 15D),
            box(1D, 9D, 1D, 3D, 11D, 12D),
            box(1D, 6D, 2D, 3D, 9D, 4D),
            box(13D, 9D, 1D, 15D, 11D, 12D),
            box(13D, 6D, 2D, 15D, 9D, 4D)
    );

    public NecrolordSofaBlock(Properties properties) {
        super(properties, LEFT_SHAPE, RIGHT_SHAPE, BOTH_SHAPE, CORNER_SHAPE, SHAPE);
    }
}
