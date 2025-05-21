package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.OvenBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class RoyalOvenBlock extends OvenBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(0D, 0D, 0D, 3.5D, 4D, 3.5D),
            box(0D, 0D, 12.5D, 3.5D, 4D, 16D),
            box(12.5D, 0D, 12.5D, 16D, 4D, 16D),
            box(12.5D, 0D, 0D, 16D, 4D, 3.5D),
            box(0D, 4D, 0D, 16D, 6D, 16D),
            box(0D, 14D, 0D, 16D, 16D, 16D),
            box(.5D, 6D, .5D, 15.5D, 14D, 15.5D),
            box(2.5D, 7D, -.5D, 12.5D, 13D, .5D)
    );

    public RoyalOvenBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
