package dev.apexstudios.fantasyfurniture.bone.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.TableBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class BoneTableBlock extends TableBlock {
    public static final VoxelShape SHAPE_TABLE_TOP = box(0D, 14D, 0D, 16D, 16D, 16D);

    public static final VoxelShape SHAPE_TABLE_LEG = ApexShapes.join(
            box(12D, 12D, 1D, 15D, 14D, 4D),
            box(12D, 0D, 1D, 15D, 2D, 4D),
            box(12.5D, 2D, 1.5D, 14.5D, 13D, 3.5D)
    );

    public BoneTableBlock(Properties properties) {
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
