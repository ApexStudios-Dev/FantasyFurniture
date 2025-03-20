package dev.apexstudios.fantasyfurniture.block;

import dev.apexstudios.apexcore.lib.component.ComponentRegistrar;
import dev.apexstudios.apexcore.lib.component.block.BlockComponent;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.component.block.types.FacingBlockComponent;
import dev.apexstudios.apexcore.lib.component.block.types.MultiBlockComponent;
import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureBlockComponentHolder;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FloorLightBlock extends FurnitureBlockComponentHolder {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(6D, 0D, 6D, 10D, 2D, 10D),
            box(7D, 2D, 7D, 9D, 20D, 9D),
            box(6.5D, 20.75D, 2.5D, 9.5D, 22.75D, 5.5D),
            box(2.5D, 20.75D, 6.5D, 5.5D, 22.75D, 9.5D),
            box(7.25D, 22.75D, 3.25D, 8.75D, 26.75D, 4.75D),
            box(3.25D, 22.75D, 7.25D, 4.75D, 26.75D, 8.75D),
            box(7.25D, 22.75D, 11.25D, 8.75D, 26.75D, 12.75D),
            box(11.25D, 22.75D, 7.25D, 12.75D, 26.75D, 8.75D),
            box(10.5D, 20.75D, 6.5D, 13.5D, 22.75D, 9.5D),
            box(6.5D, 20.75D, 10.5D, 9.5D, 22.75D, 13.5D),
            box(3D, 16.75D, 7D, 7D, 20.75, 9D),
            box(9D, 16.75D, 7D, 13D, 20.75, 9D),
            box(7D, 16.75D, 3D, 9D, 20.75, 7D),
            box(7D, 16.75D, 9D, 9D, 20.75, 13D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = ApexShapes.rotateHorizontal(SHAPE);

    private final FurnitureSet furnitureSet;
    private final int particleCount;

    public FloorLightBlock(FurnitureSet furnitureSet, Properties properties, int particleCount) {
        super(properties);

        this.furnitureSet = furnitureSet;
        this.particleCount = particleCount;
    }

    public FloorLightBlock(FurnitureSet furnitureSet, Properties properties) {
        this(furnitureSet, properties, 4);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(FACING_SHAPES, blockState, pos);
    }

    @Override
    protected void registerComponents(ComponentRegistrar<BlockComponent> registrar) {
        super.registerComponents(registrar);

        FacingBlockComponent.registerHorizontal(registrar);

        registrar.register(BlockComponentTypes.MULTI_BLOCK, builder -> builder
                .with(0, 1, 0)
                .rotatingFromComponent()
        );
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource random) {
        if(getComponentOrThrow(BlockComponentTypes.MULTI_BLOCK).indexOf(blockState) == MultiBlockComponent.ORIGIN_INDEX)
            return;

        for(var i = 0; i < particleCount; i++) {
            addParticle(level, pos, i);
        }
    }

    protected void addParticle(Level level, BlockPos pos, int index) {
        var x = pos.getX() + .5D;
        var y = pos.getY() + .85D;
        var z = pos.getZ() + .5D;
        var offset = .25D;
        var even = index % 2 == 0;

        if(index < 2)
            x = even ? x + offset : x - offset;
        else
            z = even ? z + offset : z - offset;

        playParticles(level, x, y, z);
    }

    protected void playParticles(Level level, double x, double y, double z) {
        level.addParticle(ParticleTypes.SMOKE, x, y, z, 0D, 0D, 0D);
        level.addParticle(furnitureSet.flameParticle(), x, y, z, 0D, 0D, 0D);
    }
}
