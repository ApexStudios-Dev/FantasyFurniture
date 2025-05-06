package dev.apexstudios.fantasyfurniture.nordic.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.ShelfBlock;
import java.util.Map;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NordicShelfBlock extends ShelfBlock {
    public static final VoxelShape LEFT_SHAPE = ApexShapes.join(
            box(13.5D, 9D, 2D, 15.5D, 14D, 13D),
            box(0D, 14D, 0D, 16D, 16D, 16D),
            box(13D, 6D, 13D, 16D, 14D, 16D)
    );

    public static final VoxelShape RIGHT_SHAPE = ApexShapes.join(
            box(.5D, 9D, 2D, 2.5D, 14D, 13D),
            box(0D, 14D, 0D, 16D, 16D, 16D),
            box(0D, 6D, 13D, 3D, 14D, 16D)
    );

    public static final VoxelShape BOTH_SHAPE = box(0D, 14D, 0D, 16D, 16D, 16D);

    public static final VoxelShape SHAPE = ApexShapes.join(
            box(.5D, 9D, 2D, 2.5D, 14D, 13D),
            box(13.5D, 9D, 2D, 15.5D, 14D, 13D),
            box(0D, 14D, 0D, 16D, 16D, 16D),
            box(13D, 6D, 13D, 16D, 14D, 16D),
            box(0D, 6D, 13D, 3D, 14D, 16D)
    );

    public static final Map<Direction, VoxelShape> LEFT_FACING_SHAPES = Shapes.rotateHorizontal(LEFT_SHAPE);
    public static final Map<Direction, VoxelShape> RIGHT_FACING_SHAPES = Shapes.rotateHorizontal(RIGHT_SHAPE);
    public static final Map<Direction, VoxelShape> BOTH_FACING_SHAPES = Shapes.rotateHorizontal(BOTH_SHAPE);
    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public NordicShelfBlock(Properties properties) {
        super(properties);
    }
}
