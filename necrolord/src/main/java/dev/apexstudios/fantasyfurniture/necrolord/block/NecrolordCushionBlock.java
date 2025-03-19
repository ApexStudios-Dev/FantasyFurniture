package dev.apexstudios.fantasyfurniture.necrolord.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.CushionBlock;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureBlockComponentHolder;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NecrolordCushionBlock extends CushionBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(2D, 0D, 2D, 5D, 2D, 5D),
            box(2D, 0D, 11D, 5D, 2D, 14D),
            box(11D, 0D, 11D, 14D, 2D, 14D),
            box(11D, 0D, 2D, 14D, 2D, 5D),
            box(11D, 2D, 3D, 13D, 3D, 5D),
            box(3D, 2D, 3D, 5D, 3D, 5D),
            box(3D, 2D, 11D, 5D, 3D, 13D),
            box(11D, 2D, 11D, 13D, 3D, 13D),
            box(2D, 3D, 2D, 14D, 4D, 14D),
            box(2.5D, 4D, 2.5D, 13.5D, 7D, 13.5D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public NecrolordCushionBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return FurnitureBlockComponentHolder.getShape(FACING_SHAPES, blockState, pos);
    }
}
