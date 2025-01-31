package dev.apexstudios.fantasyfurniture.block.base;

import com.google.common.collect.Maps;
import dev.apexstudios.apexcore.lib.component.ComponentRegistrar;
import dev.apexstudios.apexcore.lib.component.block.BaseEntityBlockComponentHolder;
import dev.apexstudios.apexcore.lib.component.block.BlockComponent;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.component.block.types.FluidLoggedBlockComponent;
import dev.apexstudios.apexcore.lib.component.block.types.MultiBlockComponent;
import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.set.BlockType;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.MustBeInvokedByOverriders;

public abstract class FurnitureEntityBlockComponentHolder extends BaseEntityBlockComponentHolder {
    protected final FurnitureSet furnitureSet;
    protected final BlockType<?, ?> blockType;
    private final Map<BlockState, VoxelShape> shapes = Maps.newHashMap();

    protected FurnitureEntityBlockComponentHolder(FurnitureSet furnitureSet, BlockType<?, ?> blockType, Properties properties) {
        super(properties);

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
    }

    private VoxelShape getShape(BlockState blockState, BlockPos pos) {
        var facing = getComponent(BlockComponentTypes.FACING);
        var multiBlock = getComponent(BlockComponentTypes.MULTI_BLOCK);
        var baseShape = furnitureSet.shape(blockType, blockState, Shapes::block);

        if(facing != null)
            baseShape = ApexShapes.rotateHorizontal(baseShape, facing.get(blockState));

        return multiBlock == null ? baseShape : MultiBlockComponent.fixVoxelShape(baseShape, multiBlock, blockState, pos);
    }
}
