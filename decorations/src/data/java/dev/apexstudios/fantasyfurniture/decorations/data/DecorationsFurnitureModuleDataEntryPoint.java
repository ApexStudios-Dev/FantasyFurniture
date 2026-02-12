package dev.apexstudios.fantasyfurniture.decorations.data;

import dev.apexstudios.apexcore.api.block.Dyeable;
import dev.apexstudios.apexcore.api.data.ProviderTypes;
import dev.apexstudios.apexcore.api.data.ResourceGenerator;
import dev.apexstudios.apexcore.api.data.provider.RecipeProvider;
import dev.apexstudios.apexcore.api.multiblock.MultiBlock;
import dev.apexstudios.apexcore.api.placement.BlockItemPlacementEvent;
import dev.apexstudios.apexcore.api.util.ApexTags;
import dev.apexstudios.apexcore.client.DyeColorItemTintSource;
import dev.apexstudios.fantasyfurniture.common.station.FurnitureStationRecipeBuilder;
import dev.apexstudios.fantasyfurniture.common.station.FurnitureStationSetup;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureClientDataUtil;
import dev.apexstudios.fantasyfurniture.decorations.common.DecorationsFurnitureModule;
import dev.apexstudios.fantasyfurniture.decorations.common.ber.SimpleBlockEntityBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.ber.SimpleBlockEntitySpecialRenderer;
import dev.apexstudios.fantasyfurniture.decorations.common.block.BonePileBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.BookStackBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.BowlBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.ChalicesBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.CoinStackBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.MuffinsBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.MushroomsBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.PlatterBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.SoulGemsBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.Stackable;
import dev.apexstudios.fantasyfurniture.decorations.common.block.TankardsBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.cookie.CookieJarBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.grave.GravestoneEditScreen;
import dev.apexstudios.fantasyfurniture.decorations.common.plushie.PlushieBlockItem;
import dev.apexstudios.fantasyfurniture.decorations.common.plushie.PlushieSpecialModelRenderer;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.block.model.Material;
import net.minecraft.client.renderer.block.model.VariantMutator;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplateBuilder;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredBlock;

@Mod(DecorationsFurnitureModule.ID)
public final class DecorationsFurnitureModuleDataEntryPoint {
    public DecorationsFurnitureModuleDataEntryPoint(IEventBus modBus) {
        ResourceGenerator.of(modBus, generator -> generator
                .pack()
                .providing(ProviderTypes.MODELS, (context, models) -> {
                    models.knownBlocks(DecorationsFurnitureModule.BLOCKS::holders);
                    models.knownItems(DecorationsFurnitureModule.ITEMS::holders);

                    var blockModels = models.blockModels();

                    berryBasket(DecorationsFurnitureModule.BERRY_BASKET, blockModels);
                    berryBasket(DecorationsFurnitureModule.BLUEBERRY_BASKET, blockModels);
                    berryBasket(DecorationsFurnitureModule.STRAWBERRY_BASKET, blockModels);
                    berryBasket(DecorationsFurnitureModule.SWEETBERRY_BASKET, blockModels);
                    blockModels.createNonTemplateHorizontalBlock(DecorationsFurnitureModule.BOLTS_OF_CLOTH.value());
                    bowl(DecorationsFurnitureModule.BOWL, blockModels);
                    bowl(DecorationsFurnitureModule.BEETROOT_SOUP_BOWL, blockModels);
                    bowl(DecorationsFurnitureModule.MUSHROOM_STEW_BOWL, blockModels);
                    coinStack(DecorationsFurnitureModule.GOLDEN_COIN_STACK, blockModels);
                    coinStack(DecorationsFurnitureModule.IRON_COIN_STACK, blockModels);
                    cookieJar(blockModels);
                    blockModels.createNonTemplateHorizontalBlock(DecorationsFurnitureModule.BREWING_CAULDRON.value());
                    blockModels.createNonTemplateHorizontalBlock(DecorationsFurnitureModule.GRAVESTONE_BLOCK.value());
                    blockModels.createNonTemplateHorizontalBlock(DecorationsFurnitureModule.HANGING_HERBS.value());
                    blockModels.createNonTemplateHorizontalBlock(DecorationsFurnitureModule.PAPER_STACK.value());
                    blockModels.createNonTemplateHorizontalBlock(DecorationsFurnitureModule.SPIDER_WEB_SMALL.value());
                    FurnitureClientDataUtil.createLeftRightModel(DecorationsFurnitureModule.SPIDER_WEB_WIDE.value(), blockModels);
                    FurnitureClientDataUtil.registerSimpleBlockItemModel(DecorationsFurnitureModule.SPIDER_WEB_WIDE.value(), blockModels);
                    // blockModels.registerSimpleFlatItemModel(DecorationsFurnitureModule.BRONZE_CHAIN.value());
                    blockModels.createAxisAlignedPillarBlockCustomModel(DecorationsFurnitureModule.BRONZE_CHAIN.value(), BlockModelGenerators.plainVariant(TexturedModel.CHAIN.create(DecorationsFurnitureModule.BRONZE_CHAIN.value(), blockModels.modelOutput)));
                    fairyLights(blockModels);
                    blockModels.createNonTemplateHorizontalBlock(DecorationsFurnitureModule.STOCKING.value());
                    bookstack(DecorationsFurnitureModule.BOOK_STACK_0, blockModels);
                    bookstack(DecorationsFurnitureModule.BOOK_STACK_1, blockModels);
                    tankards(DecorationsFurnitureModule.TANKARDS, blockModels);
                    tankards(DecorationsFurnitureModule.TANKARDS_HONEYMEAD, blockModels);
                    tankards(DecorationsFurnitureModule.TANKARDS_MILK, blockModels);
                    tankards(DecorationsFurnitureModule.TANKARDS_SWEETBERRY, blockModels);
                    mushrooms(DecorationsFurnitureModule.MUSHROOMS_RED, blockModels);
                    mushrooms(DecorationsFurnitureModule.MUSHROOMS_BROWN, blockModels);
                    muffins(DecorationsFurnitureModule.MUFFINS_BLUEBERRY, blockModels);
                    muffins(DecorationsFurnitureModule.MUFFINS_CHOCOLATE, blockModels);
                    muffins(DecorationsFurnitureModule.MUFFINS_SWEETBERRY, blockModels);
                    floatingTomes(blockModels);
                    stackablePumpkins(blockModels);
                    potionBottles(blockModels);
                    presents(blockModels);
                    coinStack(DecorationsFurnitureModule.COPPER_COIN_STACK, blockModels);
                    snowballs(blockModels);
                    boiledCremeTreats(blockModels);
                    sweetrolls(blockModels);
                    meadBottles(blockModels);
                    soulGems(DecorationsFurnitureModule.SOUL_GEMS_DARK, blockModels);
                    soulGems(DecorationsFurnitureModule.SOUL_GEMS_LIGHT, blockModels);
                    food(blockModels);
                    FurnitureClientDataUtil.createLeftRightModel(DecorationsFurnitureModule.TEA_SET.value(), blockModels);
                    FurnitureClientDataUtil.registerSimpleBlockItemModel(DecorationsFurnitureModule.TEA_SET.value(), blockModels);
                    teaCups(blockModels);
                    platter(DecorationsFurnitureModule.PLATTER_0, blockModels);
                    platter(DecorationsFurnitureModule.PLATTER_1, blockModels);
                    chalices(blockModels);
                    candles(blockModels);
                    FurnitureClientDataUtil.createBottomTopModel(DecorationsFurnitureModule.BANNER.value(), blockModels);
                    FurnitureClientDataUtil.registerSimpleBlockItemModel(DecorationsFurnitureModule.BANNER.value(), blockModels);
                    bonePile(DecorationsFurnitureModule.BONE_PILE_SKELETON, blockModels);
                    bonePile(DecorationsFurnitureModule.BONE_PILE_WITHER, blockModels);
                    blockModels.createNonTemplateHorizontalBlock(DecorationsFurnitureModule.CROWN.value());
                    blockModels.createNonTemplateHorizontalBlock(DecorationsFurnitureModule.CUSHIONED_CROWN.value());
                    blockModels.createNonTemplateHorizontalBlock(DecorationsFurnitureModule.FLOOR_CUSHION.value());
                    blockModels.createNonTemplateHorizontalBlock(DecorationsFurnitureModule.CANDELABRA_0.value());
                    blockModels.createNonTemplateHorizontalBlock(DecorationsFurnitureModule.CANDELABRA_1.value());
                    blockModels.createNonTemplateHorizontalBlock(DecorationsFurnitureModule.WALL_MIRROR_SMALL.value());
                    FurnitureClientDataUtil.createBottomTopModel(DecorationsFurnitureModule.WALL_MIRROR_LARGE.value(), blockModels);
                    FurnitureClientDataUtil.registerSimpleBlockItemModel(DecorationsFurnitureModule.WALL_MIRROR_LARGE.value(), blockModels);
                    blockModels.createNonTemplateHorizontalBlock(DecorationsFurnitureModule.POTTERY_0.value());
                    blockModels.createNonTemplateHorizontalBlock(DecorationsFurnitureModule.POTTERY_1.value());
                    simpleBlockEntity(DecorationsFurnitureModule.WIDOW_BLOOM_BLOCK, new SimpleBlockEntitySpecialRenderer.WidowBloom(), blockModels);
                    simpleBlockEntity(DecorationsFurnitureModule.SKULL_BLOSSOM_SKELETON_BLOCK, new SimpleBlockEntitySpecialRenderer.SkullBlossom(true), blockModels);
                    simpleBlockEntity(DecorationsFurnitureModule.SKULL_BLOSSOM_WITHER_BLOCK, new SimpleBlockEntitySpecialRenderer.SkullBlossom(false), blockModels);
                    plushie(blockModels);
                })
                .providing(ProviderTypes.LANGUAGE, (context, provider) -> {
                    provider.addCreativeModeTab(DecorationsFurnitureModule.CREATIVE_MODE_TAB, "Fantasy's Furniture - Decorations");
                    provider.add(GravestoneEditScreen.TRANSLATION, "Edit Gravestone Message");

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
                    provider.addBlock(DecorationsFurnitureModule.GRAVESTONE_BLOCK, "Gravestone");
                    provider.addBlock(DecorationsFurnitureModule.HANGING_HERBS, "Hanging Herbs");
                    provider.addBlock(DecorationsFurnitureModule.PAPER_STACK, "Paper Stack");
                    provider.addBlock(DecorationsFurnitureModule.SPIDER_WEB_SMALL, "Spiderweb Small");
                    provider.addBlock(DecorationsFurnitureModule.SPIDER_WEB_WIDE, "Spiderweb Wide");
                    provider.addBlock(DecorationsFurnitureModule.BRONZE_CHAIN, "Bronze Chain");
                    provider.addBlock(DecorationsFurnitureModule.FAIRY_LIGHTS, "Fairy Lights");
                    provider.addBlock(DecorationsFurnitureModule.STOCKING, "Stocking");
                    provider.addBlock(DecorationsFurnitureModule.BOOK_STACK_0, "Book Stack 0");
                    provider.addBlock(DecorationsFurnitureModule.BOOK_STACK_1, "Book Stack 1");
                    provider.addBlock(DecorationsFurnitureModule.TANKARDS, "Tankards");
                    provider.addBlock(DecorationsFurnitureModule.TANKARDS_HONEYMEAD, "Honeymead Tankards");
                    provider.addBlock(DecorationsFurnitureModule.TANKARDS_MILK, "Milk Tankards");
                    provider.addBlock(DecorationsFurnitureModule.TANKARDS_SWEETBERRY, "Sweetberry Tankards");
                    provider.addBlock(DecorationsFurnitureModule.MUSHROOMS_RED, "Mushrooms Red");
                    provider.addBlock(DecorationsFurnitureModule.MUSHROOMS_BROWN, "Mushrooms Brown");
                    provider.addBlock(DecorationsFurnitureModule.MUFFINS_BLUEBERRY, "Blueberry Muffins");
                    provider.addBlock(DecorationsFurnitureModule.MUFFINS_CHOCOLATE, "Chocolate Muffins");
                    provider.addBlock(DecorationsFurnitureModule.MUFFINS_SWEETBERRY, "Sweetberry Muffins");
                    provider.addBlock(DecorationsFurnitureModule.FLOATING_TOMES, "Floating Tomes");
                    provider.addBlock(DecorationsFurnitureModule.STACKABLE_PUMPKINS, "Stackable Pumpkins");
                    provider.addBlock(DecorationsFurnitureModule.POTION_BOTTLES, "Potion Bottles");
                    provider.addBlock(DecorationsFurnitureModule.PRESENTS, "Presents");
                    provider.addBlock(DecorationsFurnitureModule.COPPER_COIN_STACK, "Copper Coin Stack");
                    provider.addBlock(DecorationsFurnitureModule.SNOWBALLS, "Snowballs");
                    provider.addBlock(DecorationsFurnitureModule.BOILED_CREME_TREATS, "Boiled Creme Treats");
                    provider.addBlock(DecorationsFurnitureModule.SWEETROLLS, "Sweetrolls");
                    provider.addBlock(DecorationsFurnitureModule.MEAD_BOTTLES, "Mead Bottles");
                    provider.addBlock(DecorationsFurnitureModule.SOUL_GEMS_DARK, "Soul Gems Dark");
                    provider.addBlock(DecorationsFurnitureModule.SOUL_GEMS_LIGHT, "Soul Gems Light");
                    provider.addBlock(DecorationsFurnitureModule.FOOD_0, "Food 0");
                    provider.addBlock(DecorationsFurnitureModule.FOOD_1, "Food 1");
                    provider.addBlock(DecorationsFurnitureModule.FOOD_2, "Food 2");
                    provider.addBlock(DecorationsFurnitureModule.FOOD_3, "Food 3");
                    provider.addBlock(DecorationsFurnitureModule.TEA_SET, "Tea Set");
                    provider.addBlock(DecorationsFurnitureModule.TEA_CUPS, "Tea Cups");
                    provider.addBlock(DecorationsFurnitureModule.PLATTER_0, "Platter 0");
                    provider.addBlock(DecorationsFurnitureModule.PLATTER_1, "Platter 1");
                    provider.addBlock(DecorationsFurnitureModule.CHALICES_0, "Chalices 0");
                    provider.addBlock(DecorationsFurnitureModule.CHALICES_1, "Chalices 1");
                    provider.addBlock(DecorationsFurnitureModule.CHALICES_2, "Chalices 2");
                    provider.addBlock(DecorationsFurnitureModule.CHALICES_3, "Chalices 3");
                    provider.addBlock(DecorationsFurnitureModule.CANDLES_0, "Candles 0");
                    provider.addBlock(DecorationsFurnitureModule.CANDLES_1, "Candles 1");
                    provider.addBlock(DecorationsFurnitureModule.BANNER, "Banner");
                    provider.addBlock(DecorationsFurnitureModule.BONE_PILE_SKELETON, "Bone Pile Skeleton");
                    provider.addBlock(DecorationsFurnitureModule.BONE_PILE_WITHER, "Bone Pile Wither");
                    provider.addBlock(DecorationsFurnitureModule.CROWN, "Crown");
                    provider.addBlock(DecorationsFurnitureModule.CUSHIONED_CROWN, "Cushioned Crown");
                    provider.addBlock(DecorationsFurnitureModule.FLOOR_CUSHION, "Floor Cushion");
                    provider.addBlock(DecorationsFurnitureModule.CANDELABRA_0, "Candelabra 0");
                    provider.addBlock(DecorationsFurnitureModule.CANDELABRA_1, "Candelabra 1");
                    provider.addBlock(DecorationsFurnitureModule.WALL_MIRROR_SMALL, "Wall Mirror Small");
                    provider.addBlock(DecorationsFurnitureModule.WALL_MIRROR_LARGE, "Wall Mirror Large");
                    provider.addBlock(DecorationsFurnitureModule.POTTERY_0, "Pottery 0");
                    provider.addBlock(DecorationsFurnitureModule.POTTERY_1, "Pottery 1");
                    provider.addBlock(DecorationsFurnitureModule.WIDOW_BLOOM_BLOCK, "Widow Bloom");
                    provider.addBlock(DecorationsFurnitureModule.SKULL_BLOSSOM_SKELETON_BLOCK, "Skull Blossoms Skeleton");
                    provider.addBlock(DecorationsFurnitureModule.SKULL_BLOSSOM_WITHER_BLOCK, "Skull Blossoms Wither");
                    provider.addBlock(DecorationsFurnitureModule.PLUSHIE_BLOCK, "Plushie");
                    provider.add(PlushieBlockItem.PLAYER_KEY, "Player");
                    provider.add(PlushieBlockItem.DYANMIC_KEY, "%s %s");
                })
                .providing(ProviderTypes.RECIPES, (context, provider) -> DecorationsFurnitureModule.ITEMS.forEach(item -> furnitureStation(item, provider)))
                .providing(ProviderTypes.LOOT_TABLE, (context, provider) -> {
                    provider.knownElements(Registries.BLOCK, DecorationsFurnitureModule.BLOCKS::holders);

                    provider.block(lootTables -> {
                        DecorationsFurnitureModule.BLOCKS
                                .holders()
                                .filter(Predicate.not(DecorationsFurnitureModule.PLUSHIE_BLOCK::is))
                                .map(Holder::value)
                                .forEach(lootTables::dropSelf);

                        lootTables.accept(DecorationsFurnitureModule.PLUSHIE_BLOCK.value(), () -> LootTable
                                .lootTable()
                                .withPool(lootTables.applyExplosionCondition(DecorationsFurnitureModule.PLUSHIE_BLOCK.value(), LootPool
                                        .lootPool()
                                        .setRolls(ConstantValue.exactly(1F))
                                        .add(LootItem
                                                .lootTableItem(DecorationsFurnitureModule.PLUSHIE_BLOCK.value())
                                                .apply(CopyComponentsFunction
                                                        .copyComponentsFromBlockEntity(LootContextParams.BLOCK_ENTITY)
                                                        .include(DataComponents.PROFILE)
                                                        .include(DecorationsFurnitureModule.PLUSHIE_RENDER_NAME.value())
                                                )
                                        )
                                ))
                        );
                    });
                })
                .providing(ProviderTypes.BLOCK_TAGS, (context, provider) -> {
                    DecorationsFurnitureModule.BLOCKS.forEach(block -> {
                        provider.tag(BlockTags.MINEABLE_WITH_AXE).withElement(block);

                        if(block instanceof Dyeable) {
                            provider.tag(Tags.Blocks.DYED).withElement(block);
                        }

                        if(block instanceof MultiBlock || block instanceof Stackable || DecorationsFurnitureModule.BRONZE_CHAIN.is(block.builtInRegistryHolder())) {
                            provider.tag(BlockItemPlacementEvent.RENDERABLES).withElement(block);
                        }

                        if(block instanceof MultiBlock) {
                            provider.tag(Tags.Blocks.RELOCATION_NOT_SUPPORTED).withElement(block);
                        }
                    });

                    provider.tag(Tags.Blocks.CHAINS).withElement(DecorationsFurnitureModule.BRONZE_CHAIN);
                    provider.tag(Tags.Blocks.CHAINS).withElement(DecorationsFurnitureModule.BRONZE_CHAIN);

                    provider.tag(ApexTags.Blocks.SHEARS_EFFICIENT).withElement(DecorationsFurnitureModule.PLUSHIE_BLOCK);
                })
                .providing(ProviderTypes.ITEM_TAGS, (context, provider) -> {
                    Dyeable.dyeableItems(DecorationsFurnitureModule.REGISTREE)
                           .map(ItemLike::asItem)
                           .forEach(block -> provider.tag(Tags.Items.DYED).withElement(block));

                    provider.tag(ItemTags.CHAINS).withElement(DecorationsFurnitureModule.BRONZE_CHAIN.value().asItem());
                    provider.tag(Tags.Items.CHAINS).withElement(DecorationsFurnitureModule.BRONZE_CHAIN.value().asItem());
                })
        );
    }

    private void furnitureStation(Item item, RecipeProvider provider) {
        FurnitureStationRecipeBuilder
                .builder(RecipeCategory.DECORATIONS, provider.tag(Tags.Items.DYES), null, provider.tag(FurnitureStationSetup.BINDING_AGENT), item)
                .unlockedBy(RecipeProvider.getHasName(Tags.Items.DYES), provider.has(Tags.Items.DYES))
                .save(provider.output(), RecipeProvider.recipeKeyWithPrefix(item, "furniture_station/"));
    }

    private void berryBasket(Holder<Block> holder, BlockModelGenerators blockModels) {
        if(!DecorationsFurnitureModule.BERRY_BASKET.is(holder)) {
            var slot = TextureSlot.create("berry_basket");

            var template = ExtendedModelTemplateBuilder
                    .builder()
                    .parent(ModelLocationUtils.getModelLocation(DecorationsFurnitureModule.BERRY_BASKET.value()))
                    .requiredTextureSlot(slot);

            template.build().create(
                    holder.value(),
                    new TextureMapping().put(slot, TextureMapping.getBlockTexture(holder.value())),
                    blockModels.modelOutput
            );
        }

        blockModels.createNonTemplateHorizontalBlock(holder.value());
    }

    private void bowl(DeferredBlock<BowlBlock> holder, BlockModelGenerators blockModels) {
        if(!DecorationsFurnitureModule.BOWL.is(holder)) {
            var slot = TextureSlot.create("bowl");

            var template = ExtendedModelTemplateBuilder
                    .builder()
                    .parent(ModelLocationUtils.getModelLocation(DecorationsFurnitureModule.BOWL.value()))
                    .requiredTextureSlot(slot);

            template.build().create(
                    holder.value(),
                    new TextureMapping().put(slot, TextureMapping.getBlockTexture(holder.value())),
                    blockModels.modelOutput
            );
        }

        blockModels.createNonTemplateHorizontalBlock(holder.value());
    }

    private void coinStack(DeferredBlock<CoinStackBlock> holder, BlockModelGenerators blockModels) {
        if(!DecorationsFurnitureModule.GOLDEN_COIN_STACK.is(holder)) {
            var slot = TextureSlot.create("coin_stack");

            var template = ExtendedModelTemplateBuilder
                    .builder()
                    .parent(ModelLocationUtils.getModelLocation(DecorationsFurnitureModule.GOLDEN_COIN_STACK.value()))
                    .requiredTextureSlot(TextureSlot.PARTICLE)
                    .requiredTextureSlot(slot);

            template.build().create(
                    holder.value(),
                    TextureMapping.particle(TextureMapping.getBlockTexture(holder.value(), "_particle"))
                                  .put(slot, TextureMapping.getBlockTexture(holder.value())),
                    blockModels.modelOutput
            );
        }

        blockModels.createNonTemplateHorizontalBlock(holder.value());
    }

    private void cookieJar(BlockModelGenerators blockModels) {
        blockModels.blockStateOutput.accept(blockState(DecorationsFurnitureModule.COOKIE_JAR_BLOCK)
                .with(PropertyDispatch.modify(CookieJarBlock.FULLNESS).generate(fullness -> variant -> {
                    if(fullness == CookieJarBlock.Fullness.EMPTY) {
                        return variant;
                    }

                    return variant.withModel(variant.modelLocation().withSuffix("_" + fullness.getSerializedName()));
                }))
                .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
        );
    }

    private void fairyLights(BlockModelGenerators blockModels) {
        createdDyeColorModel(DecorationsFurnitureModule.FAIRY_LIGHTS, TextureSlot.create("lights"), false, blockModels.modelOutput);

        blockModels.blockStateOutput.accept(blockState(DecorationsFurnitureModule.FAIRY_LIGHTS)
                .with(dyedColorDispatch(DecorationsFurnitureModule.FAIRY_LIGHTS))
                .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
        );

        dyedColorItemModel(DecorationsFurnitureModule.FAIRY_LIGHTS, blockModels);
    }

    private void bookstack(DeferredBlock<BookStackBlock> holder, BlockModelGenerators blockModels) {
        createStackedTemplatedModels(DecorationsFurnitureModule.BOOK_STACK_0, holder, TextureSlot.create("book_stack"), true, blockModels.modelOutput);

        blockModels.blockStateOutput.accept(blockState(holder)
                .with(stackableDispatch(holder))
                .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
        );

        stackedItemModel(holder, blockModels);
    }

    private void tankards(DeferredBlock<TankardsBlock> holder, BlockModelGenerators blockModels) {
        createStackedTemplatedModels(DecorationsFurnitureModule.TANKARDS, holder, TextureSlot.create("tankards"), false, blockModels.modelOutput);

        blockModels.blockStateOutput.accept(blockState(holder)
                .with(stackableDispatch(holder))
                .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
        );

        stackedItemModel(holder, blockModels);
    }

    private void mushrooms(DeferredBlock<MushroomsBlock> holder, BlockModelGenerators blockModels) {
        blockModels.blockStateOutput.accept(blockState(holder)
                .with(stackableDispatch(holder))
                .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
        );

        stackedItemModel(holder, blockModels);
    }

    private void muffins(DeferredBlock<MuffinsBlock> holder, BlockModelGenerators blockModels) {
        createStackedTemplatedModels(DecorationsFurnitureModule.MUFFINS_BLUEBERRY, holder, TextureSlot.create("muffins"), true, blockModels.modelOutput);

        blockModels.blockStateOutput.accept(blockState(holder)
                .with(stackableDispatch(holder))
                .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
        );

        stackedItemModel(holder, blockModels);
    }

    private void floatingTomes(BlockModelGenerators blockModels) {
        blockModels.blockStateOutput.accept(blockState(DecorationsFurnitureModule.FLOATING_TOMES)
                .with(stackableDispatch(DecorationsFurnitureModule.FLOATING_TOMES))
                .with(dyedColorDispatch(DecorationsFurnitureModule.FLOATING_TOMES))
                .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
        );

        stackedDyedColorItemModel(DecorationsFurnitureModule.FLOATING_TOMES, blockModels);
    }

    private void stackablePumpkins(BlockModelGenerators blockModels) {
        blockModels.blockStateOutput.accept(blockState(DecorationsFurnitureModule.STACKABLE_PUMPKINS)
                .with(stackableDispatch(DecorationsFurnitureModule.STACKABLE_PUMPKINS))
                .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
        );

        stackedItemModel(DecorationsFurnitureModule.STACKABLE_PUMPKINS, blockModels);
    }

    private void potionBottles(BlockModelGenerators blockModels) {
        createdStackedDyeColorModels(DecorationsFurnitureModule.POTION_BOTTLES, TextureSlot.create("potion"), false, blockModels.modelOutput);

        blockModels.blockStateOutput.accept(blockState(DecorationsFurnitureModule.POTION_BOTTLES)
                .with(stackableDispatch(DecorationsFurnitureModule.POTION_BOTTLES))
                .with(dyedColorDispatch(DecorationsFurnitureModule.POTION_BOTTLES))
                .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
        );

        stackedDyedColorItemModel(DecorationsFurnitureModule.POTION_BOTTLES, blockModels);
    }

    private void presents(BlockModelGenerators blockModels) {
        createdStackedDyeColorModels(DecorationsFurnitureModule.PRESENTS, TextureSlot.create("presents"), true, blockModels.modelOutput);

        blockModels.blockStateOutput.accept(blockState(DecorationsFurnitureModule.PRESENTS)
                .with(stackableDispatch(DecorationsFurnitureModule.PRESENTS))
                .with(dyedColorDispatch(DecorationsFurnitureModule.PRESENTS))
                .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
        );

        stackedDyedColorItemModel(DecorationsFurnitureModule.PRESENTS, blockModels);
    }

    private void snowballs(BlockModelGenerators blockModels) {
        blockModels.blockStateOutput.accept(blockState(DecorationsFurnitureModule.SNOWBALLS)
                .with(stackableDispatch(DecorationsFurnitureModule.SNOWBALLS))
                .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
        );

        stackedItemModel(DecorationsFurnitureModule.SNOWBALLS, blockModels);
    }

    private void boiledCremeTreats(BlockModelGenerators blockModels) {
        blockModels.blockStateOutput.accept(blockState(DecorationsFurnitureModule.BOILED_CREME_TREATS)
                .with(stackableDispatch(DecorationsFurnitureModule.BOILED_CREME_TREATS))
                .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
        );

        stackedItemModel(DecorationsFurnitureModule.BOILED_CREME_TREATS, blockModels);
    }

    private void sweetrolls(BlockModelGenerators blockModels) {
        blockModels.blockStateOutput.accept(blockState(DecorationsFurnitureModule.SWEETROLLS)
                .with(stackableDispatch(DecorationsFurnitureModule.SWEETROLLS))
                .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
        );

        stackedItemModel(DecorationsFurnitureModule.SWEETROLLS, blockModels);
    }

    private void meadBottles(BlockModelGenerators blockModels) {
        blockModels.blockStateOutput.accept(blockState(DecorationsFurnitureModule.MEAD_BOTTLES)
                .with(stackableDispatch(DecorationsFurnitureModule.MEAD_BOTTLES))
                .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
        );

        stackedItemModel(DecorationsFurnitureModule.MEAD_BOTTLES, blockModels);
    }

    private void soulGems(DeferredBlock<SoulGemsBlock> holder, BlockModelGenerators blockModels) {
        if(!DecorationsFurnitureModule.SOUL_GEMS_DARK.is(holder)) {
            var slot = TextureSlot.create("soul_gems");

            var template = ExtendedModelTemplateBuilder
                    .builder()
                    .parent(ModelLocationUtils.getModelLocation(DecorationsFurnitureModule.SOUL_GEMS_DARK.value()))
                    .requiredTextureSlot(TextureSlot.PARTICLE)
                    .requiredTextureSlot(slot);

            template.build().create(
                    holder.value(),
                    TextureMapping.particle(TextureMapping.getBlockTexture(holder.value(), "_particle"))
                                  .put(slot, TextureMapping.getBlockTexture(holder.value())),
                    blockModels.modelOutput
            );
        }

        blockModels.createNonTemplateHorizontalBlock(holder.value());
    }

    private void food(BlockModelGenerators blockModels) {
        var slot = TextureSlot.create("food");

        var template = ExtendedModelTemplateBuilder
                .builder()
                .requiredTextureSlot(TextureSlot.PARTICLE)
                .requiredTextureSlot(slot);

        var textures = TextureMapping.particle(TextureMapping.getBlockTexture(DecorationsFurnitureModule.FOOD_2.value(), "_particle"));

        template.parent(ModelLocationUtils.getModelLocation(DecorationsFurnitureModule.FOOD_0.value())).build().create(
                DecorationsFurnitureModule.FOOD_2.value(),
                textures.put(slot, TextureMapping.getBlockTexture(DecorationsFurnitureModule.FOOD_2.value())),
                blockModels.modelOutput
        );

        template.parent(ModelLocationUtils.getModelLocation(DecorationsFurnitureModule.FOOD_1.value())).build().create(
                DecorationsFurnitureModule.FOOD_3.value(),
                textures.put(slot, TextureMapping.getBlockTexture(DecorationsFurnitureModule.FOOD_3.value())),
                blockModels.modelOutput
        );

        blockModels.createNonTemplateHorizontalBlock(DecorationsFurnitureModule.FOOD_0.value());
        blockModels.createNonTemplateHorizontalBlock(DecorationsFurnitureModule.FOOD_1.value());
        blockModels.createNonTemplateHorizontalBlock(DecorationsFurnitureModule.FOOD_2.value());
        blockModels.createNonTemplateHorizontalBlock(DecorationsFurnitureModule.FOOD_3.value());
    }

    private void teaCups(BlockModelGenerators blockModels) {
        blockModels.blockStateOutput.accept(blockState(DecorationsFurnitureModule.TEA_CUPS)
                .with(stackableDispatch(DecorationsFurnitureModule.TEA_CUPS))
                .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
        );

        stackedItemModel(DecorationsFurnitureModule.TEA_CUPS, blockModels);
    }

    private void platter(DeferredBlock<PlatterBlock> holder, BlockModelGenerators blockModels) {
        createStackedTemplatedModels(DecorationsFurnitureModule.PLATTER_0, holder, TextureSlot.create("platter"), true, blockModels.modelOutput);

        blockModels.blockStateOutput.accept(blockState(holder)
                .with(stackableDispatch(holder))
                .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
        );

        stackedItemModel(holder, blockModels);
    }

    private void chalices(BlockModelGenerators blockModels) {
        createStackedTemplatedModels(DecorationsFurnitureModule.CHALICES_1, DecorationsFurnitureModule.CHALICES_2, TextureSlot.create("chalices"), true, blockModels.modelOutput);

        var baseParentPath = ModelLocationUtils.getModelLocation(DecorationsFurnitureModule.CHALICES_0.value());
        var cupSlot = TextureSlot.create("cup");
        var fluidSlot = TextureSlot.create("fluid");
        var textures = TextureMapping.particle(TextureMapping.getBlockTexture(DecorationsFurnitureModule.CHALICES_3.value(), "_particle"))
                                     .put(cupSlot, TextureMapping.getBlockTexture(DecorationsFurnitureModule.CHALICES_3.value(), "_particle"))
                                     .put(fluidSlot, TextureMapping.getBlockTexture(DecorationsFurnitureModule.CHALICES_3.value(), "_tint"));

        var template = ExtendedModelTemplateBuilder
                .builder()
                .requiredTextureSlot(TextureSlot.PARTICLE)
                .requiredTextureSlot(cupSlot)
                .requiredTextureSlot(fluidSlot);

        for(var i = ChalicesBlock.COUNT.min; i < ChalicesBlock.COUNT.max + 1; i++) {
            template.parent(baseParentPath.withSuffix("_" + i))
                    .suffix("_" + i)
                    .build()
                    .create(DecorationsFurnitureModule.CHALICES_3.value(), textures, blockModels.modelOutput);
        }


        blockModels.blockStateOutput.accept(blockState(DecorationsFurnitureModule.CHALICES_0)
                .with(stackableDispatch(DecorationsFurnitureModule.CHALICES_0))
                .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
        );

        blockModels.blockStateOutput.accept(blockState(DecorationsFurnitureModule.CHALICES_1)
                .with(stackableDispatch(DecorationsFurnitureModule.CHALICES_1))
                .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
        );

        blockModels.blockStateOutput.accept(blockState(DecorationsFurnitureModule.CHALICES_2)
                .with(stackableDispatch(DecorationsFurnitureModule.CHALICES_2))
                .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
        );

        blockModels.blockStateOutput.accept(blockState(DecorationsFurnitureModule.CHALICES_3)
                .with(stackableDispatch(DecorationsFurnitureModule.CHALICES_3))
                .with(dyedColorDispatch(DecorationsFurnitureModule.CHALICES_3))
                .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
        );

        stackedItemModel(DecorationsFurnitureModule.CHALICES_0, blockModels);
        stackedItemModel(DecorationsFurnitureModule.CHALICES_1, blockModels);
        stackedItemModel(DecorationsFurnitureModule.CHALICES_2, blockModels);
        stackedDyedColorItemModel(DecorationsFurnitureModule.CHALICES_3, blockModels);
    }

    private void candles(BlockModelGenerators blockModels) {
        var slot = TextureSlot.create("candles");

        var template = ExtendedModelTemplateBuilder
                .builder()
                .parent(ModelLocationUtils.getModelLocation(DecorationsFurnitureModule.CANDLES_0.value()))
                .requiredTextureSlot(TextureSlot.PARTICLE)
                .requiredTextureSlot(slot)
                .build();

        var provider = TexturedModel.createDefault(block -> TextureMapping
                .particle(TextureMapping.getBlockTexture(block, "_particle"))
                .put(slot, TextureMapping.getBlockTexture(block)), template);

        blockModels.createNonTemplateHorizontalBlock(DecorationsFurnitureModule.CANDLES_0.value());
        blockModels.createHorizontallyRotatedBlock(DecorationsFurnitureModule.CANDLES_1.value(), provider);
    }

    private void bonePile(DeferredBlock<BonePileBlock> holder, BlockModelGenerators blockModels) {
        if(!DecorationsFurnitureModule.BONE_PILE_SKELETON.is(holder)) {
            var slot = TextureSlot.create("bone_pile");

            var template = ExtendedModelTemplateBuilder
                    .builder()
                    .parent(ModelLocationUtils.getModelLocation(DecorationsFurnitureModule.BONE_PILE_SKELETON.value()))
                    .requiredTextureSlot(TextureSlot.PARTICLE)
                    .requiredTextureSlot(slot);

            template.build().create(
                    holder.value(),
                    TextureMapping.particle(TextureMapping.getBlockTexture(holder.value(), "_particle"))
                            .put(slot, TextureMapping.getBlockTexture(holder.value())),
                    blockModels.modelOutput
            );
        }

        blockModels.createNonTemplateHorizontalBlock(holder.value());
    }

    private void simpleBlockEntity(DeferredBlock<? extends SimpleBlockEntityBlock> block, SimpleBlockEntitySpecialRenderer.Unbaked specialModel, BlockModelGenerators blockModels) {
        var blockModel = ExtendedModelTemplateBuilder
                .builder()
                .requiredTextureSlot(TextureSlot.PARTICLE)
                .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, transform -> transform
                        .rotation(75F, 45F, 0F)
                        .translation(0F, 3F, 4F)
                        .scale(.375F, .375F, .375F)
                )
                .transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND, transform -> transform
                        .rotation(75F, 45F, 0F)
                        .translation(0F, 3F, 4F)
                        .scale(.375F, .375F, .375F)
                )
                .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND, transform -> transform
                        .rotation(0F, 135F, 0F)
                        .translation(0F, 2.5F, 0F)
                        .scale(.4F, .4F, .4F)
                )
                .transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND, transform -> transform
                        .rotation(0F, 135F, 0F)
                        .translation(0F, 2.5F, 0F)
                        .scale(.4F, .4F, .4F)
                )
                .transform(ItemDisplayContext.HEAD, transform -> transform
                        .rotation(0F, 0F, 0F)
                        .translation(0F, 30F, 0F)
                        .scale(1F, 1F, 1F)
                )
                .transform(ItemDisplayContext.GROUND, transform -> transform
                        .rotation(0F, 0F, 0F)
                        .translation(0F, 6F, 0F)
                        .scale(.25F, .25F, .25F)
                )
                .transform(ItemDisplayContext.FIXED, transform -> transform
                        .rotation(-90F, 0F, 0F)
                        .translation(0F, 0F, -23F)
                        .scale(1F, 1F, 1F)
                )
                .transform(ItemDisplayContext.GUI, transform -> transform
                        .rotation(30F, -135F, 0F)
                        .translation(0F, -3F, 0F)
                        .scale(.5F, .5F, .5F)
                )
                .build()
                .create(block.value(), TextureMapping.particle(TextureMapping.getBlockTexture(block.value(), "_particle")), blockModels.modelOutput);

        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(
                block.value(),
                BlockModelGenerators.plainVariant(blockModel)
        ));

        blockModels.itemModelOutput.accept(block.value().asItem(), ItemModelUtils.specialModel(blockModel, specialModel));
    }

    private void plushie(BlockModelGenerators blockModels) {
        var model = ExtendedModelTemplateBuilder.builder()
                .parent(Identifier.withDefaultNamespace("block/block"))
                .requiredTextureSlot(TextureSlot.PARTICLE)
                .build()
                .create(DecorationsFurnitureModule.PLUSHIE_BLOCK.value(), TextureMapping.particle(Blocks.WHITE_WOOL), blockModels.modelOutput);

        var variant = BlockModelGenerators.plainVariant(model);
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(DecorationsFurnitureModule.PLUSHIE_BLOCK.value(), variant));
        blockModels.itemModelOutput.accept(DecorationsFurnitureModule.PLUSHIE_BLOCK.value().asItem(), ItemModelUtils.specialModel(model, new PlushieSpecialModelRenderer.Unbaked()));
    }

    private MultiVariantGenerator blockState(Holder<Block> holder) {
        return MultiVariantGenerator.dispatch(holder.value(), BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(holder.value())));
    }

    private <TBlock extends Block & Stackable> void stackedItemModel(DeferredBlock<TBlock> holder, BlockModelGenerators blockModels) {
        blockModels.registerSimpleItemModel(
                holder.value(),
                ModelLocationUtils.getModelLocation(holder.value(), "_" + holder.value().getStackableProperty().max)
        );
    }

    private <TBlock extends Block & Dyeable> void dyedColorItemModel(DeferredBlock<TBlock> holder, BlockModelGenerators blockModels) {
        blockModels.registerSimpleTintedItemModel(
                holder.value(),
                ModelLocationUtils.getModelLocation(holder.value()),
                new DyeColorItemTintSource()
        );
    }

    private <TBlock extends Block & Dyeable & Stackable> void stackedDyedColorItemModel(DeferredBlock<TBlock> holder, BlockModelGenerators blockModels) {
        blockModels.registerSimpleTintedItemModel(
                holder.value(),
                ModelLocationUtils.getModelLocation(holder.value(), "_" + holder.value().getStackableProperty().max),
                new DyeColorItemTintSource()
        );
    }

    private <TTemplate extends Block & Stackable, TBlock extends TTemplate> void createStackedTemplatedModels(DeferredBlock<TTemplate> templateHolder, DeferredBlock<TBlock> holder, TextureSlot slot, boolean replaceParticle, BiConsumer<Identifier, ModelInstance> modelOutput) {
        if(templateHolder.is(holder)) {
            return;
        }

        var block = holder.value();
        var stackProperty = block.getStackableProperty();
        var templateModelPath = ModelLocationUtils.getModelLocation(templateHolder.value());

        var textures = new TextureMapping()
                .put(slot, TextureMapping.getBlockTexture(block));

        if(replaceParticle) {
            textures = textures.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(block, "_particle"));
        }

        for(var i = stackProperty.min; i < stackProperty.max + 1; i++) {
            var template = ExtendedModelTemplateBuilder
                    .builder()
                    .requiredTextureSlot(slot)
                    .parent(templateModelPath.withSuffix("_" + i))
                    .suffix("_" + i);

            if(replaceParticle) {
                template = template.requiredTextureSlot(TextureSlot.PARTICLE);
            }

            template.build().create(block, textures, modelOutput);
        }
    }

    private void createdDyeColorModel(Identifier baseModelPath, Material baseTexturePath, TextureSlot slot, boolean replaceParticle, BiConsumer<Identifier, ModelInstance> modelOutput) {
        var textures = new TextureMapping().put(slot, new Material(baseTexturePath.sprite().withSuffix("_tint")));

        if(replaceParticle) {
            textures = textures.put(TextureSlot.PARTICLE, new Material(baseTexturePath.sprite().withSuffix("_tint_particle")));
        }

        var template = ExtendedModelTemplateBuilder
                .builder()
                .requiredTextureSlot(slot)
                .parent(baseModelPath);

        if(replaceParticle) {
            template = template.requiredTextureSlot(TextureSlot.PARTICLE);
        }

        template.build().create(baseModelPath.withSuffix("_tint"), textures, modelOutput);
    }

    private <TBlock extends Block & Dyeable> void createdDyeColorModel(DeferredBlock<TBlock> holder, TextureSlot slot, boolean replaceParticle, BiConsumer<Identifier, ModelInstance> modelOutput) {
        createdDyeColorModel(
                ModelLocationUtils.getModelLocation(holder.value()),
                TextureMapping.getBlockTexture(holder.value()),
                slot,
                replaceParticle,
                modelOutput
        );
    }

    private <TBlock extends Block & Stackable & Dyeable> void createdStackedDyeColorModels(DeferredBlock<TBlock> holder, TextureSlot slot, boolean replaceParticle, BiConsumer<Identifier, ModelInstance> modelOutput) {
        var block = holder.value();
        var stackProperty = block.getStackableProperty();

        for(var i = stackProperty.min; i < stackProperty.max + 1; i++) {
            createdDyeColorModel(
                    ModelLocationUtils.getModelLocation(block, "_" + i),
                    TextureMapping.getBlockTexture(block),
                    slot,
                    replaceParticle,
                    modelOutput
            );
        }
    }

    private <TBlock extends Block & Stackable> PropertyDispatch<VariantMutator> stackableDispatch(DeferredBlock<TBlock> holder) {
        return PropertyDispatch.modify(holder.value().getStackableProperty()).generate(count -> variant -> variant.withModel(variant.modelLocation().withSuffix("_" + count)));
    }

    private <TBlock extends Block & Dyeable> PropertyDispatch<VariantMutator> dyedColorDispatch(DeferredBlock<TBlock> holder) {
        return PropertyDispatch.modify(dyedColorProperty(holder)).generate(color -> variant -> {
            if(holder.value() instanceof Dyeable.WithNone && !holder.value().isBlankDyedColor(color)) {
                return variant.withModel(variant.modelLocation().withSuffix("_tint"));
            }

            return variant;
        });
    }

    private <TBlock extends Block & Dyeable> EnumProperty<Dyeable.DyedColor> dyedColorProperty(DeferredBlock<TBlock> holder) {
        return holder.value() instanceof Dyeable.WithNone ? Dyeable.WithNone.DYED_COLOR : Dyeable.Colored.DYED_COLOR;
    }
}
