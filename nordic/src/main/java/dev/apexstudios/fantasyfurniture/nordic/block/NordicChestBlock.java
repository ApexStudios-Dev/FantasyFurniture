package dev.apexstudios.fantasyfurniture.nordic.block;

import dev.apexstudios.fantasyfurniture.block.ChestBlock;
import java.util.Map;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NordicChestBlock extends ChestBlock {
    public static final VoxelShape SHAPE = box(-15D, 0D, 2D, 15D, 14D, 16D);
    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public NordicChestBlock(Properties properties) {
        super(properties);
    }
}
