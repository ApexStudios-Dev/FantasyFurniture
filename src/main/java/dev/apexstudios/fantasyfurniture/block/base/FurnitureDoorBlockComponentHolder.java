package dev.apexstudios.fantasyfurniture.block.base;

import com.google.common.collect.Maps;
import dev.apexstudios.apexcore.lib.component.ComponentRegistrar;
import dev.apexstudios.apexcore.lib.component.block.BlockComponent;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.component.block.DoorBlockComponentHolder;
import dev.apexstudios.apexcore.lib.component.block.types.FluidLoggedBlockComponent;
import dev.apexstudios.apexcore.lib.component.block.types.MultiBlockComponent;
import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.set.BlockType;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.MustBeInvokedByOverriders;

public class FurnitureDoorBlockComponentHolder extends DoorBlockComponentHolder {
    protected final FurnitureSet furnitureSet;
    protected final BlockType<?, ?> blockType;
    private final Map<BlockState, VoxelShape> shapes = Maps.newHashMap();

    public FurnitureDoorBlockComponentHolder(FurnitureSet furnitureSet, BlockType<?, ?> blockType, Properties properties) {
        super(furnitureSet.blockSetType(), properties);

        this.furnitureSet = furnitureSet;
        this.blockType = blockType;
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        return shapes.computeIfAbsent(blockState, $ -> getShape($, pos));
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

    private VoxelShape getShape(BlockState blockState, BlockPos pos) {
        var facing = getComponentOrThrow(BlockComponentTypes.FACING).get(blockState);
        var open = blockState.getValue(OPEN);

        if(open && blockState.getValue(HINGE) == DoorHingeSide.RIGHT)
            facing = facing.getOpposite();

        var multiBlock = getComponentOrThrow(BlockComponentTypes.MULTI_BLOCK);
        var baseShape = furnitureSet.shape(blockType, blockState, Shapes::block);
        var shape = ApexShapes.rotateHorizontal(baseShape, open ? facing : facing.getCounterClockWise());
        return MultiBlockComponent.fixVoxelShape(shape, multiBlock, blockState, pos);
    }
}
