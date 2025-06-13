package dev.apexstudios.fantasyfurniture.necrolord.block;

import dev.apexstudios.fantasyfurniture.block.LockBoxBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NecrolordLockBoxBlock extends LockBoxBlock {
    public static final VoxelShape SHAPE = box(.5D, 0D, 2.5D, 15.5D, 10D, 13.5D);

    public NecrolordLockBoxBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
