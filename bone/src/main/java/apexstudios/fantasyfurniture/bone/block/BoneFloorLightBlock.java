package apexstudios.fantasyfurniture.bone.block;

import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.FloorLightBlock;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class BoneFloorLightBlock extends FloorLightBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(6D, 0D, 6D, 10D, 2D, 10D),
            box(7D, 2D, 7D, 9D, 23D, 9D),
            box(2.5D, 21.75D, 6.5D, 5.5D, 23.75D, 9.5D),
            box(3.25D, 23.75D, 7.25D, 4.75D, 27.75D, 8.75D),
            box(7.25D, 24.75D, 7.25D, 8.75D, 28.75D, 8.75D),
            box(11.25D, 23.75D, 7.25D, 12.75D, 27.75D, 8.75D),
            box(10.5D, 21.75D, 6.5D, 13.5D, 23.75D, 9.5D),
            box(6.5D, 22.75D, 6.5D, 9.5D, 24.75D, 9.5D),
            box(3D, 17.75D, 7D, 7D, 21.75D, 9D),
            box(9D, 17.75D, 7D, 13D, 21.75D, 9D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = ApexShapes.rotateHorizontal(SHAPE);

    public BoneFloorLightBlock(Properties properties) {
        super(properties, 3);
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

        if(index == 0 || index == 1) {
            var offset = .25D;
            var even = index % 2 == 0;
            x = even ? x + offset : x - offset;
        } else {
            y += .1D;
        }

        level.addParticle(ParticleTypes.SMOKE, x, y, z, 0D, 0D, 0D);
        level.addParticle(ParticleTypes.FLAME, x, y, z, 0D, 0D, 0D);
    }
}
