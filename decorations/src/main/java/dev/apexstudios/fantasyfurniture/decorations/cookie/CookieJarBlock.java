package dev.apexstudios.fantasyfurniture.decorations.cookie;

import dev.apexstudios.fantasyfurniture.block.InventoryBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public final class CookieJarBlock extends InventoryBlock {
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
