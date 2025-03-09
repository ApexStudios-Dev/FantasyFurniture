package dev.apexstudios.fantasyfurniture.necrolord.block;

import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.WallLightBlock;
import dev.apexstudios.fantasyfurniture.necrolord.NecrolordFurnitureSet;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NecrolordWallLightBlock extends WallLightBlock {
    public static final VoxelShape SHAPE = box(6D, 4D, 8D, 10D, 14.5D, 16D);
    public static final Map<Direction, VoxelShape> FACING_SHAPES = ApexShapes.rotateHorizontal(SHAPE);

    public NecrolordWallLightBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource random) {
        var x = pos.getX() + .5D;
        var y = pos.getY() + .7D;
        var z = pos.getZ() + .5D;

        var facing = getComponentOrThrow(BlockComponentTypes.FACING).get(blockState).getOpposite();
        var offset = .1D;
        var offsetZ = offset * facing.getStepZ();
        var offsetX = offset * facing.getStepX();

        level.addParticle(ParticleTypes.SMOKE, x + offsetX, y + .35D, z + offsetZ, 0D, 0D, 0D);
        level.addParticle(NecrolordFurnitureSet.FLAME_PARTICLE.value(), x + offsetX, y + .35D, z + offsetZ, 0D, 0D, 0D);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(FACING_SHAPES, blockState, pos);
    }
}
