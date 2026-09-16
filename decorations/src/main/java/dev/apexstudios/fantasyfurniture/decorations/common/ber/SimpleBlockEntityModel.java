package dev.apexstudios.fantasyfurniture.decorations.common.ber;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Unit;
import org.jspecify.annotations.Nullable;

public final class SimpleBlockEntityModel {
    public final Model.Simple model;

    public SimpleBlockEntityModel(EntityModelSet models, ModelLayerLocation modelLocation) {
        model = new Model.Simple(models.bakeLayer(modelLocation), RenderTypes::entityTranslucent);
    }

    public void submitModel(PoseStack poseStack, SubmitNodeCollector nodeCollector, Identifier texture, @Nullable Direction facing, int packedLight, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        poseStack.pushPose();
        preparePose(poseStack, facing);

        var renderType = model.renderType(texture);

        nodeCollector.submitModel(
                model,
                Unit.INSTANCE,
                poseStack,
                renderType,
                packedLight,
                OverlayTexture.NO_OVERLAY,
                0
        );

        if(breakProgress != null) {
            nodeCollector.submitCrumblingOverlay(
                    model,
                    Unit.INSTANCE,
                    poseStack,
                    renderType,
                    packedLight,
                    OverlayTexture.NO_OVERLAY,
                    -1,
                    breakProgress
            );
        }

        poseStack.popPose();
    }

    public void preparePose(PoseStack poseStack, @Nullable Direction facing) {
        poseStack.translate(0D, 1D, 0D);
        poseStack.translate(.5D, .5D, .5D);
        poseStack.rotate(Axis.ZP, 180F);

        if(facing != null) {
            poseStack.rotate(Axis.YP, facing.toYRot());
        }
    }
}
