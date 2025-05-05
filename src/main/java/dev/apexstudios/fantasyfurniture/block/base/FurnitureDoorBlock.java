package dev.apexstudios.fantasyfurniture.block.base;

import com.google.common.collect.Maps;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import java.util.Map;
import net.minecraft.SharedConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FurnitureDoorBlock extends DoorBlock {
    public static final VoxelShape SHAPE = box(0D, 0D, 0D, 3D, 32D, 16D);

    private final Map<BlockState, VoxelShape> shapes = Maps.newHashMap();

    public FurnitureDoorBlock(BlockSetType type, Properties properties) {
        super(type, properties);
    }

    public FurnitureDoorBlock(FurnitureSet furnitureSet, Properties properties) {
        this(furnitureSet.blockSet(), properties);
    }

    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return FurnitureBaseBlock.getShape(SHAPE, blockState, pos);
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
}
