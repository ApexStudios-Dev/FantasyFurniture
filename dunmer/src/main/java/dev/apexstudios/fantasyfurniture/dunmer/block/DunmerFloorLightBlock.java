package dev.apexstudios.fantasyfurniture.dunmer.block;

import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.FloorLightBlock;
import dev.apexstudios.fantasyfurniture.dunmer.DunmerFurnitureSet;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class DunmerFloorLightBlock extends FloorLightBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(6D, 0D, 6D, 10D, 2D, 10D),
            box(7D, 2D, 7D, 9D, 22D, 9D),
            box(6D, 22D, 6D, 10D, 27D, 10D),
            box(7D, 27D, 7D, 9D, 28D, 9D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = ApexShapes.rotateHorizontal(SHAPE);

    public DunmerFloorLightBlock(Properties properties) {
        super(DunmerFurnitureSet.FURNITURE_SET, properties, 1);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(FACING_SHAPES, blockState, pos);
    }

    @Override
    protected void addParticle(Level level, BlockPos pos, int index) {
        playParticles(level, pos.getX() + .5D, pos.getY() + .95D, pos.getZ() + .5D);
    }
}
