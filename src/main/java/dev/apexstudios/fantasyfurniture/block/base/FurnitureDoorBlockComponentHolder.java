package dev.apexstudios.fantasyfurniture.block.base;

import dev.apexstudios.apexcore.lib.component.ComponentRegistrar;
import dev.apexstudios.apexcore.lib.component.block.BlockComponent;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentHelper;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.component.block.DoorBlockComponentHolder;
import dev.apexstudios.apexcore.lib.component.block.types.FluidLoggedBlockComponent;
import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.MustBeInvokedByOverriders;

public class FurnitureDoorBlockComponentHolder extends DoorBlockComponentHolder {
    public FurnitureDoorBlockComponentHolder(BlockSetType type, Properties properties) {
        super(type, properties);
    }

    public FurnitureDoorBlockComponentHolder(FurnitureSet furnitureSet, Properties properties) {
        this(furnitureSet.blockSet(), properties);
    }

    @MustBeInvokedByOverriders
    @Override
    protected void registerComponents(ComponentRegistrar<BlockComponent> registrar) {
        super.registerComponents(registrar);

        FluidLoggedBlockComponent.registerWater(registrar);

        registrar.register(BlockComponentTypes.MULTI_BLOCK, builder -> builder
                .with(0, 1, 0)
                .rotatingFromComponent()
        );
    }

    public static VoxelShape getShape(VoxelShape baseShape, BlockState blockState, BlockPos pos) {
        var facing = BlockComponentHelper.getComponentOrThrow(blockState, BlockComponentTypes.FACING).get(blockState);
        var open = blockState.getValue(OPEN);

        if(open && blockState.getValue(HINGE) == DoorHingeSide.RIGHT)
            facing = facing.getOpposite();

        var shape = ApexShapes.rotateHorizontal(baseShape, open ? facing : facing.getCounterClockWise());
        return FurnitureBlockComponentHolder.getShape(shape, blockState, pos);
    }

}
