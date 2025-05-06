package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.ShelfBlock;
import java.util.Map;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class RoyalShelfBlock extends ShelfBlock {
    public static final VoxelShape LEFT_SHAPE = ApexShapes.join(
            box(0D, 14D, 0D, 16D, 16D, 16D),
            box(13D, 11D, 3D, 15D, 14D, 6D),
            box(13D, 12D, 6D, 15D, 14D, 16D),
            box(13D, 8D, 14D, 15D, 12D, 16D),
            box(13D, 5D, 13D, 15D, 8D, 16D)
    );

    public static final VoxelShape RIGHT_SHAPE = ApexShapes.join(
            box(0D, 14D, 0D, 16D, 16D, 16D),
            box(1D, 11D, 3D, 3D, 14D, 6D),
            box(1D, 12D, 6D, 3D, 14D, 16D),
            box(1D, 8D, 14D, 3D, 12D, 16D),
            box(1D, 5D, 13D, 3D, 8D, 16D)
    );

    public static final VoxelShape BOTH_SHAPE = box(0D, 14D, 0D, 16D, 16D, 16D);

    public static final VoxelShape SHAPE = ApexShapes.join(
            box(0D, 14D, 0D, 16D, 16D, 16D),
            box(13D, 11D, 3D, 15D, 14D, 6D),
            box(13D, 12D, 6D, 15D, 14D, 16D),
            box(13D, 8D, 14D, 15D, 12D, 16D),
            box(13D, 5D, 13D, 15D, 8D, 16D),
            box(1D, 5D, 13D, 3D, 8D, 16D),
            box(1D, 11D, 3D, 3D, 14D, 6D),
            box(1D, 12D, 6D, 3D, 14D, 16D),
            box(1D, 8D, 14D, 3D, 12D, 16D)
    );

    public static final Map<Direction, VoxelShape> LEFT_FACING_SHAPES = Shapes.rotateHorizontal(LEFT_SHAPE);
    public static final Map<Direction, VoxelShape> RIGHT_FACING_SHAPES = Shapes.rotateHorizontal(RIGHT_SHAPE);
    public static final Map<Direction, VoxelShape> BOTH_FACING_SHAPES = Shapes.rotateHorizontal(BOTH_SHAPE);
    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public RoyalShelfBlock(Properties properties) {
        super(properties);
    }
}
