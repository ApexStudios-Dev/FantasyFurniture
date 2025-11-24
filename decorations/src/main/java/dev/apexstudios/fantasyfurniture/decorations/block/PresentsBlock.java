package dev.apexstudios.fantasyfurniture.decorations.block;

import dev.apexstudios.apexcore.lib.block.Dyeable;
import dev.apexstudios.apexcore.lib.util.ApexShapes;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class PresentsBlock extends StackedDyeableBlock implements Dyeable.WithNone {
    public static final IntegerProperty COUNT = IntegerProperty.create("count", 0, 1);

    public static final VoxelShape SHAPE_0 = ApexShapes.join(
            box(5D, 0D, 5D, 11D, 5D, 11D),
            box(4.5D, 5D, 4.5D, 11.5D, 7D, 11.5D)
    );

    public static final VoxelShape SHAPE_1 = ApexShapes.join(
            box(1D, 0D, 9.5D, 7D, 5D, 15.5D),
            box(.5D, 5D, 9D, 7.5D, 7D, 16D),
            box(1.5576035123371383D, 4D, -.04642728548911618D, 16.30760351233714D, 6D, 11.453572714510884D),
            box(2.3076035123371383D, 0D, .4535727145108839D, 15.807603512337138D, 4D, 10.703572714510884D)
    );

    public static final Map<Direction, VoxelShape> SHAPES_0 = Shapes.rotateHorizontal(SHAPE_0);
    public static final Map<Direction, VoxelShape> SHAPES_1 = Shapes.rotateHorizontal(SHAPE_1);

    public PresentsBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        var facing = blockState.getValue(FACING);
        var count = blockState.getValue(COUNT);

        return (switch(count) {
            case 0 -> SHAPES_0;
            case 1 -> SHAPES_1;
            default -> throw new IllegalStateException("Unexpected value: " + count);
        }).get(facing);
    }

    @Override
    public IntegerProperty getStackableProperty() {
        return COUNT;
    }
}
