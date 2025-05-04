package dev.apexstudios.fantasyfurniture.decorations.data;

import dev.apexstudios.apexcore.lib.data.ProviderTypes;
import dev.apexstudios.apexcore.lib.data.ResourceGenerator;
import dev.apexstudios.apexcore.lib.data.provider.RecipeProvider;
import dev.apexstudios.apexcore.lib.data.provider.model.ModelUtil;
import dev.apexstudios.fantasyfurniture.decorations.DecoBlocks;
import dev.apexstudios.fantasyfurniture.decorations.DecorItems;
import dev.apexstudios.fantasyfurniture.decorations.DecorationsFurnitureModule;
import dev.apexstudios.fantasyfurniture.station.FurnitureStationRecipeBuilder;
import dev.apexstudios.fantasyfurniture.station.FurnitureStationSetup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;

@Mod(DecorationsFurnitureModule.ID)
public final class DecorationsFurnitureModuleDataEntryPoint {
    public DecorationsFurnitureModuleDataEntryPoint(IEventBus modBus) {
        ResourceGenerator.of(modBus, generator -> generator.pack()
                .providing(ProviderTypes.LANGUAGE, (context, provider) -> provider
                        .addCreativeModeTab(DecorationsFurnitureModule.CREATIVE_MODE_TAB_KEY, "Fantasy's Furniture - Decorations")
                        .addItem(DecorItems.BERRY_BASKET, "Berry Basket")
                        .addItem(DecorItems.BERRY_BASKET_STRAWBERRY, "Strawberry Basket")
                        .addItem(DecorItems.BERRY_BASKET_SWEETBERRY, "Sweetberry Basket")
                        .addItem(DecorItems.BERRY_BASKET_BLUEBERRY, "Blueberry Basket")
                        .addItem(DecorItems.BOLTS_OF_CLOTH, "Bolts of Cloth")
                )
                .providing(ProviderTypes.LOOT_TABLE, (context, provider) -> provider
                        .block(blocks -> DecorationsFurnitureModule.REGISTREE
                                .stream(Registries.BLOCK)
                                .forEach(blocks::dropSelf)
                        )
                )
                .providing(ProviderTypes.MODELS, (context, provider) -> {
                    var blockModels = provider.blockModels();

                    ModelUtil.horizontalFacingBlock(DecoBlocks.BERRY_BASKET.value(), blockModels);
                    ModelUtil.horizontalFacingBlock(DecoBlocks.BERRY_BASKET_STRAWBERRY.value(), blockModels);
                    ModelUtil.horizontalFacingBlock(DecoBlocks.BERRY_BASKET_SWEETBERRY.value(), blockModels);
                    ModelUtil.horizontalFacingBlock(DecoBlocks.BERRY_BASKET_BLUEBERRY.value(), blockModels);
                    ModelUtil.horizontalFacingBlock(DecoBlocks.BOLTS_OF_CLOTH.value(), blockModels);
                })
                .providing(ProviderTypes.RECIPES, (context, provider) -> {
                    var dyes = provider.tag(Tags.Items.DYES);
                    var bindingAgent = provider.tag(FurnitureStationSetup.BINDING_AGENT);
                    var hasDyes = provider.has(Tags.Items.DYES);

                    DecorationsFurnitureModule.REGISTREE.stream(Registries.ITEM).forEach(item -> FurnitureStationRecipeBuilder
                            .builder(RecipeCategory.DECORATIONS, dyes, null, bindingAgent, item)
                            .unlockedBy("has_dye", hasDyes)
                            .save(provider.output(), RecipeProvider.recipeKeyWithPrefix(item, "furniture_station/"))
                    );
                })
        );
    }
}
