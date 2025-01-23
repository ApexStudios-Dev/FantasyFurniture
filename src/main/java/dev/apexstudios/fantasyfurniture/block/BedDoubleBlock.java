package dev.apexstudios.fantasyfurniture.block;

import dev.apexstudios.apexcore.lib.component.ComponentRegistrar;
import dev.apexstudios.apexcore.lib.component.block.BaseBlockComponentHolder;
import dev.apexstudios.apexcore.lib.component.block.BlockComponent;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.component.block.types.FacingBlockComponent;
import dev.apexstudios.apexcore.lib.component.block.types.FluidLoggedBlockComponent;
import dev.apexstudios.apexcore.lib.component.block.types.MultiBlockComponent;
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
        var multiBlock = getComponentOrThrow(BlockComponentTypes.MULTI_BLOCK);
        return MultiBlockComponent.fixVoxelShape(shapes.get(facing), multiBlock, blockState, pos);
    }

    @Override
    protected void registerComponents(ComponentRegistrar<BlockComponent> registrar) {
        FacingBlockComponent.registerHorizontal(registrar);
        FluidLoggedBlockComponent.registerWater(registrar);

        registrar.register(BlockComponentTypes.MULTI_BLOCK, builder -> builder
                .with(1, 0, 0)
                .with(1, 0, 1)
                .with(0, 0, 1)
                .rotatingFromComponent()
        );

        registrar.register(BlockComponentTypes.BED, builder -> builder
                .indices(1, 0)
                .indices(2, 3)
        );
    }
}
