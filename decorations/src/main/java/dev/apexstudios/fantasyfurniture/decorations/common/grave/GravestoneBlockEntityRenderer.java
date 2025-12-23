package dev.apexstudios.fantasyfurniture.decorations.common.grave;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mojang.math.Transformation;
import java.util.List;
import java.util.function.Function;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.AbstractSignRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.SignRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.core.Direction;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Util;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.SignText;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.jspecify.annotations.Nullable;

public final class GravestoneBlockEntityRenderer implements BlockEntityRenderer<GravestoneBlockEntity, SignRenderState> {
    private static final Function<Direction, SignRenderState.SignTransformations> TRANSFORMATIONS = Util.memoize(facing -> new SignRenderState.SignTransformations(Transformation.IDENTITY, textTransformation(facing, true), textTransformation(facing, false)));

    private final Font font;

    public GravestoneBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.font = context.font();
    }

    @Override
    public SignRenderState createRenderState() {
        return new SignRenderState();
    }

    @Override
    public void extractRenderState(GravestoneBlockEntity blockEntity, SignRenderState renderState, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, renderState, partialTicks, cameraPosition, breakProgress);

        renderState.maxTextLineWidth = blockEntity.getMaxTextLineWidth();
        renderState.textLineHeight = blockEntity.getTextLineHeight();
        renderState.frontText = blockEntity.getFrontText();
        renderState.backText = blockEntity.getBackText();
        renderState.isTextFilteringEnabled = Minecraft.getInstance().isTextFilteringEnabled();
        renderState.drawOutline = AbstractSignRenderer.isOutlineVisible(blockEntity.getBlockPos());
        // renderState.woodType = SignBlock.getWoodType(blockEntity.getBlockState().getBlock());
        renderState.transformations = TRANSFORMATIONS.apply(blockEntity.getBlockState().getValue(GravestoneBlock.FACING));
    }

    @Override
    public void submit(SignRenderState renderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        submitSignWithText(renderState, poseStack, submitNodeCollector);
    }

    private void submitSignWithText(SignRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector) {
        poseStack.pushPose();
        poseStack.mulPose(state.transformations.body());
        poseStack.popPose();

        if (state.frontText != null) {
            poseStack.pushPose();
            poseStack.mulPose(state.transformations.frontText());
            submitSignText(state, poseStack, submitNodeCollector, state.frontText);
            poseStack.popPose();
        }

        if (state.backText != null) {
            poseStack.pushPose();
            poseStack.mulPose(state.transformations.backText());
            submitSignText(state, poseStack, submitNodeCollector, state.backText);
            poseStack.popPose();
        }
    }

    private void submitSignText(SignRenderState renderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, SignText signText) {
        var darkColor = AbstractSignRenderer.getDarkColor(signText);
        var signMidpoint = 4 * renderState.textLineHeight / 2;
        var formattedLines = signText.getRenderMessages(renderState.isTextFilteringEnabled, (input) -> {
            List<FormattedCharSequence> components = this.font.split(input, renderState.maxTextLineWidth);
            return components.isEmpty() ? FormattedCharSequence.EMPTY : components.getFirst();
        });

        int textColor;
        boolean drawOutline;
        int lightVal;

        if (signText.hasGlowingText()) {
            textColor = signText.getColor().getTextColor();
            drawOutline = textColor == DyeColor.BLACK.getTextColor() || renderState.drawOutline;
            lightVal = 15728880;
        } else {
            textColor = darkColor;
            drawOutline = false;
            lightVal = renderState.lightCoords;
        }

        for(var i = 0; i < 4; ++i) {
            var actualLine = formattedLines[i];
            var x1 = (float)(-this.font.width(actualLine) / 2);
            submitNodeCollector.submitText(poseStack, x1, (float)(i * renderState.textLineHeight - signMidpoint), actualLine, false, Font.DisplayMode.POLYGON_OFFSET, lightVal, textColor, 0, drawOutline ? darkColor : 0);
        }
    }

    private static Transformation textTransformation(Direction facing, boolean isFrontText) {
        var result = new Matrix4f()
                .translate(.5F, .25F, .5F)
                .translate(0F, 0F, 0F)
                .rotate(Axis.YP.rotationDegrees(-facing.toYRot()));

        if (!isFrontText) {
            result.rotate(Axis.YP.rotationDegrees(180F));
        }

        return new Transformation(result.translate(new Vector3f(0F, 0.25F, 0.0625F)).scale(0.010416667F, -0.010416667F, 0.010416667F));
    }
}
