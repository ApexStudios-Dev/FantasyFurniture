package dev.apexstudios.fantasyfurniture.necrolord.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.TableBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NecrolordTableBlock extends TableBlock {
    public static final VoxelShape SHAPE_TABLE_TOP = box(0D, 14D, 0D, 16D, 16D, 16D);

    public static final VoxelShape SHAPE_TABLE_LEG = ApexShapes.join(
            box(12D, 0D, 0D, 16D, 2D, 4D),
            box(12D, 9D, 0D, 16D, 11D, 4D),
            box(13D, 2D, 1D, 15D, 14D, 3D)
    );

    public NecrolordTableBlock(Properties properties) {
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
