package apexstudios.fantasyfurniture.bone.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.OvenBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class BoneOvenBlock extends OvenBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(0D, 0D, 0D, 3D, 2D, 3D),
            box(0D, 0D, 13D, 3D, 2D, 16D),
            box(13D, 0D, 13D, 16D, 2D, 16D),
            box(13D, 0D, 0D, 16D, 2D, 3D),
            box(13D, 12D, 0D, 16D, 14D, 3D),
            box(13D, 12D, 13D, 16D, 14D, 16D),
            box(0D, 12D, 13D, 3D, 14D, 16D),
            box(0D, 12D, 0D, 3D, 14D, 3D),
            box(.5D, .5D, .5D, 15.5D, 14D, 15.5D),
            box(0D, 14D, 0D, 16D, 16D, 16D)
    );

    public BoneOvenBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
