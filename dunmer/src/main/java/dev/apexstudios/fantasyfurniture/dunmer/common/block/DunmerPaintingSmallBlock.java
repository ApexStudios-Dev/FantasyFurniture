package dev.apexstudios.fantasyfurniture.dunmer.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.PaintingSmallBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class DunmerPaintingSmallBlock extends PaintingSmallBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(0D, 1D, 14D, 16D, 3D, 16D),
            box(0D, 13D, 14D, 16D, 15D, 16D),
            box(1D, 3D, 14D, 15D, 13D, 16D)
    );

    public DunmerPaintingSmallBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
