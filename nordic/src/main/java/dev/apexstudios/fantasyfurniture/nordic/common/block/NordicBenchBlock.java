package dev.apexstudios.fantasyfurniture.nordic.common.block;

import dev.apexstudios.apexcore.api.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.common.block.BenchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NordicBenchBlock extends BenchBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(12D, 0D, 2D, 14D, 3D, 4D),
            box(-14D, 0D, 2D, -12D, 3D, 4D),
            box(-14D, 0D, 12D, -12D, 3D, 14D),
            box(12D, 0D, 12D, 14D, 3D, 14D),
            box(12D, 3D, 11.5D, 14D, 5D, 13.5D),
            box(12D, 3D, 2.5D, 14D, 5D, 4.5D),
            box(-14D, 3D, 2.5D, -12D, 5D, 4.5D),
            box(-14D, 3D, 11.5D, -12D, 5D, 13.5D),
            box(-13.5D, 3.5D, 4.5D, -12.5D, 4.5D, 11.5D),
            box(12.5D, 3.5D, 4.5D, 13.5D, 4.5D, 11.5D),
            box(-15D, 5D, 2D, 15D, 7D, 14D)
    );

    public NordicBenchBlock(Properties properties) {
        super(properties, SHAPE);
    }

    @Override
    protected boolean isBouncy(BlockState blockState) {
        return false;
    }
}
