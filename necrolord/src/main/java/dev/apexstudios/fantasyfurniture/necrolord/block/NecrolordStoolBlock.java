package dev.apexstudios.fantasyfurniture.necrolord.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.StoolBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NecrolordStoolBlock extends StoolBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(2D, 0D, 2D, 5D, 2D, 5D),
            box(2D, 0D, 11D, 5D, 2D, 14D),
            box(11D, 0D, 11D, 14D, 2D, 14D),
            box(11D, 0D, 2D, 14D, 2D, 5D),
            box(11D, 2D, 3D, 13D, 3D, 5D),
            box(3D, 2D, 3D, 5D, 3D, 5D),
            box(3D, 2D, 11D, 5D, 3D, 13D),
            box(11D, 2D, 11D, 13D, 3D, 13D),
            box(2D, 3D, 2D, 14D, 6D, 14D)
    );

    public NecrolordStoolBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
