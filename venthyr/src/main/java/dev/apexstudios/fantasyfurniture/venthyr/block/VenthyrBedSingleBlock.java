package dev.apexstudios.fantasyfurniture.venthyr.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.BedSingleBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrBedSingleBlock extends BedSingleBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(0D, 0D, 29D, 3D, 2D, 32D),
            box(13D, 0D, 29D, 16D, 2D, 32D),
            box(13D, 0D, 0D, 16D, 2D, 3D),
            box(0D, 0D, 0D, 3D, 2D, 3D),
            box(.5D, 2D, .5D, 2.5D, 12D, 2.5D),
            box(.5D, 2D, 29.5D, 2.5D, 12D, 31.5D),
            box(13.5D, 2D, 29.5D, 15.5D, 12D, 31.5D),
            box(13.5D, 2D, .5D, 15.5D, 12D, 2.5D),
            box(13D, 12D, 0D, 16D, 14D, 3D),
            box(0D, 12D, 0D, 3D, 14D, 3D),
            box(0D, 12D, 29D, 3D, 14D, 32D),
            box(13D, 12D, 29D, 16D, 14D, 32D),
            box(.5D, 0D, 2D, 15.5D, 5D, 30D),
            box(2.5D, 0D, 29.5D, 13.5D, 11D, 31.5D),
            box(2.5D, 0D, .5D, 13.5D, 13D, 2.5D),
            box(1.5D, 5D, 2.5D, 14.5D, 8D, 29.5D)
    );

    public VenthyrBedSingleBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
