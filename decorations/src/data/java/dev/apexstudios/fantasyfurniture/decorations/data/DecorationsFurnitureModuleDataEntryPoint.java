package dev.apexstudios.fantasyfurniture.decorations.data;

import dev.apexstudios.apexcore.lib.data.ProviderTypes;
import dev.apexstudios.apexcore.lib.data.ResourceGenerator;
import dev.apexstudios.apexcore.lib.data.provider.RecipeProvider;
import dev.apexstudios.fantasyfurniture.decorations.DecorationsFurnitureModule;
import dev.apexstudios.fantasyfurniture.station.FurnitureStationRecipeBuilder;
import dev.apexstudios.fantasyfurniture.station.FurnitureStationSetup;
import dev.apexstudios.registree.api.holder.DeferredBlock;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplateBuilder;
import net.neoforged.neoforge.common.Tags;

@Mod(DecorationsFurnitureModule.ID)
public final class DecorationsFurnitureModuleDataEntryPoint {
    public DecorationsFurnitureModuleDataEntryPoint(IEventBus modBus) {
        ResourceGenerator.of(modBus, generator -> generator
                .pack()
                .providing(ProviderTypes.MODELS, (context, models) -> {
                    models.fromRegistree(DecorationsFurnitureModule.REGISTREE);

                    var blockModels = models.blockModels();

                    berryBasket(DecorationsFurnitureModule.BERRY_BASKET, blockModels);
                    berryBasket(DecorationsFurnitureModule.BLUEBERRY_BASKET, blockModels);
                    berryBasket(DecorationsFurnitureModule.STRAWBERRY_BASKET, blockModels);
                    berryBasket(DecorationsFurnitureModule.SWEETBERRY_BASKET, blockModels);
                    existingHorizontalModel(DecorationsFurnitureModule.BOLTS_OF_CLOTH, blockModels);
                    bowl(DecorationsFurnitureModule.BOWL, blockModels);
                    bowl(DecorationsFurnitureModule.BEETROOT_SOUP_BOWL, blockModels);
                    bowl(DecorationsFurnitureModule.MUSHROOM_STEW_BOWL, blockModels);
                    coinStack(DecorationsFurnitureModule.GOLDEN_COIN_STACK, blockModels);
                    coinStack(DecorationsFurnitureModule.IRON_COIN_STACK, blockModels);
                })
                .providing(ProviderTypes.LANGUAGE, (context, provider) -> {
                    provider.addCreativeModeTab(DecorationsFurnitureModule.CREATIVE_MODE_TAB, "Fantasy's Furniture - Decorations");

                    provider.addBlock(DecorationsFurnitureModule.BERRY_BASKET, "Berry Basket");
                    provider.addBlock(DecorationsFurnitureModule.BLUEBERRY_BASKET, "Blueberry Basket");
                    provider.addBlock(DecorationsFurnitureModule.STRAWBERRY_BASKET, "Strawberry Basket");
                    provider.addBlock(DecorationsFurnitureModule.SWEETBERRY_BASKET, "Sweetberry Basket");
                    provider.addBlock(DecorationsFurnitureModule.BOLTS_OF_CLOTH, "Bolts of Cloth");
                    provider.addBlock(DecorationsFurnitureModule.BOWL, "Bowl");
                    provider.addBlock(DecorationsFurnitureModule.BEETROOT_SOUP_BOWL, "Beetroot Soup Bowl");
                    provider.addBlock(DecorationsFurnitureModule.MUSHROOM_STEW_BOWL, "Mushroom Stew Bowl");
                    provider.addBlock(DecorationsFurnitureModule.GOLDEN_COIN_STACK, "Golden Coin Stack");
                    provider.addBlock(DecorationsFurnitureModule.IRON_COIN_STACK, "Iron Coin Stack");
                })
                .providing(ProviderTypes.RECIPES, (context, provider) -> DecorationsFurnitureModule.REGISTREE
                        .asLookup(Registries.ITEM)
                        .filterFeatures(context.enabledFeatures())
                        .listElements()
                        .map(Holder::value)
                        .forEach(item -> furnitureStation(item, provider))
                )
                .providing(ProviderTypes.LOOT_TABLE, (context, provider) -> {
                    provider.fromRegistree(DecorationsFurnitureModule.REGISTREE);

                    provider.block(lootTables -> {
                        lootTables.dropSelf(DecorationsFurnitureModule.BERRY_BASKET.value());
                        lootTables.dropSelf(DecorationsFurnitureModule.BLUEBERRY_BASKET.value());
                        lootTables.dropSelf(DecorationsFurnitureModule.STRAWBERRY_BASKET.value());
                        lootTables.dropSelf(DecorationsFurnitureModule.SWEETBERRY_BASKET.value());
                        lootTables.dropSelf(DecorationsFurnitureModule.BOLTS_OF_CLOTH.value());
                        lootTables.dropSelf(DecorationsFurnitureModule.BOWL.value());
                        lootTables.dropSelf(DecorationsFurnitureModule.BEETROOT_SOUP_BOWL.value());
                        lootTables.dropSelf(DecorationsFurnitureModule.MUSHROOM_STEW_BOWL.value());
                        lootTables.dropSelf(DecorationsFurnitureModule.GOLDEN_COIN_STACK.value());
                        lootTables.dropSelf(DecorationsFurnitureModule.IRON_COIN_STACK.value());
                    });
                })
                .providing(ProviderTypes.BLOCK_TAGS, (context, provider) -> {
                    provider.tag(BlockTags.MINEABLE_WITH_AXE)
                            .withElement(DecorationsFurnitureModule.BERRY_BASKET)
                            .withElement(DecorationsFurnitureModule.BLUEBERRY_BASKET)
                            .withElement(DecorationsFurnitureModule.STRAWBERRY_BASKET)
                            .withElement(DecorationsFurnitureModule.SWEETBERRY_BASKET)
                            .withElement(DecorationsFurnitureModule.BOLTS_OF_CLOTH)
                            .withElement(DecorationsFurnitureModule.BOWL)
                            .withElement(DecorationsFurnitureModule.BEETROOT_SOUP_BOWL)
                            .withElement(DecorationsFurnitureModule.MUSHROOM_STEW_BOWL)
                            .withElement(DecorationsFurnitureModule.GOLDEN_COIN_STACK)
                            .withElement(DecorationsFurnitureModule.IRON_COIN_STACK);
                })
        );
    }

    private void existingModel(DeferredBlock<? extends Block> holder, BlockModelGenerators blockModels, BlockStateGenerator blockStateGenerator) {
        blockStateGenerator.accept(holder, assetPath(holder), blockModels);
    }

    private void existingHorizontalModel(DeferredBlock<? extends Block> holder, BlockModelGenerators blockModels) {
        existingModel(holder, blockModels, this::horizontalFacingBlock);
    }

    private void existingTemplate(ResourceLocation templatePath, DeferredBlock<? extends Block> holder, String textureSlot, boolean overrideParticle, BlockModelGenerators blockModels, BlockStateGenerator blockStateGenerator) {
        var slot = TextureSlot.create(textureSlot);
        var assetPath = assetPath(holder);
        var textures = new TextureMapping().put(slot, assetPath);
        var templateBuilder = ExtendedModelTemplateBuilder.builder().parent(templatePath).requiredTextureSlot(slot);

        if(overrideParticle) {
            textures = textures.put(TextureSlot.PARTICLE, assetPath.withSuffix("_particle"));
            templateBuilder = templateBuilder.requiredTextureSlot(TextureSlot.PARTICLE);
        }

        blockStateGenerator.accept(
                holder,
                templateBuilder.build().create(holder.value(), textures, blockModels.modelOutput),
                blockModels
        );
    }

    private void existingTemplateHorizontal(ResourceLocation templatePath, DeferredBlock<? extends Block> holder, String textureSlot, boolean overrideParticle, BlockModelGenerators blockModels) {
        existingTemplate(templatePath, holder, textureSlot, overrideParticle, blockModels, this::horizontalFacingBlock);
    }

    private void existingTemplate(DeferredBlock<? extends Block> templateHolder, DeferredBlock<? extends Block> holder, String textureSlot, boolean overrideParticle, BlockModelGenerators blockModels, BlockStateGenerator blockStateGenerator) {
        var template = assetPath(templateHolder);

        if(holder.is(templateHolder)) {
            blockStateGenerator.accept(holder, template, blockModels);
        } else {
            existingTemplate(template, holder, textureSlot, overrideParticle, blockModels, blockStateGenerator);
        }
    }

    private void existingTemplateHorizontal(DeferredBlock<? extends Block> templateHolder, DeferredBlock<? extends Block> holder, String textureSlot, boolean overrideParticle, BlockModelGenerators blockModels) {
        existingTemplate(templateHolder, holder, textureSlot, overrideParticle, blockModels, this::horizontalFacingBlock);
    }

    private void berryBasket(DeferredBlock<? extends Block> holder, BlockModelGenerators blockModels) {
        existingTemplateHorizontal(DecorationsFurnitureModule.BERRY_BASKET, holder, "berry_basket", false, blockModels);
    }

    private void bowl(DeferredBlock<? extends Block> holder, BlockModelGenerators blockModels) {
        existingTemplateHorizontal(DecorationsFurnitureModule.BOWL, holder, "bowl", false, blockModels);
    }

    private void coinStack(DeferredBlock<? extends Block> holder, BlockModelGenerators blockModels) {
        existingTemplateHorizontal(DecorationsFurnitureModule.identifier("block/coin_stack"), holder, "coin_stack", true, blockModels);
    }

    private void horizontalFacingBlock(Holder<? extends Block> block, ResourceLocation model, BlockModelGenerators blockModels) {
        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block.value(), BlockModelGenerators.plainVariant(model)).with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING));
    }

    private ResourceLocation assetPath(DeferredBlock<? extends Block> block) {
        return block.getId().withPrefix("block/");
    }

    private void furnitureStation(Item item, RecipeProvider provider) {
        FurnitureStationRecipeBuilder
                .builder(RecipeCategory.DECORATIONS, provider.tag(Tags.Items.DYES), null, provider.tag(FurnitureStationSetup.BINDING_AGENT), item)
                .unlockedBy(RecipeProvider.getHasName(Tags.Items.DYES), provider.has(Tags.Items.DYES))
                .save(provider.output(), RecipeProvider.recipeKeyWithPrefix(item, "furniture_station/"));
    }

    @FunctionalInterface
    interface BlockStateGenerator {
        void accept(Holder<Block> holder, ResourceLocation model, BlockModelGenerators blockModels);
    }
}
