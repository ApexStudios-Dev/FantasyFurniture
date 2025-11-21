package dev.apexstudios.fantasyfurniture.decorations.block;

import dev.apexstudios.apexcore.lib.block.Dyeable;
import dev.apexstudios.apexcore.lib.block.SimpleHorizontalDirectionalBlock;
import dev.apexstudios.apexcore.lib.util.ApexShapes;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

// TODO: Upstream into Dyeable to allow no color
public final class FairyLightsBlock extends SimpleHorizontalDirectionalBlock implements Dyeable {
    public static final EnumProperty<LightColor> COLOR = EnumProperty.create("color", LightColor.class);

    public static final VoxelShape SHAPE = ApexShapes.join(
            box(2D, 10D, 14D, 14D, 16D, 16D),
            box(5D, 8D, 14D, 11D, 10D, 16D)
    );

    public static final Map<Direction, VoxelShape> SHAPES = Shapes.rotateHorizontal(SHAPE);

    public FairyLightsBlock(Properties properties) {
        super(properties);

        registerDefaultState(defaultBlockState().setValue(COLOR, LightColor.NONE));
    }

    @Override
    public DyeColor getDyedColor(BlockState blockState) {
        var color = blockState.getValue(COLOR);
        return color == LightColor.NONE ? DyeColor.WHITE : color.color;
    }

    @Override
    public void setDyedColor(Level level, BlockPos pos, BlockState blockState, DyeColor color) {
        blockState.setValue(COLOR, LightColor.from(color));
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        var facing = blockState.getValue(FACING);
        return SHAPES.get(facing);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder.add(COLOR));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        var placementBlockState = super.getStateForPlacement(context);

        if(placementBlockState == null)
            return null;

        var color = getColorForPlacement(context);
        return placementBlockState.setValue(COLOR, color);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        var color = DyeColor.getColor(stack);
        var currentColor = blockState.getValue(COLOR);
        var newColor = currentColor;

        if(player.isSecondaryUseActive()) {
            if(color == null && currentColor != LightColor.NONE) {
                newColor = LightColor.NONE;
            }
        } else {
            if(color != null && currentColor.dyeable(color)) {
                newColor = LightColor.from(color);
            }
        }

        if(newColor != currentColor) {
            if(!level.isClientSide()) {
                level.setBlockAndUpdate(pos, blockState.setValue(COLOR, newColor));
            }

            return InteractionResult.SUCCESS;
        }

        return super.useItemOn(stack, blockState, level, pos, player, hand, hitResult);
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState blockState, boolean includeData, Player player) {
        return getCloneStack(this, blockState, player, includeData);
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState blockState, boolean includeData) {
        return getCloneStack(this, blockState, null, includeData);
    }

    static ItemStack getCloneStack(ItemLike item, BlockState blockState, @Nullable Player player, boolean includeData) {
        var stack = new ItemStack(item);

        if(includeData || (player != null && player.isCreative())) {
            var color = blockState.getValue(COLOR);

            if(color != LightColor.NONE) {
                Dyeable.setColor(stack, color.color);
            }
        }

        return stack;
    }

    static LightColor getColorForPlacement(BlockPlaceContext context) {
        var player = context.getPlayer();

        if(player == null) {
            return LightColor.NONE;
        }

        var hand = context.getHand();
        var otherHand = hand == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
        var stack = player.getItemInHand(otherHand);
        var color = DyeColor.getColor(stack);

        if(color == null) {
            color = context.getItemInHand().get(DataComponents.BASE_COLOR);
        }

        return LightColor.from(color);
    }

    public enum LightColor implements StringRepresentable {
        NONE(null),
        WHITE(DyeColor.WHITE),
        ORANGE(DyeColor.ORANGE),
        MAGENTA(DyeColor.MAGENTA),
        LIGHT_BLUE(DyeColor.LIGHT_BLUE),
        YELLOW(DyeColor.YELLOW),
        LIME(DyeColor.LIME),
        PINK(DyeColor.PINK),
        GRAY(DyeColor.GRAY),
        LIGHT_GRAY(DyeColor.LIGHT_GRAY),
        CYAN(DyeColor.CYAN),
        PURPLE(DyeColor.PURPLE),
        BLUE(DyeColor.BLUE),
        BROWN(DyeColor.BROWN),
        GREEN(DyeColor.GREEN),
        RED(DyeColor.RED),
        BLACK(DyeColor.BLACK);

        @Nullable public final DyeColor color;

        LightColor(@Nullable DyeColor color) {
            this.color = color;
        }

        @Override
        public String getSerializedName() {
            return color == null ? "none" : color.getSerializedName();
        }

        public boolean dyeable(DyeColor dyeColor) {
            return color == null || color != dyeColor;
        }

        static LightColor from(@Nullable DyeColor color) {
            return switch(color) {
                case WHITE -> WHITE;
                case ORANGE -> ORANGE;
                case MAGENTA -> MAGENTA;
                case LIGHT_BLUE -> LIGHT_BLUE;
                case YELLOW -> YELLOW;
                case LIME -> LIME;
                case PINK -> PINK;
                case GRAY -> GRAY;
                case LIGHT_GRAY -> LIGHT_GRAY;
                case CYAN -> CYAN;
                case PURPLE -> PURPLE;
                case BLUE -> BLUE;
                case BROWN -> BROWN;
                case GREEN -> GREEN;
                case RED -> RED;
                case BLACK -> BLACK;
                case null -> NONE;
            };
        }
    }
}
