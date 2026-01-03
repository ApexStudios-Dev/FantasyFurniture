package dev.apexstudios.fantasyfurniture.bone.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.PaintingSmallBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class BonePaintingSmallBlock extends PaintingSmallBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(0D, 0D, 13D, 3D, 3D, 16D),
            box(13D, 0D, 13D, 16D, 3D, 16D),
            box(13D, 13D, 13D, 16D, 16D, 16D),
            box(0D, 13D, 13D, 3D, 16D, 16D),
            box(3D, 13D, 13.5D, 13D, 15.5D, 15.5D),
            box(3D, .5D, 13.5D, 13D, 3D, 15.5D),
            box(.5D, 3D, 13.5D, 15.5D, 13D, 15.5D)
    );

    public BonePaintingSmallBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
