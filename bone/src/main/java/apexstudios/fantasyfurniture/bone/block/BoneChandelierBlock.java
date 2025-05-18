package apexstudios.fantasyfurniture.bone.block;

import dev.apexstudios.fantasyfurniture.block.ChandelierBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class BoneChandelierBlock extends ChandelierBlock {
    public static final VoxelShape SHAPE = box(0D, 0D, 0D, 16D, 16D, 16D);

    public BoneChandelierBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
