package dev.apexstudios.fantasyfurniture.block.base;

import dev.apexstudios.apexcore.lib.block.SeatBlock;
import dev.apexstudios.apexcore.lib.util.ApexShapes;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FurnitureSeatBlock extends FurnitureBaseBlock implements SeatBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(2D, 0D, 2D, 4D, 3D, 4D),
            box(12D, 0D, 12D, 14D, 3D, 14D),
            box(12D, 0D, 2D, 14D, 3D, 4D),
            box(2D, 0D, 12D, 4D, 3D, 14D),
            box(2D, 3D, 11.5D, 4D, 5D, 13.5D),
            box(12D, 3D, 11.5D, 14D, 5, 13.5D),
            box(12D, 3D, 2.5D, 14D, 5D, 4.5D),
            box(1.5D, 5D, 1.75D, 14.5D, 7D, 14.25D),
            box(2D, 3D, 2.5D, 4D, 5D, 4.5D),
            box(2.5D, 3.5D, 4.5D, 3.5D, 4.5D, 11.5D),
            box(12.5D, 3.5D, 4.5D, 13.5D, 4.5D, 11.5D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public FurnitureSeatBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(FACING_SHAPES, blockState, facingProperty(), pos);
    }
}
