package dev.apexstudios.fantasyfurniture.dunmer.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.BedSingleBlock;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureBaseBlock;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class DunmerBedSingleBlock extends BedSingleBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(0D, 0D, 0D, 2D, 11D, 2D),
            box(2D, 3D, 0D, 14D, 11D, 2D),
            box(1D, 3D, 2D, 15D, 8D, 24D),
            box(2D, 3D, 24D, 14D, 7D, 30D),
            box(0D, 3D, 2D, 16D, 5D, 30D),
            box(14D, 0D, 0D, 16D, 11D, 2D),
            box(0D, 0D, 30D, 2D, 13D, 32D),
            box(2D, 3D, 30D, 14D, 13D, 32D),
            box(14D, 0D, 30D, 16D, 13D, 32D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public DunmerBedSingleBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        return FurnitureBaseBlock.getShape(FACING_SHAPES, blockState, FACING, pos);
    }
}
