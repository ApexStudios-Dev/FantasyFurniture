package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.fantasyfurniture.block.ChandelierBlock;
import dev.apexstudios.fantasyfurniture.royal.RoyalFurnitureSet;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class RoyalChandelierBlock extends ChandelierBlock {
    public static final VoxelShape SHAPE = box(0D, 0D, 0D, 16D, 16D, 16D);
    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public RoyalChandelierBlock(Properties properties) {
        super(RoyalFurnitureSet.FURNITURE_SET, properties, 4);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(FACING_SHAPES, blockState, facingProperty(), pos);
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
