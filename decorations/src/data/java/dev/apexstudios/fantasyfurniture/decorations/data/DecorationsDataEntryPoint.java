package dev.apexstudios.fantasyfurniture.decorations.data;

import dev.apexstudios.apexcore.lib.data.ProviderTypes;
import dev.apexstudios.apexcore.lib.data.ResourceGenerator;
import dev.apexstudios.apexcore.lib.data.provider.RecipeProvider;
import dev.apexstudios.apexcore.lib.data.provider.model.ModelUtil;
import dev.apexstudios.fantasyfurniture.decorations.DecoBlocks;
import dev.apexstudios.fantasyfurniture.decorations.DecoItems;
import dev.apexstudios.fantasyfurniture.decorations.DecorationsModule;
import dev.apexstudios.fantasyfurniture.station.FurnitureStationRecipeBuilder;
import dev.apexstudios.fantasyfurniture.station.FurnitureStationSetup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;

@Mod(DecorationsModule.ID)
public final class DecorationsDataEntryPoint {
    public DecorationsDataEntryPoint(IEventBus modBus) {
        ResourceGenerator.of(modBus, generator -> generator
                .pack()
                .providing(ProviderTypes.LANGUAGE, (context, provider) -> provider
                        .addCreativeModeTab(DecorationsModule.CREATIVE_MODE_TAB_KEY, "Fantasy's Furniture - Decorations")
                        .addItem(DecoItems.BERRY_BASKET_EMPTY, "Empty Berry Basket")
                        .addItem(DecoItems.BERRY_BASKET_SWEETBERRY, "Sweetberry Basket")
                        .addItem(DecoItems.BERRY_BASKET_BLUEBERRY, "Blueberry Basket")
                        .addItem(DecoItems.BERRY_BASKET_STRAWBERRY, "Strawberry Basket")
                )
                .providing(ProviderTypes.LOOT_TABLE, (context, provider) -> provider
                        .fromRegistree(DecorationsModule.REGISTREE)
                        .block(blocks -> DecorationsModule.REGISTREE.stream(Registries.BLOCK).forEach(blocks::dropSelf))
                )
                .providing(ProviderTypes.MODELS, (context, provider) -> {
                    provider.fromRegistree(DecorationsModule.REGISTREE);

                    var blockModels = provider.blockModels();
                    ModelUtil.horizontalFacingBlock(DecoBlocks.BERRY_BASKET_EMPTY.value(), blockModels);
                    ModelUtil.horizontalFacingBlock(DecoBlocks.BERRY_BASKET_SWEETBERRY.value(), blockModels);
                    ModelUtil.horizontalFacingBlock(DecoBlocks.BERRY_BASKET_BLUEBERRY.value(), blockModels);
                    ModelUtil.horizontalFacingBlock(DecoBlocks.BERRY_BASKET_STRAWBERRY.value(), blockModels);
                })
                .providing(ProviderTypes.RECIPES, (context, provider) -> {
                    var dyes = provider.tag(Tags.Items.DYES);
                    var bindingAgent = provider.tag(FurnitureStationSetup.BINDING_AGENT);
                    var hasDyes = provider.has(Tags.Items.DYES);

                    DecorationsModule.REGISTREE.stream(Registries.ITEM).forEach(item -> FurnitureStationRecipeBuilder
                            .builder(RecipeCategory.DECORATIONS, dyes, null, bindingAgent, item)
                            .unlockedBy("has_dye", hasDyes)
                            .save(provider.output(), RecipeProvider.recipeKeyWithPrefix(item, "furniture_station/"))
                    );
                })
        );
    }
}
