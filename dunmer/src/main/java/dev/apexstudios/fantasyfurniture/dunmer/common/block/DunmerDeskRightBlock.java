package dev.apexstudios.fantasyfurniture.dunmer.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.DeskBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class DunmerDeskRightBlock extends DeskBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(12D, 0D, 2D, 14D, 14D, 4D),
            box(-14D, 0D, 2D, -12D, 14D, 4D),
            box(-14D, 0D, 12D, -12D, 14D, 14D),
            box(12D, 0D, 12D, 14D, 14D, 14D),
            box(-16D, 14D, 0D, 16D, 16D, 16D),
            box(-11D, 10D, 2D, -4D, 14D, 11D)
    );

    public DunmerDeskRightBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
