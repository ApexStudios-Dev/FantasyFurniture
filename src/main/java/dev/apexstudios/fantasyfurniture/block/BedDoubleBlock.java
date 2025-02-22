package dev.apexstudios.fantasyfurniture.block;

import dev.apexstudios.apexcore.lib.component.ComponentRegistrar;
import dev.apexstudios.apexcore.lib.component.block.BlockComponent;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.component.block.types.FacingBlockComponent;
import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureBlockComponentHolder;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BedDoubleBlock extends FurnitureBlockComponentHolder {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(-16D, 3D, 2D, 16D, 5D, 30D),
            box(-14D, 5D, 2D, 14D, 8D, 30D),
            box(-16D, 3D, 0D, 16D, 5D, 2D),
            box(-16D, 0D, 0D, -14D, 8D, 2D),
            box(14D, 0D, 0D, 16D, 8D, 2D),
            box(-16D, 12D, 0D, -8D, 14D, 2D),
            box(8D, 12D, 0D, 16D, 14D, 2D),
            box(-10D, 12D, 0D, 10D, 16D, 2D),
            box(-15D, 5D, 0D, 15D, 12D, 2D),
            box(-15D, 5D, 30D, 15D, 12D, 32D),
            box(-16D, 3D, 30D, 16D, 5D, 32D),
            box(-16D, 0D, 30D, -14D, 8D, 32D),
            box(14D, 0D, 30D, 16D, 8D, 32D),
            box(-16D, 12D, 30D, -8D, 14D, 32D),
            box(8D, 12D, 30D, 16D, 14D, 32D),
            box(-10D, 12D, 30D, 10D, 16D, 32D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = ApexShapes.rotateHorizontal(SHAPE);

    public BedDoubleBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(FACING_SHAPES, blockState, pos);
    }

    @Override
    protected void registerComponents(ComponentRegistrar<BlockComponent> registrar) {
        super.registerComponents(registrar);

        FacingBlockComponent.registerHorizontal(registrar);

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

        registrar.register(BlockComponentTypes.BOUNCE);
    }
}
