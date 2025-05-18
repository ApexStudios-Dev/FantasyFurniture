package dev.apexstudios.fantasyfurniture.dunmer.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.FloorLightBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class DunmerFloorLightBlock extends FloorLightBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(6D, 0D, 6D, 10D, 2D, 10D),
            box(7D, 2D, 7D, 9D, 22D, 9D),
            box(6D, 22D, 6D, 10D, 27D, 10D),
            box(7D, 27D, 7D, 9D, 28D, 9D)
    );

    public DunmerFloorLightBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
