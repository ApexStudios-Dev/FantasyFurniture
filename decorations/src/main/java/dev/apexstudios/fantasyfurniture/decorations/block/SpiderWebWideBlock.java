package dev.apexstudios.fantasyfurniture.decorations.block;

import dev.apexstudios.apexcore.lib.multiblock.MultiBlockProperties;
import dev.apexstudios.apexcore.lib.multiblock.MultiBlockProperty;
import dev.apexstudios.apexcore.lib.multiblock.SimpleHorizontalDirectionalMultiBlock;
import dev.apexstudios.fantasyfurniture.util.FurnitureUtil;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class SpiderWebWideBlock extends SimpleHorizontalDirectionalMultiBlock {
    public static final VoxelShape SHAPE = box(-16D, 0D, 14D, 16D, 16D, 16D);
    public static final Map<Direction, VoxelShape> SHAPES = Shapes.rotateHorizontal(SHAPE);

    public SpiderWebWideBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        return FurnitureUtil.getShape(SHAPES.get(blockState.getValue(FACING)), blockState, pos);
    }

    @Override
    public MultiBlockProperty getMultiBlockProperty() {
        return MultiBlockProperties.MB_1x1x2;
    }
}
