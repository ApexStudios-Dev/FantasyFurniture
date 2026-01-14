package dev.apexstudios.fantasyfurniture.decorations.common.plushie;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import dev.apexstudios.fantasyfurniture.decorations.common.DecorationsFurnitureModule;
import java.util.function.Consumer;
import java.util.function.Function;
import net.minecraft.client.renderer.PlayerSkinRenderCache;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.player.PlayerModelType;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector3fc;

public final class PlushieSpecialModelRenderer implements SpecialModelRenderer<PlushieRenderState> {
    private final Function<PlayerModelType, PlushieModel> modelGetter;
    private final PlayerSkinRenderCache playerSkinRenderCache;

    private PlushieSpecialModelRenderer(BakingContext context) {
        playerSkinRenderCache = context.playerSkinRenderCache();
        modelGetter = PlushieBlockEntityRenderer.modelGetter(context.entityModelSet());
    }

    @Override
    public void submit(PlushieRenderState renderState, ItemDisplayContext type, PoseStack poseStack, SubmitNodeCollector nodes, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {
        var model = modelGetter.apply(renderState.skin.model());
        PlushieBlockEntityRenderer.submitPlushie(type, poseStack, nodes, lightCoords, model, renderState, outlineColor, null);
    }

    @Override
    public PlushieRenderState extractArgument(ItemStack stack) {
        var renderState = new PlushieRenderState();
        renderState.resolve(stack.get(DataComponents.PROFILE), playerSkinRenderCache);
        return renderState;
    }

    @Override
    public void getExtents(Consumer<Vector3fc> output) {
        var renderState = extractArgument(DecorationsFurnitureModule.PLUSHIE_BLOCK.toStack());
        var model = modelGetter.apply(renderState.skin.model());
        var poseStack = new PoseStack();
        PlushieBlockEntityRenderer.setupForModel(poseStack, renderState, null);
        model.setupAnim(new AvatarRenderState());
        model.root().getExtentsForGui(poseStack, output);
    }

    public record Unbaked() implements SpecialModelRenderer.Unbaked {
        public static final MapCodec<Unbaked> MAP_CODEC = MapCodec.unit(Unbaked::new);

        @Override
        public PlushieSpecialModelRenderer bake(BakingContext context) {
            return new PlushieSpecialModelRenderer(context);
        }

        @Override
        public MapCodec<Unbaked> type() {
            return MAP_CODEC;
        }
    }
}
