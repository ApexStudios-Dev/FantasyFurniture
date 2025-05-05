package dev.apexstudios.fantasyfurniture.necrolord.block;

import dev.apexstudios.fantasyfurniture.block.ChandelierBlock;
import dev.apexstudios.fantasyfurniture.necrolord.NecrolordFurnitureSet;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NecrolordChandelierBlock extends ChandelierBlock {
    public static final VoxelShape SHAPE = box(3D, 0D, 3D, 13D, 16D, 13D);
    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public NecrolordChandelierBlock(Properties properties) {
        super(NecrolordFurnitureSet.FURNITURE_SET, properties, 1);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(FACING_SHAPES, blockState, facingProperty(), pos);
    }

    @Override
    protected void addParticle(Level level, BlockPos pos, int index) {
        playParticles(level, pos.getX() + .5D, pos.getY() + .75D, pos.getZ() + .5D);
    }
}
