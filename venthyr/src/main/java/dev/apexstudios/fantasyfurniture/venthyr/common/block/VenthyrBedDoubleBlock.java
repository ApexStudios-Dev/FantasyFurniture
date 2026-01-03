package dev.apexstudios.fantasyfurniture.venthyr.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.BedDoubleBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrBedDoubleBlock extends BedDoubleBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(-16D, 0D, 0D, -13D, 2D, 3D),
            box(-15.5D, 2D, .5D, -13.5D, 12D, 2.5D),
            box(-15.5D, 2D, 29.5D, -13.5D, 12D, 31.5D),
            box(13.5D, 2D, 29.5D, 15.5D, 12D, 31.5D),
            box(13.5D, 2D, .5D, 15.5D, 12D, 2.5D),
            box(-16D, 12D, 0D, -13D, 14D, 3D),
            box(-16D, 12D, 29D, -13D, 14D, 32D),
            box(13D, 12D, 29D, 16D, 14D, 32D),
            box(13D, 12D, 0D, 16D, 14D, 3D),
            box(13D, 0D, 0D, 16D, 2D, 3D),
            box(13D, 0D, 29D, 16D, 2D, 32D),
            box(-16D, 0D, 29D, -13D, 2D, 32D),
            box(-15.5D, 0D, 2.5D, 15.5D, 5D, 29.5D),
            box(-13.5D, 0D, 29.5D, 13.5D, 12D, 31.5D),
            box(-13.5D, 0D, .5D, 13.5D, 14D, 2.5D),
            box(-14.5D, 0D, 2.5D, 14.5D, 8D, 29.5D)
    );

    public VenthyrBedDoubleBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
