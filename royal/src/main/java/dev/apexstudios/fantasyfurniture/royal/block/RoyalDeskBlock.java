package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.DeskBlock;
import java.util.Map;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class RoyalDeskBlock extends DeskBlock {
    public static final VoxelShape SHAPE_LEFT = ApexShapes.join(
            box(-16D, 14D, 0D, 16D, 16D, 16D),
            box(13D, 11D, 1D, 15D, 14D, 4D),
            box(13D, 3D, 2D, 15D, 11D, 4D),
            box(13D, 0D, 1D, 15D, 3D, 4D),
            box(-15D, 0D, 1D, -13D, 3D, 4D),
            box(-15D, 11D, 1D, -13D, 14D, 4D),
            box(-15D, 3D, 2D, -13D, 11D, 4D),
            box(-15D, 0D, 12D, -13D, 3D, 15D),
            box(-15D, 11D, 12D, -13D, 14D, 15D),
            box(-15D, 3D, 12D, -13D, 11D, 14D),
            box(13D, 0D, 12D, 15D, 3D, 15D),
            box(13D, 11D, 12D, 15D, 14D, 15D),
            box(13D, 3D, 12D, 15D, 11D, 14D),
            box(4D, 10D, 2D, 12D, 14D, 14D),
            box(6D, 12D, 1D, 10D, 13D, 2D)
    );

    public static final VoxelShape SHAPE_RIGHT = ApexShapes.join(
            box(-16D, 14D, 0D, 16D, 16D, 16D),
            box(13D, 11D, 1D, 15D, 14D, 4D),
            box(13D, 3D, 2D, 15D, 11D, 4D),
            box(13D, 0D, 1D, 15D, 3D, 4D),
            box(-15D, 0D, 1D, -13D, 3D, 4D),
            box(-15D, 11D, 1D, -13D, 14D, 4D),
            box(-15D, 3D, 2D, -13D, 11D, 4D),
            box(-15D, 0D, 12D, -13D, 3D, 15D),
            box(-15D, 11D, 12D, -13D, 14D, 15D),
            box(-15D, 3D, 12D, -13D, 11D, 14D),
            box(13D, 0D, 12D, 15D, 3D, 15D),
            box(13D, 11D, 12D, 15D, 14D, 15D),
            box(13D, 3D, 12D, 15D, 11D, 14D),
            box(-12D, 10D, 2D, -4D, 14D, 14D),
            box(-10D, 12D, 1D, -6D, 13D, 2D)
    );

    public static final Map<Direction, VoxelShape> LEFT_FACING_SHAPES = Shapes.rotateHorizontal(SHAPE_LEFT);
    public static final Map<Direction, VoxelShape> RIGHT_FACING_SHAPES = Shapes.rotateHorizontal(SHAPE_RIGHT);

    public RoyalDeskBlock(Properties properties) {
        super(properties);
    }
}
