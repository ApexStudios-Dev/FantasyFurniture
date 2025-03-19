package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.apexcore.lib.component.ComponentRegistrar;
import dev.apexstudios.apexcore.lib.component.block.BlockComponent;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.WardrobeBlock;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureBlockComponentHolder;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class RoyalWardrobeBlock extends WardrobeBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(12D, 0D, 2D, 14D, 3D, 5D),
            box(-14D, 0D, 2D, -12D, 3D, 5D),
            box(-14D, 0D, 11D, -12D, 3D, 14D),
            box(12D, 0D, 11D, 14D, 3D, 14D),
            box(12D, 3D, 11D, 14D, 4D, 13D),
            box(12D, 3D, 3D, 14D, 4D, 5D),
            box(-14D, 3D, 3D, -12D, 4D, 5D),
            box(-14D, 3D, 11D, -12D, 4D, 13D),
            box(-15D, 4D, 1D, 15D, 6D, 15D),
            box(12D, 6D, 2D, 14D, 30D, 4D),
            box(-14D, 6D, 2D, -12D, 30D, 4D),
            box(-14D, 6D, 12D, -12D, 30D, 14D),
            box(12D, 6D, 12D, 14D, 30D, 14D),
            box(-12D, 6D, 3D, 12D, 30D, 13D),
            box(-15D, 30D, 1D, 15D, 32D, 15D),
            box(12D, 32D, 2D, 14D, 46D, 4D),
            box(-14D, 32D, 2D, -12D, 46D, 4D),
            box(-14D, 32D, 12D, -12D, 46D, 14D),
            box(12D, 32D, 12D, 14D, 46D, 14D),
            box(-15D, 46D, 1D, 15D, 48D, 15D),
            box(-13D, 32D, 3D, 13D, 46D, 13D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public RoyalWardrobeBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return FurnitureBlockComponentHolder.getShape(FACING_SHAPES, blockState, pos);
    }

    @Override
    protected void registerComponents(ComponentRegistrar<BlockComponent> registrar) {
        super.registerComponents(registrar);

        registrar.register(BlockComponentTypes.DYEABLE);
    }
}
