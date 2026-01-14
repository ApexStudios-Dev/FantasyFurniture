package dev.apexstudios.fantasyfurniture.decorations.common.plushie;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import java.util.function.Function;
import net.minecraft.client.Minecraft;
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
import net.minecraft.network.chat.Component;
import net.minecraft.util.Util;
import net.minecraft.world.entity.player.PlayerModelType;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public final class PlushieBlockEntityRenderer implements BlockEntityRenderer<PlushieBlockEntity, PlushieBlockEntityRenderer.RenderState> {
    private final Function<PlayerModelType, PlushieModel> modelGetter;
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

        var profile = blockEntity.getProfile();
        var segment = blockEntity.getBlockState().getValue(PlushieBlock.ROTATION);
        var pos = blockEntity.getBlockPos();

        renderState.plushieRenderState.resolve(profile, playerSkinRenderCache);
        renderState.plushieRenderState.rotation = RotationSegment.convertToDegrees(segment);
        renderState.plushieRenderState.facing = RotationSegment.convertToDirection(segment).orElse(null);
        renderState.distanceToCameraSq = cameraPosition.distanceToSqr(Vec3.atCenterOf(pos));

        if(blockEntity.shouldRenderName() && profile != null && Minecraft.getInstance().hitResult instanceof BlockHitResult hitResult && hitResult.getBlockPos().equals(pos)) {
            renderState.playerName = profile.name().map(Component::literal).orElse(null);
        }
    }

    @Override
    public void submit(RenderState renderState, PoseStack poseStack, SubmitNodeCollector nodes, CameraRenderState cameraRenderState) {
        submitPlushie(null, poseStack, nodes, renderState.lightCoords, modelGetter.apply(renderState.plushieRenderState.skin.model()), renderState.plushieRenderState, 0, renderState.breakProgress);

        if(renderState.playerName != null) {
            nodes.submitNameTag(poseStack, new Vec3(.5D, .65D, .5D), 0, renderState.playerName, true, renderState.lightCoords, renderState.distanceToCameraSq, cameraRenderState);
        }

        /*if(renderState.plushieRenderState.facing != null) {
            Gizmos.billboardTextOverBlock(renderState.plushieRenderState.facing.getSerializedName(), renderState.blockPos, 0, CommonColors.WHITE, .5F);
        }*/
    }

    public static void submitPlushie(@Nullable ItemDisplayContext displayContext, PoseStack poseStack, SubmitNodeCollector nodes, int lightCoords, PlushieModel model, PlushieRenderState plushieRenderState, int outlineColor, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
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

    public static Function<PlayerModelType, PlushieModel> modelGetter(EntityModelSet entityModelSet) {
        return Util.memoize(modelType -> {
            var slim = modelType == PlayerModelType.SLIM;
            return new PlushieModel(entityModelSet.bakeLayer(slim ? ModelLayers.PLAYER_SLIM : ModelLayers.PLAYER), slim);
        });
    }

    public static final class RenderState extends BlockEntityRenderState {
        public final PlushieRenderState plushieRenderState = new PlushieRenderState();
        @Nullable public Component playerName = null;
        public double distanceToCameraSq = 0D;
    }
}
