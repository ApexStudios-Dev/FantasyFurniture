package dev.apexstudios.fantasyfurniture.royal.block;

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

public final class RoyalBedDoubleBlock extends BedDoubleBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(-16D, 0D, 0D, -13D, 3D, 2D),
            box(-15D, 3D, 0D, -13D, 9D, 2D),
            box(-16D, 9D, 0D, -13D, 12D, 2D),
            box(-16D, 9D, 30D, -13D, 12D, 32D),
            box(-16D, 0D, 30D, -13D, 3D, 32D),
            box(-15D, 3D, 30D, -13D, 9D, 32D),
            box(13D, 9D, 30D, 16D, 12D, 32D),
            box(13D, 0D, 30D, 16D, 3D, 32D),
            box(13D, 3D, 30D, 15D, 9D, 32D),
            box(13D, 9D, 0D, 16D, 12D, 2D),
            box(13D, 0D, 0D, 16D, 3D, 2D),
            box(13D, 3D, 0D, 15D, 9D, 2D),
            box(-15D, 3D, 2D, 15D, 5D, 32D),
            box(-13D, 2D, 30D, -12D, 3D, 32D),
            box(12D, 2D, 30D, 13D, 3D, 32D),
            box(12D, 2D, 0D, 13D, 3D, 2D),
            box(-13D, 2D, 0D, -12D, 3D, 2D),
            box(-14D, 3D, 0D, 14D, 15D, 2D),
            box(-14D, 3D, 30D, 14D, 15D, 32D),
            box(-14D, 3D, 2D, 14D, 8D, 30D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public RoyalBedDoubleBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        return FurnitureBaseBlock.getShape(FACING_SHAPES, blockState, FACING, pos);
    }
}
