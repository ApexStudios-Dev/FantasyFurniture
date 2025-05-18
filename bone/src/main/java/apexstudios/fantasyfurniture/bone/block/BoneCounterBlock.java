package apexstudios.fantasyfurniture.bone.block;

import dev.apexstudios.fantasyfurniture.block.CounterBlock;
import net.minecraft.world.phys.shapes.Shapes;

public final class BoneCounterBlock extends CounterBlock {
    public BoneCounterBlock(Properties properties) {
        super(properties, Shapes.block(), Shapes.block()); // TODO
    }
}
