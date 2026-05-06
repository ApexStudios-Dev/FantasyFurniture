package dev.apexstudios.fantasyfurniture.bone.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.SofaBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class BoneSofaBlock extends SofaBlock {
    public static final VoxelShape LEFT_SHAPE = ApexShapes.join(
            box(0D, 4D, 1.5D, 14D, 6D, 14.5D),
            box(0D, 6D, 12.5D, 14D, 15D, 14.5D),
            box(14D, 3.5D, 12D, 16D, 15.5D, 15D),
            box(14D, 3.5D, 2D, 16D, 11.5D, 12D),
            box(14D, 3.5D, 1D, 16D, 6.5D, 2D),
            box(12D, 2D, 1.5D, 14D, 4D, 3.5D),
            box(12D, 2D, 12.5D, 14D, 4D, 14.5D),
            box(12D, 3D, 3.5D, 14D, 4D, 12.5D),
            box(11.5D, 0D, 1D, 14.5D, 2D, 4D),
            box(11.5D, 0D, 12D, 14.5D, 2D, 15D)
    );

    public static final VoxelShape RIGHT_SHAPE = ApexShapes.join(
            box(2D, 4D, 1.5D, 16D, 6D, 14.5D),
            box(2D, 6D, 12.5D, 16D, 15D, 14.5D),
            box(0D, 3.5D, 12D, 2D, 15.5D, 15D),
            box(0D, 3.5D, 2D, 2D, 11.5D, 12D),
            box(0D, 3.5D, 1D, 2D, 6.5D, 2D),
            box(2D, 2D, 1.5D, 4D, 4D, 3.5D),
            box(2D, 2D, 12.5D, 4D, 4D, 14.5D),
            box(2D, 3D, 3.5D, 4D, 4D, 12.5D),
            box(1.5D, 0D, 1D, 4.5D, 2D, 4D),
            box(1.5D, 0D, 12D, 4.5D, 2D, 15D)
    );

    public static final VoxelShape BOTH_SHAPE = ApexShapes.join(
            box(0D, 4D, 1.5D, 16D, 6D, 14.5D),
            box(0D, 6D, 12.5D, 16D, 15D, 14.5D)
    );

    public static final VoxelShape CORNER_SHAPE = ApexShapes.join(
            box(1D, 0D, 1D, 4, 2D, 4D),
            box(1D, 0D, 11.5D, 4D, 2D, 14.5D),
            box(11.5D, 0D, 11.5D, 14.5D, 2D, 14.5D),
            box(11.5D, 0D, 1D, 14.5D, 2D, 4D),
            box(12D, 2D, 1.5D, 14D, 4D, 3.5D),
            box(1.5D, 2D, 1.5D, 3.5D, 4D, 3.5D),
            box(1.5D, 2D, 12D, 3.5D, 4D, 14D),
            box(12D, 2D, 12D, 14D, 4D, 14D),
            box(0D, 4D, 1.5D, 1.5D, 6D, 14.5D),
            box(1.5D, 4D, 0D, 14.5D, 6D, 14.5D),
            box(12.5D, 6D, 0D, 14.5D, 15D, 14.5D),
            box(12D, 4D, 12D, 15D, 15.5D, 15D),
            box(0D, 6D, 12.5D, 12D, 15D, 14.5D)
    );

    public static final VoxelShape SHAPE = ApexShapes.join(
            box(1.5D, 0D, 1D, 4.5D, 2D, 4D),
            box(1.5D, 0D, 12D, 4.5D, 2D, 15D),
            box(11.5D, 0D, 12D, 14.5D, 2D, 15D),
            box(11.5D, 0D, 1D, 14.5D, 2D, 4D),
            box(12D, 2D, 1.5D, 14D, 4D, 3.5D),
            box(2D, 2D, 1.5D, 4D, 4D, 3.5D),
            box(2D, 2D, 12.5D, 4D, 4D, 14.5D),
            box(12D, 2D, 12.5D, 14D, 4D, 14.5D),
            box(12D, 3D, 3.5D, 14D, 4D, 12.5D),
            box(2D, 3D, 3.5D, 4D, 4D, 12.5D),
            box(2D, 4D, 1.5D, 14D, 6D, 14.5D),
            box(2D, 6D, 12.5D, 14D, 15D, 14.5D),
            box(14D, 3.5D, 12D, 16D, 15.5D, 15D),
            box(14D, 6D, 2D, 16D, 11.5D, 12D),
            box(14D, 3.5D, 1D, 16D, 6.5D, 12D),
            box(0D, 3.5D, 1D, 2D, 6.5D, 12D),
            box(0D, 3.5D, 12D, 2D, 15.5D, 15D),
            box(0D, 6D, 2D, 2D, 11.5D, 12D)
    );

    public BoneSofaBlock(Properties properties) {
        super(properties, LEFT_SHAPE, RIGHT_SHAPE, BOTH_SHAPE, CORNER_SHAPE, SHAPE);
    }

    @Override
    protected boolean isBouncy(BlockState blockState) {
        return false;
    }
}
