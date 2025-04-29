package dev.apexstudios.fantasyfurniture.block;

import dev.apexstudios.apexcore.lib.component.block.BlockComponentRegistrar;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureBlockComponentHolder;
import dev.apexstudios.fantasyfurniture.block.base.InventoryBlock;
import dev.apexstudios.fantasyfurniture.block.entity.ChestBlockEntity;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ChestBlock extends InventoryBlock {
    public static final VoxelShape SHAPE = box(-15D, 0D, 2D, 15D, 14D, 16D);
    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public ChestBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return FurnitureBlockComponentHolder.getShape(FACING_SHAPES, blockState, pos);
    }

    @Override
    protected void registerComponents(BlockComponentRegistrar registrar) {
        super.registerComponents(registrar);

        registrar.register(BlockComponentTypes.MULTI_BLOCK, builder -> builder
                .with(0, 0, 1)
                .rotatingFromComponent()
        );
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState blockState) {
        return new ChestBlockEntity(pos, blockState);
    }
}
