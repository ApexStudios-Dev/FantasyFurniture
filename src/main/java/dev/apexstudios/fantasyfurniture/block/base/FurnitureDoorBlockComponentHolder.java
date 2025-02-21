package dev.apexstudios.fantasyfurniture.block.base;

import com.google.common.collect.Maps;
import dev.apexstudios.apexcore.lib.component.ComponentRegistrar;
import dev.apexstudios.apexcore.lib.component.block.BlockComponent;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentHelper;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.component.block.DoorBlockComponentHolder;
import dev.apexstudios.apexcore.lib.component.block.types.FluidLoggedBlockComponent;
import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import java.util.Map;
import net.minecraft.SharedConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.MustBeInvokedByOverriders;

public class FurnitureDoorBlockComponentHolder extends DoorBlockComponentHolder {
    public static final VoxelShape SHAPE = box(0D, 0D, 0D, 3D, 32D, 16D);

    private final Map<BlockState, VoxelShape> shapes = Maps.newHashMap();

    public FurnitureDoorBlockComponentHolder(BlockSetType type, Properties properties) {
        super(type, properties);
    }

    public FurnitureDoorBlockComponentHolder(FurnitureSet furnitureSet, Properties properties) {
        this(furnitureSet.blockSet(), properties);
    }

    protected VoxelShape getFurnitureShape(BlockState blockState, BlockPos pos) {
        return getShape(SHAPE, blockState, pos);
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

    @MustBeInvokedByOverriders
    @Override
    protected void registerComponents(ComponentRegistrar<BlockComponent> registrar) {
        super.registerComponents(registrar);

        FluidLoggedBlockComponent.registerWater(registrar);

        registrar.register(BlockComponentTypes.MULTI_BLOCK, builder -> builder
                .with(0, 1, 0)
                .rotatingFromComponent()
        );
    }

    public static VoxelShape getShape(VoxelShape baseShape, BlockState blockState, BlockPos pos) {
        var facing = BlockComponentHelper.getComponentOrThrow(blockState, BlockComponentTypes.FACING).get(blockState);
        var open = blockState.getValue(OPEN);

        if(open && blockState.getValue(HINGE) == DoorHingeSide.RIGHT)
            facing = facing.getOpposite();

        var shape = ApexShapes.rotateHorizontal(baseShape, open ? facing : facing.getCounterClockWise());
        return FurnitureBlockComponentHolder.getShape(shape, blockState, pos);
    }

}
