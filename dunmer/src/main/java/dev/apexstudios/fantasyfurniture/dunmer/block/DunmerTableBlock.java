package dev.apexstudios.fantasyfurniture.dunmer.block;

import dev.apexstudios.fantasyfurniture.block.TableBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class DunmerTableBlock extends TableBlock {
    public static final VoxelShape SHAPE_TABLE_TOP = box(0D, 14D, 0D, 16D, 16D, 16D);

    public DunmerTableBlock(Properties properties) {
        super(properties);
    }
}
