package dev.apexstudios.fantasyfurniture.nordic.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.SofaBlock;
import java.util.Map;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NordicSofaBlock extends SofaBlock {
    public static final VoxelShape LEFT_SHAPE = ApexShapes.join(
            box(0D, 3D, 0D, 16D, 6D, 16D),
            box(0D, 6D, 13D, 16D, 16D, 16D),
            box(14D, 10D, 0D, 16D, 12D, 13D),
            box(14D, 6D, 0D, 16D, 10D, 2D),
            box(13D, 0D, 1D, 15D, 3D, 3D),
            box(13D, 0D, 13D, 15D, 3D, 15D)
    );

    public static final VoxelShape RIGHT_SHAPE = ApexShapes.join(
            box(0D, 3D, 0D, 16D, 6D, 16D),
            box(0D, 6D, 13D, 16D, 16D, 16D),
            box(0D, 10D, 0D, 2D, 12D, 13D),
            box(0D, 6D, 0D, 2D, 10D, 2D),
            box(1D, 0D, 1D, 3D, 3D, 3D),
            box(1D, 0D, 13D, 3D, 3D, 15D)
    );

    public static final VoxelShape BOTH_SHAPE = ApexShapes.join(
            box(0D, 3D, 0D, 16D, 6D, 16D),
            box(0D, 6D, 13D, 16D, 16D, 16D)
    );

    public static final VoxelShape CORNER_SHAPE = ApexShapes.join(
            box(1D, 0D, 1D, 3D, 3D, 3D),
            box(1D, 0D, 13D, 3D, 3D, 15D),
            box(13D, 0D, 13D, 15D, 3D, 15D),
            box(13D, 0D, 1D, 15D, 3D, 3D),
            box(0D, 3D, 0D, 16D, 6D, 16D),
            box(0D, 6D, 13D, 16D, 16D, 16D),
            box(13D, 6D, 0D, 16D, 16D, 13D)
    );

    public static final VoxelShape SHAPE = ApexShapes.join(
            box(1D, 0D, 1D, 3D, 3D, 3D),
            box(1D, 0D, 13D, 3D, 3D, 15D),
            box(13D, 0D, 13D, 15D, 3D, 15D),
            box(13D, 0D, 1D, 15D, 3D, 3D),
            box(0D, 3D, 0D, 16D, 6D, 16D),
            box(0D, 6D, 13D, 16D, 16D, 16D),
            box(14D, 10D, 0D, 16D, 12D, 14D),
            box(0D, 10D, 0D, 2D, 12D, 14D),
            box(0D, 6D, 0D, 2D, 10D, 2D),
            box(14D, 6D, 0D, 16D, 10D, 2D)
    );

    public static final Map<Direction, VoxelShape> LEFT_FACING_SHAPES = Shapes.rotateHorizontal(LEFT_SHAPE);
    public static final Map<Direction, VoxelShape> RIGHT_FACING_SHAPES = Shapes.rotateHorizontal(RIGHT_SHAPE);
    public static final Map<Direction, VoxelShape> BOTH_FACING_SHAPES = Shapes.rotateHorizontal(BOTH_SHAPE);
    public static final Map<Direction, VoxelShape> CORNER_FACING_SHAPES = Shapes.rotateHorizontal(CORNER_SHAPE);
    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public NordicSofaBlock(Properties properties) {
        super(properties);
    }
}
