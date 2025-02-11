package dev.apexstudios.fantasyfurniture.nordic.block;

import com.google.common.collect.Maps;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureDoorBlockComponentHolder;
import dev.apexstudios.fantasyfurniture.nordic.NordicFurnitureSet;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NordicDoorBlock extends FurnitureDoorBlockComponentHolder {
    public static final VoxelShape SHAPE = box(0D, 0D, 0D, 3D, 32D, 16D);

    private final Map<BlockState, VoxelShape> shapes = Maps.newHashMap();

    public NordicDoorBlock(Properties properties) {
        super(NordicFurnitureSet.FURNITURE_SET, properties);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        return shapes.computeIfAbsent(blockState, $ -> getShape(SHAPE, $, pos));
    }
}
