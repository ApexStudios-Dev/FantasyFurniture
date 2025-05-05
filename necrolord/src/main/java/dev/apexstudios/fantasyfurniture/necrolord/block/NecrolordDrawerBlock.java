package dev.apexstudios.fantasyfurniture.necrolord.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.DrawerBlock;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NecrolordDrawerBlock extends DrawerBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(0D, 0D, 0D, 3D, 2D, 3D),
            box(0D, 14D, 0D, 3D, 16D, 3D),
            box(0D, 14D, 13D, 3D, 16D, 16D),
            box(0D, 0D, 13D, 3D, 2D, 16D),
            box(13D, 0D, 13D, 16D, 2D, 16D),
            box(13D, 14D, 13D, 16D, 16D, 16D),
            box(13D, 14D, 0D, 16D, 16D, 3D),
            box(13D, 0D, 0D, 16D, 2D, 3D),
            box(1D, 0D, 1D, 15D, 16D, 15D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public NecrolordDrawerBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(FACING_SHAPES, blockState, facingProperty(), pos);
    }
}
