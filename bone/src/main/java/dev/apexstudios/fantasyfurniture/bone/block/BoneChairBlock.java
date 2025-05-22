package dev.apexstudios.fantasyfurniture.bone.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.ChairBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class BoneChairBlock extends ChairBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(1.5D, 0D, 1.5D, 4.5D, 3D, 4.5D),
            box(1.5D, 0D, 11.5D, 4.5D, 3D, 14.5D),
            box(11.5D, 0D, 11.5D, 14.5D, 3D, 14.5D),
            box(11.5D, 0D, 1.5D, 14.5D, 3D, 4.5D),
            box(2D, 3D, 12D, 4D, 7D, 14D),
            box(2D, 3D, 2D, 4D, 7D, 4D),
            box(12D, 3D, 2D, 14D, 7D, 4D),
            box(12D, 3D, 12D, 14D, 7D, 14D),
            box(1.5D, 7D, 1.5D, 14.5D, 9D, 14.5D),
            box(2D, 9D, 12D, 14D, 26D, 14D)
    );

    public BoneChairBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
