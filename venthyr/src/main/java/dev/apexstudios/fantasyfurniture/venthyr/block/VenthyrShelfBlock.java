package dev.apexstudios.fantasyfurniture.venthyr.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.ShelfBlock;
import java.util.Map;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrShelfBlock extends ShelfBlock {
    public static final VoxelShape LEFT_SHAPE = ApexShapes.join(
            box(0D, 13D, 0D, 16D, 16D, 16D),
            box(13D, 3D, 12D, 16D, 6D, 16D),
            box(13D, 9D, 10D, 16D, 13D, 16D),
            box(13D, 11D, 3D, 16D, 13D, 10D),
            box(13D, 10D, 0D, 16D, 13D, 3D),
            box(13D, 6D, 13D, 16D, 9D, 16D)
    );

    public static final VoxelShape RIGHT_SHAPE = ApexShapes.join(
            box(0D, 3D, 12D, 3D, 6D, 16D),
            box(0D, 6D, 13D, 3D, 9D, 16D),
            box(0D, 9D, 10D, 3D, 13D, 16D),
            box(0D, 11D, 3D, 3D, 13D, 10D),
            box(0D, 10D, 0D, 3D, 13D, 3D),
            box(0D, 13D, 0D, 16D, 16D, 16D)
    );

    public static final VoxelShape BOTH_SHAPE = box(0D, 13D, 0D, 16D, 16D, 16D);

    public static final VoxelShape SHAPE = ApexShapes.join(
            box(0D, 13D, 0D, 16D, 16D, 16D),
            box(13D, 9D, 10D, 16D, 13D, 16D),
            box(13D, 11D, 3D, 16D, 13D, 10D),
            box(13D, 10D, 0D, 16D, 13D, 3D),
            box(13D, 6D, 13D, 16D, 9D, 16D),
            box(13D, 3D, 12D, 16D, 6D, 16D),
            box(0D, 3D, 12D, 3D, 6D, 16D),
            box(0D, 9D, 10D, 3D, 13D, 16D),
            box(0D, 11D, 3D, 3D, 13D, 10D),
            box(0D, 10D, 0D, 3D, 13D, 3D),
            box(0D, 6D, 13D, 3D, 9D, 16D)
    );

    public static final Map<Direction, VoxelShape> LEFT_FACING_SHAPES = Shapes.rotateHorizontal(LEFT_SHAPE);
    public static final Map<Direction, VoxelShape> RIGHT_FACING_SHAPES = Shapes.rotateHorizontal(RIGHT_SHAPE);
    public static final Map<Direction, VoxelShape> BOTH_FACING_SHAPES = Shapes.rotateHorizontal(BOTH_SHAPE);
    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public VenthyrShelfBlock(Properties properties) {
        super(properties);
    }
}
