package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.apexcore.lib.component.ComponentRegistrar;
import dev.apexstudios.apexcore.lib.component.block.BlockComponent;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.DresserBlock;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureBlockComponentHolder;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class RoyalDresserBlock extends DresserBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(-15D, 0D, 1D, -13D, 3D, 4D),
            box(-15D, 0D, 12D, -13D, 3D, 15D),
            box(13D, 0D, 12D, 15D, 3D, 15D),
            box(13D, 0D, 1D, 15D, 3D, 4D),
            box(13D, 3D, 2D, 15D, 14D, 4D),
            box(-15D, 3D, 2D, -13D, 14D, 4D),
            box(-15D, 3D, 12D, -13D, 14D, 14D),
            box(13D, 3D, 12D, 15D, 14D, 14D),
            box(-15.5D, 5D, 1.5D, 15.5D, 7D, 14.5D),
            box(-16D, 14D, 0D, 16D, 16D, 16D),
            box(-14D, 7D, 3D, 14D, 14D, 13D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public RoyalDresserBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return FurnitureBlockComponentHolder.getShape(FACING_SHAPES, blockState, pos);
    }

    @Override
    protected void registerComponents(ComponentRegistrar<BlockComponent, Block> registrar) {
        super.registerComponents(registrar);

        registrar.register(BlockComponentTypes.DYEABLE);
    }
}
