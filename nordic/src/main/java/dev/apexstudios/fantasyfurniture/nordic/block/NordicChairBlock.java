package dev.apexstudios.fantasyfurniture.nordic.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.ChairBlock;
import java.util.Map;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NordicChairBlock extends ChairBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(2D, 0D, 2D, 4D, 4D, 4D),
            box(2.5D, 4.5D, 4.5D, 3.5D, 5.5D, 11.5D),
            box(12.5D, 4.5D, 4.5D, 13.5D, 5.5D, 11.5D),
            box(12D, 0D, 2D, 14D, 4D, 4D),
            box(2D, 0D, 12D, 4D, 4D, 14D),
            box(2D, 7D, 2D, 14D, 9D, 14D),
            box(2D, 9D, 13D, 14D, 25D, 14D),
            box(12D, 0D, 12D, 14D, 4D, 14D),
            box(2D, 4D, 11.5D, 4D, 7D, 13.5D),
            box(12D, 4D, 11.5D, 14D, 7D, 13.5D),
            box(2D, 4D, 2.5D, 4D, 7D, 4.5D),
            box(12D, 4D, 2.5D, 14D, 7D, 4.5D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public NordicChairBlock(Properties properties) {
        super(properties);
    }
}
