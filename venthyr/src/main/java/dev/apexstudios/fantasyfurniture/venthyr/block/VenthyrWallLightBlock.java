package dev.apexstudios.fantasyfurniture.venthyr.block;

import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.WallLightBlock;
import dev.apexstudios.fantasyfurniture.venthyr.VenthyrFurnitureSet;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrWallLightBlock extends WallLightBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(6D, 1D, 15D, 10D, 3D, 16D),
            box(5D, 3D, 15D, 11D, 12D, 16D),
            box(6D, 12D, 15D, 10D, 14D, 16D),
            box(7D, 3.5D, 14D, 9D, 5.5D, 15D),
            box(4.25D, 2.5D, 10.5D, 11.75D, 11.5D, 14D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = ApexShapes.rotateHorizontal(SHAPE);

    public VenthyrWallLightBlock(Properties properties) {
        super(VenthyrFurnitureSet.FURNITURE_SET, properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(FACING_SHAPES, blockState, pos);
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource random) {
        var x = pos.getX() + .5D;
        var y = pos.getY() + .5D + .35D;
        var z = pos.getZ() + .5D;

        var facing = getComponentOrThrow(BlockComponentTypes.FACING).get(blockState).getOpposite();
        var face = facing.getClockWise();

        var xOffset = .15D * face.getStepX();
        var zOffset = .15D * face.getStepZ();

        x += .25D * facing.getStepX();
        z += .25D * facing.getStepZ();

        playParticles(level, x + xOffset, y, z + zOffset);
    }
}
