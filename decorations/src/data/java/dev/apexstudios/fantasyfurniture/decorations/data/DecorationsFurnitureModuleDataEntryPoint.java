package dev.apexstudios.fantasyfurniture.decorations.data;

import dev.apexstudios.apexcore.core.client.DyeColorItemTintSource;
import dev.apexstudios.apexcore.lib.block.Dyeable;
import dev.apexstudios.apexcore.lib.data.ProviderTypes;
import dev.apexstudios.apexcore.lib.data.ResourceGenerator;
import dev.apexstudios.apexcore.lib.data.provider.RecipeProvider;
import dev.apexstudios.apexcore.lib.multiblock.MultiBlock;
import dev.apexstudios.fantasyfurniture.decorations.DecorationsFurnitureModule;
import dev.apexstudios.fantasyfurniture.decorations.block.BonePileBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.BookStackBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.BowlBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.ChalicesBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.CoinStackBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.MuffinsBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.MushroomsBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.PlatterBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.SoulGemsBlock;
import dev.apexstudios.fantasyfurniture.decorations.block.Stackable;
import dev.apexstudios.fantasyfurniture.decorations.block.TankardsBlock;
import dev.apexstudios.fantasyfurniture.decorations.cookie.CookieJarBlock;
import dev.apexstudios.fantasyfurniture.station.FurnitureStationRecipeBuilder;
import dev.apexstudios.fantasyfurniture.station.FurnitureStationSetup;
import dev.apexstudios.fantasyfurniture.util.FurnitureClientDataUtil;
import dev.apexstudios.placementvisualizer.api.BlockItemPlacementEvent;
import dev.apexstudios.registree.api.holder.DeferredBlock;
import java.util.function.BiConsumer;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.block.model.VariantMutator;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.EnumProperty;
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
                    blockModels.createNonTemplateHorizontalBlock(DecorationsFurnitureModule.BOLTS_OF_CLOTH.value());
                    bowl(DecorationsFurnitureModule.BOWL, blockModels);
                    bowl(DecorationsFurnitureModule.BEETROOT_SOUP_BOWL, blockModels);
                    bowl(DecorationsFurnitureModule.MUSHROOM_STEW_BOWL, blockModels);
                    coinStack(DecorationsFurnitureModule.GOLDEN_COIN_STACK, blockModels);
                    coinStack(DecorationsFurnitureModule.IRON_COIN_STACK, blockModels);
                    cookieJar(blockModels);
                    blockModels.createNonTemplateHorizontalBlock(DecorationsFurnitureModule.BREWING_CAULDRON.value());
                    blockModels.createNonTemplateHorizontalBlock(DecorationsFurnitureModule.GRAVESTONE.value());
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
                    DecorationsFurnitureModule.REGISTREE.listElements(Registries.BLOCK).map(Holder::value).forEach(block -> {
                        provider.tag(BlockTags.MINEABLE_WITH_AXE).withElement(block);

                        if(block instanceof Dyeable) {
                            provider.tag(Tags.Blocks.DYED).withElement(block);
                        }

                        if(block instanceof MultiBlock || block instanceof Stackable || DecorationsFurnitureModule.BRONZE_CHAIN.is(block)) {
                            provider.tag(BlockItemPlacementEvent.RENDERABLES).withElement(block);
                        }

                        if(block instanceof MultiBlock) {
                            provider.tag(Tags.Blocks.RELOCATION_NOT_SUPPORTED).withElement(block);
                        }
                    });

                    provider.tag(Tags.Blocks.CHAINS).withElement(DecorationsFurnitureModule.BRONZE_CHAIN);
                    provider.tag(Tags.Blocks.CHAINS).withElement(DecorationsFurnitureModule.BRONZE_CHAIN);
                })
                .providing(ProviderTypes.ITEM_TAGS, (context, provider) -> {
                    Dyeable.dyeableItems(DecorationsFurnitureModule.REGISTREE)
                           .map(ItemLike::asItem)
                           .forEach(block -> provider.tag(Tags.Items.DYED).withElement(block));

                    provider.tag(ItemTags.CHAINS).withElement(DecorationsFurnitureModule.BRONZE_CHAIN.asItem());
                    provider.tag(Tags.Items.CHAINS).withElement(DecorationsFurnitureModule.BRONZE_CHAIN.asItem());
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
        var baseTexturePath = TextureMapping.getBlockTexture(DecorationsFurnitureModule.CHALICES_3.value());
        var cupSlot = TextureSlot.create("cup");
        var fluidSlot = TextureSlot.create("fluid");
        var textures = TextureMapping.particle(baseTexturePath.withSuffix("_particle"))
                                     .put(cupSlot, baseTexturePath)
                                     .put(fluidSlot, baseTexturePath.withSuffix("_tint"));

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

    private <TTemplate extends Block & Stackable, TBlock extends TTemplate> void createStackedTemplatedModels(DeferredBlock<TTemplate> templateHolder, DeferredBlock<TBlock> holder, TextureSlot slot, boolean replaceParticle, BiConsumer<ResourceLocation, ModelInstance> modelOutput) {
        if(templateHolder.is(holder)) {
            return;
        }

        var block = holder.value();
        var stackProperty = block.getStackableProperty();
        var templateModelPath = ModelLocationUtils.getModelLocation(templateHolder.value());
        var baseTexturePath = TextureMapping.getBlockTexture(block);

        var textures = new TextureMapping()
                .put(slot, baseTexturePath);

        if(replaceParticle) {
            textures = textures.put(TextureSlot.PARTICLE, baseTexturePath.withSuffix("_particle"));
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

    private void createdDyeColorModel(ResourceLocation baseModelPath, ResourceLocation baseTexturePath, TextureSlot slot, boolean replaceParticle, BiConsumer<ResourceLocation, ModelInstance> modelOutput) {
        var textures = new TextureMapping().put(slot, baseTexturePath.withSuffix("_tint"));

        if(replaceParticle) {
            textures = textures.put(TextureSlot.PARTICLE, baseTexturePath.withSuffix("_tint_particle"));
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

    private <TBlock extends Block & Dyeable> void createdDyeColorModel(DeferredBlock<TBlock> holder, TextureSlot slot, boolean replaceParticle, BiConsumer<ResourceLocation, ModelInstance> modelOutput) {
        createdDyeColorModel(
                ModelLocationUtils.getModelLocation(holder.value()),
                TextureMapping.getBlockTexture(holder.value()),
                slot,
                replaceParticle,
                modelOutput
        );
    }

    private <TBlock extends Block & Stackable & Dyeable> void createdStackedDyeColorModels(DeferredBlock<TBlock> holder, TextureSlot slot, boolean replaceParticle, BiConsumer<ResourceLocation, ModelInstance> modelOutput) {
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
