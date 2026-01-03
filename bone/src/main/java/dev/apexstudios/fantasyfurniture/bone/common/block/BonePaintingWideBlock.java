package dev.apexstudios.fantasyfurniture.bone.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.PaintingWideBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class BonePaintingWideBlock extends PaintingWideBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(-16D, 0D, 13D, -13D, 3D, 16D),
            box(13D, 0D, 13D, 16D, 3D, 16D),
            box(13D, 13D, 13D, 16D, 16D, 16D),
            box(-16D, 13D, 13D, -13D, 16D, 16D),
            box(-13D, 13D, 13.5D, 13D, 15.5D, 15.5D),
            box(-13D, .5D, 13.5D, 13D, 3D, 15.5D),
            box(-15.5D, 3D, 13.5D, 15.5D, 13D, 15.5D)
    );

    public BonePaintingWideBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
