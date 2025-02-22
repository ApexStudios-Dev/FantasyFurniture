package dev.apexstudios.fantasyfurniture.block;

import dev.apexstudios.apexcore.lib.component.ComponentRegistrar;
import dev.apexstudios.apexcore.lib.component.block.BlockComponent;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureBlockComponentHolder;
import dev.apexstudios.fantasyfurniture.block.base.SeatBlock;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CushionBlock extends SeatBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(2D, 0D, 2D, 4D, 2D, 4D),
            box(2D, 0D, 12D, 4D, 2D, 14D),
            box(12D, 0D, 12D, 14D, 2D, 14D),
            box(12D, 0D, 2D, 14D, 2D, 4D),
            box(2D, 5D, 2.25D, 14D, 7D, 13.75D),
            box(1.75D, 4D, 2D, 14.25D, 5D, 14D),
            box(2D, 2D, 2.5D, 4D, 4D, 4.5D),
            box(12D, 2D, 2.5D, 14D, 4D, 4.5D),
            box(12D, 2D, 11.5D, 14D, 4D, 13.5D),
            box(2D, 2D, 11.5D, 4D, 4D, 13.5D),
            box(2.5D, 2.5D, 4.5D, 3.5D, 3.5D, 11.5D),
            box(12.5D, 2.5D, 4.5D, 13.5D, 3.5D, 11.5D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = ApexShapes.rotateHorizontal(SHAPE);

    public CushionBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return FurnitureBlockComponentHolder.getShape(FACING_SHAPES, blockState, pos);
    }

    @Override
    protected void registerComponents(ComponentRegistrar<BlockComponent> registrar) {
        super.registerComponents(registrar);

        registrar.register(BlockComponentTypes.BOUNCE);
    }
}
