package dev.apexstudios.fantasyfurniture.necrolord.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.FloorLightBlock;
import dev.apexstudios.fantasyfurniture.necrolord.NecrolordFurnitureSet;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NecrolordFloorLightBlock extends FloorLightBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(5D, 0D, 5D, 11D, 2D, 11D),
            box(6.5D, 2D, 6.5D, 9.5D, 4D, 9.5D),
            box(6.5D, 10D, 6.5D, 9.5D, 12D, 9.5D),
            box(7D, 4D, 7D, 9D, 18D, 9D),
            box(1.25D, 18D, 6.5D, 14.75D, 25D, 9.5D),
            box(12.25D, 25D, 7D, 14.25D, 28D, 9D),
            box(1.75D, 25D, 7D, 3.75D, 28D, 9D),
            box(7D, 25D, 7D, 9D, 29D, 9D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public NecrolordFloorLightBlock(Properties properties) {
        super(NecrolordFurnitureSet.FURNITURE_SET, properties, 3);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(FACING_SHAPES, blockState, facingProperty(), pos);
    }

    @Override
    protected void addParticle(Level level, BlockPos pos, int index) {
        var x = pos.getX() + .5D;
        var y = pos.getY() + .95D;
        var z = pos.getZ() + .5D;

        if(index == 0 || index == 1) {
            var offset = .3D;
            var even = index % 2 == 0;
            x = even ? x + offset : x - offset;
        } else {
            y += .1D;
        }

        playParticles(level, x, y, z);
    }
}
