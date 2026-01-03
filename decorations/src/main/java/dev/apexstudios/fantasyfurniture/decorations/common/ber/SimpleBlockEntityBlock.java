package dev.apexstudios.fantasyfurniture.decorations.common.ber;

import dev.apexstudios.apexcore.api.multiblock.MultiBlock;
import dev.apexstudios.apexcore.api.multiblock.MultiBlockProperties;
import dev.apexstudios.apexcore.api.multiblock.MultiBlockProperty;
import dev.apexstudios.apexcore.api.multiblock.SimpleHorizontalDirectionalMultiBlock;
import dev.apexstudios.apexcore.api.util.ApexShapes;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public sealed abstract class SimpleBlockEntityBlock extends SimpleHorizontalDirectionalMultiBlock implements EntityBlock {
    private final Map<Direction, VoxelShape> shapes;

    protected SimpleBlockEntityBlock(Properties properties, VoxelShape baseShape) {
        super(properties);

        shapes = Shapes.rotateHorizontal(baseShape);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        return MultiBlock.fixShape(shapes.get(blockState.getValue(FACING)), blockState, pos);
    }

    @Override
    protected RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.INVISIBLE;
    }

    @Override
    protected boolean triggerEvent(BlockState blockState, Level level, BlockPos pos, int id, int param) {
        var blockEntity = level.getBlockEntity(pos);
        return blockEntity == null ? super.triggerEvent(blockState, level, pos, id, param) : blockEntity.triggerEvent(id, param);
    }

    @Nullable
    @Override
    protected MenuProvider getMenuProvider(BlockState blockState, Level level, BlockPos pos) {
        return level.getBlockEntity(pos) instanceof MenuProvider provider ? provider : null;
    }

    @Override
    public MultiBlockProperty getMultiBlockProperty() {
        return MultiBlockProperties.MB_1x2x1;
    }

    public static final class WidowBloom extends SimpleBlockEntityBlock {
        public static final VoxelShape SHAPE = ApexShapes.join(
                box(6D, 0D, 6D, 10D, 3D, 10D),
                box(5.5D, 3D, 5.5D, 10.5D, 6D, 10.5D),
                box(5D, 6D, 5D, 11D, 8D, 11D),
                box(4.5D, 8D, 4.5D, 11.5D, 10D, 11.5D),
                box(5D, 10D, 5D, 11D, 11D, 11D)
        );

        public WidowBloom(Properties properties) {
            super(properties, SHAPE);
        }

        @Override
        public BlockEntity newBlockEntity(BlockPos pos, BlockState blockState) {
            return blockState.getValue(getMultiBlockProperty()) == 0 ? new SimpleBlockEntity.WidowBloom(pos, blockState) : null;
        }
    }

    public static final class SkullBlossom extends SimpleBlockEntityBlock {
        public static final VoxelShape SHAPE = box(4D, 0D, 4D, 12D, 8D, 12D);

        public SkullBlossom(Properties properties) {
            super(properties, SHAPE);
        }

        @Override
        public BlockEntity newBlockEntity(BlockPos pos, BlockState blockState) {
            return blockState.getValue(getMultiBlockProperty()) == 0 ? new SimpleBlockEntity.SkullBlossom(pos, blockState) : null;
        }
    }
}
