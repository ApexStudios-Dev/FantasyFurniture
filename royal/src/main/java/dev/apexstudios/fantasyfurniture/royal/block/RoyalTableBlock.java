package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.fantasyfurniture.block.TableBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class RoyalTableBlock extends TableBlock {
    public static final VoxelShape SHAPE_TABLE_TOP = box(0D, 14D, 0D, 16D, 16D, 16D);
    public static final VoxelShape SHAPE_TABLE_LEG = box(11D, 0D, 1D, 15D, 14D, 5D);

    public RoyalTableBlock(Properties properties) {
        super(properties);
    }
}
