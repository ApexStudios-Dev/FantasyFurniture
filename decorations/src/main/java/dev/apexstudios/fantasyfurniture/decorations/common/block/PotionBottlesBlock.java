package dev.apexstudios.fantasyfurniture.decorations.common.block;

import dev.apexstudios.apexcore.api.block.Dyeable;
import dev.apexstudios.apexcore.api.util.ApexShapes;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class PotionBottlesBlock extends StackedDyeableBlock implements Dyeable.WithNone {
    public static final IntegerProperty COUNT = IntegerProperty.create("count", 0, 2);

    public static final VoxelShape SHAPE_0 = box(6D, 0D, 6D, 10D, 10D, 10D);

    public static final VoxelShape SHAPE_1 = ApexShapes.join(
            box(10D, 0D, 7D, 14D, 10D, 11D),
            box(1.25D, 0D, 3.25D, 6.75D, 10D, 8.75D)
    );

    public static final VoxelShape SHAPE_2 = ApexShapes.join(
            box(10D, 0D, 10D, 14D, 10D, 14D),
            box(1.25D, 0D, 6.25D, 6.75D, 10D, 11.75D),
            box(6.25D, 0D, 1.25D, 11.75D, 10D, 6.75D)
    );

    public static final Map<Direction, VoxelShape> SHAPES_0 = Shapes.rotateHorizontal(SHAPE_0);
    public static final Map<Direction, VoxelShape> SHAPES_1 = Shapes.rotateHorizontal(SHAPE_1);
    public static final Map<Direction, VoxelShape> SHAPES_2 = Shapes.rotateHorizontal(SHAPE_2);

    public PotionBottlesBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        var facing = blockState.getValue(FACING);
        var count = blockState.getValue(COUNT);

        return (switch(count) {
            case 0 -> SHAPES_0;
            case 1 -> SHAPES_1;
            case 2 -> SHAPES_2;
            default -> throw new IllegalStateException("Unexpected value: " + count);
        }).get(facing);
    }

    @Override
    public IntegerProperty getStackableProperty() {
        return COUNT;
    }
}
