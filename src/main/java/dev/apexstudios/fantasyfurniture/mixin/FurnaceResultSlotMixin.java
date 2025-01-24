package dev.apexstudios.fantasyfurniture.mixin;

import dev.apexstudios.fantasyfurniture.oven.OvenMenuContainer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.FurnaceResultSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FurnaceResultSlot.class)
public class FurnaceResultSlotMixin {
    @Shadow @Final private Player player;

    @Inject(
            method = "checkTakeAchievements",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;onCraftedBy(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;I)V",
                    shift = At.Shift.AFTER
            )
    )
    private void FantasyFurniture$checkTakeAchievements(ItemStack stack, CallbackInfo ci) {
        var self = (FurnaceResultSlot) (Object) this;

        if(player instanceof ServerPlayer player && self.container instanceof OvenMenuContainer oven)
            oven.awardUsedRecipesAndPopExperience(player);
    }
}
