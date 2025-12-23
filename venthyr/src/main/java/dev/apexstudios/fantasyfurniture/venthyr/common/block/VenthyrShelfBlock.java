package dev.apexstudios.fantasyfurniture.venthyr.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.ShelfBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrShelfBlock extends ShelfBlock {
    public static final VoxelShape LEFT_SHAPE = ApexShapes.join(
            box(13D, 3D, 12D, 16D, 6D, 16D),
            box(13D, 9D, 10D, 16D, 13D, 16D),
            box(13D, 11D, 3D, 16D, 13D, 10D),
            box(13D, 10D, 0D, 16D, 13D, 3D),
            box(13D, 6D, 13D, 16D, 9D, 16D)
    );

    public static final VoxelShape RIGHT_SHAPE = ApexShapes.join(
            box(0D, 3D, 12D, 3D, 6D, 16D),
            box(0D, 6D, 13D, 3D, 9D, 16D),
            box(0D, 9D, 10D, 3D, 13D, 16D),
            box(0D, 11D, 3D, 3D, 13D, 10D),
            box(0D, 10D, 0D, 3D, 13D, 3D)
    );

    public static final VoxelShape TOP_SHAPE = box(0D, 13D, 0D, 16D, 16D, 16D);

    public VenthyrShelfBlock(Properties properties) {
        super(properties, LEFT_SHAPE, RIGHT_SHAPE, TOP_SHAPE);
    }
}
