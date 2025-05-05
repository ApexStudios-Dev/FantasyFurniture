package dev.apexstudios.fantasyfurniture.necrolord.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.BedDoubleBlock;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureBaseBlock;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NecrolordBedDoubleBlock extends BedDoubleBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(-16D, 0D, 0D, -12D, 2D, 4D),
            box(-16D, 0D, 28D, -12D, 2D, 32D),
            box(12D, 0D, 28D, 16D, 2D, 32D),
            box(12D, 0D, 0D, 16D, 2D, 4D),
            box(12D, 13D, 0D, 16D, 15D, 4D),
            box(-16D, 13D, 0D, -12D, 15D, 4D),
            box(-16D, 12D, 28D, -12D, 14D, 32D),
            box(12D, 12D, 28D, 16D, 14D, 32D),
            box(13D, 2D, 29D, 15D, 12D, 31D),
            box(-15D, 2D, 29D, -13D, 12D, 31D),
            box(-15D, 2D, 1D, -13D, 13D, 3D),
            box(13D, 2D, 1D, 15D, 13D, 3D),
            box(-13D, 3D, 1D, 13D, 12D, 3D),
            box(-13D, 3D, 29D, 13D, 11D, 31D),
            box(-15D, 3D, 3D, 15D, 8D, 29D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public NecrolordBedDoubleBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        return FurnitureBaseBlock.getShape(FACING_SHAPES, blockState, FACING, pos);
    }
}
