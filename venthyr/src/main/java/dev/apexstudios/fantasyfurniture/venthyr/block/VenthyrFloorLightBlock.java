package dev.apexstudios.fantasyfurniture.venthyr.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.FloorLightBlock;
import dev.apexstudios.fantasyfurniture.venthyr.VenthyrFurnitureSet;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrFloorLightBlock extends FloorLightBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(6D, 0D, 6D, 10D, 2D, 10D),
            box(7D, 2D, 7D, 9D, 20D, 9D),
            box(4D, 17.75D, 7D, 7D, 21.75D, 9D),
            box(9D, 17.75D, 7D, 12D, 21.75D, 9D),
            box(7D, 17.75D, 4D, 9D, 21.75D, 7D),
            box(7D, 17.75D, 9D, 9D, 21.75D, 12D),
            box(2.5D, 20.75D, 2.5D, 13.5D, 24D, 13.5D),
            box(10.25D, 24D, 10.25D, 12.5D, 28.75D, 12.5D),
            box(3.5D, 24D, 10.25D, 5.75D, 28.75D, 12.5D),
            box(3.5D, 24D, 3.5D, 5.75D, 28.75D, 5.75D),
            box(10.25D, 24D, 3.5D, 12.5D, 28.75D, 5.75D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public VenthyrFloorLightBlock(Properties properties) {
        super(VenthyrFurnitureSet.FURNITURE_SET, properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(FACING_SHAPES, blockState, pos);
    }

    @Override
    protected void addParticle(Level level, BlockPos pos, int index) {
        var x = pos.getX() + .5D;
        var y = pos.getY() + .95D;
        var z = pos.getZ() + .5D;
        var offset = .2D;
        var even = index % 2 == 0;

        x = even ? x + offset : x - offset;
        z = index < 2 ? z + offset : z - offset;

        playParticles(level, x, y, z);
    }
}
