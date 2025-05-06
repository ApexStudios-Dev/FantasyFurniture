package dev.apexstudios.fantasyfurniture.venthyr.block;

import dev.apexstudios.fantasyfurniture.block.ChestBlock;
import java.util.Map;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrChestBlock extends ChestBlock {
    public static final VoxelShape SHAPE = box(-13D, 0D, 1D, 13D, 14.25D, 15D);

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public VenthyrChestBlock(Properties properties) {
        super(properties);
    }
}
