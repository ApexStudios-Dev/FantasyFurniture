package dev.apexstudios.fantasyfurniture.block;

import dev.apexstudios.apexcore.lib.component.ComponentRegistrar;
import dev.apexstudios.apexcore.lib.component.block.BlockComponent;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureBlockComponentHolder;
import dev.apexstudios.fantasyfurniture.block.base.InventoryBlock;
import dev.apexstudios.fantasyfurniture.block.entity.BookshelfBlockEntity;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BookshelfBlock extends InventoryBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(-15D, 0D, 1D, 15D, 30D, 15D),
            box(-16D, 30D, 0D, 16D, 32D, 16D)
    );

    public static final Map<Direction, VoxelShape> FACING_SHAPES = Shapes.rotateHorizontal(SHAPE);

    public BookshelfBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return FurnitureBlockComponentHolder.getShape(FACING_SHAPES, blockState, pos);
    }

    @Override
    protected void registerComponents(ComponentRegistrar<BlockComponent, Block> registrar) {
        super.registerComponents(registrar);

        registrar.register(BlockComponentTypes.MULTI_BLOCK, builder -> builder
                .with(0, 0, 1)
                .with(0, 1, 1)
                .with(0, 1, 0)
                .rotatingFromComponent()
        );
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState blockState) {
        return new BookshelfBlockEntity(pos, blockState);
    }
}
