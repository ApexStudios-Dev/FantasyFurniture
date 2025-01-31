package dev.apexstudios.fantasyfurniture.block;

import dev.apexstudios.apexcore.lib.component.ComponentRegistrar;
import dev.apexstudios.apexcore.lib.component.block.BlockComponent;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.component.block.types.FacingBlockComponent;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureBlockComponentHolder;
import dev.apexstudios.fantasyfurniture.set.BlockType;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public final class WallLightBlock extends FurnitureBlockComponentHolder {
    public WallLightBlock(FurnitureSet furnitureSet, BlockType<?, ?> blockType, Properties properties) {
        super(furnitureSet, blockType, properties);
    }

    @Override
    protected void registerComponents(ComponentRegistrar<BlockComponent> registrar) {
        super.registerComponents(registrar);

        FacingBlockComponent.registerHorizontal(registrar);
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
        level.addParticle(ParticleTypes.FLAME, x + offsetX, y + .35D, z + offsetZ, 0D, 0D, 0D);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        var blockState = super.getStateForPlacement(context);

        if(blockState == null)
            return null;

        var facingComponent = getComponentOrThrow(BlockComponentTypes.FACING);

        for(var facing : context.getNearestLookingDirections()) {
            if(facing.getAxis().isHorizontal()) {
                blockState = facingComponent.set(blockState, facing.getOpposite());
                return blockState;
            }
        }

        return null;
    }
}
