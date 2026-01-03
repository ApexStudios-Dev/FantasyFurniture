package dev.apexstudios.fantasyfurniture.decorations.common.ber;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.apexstudios.apexcore.api.block.SimpleHorizontalDirectionalBlock;
import java.util.Objects;
import java.util.function.Function;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public final class SimpleBlockEntityRenderer implements BlockEntityRenderer<BlockEntity, SimpleBlockEntityRenderer.RenderState> {
    private final SimpleBlockEntityModel model;
    private final Function<BlockEntity, Identifier> textureGetter;

    private SimpleBlockEntityRenderer(EntityModelSet models, ModelLayerLocation modelLocation, Function<BlockEntity, Identifier> textureGetter) {
        this.textureGetter = textureGetter;

        model = new SimpleBlockEntityModel(models, modelLocation);
    }

    @Override
    public RenderState createRenderState() {
        return new RenderState();
    }

    @Override
    public void extractRenderState(BlockEntity blockEntity, RenderState renderState, float partialTick, Vec3 cameraPosition, @Nullable ModelFeatureRenderer.CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, renderState, partialTick, cameraPosition, breakProgress);
        renderState.facing = blockEntity.getBlockState().getValue(SimpleHorizontalDirectionalBlock.FACING);
        renderState.texture = textureGetter.apply(blockEntity);
    }

    @Override
    public void submit(RenderState renderState, PoseStack poseStack, SubmitNodeCollector nodeCollector, CameraRenderState cameraRenderState) {
        model.submitModel(poseStack, nodeCollector, Objects.requireNonNullElseGet(renderState.texture, MissingTextureAtlasSprite::getLocation), renderState.facing, renderState.lightCoords, renderState.breakProgress);
    }

    public static BlockEntityRendererProvider<BlockEntity, RenderState> create(ModelLayerLocation modelLocation, Function<BlockEntity, Identifier> textureGetter) {
        return context -> new SimpleBlockEntityRenderer(context.entityModelSet(), modelLocation, textureGetter);
    }

    public static final class RenderState extends BlockEntityRenderState {
        @Nullable public Direction facing;
        @Nullable public Identifier texture;
    }
}
