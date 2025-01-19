package dev.apexstudios.fantasyfurniture.block;

import dev.apexstudios.apexcore.lib.component.ComponentRegistrar;
import dev.apexstudios.apexcore.lib.component.block.BaseBlockComponentHolder;
import dev.apexstudios.apexcore.lib.component.block.BlockComponent;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.component.block.types.FacingBlockComponent;
import dev.apexstudios.apexcore.lib.component.block.types.FluidLoggedBlockComponent;
import dev.apexstudios.apexcore.lib.multiblock.MultiBlock;
import dev.apexstudios.apexcore.lib.multiblock.MultiBlockType;
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

public final class BedDoubleBlock extends BaseBlockComponentHolder {
    public static final MultiBlockType MULTI_BLOCK_TYPE = MultiBlockType.builder()
            .with(1, 0, 0)
            .with(1, 0, 1)
            .with(0, 0, 1)
            .rotatingFromComponent()
            .build();

    private final FurnitureSet furnitureSet;
    private final Map<Direction, VoxelShape> shapes;

    public BedDoubleBlock(Properties properties) {
        super(properties);

        furnitureSet = ((FurnitureBlock.Injector) properties).FantasyFurniture$getFurnitureSet();
        shapes = ApexShapes.rotateHorizontal(furnitureSet.shape(BlockType.BED_DOUBLE, Shapes::block));
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
}
