package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.fantasyfurniture.block.WallLightBlock;
import java.util.Map;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class RoyalWallLightBlock extends WallLightBlock {
    public static final VoxelShape SHAPE = box(4.75D, 1D, 11.75D, 11.25D, 13D, 16D);
    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public RoyalWallLightBlock(Properties properties) {
        super(properties);
    }
}
