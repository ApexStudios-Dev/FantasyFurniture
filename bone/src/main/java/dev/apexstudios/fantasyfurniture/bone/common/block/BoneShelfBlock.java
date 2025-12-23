package dev.apexstudios.fantasyfurniture.bone.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.ShelfBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class BoneShelfBlock extends ShelfBlock {
    public static final VoxelShape TOP_SHAPE = box(0D, 14D, 0D, 16D, 16D, 16D);

    public static final VoxelShape LEFT_SHAPE = ApexShapes.join(
            box(13D, 6D, 13D, 16D, 8D, 16D),
            box(13.5D, 8D, 14D, 15.5D, 14D, 16D)
    );

    public static final VoxelShape RIGHT_SHAPE = ApexShapes.join(
            box(0D, 6D, 13D, 3D, 8D, 16D),
            box(.5D, 8D, 14D, 2.5D, 15D, 16D)
    );

    public BoneShelfBlock(Properties properties) {
        super(properties, LEFT_SHAPE, RIGHT_SHAPE, TOP_SHAPE);
    }
}
