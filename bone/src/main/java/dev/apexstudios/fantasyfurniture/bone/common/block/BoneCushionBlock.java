package dev.apexstudios.fantasyfurniture.bone.common.block;

import dev.apexstudios.fantasyfurniture.common.block.CushionBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class BoneCushionBlock extends CushionBlock {
    public static final VoxelShape SHAPE = box(4D, 0D, 4D, 12D, 7D, 12D);

    public BoneCushionBlock(Properties properties) {
        super(properties.bounceRestitution(0F), SHAPE);
    }
}
