package dev.apexstudios.fantasyfurniture.dunmer.block;

import dev.apexstudios.fantasyfurniture.block.ChandelierBlock;
import dev.apexstudios.fantasyfurniture.dunmer.DunmerFurnitureSet;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class DunmerChandelierBlock extends ChandelierBlock {
    public static final VoxelShape SHAPE = box(1D, 2D, 1D, 15D, 16D, 15D);
    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public DunmerChandelierBlock(Properties properties) {
        super(DunmerFurnitureSet.FURNITURE_SET, properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(FACING_SHAPES, blockState, pos);
    }

    @Override
    protected void addParticle(Level level, BlockPos pos, int index) {
        var x = pos.getX() + .5D;
        var y = pos.getY() + .85D;
        var z = pos.getZ() + .5D;
        var offset = .3D;
        var even = index % 2 == 0;

        if(index < 2) {
            x = even ? x + offset : x - offset;
            z = !even ? z + offset : z - offset;
        } else {
            x = even ? x + offset : x - offset;
            z = even ? z + offset : z - offset;
        }

        playParticles(level, x, y, z);
    }
}
