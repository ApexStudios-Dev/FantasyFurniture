package dev.apexstudios.fantasyfurniture.block.base;

import dev.apexstudios.apexcore.lib.component.ComponentRegistrar;
import dev.apexstudios.apexcore.lib.component.block.BaseBlockComponentHolder;
import dev.apexstudios.apexcore.lib.component.block.BlockComponent;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentHelper;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.component.block.types.FluidLoggedBlockComponent;
import dev.apexstudios.apexcore.lib.component.block.types.MultiBlockComponent;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.MustBeInvokedByOverriders;

public class FurnitureBlockComponentHolder extends BaseBlockComponentHolder {
    protected FurnitureBlockComponentHolder(Properties properties) {
        super(properties);
    }

    @MustBeInvokedByOverriders
    @Override
    protected void registerComponents(ComponentRegistrar<BlockComponent> registrar) {
        super.registerComponents(registrar);

        FluidLoggedBlockComponent.registerWater(registrar);
    }

    public static VoxelShape getShape(Map<Direction, VoxelShape> shapes, BlockState blockState, BlockPos pos) {
        var facing = BlockComponentHelper.getComponentOrThrow(blockState, BlockComponentTypes.FACING).get(blockState);
        return getShape(shapes.get(facing), blockState, pos);
    }

    public static VoxelShape getShape(VoxelShape shape, BlockState blockState, BlockPos pos) {
        var multiBlock = BlockComponentHelper.getComponent(blockState, BlockComponentTypes.MULTI_BLOCK);

        if(multiBlock == null)
            return shape;

        return MultiBlockComponent.fixVoxelShape(shape, multiBlock, blockState, pos);
    }
}
