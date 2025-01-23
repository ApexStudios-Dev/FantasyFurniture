package dev.apexstudios.fantasyfurniture.block;

import dev.apexstudios.apexcore.lib.component.ComponentRegistrar;
import dev.apexstudios.apexcore.lib.component.block.BaseEntityBlockComponentHolder;
import dev.apexstudios.apexcore.lib.component.block.BlockComponent;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.component.block.types.FacingBlockComponent;
import dev.apexstudios.apexcore.lib.component.block.types.FluidLoggedBlockComponent;
import dev.apexstudios.apexcore.lib.multiblock.MultiBlock;
import dev.apexstudios.apexcore.lib.multiblock.MultiBlockType;
import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.entity.DeskBlockEntity;
import dev.apexstudios.fantasyfurniture.set.BlockType;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DeskBlock extends BaseEntityBlockComponentHolder {
    public static final MultiBlockType MULTI_BLOCK_TYPE = MultiBlockType.builder()
            .with(0, 0, 1)
            .rotatingFromComponent()
            .build();

    private final FurnitureSet furnitureSet;
    private final BlockType<?, ?> blockType;
    private final Map<Direction, VoxelShape> shapes;

    public DeskBlock(BlockBehaviour.Properties properties) {
        super(properties);

        var injector = (FurnitureBlock.Injector) properties;
        furnitureSet = injector.FantasyFurniture$getFurnitureSet();
        blockType = injector.FantasyFurniture$getBlockType();
        shapes = ApexShapes.rotateHorizontal(furnitureSet.shape(blockType, Shapes::block));
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        var facing = getComponentOrThrow(BlockComponentTypes.FACING).get(blockState);
        var multiBlockType = getComponentOrThrow(BlockComponentTypes.MULTI_BLOCK).getMultiBlockType();
        return MultiBlock.fixVoxelShape(shapes.get(facing), multiBlockType, blockState, pos);
    }

    @Override
    protected void registerComponents(ComponentRegistrar<BlockComponent> registrar) {
        FacingBlockComponent.registerHorizontal(registrar);
        FluidLoggedBlockComponent.registerWater(registrar);
        registrar.register(BlockComponentTypes.MULTI_BLOCK, builder -> builder.type(MULTI_BLOCK_TYPE));
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState blockState) {
        return new DeskBlockEntity(pos, blockState);
    }

    @Override
    protected boolean hasAnalogOutputSignal(BlockState blockState) {
        return true;
    }
}
