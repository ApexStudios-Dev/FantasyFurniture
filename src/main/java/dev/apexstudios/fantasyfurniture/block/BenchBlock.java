package dev.apexstudios.fantasyfurniture.block;

import dev.apexstudios.apexcore.lib.component.ComponentRegistrar;
import dev.apexstudios.apexcore.lib.component.block.BlockComponent;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.base.SeatBlock;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BenchBlock extends SeatBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(12D, 0D, 2D, 14D, 3D, 4D),
            box(-14D, 0D, 2D, -12D, 3D, 4D),
            box(-14D, 0D, 12D, -12D, 3D, 14D),
            box(12D, 0D, 12D, 14D, 3D, 14D),
            box(12D, 3D, 11.5D, 14D, 5D, 13.5D),
            box(12D, 3D, 2.5D, 14D, 5D, 4.5D),
            box(-14D, 3D, 2.5D, -12D, 5D, 4.5D),
            box(-14D, 3D, 11.5D, -12D, 5D, 13.5D),
            box(-13.5D, 3.5D, 4.5D, -12.5D, 4.5D, 11.5D),
            box(12.5D, 3.5D, 4.5D, 13.5D, 4.5D, 11.5D),
            box(-15D, 5D, 2D, 15D, 7D, 14D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = ApexShapes.rotateHorizontal(SHAPE);

    public BenchBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(FACING_SHAPES, blockState, pos);
    }

    @Override
    protected void registerComponents(ComponentRegistrar<BlockComponent> registrar) {
        super.registerComponents(registrar);

        registrar.register(BlockComponentTypes.MULTI_BLOCK, builder -> builder
                .with(0, 0, 1)
                .rotatingFromComponent()
        );
    }
}
