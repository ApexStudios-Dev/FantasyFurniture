package dev.apexstudios.fantasyfurniture.block;

import dev.apexstudios.apexcore.lib.component.ComponentRegistrar;
import dev.apexstudios.apexcore.lib.component.block.BaseBlockComponentHolder;
import dev.apexstudios.apexcore.lib.component.block.BlockComponent;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.component.block.types.FacingBlockComponent;
import dev.apexstudios.apexcore.lib.component.block.types.FluidLoggedBlockComponent;
import dev.apexstudios.apexcore.lib.component.block.types.MultiBlockComponent;
import dev.apexstudios.apexcore.lib.component.block.types.SeatBlockComponent;
import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.set.BlockType;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ChairBlock extends BaseBlockComponentHolder {
    protected final FurnitureSet furnitureSet;
    protected final BlockType<?, ?> blockType;
    private final Map<Direction, VoxelShape> shapes;

    public ChairBlock(Properties properties) {
        super(properties);

        var injector = (FurnitureBlock.Injector) properties;
        furnitureSet = injector.FantasyFurniture$getFurnitureSet();
        blockType = injector.FantasyFurniture$getBlockType();
        shapes = ApexShapes.rotateHorizontal(furnitureSet.shape(blockType, Shapes::block));
    }

    @Override
    protected void registerComponents(ComponentRegistrar<BlockComponent> registrar) {
        FacingBlockComponent.registerHorizontal(registrar);
        FluidLoggedBlockComponent.registerWater(registrar);
        registrar.register(SeatBlockComponent.COMPONENT_TYPE);

        registrar.register(BlockComponentTypes.MULTI_BLOCK, builder -> builder
                .with(0, 1, 0)
                .rotatingFromComponent()
        );
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        var facing = getComponentOrThrow(BlockComponentTypes.FACING).get(blockState);
        var multiBlock = getComponentOrThrow(BlockComponentTypes.MULTI_BLOCK);
        return MultiBlockComponent.fixVoxelShape(shapes.get(facing), multiBlock, blockState, pos);
    }
}
