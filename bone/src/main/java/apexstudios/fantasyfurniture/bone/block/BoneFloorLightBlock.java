package apexstudios.fantasyfurniture.bone.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.FloorLightBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class BoneFloorLightBlock extends FloorLightBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(6D, 0D, 6D, 10D, 2D, 10D),
            box(7D, 2D, 7D, 9D, 23D, 9D),
            box(2.5D, 21.75D, 6.5D, 5.5D, 23.75D, 9.5D),
            box(3.25D, 23.75D, 7.25D, 4.75D, 27.75D, 8.75D),
            box(7.25D, 24.75D, 7.25D, 8.75D, 28.75D, 8.75D),
            box(11.25D, 23.75D, 7.25D, 12.75D, 27.75D, 8.75D),
            box(10.5D, 21.75D, 6.5D, 13.5D, 23.75D, 9.5D),
            box(6.5D, 22.75D, 6.5D, 9.5D, 24.75D, 9.5D),
            box(3D, 17.75D, 7D, 7D, 21.75D, 9D),
            box(9D, 17.75D, 7D, 13D, 21.75D, 9D)
    );

    public BoneFloorLightBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
