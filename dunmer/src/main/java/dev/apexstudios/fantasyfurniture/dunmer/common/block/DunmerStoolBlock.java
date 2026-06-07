package dev.apexstudios.fantasyfurniture.dunmer.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.StoolBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class DunmerStoolBlock extends StoolBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(12D, 0D, 2D, 14D, 5D, 4D),
            box(2D, 0D, 2D, 4D, 5D, 4D),
            box(2D, 0D, 12D, 4D, 5D, 14D),
            box(12D, 0D, 12D, 14D, 5D, 14D),
            box(1D, 5D, 1, 15D, 7D, 15D),
            box(12.5D, 2.5D, 4D, 13.5D, 3.5D, 12D),
            box(2.5D, 2.5D, 4D, 3.5D, 3.5D, 12D)
    );

    public DunmerStoolBlock(Properties properties) {
        super(properties.bounceRestitution(0F), SHAPE);
    }
}
