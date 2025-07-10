package dev.apexstudios.fantasyfurniture.decorations.data;

import dev.apexstudios.fantasyfurniture.decorations.DecorationsModule;
import java.util.function.UnaryOperator;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplateBuilder;

public interface DecorationModels {
    ModelTemplate BERRY_BASKET = ExtendedModelTemplateBuilder.builder()
            .parent(blockModel("berry_basket"))
            .requiredTextureSlot(Slots.BERRY_BASKET)
            .build();

    static void berryBasket(Block block, BlockModelGenerators blockModels, boolean needsRenderType) {
        blockModels.createHorizontallyRotatedBlock(block, Textured.BERRY_BASKET.updateTemplate(withOptionalCutout(needsRenderType)));
    }

    static UnaryOperator<ModelTemplate> withOptionalCutout(boolean needsRenderType) {
        return needsRenderType ? DecorationModels::withCutout : UnaryOperator.identity();
    }

    static ModelTemplate withCutout(ModelTemplate template) {
        return template.extend().renderType("cutout").build();
    }

    static ResourceLocation blockModel(String path) {
        return DecorationsModule.REGISTREE.registryName("block/" + path);
    }

    interface Slots {
        TextureSlot BERRY_BASKET = TextureSlot.create("berry_basket");
    }

    interface Textured {
        TexturedModel.Provider BERRY_BASKET = simple(Slots.BERRY_BASKET, DecorationModels.BERRY_BASKET);

        private static TexturedModel.Provider simple(TextureSlot slot, ModelTemplate template) {
            return TexturedModel.createDefault(block -> new TextureMapping().put(slot, TextureMapping.getBlockTexture(block)), template);
        }
    }
}
