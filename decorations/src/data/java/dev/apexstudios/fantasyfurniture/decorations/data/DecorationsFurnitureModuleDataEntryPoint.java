package dev.apexstudios.fantasyfurniture.decorations.data;

import dev.apexstudios.apexcore.core.client.DyeColorItemTintSource;
import dev.apexstudios.apexcore.lib.block.Dyeable;
import dev.apexstudios.apexcore.lib.data.ProviderTypes;
import dev.apexstudios.apexcore.lib.data.ResourceGenerator;
import dev.apexstudios.apexcore.lib.data.provider.RecipeProvider;
import dev.apexstudios.fantasyfurniture.decorations.DecorationsFurnitureModule;
import dev.apexstudios.fantasyfurniture.decorations.cookie.CookieJarBlock;
import dev.apexstudios.fantasyfurniture.station.FurnitureStationRecipeBuilder;
import dev.apexstudios.fantasyfurniture.station.FurnitureStationSetup;
import java.util.Objects;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
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
                    cookieJar(blockModels);
                    existingHorizontalModel(DecorationsFurnitureModule.BREWING_CAULDRON, blockModels);
                    existingHorizontalModel(DecorationsFurnitureModule.GRAVESTONE, blockModels);
                    existingHorizontalModel(DecorationsFurnitureModule.HANGING_HERBS, blockModels);
                    existingHorizontalModel(DecorationsFurnitureModule.PAPER_STACK, blockModels);

                    DecorationsFurnitureModule.dyeables().forEach(block -> blockModels.registerSimpleTintedItemModel(
                            block,
                            assetPath(block),
                            new DyeColorItemTintSource(Dyeable.DEFAULT_COLOR)
                    ));
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
                    provider.addBlock(DecorationsFurnitureModule.COOKIE_JAR_BLOCK, "Cookie Jar");
                    provider.addBlock(DecorationsFurnitureModule.BREWING_CAULDRON, "Brewing Cauldron");
                    provider.addBlock(DecorationsFurnitureModule.GRAVESTONE, "Gravestone");
                    provider.addBlock(DecorationsFurnitureModule.HANGING_HERBS, "Hanging Herbs");
                    provider.addBlock(DecorationsFurnitureModule.PAPER_STACK, "Paper Stack");
                })
                .providing(ProviderTypes.RECIPES, (context, provider) -> DecorationsFurnitureModule.REGISTREE
                        .listElements(Registries.ITEM)
                        .map(Holder::value)
                        .forEach(item -> furnitureStation(item, provider))
                )
                .providing(ProviderTypes.LOOT_TABLE, (context, provider) -> {
                    provider.fromRegistree(DecorationsFurnitureModule.REGISTREE);

                    provider.block(lootTables -> DecorationsFurnitureModule.REGISTREE
                            .listElements(Registries.BLOCK)
                            .map(Holder::value)
                            .forEach(lootTables::dropSelf)
                    );
                })
                .providing(ProviderTypes.BLOCK_TAGS, (context, provider) -> {
                    DecorationsFurnitureModule.REGISTREE
                            .listElements(Registries.BLOCK)
                            .map(Holder::value)
                            .forEach(block -> provider.tag(BlockTags.MINEABLE_WITH_AXE).withElement(block));

                    DecorationsFurnitureModule.dyeables().forEach(block -> provider.tag(Tags.Blocks.DYED).withElement(block));
                })
                .providing(ProviderTypes.ITEM_TAGS, (context, provider) -> DecorationsFurnitureModule
                        .dyeables()
                        .map(ItemLike::asItem)
                        .forEach(block -> provider.tag(Tags.Items.DYED).withElement(block))
                )
        );
    }

    private void existingModel(Holder<Block> holder, BlockModelGenerators blockModels, BlockStateGenerator blockStateGenerator) {
        blockStateGenerator.accept(holder, assetPath(holder), blockModels);
    }

    private void existingHorizontalModel(Holder<Block> holder, BlockModelGenerators blockModels) {
        existingModel(holder, blockModels, this::horizontalFacingBlock);
    }

    private void existingTemplate(ResourceLocation templatePath, Holder<Block> holder, String textureSlot, boolean overrideParticle, BlockModelGenerators blockModels, BlockStateGenerator blockStateGenerator) {
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

    private void existingTemplateHorizontal(ResourceLocation templatePath, Holder<Block> holder, String textureSlot, boolean overrideParticle, BlockModelGenerators blockModels) {
        existingTemplate(templatePath, holder, textureSlot, overrideParticle, blockModels, this::horizontalFacingBlock);
    }

    private void existingTemplate(Holder<Block> templateHolder, Holder<Block> holder, String textureSlot, boolean overrideParticle, BlockModelGenerators blockModels, BlockStateGenerator blockStateGenerator) {
        var template = assetPath(templateHolder);

        if(holder.is(templateHolder)) {
            blockStateGenerator.accept(holder, template, blockModels);
        } else {
            existingTemplate(template, holder, textureSlot, overrideParticle, blockModels, blockStateGenerator);
        }
    }

    private void existingTemplateHorizontal(Holder<Block> templateHolder, Holder<Block> holder, String textureSlot, boolean overrideParticle, BlockModelGenerators blockModels) {
        existingTemplate(templateHolder, holder, textureSlot, overrideParticle, blockModels, this::horizontalFacingBlock);
    }

    private void berryBasket(Holder<Block> holder, BlockModelGenerators blockModels) {
        existingTemplateHorizontal(DecorationsFurnitureModule.BERRY_BASKET, holder, "berry_basket", false, blockModels);
    }

    private void bowl(Holder<Block> holder, BlockModelGenerators blockModels) {
        existingTemplateHorizontal(DecorationsFurnitureModule.BOWL, holder, "bowl", false, blockModels);
    }

    private void coinStack(Holder<Block> holder, BlockModelGenerators blockModels) {
        existingTemplateHorizontal(DecorationsFurnitureModule.identifier("block/coin_stack"), holder, "coin_stack", true, blockModels);
    }

    private void horizontalFacingBlock(Holder<Block> block, ResourceLocation model, BlockModelGenerators blockModels) {
        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block.value(), BlockModelGenerators.plainVariant(model)).with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING));
    }

    private void cookieJar(BlockModelGenerators blockModels) {
        var assetPath = assetPath(DecorationsFurnitureModule.COOKIE_JAR_BLOCK);

        blockModels.blockStateOutput.accept(MultiVariantGenerator
                .dispatch(DecorationsFurnitureModule.COOKIE_JAR_BLOCK.value(), BlockModelGenerators.plainVariant(assetPath))
                .with(PropertyDispatch.modify(CookieJarBlock.FULLNESS)
                                      .select(CookieJarBlock.Fullness.FULL, variant -> variant.withModel(assetPath.withSuffix("_full")))
                                      .select(CookieJarBlock.Fullness.HALF, variant -> variant.withModel(assetPath.withSuffix("_half")))
                                      .select(CookieJarBlock.Fullness.EMPTY, variant -> variant)
                )
                .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
        );
    }

    private ResourceLocation assetPath(ResourceKey<?> registryKey) {
        return registryKey.location().withPrefix("block/");
    }

    private ResourceLocation assetPath(Holder<?> holder) {
        return assetPath(Objects.requireNonNull(holder.getKey()));
    }

    private ResourceLocation assetPath(Block block) {
        return assetPath(block.builtInRegistryHolder());
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
