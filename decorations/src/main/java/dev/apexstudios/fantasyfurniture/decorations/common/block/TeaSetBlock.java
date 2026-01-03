package dev.apexstudios.fantasyfurniture.decorations.common.block;

import dev.apexstudios.apexcore.api.multiblock.MultiBlockProperties;
import dev.apexstudios.apexcore.api.multiblock.MultiBlockProperty;
import dev.apexstudios.apexcore.api.multiblock.SimpleHorizontalDirectionalMultiBlock;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class TeaSetBlock extends SimpleHorizontalDirectionalMultiBlock {
    public static final VoxelShape SHAPE = box(-11D, 0D, 2D, 11D, 9D, 14D);
    public static final Map<Direction, VoxelShape> SHAPES = Shapes.rotateHorizontal(SHAPE);

    public TeaSetBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        return FurnitureUtil.getShape(SHAPE, blockState, pos);
    }

    @Override
    public MultiBlockProperty getMultiBlockProperty() {
        return MultiBlockProperties.MB_1x1x2;
    }
}
