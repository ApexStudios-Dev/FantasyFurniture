package dev.apexstudios.fantasyfurniture.block;

import dev.apexstudios.apexcore.lib.component.ComponentRegistrar;
import dev.apexstudios.apexcore.lib.component.block.BaseBlockComponentHolder;
import dev.apexstudios.apexcore.lib.component.block.BlockComponent;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.component.block.types.DoorBlockComponent;
import dev.apexstudios.apexcore.lib.component.block.types.FacingBlockComponent;
import dev.apexstudios.apexcore.lib.component.block.types.FluidLoggedBlockComponent;
import dev.apexstudios.apexcore.lib.multiblock.MultiBlock;
import dev.apexstudios.apexcore.lib.multiblock.MultiBlockType;
import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.set.BlockType;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class DoorBlock extends BaseBlockComponentHolder {
    public static final MultiBlockType MULTI_BLOCK_TYPE = MultiBlockType.builder()
            .with(0, 1, 0)
            .rotatingFromComponent()
            .build();

    private final FurnitureSet furnitureSet;
    private final BlockType<?, ?> blockType;
    private final Map<Direction, VoxelShape> openShapes;
    private final Map<Direction, VoxelShape> closedShapes;

    public DoorBlock(Properties properties) {
        super(properties);

        var injector = (FurnitureBlock.Injector) properties;
        furnitureSet = injector.FantasyFurniture$getFurnitureSet();
        blockType = injector.FantasyFurniture$getBlockType();
        openShapes = ApexShapes.rotateHorizontal(furnitureSet.shape(blockType, Shapes::block));
        closedShapes = openShapes.keySet().stream().collect(Collectors.toMap(Function.identity(), facing -> openShapes.get(facing.getCounterClockWise())));
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        var facing = getComponentOrThrow(BlockComponentTypes.FACING).get(blockState);
        var open = blockState.getValue(DoorBlockComponent.OPEN);

        if(open && blockState.getValue(DoorBlockComponent.HINGE) == DoorHingeSide.RIGHT)
            facing = facing.getOpposite();

        var multiBlockType = getComponentOrThrow(BlockComponentTypes.MULTI_BLOCK).getMultiBlockType();
        var shapes = open ? openShapes : closedShapes;
        return MultiBlock.fixVoxelShape(shapes.get(facing), multiBlockType, blockState, pos);
    }

    @Override
    protected void registerComponents(ComponentRegistrar<BlockComponent> registrar) {
        FacingBlockComponent.registerHorizontal(registrar, builder -> builder.facingForPlacement(UseOnContext::getHorizontalDirection));
        registrar.register(BlockComponentTypes.MULTI_BLOCK, builder -> builder.type(MULTI_BLOCK_TYPE));
        registrar.register(BlockComponentTypes.DOOR);
        FluidLoggedBlockComponent.registerWater(registrar);
    }
}
