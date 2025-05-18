package apexstudios.fantasyfurniture.bone.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.BookshelfBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class BoneBookshelfBlock extends BookshelfBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(11D, 0D, 1D, 15D, 2D, 5D),
            box(-15D, 0D, 1D, -11D, 2D, 5D),
            box(-15D, 0D, 11D, -11D, 2D, 15D),
            box(11D, 0D, 11D, 15D, 2D, 15D),
            box(-15D, 3D, 1D, 15D, 5D, 15D),
            box(-15D, 30D, 1D, 15D, 32D, 15D),
            box(-14D, 5D, 2D, 14D, 30D, 14D),
            box(12D, 2D, 2D, 14D, 3D, 4D),
            box(12D, 2D, 12D, 14D, 3D, 14D),
            box(-14D, 2D, 12D, -12D, 3D, 14D),
            box(-14D, 2D, 2D, -12D, 3D, 4D)
    );

    public BoneBookshelfBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
