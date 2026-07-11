package dev.apexstudios.fantasyfurniture.bone.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.apexstudios.fantasyfurniture.bone.common.WitherFurnitureSet;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.entity.SignText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SignBlockEntity.class)
public class SignBlockEntityMixin {
    @ModifyReturnValue(
            method = "createDefaultSignText",
            at = @At("RETURN")
    )
    private SignText FantasyFurniture_Bone$createDefaultSignText(SignText original) {
        var self = SignBlockEntity.class.cast(this);
        var blockState = self.getBlockState();

        if(WitherFurnitureSet.FURNITURE_SET.sign.isEither(blockState) || WitherFurnitureSet.FURNITURE_SET.hangingSign.isEither(blockState)) {
            return new SignText(
                    new Component[] { CommonComponents.EMPTY, CommonComponents.EMPTY, CommonComponents.EMPTY, CommonComponents.EMPTY },
                    new Component[] { CommonComponents.EMPTY, CommonComponents.EMPTY, CommonComponents.EMPTY, CommonComponents.EMPTY },
                    DyeColor.WHITE,
                    false
            );
        }

        return original;
    }
}
