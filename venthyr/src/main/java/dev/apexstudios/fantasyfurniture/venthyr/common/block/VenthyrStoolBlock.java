package dev.apexstudios.fantasyfurniture.venthyr.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.StoolBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrStoolBlock extends StoolBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(2D, 0D, 2D, 5D, 4D, 5D),
            box(2D, 0D, 11D, 5D, 4D, 14D),
            box(11D, 0D, 11D, 14D, 4D, 14D),
            box(11D, 0D, 2D, 14D, 4D, 5D),
            box(1D, 4D, 1D, 15D, 7D, 15D)
    );

    public VenthyrStoolBlock(Properties properties) {
        super(properties, SHAPE);
    }

    @Override
    protected boolean isBouncy(BlockState blockState) {
        return false;
    }
}
