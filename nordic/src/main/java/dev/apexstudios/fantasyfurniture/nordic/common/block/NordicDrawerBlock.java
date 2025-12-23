package dev.apexstudios.fantasyfurniture.nordic.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.DrawerBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NordicDrawerBlock extends DrawerBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(1D, 0D, 1D, 15D, 13D, 15D),
            box(0D, 13D, 0D, 16D, 16D, 16D)
    );

    public NordicDrawerBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
