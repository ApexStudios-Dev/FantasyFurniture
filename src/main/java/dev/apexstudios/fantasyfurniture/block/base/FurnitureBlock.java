package dev.apexstudios.fantasyfurniture.block.base;

import com.google.common.collect.Maps;
import dev.apexstudios.fantasyfurniture.set.BlockType;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FurnitureBlock extends Block {
    protected final FurnitureSet furnitureSet;
    protected final BlockType<?, ?> blockType;
    private final Map<BlockState, VoxelShape> shapes = Maps.newHashMap();

    public FurnitureBlock(FurnitureSet furnitureSet, BlockType<?, ?> blockType, Properties properties) {
        super(properties);

        this.furnitureSet = furnitureSet;
        this.blockType = blockType;
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        return shapes.computeIfAbsent(blockState, $ -> furnitureSet.shape(blockType, $, () -> super.getShape($, level, pos, context)));
    }
}
