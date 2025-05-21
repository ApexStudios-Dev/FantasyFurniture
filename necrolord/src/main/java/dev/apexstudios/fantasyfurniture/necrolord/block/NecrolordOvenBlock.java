package dev.apexstudios.fantasyfurniture.necrolord.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.OvenBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NecrolordOvenBlock extends OvenBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(1D, 0D, 1D, 4D, 2D, 4D),
            box(1D, 0D, 12D, 4D, 2D, 15D),
            box(12D, 0D, 12D, 15D, 2D, 15D),
            box(12D, 0D, 1D, 15D, 2D, 4D),
            box(0D, 2D, 0D, 16D, 16D, 16D)
    );

    public NecrolordOvenBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
