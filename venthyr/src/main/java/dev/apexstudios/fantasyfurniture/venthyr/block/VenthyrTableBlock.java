package dev.apexstudios.fantasyfurniture.venthyr.block;

import com.google.common.collect.Maps;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.block.TableBlock;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class VenthyrTableBlock extends TableBlock {
    public static final VoxelShape SHAPE_TABLE_TOP = box(0D, 13D, 0D, 16D, 16D, 16D);
    public static final VoxelShape SHAPE_TABLE_LEG = box(13D, 0D, 1D, 15D, 13D, 3D);

    private final Map<BlockState, VoxelShape> shapes = Maps.newHashMap();

    public VenthyrTableBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        return shapes.computeIfAbsent(blockState, $ -> {
            var facing = getComponentOrThrow(BlockComponentTypes.FACING).get($);
            return ApexShapes.rotateHorizontal(getShape($, SHAPE_TABLE_TOP, SHAPE_TABLE_LEG), facing);
        });
    }

    @Override
    public InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos pos, Player player, BlockHitResult result) {
        if(player.isShiftKeyDown()) {
            shapes.clear();
            return InteractionResult.SUCCESS;
        }

        return super.useWithoutItem(blockState, level, pos, player, result);
    }
}
