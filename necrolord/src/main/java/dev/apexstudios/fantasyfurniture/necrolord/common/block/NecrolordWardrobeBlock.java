package dev.apexstudios.fantasyfurniture.necrolord.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.WardrobeBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NecrolordWardrobeBlock extends WardrobeBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(12D, 0D, 0D, 16D, 2D, 4D),
            box(-16D, 0D, 0D, -12D, 2D, 4D),
            box(-16D, 0D, 12D, -12D, 2D, 16D),
            box(12D, 0D, 12D, 16D, 2D, 16D),
            box(12D, 8D, 12D, 16D, 10D, 16D),
            box(-16D, 8D, 12D, -12D, 10D, 16D),
            box(-16D, 8D, 0D, -12D, 10D, 4D),
            box(12D, 8D, 0D, 16D, 10D, 4D),
            box(12D, 30D, 0D, 16D, 32D, 4D),
            box(-16D, 30D, 0D, -12D, 32D, 4D),
            box(-16D, 30D, 12D, -12D, 32D, 16D),
            box(12D, 30D, 12D, 16D, 32D, 16D),
            box(-15D, 0D, 1D, 15D, 32D, 15D),
            box(-15D, 32D, 1D, 15D, 43D, 15D),
            box(12D, 41D, 0D, 16D, 43D, 4D),
            box(-16D, 41D, 0D, -12D, 43D, 4D),
            box(-16D, 41D, 12D, -12D, 43D, 16D),
            box(12D, 41D, 12D, 16D, 43D, 16D)
    );

    public NecrolordWardrobeBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
