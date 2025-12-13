package dev.apexstudios.fantasyfurniture.decorations.cookie;

import dev.apexstudios.apexcore.lib.util.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.InventoryBlock;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class CookieJarBlock extends InventoryBlock {
    public static final VoxelShape SHAPE = ApexShapes.join(
            box(4D, 0D, 4D, 12D, 9D, 12D),
            box(5D, 9D, 5D, 11D, 10D, 11D),
            box(4.5D, 10D, 4.5D, 11.5D, 12D, 11.5D)
    );

    public static final Map<Direction, VoxelShape> SHAPES = Shapes.rotateHorizontal(SHAPE);

    public static final EnumProperty<Fullness> FULLNESS = EnumProperty.create("fullness", Fullness.class);

    public CookieJarBlock(Properties properties) {
        super(properties);

        registerDefaultState(defaultBlockState().setValue(FULLNESS, Fullness.EMPTY));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder.add(FULLNESS));
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState blockState) {
        return new CookieJarBlockEntity(pos, blockState);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        var facing = blockState.getValue(FACING);
        return SHAPES.get(facing);
    }

    public enum Fullness implements StringRepresentable {
        EMPTY("empty"),
        HALF("half"),
        FULL("full");

        private final String serializedName;

        Fullness(String serializedName) {
            this.serializedName = serializedName;
        }

        @Override
        public String getSerializedName() {
            return serializedName;
        }
    }
}
