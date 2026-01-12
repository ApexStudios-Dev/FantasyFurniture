package dev.apexstudios.fantasyfurniture.decorations.common.plushie;

import net.minecraft.client.renderer.PlayerSkinRenderCache;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.PlayerModelType;
import net.minecraft.world.item.component.ResolvableProfile;
import net.minecraft.world.level.block.SkullBlock;
import org.jspecify.annotations.Nullable;

public final class PlushieRenderState {
    public RenderType renderType = SkullBlockRenderer.getSkullRenderType(SkullBlock.Types.PLAYER, null);
    public boolean slim = false;
    public float rotation = 0F;
    @Nullable public Direction facing = null;

    public void resolve(@Nullable ResolvableProfile profile, PlayerSkinRenderCache playerSkinRenderCache) {
        if(profile == null) {
            return;
        }

        var skin = playerSkinRenderCache.getOrDefault(profile);
        renderType = skin.renderType();
        slim = skin.playerSkin().model() == PlayerModelType.SLIM;
    }
}
