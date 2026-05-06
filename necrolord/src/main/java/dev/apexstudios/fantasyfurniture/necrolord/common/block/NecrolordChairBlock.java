package dev.apexstudios.fantasyfurniture.necrolord.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.ChairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NecrolordChairBlock extends ChairBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(1D, 0D, 1D, 4D, 2D, 4D),
            box(1D, 7D, 1D, 4D, 9D, 4D),
            box(1D, 0D, 12D, 4D, 2D, 15D),
            box(1D, 7D, 12D, 4D, 9D, 15D),
            box(12D, 0D, 12D, 15D, 2D, 15D),
            box(12D, 7D, 12D, 15D, 9D, 15D),
            box(12D, 7D, 1D, 15D, 9D, 4D),
            box(12D, 0D, 1D, 15D, 2D, 4D),
            box(2D, 0D, 2D, 14D, 9D, 14D),
            box(2D, 9D, 12D, 14D, 24D, 14D),
            box(12D, 20D, 11.5D, 15D, 24D, 14.5D),
            box(1D, 20D, 11.5D, 4D, 24D, 14.5D),
            box(2D, 24D, 11.5D, 5D, 27D, 14.5D),
            box(11D, 24D, 11.5D, 14D, 27D, 14.5D),
            box(10D, 27D, 11.5D, 12D, 29D, 14.5D),
            box(4D, 27D, 11.5D, 6D, 29D, 14.5D)
    );

    public NecrolordChairBlock(Properties properties) {
        super(properties, SHAPE);
    }

    @Override
    protected boolean isBouncy(BlockState blockState) {
        return false;
    }
}
