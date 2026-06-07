package dev.apexstudios.fantasyfurniture.common.block;

import dev.apexstudios.apexcore.api.multiblock.MultiBlock;
import dev.apexstudios.apexcore.api.multiblock.MultiBlockProperties;
import dev.apexstudios.apexcore.api.multiblock.MultiBlockProperty;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ChairBlock extends SeatMultiBlock {
    private final Map<Direction, VoxelShape> shapes;

    public ChairBlock(Properties properties, VoxelShape baseShape) {
        super(properties);

        shapes = Shapes.rotateHorizontal(baseShape);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        return FurnitureUtil.getShape(shapes, blockState, pos);
    }

    @Override
    public MultiBlockProperty getMultiBlockProperty() {
        return MultiBlockProperties.MB_1x2x1;
    }

    @Override
    public float getBounceRestitution(Level level, BlockPos pos, BlockState blockState, Entity entity) {
        return MultiBlock.getIndex(blockState) == 0 ? super.getBounceRestitution(level, pos, blockState, entity) : 0F;
    }
}
