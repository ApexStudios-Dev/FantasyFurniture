package dev.apexstudios.fantasyfurniture.block.base;

import com.google.common.collect.Maps;
import dev.apexstudios.apexcore.lib.block.BaseBlock;
import dev.apexstudios.apexcore.lib.block.FacingBlock;
import dev.apexstudios.apexcore.lib.block.FluidLoggedBlock;
import dev.apexstudios.apexcore.lib.block.MultiBlock;
import java.util.Map;
import net.minecraft.SharedConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public abstract class FurnitureBaseBlock extends BaseBlock implements FacingBlock, FluidLoggedBlock {
    private final Map<BlockState, VoxelShape> shapes = Maps.newHashMap();

    protected FurnitureBaseBlock(Properties properties) {
        super(properties);
    }

    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return Shapes.block();
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        return shapes.computeIfAbsent(blockState, $ -> getFurnitureShape($, pos));
    }

    @Override
    public InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos pos, Player player, BlockHitResult result) {
        if(SharedConstants.IS_RUNNING_IN_IDE && player.isShiftKeyDown() && !shapes.isEmpty()) {
            player.displayClientMessage(Component.literal("Cleared VoxelShape cache"), true);
            shapes.clear();
            return InteractionResult.SUCCESS;
        }

        return super.useWithoutItem(blockState, level, pos, player, result);
    }

    public static VoxelShape getShape(Map<Direction, VoxelShape> shapes, BlockState blockState, Property<Direction> facingProperty, BlockPos pos) {
        var facing = blockState.getValue(facingProperty);
        var shape = shapes.get(facing);
        return getShape(shape, blockState, pos);
    }

    public static VoxelShape getShape(VoxelShape shape, BlockState blockState, BlockPos pos) {
        if(blockState.getBlock() instanceof MultiBlock multiBlock)
            return multiBlock.fixVoxelShape(shape, blockState, pos);

        return shape;
    }
}
