package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.FloorLightBlock;
import dev.apexstudios.fantasyfurniture.royal.RoyalFurnitureSet;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class RoyalFloorLightBlock extends FloorLightBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(6D, 0D, 6D, 10D, 2D, 10D),
            box(7D, 2D, 7D, 9D, 30D, 9D),
            box(3D, 20D, 7D, 13D, 22D, 9D),
            box(11D, 22D, 7D, 13D, 29D, 9D),
            box(3D, 22D, 7D, 5D, 29D, 9D),
            box(2.5D, 24D, 6.5D, 5.5D, 25D, 9.5D),
            box(6.5D, 25D, 6.5D, 9.5D, 26D, 9.5D),
            box(10.5D, 24D, 6.5D, 13.5D, 25D, 9.5D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public RoyalFloorLightBlock(Properties properties) {
        super(RoyalFurnitureSet.FURNITURE_SET, properties, 3);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(FACING_SHAPES, blockState, facingProperty(), pos);
    }

    @Override
    protected void addParticle(Level level, BlockPos pos, int index) {
        var x = pos.getX() + .5D;
        var y = pos.getY() + 1D;
        var z = pos.getZ() + .5D;

        if(index == 0 || index == 1) {
            var offset = .25D;
            var even = index % 2 == 0;
            x = even ? x + offset : x - offset;
        } else {
            y += .1D;
        }

        playParticles(level, x, y, z);
    }
}
