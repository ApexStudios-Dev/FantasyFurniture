package dev.apexstudios.fantasyfurniture.decorations.plushie;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import java.util.function.Function;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.PlayerSkinRenderCache;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.component.ResolvableProfile;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public final class PlushieBlockEntityRenderer implements BlockEntityRenderer<PlushieBlockEntity, PlushieBlockEntityRenderer.RenderState> {
    private final Function<Boolean, PlushieModel> modelGetter;
    private final PlayerSkinRenderCache playerSkinRenderCache;

    public PlushieBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        playerSkinRenderCache = context.playerSkinRenderCache();
        modelGetter = modelGetter(context.entityModelSet());
    }

    @Override
    public RenderState createRenderState() {
        return new RenderState();
    }

    @Override
    public void extractRenderState(PlushieBlockEntity blockEntity, RenderState renderState, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, renderState, partialTicks, cameraPosition, breakProgress);
        renderState.resolve(blockEntity.getProfile(), playerSkinRenderCache);

        var segment = blockEntity.getBlockState().getValue(PlushieBlock.ROTATION);
        renderState.plushieRenderState.rotation = RotationSegment.convertToDegrees(segment);
        renderState.plushieRenderState.facing = RotationSegment.convertToDirection(segment).orElse(null);
    }

    @Override
    public void submit(RenderState renderState, PoseStack poseStack, SubmitNodeCollector nodes, CameraRenderState cameraRenderState) {
        submitPlushie(null, poseStack, nodes, renderState.lightCoords, renderState.plushieModel, renderState.plushieRenderState, null, 0, renderState.breakProgress);

        /*if(renderState.plushieRenderState.facing != null) {
            Gizmos.billboardTextOverBlock(renderState.plushieRenderState.facing.getSerializedName(), renderState.blockPos, 0, CommonColors.WHITE, .5F);
        }*/
    }

    public static void submitPlushie(@Nullable ItemDisplayContext displayContext, PoseStack poseStack, SubmitNodeCollector nodes, int lightCoords, PlushieModel model, PlushieRenderState plushieRenderState, @Nullable AvatarRenderState avatarRenderState, int outlineColor, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        poseStack.pushPose();
        setupForModel(poseStack, plushieRenderState, displayContext);
        nodes.submitModel(model, new AvatarRenderState(), poseStack, plushieRenderState.renderType, lightCoords, OverlayTexture.NO_OVERLAY, outlineColor, breakProgress);
        poseStack.popPose();
    }

    public static void setupForModel(PoseStack poseStack, PlushieRenderState renderState, @Nullable ItemDisplayContext displayContext) {
        poseStack.translate(.5F, 0F, .5F);

        if(renderState.facing != null) {
            poseStack.translate(renderState.facing.getStepX() * -.175F, 0F, renderState.facing.getStepZ() * -.175F);
        }

        poseStack.mulPose(Axis.YN.rotationDegrees(renderState.rotation));

        poseStack.scale(-1F, -1F, 1F);
        poseStack.scale(.625F, .625F, .625F);
        poseStack.translate(0F, -1F, 0F);

        if(displayContext == ItemDisplayContext.HEAD) {
            poseStack.translate(0F, -1.5F, 0F);
        }
    }

    public static Function<Boolean, PlushieModel> modelGetter(EntityModelSet entityModelSet) {
        return slim -> new PlushieModel(
                entityModelSet.bakeLayer(slim ? ModelLayers.PLAYER_SLIM :  ModelLayers.PLAYER),
                slim
        );
    }

    public final class RenderState extends BlockEntityRenderState {
        public PlushieModel plushieModel = modelGetter.apply(false);
        public final PlushieRenderState plushieRenderState = new PlushieRenderState();

        public void resolve(@Nullable ResolvableProfile profile, PlayerSkinRenderCache playerSkinRenderCache) {
            plushieRenderState.resolve(profile, playerSkinRenderCache);
            plushieModel = modelGetter.apply(plushieRenderState.slim);
        }
    }
}
