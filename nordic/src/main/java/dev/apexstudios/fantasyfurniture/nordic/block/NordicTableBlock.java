package dev.apexstudios.fantasyfurniture.nordic.block;

import dev.apexstudios.fantasyfurniture.block.TableBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NordicTableBlock extends TableBlock {
    public static final VoxelShape SHAPE_TABLE_TOP = box(0D, 13D, 0D, 16D, 16D, 16D);
    public static final VoxelShape SHAPE_TABLE_LEG = box(13D, 0D, 1D, 15D, 13D, 3D);

    public NordicTableBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getLegShape() {
        return SHAPE_TABLE_LEG;
    }

    @Override
    protected VoxelShape getTopShape() {
        return SHAPE_TABLE_TOP;
    }
}
