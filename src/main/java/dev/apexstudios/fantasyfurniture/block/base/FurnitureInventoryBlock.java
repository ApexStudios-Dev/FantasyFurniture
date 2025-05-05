package dev.apexstudios.fantasyfurniture.block.base;

import com.google.common.collect.Maps;
import dev.apexstudios.apexcore.lib.block.BaseBlock;
import dev.apexstudios.apexcore.lib.block.FacingBlock;
import dev.apexstudios.apexcore.lib.block.FluidLoggedBlock;
import dev.apexstudios.apexcore.lib.block.InventoryBlock;
import dev.apexstudios.fantasyfurniture.block.entity.FurnitureInventoryBlockEntity;
import java.util.Map;
import net.minecraft.SharedConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FurnitureInventoryBlock extends BaseBlock implements FacingBlock, FluidLoggedBlock, InventoryBlock {
    private final Map<BlockState, VoxelShape> shapes = Maps.newHashMap();

    public FurnitureInventoryBlock(Properties properties) {
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

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState blockState) {
        return new FurnitureInventoryBlockEntity(pos, blockState);
    }

    public static VoxelShape getShape(Map<Direction, VoxelShape> shapes, BlockState blockState, Property<Direction> facingProperty, BlockPos pos) {
        var facing = blockState.getValue(facingProperty);
        // return getShape(shapes.get(facing), blockState, pos);
        return shapes.get(facing);
    }

    // TODO
    /*public static VoxelShape getShape(VoxelShape shape, BlockState blockState, BlockPos pos) {
        var multiBlock = BlockComponentHelper.getComponent(blockState, BlockComponentTypes.MULTI_BLOCK);

        if(multiBlock == null)
            return shape;

        return MultiBlockComponent.fixVoxelShape(shape, multiBlock, blockState, pos);
    }*/
}
