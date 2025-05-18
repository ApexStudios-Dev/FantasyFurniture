package apexstudios.fantasyfurniture.bone.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.DeskBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class BoneDeskLeftBlock extends DeskBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(-15D, 0D, 1D, -12D, 2D, 4D),
            box(-15D, 0D, 12D, -12D, 2D, 15D),
            box(12D, 0D, 12D, 15D, 2D, 15D),
            box(12D, 0D, 1D, 15D, 2D, 4D),
            box(12D, 12D, 1D, 15D, 14D, 4D),
            box(-15D, 12D, 1D, -12D, 14D, 4D),
            box(12D, 12D, 12D, 15D, 14D, 15D),
            box(-15D, 12D, 12D, -12D, 14D, 15D),
            box(12.5D, 2D, 1.5D, 14.5D, 12D, 3.5D),
            box(-14.5D, 2D, 1.5D, -12.5D, 12D, 3.5D),
            box(12.5D, 2D, 12.5D, 14.5D, 12D, 14.5D),
            box(-14.5D, 2D, 12.5D, -12.5D, 12D, 14.5D),
            box(-16D, 14D, 0D, 16D, 16D, 16D),
            box(5D, 9D, 3D, 12D, 14D, 13D)
    );

    public BoneDeskLeftBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
