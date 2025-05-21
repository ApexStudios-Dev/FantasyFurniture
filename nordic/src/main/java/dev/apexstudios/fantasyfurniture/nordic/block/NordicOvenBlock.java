package dev.apexstudios.fantasyfurniture.nordic.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.OvenBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NordicOvenBlock extends OvenBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(0D, 0D, 0D, 16D, 1D, 16D),
            box(0D, 1D, 1D, 16D, 9D, 16D),
            box(0D, 9D, 0D, 16D, 10D, 16D),
            box(1D, 10D, 3D, 15D, 14D, 16D),
            box(2D, 14D, 3D, 14D, 16D, 16D)
    );

    public NordicOvenBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
