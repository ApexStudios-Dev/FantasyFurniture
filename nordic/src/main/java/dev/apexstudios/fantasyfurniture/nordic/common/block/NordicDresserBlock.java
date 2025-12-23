package dev.apexstudios.fantasyfurniture.nordic.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.DresserBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NordicDresserBlock extends DresserBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(-15D, 0D, 1D, 15D, 16D, 15D),
            box(-16D, 13D, 14D, 16D, 16D, 16D),
            box(-16D, 13D, 0D, 16D, 16D, 2D)
    );

    public NordicDresserBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
