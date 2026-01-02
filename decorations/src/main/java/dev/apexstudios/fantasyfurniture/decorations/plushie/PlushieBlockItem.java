package dev.apexstudios.fantasyfurniture.decorations.plushie;

import dev.apexstudios.fantasyfurniture.decorations.DecorationsFurnitureModule;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ResolvableProfile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import org.jspecify.annotations.Nullable;

public final class PlushieBlockItem extends BlockItem {
    public static final String PLAYER_KEY = DecorationsFurnitureModule.PLUSHIE_BLOCK.getId().toLanguageKey("block", "player");
    public static final String DYANMIC_KEY = DecorationsFurnitureModule.PLUSHIE_BLOCK.getId().toLanguageKey("block", "dyanmic");

    public PlushieBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        var stack = player.getItemInHand(hand);

        if(player.isSecondaryUseActive()) {
            setProfile(stack, ResolvableProfile.createResolved(player.getGameProfile()));
            return InteractionResult.SUCCESS.heldItemTransformedTo(stack);
        }

        return super.use(level, player, hand);
    }

    public static void setProfile(ItemStack stack, @Nullable ResolvableProfile profile) {
        stack.set(DataComponents.PROFILE, profile);

        if(stack.has(DataComponents.PROFILE)) {
            stack.set(DataComponents.ITEM_NAME, createDynamicName(stack));
        } else {
            stack.remove(DataComponents.ITEM_NAME);
        }
    }

    public static Component createDynamicName(ItemStack stack) {
        var playerName = resolvePlayerNameOrDefault(stack);
        var itemName = Component.translatable(stack.getItem().getDescriptionId());
        return Component.translatable(DYANMIC_KEY, playerName, itemName);
    }

    public static @Nullable Component resolvePlayerName(ItemStack stack) {
        var server = ServerLifecycleHooks.getCurrentServer();
        var profile = stack.get(DataComponents.PROFILE);

        if(server == null || profile == null) {
            return null;
        }

        var resolved = profile.resolveProfile(server.services().profileResolver()).join();
        var player = server.getPlayerList().getPlayer(resolved.id());

        if(player != null) {
            return player.getName().plainCopy();
        }

        var name = resolved.name();
        return name == null || name.isBlank() ? null : Component.literal(name);
    }

    public static Component resolvePlayerNameOrDefault(ItemStack stack) {
        var playerName = resolvePlayerName(stack);
        return playerName == null ? Component.translatable(PLAYER_KEY) : playerName;
    }
}
