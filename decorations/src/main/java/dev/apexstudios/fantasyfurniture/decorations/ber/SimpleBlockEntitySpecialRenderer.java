package dev.apexstudios.fantasyfurniture.decorations.ber;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Set;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import org.joml.Vector3f;

public final class SimpleBlockEntitySpecialRenderer implements NoDataSpecialModelRenderer {
    private final SimpleBlockEntityModel model;
    private final ResourceLocation texture;

    private SimpleBlockEntitySpecialRenderer(SimpleBlockEntityModel model, ResourceLocation texture) {
        this.model = model;
        this.texture = texture;
    }

    @Override
    public void submit(ItemDisplayContext displayContext, PoseStack poseStack, SubmitNodeCollector nodeCollector, int packedLight, int packedOverlay, boolean hasFoil, int outlineColor) {
        model.submitModel(poseStack, nodeCollector, texture, null, packedLight, null);
    }

    @Override
    public void getExtents(Set<Vector3f> output) {
        var poseStack = new PoseStack();
        model.preparePose(poseStack, null);
        model.model.root().getExtentsForGui(poseStack, output);
    }

    public static abstract class Unbaked implements SpecialModelRenderer.Unbaked {
        private final ModelLayerLocation modelLocation;
        private final ResourceLocation texture;

        private Unbaked(ModelLayerLocation modelLocation, ResourceLocation texture) {
            this.modelLocation = modelLocation;
            this.texture = texture;
        }

        @Override
        public SpecialModelRenderer<?> bake(BakingContext context) {
            return new SimpleBlockEntitySpecialRenderer(new SimpleBlockEntityModel(context.entityModelSet(), modelLocation), texture);
        }
    }

    public static final class WidowBloom extends Unbaked {
        public static final MapCodec<WidowBloom> MAP_CODEC = MapCodec.unit(WidowBloom::new);

        public WidowBloom() {
            super(DecorLayerDefinitions.WIDOW_BLOOM, DecorLayerDefinitions.WIDOW_BLOOM_TEXTURE);
        }

        @Override
        public MapCodec<WidowBloom> type() {
            return MAP_CODEC;
        }
    }

    public static final class SkullBlossom extends Unbaked {
        public static final MapCodec<SkullBlossom> MAP_CODEC = RecordCodecBuilder.mapCodec(builder -> builder.group(
                Codec.BOOL.fieldOf("skeleton").forGetter(model -> model.skeleton)
        ).apply(builder, SkullBlossom::new));

        private final boolean skeleton;

        public SkullBlossom(boolean skeleton) {
            super(DecorLayerDefinitions.SKULL_BLOSSOM, skeleton ? DecorLayerDefinitions.SKULL_BLOSSOM_SKELETON_TEXTURE : DecorLayerDefinitions.SKULL_BLOSSOM_WITHER_TEXTURE);

            this.skeleton = skeleton;
        }

        @Override
        public MapCodec<SkullBlossom> type() {
            return MAP_CODEC;
        }
    }
}
