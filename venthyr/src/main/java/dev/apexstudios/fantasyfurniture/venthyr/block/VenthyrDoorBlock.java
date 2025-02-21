package dev.apexstudios.fantasyfurniture.venthyr.block;

import com.google.common.collect.Maps;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureDoorBlockComponentHolder;
import dev.apexstudios.fantasyfurniture.venthyr.VenthyrFurnitureSet;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrDoorBlock extends FurnitureDoorBlockComponentHolder {
    public static final VoxelShape SHAPE = box(0D, 0D, 0D, 3D, 32D, 16D);

    private final Map<BlockState, VoxelShape> shapes = Maps.newHashMap();

    public VenthyrDoorBlock(Properties properties) {
        super(VenthyrFurnitureSet.FURNITURE_SET, properties);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        return shapes.computeIfAbsent(blockState, $ -> getShape(SHAPE, $, pos));
    }
}
