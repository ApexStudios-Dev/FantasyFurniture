package dev.apexstudios.fantasyfurniture.common.block;

import dev.apexstudios.apexcore.api.multiblock.BedMultiBlock;
import dev.apexstudios.apexcore.api.multiblock.MultiBlockProperty;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BedDoubleBlock extends BedMultiBlock {
    public static final MultiBlockProperty MULTI_BLOCK = MultiBlockProperty.create(builder -> builder
            .with(-1, 0, 0)
            .with(-1, 0, -1)
            .with(0, 0, -1)
    );

    private final Map<Direction, VoxelShape> shapes;

    public BedDoubleBlock(Properties properties, VoxelShape baseShape) {
        super(properties);

        shapes = Shapes.rotateHorizontal(baseShape);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        var facing = blockState.getValue(FACING).getOpposite();
        var shape = shapes.get(facing);
        return FurnitureUtil.getShape(shape, blockState, pos);
    }

    @Override
    public MultiBlockProperty getMultiBlockProperty() {
        return MULTI_BLOCK;
    }
}
