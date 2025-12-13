package dev.apexstudios.fantasyfurniture.decorations.grave;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.AbstractSignRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.blockentity.state.SignRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public final class GravestoneBlockEntityRenderer implements BlockEntityRenderer<GravestoneBlockEntity, SignRenderState> {
    private static final Vec3 TEXT_OFFSET = new Vec3(0D, 0D, .063D);

    private final Font font;

    public GravestoneBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        font = context.font();
    }

    @Override
    public SignRenderState createRenderState() {
        return new SignRenderState();
    }

    @Override
    public void extractRenderState(GravestoneBlockEntity blockEntity, SignRenderState renderState, float partialTick, Vec3 cameraPosition, @Nullable ModelFeatureRenderer.CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, renderState, partialTick, cameraPosition, breakProgress);

        renderState.maxTextLineWidth = blockEntity.getMaxTextLineWidth();
        renderState.textLineHeight = blockEntity.getTextLineHeight();
        renderState.frontText = blockEntity.getFrontText();
        renderState.backText = blockEntity.getBackText();
        renderState.isTextFilteringEnabled = Minecraft.getInstance().isTextFilteringEnabled();
        renderState.drawOutline = AbstractSignRenderer.isOutlineVisible(blockEntity.getBlockPos());
    }

    @Override
    public void submit(SignRenderState renderState, PoseStack poseStack, SubmitNodeCollector nodeCollector, CameraRenderState cameraRenderState) {
        var blockstate = renderState.blockState;
        var block = (GravestoneBlock) blockstate.getBlock();
        submitSignWithText(renderState, poseStack, blockstate, block, nodeCollector);
    }

    private void submitSignWithText(SignRenderState renderState, PoseStack poseStack, BlockState blockState, GravestoneBlock block, SubmitNodeCollector nodeCollector) {
        poseStack.pushPose();
        SignRenderer.translateBase(poseStack, -block.getYRotationDegrees(blockState));
        submitSignText(renderState, poseStack, nodeCollector, true);
        submitSignText(renderState, poseStack, nodeCollector, false);
        poseStack.popPose();
    }

    private void submitSignText(SignRenderState renderState, PoseStack poseStack, SubmitNodeCollector nodeCollector, boolean isFront) {
        var text = isFront ? renderState.frontText : renderState.backText;
        if (text != null) {
            poseStack.pushPose();
            translateSignText(poseStack, isFront, new Vec3(0D, 0D, .063D));
            var i = AbstractSignRenderer.getDarkColor(text);
            var j = 4 * renderState.textLineHeight / 2;

            var lines = text.getRenderMessages(renderState.isTextFilteringEnabled, line -> {
                var list = font.split(line, renderState.maxTextLineWidth);
                return list.isEmpty() ? FormattedCharSequence.EMPTY : list.getFirst();
            });

            int color;
            boolean flag;
            int l;

            if (text.hasGlowingText()) {
                color = text.getColor().getTextColor();
                flag = color == DyeColor.BLACK.getTextColor() || renderState.drawOutline;
                l = 15728880;
            } else {
                color = i;
                flag = false;
                l = renderState.lightCoords;
            }

            for (var i1 = 0; i1 < 4; i1++) {
                var line = lines[i1];
                var f = -font.width(line) / 2F;
                nodeCollector.submitText(poseStack, f, i1 * renderState.textLineHeight - j, line, false, Font.DisplayMode.POLYGON_OFFSET, l, color, 0, flag ? i : 0);
            }

            poseStack.popPose();
        }
    }

    private void translateSignText(PoseStack poseStack, boolean isFront, Vec3 offset) {
        if (!isFront) {
            poseStack.mulPose(Axis.YP.rotationDegrees(180F));
        }

        var scale = .015625F * SignRenderer.RENDER_SCALE;
        poseStack.translate(offset);
        poseStack.scale(scale, -scale, scale);
    }
}
