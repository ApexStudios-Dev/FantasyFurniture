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

                    berryBasket(DecorationsFurnitureModule.BERRY_BASKET, blockModels, true);
                    berryBasket(DecorationsFurnitureModule.BLUEBERRY_BASKET, blockModels, false);
                    berryBasket(DecorationsFurnitureModule.STRAWBERRY_BASKET, blockModels, false);
                    berryBasket(DecorationsFurnitureModule.SWEETBERRY_BASKET, blockModels, false);

                    existingHorizontalModel(DecorationsFurnitureModule.BOLTS_OF_CLOTH, blockModels);
                })
                .providing(ProviderTypes.LANGUAGE, (context, provider) -> {
                    provider.addCreativeModeTab(DecorationsFurnitureModule.CREATIVE_MODE_TAB, "Fantasy's Furniture - Decorations");

                    provider.addBlock(DecorationsFurnitureModule.BERRY_BASKET, "Berry Basket");
                    provider.addBlock(DecorationsFurnitureModule.BLUEBERRY_BASKET, "Blueberry Basket");
                    provider.addBlock(DecorationsFurnitureModule.STRAWBERRY_BASKET, "Strawberry Basket");
                    provider.addBlock(DecorationsFurnitureModule.SWEETBERRY_BASKET, "Sweetberry Basket");
                    provider.addBlock(DecorationsFurnitureModule.BOLTS_OF_CLOTH, "Bolts of Cloth");
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
                    });
                })
                .providing(ProviderTypes.BLOCK_TAGS, (context, provider) -> {
                    provider.tag(BlockTags.MINEABLE_WITH_AXE)
                            .withElement(DecorationsFurnitureModule.BERRY_BASKET)
                            .withElement(DecorationsFurnitureModule.BLUEBERRY_BASKET)
                            .withElement(DecorationsFurnitureModule.STRAWBERRY_BASKET)
                            .withElement(DecorationsFurnitureModule.SWEETBERRY_BASKET)
                            .withElement(DecorationsFurnitureModule.BOLTS_OF_CLOTH);
                })
        );
    }

    private void existingModel(DeferredBlock<? extends Block> holder, BlockModelGenerators blockModels, BlockStateGenerator blockStateGenerator) {
        blockStateGenerator.accept(holder, assetPath(holder), blockModels);
    }

    private void existingHorizontalModel(DeferredBlock<? extends Block> holder, BlockModelGenerators blockModels) {
        existingModel(holder, blockModels, this::horizontalFacingBlock);
    }

    private void berryBasket(DeferredBlock<? extends Block> holder, BlockModelGenerators blockModels, boolean isEmpty) {
        var template = assetPath(DecorationsFurnitureModule.BERRY_BASKET);
        var model = template;

        if(!isEmpty) {
            var slot = TextureSlot.create("berry_basket");
            var assetPath = assetPath(holder);

            var textures = new TextureMapping().put(slot, assetPath);

            model = ExtendedModelTemplateBuilder.builder()
                                                .parent(template)
                                                .requiredTextureSlot(slot)
                                                .build()
                                                .create(holder.value(), textures, blockModels.modelOutput);
        }

        horizontalFacingBlock(holder, model, blockModels);
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
