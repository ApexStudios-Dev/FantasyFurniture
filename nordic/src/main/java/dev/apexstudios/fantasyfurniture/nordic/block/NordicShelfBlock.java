package dev.apexstudios.fantasyfurniture.nordic.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.ShelfBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NordicShelfBlock extends ShelfBlock {
    public static final VoxelShape LEFT_SHAPE = ApexShapes.join(
            box(13.5D, 9D, 2D, 15.5D, 14D, 13D),
            box(13D, 6D, 13D, 16D, 14D, 16D)
    );

    public static final VoxelShape RIGHT_SHAPE = ApexShapes.join(
            box(.5D, 9D, 2D, 2.5D, 14D, 13D),
            box(0D, 6D, 13D, 3D, 14D, 16D)
    );

    public static final VoxelShape TOP_SHAPE = box(0D, 14D, 0D, 16D, 16D, 16D);

    public NordicShelfBlock(Properties properties) {
        super(properties, LEFT_SHAPE, RIGHT_SHAPE, TOP_SHAPE);
    }
}
