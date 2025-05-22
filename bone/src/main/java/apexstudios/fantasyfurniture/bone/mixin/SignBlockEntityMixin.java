package apexstudios.fantasyfurniture.bone.mixin;

import apexstudios.fantasyfurniture.bone.WitherFurnitureSet;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
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
        var wither = WitherFurnitureSet.get();
        return wither.sign.sign().is(blockState) || wither.sign.wall().is(blockState) || wither.hangingSign.sign().is(blockState) || wither.hangingSign.wall().is(blockState) ? new SignText(
                new Component[] { CommonComponents.EMPTY, CommonComponents.EMPTY, CommonComponents.EMPTY, CommonComponents.EMPTY },
                new Component[] { CommonComponents.EMPTY, CommonComponents.EMPTY, CommonComponents.EMPTY, CommonComponents.EMPTY },
                DyeColor.WHITE,
                false
        ) : original;
    }
}
