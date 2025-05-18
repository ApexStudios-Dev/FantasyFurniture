package dev.apexstudios.fantasyfurniture.block;

import dev.apexstudios.fantasyfurniture.util.FurnitureUtil;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WallLightBlock extends WallTorchBlock {
    private final Map<Direction, VoxelShape> shapes;

    public WallLightBlock(SimpleParticleType particleType, Properties properties, VoxelShape baseShape) {
        super(particleType, properties);

        shapes = Shapes.rotateHorizontal(baseShape);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        return FurnitureUtil.getShape(shapes, blockState, pos);
    }
}
