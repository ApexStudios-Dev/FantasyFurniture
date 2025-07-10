package dev.apexstudios.fantasyfurniture.decorations.data;

import dev.apexstudios.apexcore.lib.data.ProviderTypes;
import dev.apexstudios.apexcore.lib.data.ResourceGenerator;
import dev.apexstudios.apexcore.lib.data.provider.RecipeProvider;
import dev.apexstudios.apexcore.lib.data.provider.context.ProviderListenerContext;
import dev.apexstudios.fantasyfurniture.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.decorations.DecorationsModule;
import dev.apexstudios.fantasyfurniture.station.FurnitureStationRecipeBuilder;
import dev.apexstudios.fantasyfurniture.station.FurnitureStationSetup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.NeoForgeConditions;

@Mod(DecorationsModule.ID)
public final class DecorationsModuleDataEntryPoint {
    public DecorationsModuleDataEntryPoint(IEventBus modBus) {
        ResourceGenerator.of(modBus, generator -> generator.pack()
                .providing(ProviderTypes.LANGUAGE, (context, provider) -> provider
                        .addCreativeModeTab(DecorationsModule.CREATIVE_MODE_TAB, "Fantasy's Furniture - Decorations")
                        .addBlock(DecorationsModule.EMPTY_BERRY_BASKET, "Empty Berry Basket")
                        .addBlock(DecorationsModule.BLUEBERRY_BASKET, "Blueberry Basket")
                        .addBlock(DecorationsModule.STRAWBERRY_BASKET, "Strawberry Basket")
                        .addBlock(DecorationsModule.SWEETBERRY_BASKET, "Sweet Berry Basket")
                )
                .providing(ProviderTypes.MODELS, (context, provider) -> {
                    provider.fromRegistree(DecorationsModule.REGISTREE);
                    var blockModels = provider.blockModels();

                    DecorationModels.berryBasket(DecorationsModule.EMPTY_BERRY_BASKET.value(), blockModels, true);
                    DecorationModels.berryBasket(DecorationsModule.BLUEBERRY_BASKET.value(), blockModels, false);
                    DecorationModels.berryBasket(DecorationsModule.STRAWBERRY_BASKET.value(), blockModels, false);
                    DecorationModels.berryBasket(DecorationsModule.SWEETBERRY_BASKET.value(), blockModels, false);
                })
                .providing(ProviderTypes.LOOT_TABLE, (context, provider) -> provider
                        .fromRegistree(DecorationsModule.REGISTREE)
                        .block(blocks -> {
                            blocks.dropSelf(DecorationsModule.EMPTY_BERRY_BASKET.value());
                            blocks.dropSelf(DecorationsModule.BLUEBERRY_BASKET.value());
                            blocks.dropSelf(DecorationsModule.STRAWBERRY_BASKET.value());
                            blocks.dropSelf(DecorationsModule.SWEETBERRY_BASKET.value());
                        })
                )
                .providing(ProviderTypes.BLOCK_TAGS, (context, provider) -> {
                    provider.tag(BlockTags.MINEABLE_WITH_AXE)
                            .withElement(DecorationsModule.EMPTY_BERRY_BASKET)
                            .withElement(DecorationsModule.BLUEBERRY_BASKET)
                            .withElement(DecorationsModule.STRAWBERRY_BASKET)
                            .withElement(DecorationsModule.SWEETBERRY_BASKET);
                })
                /*.providing(ProviderTypes.ITEM_TAGS, (context, provider) -> {

                })*/
                .providing(ProviderTypes.RECIPES, (context, provider) -> {
                    furnitureStation(DecorationsModule.EMPTY_BERRY_BASKET, context, provider);
                    furnitureStation(DecorationsModule.BLUEBERRY_BASKET, context, provider);
                    furnitureStation(DecorationsModule.STRAWBERRY_BASKET, context, provider);
                    furnitureStation(DecorationsModule.SWEETBERRY_BASKET, context, provider);
                })
        );
    }

    private void furnitureStation(ItemLike result, ProviderListenerContext context, RecipeProvider provider) {
        var output = result.asItem().isEnabled(context.enabledFeatures()) ? provider.output() : provider.output().withConditions(NeoForgeConditions.featureFlagsEnabled(FantasyFurniture.EXPERIMENTAL));
        var dyes = provider.tag(Tags.Items.DYES);
        var bindingAgent = provider.tag(FurnitureStationSetup.BINDING_AGENT);

        FurnitureStationRecipeBuilder.builder(RecipeCategory.DECORATIONS, dyes, null, bindingAgent, result)
                .unlockedBy(RecipeProvider.getHasName(Tags.Items.DYES), provider.has(Tags.Items.DYES))
                .save(output, RecipeProvider.recipeKeyWithPrefix(result, "furniture_station/"));
    }
}
