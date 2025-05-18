package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.fantasyfurniture.block.LockBoxBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class RoyalLockBoxBlock extends LockBoxBlock {
    public static final VoxelShape SHAPE = box(2.5D, 0D, 3.5D, 13.5D, 9.25D, 12.5D);

    public RoyalLockBoxBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
