package dev.apexstudios.fantasyfurniture.block;

import dev.apexstudios.apexcore.lib.component.ComponentRegistrar;
import dev.apexstudios.apexcore.lib.component.block.BlockComponent;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.component.block.types.FacingBlockComponent;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureBlockComponentHolder;
import dev.apexstudios.fantasyfurniture.block.property.ShelfConnection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public final class ShelfBlock extends FurnitureBlockComponentHolder {
    public static final EnumProperty<ShelfConnection> CONNECTION = ShelfConnection.PROPERTY;

    public ShelfBlock(Properties properties) {
        super(properties);

        registerDefaultState(defaultBlockState().setValue(CONNECTION, ShelfConnection.BOTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CONNECTION);
    }

    @Override
    protected void registerComponents(ComponentRegistrar<BlockComponent> registrar) {
        super.registerComponents(registrar);

        FacingBlockComponent.registerHorizontal(registrar);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        var blockState = super.getStateForPlacement(context);

        if(blockState == null)
            return null;

        var level = context.getLevel();
        var pos = context.getClickedPos();
        var facingProperty = getComponentOrThrow(BlockComponentTypes.FACING).getProperty();

        return blockState.setValue(CONNECTION, ShelfConnection.determine(level, pos, blockState, facingProperty));
    }

    @Override
    public BlockState updateShape(BlockState blockState, LevelReader level, ScheduledTickAccess tickAccess, BlockPos pos, Direction facing, BlockPos neighborPos, BlockState neighborBlockState, RandomSource random) {
        var result = blockState;

        if(facing.getAxis().isHorizontal()) {
            var facingProperty = getComponentOrThrow(BlockComponentTypes.FACING).getProperty();
            result = blockState.setValue(CONNECTION, ShelfConnection.determine(level, pos, blockState, facingProperty));
        }

        return super.updateShape(result, level, tickAccess, pos, facing, neighborPos, neighborBlockState, random);
    }
}
