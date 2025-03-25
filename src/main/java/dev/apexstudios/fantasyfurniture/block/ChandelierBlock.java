package dev.apexstudios.fantasyfurniture.block;

import dev.apexstudios.apexcore.lib.component.ComponentRegistrar;
import dev.apexstudios.apexcore.lib.component.block.BlockComponent;
import dev.apexstudios.apexcore.lib.component.block.types.FacingBlockComponent;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureBlockComponentHolder;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ChandelierBlock extends FurnitureBlockComponentHolder {
    public static final VoxelShape SHAPE = box(1D, 0D, 1D, 15, 16D, 15D);
    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    private final FurnitureSet furnitureSet;
    private final int particleCount;

    public ChandelierBlock(FurnitureSet furnitureSet, Properties properties, int particleCount) {
        super(properties);

        this.furnitureSet = furnitureSet;
        this.particleCount = particleCount;
    }

    public ChandelierBlock(FurnitureSet furnitureSet, Properties properties) {
        this(furnitureSet, properties, 4);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(FACING_SHAPES, blockState, pos);
    }

    @Override
    protected void registerComponents(ComponentRegistrar<BlockComponent, Block> registrar) {
        super.registerComponents(registrar);

        FacingBlockComponent.registerHorizontal(registrar);
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource random) {
        for(var i = 0; i < particleCount; i++) {
            addParticle(level, pos, i);
        }
    }

    protected void addParticle(Level level, BlockPos pos, int index) {
        var x = pos.getX() + .5D;
        var y = pos.getY() + .65D;
        var z = pos.getZ() + .5D;
        var offset = .25D;
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

    protected void playParticles(Level level, double x, double y, double z) {
        level.addParticle(ParticleTypes.SMOKE, x, y, z, 0D, 0D, 0D);
        level.addParticle(furnitureSet.flameParticle(), x, y, z, 0D, 0D, 0D);
    }
}
