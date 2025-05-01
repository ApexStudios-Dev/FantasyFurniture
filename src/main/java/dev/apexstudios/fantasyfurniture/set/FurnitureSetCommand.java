package dev.apexstudios.fantasyfurniture.set;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentHelper;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;
import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

public interface FurnitureSetCommand {
    static LiteralArgumentBuilder<CommandSourceStack> register() {
        return literal("furniture-set")
                .then(literal("place")
                        .requires(source -> {
                            var player = source.getPlayer();

                            if(player == null)
                                return false;
                            if(player.isSpectator())
                                return false;
                            if(player.gameMode().isBlockPlacingRestricted())
                                return false;
                            if(!player.mayBuild())
                                return false;
                            return true;
                        })
                        .then(argument("furniture-set", ResourceLocationArgument.id())
                                .suggests((context, builder) -> SharedSuggestionProvider.suggestResource(FurnitureSet.getIds(), builder))
                                .then(argument("pos", BlockPosArgument.blockPos())
                                        .executes(context -> {
                                            var player = context.getSource().getPlayerOrException();
                                            var furnitureSet = getFurnitureSet(context);
                                            var pos = BlockPosArgument.getLoadedBlockPos(context, "pos");
                                            return furnitureSet == null ? -1 : placeFurnitureSet(player, furnitureSet, pos);
                                        })
                                )
                                .executes(context -> {
                                    var player = context.getSource().getPlayerOrException();
                                    var furnitureSet = getFurnitureSet(context);
                                    return furnitureSet == null ? -1 : placeFurnitureSet(player, furnitureSet, player.blockPosition().below());
                                })
                        )
                );
    }

    @Nullable
    private static FurnitureSet getFurnitureSet(CommandContext<CommandSourceStack> context) {
        var furnitureSetName = ResourceLocationArgument.getId(context, "furniture-set");
        return FurnitureSet.get(furnitureSetName);
    }

    private static int placeFurnitureSet(ServerPlayer player, FurnitureSet furnitureSet, BlockPos origin) {
        var facing = player.getDirection().getClockWise();
        var blockTypes = List.copyOf(furnitureSet.blockTypes());
        var pos = origin.mutable();
        var level = player.level();
        var stack = player.getMainHandItem();
        var hitResult = Item.getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);
        var context = new BlockPlaceContext(player, InteractionHand.MAIN_HAND, stack, hitResult);
        var enabledFeatures = level.enabledFeatures();

        for(var blockType : blockTypes) {
            var placePos = pos.immutable();
            var block = furnitureSet.get(blockType).orElse(null);

            if (block == null/* || !block.isEnabled(enabledFeatures)*/)
                continue;

            var blockState = block.getStateForPlacement(context);

            if (blockState == null)
                blockState = block.defaultBlockState();

            level.setBlockAndUpdate(placePos, blockState);
            block.setPlacedBy(level, placePos, blockState, player, stack);

            BlockComponentHelper.runForComponent(blockState, BlockComponentTypes.MULTI_BLOCK, component -> {
                var maxX = 0;
                var maxZ = 0;

                for (var local : component.localPositions()) {
                    maxX = Math.max(maxX, local.x());
                    maxZ = Math.max(maxZ, local.z());
                }

                var amount = facing.getAxis().choose(maxX, 0, maxZ);

                if (facing.getAxisDirection() == Direction.AxisDirection.NEGATIVE)
                    amount *= -1;

                pos.move(facing, amount);
            });

            pos.move(facing, 2);
        }

        if(true)
            return Command.SINGLE_SUCCESS;

        var start = origin.below();
        var end = pos.move(Direction.DOWN).immutable();
        var counter = new AtomicInteger();

        BlockPos.betweenClosed(start, end).forEach(floorPos -> {
            var floorBlock = counter.getAndIncrement() % 2 == 0 ? Blocks.WHITE_TERRACOTTA : Blocks.BLACK_TERRACOTTA;
            var floorBlockState = floorBlock.getStateForPlacement(context);

            if(floorBlockState == null)
                floorBlockState = floorBlock.defaultBlockState();

            level.setBlockAndUpdate(floorPos, floorBlockState);
            floorBlock.setPlacedBy(level, floorPos, floorBlockState, player, stack);
        });

        return Command.SINGLE_SUCCESS;
    }
}
