package dev.apexstudios.fantasyfurniture.necrolord.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.CushionBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NecrolordCushionBlock extends CushionBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(2D, 0D, 2D, 5D, 2D, 5D),
            box(2D, 0D, 11D, 5D, 2D, 14D),
            box(11D, 0D, 11D, 14D, 2D, 14D),
            box(11D, 0D, 2D, 14D, 2D, 5D),
            box(11D, 2D, 3D, 13D, 3D, 5D),
            box(3D, 2D, 3D, 5D, 3D, 5D),
            box(3D, 2D, 11D, 5D, 3D, 13D),
            box(11D, 2D, 11D, 13D, 3D, 13D),
            box(2D, 3D, 2D, 14D, 4D, 14D),
            box(2.5D, 4D, 2.5D, 13.5D, 7D, 13.5D)
    );

    public NecrolordCushionBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
