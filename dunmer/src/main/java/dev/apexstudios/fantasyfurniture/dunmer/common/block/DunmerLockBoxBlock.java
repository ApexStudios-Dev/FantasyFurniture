package dev.apexstudios.fantasyfurniture.dunmer.common.block;

import dev.apexstudios.fantasyfurniture.common.block.LockBoxBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class DunmerLockBoxBlock extends LockBoxBlock {
    public static final VoxelShape SHAPE = box(2D, 0D, 3D, 14D, 14D, 13D);

    public DunmerLockBoxBlock(Properties properties) {
        super(properties, SHAPE);
    }
}
