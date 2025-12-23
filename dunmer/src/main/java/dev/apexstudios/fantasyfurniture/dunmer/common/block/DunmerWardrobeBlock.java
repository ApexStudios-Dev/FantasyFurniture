package dev.apexstudios.fantasyfurniture.dunmer.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.WardrobeBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class DunmerWardrobeBlock extends WardrobeBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(13D, 32D, 1D, 15D, 40D, 3D),
            box(-15D, 32D, 1D, -13D, 40D, 3D),
            box(-15D, 32D, 13D, -13D, 40D, 15D),
            box(13D, 32D, 13D, 15D, 40D, 15D),
            box(-16D, 40D, 0D, 16D, 42D, 16D),
            box(-13D, 32D, 2D, 13D, 40D, 14D),
            box(-15D, 0D, 1D, -13D, 30D, 3D),
            box(-15D, 0D, 13D, -13D, 30D, 15D),
            box(13D, 0D, 13D, 15D, 30D, 15D),
            box(13D, 0D, 1D, 15D, 30D, 3D),
            box(-16D, 30D, 0D, 16D, 32D, 16D),
            box(-16D, 20D, 0D, 16D, 22D, 16D),
            box(-16D, 2D, 0D, 16D, 4D, 16D),
            box(-13D, 4D, 2D, 13D, 30D, 14D)
    );

    public DunmerWardrobeBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
