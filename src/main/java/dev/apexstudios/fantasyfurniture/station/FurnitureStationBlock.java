package dev.apexstudios.fantasyfurniture.station;

import dev.apexstudios.apexcore.lib.component.ComponentRegistrar;
import dev.apexstudios.apexcore.lib.component.block.BaseBlockComponentHolder;
import dev.apexstudios.apexcore.lib.component.block.BlockComponent;
import dev.apexstudios.apexcore.lib.component.block.types.FacingBlockComponent;
import dev.apexstudios.apexcore.lib.component.block.types.FluidLoggedBlockComponent;
import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class FurnitureStationBlock extends BaseBlockComponentHolder {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(1D, 0D, 1D, 3D, 14D, 3D),
            box(1D, 0D, 13D, 3D, 14D, 15D),
            box(13D, 0D, 13D, 15D, 14D, 15D),
            box(13D, 0D, 1D, 15D, 14D, 3D),
            box(0D, 14D, 0D, 16D, 16D, 16D),
            box(2D, 2D, 2D, 14D, 14D, 14D)
    );

    FurnitureStationBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected void registerComponents(ComponentRegistrar<BlockComponent> registrar) {
        FluidLoggedBlockComponent.registerWater(registrar);
        FacingBlockComponent.registerHorizontal(registrar);
    }

    @Override
    protected MenuProvider getMenuProvider(BlockState blockState, Level level, BlockPos pos) {
        return new SimpleMenuProvider((windowId, inventory, player) -> new FurnitureStationMenu(windowId, inventory, ContainerLevelAccess.create(level, pos)), getName());
    }
}
